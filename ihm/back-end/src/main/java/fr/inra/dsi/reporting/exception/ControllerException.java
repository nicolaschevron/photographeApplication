package fr.inra.dsi.reporting.exception;

import org.springframework.http.HttpStatus;

import fr.inra.dsi.reporting.exception.json.BasicFeedback;

/**
 * Implémentation basique des excpetions des controlleurs.
 * @author gugau
 *
 */
public class ControllerException extends AbstractControllerException {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructeur.
     * @param feedback
     * @param status
     * @see AbstractControllerException
     */
    public ControllerException(BasicFeedback feedback, HttpStatus status) {
        super(feedback.getMessage(), feedback, status);
    }

    /**
     * Constructeur.
     * @param feedbackMessage
     * @param status
     * @see AbstractControllerException
     */
    public ControllerException(String feedbackMessage, HttpStatus status) {
        super(feedbackMessage, new BasicFeedback(feedbackMessage), status);
    }

    /**
     * Constructeur.
     * @param excMsg
     * @param feedback
     * @param status
     * @see AbstractControllerException
     */
    public ControllerException(String excMsg, BasicFeedback feedback, HttpStatus status) {
        super(excMsg, feedback, status);
    }

    /**
     * Constructeur.
     * @param excMsg
     * @param thr
     * @param feedback
     * @param status
     * @see AbstractControllerException
     */
    public ControllerException(String excMsg, Throwable thr,
            BasicFeedback feedback, HttpStatus status) {
        super(excMsg, thr, feedback, status);
    }

}
