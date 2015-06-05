package fr.inra.dsi.reporting.web;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.inra.dsi.reporting.dto.ConfigurationDto;
import fr.inra.dsi.reporting.util.WebConstantUtil;

/**
 * Transmet des informations de configuration (adresse mail de 
 * feedback, etc.).
 * 
 * @author lepra
 */
@Controller 
public class ConfigurationController {

    @Value("${configuration.feedback.mail}")
    private transient String feedbackEmail; 
    
    @Value("${configuration.feedback.subject}")
    private transient String feedbackSubject;
    
    /**
     * Constructeur vide.
     */
    public ConfigurationController() {
        super();
    }
    
    /**
     * Retourne l'adresse e-mail de feedback.
     * 
     * @param response
     * @return String email.
     * @deprecated l'email est embarqué dans la conf renvoyée par {@link #getConf(HttpServletResponse)}
     */
    @Deprecated
    @RequestMapping(value = WebConstantUtil.CONF_FEEDBACK_EMAIL_PATH, method=RequestMethod.GET)
    public @ResponseBody String getFeedbackEmail(HttpServletResponse response) {
        return feedbackEmail;
    }
    
    /**
     * Renvoie la conf.
     * @param response
     * @return
     */
    @RequestMapping(value = WebConstantUtil.CONF_PATH, method=RequestMethod.GET, produces="application/json")
    @ResponseBody
    public ConfigurationDto getConf(HttpServletResponse response) {
        ConfigurationDto result = new ConfigurationDto();
        result.setFeedbackEmail(feedbackEmail);
        result.setFeedbackSubject(feedbackSubject);
        return result ;
    }
    
}
