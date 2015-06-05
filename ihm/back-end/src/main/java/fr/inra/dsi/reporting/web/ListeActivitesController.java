package fr.inra.dsi.reporting.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.inra.dsi.reporting.dataservice.IRapportListeActivitesService;
import fr.inra.dsi.reporting.dto.RapportDto;
import fr.inra.dsi.reporting.dto.SearchDto;
import fr.inra.dsi.reporting.exception.AbstractControllerException;
import fr.inra.dsi.reporting.exception.ValidationException;
import fr.inra.dsi.reporting.model.RapportListeActivites;
import fr.inra.dsi.reporting.security.Constant;
import fr.inra.dsi.reporting.util.WebConstantUtil;



/**
 * Classe controlleur traitant l'affichage des listes d'activites.
 * 
 * @author cebri
 */
@Controller
@RequestMapping(value=WebConstantUtil.RAPPORT_LISTE_ACT_PATH)
public class ListeActivitesController extends AbstractRapportController<RapportListeActivites> {
    
    /**
     * Service
     */
	@Autowired
    public transient IRapportListeActivitesService listeActivitesService;
    
	/**
	 * Constructeur
	 */
	public ListeActivitesController() {
	    super(RapportListeActivites.class, WebConstantUtil.RAPPORT_LISTE_ACT_PATH,
	            WebConstantUtil.CLE_PROP_RAPPORT_LISTE_ACT, WebConstantUtil.RAPPORT_LISTE_ACT_COLONNE_KEY);
    }

	/**
     * {@inheritDoc} 
     */
    @Override
    public List<RapportListeActivites> getAllData(HttpServletRequest request) throws ValidationException {
        return listeActivitesService.getAllRapports(RapportListeActivites.class);
    }
    
    /**
     * {@inheritDoc} 
     */
    @Override
    public List<RapportListeActivites> searchData(SearchDto search, HttpServletRequest request)
            throws ValidationException {
        return listeActivitesService.search(search, RapportListeActivites.class, 
                WebConstantUtil.RAPPORT_LISTE_ACT_COLONNE_KEY);
    }

    /**
     * {@inheritDoc}
     */
    @PreAuthorize("hasRole('"+Constant.ROLE_ADMIN+"')"
            + " or hasPermission('"+WebConstantUtil.RAPPORT_LISTE_ACT_PATH+"', 'RapportListeActivites', '"+Constant.PERMISSION_READ_REPORT+"')")
    public RapportDto getRapport(HttpServletRequest request) throws AbstractControllerException {
        return super.getRapport(request);
    }
    
    /**
     * {@inheritDoc}
     */
    @PreAuthorize("hasRole('"+Constant.ROLE_ADMIN+"')"
            + " or hasPermission('"+WebConstantUtil.RAPPORT_LISTE_ACT_PATH+"', 'RapportListeActivites', '"+Constant.PERMISSION_READ_REPORT+"')")
    public RapportDto exportRapport(SearchDto search, HttpServletRequest request) throws AbstractControllerException {
        return super.exportRapport(search, request);
    }
    
    /**
     * Setter
     */
    public void setListeActivitesService(
            IRapportListeActivitesService listeActivitesService) {
        this.listeActivitesService = listeActivitesService;
    }
    
}
