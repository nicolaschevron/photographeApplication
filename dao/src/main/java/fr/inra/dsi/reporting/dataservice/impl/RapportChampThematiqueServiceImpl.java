package fr.inra.dsi.reporting.dataservice.impl;

import org.springframework.stereotype.Service;

import fr.inra.dsi.reporting.dataservice.IRapportChampThematiqueService;
import fr.inra.dsi.reporting.model.RapportChampThematique;

/**
 * Implementation du service
 * de traitement des rapports
 * ChampThematique. 
 * 
 * @author lepra
 */
@Service
public class RapportChampThematiqueServiceImpl extends RapportServiceImpl<RapportChampThematique> implements IRapportChampThematiqueService {

    /**
     * Constructeur.
     */
    public RapportChampThematiqueServiceImpl() {
        super();
    }

}
