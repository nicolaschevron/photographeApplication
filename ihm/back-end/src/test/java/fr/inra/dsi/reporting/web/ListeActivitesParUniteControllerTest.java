package fr.inra.dsi.reporting.web;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonString;
import javax.json.JsonValue;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import fr.inra.dsi.reporting.bean.RapportToCsvMessageConverter;
import fr.inra.dsi.reporting.data.impl.RapportListeActivitesParUniteBuilderImpl;
import fr.inra.dsi.reporting.dataservice.IRapportDescriptionService;
import fr.inra.dsi.reporting.dataservice.IRapportListeActivitesParUniteService;
import fr.inra.dsi.reporting.dto.RapportDto;
import fr.inra.dsi.reporting.dto.RoleAgent;
import fr.inra.dsi.reporting.dto.SearchOrder;
import fr.inra.dsi.reporting.model.RapportDescription;
import fr.inra.dsi.reporting.model.RapportListeActivitesParUnite;
import fr.inra.dsi.reporting.resultmatcher.CSVResultMatcher;
import fr.inra.dsi.reporting.resultmatcher.JSONResultMatcher;
import fr.inra.dsi.reporting.resultmatcher.JSONValueResultMatcher;
import fr.inra.dsi.reporting.security.Constant;
import fr.inra.dsi.reporting.security.INRAUser;
import fr.inra.dsi.reporting.util.TestUtil;
import fr.inra.dsi.reporting.util.WebConstantUtil;
import fr.inra.dsi.reporting.ws.mock.AuthenticationWebServiceMock;

public class ListeActivitesParUniteControllerTest extends AbstractControllerTest {

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
	
    @Autowired
    private IRapportListeActivitesParUniteService listeActivitesParUniteService;
    
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private IRapportDescriptionService rapportDescriptionService;
    
    public ListeActivitesParUniteControllerTest() {
		super();
	}

	@Test
    public void testSecurity() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders.get(WebConstantUtil.RAPPORT_LISTE_ACT_BY_UNITE_FIND_PATH))
            .andExpect(MockMvcResultMatchers.status().isUnauthorized());
        
        this.mockMvc.perform(MockMvcRequestBuilders.get(WebConstantUtil.RAPPORT_LISTE_ACT_BY_UNITE_FIND_PATH)
                .with(SecurityMockMvcRequestPostProcessors.user(userDetailsService.loadUserByUsername(AuthenticationWebServiceMock.MOCK_LOGIN_DU))))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    public void setListeActivitesParUniteService(
            IRapportListeActivitesParUniteService listeActivitesParUniteService) {
        this.listeActivitesParUniteService = listeActivitesParUniteService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    
    @Test
    public void testAccessRapportListeActivitesParUnite() throws Exception {
        //Utilisateurs mock
        UserDetails user = userDetailsService.loadUserByUsername(AuthenticationWebServiceMock.MOCK_LOGIN);
        UserDetails admin = userDetailsService.loadUserByUsername(AuthenticationWebServiceMock.MOCK_LOGIN_ADMIN);
        UserDetails du = userDetailsService.loadUserByUsername(AuthenticationWebServiceMock.MOCK_LOGIN_DU);
        UserDetails cd = userDetailsService.loadUserByUsername(AuthenticationWebServiceMock.MOCK_LOGIN_CD);
        UserDetails cnue = userDetailsService.loadUserByUsername(AuthenticationWebServiceMock.MOCK_LOGIN_CNUE);
        UserDetails cnoc = userDetailsService.loadUserByUsername(AuthenticationWebServiceMock.MOCK_LOGIN_CNOC);
        
        //Un utilisateur quelconque n'a pas accès
        this.mockMvc.perform(MockMvcRequestBuilders.get(WebConstantUtil.RAPPORT_LISTE_ACT_BY_UNITE_FIND_PATH)
                .with(SecurityMockMvcRequestPostProcessors.user(user)))
            .andExpect(MockMvcResultMatchers.status().isForbidden());
        
        //Un CNOC, CNUE, DU, un CD ou un admin a accès au rapport
        this.mockMvc.perform(MockMvcRequestBuilders.get(WebConstantUtil.RAPPORT_LISTE_ACT_BY_UNITE_FIND_PATH)
                .with(SecurityMockMvcRequestPostProcessors.user(du)))
            .andExpect(MockMvcResultMatchers.status().isOk());
        this.mockMvc.perform(MockMvcRequestBuilders.get(WebConstantUtil.RAPPORT_LISTE_ACT_BY_UNITE_FIND_PATH)
                .with(SecurityMockMvcRequestPostProcessors.user(admin)))
            .andExpect(MockMvcResultMatchers.status().isOk());
        this.mockMvc.perform(MockMvcRequestBuilders.get(WebConstantUtil.RAPPORT_LISTE_ACT_BY_UNITE_FIND_PATH)
                .with(SecurityMockMvcRequestPostProcessors.user(cd)))
            .andExpect(MockMvcResultMatchers.status().isOk());
        this.mockMvc.perform(MockMvcRequestBuilders.get(WebConstantUtil.RAPPORT_LISTE_ACT_BY_UNITE_FIND_PATH)
                .with(SecurityMockMvcRequestPostProcessors.user(cnoc)))
            .andExpect(MockMvcResultMatchers.status().isOk());
        this.mockMvc.perform(MockMvcRequestBuilders.get(WebConstantUtil.RAPPORT_LISTE_ACT_BY_UNITE_FIND_PATH)
                .with(SecurityMockMvcRequestPostProcessors.user(cnue)))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @Test
    public void testRapportListeActivitesParUnite() throws Exception {
        //Utilisateurs mock
        INRAUser du = (INRAUser)userDetailsService.loadUserByUsername(AuthenticationWebServiceMock.MOCK_LOGIN_DU);
        INRAUser admin = (INRAUser)userDetailsService.loadUserByUsername(AuthenticationWebServiceMock.MOCK_LOGIN_ADMIN);
        INRAUser cd = (INRAUser)userDetailsService.loadUserByUsername(AuthenticationWebServiceMock.MOCK_LOGIN_CD);
        INRAUser cnue = (INRAUser)userDetailsService.loadUserByUsername(AuthenticationWebServiceMock.MOCK_LOGIN_CNUE);
        INRAUser cnoc = (INRAUser)userDetailsService.loadUserByUsername(AuthenticationWebServiceMock.MOCK_LOGIN_CNOC);
        INRAUser dar = (INRAUser)userDetailsService.loadUserByUsername(AuthenticationWebServiceMock.MOCK_LOGIN_DAR);
        
        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.addAll(du.getAuthorities());
        authorities.addAll(cd.getAuthorities());
        List<RoleAgent> roles = new ArrayList<RoleAgent>();
        roles.addAll(du.getRoles());
        roles.addAll(cd.getRoles());
        INRAUser du_et_cd = new INRAUser(AuthenticationWebServiceMock.MOCK_LOGIN, "", authorities);
        du_et_cd.setRoles(roles);
        
        List<String> codesUnite = du.getCodesUnite();
        org.springframework.util.Assert.notEmpty(codesUnite, "Le DU utilisé pour le test n'est DU d'aucune unité");
        
        List<String> codesDept = cd.getCodesDepartement();
        org.springframework.util.Assert.notEmpty(codesDept, "Le CD utilisé pour le test n'est CD d'aucun département");
        
        //Résultat attentdu
        RapportDto expected = new RapportDto();
        List<RapportListeActivitesParUnite> lignes = new ArrayList<RapportListeActivitesParUnite>();
        RapportListeActivitesParUnite targetLigne = new RapportListeActivitesParUnite();
        targetLigne.setCodeUnite(codesUnite.get(0));
        targetLigne.setCodiqueDepartement(codesDept.get(0));
        lignes.add(targetLigne);
        expected.setLignesRapport(lignes);
        
        //Création du jeux de données
        final RapportListeActivitesParUnite ligneAppartenentDu = new RapportListeActivitesParUnite();
        ligneAppartenentDu.setCodeUnite(targetLigne.getCodeUnite());
        ligneAppartenentDu.setCodiqueDepartement(TestUtil.getNextCodeDepartement());
        listeActivitesParUniteService.saveOrUpdate(ligneAppartenentDu);
        final RapportListeActivitesParUnite ligneNAppartenentPasDu = new RapportListeActivitesParUnite();
        String codeUniteAppartenantPasDu = TestUtil.getNextCodeUnite();
        Assert.assertFalse(codesUnite.contains(codeUniteAppartenantPasDu));
        ligneNAppartenentPasDu.setCodeUnite(codeUniteAppartenantPasDu);
        ligneNAppartenentPasDu.setCodiqueDepartement(TestUtil.getNextCodeDepartement());
        listeActivitesParUniteService.saveOrUpdate(ligneNAppartenentPasDu);
        
        //L'admin voit tout
        this.mockMvc.perform(MockMvcRequestBuilders.get(WebConstantUtil.RAPPORT_LISTE_ACT_BY_UNITE_FIND_PATH)
                .with(SecurityMockMvcRequestPostProcessors.user(admin)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(new JSONValueResultMatcher("lignesRapport") {
                //On recherche la présence des deux ligne
                @Override
                public void assertValue(JsonValue actualJsonValue) {
                    JsonArray array = (JsonArray) actualJsonValue;
                    int found = 0;
                    String val1 = String.valueOf(ligneAppartenentDu.getCodeUnite());
                    String val2 = String.valueOf(ligneNAppartenentPasDu.getCodeUnite());
                    for(JsonValue item : array){
                        String itemCode = ((JsonObject)item).getString("codeUnite");
                        if(val1.equals(itemCode) || val2.equals(itemCode)) {
                            found = found+1;
                        }
                    }
                    Assert.assertTrue("Les deux lignes de rapports n'ont pas été trouvées", found > 0);
                }
            });
        
        
        final RapportListeActivitesParUnite ligneAppartenentCd = new RapportListeActivitesParUnite();
        ligneAppartenentCd.setCodiqueDepartement(targetLigne.getCodiqueDepartement());
        ligneAppartenentCd.setCodeUnite(TestUtil.getNextCodeUnite());
        listeActivitesParUniteService.saveOrUpdate(ligneAppartenentCd);
        final RapportListeActivitesParUnite ligneNAppartenentPasCd = new RapportListeActivitesParUnite();
        String codeDeptAppartenantPasCd = TestUtil.getNextCodeDepartement();
        Assert.assertFalse(codesDept.contains(codeDeptAppartenantPasCd));
        ligneNAppartenentPasCd.setCodiqueDepartement(codeDeptAppartenantPasCd);
        ligneNAppartenentPasCd.setCodeUnite(TestUtil.getNextCodeUnite());
        listeActivitesParUniteService.saveOrUpdate(ligneNAppartenentPasCd);

        
        //Le DU ne voit que les unités dont il est DU
        this.mockMvc.perform(MockMvcRequestBuilders.get(WebConstantUtil.RAPPORT_LISTE_ACT_BY_UNITE_FIND_PATH)
                .with(SecurityMockMvcRequestPostProcessors.user(du)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(new JSONValueResultMatcher("lignesRapport[0].codeUnite") {
                //Recherche dans les lignes renvoyées pour vérifier que la ligne ligneAppartenentDu est présente
                @Override
                public void assertValue(JsonValue actualJsonValue) {
                    Assert.assertEquals(ligneAppartenentDu.getCodeUnite(), ((JsonString)actualJsonValue).getString());
                }
            }).andExpect(new JSONValueResultMatcher("lignesRapport") {
                //Recherche dans les lignes renvoyées pour vérifier que la ligne ligneNAppartenentPasDu n'est pas présente
                @Override
                public void assertValue(JsonValue actualJsonValue) {
                    JsonArray array = (JsonArray) actualJsonValue;
                    for(JsonValue item : array){
                        Assert.assertFalse("L'unité n'appartenant pas au DU est présente dans le rapport",
                                ligneNAppartenentPasDu.getCodeUnite().equals(((JsonObject)item).getString("codeUnite")));
                    }
                }
            });

        //Le CD ne voit que les département dont il est CD
        this.mockMvc.perform(MockMvcRequestBuilders.get(WebConstantUtil.RAPPORT_LISTE_ACT_BY_UNITE_FIND_PATH)
                .with(SecurityMockMvcRequestPostProcessors.user(cd)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(new JSONValueResultMatcher("lignesRapport[0].codiqueDepartement") {
                //Recherche dans les lignes renvoyées pour vérifier que la ligne ligneAppartenentCd est présente
                @Override
                public void assertValue(JsonValue actualJsonValue) {
                    Assert.assertEquals(ligneAppartenentCd.getCodiqueDepartement(), ((JsonString)actualJsonValue).getString());
                }
            }).andExpect(new JSONValueResultMatcher("lignesRapport") {
                //Recherche dans les lignes renvoyées pour vérifier que la ligne ligneNAppartenentPasCd n'est pas présente
                @Override
                public void assertValue(JsonValue actualJsonValue) {
                    JsonArray array = (JsonArray) actualJsonValue;
                    for(JsonValue item : array){
                        Assert.assertFalse("L'unité n'appartenant pas au CD est présente dans le rapport",
                        		ligneNAppartenentPasCd.getCodiqueDepartement().equals(((JsonObject)item).getString("codiqueDepartement")));
                    }
                }
            });
        
        final RapportListeActivitesParUnite ligneAppartenantCnue = new RapportListeActivitesParUnite();
        ligneAppartenantCnue.setAbreviationTypeAc("ACE");
        listeActivitesParUniteService.saveOrUpdate(ligneAppartenantCnue);
        final RapportListeActivitesParUnite ligneNAppartenantCnoc = new RapportListeActivitesParUnite();
        ligneNAppartenantCnoc.setAbreviationTypeAc("ACS");
        listeActivitesParUniteService.saveOrUpdate(ligneNAppartenantCnoc);
        
        //Le CNOC ne voit que les ACS
        this.mockMvc.perform(MockMvcRequestBuilders.get(WebConstantUtil.RAPPORT_LISTE_ACT_BY_UNITE_FIND_PATH)
                .with(SecurityMockMvcRequestPostProcessors.user(cnoc)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(new JSONValueResultMatcher("lignesRapport[0].abreviationTypeAc") {
                //Recherche dans les lignes renvoyées pour vérifier que la ligne ligneAppartenentCd est présente
                @Override
                public void assertValue(JsonValue actualJsonValue) {
                    Assert.assertEquals(ligneNAppartenantCnoc.getAbreviationTypeAc(), ((JsonString)actualJsonValue).getString());
                }
            }).andExpect(new JSONValueResultMatcher("lignesRapport") {
                //Recherche dans les lignes renvoyées pour vérifier que la ligne ligneNAppartenentPasCd n'est pas présente
                @Override
                public void assertValue(JsonValue actualJsonValue) {
                    JsonArray array = (JsonArray) actualJsonValue;
                    for(JsonValue item : array){
                        Assert.assertFalse("L'unité n'appartenant pas au DU est présente dans le rapport",
                        		ligneAppartenantCnue.getAbreviationTypeAc().equals(((JsonObject)item).getString("abreviationTypeAc")));
                    }
                }
            });
        
        //Le CNOC ne voit que les ACE
        this.mockMvc.perform(MockMvcRequestBuilders.get(WebConstantUtil.RAPPORT_LISTE_ACT_BY_UNITE_FIND_PATH)
                .with(SecurityMockMvcRequestPostProcessors.user(cnue)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(new JSONValueResultMatcher("lignesRapport[0].abreviationTypeAc") {
                //Recherche dans les lignes renvoyées pour vérifier que la ligne ligneAppartenentCd est présente
                @Override
                public void assertValue(JsonValue actualJsonValue) {
                    Assert.assertEquals(ligneAppartenantCnue.getAbreviationTypeAc(), ((JsonString)actualJsonValue).getString());
                }
            }).andExpect(new JSONValueResultMatcher("lignesRapport") {
                //Recherche dans les lignes renvoyées pour vérifier que la ligne ligneNAppartenentPasCd n'est pas présente
                @Override
                public void assertValue(JsonValue actualJsonValue) {
                    JsonArray array = (JsonArray) actualJsonValue;
                    for(JsonValue item : array){
                        Assert.assertFalse("L'unité n'appartenant pas au DU est présente dans le rapport",
                        		ligneNAppartenantCnoc.getAbreviationTypeAc().equals(((JsonObject)item).getString("abreviationTypeAc")));
                    }
                }
            });
        
        
        final RapportListeActivitesParUnite ligneAppartenentDuMaisPasCd = new RapportListeActivitesParUnite();
        String codeDeptAppartenentDuMaisPasCd = TestUtil.getNextCodeDepartement();
        Assert.assertFalse(codesDept.contains(codeDeptAppartenentDuMaisPasCd));
        ligneAppartenentDuMaisPasCd.setCodiqueDepartement(codeDeptAppartenentDuMaisPasCd);
        ligneAppartenentDuMaisPasCd.setCodeUnite(TestUtil.getNextCodeUnite());
        listeActivitesParUniteService.saveOrUpdate(ligneAppartenentDuMaisPasCd);
        
        //On active le rapport uniquement pour les DU
        RapportDescription rapportDesc = rapportDescriptionService.getRapportDescriptionForClazzAndRestCall(RapportListeActivitesParUnite.class.getSimpleName(), WebConstantUtil.RAPPORT_LISTE_ACT_BY_UNITE_PATH);
        String oldRestriction = rapportDesc.getRestrictionRoles();
        rapportDesc.setRestrictionRoles(Constant.ROLE_DU);
        rapportDescriptionService.saveOrUpdate(rapportDesc);
        try {
            //On test si l'utilisateur qui est CD et DU ne voit que les informations pour le DU.
            this.mockMvc.perform(MockMvcRequestBuilders.get(WebConstantUtil.RAPPORT_LISTE_ACT_BY_UNITE_FIND_PATH)
                    .with(SecurityMockMvcRequestPostProcessors.user(du_et_cd)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(new JSONValueResultMatcher("lignesRapport") {
                    //Recherche dans les lignes renvoyées pour vérifier que la ligne ligneAppartenentCd n'est pas présente
                    @Override
                    public void assertValue(JsonValue actualJsonValue) {
                        JsonArray array = (JsonArray) actualJsonValue;
                        for(JsonValue item : array){
                            Assert.assertFalse("L'unité appartenant au CD est présente dans le rapport",
                                    ligneAppartenentDuMaisPasCd.getCodiqueDepartement().equals(((JsonObject)item).getString("codiqueDepartement")));
                        }
                    }
                });
        } catch(Exception e){
            throw e;
        } finally {
            //Restauration des rôles
            rapportDesc.setRestrictionRoles(oldRestriction);
            rapportDescriptionService.saveOrUpdate(rapportDesc);
        }
        
        
        Assert.assertFalse("Le DAR n'est DAR d'aucune structure", dar.getCodesDirectionAppui().isEmpty());
        
        final RapportListeActivitesParUnite ligneAppartenentDar = new RapportListeActivitesParUnite();
        ligneAppartenentDar.setCodiqueDepartement(dar.getCodesDirectionAppui().get(0));
        ligneAppartenentDar.setCodeUnite(TestUtil.getNextCodeUnite());
        listeActivitesParUniteService.saveOrUpdate(ligneAppartenentDar);
        final RapportListeActivitesParUnite ligneNAppartenentPasDar = new RapportListeActivitesParUnite();
        String codeDeptAppartenantPasDar = TestUtil.getNextCodeDepartement();
        Assert.assertFalse(codesDept.contains(codeDeptAppartenantPasDar));
        ligneNAppartenentPasDar.setCodiqueDepartement(codeDeptAppartenantPasDar);
        ligneNAppartenentPasDar.setCodeUnite(TestUtil.getNextCodeUnite());
        listeActivitesParUniteService.saveOrUpdate(ligneNAppartenentPasDar);
        
        //Le DAR ne voit que les directions dont il est DAR
        this.mockMvc.perform(MockMvcRequestBuilders.get(WebConstantUtil.RAPPORT_LISTE_ACT_BY_UNITE_FIND_PATH)
                .with(SecurityMockMvcRequestPostProcessors.user(dar)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(new JSONValueResultMatcher("lignesRapport[0].codiqueDepartement") {
                //Recherche dans les lignes renvoyées pour vérifier que la ligne ligneAppartenentDar est présente
                @Override
                public void assertValue(JsonValue actualJsonValue) {
                    Assert.assertEquals(ligneAppartenentDar.getCodiqueDepartement(), ((JsonString)actualJsonValue).getString());
                }
            }).andExpect(new JSONValueResultMatcher("lignesRapport") {
                //Recherche dans les lignes renvoyées pour vérifier que la ligne ligneNAppartenentPasDar n'est pas présente
                @Override
                public void assertValue(JsonValue actualJsonValue) {
                    JsonArray array = (JsonArray) actualJsonValue;
                    for(JsonValue item : array){
                        Assert.assertFalse("L'unité n'appartenant pas au DAR est présente dans le rapport",
                                ligneNAppartenentPasDar.getCodiqueDepartement().equals(((JsonObject)item).getString("codiqueDepartement")));
                    }
                }
            });
    }
    
    @Test
    public void testFindRapportsListeActivitesParUnite() throws Exception {
    	
        this.mockMvc.perform(MockMvcRequestBuilders.get(WebConstantUtil.RAPPORT_LISTE_ACT_BY_UNITE_FIND_PATH))
        .andExpect(MockMvcResultMatchers.status().isUnauthorized());
        StringBuilder expectedJson = new StringBuilder();
       expectedJson.append("\"}],\"colonnesRapport\":[{")
      .append("\"libelle\":\"").append(messageSource.getMessage("rapportlisteactivitesparunite.label.colonne.codeActiviteCollective", null , null)).append("\",\"cle\":\"codeActiviteCollective")
      .append("\"},{\"libelle\":\"").append(messageSource.getMessage("rapportlisteactivitesparunite.label.colonne.libelleAc", null , null)).append("\",\"cle\":\"libelleAc")
      .append("\"},{\"libelle\":\"").append(messageSource.getMessage("rapportlisteactivitesparunite.label.colonne.libelleTypeAc", null , null)).append("\",\"cle\":\"libelleTypeAc")
      .append("\"},{\"libelle\":\"").append(messageSource.getMessage("rapportlisteactivitesparunite.label.colonne.codeUnite", null , null)).append("\",\"cle\":\"codeUnite")
      .append("\"},{\"libelle\":\"").append(messageSource.getMessage("rapportlisteactivitesparunite.label.colonne.sigleUnite", null , null)).append("\",\"cle\":\"sigleUnite")
      .append("\"},{\"libelle\":\"").append(messageSource.getMessage("rapportlisteactivitesparunite.label.colonne.typeUnite", null , null)).append("\",\"cle\":\"typeUnite")
      .append("\"},{\"libelle\":\"").append(messageSource.getMessage("rapportlisteactivitesparunite.label.colonne.caracteristiqueUnite", null , null)).append("\",\"cle\":\"caracteristiqueUnite")
      .append("\"},{\"libelle\":\"").append(messageSource.getMessage("rapportlisteactivitesparunite.label.colonne.uniteLeader", null , null)).append("\",\"cle\":\"uniteLeader")
      .append("\"},{\"libelle\":\"").append(messageSource.getMessage("rapportlisteactivitesparunite.label.colonne.date", null , null)).append("\",\"cle\":\"date")
      .append("\"},{\"libelle\":\"").append(messageSource.getMessage("rapportlisteactivitesparunite.label.colonne.dateFinPrevue", null , null)).append("\",\"cle\":\"dateFinPrevue")
      .append("\"},{\"libelle\":\"").append(messageSource.getMessage("rapportlisteactivitesparunite.label.colonne.dateFinEffective", null , null)).append("\",\"cle\":\"dateFinEffective")
      .append("\"},{\"libelle\":\"").append(messageSource.getMessage("rapportlisteactivitesparunite.label.colonne.codiqueDepartement", null , null)).append("\",\"cle\":\"codiqueDepartement")
      .append("\"},{\"libelle\":\"").append(messageSource.getMessage("rapportlisteactivitesparunite.label.colonne.intituleDepartement", null , null)).append("\",\"cle\":\"intituleDepartement")
      .append("\"},{\"libelle\":\"").append(messageSource.getMessage("rapportlisteactivitesparunite.label.colonne.sigleDepartement", null , null)).append("\",\"cle\":\"sigleDepartement")
      .append("\"},{\"libelle\":\"").append(messageSource.getMessage("rapportlisteactivitesparunite.label.colonne.codeCentre", null , null)).append("\",\"cle\":\"codeCentre")
      .append("\"},{\"libelle\":\"").append(messageSource.getMessage("rapportlisteactivitesparunite.label.colonne.libelleCentre", null , null)).append("\",\"cle\":\"libelleCentre")
      .append("\"},{\"libelle\":\"").append(messageSource.getMessage("rapportlisteactivitesparunite.label.colonne.sigleCentre", null , null)).append("\",\"cle\":\"sigleCentre")
      .append("\"},{\"libelle\":\"").append(messageSource.getMessage("rapportlisteactivitesparunite.label.colonne.sitePrincipalCentre", null , null)).append("\",\"cle\":\"sitePrincipalCentre")
      .append("\"}]}"); 
      
 		this.mockMvc.perform(MockMvcRequestBuilders.get(WebConstantUtil.RAPPORT_LISTE_ACT_BY_UNITE_FIND_PATH)
                .with(SecurityMockMvcRequestPostProcessors.user(userDetailsService.loadUserByUsername(AuthenticationWebServiceMock.MOCK_LOGIN_DU))))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(new JSONResultMatcher("{\"lignesRapport\":[{\"codeActiviteCollective\":\""
                    + RapportListeActivitesParUniteBuilderImpl.AC_U_CODE_ACTIVITE_COLLECTIVE + "\",\"libelleAc\":\""
            		+ RapportListeActivitesParUniteBuilderImpl.AC_U_LIBELLE_AC
            		+ "\",\"libelleTypeAc\":\""
            		+ RapportListeActivitesParUniteBuilderImpl.AC_U_LIBELLE_TYPE_AC
            		+"\",\"codeUnite\":\""
            		+ RapportListeActivitesParUniteBuilderImpl.AC_U_CODE_UNITE
            		+"\",\"sigleUnite\":\""
            		+ RapportListeActivitesParUniteBuilderImpl.AC_U_SIGLE_UNITE
            		+"\",\"typeUnite\":\""
            		+ RapportListeActivitesParUniteBuilderImpl.AC_U_TYPE_UNITE
            		+ "\",\"caracteristiqueUnite\":\""
            		+ RapportListeActivitesParUniteBuilderImpl.AC_U_CARACTERISTIQUE_UNITE
            		+ "\",\"uniteLeader\":"
            		+ RapportListeActivitesParUniteBuilderImpl.AC_U_UNITE_LEADER
            		+",\"date\":\""
            		+ RapportListeActivitesParUniteBuilderImpl.AC_U_DATE
            		+"\",\"dateFinPrevue\":\""
            		+ RapportListeActivitesParUniteBuilderImpl.AC_U_DATE_FIN_PREVUE
            		+ "\",\"dateFinEffective\":\""
            		+ RapportListeActivitesParUniteBuilderImpl.AC_U_DATE_FIN_EFFECTIVE
            		+ "\",\"codiqueDepartement\":\""
            		+ RapportListeActivitesParUniteBuilderImpl.AC_U_CODIQUE_DEPARTEMENT
            		+"\",\"intituleDepartement\":\""
            		+ RapportListeActivitesParUniteBuilderImpl.AC_U_INTITULE_DEPARTEMENT
            		+ "\",\"sigleDepartement\":\""
            		+ RapportListeActivitesParUniteBuilderImpl.AC_U_SIGLE_DEPARTEMENT
            		+ "\",\"codeCentre\":\""
            		+ RapportListeActivitesParUniteBuilderImpl.AC_U_CODE_CENTRE
            		+"\",\"libelleCentre\":\""
            		+ RapportListeActivitesParUniteBuilderImpl.AC_U_LIBELLE_CENTRE
            		+"\",\"sigleCentre\":\""
            		+ RapportListeActivitesParUniteBuilderImpl.AC_U_SIGLE_CENTRE
            		+ "\",\"sitePrincipalCentre\":\""
            		+ RapportListeActivitesParUniteBuilderImpl.AC_U_SITE_PRINCIPAL_CENTRE
            		+expectedJson));
      
    	
    }
    
    /**
     * Test l'export des Activites par unites
     * @throws Exception
     */
    @Test
    public void testExportRapportsListeActivitesParUnite() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders.get(WebConstantUtil.RAPPORT_LISTE_ACT_BY_UNITE_EXPORT_PATH))
            .andExpect(MockMvcResultMatchers.status().isUnauthorized());
        
        DateFormat dfTest = new SimpleDateFormat(FORMAT_DATE_IHM);
        DateFormat df = new SimpleDateFormat(FORMAT_DATE_JSON, Locale.FRANCE);
        StringBuilder expectedCsv = new StringBuilder();
        
        expectedCsv.append(messageSource.getMessage("rapportlisteactivitesparunite.label.colonne.codeActiviteCollective", null , null))
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(messageSource.getMessage("rapportlisteactivitesparunite.label.colonne.libelleAc", null , null))
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(messageSource.getMessage("rapportlisteactivitesparunite.label.colonne.libelleTypeAc", null , null))
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(messageSource.getMessage("rapportlisteactivitesparunite.label.colonne.codeUnite", null , null))
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(messageSource.getMessage("rapportlisteactivitesparunite.label.colonne.sigleUnite", null , null))
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(messageSource.getMessage("rapportlisteactivitesparunite.label.colonne.typeUnite", null , null))
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(messageSource.getMessage("rapportlisteactivitesparunite.label.colonne.caracteristiqueUnite", null , null))
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(messageSource.getMessage("rapportlisteactivitesparunite.label.colonne.uniteLeader", null , null))
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(messageSource.getMessage("rapportlisteactivitesparunite.label.colonne.date", null , null))
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(messageSource.getMessage("rapportlisteactivitesparunite.label.colonne.dateFinPrevue", null , null))
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(messageSource.getMessage("rapportlisteactivitesparunite.label.colonne.dateFinEffective", null , null))
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(messageSource.getMessage("rapportlisteactivitesparunite.label.colonne.codiqueDepartement", null , null))
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(messageSource.getMessage("rapportlisteactivitesparunite.label.colonne.intituleDepartement", null , null))
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(messageSource.getMessage("rapportlisteactivitesparunite.label.colonne.sigleDepartement", null , null))
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(messageSource.getMessage("rapportlisteactivitesparunite.label.colonne.codeCentre", null , null))
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(messageSource.getMessage("rapportlisteactivitesparunite.label.colonne.libelleCentre", null , null))
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(messageSource.getMessage("rapportlisteactivitesparunite.label.colonne.sigleCentre", null , null))
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(messageSource.getMessage("rapportlisteactivitesparunite.label.colonne.sitePrincipalCentre", null , null))
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(messageSource.getMessage("rapportlisteactivitesparunite.label.colonne.matriculeAp", null , null))
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(messageSource.getMessage("rapportlisteactivitesparunite.label.colonne.nomAp", null , null))
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(messageSource.getMessage("rapportlisteactivitesparunite.label.colonne.prenomAp", null , null))
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(messageSource.getMessage("rapportlisteactivitesparunite.label.colonne.gradeAp", null , null))
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(messageSource.getMessage("rapportlisteactivitesparunite.label.colonne.mailAp", null , null))
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(messageSource.getMessage("rapportlisteactivitesparunite.label.colonne.dateNominationAp", null , null))
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(messageSource.getMessage("rapportlisteactivitesparunite.label.colonne.pourcentageTempsTravailAp", null , null))
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR_LINE).append(RapportListeActivitesParUniteBuilderImpl.AC_U_CODE_ACTIVITE_COLLECTIVE )
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(RapportListeActivitesParUniteBuilderImpl.AC_U_LIBELLE_AC)
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(RapportListeActivitesParUniteBuilderImpl.AC_U_LIBELLE_TYPE_AC)
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(RapportListeActivitesParUniteBuilderImpl.AC_U_CODE_UNITE)
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(RapportListeActivitesParUniteBuilderImpl.AC_U_SIGLE_UNITE)
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(RapportListeActivitesParUniteBuilderImpl.AC_U_TYPE_UNITE)
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(RapportListeActivitesParUniteBuilderImpl.AC_U_CARACTERISTIQUE_UNITE)
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(RapportListeActivitesParUniteBuilderImpl.AC_U_UNITE_LEADER)
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(dfTest.format(df.parse(RapportListeActivitesParUniteBuilderImpl.AC_U_DATE)))
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(dfTest.format(df.parse(RapportListeActivitesParUniteBuilderImpl.AC_U_DATE_FIN_PREVUE)))
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(dfTest.format(df.parse(RapportListeActivitesParUniteBuilderImpl.AC_U_DATE_FIN_EFFECTIVE)))
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(RapportListeActivitesParUniteBuilderImpl.AC_U_CODIQUE_DEPARTEMENT)
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(RapportListeActivitesParUniteBuilderImpl.AC_U_INTITULE_DEPARTEMENT)
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(RapportListeActivitesParUniteBuilderImpl.AC_U_SIGLE_DEPARTEMENT)
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(RapportListeActivitesParUniteBuilderImpl.AC_U_CODE_CENTRE)
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(RapportListeActivitesParUniteBuilderImpl.AC_U_LIBELLE_CENTRE)
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(RapportListeActivitesParUniteBuilderImpl.AC_U_SIGLE_CENTRE)
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(RapportListeActivitesParUniteBuilderImpl.AC_U_SITE_PRINCIPAL_CENTRE)
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(RapportListeActivitesParUniteBuilderImpl.AC_U_AP_MATRICULE)
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(RapportListeActivitesParUniteBuilderImpl.AC_U_AP_NOM)
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(RapportListeActivitesParUniteBuilderImpl.AC_U_AP_PRENOM)
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(RapportListeActivitesParUniteBuilderImpl.AC_U_AP_GRADE)
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(RapportListeActivitesParUniteBuilderImpl.AC_U_AP_MAIL)
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(dfTest.format(df.parse(RapportListeActivitesParUniteBuilderImpl.AC_U_AP_DATE_NOMINATION)))
        .append(RapportToCsvMessageConverter.CSV_SEPARATOR).append(RapportListeActivitesParUniteBuilderImpl.AC_U_AP_POURCENTAGE_TRAVAIL);
        
        
        this.mockMvc.perform(MockMvcRequestBuilders.get(WebConstantUtil.RAPPORT_LISTE_ACT_BY_UNITE_EXPORT_PATH)
                .with(SecurityMockMvcRequestPostProcessors.user(userDetailsService.loadUserByUsername(AuthenticationWebServiceMock.MOCK_LOGIN_DU)))
                .param(PARAMETER_ORDER_FIELD, PARAMETER_ORDER_FIELD_VALUE)
                .param(PARAMETER_ORDER, SearchOrder.ASC.toString().toUpperCase()))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(CSVResultMatcher.contains(expectedCsv.toString()));
        
    }
    
}
