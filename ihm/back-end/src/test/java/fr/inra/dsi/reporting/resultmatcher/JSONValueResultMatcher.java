package fr.inra.dsi.reporting.resultmatcher;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.json.JsonValue.ValueType;

import junit.framework.Assert;
import junit.framework.AssertionFailedError;

import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.util.StringUtils;

/**
 * ResultMatcher abstrait permettant d'effectuer un test sur une valeur présente dans un JSON.
 * 
 * Cette classe utilise un chemin ({@link #path}) pour atteindre la valeur devant être testée. Par exemple:
 * - monChamp
 * - monChamp.monSousChamp
 * - monTableauDObjet[1]
 * - monTableauDObjet[0].monChamp
 * Avec la structure JSON:
 * {
 *     monChamp: {
 *             monSousChamp: "toto"
 *         },
 *     monTableauDObjet: [
 *         {
 *             monChamp: "a"
 *         },
 *         {
 *             monChamp: "b"
 *         }
 *     ]
 * }
 * 
 * Pour les indices des tableaux, le wildcard (*) est supporté, exemple:
 * - monTableauDObjet[*].monChamp
 * L'assertion est alors vérifiée pour tout les éléments du tableau.
 * 
 * Il est également possible de rechercher une valeur qui correspond avec "?", exemple:
 * - monTableauDObjet[?].monChamp
 * L'assertion est alors vérifiée pour tout les éléments du tableau jusqu'à ce
 * qu'elle soit vraie. Si aucun élément du tableau ne valide l'assertion alors le
 * test échoue.
 * 
 * @author gugau
 *
 */
public abstract class JSONValueResultMatcher implements ResultMatcher {

    private String path;
    
    /**
     * Constructeur.
     * @param path le chemin vers la valeur
     */
    public JSONValueResultMatcher(String path) {
        super();
        this.path = path;
    }

    @Override
    public void match(MvcResult mvcResult) throws Exception {
        String jsonStr = mvcResult.getResponse().getContentAsString();
        JsonReader jsonReader = Json.createReader(new StringReader(jsonStr));
        JsonObject jsonObj = jsonReader.readObject();
        jsonReader.close();
        
        assertValueInJsonEquals("", this.path, jsonObj);
    }
    
    /**
     * Méthode où doit être testée la valeur.
     * 
     * Le paramètre actualJsonValue peut être de type JsonObject, JsonArray, ...
     * En fonction de ce qui se trouve au {@link #path} demandé.
     * 
     * @param actualJsonValue la valeur au chemin {@link #path}
     */
    public abstract void assertValue(JsonValue actualJsonValue);
    
    /*
     * Recherche récurcive
     */
    private void assertValueInJsonEquals(String path, String expectedValuePath, JsonValue actualValue){
        if(StringUtils.isEmpty(expectedValuePath)){
            assertValue( actualValue);
        } else if(expectedValuePath.indexOf('.') == 0) {
            assertValueInJsonEquals(path, expectedValuePath.substring(1), actualValue);
        } else {
            int dotIndex = expectedValuePath.indexOf('.');
            int bracketIndex = expectedValuePath.indexOf('[');
            
            boolean fieldRec = (dotIndex != -1)
                    && ((dotIndex < bracketIndex) || (bracketIndex == -1));
            boolean arrayRec = (bracketIndex != -1)
                    && ((bracketIndex < dotIndex) || (dotIndex == -1));
            
            if(fieldRec){
                //Récursion : monObject.monChamp => monChamp
                String pathField = expectedValuePath.substring(0, dotIndex);
                String recPath = path+"."+pathField;
                String recValuePath = expectedValuePath.substring(dotIndex+1);
                if (!actualValue.getValueType().equals(ValueType.OBJECT)) {
                    Assert.fail(path+": ce champ n'est pas un object, pas de champ '"+pathField+"'");
                } else {
                    JsonObject jsonObject = (JsonObject) actualValue;
                    if (jsonObject.containsKey(pathField)) {
                        assertValueInJsonEquals(recPath, recValuePath, jsonObject.get(pathField));
                    } else {
                        Assert.fail(path+": pas de champ '"+pathField+"' sur l'objet");
                    }
                }
            } else if(arrayRec){
                int secondBracketIndex = expectedValuePath.indexOf(']');
                if(secondBracketIndex == -1 || bracketIndex > secondBracketIndex) {
                    Assert.fail(path+": "+expectedValuePath+": indice incorrecte ");
                }
                if(bracketIndex == 0){
                    //Récursion multiple : [i] => [1], [2], [3], ...
                    if(actualValue.getValueType().equals(ValueType.ARRAY)){
                        String brackets = expectedValuePath.substring(bracketIndex, secondBracketIndex+1);
                        String recPath = path+brackets;
                        String recValuePath = expectedValuePath.substring(secondBracketIndex+1);
                        JsonArray jsonArray = (JsonArray) actualValue;
                        int jsonArrayIndex = -1;
                        String jsonArrayIndexStr = brackets.replace("[", "").replace("]", "");
                        if("*".equals(jsonArrayIndexStr)){
                            for(JsonValue item : jsonArray){
                                assertValueInJsonEquals(recPath, recValuePath, item);
                            }
                        } else if("?".equals(jsonArrayIndexStr)){
                            boolean success = false;
                            String failMessages = "";
                            for(JsonValue item : jsonArray){
                                try {
                                    assertValueInJsonEquals(recPath, recValuePath, item);
                                    success = true;
                                } catch(AssertionError e) {
                                    if(failMessages.length() < 300){
                                        failMessages = failMessages+e.getMessage()+", ";
                                    }
                                }
                                if(success){
                                    break;
                                }
                            }
                            if(!success){
                                throw new AssertionFailedError("Aucun élément du tableau ne valide l'assertion : "+failMessages+"...");
                            }
                        } else {
                            try {
                                jsonArrayIndex = Integer.valueOf(jsonArrayIndexStr);
                            } catch(NumberFormatException e){
                                Assert.fail(recPath+": '"+jsonArrayIndexStr+"' n'est pas un indice de tableau");
                            }
                            Assert.assertTrue(recPath+": l'indice "+jsonArrayIndexStr+" dépasse la taille du tableau", (jsonArrayIndex < jsonArray.size()));
                            assertValueInJsonEquals(recPath, recValuePath, jsonArray.get(jsonArrayIndex));
                        }
                    } else {
                        Assert.fail(path+": ce champ n'est pas un tableau");
                    }
                } else {
                    //Récursion : monTableau[i] => [i]
                    String pathField = expectedValuePath.substring(0, bracketIndex);
                    String recPath = path+"."+expectedValuePath.substring(0, bracketIndex);
                    String recValuePath = expectedValuePath.substring(bracketIndex);
                    if (!actualValue.getValueType().equals(ValueType.OBJECT)) {
                        Assert.fail(path+": ce champ n'est pas un object, pas de champ '"+expectedValuePath+"'");
                    } else {
                        JsonObject jsonObject = (JsonObject) actualValue;
                        if (jsonObject.containsKey(pathField)) {
                            assertValueInJsonEquals(recPath, recValuePath, jsonObject.get(pathField));
                        } else {
                            Assert.fail(path+": pas de champ '"+pathField+"' sur l'objet");
                        }
                    }
                }
            } else {
                //Récursion : monChamp => ''
                if(actualValue.getValueType().equals(ValueType.OBJECT)) {
                    String recPath = path+"."+expectedValuePath;
                    JsonObject jsonObject = (JsonObject) actualValue;
                    Assert.assertTrue(recPath+": la valeur est manquante dans le JSON", jsonObject.containsKey(expectedValuePath));
                    assertValueInJsonEquals(recPath, "", jsonObject.get(expectedValuePath));
                } else {
                    Assert.fail(path+": ce champ n'est pas un object, pas de champ '"+expectedValuePath+"'");
                }
            }
        }
    }
    
}
