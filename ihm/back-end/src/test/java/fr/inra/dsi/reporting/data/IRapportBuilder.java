package fr.inra.dsi.reporting.data;

import java.text.ParseException;

import fr.inra.dsi.reporting.model.IRapport;

/** 
 * Interface pour les classes Builder.
 * 
 * @author lepra
 *
 * @param <T>
 */
public interface IRapportBuilder<T extends IRapport> {

    /**
     * Methode de creation des rapports.
     * 
     * @throws ParseException
     */
    void createRapports() throws ParseException;
    
}
