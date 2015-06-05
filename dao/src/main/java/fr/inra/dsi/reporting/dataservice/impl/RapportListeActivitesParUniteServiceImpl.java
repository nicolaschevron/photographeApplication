package fr.inra.dsi.reporting.dataservice.impl;

import org.springframework.stereotype.Service;

import fr.inra.dsi.reporting.dataservice.IRapportListeActivitesParUniteService;
import fr.inra.dsi.reporting.model.RapportListeActivitesParUnite;

/**
 * Implémentation du service RapportListeActivitesParUnite.
 * 
 * @author gugau
 *
 */
@Service
public class RapportListeActivitesParUniteServiceImpl extends
        RapportServiceImpl<RapportListeActivitesParUnite> implements IRapportListeActivitesParUniteService {

    /**
     * Constructeur
     */
    public RapportListeActivitesParUniteServiceImpl(){
        super();
    }
    
}
