package fr.inra.dsi.reporting.dataservice.impl;

import org.springframework.stereotype.Service;

import fr.inra.dsi.reporting.dataservice.IRapportParticipationAgentSurActiviteService;
import fr.inra.dsi.reporting.model.RapportParticipationAgentSurActivite;

/**
 * Service de CRUD sur les donnees du rapport
 * de participations des agents aux activites.
 * 
 * @author lepra
 */
@Service
public class RapportParticipationAgentSurActiviteServiceImpl extends 
        RapportServiceImpl<RapportParticipationAgentSurActivite> implements IRapportParticipationAgentSurActiviteService {

    /**
     * Constructeur vide.
     */
    public RapportParticipationAgentSurActiviteServiceImpl() {
        super();
    }
}
