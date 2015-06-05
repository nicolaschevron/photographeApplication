package fr.inra.dsi.reporting.exception.json;

/**
 * Classe mère des objets "feedback".
 * 
 * Feedback avec un simple message texte.
 * @author gugau
 *
 */
public class BasicFeedback {

    private String message;

    /**
     * Constructeur
     * @param message le message d'information pour l'utilisateur
     */
    public BasicFeedback(String message) {
        super();
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
