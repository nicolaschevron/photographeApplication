package fr.inra.dsi.reporting.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.inra.dsi.reporting.dataservice.IRapportListeActivitesParUniteService;
import fr.inra.dsi.reporting.dto.RapportDto;
import fr.inra.dsi.reporting.dto.SearchDto;
import fr.inra.dsi.reporting.exception.AbstractControllerException;
import fr.inra.dsi.reporting.exception.ValidationException;
import fr.inra.dsi.reporting.model.RapportListeActivitesParUnite;
import fr.inra.dsi.reporting.security.Constant;
import fr.inra.dsi.reporting.service.ISecurityListeActivitesParUniteService;
import fr.inra.dsi.reporting.service.ISecurityManagerService;
import fr.inra.dsi.reporting.util.WebConstantUtil;

/**
 * Classe controlleur traitant l'affichage des listes d'activites par unite.
 * 
 * @author gugau
 *
 */
@Controller
@RequestMapping(value=WebConstantUtil.RAPPORT_LISTE_ACT_BY_UNITE_PATH)
public class ListeActivitesParUniteController extends AbstractRapportController<RapportListeActivitesParUnite> {

    @Autowired
    private IRapportListeActivitesParUniteService listeActivitesParUniteService;

    @Autowired
    private ISecurityListeActivitesParUniteService securityListeActivitesParUniteService;
    
    @Autowired
    private ISecurityManagerService managerService;
    
    /**
     * Constructeur
     */
    public ListeActivitesParUniteController() {
        super(RapportListeActivitesParUnite.class, WebConstantUtil.RAPPORT_LISTE_ACT_BY_UNITE_PATH,
                WebConstantUtil.CLE_PROP_RAPPORT_LISTE_ACT_BY_UNITE, WebConstantUtil.RAPPORT_LISTE_ACT_BY_UNITE_COLONNE_KEY);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<RapportListeActivitesParUnite> getAllData(HttpServletRequest request)
            throws ValidationException {
    	List<RapportListeActivitesParUnite> result;
    	
    	if (request.isUserInRole(Constant.ROLE_ADMIN)){
    		result = listeActivitesParUniteService.getAllRapports(RapportListeActivitesParUnite.class);
    	} else {
    		result = securityListeActivitesParUniteService.getAllRapports(this.getUserInra(), RapportListeActivitesParUnite.class);
    	}

        return result;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<RapportListeActivitesParUnite> searchData(SearchDto search, HttpServletRequest request)
            throws ValidationException {
    	List<RapportListeActivitesParUnite> result;
    	if (request.isUserInRole(Constant.ROLE_ADMIN)){
    		result = listeActivitesParUniteService.search(search, 
            		RapportListeActivitesParUnite.class, WebConstantUtil.RAPPORT_LISTE_ACT_BY_UNITE_COLONNE_KEY);
    	} else {
    		result = securityListeActivitesParUniteService.search(this.getUserInra(), search, RapportListeActivitesParUnite.class, WebConstantUtil.RAPPORT_LISTE_ACT_BY_UNITE_COLONNE_KEY);
    	}
    		
        return result; 
    }
    
    /**
     * {@inheritDoc}
     */
    @PreAuthorize("hasRole('"+Constant.ROLE_ADMIN+"')"
            + " or hasPermission('"+WebConstantUtil.RAPPORT_LISTE_ACT_BY_UNITE_PATH+"', 'RapportListeActivitesParUnite', '"+Constant.PERMISSION_READ_REPORT+"')")
    public RapportDto getRapport(HttpServletRequest request) throws AbstractControllerException {
        return super.getRapport(request);
    }
    
    /**
     * {@inheritDoc}
     */
    @PreAuthorize("hasRole('"+Constant.ROLE_ADMIN+"')"
            + " or hasPermission('"+WebConstantUtil.RAPPORT_LISTE_ACT_BY_UNITE_PATH+"', 'RapportListeActivitesParUnite', '"+Constant.PERMISSION_READ_REPORT+"')")
    public RapportDto exportRapport(SearchDto search, HttpServletRequest request) throws AbstractControllerException {
        return super.exportRapport(search, request);
    }
    
    /**
     * Setter
     */
    public void setListeActivitesParUniteService(
            IRapportListeActivitesParUniteService listeActivitesParUniteService) {
        this.listeActivitesParUniteService = listeActivitesParUniteService;
    }

    /**
     * Setter
     */
    public void setSecurityListeActivitesParUniteService(
            ISecurityListeActivitesParUniteService securityListeActivitesParUniteService) {
        this.securityListeActivitesParUniteService = securityListeActivitesParUniteService;
    }

    /**
     * Setter
     */
    public void setManagerService(ISecurityManagerService managerService) {
        this.managerService = managerService;
    }

}
