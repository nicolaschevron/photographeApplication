package fr.inra.dsi.reporting.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.inra.dsi.reporting.dataservice.IRapportDescriptionService;
import fr.inra.dsi.reporting.dto.RapportDto;
import fr.inra.dsi.reporting.dto.SearchDto;
import fr.inra.dsi.reporting.dto.factory.RapportDtoFactory;
import fr.inra.dsi.reporting.exception.AbstractControllerException;
import fr.inra.dsi.reporting.exception.ControllerException;
import fr.inra.dsi.reporting.exception.ValidationException;
import fr.inra.dsi.reporting.exception.json.BasicFeedback;
import fr.inra.dsi.reporting.model.RapportDescription;
import fr.inra.dsi.reporting.security.INRAUser;
import fr.inra.dsi.reporting.util.ControllerUtil;

/**
 * Classe de base pour réaliser le controller d'un rapport. 
 * 
 * @author gugau
 *
 * @param <E> le type de données
 */
public abstract class AbstractRapportController<E> {

    private Class<E> clazz;
    private String restActionURL;
    private String titlePropKey;
    private List<String> columnsTitles;
    
    @Autowired
    private transient MessageSource messageSource;
    
    @Autowired
    private IRapportDescriptionService rapportDescriptionService;

    /**
     * Constructeur
     * @param clazz le type de données (entity)
     * @param restActionURL URL de base de l'action
     * @param titlePropKey titre du rapport
     * @param columnsTitles nom des colonnes du rapport
     */
    public AbstractRapportController(Class<E> clazz, String restActionURL, String titlePropKey,
            List<String> columnsTitles){
        super();
        this.clazz = clazz;
        this.restActionURL = restActionURL;
        this.titlePropKey = titlePropKey;
        this.columnsTitles = columnsTitles;
    }
    
    /**
     * Renvoie toutes les données.
     * @param request
     * @return
     * @throws AbstractControllerException
     */
    @RequestMapping(value = "/all", method=RequestMethod.GET)
    @ResponseBody
    public RapportDto getRapport(HttpServletRequest request) throws AbstractControllerException {
        return buildResponse(getAllData(request));
    }
    
    /**
     * Effectue un export CSV des données
     * @param search les paramètres de recherche
     * @param request
     * @return
     * @throws AbstractControllerException
     */
    @RequestMapping(value = "/export", method=RequestMethod.GET, produces="text/csv")
    @ResponseBody
    public RapportDto exportRapport(SearchDto search, HttpServletRequest request) throws AbstractControllerException {
        return buildResponse(searchData(search, request));
    }
    
    /**
     * Renvoie toutes les données du rapport (toutes les lignes).
     * @param request
     * @return
     * @throws ValidationException
     */
    public abstract List<E> getAllData(HttpServletRequest request) throws ValidationException;
    
    /**
     * 
     * Effectue une recherche dans les données du rapport.
     * @param search les paramètres de recherche
     * @param request
     * @return
     * @throws ValidationException
     */
    public abstract List<E> searchData(SearchDto search,HttpServletRequest request) throws ValidationException;
    
    /**
     * Setter
     */
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * Setter
     */
    public void setRapportDescriptionService(
            IRapportDescriptionService rapportDescriptionService) {
        this.rapportDescriptionService = rapportDescriptionService;
    }
    
    /**
     * Cette methode retourne l utilisateur en session
     * @return {@link INRAUser}
     */
    public INRAUser getUserInra(){
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return (INRAUser) auth.getPrincipal();
    }
    
    private RapportDto buildResponse(List<E> rapports) throws ControllerException{
        RapportDescription rapportDesc = rapportDescriptionService
                .getRapportDescriptionForClazzAndRestCall(clazz.getSimpleName(), restActionURL);
        
        if (rapportDesc == null) {
            throw new ControllerException(messageSource.getMessage("message.rapport.notexist", null, null), HttpStatus.NOT_FOUND);
        } else {
            if (rapportDesc.isActif() || ControllerUtil.isAdmin()) {
                try {
                    return RapportDtoFactory.getRapportDto(messageSource, columnsTitles, 
                            titlePropKey, rapports, rapportDesc, this.clazz);
                } catch (NoSuchFieldException | SecurityException e) {
                    String message = messageSource.getMessage("message.error.internal", null, null);
                    throw new ControllerException(message, e, new BasicFeedback(message), HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                throw new ControllerException(messageSource.getMessage("message.rapport.disabled", null, null), HttpStatus.NOT_FOUND);
            }
        }
    }
    
}
