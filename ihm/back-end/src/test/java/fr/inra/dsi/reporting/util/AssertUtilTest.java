package fr.inra.dsi.reporting.util;

import java.io.IOException;
import java.io.StringReader;

import junit.framework.Assert;
import junit.framework.AssertionFailedError;

import org.apache.commons.csv.CSVParser;
import org.junit.Test;

import fr.inra.dsi.reporting.bean.RapportToCsvMessageConverter;

/**
 * Test les méthodes utilitaire de AssertUtil.
 * @author gugau
 *
 */
public class AssertUtilTest {

    private static final String HEADERS = "Colonne 1,Colonne 2,Colonne 3\n";
    private static final String LINE0 = "val01,val02,val03\n";
    private static final String LINE1 = "val11,val12,val13\n";
    private static final String LINE2 = "val21,val22,val23\n";
    private static final String LINE_MISSING = "valX1,valX2,valX3\n";
    
    @Test
    public void testAssertCsvContains() throws IOException{
        boolean testOk = false;
        String actual = HEADERS
                + LINE0
                + LINE1
                + LINE2;

        AssertUtil.assertCsvContains((HEADERS+LINE0), actual);
        AssertUtil.assertCsvContains((HEADERS+LINE1), actual);
        AssertUtil.assertCsvContains((HEADERS+LINE2), actual);
        AssertUtil.assertCsvContains((HEADERS+LINE0+LINE2), actual);
        try {
            testOk = false;
            AssertUtil.assertCsvContains((HEADERS+LINE0+LINE_MISSING+LINE2), actual);
        } catch(AssertionError e) {
            testOk = true;
        }
        Assert.assertTrue(testOk);
    }

    @Test
    public void testAssertCsvOrderedContains() throws IOException{
        boolean testOk = false;
        String actual = HEADERS
                + LINE0
                + LINE1
                + LINE2;

        AssertUtil.assertCsvOrderedContains((HEADERS+LINE0), actual);
        AssertUtil.assertCsvOrderedContains((HEADERS+LINE1), actual);
        AssertUtil.assertCsvOrderedContains((HEADERS+LINE2), actual);
        AssertUtil.assertCsvOrderedContains((HEADERS+LINE0+LINE1), actual);
        AssertUtil.assertCsvOrderedContains((HEADERS+LINE1+LINE2), actual);
        AssertUtil.assertCsvOrderedContains((HEADERS+LINE0+LINE2), actual);
        try {
            testOk = false;
            AssertUtil.assertCsvOrderedContains((HEADERS+LINE0+LINE_MISSING+LINE2), actual);
        } catch(AssertionError e) {
            testOk = true;
        }
        Assert.assertTrue(testOk);

        try {
            testOk = false;
            System.out.println(">0");
            AssertUtil.assertCsvOrderedContains((HEADERS+LINE1+LINE2+LINE0), actual);
        } catch(AssertionFailedError e) {
            testOk = true;
        }
        Assert.assertTrue(testOk);

        try {
            testOk = false;
            AssertUtil.assertCsvOrderedContains((HEADERS+LINE0+LINE2+LINE1), actual);
        } catch(AssertionError e) {
            testOk = true;
        }
        Assert.assertTrue(testOk);
    }
    
    @Test
    public void testAssertCsvEquals() throws IOException{
        boolean testOk = false;
        String actual = HEADERS
                + LINE0
                + LINE1
                + LINE2;
        
        AssertUtil.assertCsvEquals(actual, actual);
        try {
            testOk = false;
            AssertUtil.assertCsvEquals((HEADERS+LINE0+LINE2), actual);
        } catch(AssertionError e) {
            testOk = true;
        }
        Assert.assertTrue(testOk);
        try {
            testOk = false;
            AssertUtil.assertCsvEquals((HEADERS+LINE0+LINE_MISSING+LINE2), actual);
        } catch(AssertionError e) {
            testOk = true;
        }
        Assert.assertTrue(testOk);
        try {
            testOk = false;
            AssertUtil.assertCsvEquals((HEADERS+LINE0+LINE1+LINE2+LINE_MISSING), actual);
        } catch(AssertionError e) {
            testOk = true;
        }
        Assert.assertTrue(testOk);
    }
    
}
