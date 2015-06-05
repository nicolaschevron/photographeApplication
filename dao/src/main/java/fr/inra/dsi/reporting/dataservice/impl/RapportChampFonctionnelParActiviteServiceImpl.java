package fr.inra.dsi.reporting.dataservice.impl;

import org.springframework.stereotype.Service;

import fr.inra.dsi.reporting.dataservice.IRapportChampFonctionnelParActiviteService;
import fr.inra.dsi.reporting.model.RapportChampsFonctionnelParActivite;

/**
 * Implementation du service
 * de traitement des rapports
 * ChampThematique par activite. 
 * 
 * @author niche
 */
@Service
public class RapportChampFonctionnelParActiviteServiceImpl extends RapportServiceImpl<RapportChampsFonctionnelParActivite> implements IRapportChampFonctionnelParActiviteService {

    /**
     * Constructeur.
     */
    public RapportChampFonctionnelParActiviteServiceImpl() {
        super();
    }

}
