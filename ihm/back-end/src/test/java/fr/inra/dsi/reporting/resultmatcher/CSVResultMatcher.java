package fr.inra.dsi.reporting.resultmatcher;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.junit.Assert;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.util.StringUtils;

import fr.inra.dsi.reporting.bean.RapportToCsvMessageConverter;
import fr.inra.dsi.reporting.util.AssertUtil;

/**
 * ResultMatcher permettant de tester le résultat d'un appel CSV
 * 
 * @author niche
 *
 */
public class CSVResultMatcher implements ResultMatcher {

	 /**
     * Le contenu CSV attendu
     */
    private String expectedCsv;

    /**
     * Test strict (equals) ou non (contains)
     */
    private boolean strict;
    /**
     * Tester l'ordre (pour les tests non strict)
     */
    private boolean ordered;
    
    /**
     * Test si le résultat obtenu est égale à expectedCsv
     * @param expectedCsv
     * @return
     * @see AssertUtil#assertCsvEquals(String, String)
     */
    public static ResultMatcher equals(String expectedCsv){
        return new CSVResultMatcher(expectedCsv, true, false);
    }
    
    /**
     * Teste si le résultat obtenu contient expectedCsv
     * @param expectedCsv
     * @return
     * @see AssertUtil#assertCsvContains(String, String)
     */
    public static ResultMatcher contains(String expectedCsv){
        return new CSVResultMatcher(expectedCsv, false, false);
    }
    
    /**
     * Teste si le résultat obtenu contient expectedCsv et dans le bon ordre
     * @param expectedCsv
     * @return
     * @see AssertUtil#assertCsvOrderedContains(String, String)
     */
    public static ResultMatcher containsOrdered(String expectedCsv){
        return new CSVResultMatcher(expectedCsv, false, true);
    }
    
    /**
     * Teste si un CSV est trié par ordre ascendant.
     * Les cases vides sont ignorées du tri.
     * @param columnIndex l'indice de la colonne qui dois être triée
     * @return
     */
    public static ResultMatcher sortedAsc(final int columnIndex){
        return sorted(columnIndex, true);
    }
    
    /**
     * Teste si un CSV est trié par ordre descendant.
     * Les cases vides sont ignorées du tri.
     * @param columnIndex l'indice de la colonne qui dois être triée
     * @return
     */
    public static ResultMatcher sortedDesc(final int columnIndex){
        return sorted(columnIndex, false);
    }
    
    private static ResultMatcher sorted(final int columnIndex, final boolean asc){
        return new ResultMatcher() {
            @Override
            public void match(MvcResult mvcResult) throws Exception {
                CSVParser expected = CSVParser.parse(mvcResult.getResponse().getContentAsString(), RapportToCsvMessageConverter.CSV_FILE_FORMAT);
                
                boolean first = true;
                String previous = null;
                for(CSVRecord line : expected){
                    if(first){
                        first = false;
                    } else if(previous == null) {
                        previous = line.get(columnIndex);
                    } else {
                        String current = line.get(columnIndex);
                        if(!StringUtils.isEmpty(previous) && !StringUtils.isEmpty(current)){
                            if(asc){
                                Assert.assertTrue("'"+previous+"' avant '"+current+"'", (previous.compareTo(current) < 1));
                            } else {
                                Assert.assertTrue("'"+previous+"' après '"+current+"'", (previous.compareTo(current) > -1));
                            }
                        }
                        previous = current;
                    }
                }
            }
        };
    }
    
    /**
     * Constructeur.
     * @param expectedCsv
     * @param strict si true ce matcher fera un test "equals", si false un test "contains" (au niveau ligne)
     */
    private CSVResultMatcher(String expectedCsv, boolean strict, boolean ordered) {
        super();
        this.expectedCsv = expectedCsv;
        this.strict = strict;
        this.ordered = ordered;
    }
	
   /**
    * {@inheritDoc}
    */
	@Override
	public void match(MvcResult mvcResult) throws Exception {
		String actualCsv = mvcResult.getResponse().getContentAsString(); 
		if(strict){
		    AssertUtil.assertCsvEquals(expectedCsv, actualCsv);
		} else {
		    if(ordered){
                AssertUtil.assertCsvOrderedContains(expectedCsv, actualCsv);
		    } else {
                AssertUtil.assertCsvContains(expectedCsv, actualCsv);
		    }
		}
	}

}
