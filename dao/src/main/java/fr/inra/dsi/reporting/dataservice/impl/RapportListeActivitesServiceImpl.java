package fr.inra.dsi.reporting.dataservice.impl;

import org.springframework.stereotype.Service;

import fr.inra.dsi.reporting.dataservice.IRapportListeActivitesService;
import fr.inra.dsi.reporting.model.RapportListeActivites;

/**
 * Implementation du service
 * de traitement des rapports
 * ListeActivites. 
 * 
 * @author cebri
 */
@Service
public class RapportListeActivitesServiceImpl extends RapportServiceImpl<RapportListeActivites> implements IRapportListeActivitesService {

    /**
     * Constructeur.
     */
    public RapportListeActivitesServiceImpl() {
        super();
    }

}
