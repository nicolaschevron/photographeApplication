package fr.inra.dsi.reporting.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import fr.inra.dsi.reporting.bean.RapportToCsvMessageConverter;
import fr.inra.dsi.reporting.data.impl.RapportListeActivitesBuilderImpl;
import fr.inra.dsi.reporting.dataservice.IRapportListeActivitesService;
import fr.inra.dsi.reporting.dto.SearchOrder;
import fr.inra.dsi.reporting.resultmatcher.CSVResultMatcher;
import fr.inra.dsi.reporting.resultmatcher.JSONResultMatcher;
import fr.inra.dsi.reporting.util.WebConstantUtil;

public class ListeActivitesWebControllerTest extends AbstractControllerTest{
	
	/**
	 * Format de date du json
	 */
	private static final String FORMAT_DATE_JSON = "yyyy-MM-dd'T'HH:mm:ss'Z'";

	/**
	 * Format de date de l'ihm
	 */
	private static final String FORMAT_DATE_IHM = "dd/MM/yyyy";

	/**
	 * Parametre envoye au back-end pour l'export du rapportListeActivite
	 */
	private static final String PARAMETER_ORDER = "order";

	/**
	 * Parametre envoye au back-end pour l'export du rapportListeActivite
	 */
	private static final String PARAMETER_ORDER_FIELD = "orderField";

	/**
	 * Valeur du parametre envoye au back-end pour l'export du rapportListeActivite
	 */
	private static final String PARAMETER_ORDER_FIELD_VALUE = "libelleTypeAc";

	/**
     * Service spring security UserDetailsService
     */
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
    private IRapportListeActivitesService rapportListeActiviteService;
    
    @Autowired
	private transient MessageSource messageSource;

    /**
	 * Constructeur vide.
	 */
    public ListeActivitesWebControllerTest() {
    	super();
    }
    
    /**
     * Test de la methode find rapportsListeactivites
     * @throws Exception
     */
    @Test
    public void testFindRapportsListeActivites() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders.get(WebConstantUtil.RAPPORT_LISTE_ACT_FIND_PATH))
            .andExpect(MockMvcResultMatchers.status().isUnauthorized());
  		
        StringBuilder expectedJson = new StringBuilder();
      expectedJson.append("\"}],\"colonnesRapport\":[{")
      .append("\"libelle\":\"").append(messageSource.getMessage("rapportlisteactivites.label.colonne.libelleTypeAc", null , null)).append("\",\"cle\":\"libelleTypeAc")
      .append("\"},{\"libelle\":\"").append(messageSource.getMessage("rapportlisteactivites.label.colonne.abreviationTypeAc", null , null)).append("\",\"cle\":\"abreviationTypeAc")
      .append("\"},{\"libelle\":\"").append(messageSource.getMessage("rapportlisteactivites.label.colonne.codeActiviteCollective", null , null)).append("\",\"cle\":\"codeActiviteCollective")
      .append("\"},{\"libelle\":\"").append(messageSource.getMessage("rapportlisteactivites.label.colonne.libelleAc", null , null)).append("\",\"cle\":\"libelleAc")
      .append("\"},{\"libelle\":\"").append(messageSource.getMessage("rapportlisteactivites.label.colonne.date", null , null)).append("\",\"cle\":\"date")
      .append("\"},{\"libelle\":\"").append(messageSource.getMessage("rapportlisteactivites.label.colonne.dateFinPrevue", null , null)).append("\",\"cle\":\"dateFinPrevue")
      .append("\"},{\"libelle\":\"").append(messageSource.getMessage("rapportlisteactivites.label.colonne.dateFinEffective", null , null)).append("\",\"cle\":\"dateFinEffective")
      .append("\"},{\"libelle\":\"").append(messageSource.getMessage("rapportlisteactivites.label.colonne.etatAc", null , null)).append("\",\"cle\":\"etatAc")
      .append("\"},{\"libelle\":\"").append(messageSource.getMessage("rapportlisteactivites.label.colonne.codeUnite", null , null)).append("\",\"cle\":\"codeUnite")
      .append("\"}]}"); 
        
  		this.mockMvc.perform(MockMvcRequestBuilders.get(WebConstantUtil.RAPPORT_LISTE_ACT_FIND_PATH)
                .with(SecurityMockMvcRequestPostProcessors.user(userDetailsService.loadUserByUsername("test"))))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(new JSONResultMatcher("{\"lignesRapport\":[{\"identifiant\":"
                    + RapportListeActivitesBuilderImpl.ACC_IDENTIFIANT + ",\"libelleTypeAc\":\""
            		+ RapportListeActivitesBuilderImpl.ACC_TYPE_LIBELLE
            		+ "\",\"abreviationTypeAc\":\""
            		+ RapportListeActivitesBuilderImpl.ACC_ABREV
            		+"\",\"codeActiviteCollective\":\""
            		+ RapportListeActivitesBuilderImpl.ACC_ID_FONC
            		+"\",\"libelleAc\":\""
            		+ RapportListeActivitesBuilderImpl.ACC_LIBELLE
            		+ "\",\"date\":\""
            		+ RapportListeActivitesBuilderImpl.ACC_DATE_DEBUT
            		+ "\",\"dateFinPrevue\":\""
            		+ RapportListeActivitesBuilderImpl.ACC_DATE_FIN_PREVUE
            		+"\",\"dateFinEffective\":\""
            		+ RapportListeActivitesBuilderImpl.ACC_DATE_FIN_EFFECTIVE
            		+"\",\"etatAc\":\""
            		+ RapportListeActivitesBuilderImpl.ACC_ETAT
            		+ "\",\"codeUnite\":\""
            		+ RapportListeActivitesBuilderImpl.ACC_CODE_UNITE
            		+expectedJson));
    }
    
    /**
     * Test de la methode find rapportsListeactivites
     * @throws Exception
     */
    @Test
    public void testExportRapportsListeActivites() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders.get(WebConstantUtil.RAPPORT_LISTE_ACT_EXPORT_PATH))
            .andExpect(MockMvcResultMatchers.status().isUnauthorized());
        
        DateFormat dfTest = new SimpleDateFormat(FORMAT_DATE_IHM);
        DateFormat df = new SimpleDateFormat(FORMAT_DATE_JSON, Locale.FRANCE);
        StringBuilder expectedCsv = new StringBuilder();
        expectedCsv.append(messageSource.getMessage("rapportlisteactivites.label.colonne.libelleTypeAc", null , null))
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(messageSource.getMessage("rapportlisteactivites.label.colonne.abreviationTypeAc", null , null))
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(messageSource.getMessage("rapportlisteactivites.label.colonne.codeActiviteCollective", null , null))
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(messageSource.getMessage("rapportlisteactivites.label.colonne.libelleAc", null , null))
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(messageSource.getMessage("rapportlisteactivites.label.colonne.date", null , null))
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(messageSource.getMessage("rapportlisteactivites.label.colonne.dateFinPrevue", null , null))
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(messageSource.getMessage("rapportlisteactivites.label.colonne.dateFinEffective", null , null))
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(messageSource.getMessage("rapportlisteactivites.label.colonne.etatAc", null , null))
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(messageSource.getMessage("rapportlisteactivites.label.colonne.codeUnite", null , null))
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR_LINE).append(RapportListeActivitesBuilderImpl.ACC_TYPE_LIBELLE )
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(RapportListeActivitesBuilderImpl.ACC_ABREV)
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(RapportListeActivitesBuilderImpl.ACC_ID_FONC)
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(RapportListeActivitesBuilderImpl.ACC_LIBELLE)
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(dfTest.format(df.parse(RapportListeActivitesBuilderImpl.ACC_DATE_DEBUT)))
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(dfTest.format(df.parse(RapportListeActivitesBuilderImpl.ACC_DATE_FIN_PREVUE)))
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(dfTest.format(df.parse(RapportListeActivitesBuilderImpl.ACC_DATE_FIN_EFFECTIVE)))
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(RapportListeActivitesBuilderImpl.ACC_ETAT)
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(RapportListeActivitesBuilderImpl.ACC_CODE_UNITE);
        
        this.mockMvc.perform(MockMvcRequestBuilders.get(WebConstantUtil.RAPPORT_LISTE_ACT_EXPORT_PATH)
                .with(SecurityMockMvcRequestPostProcessors.user(userDetailsService.loadUserByUsername("test")))
                .param(PARAMETER_ORDER_FIELD, PARAMETER_ORDER_FIELD_VALUE)
                .param(PARAMETER_ORDER, SearchOrder.ASC.toString().toUpperCase()))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(CSVResultMatcher.contains(expectedCsv.toString()));
        
    }

}
