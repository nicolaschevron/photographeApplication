package fr.inra.dsi.reporting.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.json.JsonValue.ValueType;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.util.StringUtils;

import fr.inra.dsi.reporting.bean.RapportToCsvMessageConverter;
import junit.framework.Assert;
import junit.framework.AssertionFailedError;

/**
 * Classe utilitaire pour les tests.
 * @author gugau
 *
 */
public class AssertUtil {

    /**
     * Constructeur.
     */
    private AssertUtil(){
        super();
    }
    
    /**
     * Teste si le contenu du expected est contenu dans actual.
     * @param expected
     * @param actual
     */
    public static void assertJsonContains(JsonObject expected, JsonObject actual){
        AssertUtil.internalAssertJsonContains(expected, actual, "");
    }
    private static void internalAssertJsonContains(JsonObject expected, JsonObject actual, String path){
        for(Entry<String, JsonValue> field : expected.entrySet()){
            String subPath;
            if(StringUtils.isEmpty(path)){
                subPath = field.getKey();
            } else {
                subPath = path+"."+field.getKey();
            }
            if(actual.containsKey(field.getKey())){
                AssertUtil.internalAssertJsonValueContains(expected.get(field.getKey()), actual.get(field.getKey()), subPath);
            } else {
                Assert.fail(subPath+": valeur manquante dans le JSON");
            }
        }
    }
    
    /**
     * Teste si le contenu de expected est contenu dans actual.
     * 
     * Dans le cas d'un tableau, actual doit commencer par expected pour que l'assertion soit vraie.
     * @param expected
     * @param actual
     */
    public static void assertJsonValueContains(JsonValue expected, JsonValue actual){
        internalAssertJsonValueContains(expected, actual, "");
    }
    private static void internalAssertJsonValueContains(JsonValue expected, JsonValue actual, String path){
        if(expected.getValueType().equals(ValueType.OBJECT)){
            AssertUtil.internalAssertJsonContains((JsonObject)expected, (JsonObject)actual, path);
        } else if(expected.getValueType().equals(ValueType.ARRAY)){
            JsonArray expectedArray = (JsonArray)expected;
            JsonArray actualArray = (JsonArray)actual;
            Assert.assertTrue(path + ": tableau ["+actualArray.size()+"] obtenu, tableau ["+expectedArray.size()+"] minimum attendu dans le JSON",
                (expectedArray.size() <= actualArray.size()));
            for(int index=0;index<expectedArray.size(); index++){
                AssertUtil.internalAssertJsonValueContains(expectedArray.get(index), actualArray.get(index), path+"["+index+"]");
            }
        } else if(expected.getValueType() != ValueType.NULL) {
            try {
                Assert.assertEquals(expected, actual);
            } catch(AssertionFailedError e) {
                throw new AssertionFailedError(path+": "+e.getMessage());
            }
        }
    }
    
    /**
     * Teste si le contenu de expected est égale à actual.
     * @param expected
     * @param actual
     */
    public static void assertJsonEquals(JsonObject expected, JsonObject actual){
        AssertUtil.assertJsonContains(expected, actual);
        AssertUtil.assertJsonContains(actual, expected);//TODO améliorer car ici les messages des exceptions prêteront à confusion
    }
    
    /**
     * Test si le contenu de expectedCsv est égal à actualCsv.
     * @param expectedCsv
     * @param actualCsv
     * @throws IOException 
     */
    public static void assertCsvEquals(String expectedCsv, String actualCsv) throws IOException{
        CSVParser expected = CSVParser.parse(expectedCsv, RapportToCsvMessageConverter.CSV_FILE_FORMAT);
        CSVParser actual = CSVParser.parse(actualCsv, RapportToCsvMessageConverter.CSV_FILE_FORMAT);
        
        Iterator<CSVRecord> expectedIterator = expected.iterator();
        Iterator<CSVRecord> actualIterator = actual.iterator();
        
        while(expectedIterator.hasNext()){
            Assert.assertTrue("Le résultat obtenu contient moins d'élément que le résultat attendu", actualIterator.hasNext());
            CSVRecord expectedRecord = expectedIterator.next();
            CSVRecord actualRecord = actualIterator.next();
            Assert.assertEquals("Le nombre de colonne de l'entréé "+actualRecord.size()+" ne correspond pas au résultat attendu", expectedRecord.size(), actualRecord.size());
            Assert.assertTrue("La ligne "+expectedRecord.getRecordNumber()+" du CSV ne correspond pas au résultat attendu",
                    testCSVRecordEquals(expectedRecord, actualRecord));
        }
        Assert.assertFalse("Le résultat obtenu contient plus d'élément que le résultat attendu", actualIterator.hasNext());
    }
    
    /**
     * Teste si toutes les lignes de expectedCsv sont dans actualCsv, quelque soit l'ordre
     *
     * @param expectedCsv
     * @param actualCsv
     * @throws IOException
     */
    public static void assertCsvContains(String expectedCsv, String actualCsv) throws IOException{
        CSVParser expected = CSVParser.parse(expectedCsv, RapportToCsvMessageConverter.CSV_FILE_FORMAT);
        
        List<Boolean> matches = new ArrayList<Boolean>();
        for(CSVRecord expectedRecord : expected){
            boolean foundMatchLine = false;
            CSVParser actual = CSVParser.parse(actualCsv, RapportToCsvMessageConverter.CSV_FILE_FORMAT);
            for(CSVRecord actualRecord : actual){
                if(testCSVRecordEquals(expectedRecord, actualRecord)){
                    foundMatchLine = true;
                    break;
                }
            }
            matches.add(Boolean.valueOf(foundMatchLine));
            if(!foundMatchLine){
                Assert.fail("La ligne "+expectedRecord.getRecordNumber()+" est absente du CSV obtenu");
            }
        }
    }
    
    /**
     * Teste si toutes les lignes de expectedCsv sont dans actualCsv et apparaissent dans le même ordre
     *
     * @param expectedCsv
     * @param actualCsv
     * @throws IOException
     */
    public static void assertCsvOrderedContains(String expectedCsv, String actualCsv) throws IOException{
        CSVParser expected = CSVParser.parse(expectedCsv, RapportToCsvMessageConverter.CSV_FILE_FORMAT);
        
        CSVParser actual = CSVParser.parse(actualCsv, RapportToCsvMessageConverter.CSV_FILE_FORMAT);
        List<CSVRecord> actualRecords = actual.getRecords();
        int index = 0;
        for(CSVRecord expectedRecord : expected){
            boolean foundMatchLine = false;
            while(index < actualRecords.size()){
                CSVRecord actualRecord = actualRecords.get(index);
                index++;
                if(testCSVRecordEquals(expectedRecord, actualRecord)){
                    foundMatchLine = true;
                    break;
                }
            }
            if(!foundMatchLine){
                Assert.fail("La ligne "+expectedRecord.getRecordNumber()+" est absente du CSV obtenu ou dans le mauvais ordre");
            }
        }
    }
    
    private static boolean testCSVRecordEquals(CSVRecord expectedRecord, CSVRecord actualRecord) {
        if(expectedRecord.size() == actualRecord.size()){
            Iterator<String> expectedRecordIterator = expectedRecord.iterator();
            Iterator<String> actualRecordIterator = actualRecord.iterator();
            
            boolean foundNotMatchField = false;
            while(expectedRecordIterator.hasNext() && !foundNotMatchField){
                String expectedValue = expectedRecordIterator.next();
                String actualValue = actualRecordIterator.next();
                if(StringUtils.isEmpty(expectedValue)){
                    if(!StringUtils.isEmpty(actualValue)){
                        foundNotMatchField = true;
                    }
                } else if(!expectedValue.equals(actualValue)) {
                    foundNotMatchField = true;
                }
            }
            return !foundNotMatchField;
        } else {
            return false;
        }
    }
}
