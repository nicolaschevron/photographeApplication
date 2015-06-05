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
import fr.inra.dsi.reporting.data.impl.RapportListeChampsThematiquesBuilderImpl;
import fr.inra.dsi.reporting.dataservice.IRapportChampThematiqueService;
import fr.inra.dsi.reporting.dto.SearchOrder;
import fr.inra.dsi.reporting.exception.ValidationException;
import fr.inra.dsi.reporting.resultmatcher.CSVResultMatcher;
import fr.inra.dsi.reporting.resultmatcher.JSONResultMatcher;
import fr.inra.dsi.reporting.util.WebConstantUtil;

public class ListeChampsThematiquesControllerTest extends AbstractControllerTest {

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
	private static final String PARAMETER_ORDER_FIELD_VALUE = "intitule";
	
	/**
     * Service spring security UserDetailsService
     */
    @Autowired
    private UserDetailsService userDetailsService;
	
    @Autowired
	private transient MessageSource messageSource;
    
    @Autowired
    private IRapportChampThematiqueService rapportChampThematiqueService;
    
    /**
	 * Constructeur vide.
	 */
    public ListeChampsThematiquesControllerTest() {
		super();
	}

	/**
     * Test de la methode findRapportsListeChampsThematiques
     * @throws Exception
     */
    @Test
	public void testFindRapportsListeChampsThematiques() throws Exception {
    	
        this.mockMvc.perform(MockMvcRequestBuilders.get(WebConstantUtil.RAPPORT_LISTE_CT_FIND_PATH))
        .andExpect(MockMvcResultMatchers.status().isUnauthorized());
        StringBuilder expectedJson = new StringBuilder();
      expectedJson.append("\"}],\"colonnesRapport\":[{")
      .append("\"libelle\":\"").append(messageSource.getMessage("rapportlistect.label.colonne.code", null , null)).append("\",\"cle\":\"code")
      .append("\"},{\"libelle\":\"").append(messageSource.getMessage("rapportlistect.label.colonne.sigle", null , null)).append("\",\"cle\":\"sigle")
      .append("\"},{\"libelle\":\"").append(messageSource.getMessage("rapportlistect.label.colonne.sigleNum", null , null)).append("\",\"cle\":\"sigleNum")
      .append("\"},{\"libelle\":\"").append(messageSource.getMessage("rapportlistect.label.colonne.description", null , null)).append("\",\"cle\":\"description")
      .append("\"},{\"libelle\":\"").append(messageSource.getMessage("rapportlistect.label.colonne.intitule", null , null)).append("\",\"cle\":\"intitule")
      .append("\"},{\"libelle\":\"").append(messageSource.getMessage("rapportlistect.label.colonne.dateDebut", null , null)).append("\",\"cle\":\"dateDebut")
      .append("\"},{\"libelle\":\"").append(messageSource.getMessage("rapportlistect.label.colonne.dateFin", null , null)).append("\",\"cle\":\"dateFin")
      .append("\"},{\"libelle\":\"").append(messageSource.getMessage("rapportlistect.label.colonne.dateFinEffective", null , null)).append("\",\"cle\":\"dateFinEffective")
      .append("\"}]}"); 

      
		this.mockMvc.perform(MockMvcRequestBuilders.get(WebConstantUtil.RAPPORT_LISTE_CT_FIND_PATH)
                .with(SecurityMockMvcRequestPostProcessors.user(userDetailsService.loadUserByUsername("test"))))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(new JSONResultMatcher("{\"lignesRapport\":[{\"identifiant\":"
                    + RapportListeChampsThematiquesBuilderImpl.CT_ID + ",\"code\":"
            		+ RapportListeChampsThematiquesBuilderImpl.CT_CODE
            		+ ",\"sigle\":\""
            		+ RapportListeChampsThematiquesBuilderImpl.CT_SIGLE
            		+"\",\"sigleNum\":"
            		+ RapportListeChampsThematiquesBuilderImpl.CT_SIGLE_NUM
            		+",\"description\":\""
            		+ RapportListeChampsThematiquesBuilderImpl.CT_DESC
            		+ "\",\"intitule\":\""
            		+ RapportListeChampsThematiquesBuilderImpl.CT_INTITULE
            		+ "\",\"dateDebut\":\""
            		+ RapportListeChampsThematiquesBuilderImpl.CT_DATE_DEBUT
            		+"\",\"dateFin\":\""
            		+ RapportListeChampsThematiquesBuilderImpl.CT_DATE_FIN
            		+"\",\"dateFinEffective\":\""
            		+ RapportListeChampsThematiquesBuilderImpl.CT_DATE_EFFECTIVE
            		+expectedJson));
		
		
	}
    
    /**
     * Réalise l'export CSV d'un rapport
     * @param request
     * @param search les paramètres de recherche
     * @return
     * @throws ValidationException
     */
    @Test
    public void testExportRapportsChampsThematiques() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get(WebConstantUtil.RAPPORT_LISTE_CT_EXPORT_PATH))
        .andExpect(MockMvcResultMatchers.status().isUnauthorized());
        
        DateFormat dfTest = new SimpleDateFormat(FORMAT_DATE_IHM);
        DateFormat df = new SimpleDateFormat(FORMAT_DATE_JSON, Locale.FRANCE);
        StringBuilder expectedCsv = new StringBuilder();
        expectedCsv.append(messageSource.getMessage("rapportlistect.label.colonne.code", null , null))
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(messageSource.getMessage("rapportlistect.label.colonne.sigle", null , null))
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(messageSource.getMessage("rapportlistect.label.colonne.sigleNum", null , null))
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(messageSource.getMessage("rapportlistect.label.colonne.description", null , null))
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(messageSource.getMessage("rapportlistect.label.colonne.intitule", null , null))
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(messageSource.getMessage("rapportlistect.label.colonne.dateDebut", null , null))
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(messageSource.getMessage("rapportlistect.label.colonne.dateFin", null , null))
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(messageSource.getMessage("rapportlistect.label.colonne.dateFinEffective", null , null))
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR_LINE).append(RapportListeChampsThematiquesBuilderImpl.CT_CODE )
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(RapportListeChampsThematiquesBuilderImpl.CT_SIGLE)
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(RapportListeChampsThematiquesBuilderImpl.CT_SIGLE_NUM)
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(RapportListeChampsThematiquesBuilderImpl.CT_DESC)
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(RapportListeChampsThematiquesBuilderImpl.CT_INTITULE)
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(dfTest.format(df.parse(RapportListeChampsThematiquesBuilderImpl.CT_DATE_DEBUT)))
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(dfTest.format(df.parse(RapportListeChampsThematiquesBuilderImpl.CT_DATE_FIN)))
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(dfTest.format(df.parse(RapportListeChampsThematiquesBuilderImpl.CT_DATE_EFFECTIVE)));
        
        this.mockMvc.perform(MockMvcRequestBuilders.get(WebConstantUtil.RAPPORT_LISTE_CT_EXPORT_PATH)
                .with(SecurityMockMvcRequestPostProcessors.user(userDetailsService.loadUserByUsername("test")))
                .param(PARAMETER_ORDER_FIELD, PARAMETER_ORDER_FIELD_VALUE)
                .param(PARAMETER_ORDER, SearchOrder.ASC.toString().toUpperCase()))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(CSVResultMatcher.contains(expectedCsv.toString()));
    }
}
