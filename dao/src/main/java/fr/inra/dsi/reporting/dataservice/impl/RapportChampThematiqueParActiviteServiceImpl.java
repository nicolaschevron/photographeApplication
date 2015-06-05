package fr.inra.dsi.reporting.dataservice.impl;

import org.springframework.stereotype.Service;

import fr.inra.dsi.reporting.dataservice.IRapportChampThematiqueParActiviteService;
import fr.inra.dsi.reporting.model.RapportChampThematiqueParActivite;

/**
 * Implementation du service
 * de traitement des rapports
 * ChampThematique par activite. 
 * 
 * @author niche
 */
@Service
public class RapportChampThematiqueParActiviteServiceImpl extends RapportServiceImpl<RapportChampThematiqueParActivite> implements IRapportChampThematiqueParActiviteService {

    /**
     * Constructeur.
     */
    public RapportChampThematiqueParActiviteServiceImpl() {
        super();
    }

}
