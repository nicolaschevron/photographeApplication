package fr.inra.dsi.reporting.resultmatcher;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.core.JsonProcessingException;

import fr.inra.dsi.reporting.bean.CustomJacksonObjectMapper;
import fr.inra.dsi.reporting.util.AssertUtil;

/**
 * ResultMatcher permettant de tester le résultat d'un appel JSON
 * 
 * Ce Matcher teste que le JSON renvoyé contient au minimum les données
 * souhaités ("contains" et non "equals").
 * 
 * @author gugau
 *
 */
public class JSONResultMatcher implements ResultMatcher {

    /**
     * Le contenu JSON attendu
     */
    private String expectedJson;
    
    /**
     * Test si les données du JSON expectedJson sont bien dans le résultat obtenu 
     * @param expectedJson
     * @return
     */
    public static ResultMatcher contains(String expectedJson){
        return new JSONResultMatcher(expectedJson);
    }
    
    /**
     * Test si les données de l'object expected sont bien dans le résultat obtenu
     * @param expected
     * @return
     */
    public static ResultMatcher contains(Object expected) throws JsonProcessingException {
        return new JSONResultMatcher(expected);
    }
    
    /**
     * Constructeur.
     * @param expectedJson
     */
    public JSONResultMatcher(String expectedJson) {
        super();
        this.expectedJson = expectedJson;
    }
    
    /**
     * Constructeur.
     * 
     * Compare le résultat obtenu à l'objet expected sérialisé
     * @param expected le résultat attendu.
     * @throws JsonProcessingException 
     */
    public JSONResultMatcher(Object expected) throws JsonProcessingException {
        super();
        CustomJacksonObjectMapper serializer = new CustomJacksonObjectMapper();
        this.expectedJson = serializer.writeValueAsString(expected);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void match(MvcResult mvcResult) throws Exception {
        String jsonStr = mvcResult.getResponse().getContentAsString();
        JsonReader jsonReader = Json.createReader(new StringReader(jsonStr));
        JsonObject jsonObj = jsonReader.readObject();
        jsonReader.close();
        
        jsonReader = Json.createReader(new StringReader(expectedJson));
        JsonObject jsonObjExpected = jsonReader.readObject();
        jsonReader.close();
        
        AssertUtil.assertJsonContains(jsonObjExpected, jsonObj);
    }

    public String getExpectedJson() {
        return expectedJson;
    }

    public void setExpectedJson(String expectedJson) {
        this.expectedJson = expectedJson;
    }

}
