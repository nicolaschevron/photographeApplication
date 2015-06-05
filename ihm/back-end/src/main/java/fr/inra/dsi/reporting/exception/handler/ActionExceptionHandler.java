package fr.inra.dsi.reporting.exception.handler;

import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import fr.inra.dsi.reporting.exception.AbstractControllerException;

/**
 * Intercepte et traite de maniere globale les exceptions propages par les actions.
 * 
 * @author lepra
 */
@ControllerAdvice
public class ActionExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = Logger.getLogger(ActionExceptionHandler.class);
    
    /**
     * Constructeur.
     */
    public ActionExceptionHandler() {
        super();
    }
    
    /**
     * Intercepte les exceptions de Validation et retourne une erreur JSON.
     * 
     * @param ex Exception propagee.
     * @param request Requete courante.
     * @return
     */
    @ExceptionHandler({ AbstractControllerException.class })
    protected ResponseEntity<Object> handleInvalidRequest(Exception exce, WebRequest request) {
        LOGGER.error(exce);
        
        AbstractControllerException controllerExc = (AbstractControllerException) exce;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        return handleExceptionInternal(exce, controllerExc.getFeedback(), headers, controllerExc.getStatus(), request);
    }
    
}
