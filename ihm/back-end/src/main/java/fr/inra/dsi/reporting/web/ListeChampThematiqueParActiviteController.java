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
import fr.inra.dsi.reporting.model.RapportChampThematiqueParActivite;
import fr.inra.dsi.reporting.security.Constant;
import fr.inra.dsi.reporting.service.ISecurityRapportChampThematiqueParActiviteService;
import fr.inra.dsi.reporting.util.WebConstantUtil;

/**
 * Controller permettant de récupérer les données du rapport des CT avec participation des unités.
 * @author gugau
 *
 */
@Controller
@RequestMapping(value=WebConstantUtil.RAPPORT_LISTE_CT_PARTICIPATION_UNITE_PATH)
public class ListeChampThematiqueParActiviteController extends
        AbstractRapportController<RapportChampThematiqueParActivite> {
    
    /**
     * rapportCtService
     */
    @Autowired
    private ISecurityRapportChampThematiqueParActiviteService rapportCtService;
    
    public ListeChampThematiqueParActiviteController() {
        super(RapportChampThematiqueParActivite.class, WebConstantUtil.RAPPORT_LISTE_CT_PARTICIPATION_UNITE_PATH,
                WebConstantUtil.CLE_PROP_RAPPORT_LISTE_CT_PARTICIPATION_UNITE,
                WebConstantUtil.RAPPORT_LISTE_CT_PARTICIPATION_UNITE_COLONNE_KEY);
    }

    /**
     * {@inheritDoc}
     */
    @PreAuthorize("hasRole('"+Constant.ROLE_ADMIN+"')"
            + " or hasPermission('"+WebConstantUtil.RAPPORT_LISTE_CT_PARTICIPATION_UNITE_PATH+"', 'RapportChampThematiqueParActivite', '"+Constant.PERMISSION_READ_REPORT+"')")
    public RapportDto getRapport(HttpServletRequest request) throws AbstractControllerException {
        return super.getRapport(request);
    }
    
    /**
     * {@inheritDoc}
     */
    @PreAuthorize("hasRole('"+Constant.ROLE_ADMIN+"')"
            + " or hasPermission('"+WebConstantUtil.RAPPORT_LISTE_CT_PARTICIPATION_UNITE_PATH+"', 'RapportChampThematiqueParActivite', '"+Constant.PERMISSION_READ_REPORT+"')")
    public RapportDto exportRapport(SearchDto search, HttpServletRequest request) throws AbstractControllerException {
        return super.exportRapport(search, request);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RapportChampThematiqueParActivite> getAllData(
            HttpServletRequest request) throws ValidationException {
        return rapportCtService.getAllRapports(getUserInra(), RapportChampThematiqueParActivite.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RapportChampThematiqueParActivite> searchData(SearchDto search,
            HttpServletRequest request) throws ValidationException {
        return rapportCtService.search(getUserInra(), search, RapportChampThematiqueParActivite.class,
                WebConstantUtil.RAPPORT_LISTE_CT_PARTICIPATION_UNITE_COLONNE_KEY);
    }

    /**
     * Setter
     */
    public void setRapportCtService(
            ISecurityRapportChampThematiqueParActiviteService rapportCtService) {
        this.rapportCtService = rapportCtService;
    }

}
