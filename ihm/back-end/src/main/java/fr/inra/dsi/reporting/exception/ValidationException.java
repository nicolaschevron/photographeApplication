package fr.inra.dsi.reporting.exception;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

import fr.inra.dsi.reporting.exception.json.ValidationFeedback;

/**
 * Classe d'exception lors des validations de formulaire.
 * 
 * @author lepra
 */
public class ValidationException extends AbstractControllerException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructeur.
     * @param excMsg
     * @param thr
     * @param message
     * @param errors
     * @param fieldIds
     */
    public ValidationException(String excMsg, Throwable thr, String message, List<String> errors, List<String> fieldIds) {
        super(excMsg, thr, new ValidationFeedback(message, errors, fieldIds), HttpStatus.BAD_REQUEST);
    }
    
    /**
     * Constructeur.
     * @param excMsg
     * @param thr
     * @param message
     * @param errors
     * @param fieldIds
     */
    public ValidationException(String excMsg, Throwable thr, String message, String[] errors, String... fieldIds) {
        super(excMsg, thr, new ValidationFeedback(message, Arrays.asList(errors), Arrays.asList(fieldIds)), HttpStatus.BAD_REQUEST);
    }

    /**
     * Constructeur.
     * @param excMsg
     * @param feedback
     */
    public ValidationException(String excMsg, ValidationFeedback feedback) {
        super(excMsg, feedback, HttpStatus.BAD_REQUEST);
    }
}
