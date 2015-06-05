package fr.inra.dsi.reporting.dto;

import java.util.HashMap;
import java.util.Map;

/**
 * Représente la configuration du back-end (paramètres de conf pour le fonctionnel)
 * @author gugau
 *
 */
public class ConfigurationDto {

    private static final String FEEDBACK_EMAIL_KEY = "email";
    private static final String FEEDBACK_SUBJECT_KEY = "subject";
    
    private Map<String, Object> feedback = new HashMap<String, Object>();
    
    /**
     * Constructeur.
     */
    public ConfigurationDto() {
        super();
    }

    /**
     * Getter
     * @return
     */
    public Map<String, Object> getFeedback() {
        return feedback;
    }

    /**
     * Setter
     * @param feedback
     */
    public void setFeedback(Map<String, Object> feedback) {
        this.feedback = feedback;
    }

    /**
     * Affecte l'adresse email de feeback utilisateur
     * @param feedbackEmail
     */
    public void setFeedbackEmail(String feedbackEmail) {
        feedback.put(FEEDBACK_EMAIL_KEY, feedbackEmail);
    }
    
    /**
     * Affecte le sujet pour l'email de feedback
     * @param feedbackSubject
     */
    public void setFeedbackSubject(String feedbackSubject) {
        feedback.put(FEEDBACK_SUBJECT_KEY, feedbackSubject.replace("&", "%26"));
    }
    
}
