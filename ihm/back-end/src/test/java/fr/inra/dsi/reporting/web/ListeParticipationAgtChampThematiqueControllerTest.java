package fr.inra.dsi.reporting.web;


import java.util.ArrayList;
import java.util.List;

import javax.json.JsonArray;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonString;
import javax.json.JsonValue;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import fr.inra.dsi.reporting.dataservice.IRapportParticipationAgtChampThematiqueService;
import fr.inra.dsi.reporting.model.RapportParticipationAgtChampThematique;
import fr.inra.dsi.reporting.resultmatcher.CSVResultMatcher;
import fr.inra.dsi.reporting.resultmatcher.JSONValueResultMatcher;
import fr.inra.dsi.reporting.security.INRAUser;
import fr.inra.dsi.reporting.util.TestUtil;
import fr.inra.dsi.reporting.util.WebConstantUtil;
import fr.inra.dsi.reporting.ws.mock.AuthenticationWebServiceMock;

public class ListeParticipationAgtChampThematiqueControllerTest extends AbstractControllerTest {

    private static final String RAPPORT_PATH = WebConstantUtil.RAPPORT_PARTICIPATION_AGT_CT_PATH;
	
    @Autowired
    private IRapportParticipationAgtChampThematiqueService rapportParticipationAgtChampThematiqueService;
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    //Utilisateurs mock
    private INRAUser user;
    private INRAUser admin;
    private INRAUser du;
    private INRAUser dar;
    private INRAUser cd;
    private INRAUser pc;
    private INRAUser cnue;
    private INRAUser cnoc;

    //Lignes du rapport
    private final RapportParticipationAgtChampThematique appartientDu = new RapportParticipationAgtChampThematique();
    private final RapportParticipationAgtChampThematique appartientPasDu = new RapportParticipationAgtChampThematique();
    
    private final RapportParticipationAgtChampThematique appartientDar = new RapportParticipationAgtChampThematique();
    private final RapportParticipationAgtChampThematique appartientPasDar = new RapportParticipationAgtChampThematique();
    
    private final RapportParticipationAgtChampThematique appartientCd = new RapportParticipationAgtChampThematique();
    private final RapportParticipationAgtChampThematique appartientPasCd = new RapportParticipationAgtChampThematique();
    
    private final RapportParticipationAgtChampThematique appartientPc = new RapportParticipationAgtChampThematique();
    private final RapportParticipationAgtChampThematique appartientPasPc = new RapportParticipationAgtChampThematique();

    @Before
    public void initTest() throws Exception{
        user = (INRAUser)userDetailsService.loadUserByUsername(AuthenticationWebServiceMock.MOCK_LOGIN);
        admin = (INRAUser)userDetailsService.loadUserByUsername(AuthenticationWebServiceMock.MOCK_LOGIN_ADMIN);
        
        du = (INRAUser)userDetailsService.loadUserByUsername(AuthenticationWebServiceMock.MOCK_LOGIN_DU);
        org.springframework.util.Assert.notEmpty(du.getCodesUnite(), "Le DU utilisé pour le test n'est DU d'aucune unité");
        appartientDu.setCodeUniteAgentRpCt(du.getCodesUnite().get(0));
        String codeUniteAppartientPasDu = TestUtil.getNextCodeString();
        Assert.assertFalse(du.getCodesUnite().contains(codeUniteAppartientPasDu));
        appartientPasDu.setCodeUniteAgentRpCt(codeUniteAppartientPasDu);

        //appartientPasDu.setIdentifiant(... setté par la BDD ...);
        
        dar = (INRAUser)userDetailsService.loadUserByUsername(AuthenticationWebServiceMock.MOCK_LOGIN_DAR);
        org.springframework.util.Assert.notEmpty(dar.getCodesDirectionAppui(), "Le DAR utilisé pour le test n'est DAR d'aucune direction d'appui");
        appartientDar.setCodiqueDepartementAgentRpCt(dar.getCodesDirectionAppui().get(0));
        //appartientDar.setIdentifiant(... setté par la BDD ...);
        String codeDepartementAppartientPasDar = TestUtil.getNextCodeString();
        Assert.assertFalse(dar.getCodesDirectionAppui().contains(codeDepartementAppartientPasDar));
        appartientPasDar.setCodiqueDepartementAgentRpCt(codeDepartementAppartientPasDar);
        //appartientPasDar.setIdentifiant(... setté par la BDD ...);
        
        cd = (INRAUser)userDetailsService.loadUserByUsername(AuthenticationWebServiceMock.MOCK_LOGIN_CD);
        org.springframework.util.Assert.notEmpty(cd.getCodesDepartement(), "Le CD utilisé pour le test n'est CD d'aucun département");
        appartientCd.setCodiqueDepartementAgentRpCt(cd.getCodesDepartement().get(0));
        //appartientCd.setIdentifiant(... setté par la BDD ...);
        String codeDepartementAppartientPasCd = TestUtil.getNextCodeString();
        Assert.assertFalse(cd.getCodesDepartement().contains(codeDepartementAppartientPasCd));
        appartientPasCd.setCodiqueDepartementAgentRpCt(codeDepartementAppartientPasCd);
        //appartientPasCd.setIdentifiant(... setté par la BDD ...);
        
        pc = (INRAUser)userDetailsService.loadUserByUsername(AuthenticationWebServiceMock.MOCK_LOGIN_PC);
        org.springframework.util.Assert.notEmpty(pc.getCodesCentre(), "Le PC utilisé pour le test n'est PC d'aucun centre");
        appartientPc.setCodeCentreRpCt(pc.getCodesCentre().get(0));
        //appartientPc.setIdentifiant(... setté par la BDD ...);
        String codeDepartementAppartientPasPc = TestUtil.getNextCodeString();
        Assert.assertFalse(cd.getCodesDepartement().contains(codeDepartementAppartientPasPc));
        appartientPasPc.setCodeCentreRpCt(codeDepartementAppartientPasPc);
        //appartientPasPc.setIdentifiant(... setté par la BDD ...);
        
        cnue = (INRAUser)userDetailsService.loadUserByUsername(AuthenticationWebServiceMock.MOCK_LOGIN_CNUE);
        cnoc = (INRAUser)userDetailsService.loadUserByUsername(AuthenticationWebServiceMock.MOCK_LOGIN_CNOC);

        rapportParticipationAgtChampThematiqueService.saveOrUpdate(appartientDu);
        rapportParticipationAgtChampThematiqueService.saveOrUpdate(appartientPasDu);
        rapportParticipationAgtChampThematiqueService.saveOrUpdate(appartientCd);
        rapportParticipationAgtChampThematiqueService.saveOrUpdate(appartientPasCd);
        rapportParticipationAgtChampThematiqueService.saveOrUpdate(appartientDar);
        rapportParticipationAgtChampThematiqueService.saveOrUpdate(appartientPasDar);
        rapportParticipationAgtChampThematiqueService.saveOrUpdate(appartientPc);
        rapportParticipationAgtChampThematiqueService.saveOrUpdate(appartientPasPc);

    }
    
    @Test
    public void testDU() throws Exception{
        final INRAUser finalDu = du;
        this.mockMvc.perform(MockMvcRequestBuilders.get(RAPPORT_PATH+"/all")
                .with(SecurityMockMvcRequestPostProcessors.user(du)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(new JSONValueResultMatcher("lignesRapport[*].codeUniteAgentRpCt") {
                //Toutes les lignes affichées sont affectées à une unité du DU
                @Override
                public void assertValue(JsonValue actualJsonValue) {
                    Assert.assertTrue(finalDu.getCodesUnite().contains(((JsonString)actualJsonValue).getString()));
                }
            })
            .andExpect(new JSONValueResultMatcher("lignesRapport[?].identifiant") {
                //La ligne appartientDu est dans le résultat
                @Override
                public void assertValue(JsonValue actualJsonValue) {
                    Assert.assertEquals(appartientDu.getIdentifiant(), ((JsonNumber)actualJsonValue).intValue());
                }
            })
            .andExpect(new JSONValueResultMatcher("lignesRapport[*].identifiant") {
                //La ligne appartientPasDu n'est pas dans le résultat
                @Override
                public void assertValue(JsonValue actualJsonValue) {
                    Assert.assertFalse(appartientPasDu.getIdentifiant() == ((JsonNumber)actualJsonValue).intValue());
                }
            });
    }
    
    @Test
    public void testCD() throws Exception{
        final INRAUser finalCd = cd;
        this.mockMvc.perform(MockMvcRequestBuilders.get(RAPPORT_PATH+"/all")
                .with(SecurityMockMvcRequestPostProcessors.user(cd)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(new JSONValueResultMatcher("lignesRapport[*].codiqueDepartementAgentRpCt") {
                //Toutes les lignes affichées sont affectées à département du CD
                @Override
                public void assertValue(JsonValue actualJsonValue) {
                    Assert.assertTrue(finalCd.getCodesDepartement().contains(((JsonString)actualJsonValue).getString()));
                }
            })
            .andExpect(new JSONValueResultMatcher("lignesRapport[?].identifiant") {
                //La ligne appartientCd est dans le résultat
                @Override
                public void assertValue(JsonValue actualJsonValue) {
                    Assert.assertEquals(appartientCd.getIdentifiant(), ((JsonNumber)actualJsonValue).intValue());
                }
            })
            .andExpect(new JSONValueResultMatcher("lignesRapport[*].identifiant") {
                //La ligne appartientPasCd n'est pas dans le résultat
                @Override
                public void assertValue(JsonValue actualJsonValue) {
                    Assert.assertFalse(appartientPasCd.getIdentifiant() == ((JsonNumber)actualJsonValue).intValue());
                }
            });
    }
    
    @Test
    public void testDAR() throws Exception{
        final INRAUser finalDar = dar;
        this.mockMvc.perform(MockMvcRequestBuilders.get(RAPPORT_PATH+"/all")
                .with(SecurityMockMvcRequestPostProcessors.user(dar)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(new JSONValueResultMatcher("lignesRapport[*].codiqueDepartementAgentRpCt") {
                //Toutes les lignes affichées sont affectées à une direction du DAR
                @Override
                public void assertValue(JsonValue actualJsonValue) {
                    Assert.assertTrue(finalDar.getCodesDirectionAppui().contains(((JsonString)actualJsonValue).getString()));
                }
            })
            .andExpect(new JSONValueResultMatcher("lignesRapport[?].identifiant") {
                //La ligne appartientDar est dans le résultat
                @Override
                public void assertValue(JsonValue actualJsonValue) {
                    Assert.assertEquals(appartientDar.getIdentifiant(), ((JsonNumber)actualJsonValue).intValue());
                }
            })
            .andExpect(new JSONValueResultMatcher("lignesRapport[*].identifiant") {
                //La ligne appartientPasDar n'est pas dans le résultat
                @Override
                public void assertValue(JsonValue actualJsonValue) {
                    Assert.assertFalse(appartientPasDar.getIdentifiant() == ((JsonNumber)actualJsonValue).intValue());
                }
            });
    }
    
    @Test
    public void testPC() throws Exception{
        final INRAUser finalPc = pc;
        this.mockMvc.perform(MockMvcRequestBuilders.get(RAPPORT_PATH+"/all")
                .with(SecurityMockMvcRequestPostProcessors.user(pc)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(new JSONValueResultMatcher("lignesRapport[*].codeCentreRpCt") {
                //Tous les lignes affichées sont affectés à un centre du PC
                @Override
                public void assertValue(JsonValue actualJsonValue) {
                    Assert.assertTrue(finalPc.getCodesCentre().contains(((JsonString)actualJsonValue).getString()));
                }
            })
            .andExpect(new JSONValueResultMatcher("lignesRapport[?].identifiant") {
                //La ligne appartientPc est dans le résultat
                @Override
                public void assertValue(JsonValue actualJsonValue) {
                    Assert.assertEquals(appartientPc.getIdentifiant(), ((JsonNumber)actualJsonValue).intValue());
                }
            })
            .andExpect(new JSONValueResultMatcher("lignesRapport[*].identifiant") {
                //La ligne appartientPasDar n'est pas dans le résultat
                @Override
                public void assertValue(JsonValue actualJsonValue) {
                    Assert.assertFalse(appartientPasPc.getIdentifiant() == ((JsonNumber)actualJsonValue).intValue());
                }
            });
    }

    @Test
    public void testCNOC() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders.get(RAPPORT_PATH+"/all")
                .with(SecurityMockMvcRequestPostProcessors.user(cnoc)))
            .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    public void testCNUE() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders.get(RAPPORT_PATH+"/all")
                .with(SecurityMockMvcRequestPostProcessors.user(cnue)))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }
    
    @Test
    public void testADMIN() throws Exception{
        final List<Integer> listeIdentifiant = new ArrayList<Integer>();
        listeIdentifiant.add(appartientDu.getIdentifiant());
        listeIdentifiant.add(appartientPasDu.getIdentifiant());
        listeIdentifiant.add(appartientCd.getIdentifiant());
        listeIdentifiant.add(appartientPasCd.getIdentifiant());
        listeIdentifiant.add(appartientDar.getIdentifiant());
        listeIdentifiant.add(appartientPasDar.getIdentifiant());
        
        this.mockMvc.perform(MockMvcRequestBuilders.get(RAPPORT_PATH+"/all")
                .with(SecurityMockMvcRequestPostProcessors.user(admin)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(new JSONValueResultMatcher("lignesRapport") {
                //On retrouve toutes les lignes
                @Override
                public void assertValue(JsonValue actualJsonValue) {
                    int found = 0;
                    for(Integer code : listeIdentifiant) {
                        for(JsonValue item : (JsonArray)actualJsonValue){
                            if(code == ((JsonObject)item).getInt("identifiant")){
                                ++found;
                            }
                        }
                    }
                    Assert.assertEquals(listeIdentifiant.size(), found);
                }
            });
    }
    
    @Test
    public void testAccess() throws Exception{
        //Accès interdit
        this.mockMvc.perform(MockMvcRequestBuilders.get(RAPPORT_PATH+"/all"))
            .andExpect(MockMvcResultMatchers.status().isUnauthorized());
        this.mockMvc.perform(MockMvcRequestBuilders.get(RAPPORT_PATH+"/all")
                .with(SecurityMockMvcRequestPostProcessors.user(user)))
            .andExpect(MockMvcResultMatchers.status().isForbidden());
    }
    
    @Test
    public void testExport() throws Exception{
        String expectedCsv = "Code CT;CT;Sigle CT;Département agent;Département du CT;Matricule agent;Nom agent;prénom agent;Unité de l'agent;Type agent;Catégorie agent;Grade agent;% temps de travail\n"
                +buildCsvLine(appartientCd)+"\n"
                +buildCsvLine(appartientDar)+"\n"
                +buildCsvLine(appartientDu)+"\n"
                +buildCsvLine(appartientPc)+"\n";
        
        //Accès interdit
        this.mockMvc.perform(MockMvcRequestBuilders.get(RAPPORT_PATH+"/export"))
            .andExpect(MockMvcResultMatchers.status().isUnauthorized());
        
        //Test du tri
        this.mockMvc.perform(MockMvcRequestBuilders.get(RAPPORT_PATH+"/export")
                .param("orderField", "nomCtRpCt")
                .param("order", "ASC")
                .with(SecurityMockMvcRequestPostProcessors.user(admin)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(CSVResultMatcher.contains(expectedCsv));
        this.mockMvc.perform(MockMvcRequestBuilders.get(RAPPORT_PATH+"/export")
                .param("orderField", "nomCtRpCt")
                .param("order", "DESC")
                .with(SecurityMockMvcRequestPostProcessors.user(admin)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(CSVResultMatcher.sortedDesc(1));
        this.mockMvc.perform(MockMvcRequestBuilders.get(RAPPORT_PATH+"/export")
                .param("orderField", "matriculePersonneRpCt")
                .param("order", "ASC")
                .with(SecurityMockMvcRequestPostProcessors.user(admin)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(CSVResultMatcher.sortedAsc(4));
    }
    
    private String buildCsvLine(RapportParticipationAgtChampThematique data){
        StringBuilder sb = new StringBuilder();

        sb.append(prettyPrintCSV(data.getCodeCtRpCt()));
        sb.append(';');
        sb.append(prettyPrintCSV(data.getNomCtRpCt()));
        sb.append(';');
        sb.append(prettyPrintCSV(data.getSigleCtRpCt()));
        sb.append(';');
        sb.append(prettyPrintCSV(data.getCodiqueDepartementAgentRpCt()));
        sb.append(';');
        sb.append(prettyPrintCSV(data.getSigleDeptPiloteRpCt()));
        sb.append(';');
        sb.append(prettyPrintCSV(data.getMatriculePersonneRpCt()));
        sb.append(';');
        sb.append(prettyPrintCSV(data.getNomPersonneRpCt()));
        sb.append(';');
        sb.append(prettyPrintCSV(data.getPrenomPersonneRpCt()));
        sb.append(';');
        sb.append(prettyPrintCSV(data.getCodeUniteAgentRpCt()));
        sb.append(';');
        sb.append(prettyPrintCSV(data.getTypePersonneRpCt()));
        sb.append(';');
        sb.append(prettyPrintCSV(data.getCategoriePersonneRpCt()));
        sb.append(';');
        sb.append(prettyPrintCSV(data.getCodeGradePersonneRpCt()));
        sb.append(';');
        sb.append(prettyPrintCSV(data.getPourcentageTempsTravailPersonneRpCt()));
        
        return sb.toString();
    }
    
    private String prettyPrintCSV(Object obj){
        if(obj == null){
            return "";
        } else {
            return obj.toString();
        }
    }
    
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public void setRapportParticipationAgtChampThematiqueService(
            IRapportParticipationAgtChampThematiqueService rapportParticipationAgtChampThematiqueService) {
        this.rapportParticipationAgtChampThematiqueService = rapportParticipationAgtChampThematiqueService;
    }
    
    
}
