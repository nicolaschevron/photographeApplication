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
import fr.inra.dsi.reporting.model.RapportParticipationAgtChampThematique;
import fr.inra.dsi.reporting.security.Constant;
import fr.inra.dsi.reporting.service.ISecurityRapportParticipationAgtChampThematiqueService;
import fr.inra.dsi.reporting.util.WebConstantUtil;

/**
 * Controlleur pour le rapport de participation des agents sur les champs thématiques.
 * @author gugau
 *
 */
@Controller
@RequestMapping(WebConstantUtil.RAPPORT_PARTICIPATION_AGT_CT_PATH)
public class ListeParticipationAgtChampThematiqueController extends
        AbstractRapportController<RapportParticipationAgtChampThematique> {

    @Autowired
    ISecurityRapportParticipationAgtChampThematiqueService rapportParticipationAgtChampThematiqueService;
    
    /**
     * Constructeur.
     */
    public ListeParticipationAgtChampThematiqueController() {
        super(RapportParticipationAgtChampThematique.class, WebConstantUtil.RAPPORT_PARTICIPATION_AGT_CT_PATH,
                WebConstantUtil.CLE_RAPPORT_PARTICIPATION_AGT_CT, WebConstantUtil.RAPPORT_PARTICIPATION_AGT_CT_COLONNE_KEY);
    }

    /**
     * {@inheritDoc}
     */
    @PreAuthorize("hasRole('"+Constant.ROLE_ADMIN+"')"
            + " or hasPermission('"+WebConstantUtil.RAPPORT_PARTICIPATION_AGT_CT_PATH+"', 'RapportParticipationAgtChampThematique', '"+Constant.PERMISSION_READ_REPORT+"')")
    public RapportDto getRapport(HttpServletRequest request) throws AbstractControllerException {
        return super.getRapport(request);
    }
    
    /**
     * {@inheritDoc}
     */
    @PreAuthorize("hasRole('"+Constant.ROLE_ADMIN+"')"
            + " or hasPermission('"+WebConstantUtil.RAPPORT_PARTICIPATION_AGT_CT_PATH+"', 'RapportParticipationAgtChampThematique', '"+Constant.PERMISSION_READ_REPORT+"')")
    public RapportDto exportRapport(SearchDto search, HttpServletRequest request) throws AbstractControllerException {
        return super.exportRapport(search, request);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<RapportParticipationAgtChampThematique> getAllData(
            HttpServletRequest request) throws ValidationException {
        return rapportParticipationAgtChampThematiqueService.getAllRapports(getUserInra(), RapportParticipationAgtChampThematique.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RapportParticipationAgtChampThematique> searchData(
            SearchDto search, HttpServletRequest request)
            throws ValidationException {
        return rapportParticipationAgtChampThematiqueService.search(getUserInra(), search, RapportParticipationAgtChampThematique.class,
                WebConstantUtil.RAPPORT_PARTICIPATION_AGT_CT_COLONNE_KEY);
    }

}
