package fr.inra.dsi.reporting.web;

import java.util.List;

import javax.management.ListenerNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import fr.inra.dsi.reporting.dataservice.IRapportDescriptionService;
import fr.inra.dsi.reporting.dto.RapportDto;
import fr.inra.dsi.reporting.dto.factory.RapportDtoFactory;
import fr.inra.dsi.reporting.exception.ControllerException;
import fr.inra.dsi.reporting.exception.ValidationException;
import fr.inra.dsi.reporting.exception.json.BasicFeedback;
import fr.inra.dsi.reporting.model.RapportDescription;
import fr.inra.dsi.reporting.security.INRAUser;
import fr.inra.dsi.reporting.service.ISecurityManagerService;
import fr.inra.dsi.reporting.util.ControllerUtil;
import fr.inra.dsi.reporting.util.WebConstantUtil;

/**
 * Classe controlleur traitant l'affichage de la page d'accueil.
 * 
 * @author niche
 */
@Controller
public class RapportDescriptionController {

	private static final Logger LOGGER = Logger.getLogger(RapportDescriptionController.class);

	@Value("${reporting.rapportDescritpion.class.exclude}")
	private transient String classToExclude; 
	
	@Autowired
	private IRapportDescriptionService rapportDescriptionService;
	 
	@Autowired
	private transient MessageSource messageSource;
	
    @Autowired
    private ISecurityManagerService managerService;
	 
  
  /**
	 * Constructeur vide.
	 */
	public RapportDescriptionController() {
	    super();
  }
	
	
	/**
	 * Recupere la liste de tous les rapports.
	 * 
	 * @param begin
	 * @param end
	 * @param response
	 * @return {@link ListenerNotFoundException}
	 * @throws ValidationException
	 */
    @RequestMapping(value = WebConstantUtil.RAPPORT_LISTE_RAP_DESC_FIND_PATH, method=RequestMethod.GET)
    @ResponseBody
    public RapportDto findListeRapportDescription(HttpServletResponse response, HttpServletRequest request) throws ControllerException, ValidationException {
        //Gestion du role admin pour la page d accueil
    	List<String> listCle;
    	List<RapportDescription> rapportDescs;
        if (ControllerUtil.isAdmin()){
        	listCle=WebConstantUtil.RAPPORT_DESC_COLONNE_KEY_ADMIN;
        	rapportDescs = rapportDescriptionService.getAllRapportDescriptions();
        	LOGGER.debug("Envoi du tableau pour les colonnes d un admin");
        }else{
           	listCle=WebConstantUtil.RAPPORT_DESC_COLONNE_KEY;
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            rapportDescs = managerService.getAllRapportDescription((INRAUser)auth.getPrincipal());
            LOGGER.debug("Envoi du tableau pour les colonnes d un utilisateur non admin");
        }
        
        //appel generique pour la creation d un rapport 
       	RapportDto rapportDto;
        try {
            rapportDto = RapportDtoFactory.getRapportDto(messageSource, 
            		listCle, WebConstantUtil.CLE_PROP_RAPPORT_DESC, rapportDescs,
            		new RapportDescription(messageSource.getMessage(WebConstantUtil.CLE_PROP_RAPPORT_DESC_TITRE, 
            				null , null), null, null, false, null, null), RapportDescription.class);
        } catch (NoSuchFieldException | SecurityException
                | NoSuchMessageException e) {
            String message = messageSource.getMessage("message.error.internal", null, null);
            throw new ControllerException(message, e, new BasicFeedback(message), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    	return rapportDto;
    }
    
	/**
	 * Active/desactive une liste de rapport
	 * 
	 * @param ids Liste des identifiants des description de rapport a mettre a jour
	 * @param value 
	 */
    @RequestMapping(value = WebConstantUtil.RAPPORT_DESC_UPDATE_AVAILABILITY, method=RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public void updateRapportAvailibility(@PathVariable("ids") List<Integer> ids, @PathVariable("value") boolean value) {
    	rapportDescriptionService.updateRapportsActif(ids, value);
    }

    public void setManagerService(ISecurityManagerService managerService) {
        this.managerService = managerService;
    }
	
}
