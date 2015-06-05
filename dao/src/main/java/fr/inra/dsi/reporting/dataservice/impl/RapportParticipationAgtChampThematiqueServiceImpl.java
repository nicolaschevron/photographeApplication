package fr.inra.dsi.reporting.dataservice.impl;

import org.springframework.stereotype.Service;

import fr.inra.dsi.reporting.dataservice.IRapportParticipationAgtChampThematiqueService;
import fr.inra.dsi.reporting.model.RapportParticipationAgtChampThematique;

/**
 * Service de CRUD sur les donnees du rapport
 * de participations des agents aux activites.
 * 
 * @author lepra
 */
@Service
public class RapportParticipationAgtChampThematiqueServiceImpl extends 
        RapportServiceImpl<RapportParticipationAgtChampThematique> implements IRapportParticipationAgtChampThematiqueService {

    /**
     * Constructeur vide.
     */
    public RapportParticipationAgtChampThematiqueServiceImpl() {
        super();
    }
}
