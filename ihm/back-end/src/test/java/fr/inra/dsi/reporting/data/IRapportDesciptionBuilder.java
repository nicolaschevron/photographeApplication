package fr.inra.dsi.reporting.data;

import java.text.ParseException;

public interface IRapportDesciptionBuilder {
    /**
     * Methode de creation des descriptions de rapport.
     * 
     * @throws ParseException
     */
    void createRapports() throws ParseException;
}
