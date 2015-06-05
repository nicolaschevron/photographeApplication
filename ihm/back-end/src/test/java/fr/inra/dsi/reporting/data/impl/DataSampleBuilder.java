package fr.inra.dsi.reporting.data.impl;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.inra.dsi.reporting.data.IRapportBuilder;
import fr.inra.dsi.reporting.data.IRapportDesciptionBuilder;
import fr.inra.dsi.reporting.model.IRapport;

/**
 * Approche generique du design-pattern Builder pour
 * creer un jeu de donnees dans l'unite de persistence.
 * 
 * @author lepra
 */
@Component
public final class DataSampleBuilder {

    private static boolean databaseCreated = false;
    
    /**
     * Liste des createurs de donnees.
     * Auto-injectee.
     */
    @Autowired
    private transient List<IRapportBuilder<IRapport>> rapportCreators;
    
    @Autowired
    private transient List<IRapportDesciptionBuilder> rapportDescriptionsCreator;
    /**
     * Constructeur.
     */
    public DataSampleBuilder() {
        super();
    }
    
    /**
     * Appelle la methode de creation des donnees
     * pour chaque classe Builder. L'appel n'est
     * fait qu'une seule fois.
     * 
     * @throws ParseException
     */
    public void createDataset() throws ParseException {
        if (!isDatabaseCreated()) {
            for (IRapportBuilder<?> rapportCreator : rapportCreators) {
                rapportCreator.createRapports();
            }
            for (IRapportDesciptionBuilder rapportDescriptionCreator : rapportDescriptionsCreator ) {
            	rapportDescriptionCreator.createRapports();
            }
            synchronized(DataSampleBuilder.class) {
                databaseCreated = true;
            }
        }
    }
    
    /**
     * Permet de savoir si les donnees 
     * ont ete inserees ou pas.
     * 
     * @return boolean
     */
    public boolean isDatabaseCreated() {
        synchronized(DataSampleBuilder.class) {
            return DataSampleBuilder.databaseCreated;
        }
    }
    
}
