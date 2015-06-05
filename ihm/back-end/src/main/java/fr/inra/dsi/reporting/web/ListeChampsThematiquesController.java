package fr.inra.dsi.reporting.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.inra.dsi.reporting.dataservice.IRapportChampThematiqueService;
import fr.inra.dsi.reporting.dto.RapportDto;
import fr.inra.dsi.reporting.dto.SearchDto;
import fr.inra.dsi.reporting.exception.AbstractControllerException;
import fr.inra.dsi.reporting.exception.ValidationException;
import fr.inra.dsi.reporting.model.RapportChampThematique;
import fr.inra.dsi.reporting.security.Constant;
import fr.inra.dsi.reporting.util.WebConstantUtil;

/**
 * Classe controlleur traitant l'affichage des champs thematiques
 * 
 * @author cebri
 */
@Controller
@RequestMapping(value=WebConstantUtil.RAPPORT_LISTE_CT_PATH)
public class ListeChampsThematiquesController extends AbstractRapportController<RapportChampThematique> {

    /**
     * Service
     */
	@Autowired
	public transient IRapportChampThematiqueService champthematiqueService;
	
	/**
	 * Constructeur
	 */
	public ListeChampsThematiquesController() {
		super(RapportChampThematique.class, WebConstantUtil.RAPPORT_LISTE_CT_PATH,
		        WebConstantUtil.CLE_PROP_RAPPORT_LISTE_CT, WebConstantUtil.RAPPORT_LISTE_CT_COLONNE_KEY);
	}

	/**
	 * {@inheritDoc} 
	 */
    @Override
    public List<RapportChampThematique> getAllData(HttpServletRequest request) throws ValidationException {
        return champthematiqueService.getAllRapports(RapportChampThematique.class);
    }

    /**
     * {@inheritDoc} 
     */
    @Override
    public List<RapportChampThematique> searchData(SearchDto search, HttpServletRequest request)
            throws ValidationException {
        return champthematiqueService.search(search, RapportChampThematique.class, 
                WebConstantUtil.RAPPORT_LISTE_CT_COLONNE_KEY);
    }

    /**
     * {@inheritDoc}
     */
    @PreAuthorize("hasRole('"+Constant.ROLE_ADMIN+"')"
            + " or hasPermission('"+WebConstantUtil.RAPPORT_LISTE_CT_PATH+"', 'RapportChampThematique', '"+Constant.PERMISSION_READ_REPORT+"')")
    public RapportDto getRapport(HttpServletRequest request) throws AbstractControllerException {
        return super.getRapport(request);
    }
    
    /**
     * {@inheritDoc}
     */
    @PreAuthorize("hasRole('"+Constant.ROLE_ADMIN+"')"
            + " or hasPermission('"+WebConstantUtil.RAPPORT_LISTE_CT_PATH+"', 'RapportChampThematique', '"+Constant.PERMISSION_READ_REPORT+"')")
    public RapportDto exportRapport(SearchDto search, HttpServletRequest request) throws AbstractControllerException {
        return super.exportRapport(search, request);
    }

    /**
     * Setter
     */
    public void setChampthematiqueService(
            IRapportChampThematiqueService champthematiqueService) {
        this.champthematiqueService = champthematiqueService;
    }
    
}
