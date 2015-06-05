package fr.inra.dsi.reporting.web;

import java.util.ArrayList;
import java.util.List;

import javax.json.JsonArray;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonString;
import javax.json.JsonValue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import fr.inra.dsi.reporting.dataservice.IRapportParticipationAgentSurActiviteService;
import fr.inra.dsi.reporting.model.RapportParticipationAgentSurActivite;
import fr.inra.dsi.reporting.resultmatcher.JSONValueResultMatcher;
import fr.inra.dsi.reporting.security.INRAUser;
import fr.inra.dsi.reporting.util.TestUtil;
import fr.inra.dsi.reporting.util.WebConstantUtil;
import fr.inra.dsi.reporting.ws.mock.AuthenticationWebServiceMock;

public class ListeRapportParticipationAgentSurActiviteControllerTest extends
        AbstractControllerTest {

    @Autowired
    private IRapportParticipationAgentSurActiviteService rapportParticipationAgentService;
    
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

    private final RapportParticipationAgentSurActivite appartientDu = new RapportParticipationAgentSurActivite();
    private final RapportParticipationAgentSurActivite appartientPasDu = new RapportParticipationAgentSurActivite();
    
    private final RapportParticipationAgentSurActivite appartientDar = new RapportParticipationAgentSurActivite();
    private final RapportParticipationAgentSurActivite appartientPasDar = new RapportParticipationAgentSurActivite();
    
    private final RapportParticipationAgentSurActivite appartientCd = new RapportParticipationAgentSurActivite();
    private final RapportParticipationAgentSurActivite appartientPasCd = new RapportParticipationAgentSurActivite();
    
    private final RapportParticipationAgentSurActivite appartientPc = new RapportParticipationAgentSurActivite();
    private final RapportParticipationAgentSurActivite appartientPasPc = new RapportParticipationAgentSurActivite();

    private final RapportParticipationAgentSurActivite acr = new RapportParticipationAgentSurActivite();
    private final RapportParticipationAgentSurActivite acs = new RapportParticipationAgentSurActivite();
    private final RapportParticipationAgentSurActivite ace = new RapportParticipationAgentSurActivite();
    private final RapportParticipationAgentSurActivite aca = new RapportParticipationAgentSurActivite();
    
    @Before
    public void initTest() throws Exception{
        user = (INRAUser)userDetailsService.loadUserByUsername(AuthenticationWebServiceMock.MOCK_LOGIN);
        admin = (INRAUser)userDetailsService.loadUserByUsername(AuthenticationWebServiceMock.MOCK_LOGIN_ADMIN);
        
        du = (INRAUser)userDetailsService.loadUserByUsername(AuthenticationWebServiceMock.MOCK_LOGIN_DU);
        org.springframework.util.Assert.notEmpty(du.getCodesUnite(), "Le DU utilisé pour le test n'est DU d'aucune unité");
        appartientDu.setCodeUniteLeader(du.getCodesUnite().get(0));
        //appartientDu.setIdentifiant(... setté par la BDD ...);
        String codeUniteAppartientPasDu = TestUtil.getNextCodeString();
        Assert.assertFalse(du.getCodesUnite().contains(codeUniteAppartientPasDu));
        appartientPasDu.setCodeUniteLeader(codeUniteAppartientPasDu);
        //appartientPasDu.setIdentifiant(... setté par la BDD ...);
        
        dar = (INRAUser)userDetailsService.loadUserByUsername(AuthenticationWebServiceMock.MOCK_LOGIN_DAR);
        org.springframework.util.Assert.notEmpty(dar.getCodesDirectionAppui(), "Le DAR utilisé pour le test n'est DAR d'aucune direction d'appui");
        appartientDar.setCodiqueDepartement(dar.getCodesDirectionAppui().get(0));
        //appartientDar.setIdentifiant(... setté par la BDD ...);
        String codeDepartementAppartientPasDar = TestUtil.getNextCodeString();
        Assert.assertFalse(dar.getCodesDirectionAppui().contains(codeDepartementAppartientPasDar));
        appartientPasDar.setCodiqueDepartement(codeDepartementAppartientPasDar);
        //appartientPasDar.setIdentifiant(... setté par la BDD ...);
        
        cd = (INRAUser)userDetailsService.loadUserByUsername(AuthenticationWebServiceMock.MOCK_LOGIN_CD);
        org.springframework.util.Assert.notEmpty(cd.getCodesDepartement(), "Le CD utilisé pour le test n'est CD d'aucun département");
        appartientCd.setCodiqueDepartement(cd.getCodesDepartement().get(0));
        //appartientCd.setIdentifiant(... setté par la BDD ...);
        String codeDepartementAppartientPasCd = TestUtil.getNextCodeString();
        Assert.assertFalse(cd.getCodesDepartement().contains(codeDepartementAppartientPasCd));
        appartientPasCd.setCodiqueDepartement(codeDepartementAppartientPasCd);
        //appartientPasCd.setIdentifiant(... setté par la BDD ...);
        
        pc = (INRAUser)userDetailsService.loadUserByUsername(AuthenticationWebServiceMock.MOCK_LOGIN_PC);
        org.springframework.util.Assert.notEmpty(pc.getCodesCentre(), "Le PC utilisé pour le test n'est PC d'aucun centre");
        appartientPc.setCodeCentre(pc.getCodesCentre().get(0));
        //appartientPc.setIdentifiant(... setté par la BDD ...);
        String codeDepartementAppartientPasPc = TestUtil.getNextCodeString();
        Assert.assertFalse(cd.getCodesDepartement().contains(codeDepartementAppartientPasPc));
        appartientPasPc.setCodeCentre(codeDepartementAppartientPasPc);
        //appartientPasPc.setIdentifiant(... setté par la BDD ...);

        acr.setAbreviationTypeAc("ACR");
        //acr.setIdentifiant(... setté par la BDD ...);
        acs.setAbreviationTypeAc("ACS");
        //acs.setIdentifiant(... setté par la BDD ...);
        ace.setAbreviationTypeAc("ACE");
        //ace.setIdentifiant(... setté par la BDD ...);
        aca.setAbreviationTypeAc("ACA");
        //aca.setIdentifiant(... setté par la BDD ...);
        
        cnue = (INRAUser)userDetailsService.loadUserByUsername(AuthenticationWebServiceMock.MOCK_LOGIN_CNUE);
        cnoc = (INRAUser)userDetailsService.loadUserByUsername(AuthenticationWebServiceMock.MOCK_LOGIN_CNOC);

        rapportParticipationAgentService.saveOrUpdate(appartientDu);
        rapportParticipationAgentService.saveOrUpdate(appartientPasDu);
        rapportParticipationAgentService.saveOrUpdate(appartientCd);
        rapportParticipationAgentService.saveOrUpdate(appartientPasCd);
        rapportParticipationAgentService.saveOrUpdate(appartientDar);
        rapportParticipationAgentService.saveOrUpdate(appartientPasDar);
        rapportParticipationAgentService.saveOrUpdate(appartientPc);
        rapportParticipationAgentService.saveOrUpdate(appartientPasPc);
        rapportParticipationAgentService.saveOrUpdate(acr);
        rapportParticipationAgentService.saveOrUpdate(acs);
        rapportParticipationAgentService.saveOrUpdate(ace);
        rapportParticipationAgentService.saveOrUpdate(aca);
    }

    @Test
    public void testDU() throws Exception{
        final INRAUser finalDu = du;
        this.mockMvc.perform(MockMvcRequestBuilders.get(WebConstantUtil.RAPPORT_LISTE_PARTICIPATION_AGENT_PATH+"/all")
                .with(SecurityMockMvcRequestPostProcessors.user(du)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(new JSONValueResultMatcher("lignesRapport[*].codeUniteLeader") {
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
        this.mockMvc.perform(MockMvcRequestBuilders.get(WebConstantUtil.RAPPORT_LISTE_PARTICIPATION_AGENT_PATH+"/all")
                .with(SecurityMockMvcRequestPostProcessors.user(cd)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(new JSONValueResultMatcher("lignesRapport[*].codiqueDepartement") {
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
        this.mockMvc.perform(MockMvcRequestBuilders.get(WebConstantUtil.RAPPORT_LISTE_PARTICIPATION_AGENT_PATH+"/all")
                .with(SecurityMockMvcRequestPostProcessors.user(dar)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(new JSONValueResultMatcher("lignesRapport[*].codiqueDepartement") {
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
        this.mockMvc.perform(MockMvcRequestBuilders.get(WebConstantUtil.RAPPORT_LISTE_PARTICIPATION_AGENT_PATH+"/all")
                .with(SecurityMockMvcRequestPostProcessors.user(pc)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(new JSONValueResultMatcher("lignesRapport[*].codeCentre") {
                //Tous le CT affiché sont affectés à un centre du PC
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
        this.mockMvc.perform(MockMvcRequestBuilders.get(WebConstantUtil.RAPPORT_LISTE_PARTICIPATION_AGENT_PATH+"/all")
                .with(SecurityMockMvcRequestPostProcessors.user(cnoc)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(new JSONValueResultMatcher("lignesRapport[*].abreviationTypeAc") {
                //Tous le CT affiché sont affectés à une ACS
                @Override
                public void assertValue(JsonValue actualJsonValue) {
                    Assert.assertEquals("ACS", ((JsonString)actualJsonValue).getString());
                }
            })
            .andExpect(new JSONValueResultMatcher("lignesRapport[?].identifiant") {
                //La ligne acs est dans le résultat
                @Override
                public void assertValue(JsonValue actualJsonValue) {
                    Assert.assertEquals(acs.getIdentifiant(), ((JsonNumber)actualJsonValue).intValue());
                }
            });
    }

    @Test
    public void testCNUE() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders.get(WebConstantUtil.RAPPORT_LISTE_PARTICIPATION_AGENT_PATH+"/all")
                .with(SecurityMockMvcRequestPostProcessors.user(cnue)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(new JSONValueResultMatcher("lignesRapport[*].abreviationTypeAc") {
                //Tous le CT affiché sont affectés à une ACE
                @Override
                public void assertValue(JsonValue actualJsonValue) {
                    Assert.assertEquals("ACE", ((JsonString)actualJsonValue).getString());
                }
            })
            .andExpect(new JSONValueResultMatcher("lignesRapport[?].identifiant") {
                //La ligne ace est dans le résultat
                @Override
                public void assertValue(JsonValue actualJsonValue) {
                    Assert.assertEquals(ace.getIdentifiant(), ((JsonNumber)actualJsonValue).intValue());
                }
            });
    }

    @Test
    public void testADMIN() throws Exception{
        final List<Integer> listeCodeCt = new ArrayList<Integer>();
        listeCodeCt.add(appartientDu.getIdentifiant());
        listeCodeCt.add(appartientPasDu.getIdentifiant());
        listeCodeCt.add(appartientCd.getIdentifiant());
        listeCodeCt.add(appartientPasCd.getIdentifiant());
        listeCodeCt.add(appartientDar.getIdentifiant());
        listeCodeCt.add(appartientPasDar.getIdentifiant());
        listeCodeCt.add(ace.getIdentifiant());
        listeCodeCt.add(aca.getIdentifiant());
        listeCodeCt.add(acs.getIdentifiant());
        listeCodeCt.add(acr.getIdentifiant());
        
        this.mockMvc.perform(MockMvcRequestBuilders.get(WebConstantUtil.RAPPORT_LISTE_PARTICIPATION_AGENT_PATH+"/all")
                .with(SecurityMockMvcRequestPostProcessors.user(admin)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(new JSONValueResultMatcher("lignesRapport") {
                //On retrouve toutes les AC
                @Override
                public void assertValue(JsonValue actualJsonValue) {
                    int found = 0;
                    for(Integer code : listeCodeCt) {
                        for(JsonValue item : (JsonArray)actualJsonValue){
                            if(code == ((JsonObject)item).getInt("identifiant")){
                                ++found;
                            }
                        }
                    }
                    Assert.assertEquals(listeCodeCt.size(), found);
                }
            });
    }
    
    
    
    @Test
    public void testAccess() throws Exception{
        //Accès interdit
        this.mockMvc.perform(MockMvcRequestBuilders.get(WebConstantUtil.RAPPORT_LISTE_PARTICIPATION_AGENT_PATH+"/all"))
            .andExpect(MockMvcResultMatchers.status().isUnauthorized());
        this.mockMvc.perform(MockMvcRequestBuilders.get(WebConstantUtil.RAPPORT_LISTE_PARTICIPATION_AGENT_PATH+"/all")
                .with(SecurityMockMvcRequestPostProcessors.user(user)))
            .andExpect(MockMvcResultMatchers.status().isForbidden());
    }
    
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public void setRapportParticipationAgentService(
            IRapportParticipationAgentSurActiviteService rapportParticipationAgentService) {
        this.rapportParticipationAgentService = rapportParticipationAgentService;
    }

}
