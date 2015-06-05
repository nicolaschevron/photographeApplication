package fr.inra.dsi.reporting.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.inra.dsi.reporting.dto.RapportDto;
import fr.inra.dsi.reporting.dto.SearchDto;
import fr.inra.dsi.reporting.exception.AbstractControllerException;
import fr.inra.dsi.reporting.exception.ValidationException;
import fr.inra.dsi.reporting.model.RapportMotsClesParActivite;
import fr.inra.dsi.reporting.security.Constant;
import fr.inra.dsi.reporting.service.ISecurityRapportMotsClesParActiviteService;
import fr.inra.dsi.reporting.util.WebConstantUtil;

/**
 * Classe controlleur traitant l'affichage des mots cles par activite
 * 
 * @author cebri
 */
@Controller
@RequestMapping(value=WebConstantUtil.RAPPORT_MOTS_CLES_PAR_ACTIVITE_PATH)
public class ListeMotsClesParActiviteController extends AbstractRapportController<RapportMotsClesParActivite> {

	@Autowired
    private ISecurityRapportMotsClesParActiviteService rapportMotsClesService;
    
	public ListeMotsClesParActiviteController() {
		super(RapportMotsClesParActivite.class, WebConstantUtil.RAPPORT_MOTS_CLES_PAR_ACTIVITE_PATH, 
				WebConstantUtil.CLE_PROP_RAPPORT_MOTS_CLES_PAR_ACTIVITE, 
				WebConstantUtil.RAPPORT_MOTS_CLES_PAR_ACTIVITE_COLONNE_KEY);
	}

    /**
     * {@inheritDoc}
     */
    @PreAuthorize("hasRole('"+Constant.ROLE_ADMIN+"')"
            + " or hasPermission('"+WebConstantUtil.RAPPORT_MOTS_CLES_PAR_ACTIVITE_PATH+"', 'RapportMotsClesParActivite', '"+Constant.PERMISSION_READ_REPORT+"')")
    public RapportDto getRapport(HttpServletRequest request) throws AbstractControllerException {
        return super.getRapport(request);
    }
	
    /**
     * {@inheritDoc}
     */
    @PreAuthorize("hasRole('"+Constant.ROLE_ADMIN+"')"
            + " or hasPermission('"+WebConstantUtil.RAPPORT_MOTS_CLES_PAR_ACTIVITE_PATH+"', 'RapportMotsClesParActivite', '"+Constant.PERMISSION_READ_REPORT+"')")
    public RapportDto exportRapport(SearchDto search, HttpServletRequest request) throws AbstractControllerException {
        return super.exportRapport(search, request);
    }
    
	@Override
	public List<RapportMotsClesParActivite> getAllData(
			HttpServletRequest request) throws ValidationException {
		return rapportMotsClesService.getAllRapports(getUserInra(), RapportMotsClesParActivite.class);
	}

	@Override
	public List<RapportMotsClesParActivite> searchData(SearchDto search,
			HttpServletRequest request) throws ValidationException {
		return rapportMotsClesService.search(getUserInra(), search, RapportMotsClesParActivite.class, WebConstantUtil.RAPPORT_MOTS_CLES_PAR_ACTIVITE_COLONNE_KEY);
	}

    public void setRapportMotsClesService(
    		ISecurityRapportMotsClesParActiviteService rapportMotsClesService) {
        this.rapportMotsClesService = rapportMotsClesService;
    }
	
}
