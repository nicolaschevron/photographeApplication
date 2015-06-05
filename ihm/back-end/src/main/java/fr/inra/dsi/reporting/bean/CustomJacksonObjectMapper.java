package fr.inra.dsi.reporting.bean;

import java.text.SimpleDateFormat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * ObjectMapper personnalisé.
 * 
 * Utilisé par Spring pour la sérialisation JSON.
 * @author gugau
 *
 */
public class CustomJacksonObjectMapper extends ObjectMapper {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructeur.
     */
    public CustomJacksonObjectMapper() {
        super();
        configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);            
        setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'"));
    }
    
}
