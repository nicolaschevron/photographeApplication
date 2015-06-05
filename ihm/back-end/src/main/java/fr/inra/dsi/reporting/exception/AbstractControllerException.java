package fr.inra.dsi.reporting.exception;

import org.springframework.http.HttpStatus;

import fr.inra.dsi.reporting.exception.json.BasicFeedback;

/**
 * Classe mère des exceptions des controlleurs
 * @author gugau
 *
 */
public abstract class AbstractControllerException extends Exception {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Feedback utilisateur
     */
    private final BasicFeedback feedback;
    
    /**
     * Status HTTP à renvoyer
     */
    private final HttpStatus status;
    
    /**
     * Constructeur.
     * @param excMsg
     * @param thr
     * @param feedback feedback pour l'utilisateur
     * @param status statut HTTP à renvoyer
     */
    public AbstractControllerException(String excMsg, Throwable thr, BasicFeedback feedback, HttpStatus status) {
        super(excMsg, thr);
        this.feedback = feedback;
        this.status = status;
    }
    
    /**
     * Constructeur.
     * @param excMsg
     * @param feedback feedback pour l'utilisateur
     * @param status statut HTTP à renvoyer
     */
    public AbstractControllerException(String excMsg, BasicFeedback feedback, HttpStatus status) {
        super(excMsg);
        this.feedback = feedback;
        this.status = status;
    }

    public BasicFeedback getFeedback() {
        return feedback;
    }

    public HttpStatus getStatus() {
        return status;
    }
    
}
