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
import fr.inra.dsi.reporting.model.RapportParticipationAgentSurActivite;
import fr.inra.dsi.reporting.security.Constant;
import fr.inra.dsi.reporting.service.ISecurityRapportParticipationAgentSurActiviteService;
import fr.inra.dsi.reporting.util.WebConstantUtil;

/**
 * Controlleur pour le rapport des participations des agents par activités.
 * @author gugau
 *
 */
@Controller
@RequestMapping(WebConstantUtil.RAPPORT_LISTE_PARTICIPATION_AGENT_PATH)
public class ListeRapportParticipationAgentSurActiviteController
    extends AbstractRapportController<RapportParticipationAgentSurActivite>{

    @Autowired
    private ISecurityRapportParticipationAgentSurActiviteService rapportParticipationService;
    
    /**
     * Constructeur.
     */
    public ListeRapportParticipationAgentSurActiviteController() {
        super(RapportParticipationAgentSurActivite.class, WebConstantUtil.RAPPORT_LISTE_PARTICIPATION_AGENT_PATH,
                WebConstantUtil.CLE_PROP_RAPPORT_LISTE_PARTICIPATION_AGENT,
                WebConstantUtil.RAPPORT_LISTE_PARTICIPATION_AGENT_COLONNE_KEY);
    }
    
    /**
     * {@inheritDoc}
     */
    @PreAuthorize("hasRole('"+Constant.ROLE_ADMIN+"')"
            + " or hasPermission('"+WebConstantUtil.RAPPORT_LISTE_PARTICIPATION_AGENT_PATH+"', 'RapportParticipationAgentSurActivite', '"+Constant.PERMISSION_READ_REPORT+"')")
    public RapportDto getRapport(HttpServletRequest request) throws AbstractControllerException {
        return super.getRapport(request);
    }
    
    /**
     * {@inheritDoc}
     */
    @PreAuthorize("hasRole('"+Constant.ROLE_ADMIN+"')"
            + " or hasPermission('"+WebConstantUtil.RAPPORT_LISTE_PARTICIPATION_AGENT_PATH+"', 'RapportParticipationAgentSurActivite', '"+Constant.PERMISSION_READ_REPORT+"')")
    public RapportDto exportRapport(SearchDto search, HttpServletRequest request) throws AbstractControllerException {
        return super.exportRapport(search, request);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<RapportParticipationAgentSurActivite> getAllData(
            HttpServletRequest request) throws ValidationException {
        return rapportParticipationService.getAllRapports(getUserInra(), RapportParticipationAgentSurActivite.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RapportParticipationAgentSurActivite> searchData(
            SearchDto search, HttpServletRequest request)
            throws ValidationException {
        return rapportParticipationService.search(getUserInra(), search, RapportParticipationAgentSurActivite.class,
                WebConstantUtil.RAPPORT_LISTE_PARTICIPATION_AGENT_COLONNE_KEY);
    }

    public void setRapportParticipationService(
            ISecurityRapportParticipationAgentSurActiviteService rapportParticipationService) {
        this.rapportParticipationService = rapportParticipationService;
    }

}
