package fr.inra.dsi.reporting.exception.json;

import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe permet de parametre des messages d'erreurs
 * pour des champs de formulaire precis
 * suite a une erreur de validation.
 * 
 * @author lepra
 */
public class ValidationFeedback extends BasicFeedback {

    private transient List<String> errors = new ArrayList<>();
    private transient List<String> fieldIds = new ArrayList<>();
    
    /**
     * Constructeur vide.
     */
    public ValidationFeedback(String message) {
        super(message);
    }
    
    /**
     * Constructeur. 
     * 
     * @param errors message d'erreurs
     * @param fieldIds identifiant des champs
     */
    public ValidationFeedback(String message, List<String> errors, List<String> fieldIds) {
        super(message);
        this.errors = errors;
        this.fieldIds = fieldIds;
    } 
   
    /**
     * Indique si la validation est ok ou pas.
     * 
     * @return boolean
     */
    public boolean isValid() {
        return errors.isEmpty() && fieldIds.isEmpty();
    }
    
    /**
     * Recuperation de la liste des champs de formulaire.
     * 
     * @return {@link List}
     */
    public List<String> getFieldIds() {
        return fieldIds;
    }

    /**
     * Recuperation de la liste des messages.
     * 
     * @return {@link List}
     */
    public List<String> getErrors() {
        return errors;
    }
    
}
