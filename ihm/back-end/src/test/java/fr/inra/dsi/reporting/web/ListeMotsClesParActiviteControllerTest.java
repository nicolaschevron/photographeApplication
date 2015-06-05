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

import fr.inra.dsi.reporting.dataservice.IRapportMotsClesParActiviteService;
import fr.inra.dsi.reporting.model.RapportMotsClesParActivite;
import fr.inra.dsi.reporting.resultmatcher.JSONValueResultMatcher;
import fr.inra.dsi.reporting.security.INRAUser;
import fr.inra.dsi.reporting.util.TestUtil;
import fr.inra.dsi.reporting.util.WebConstantUtil;
import fr.inra.dsi.reporting.ws.mock.AuthenticationWebServiceMock;

public class ListeMotsClesParActiviteControllerTest extends AbstractControllerTest {
	
    @Autowired
    private IRapportMotsClesParActiviteService rapportMotsClesParActiviteService;
    
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

    private final RapportMotsClesParActivite appartientDu = new RapportMotsClesParActivite();
    private final RapportMotsClesParActivite appartientPasDu = new RapportMotsClesParActivite();
    
    private final RapportMotsClesParActivite appartientDar = new RapportMotsClesParActivite();
    private final RapportMotsClesParActivite appartientPasDar = new RapportMotsClesParActivite();
    
    private final RapportMotsClesParActivite appartientCd = new RapportMotsClesParActivite();
    private final RapportMotsClesParActivite appartientPasCd = new RapportMotsClesParActivite();
    
    private final RapportMotsClesParActivite appartientPc = new RapportMotsClesParActivite();
    private final RapportMotsClesParActivite appartientPasPc = new RapportMotsClesParActivite();

    private final RapportMotsClesParActivite acr = new RapportMotsClesParActivite();
    private final RapportMotsClesParActivite acs = new RapportMotsClesParActivite();
    private final RapportMotsClesParActivite ace = new RapportMotsClesParActivite();
    private final RapportMotsClesParActivite aca = new RapportMotsClesParActivite();
    
    @Before
    public void initTest() throws Exception{
        user = (INRAUser)userDetailsService.loadUserByUsername(AuthenticationWebServiceMock.MOCK_LOGIN);
        admin = (INRAUser)userDetailsService.loadUserByUsername(AuthenticationWebServiceMock.MOCK_LOGIN_ADMIN);
        
        du = (INRAUser)userDetailsService.loadUserByUsername(AuthenticationWebServiceMock.MOCK_LOGIN_DU);
        org.springframework.util.Assert.notEmpty(du.getCodesUnite(), "Le DU utilisé pour le test n'est DU d'aucune unité");
        appartientDu.setCodeUnite(du.getCodesUnite().get(0));
        String codeUniteAppartientPasDu = TestUtil.getNextCodeString();
        Assert.assertFalse(du.getCodesUnite().contains(codeUniteAppartientPasDu));
        appartientPasDu.setCodeUnite(codeUniteAppartientPasDu);

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
        acr.setLibelleAc("aaaaaaaaa");
        acr.setListeMotsCles("xxxxxxxx");
        //acr.setIdentifiant(... setté par la BDD ...);
        acs.setAbreviationTypeAc("ACS");
        acs.setLibelleAc("bbbbbbbb");
        acs.setListeMotsCles("yyyyyyyy");
        //acs.setIdentifiant(... setté par la BDD ...);
        ace.setAbreviationTypeAc("ACE");
        ace.setLibelleAc("cccccccc");
        ace.setListeMotsCles("yyyyyyyy");
        //ace.setIdentifiant(... setté par la BDD ...);
        aca.setAbreviationTypeAc("ACA");
        aca.setLibelleAc("dddddddd");
        aca.setListeMotsCles("zzzzzzzz");
        //aca.setIdentifiant(... setté par la BDD ...);
        
        cnue = (INRAUser)userDetailsService.loadUserByUsername(AuthenticationWebServiceMock.MOCK_LOGIN_CNUE);
        cnoc = (INRAUser)userDetailsService.loadUserByUsername(AuthenticationWebServiceMock.MOCK_LOGIN_CNOC);

        rapportMotsClesParActiviteService.saveOrUpdate(appartientDu);
        rapportMotsClesParActiviteService.saveOrUpdate(appartientPasDu);
        rapportMotsClesParActiviteService.saveOrUpdate(appartientCd);
        rapportMotsClesParActiviteService.saveOrUpdate(appartientPasCd);
        rapportMotsClesParActiviteService.saveOrUpdate(appartientDar);
        rapportMotsClesParActiviteService.saveOrUpdate(appartientPasDar);
        rapportMotsClesParActiviteService.saveOrUpdate(appartientPc);
        rapportMotsClesParActiviteService.saveOrUpdate(appartientPasPc);
        rapportMotsClesParActiviteService.saveOrUpdate(acr);
        rapportMotsClesParActiviteService.saveOrUpdate(acs);
        rapportMotsClesParActiviteService.saveOrUpdate(ace);
        rapportMotsClesParActiviteService.saveOrUpdate(aca);

    }
    
    @Test
    public void testDU() throws Exception{
        final INRAUser finalDu = du;
        this.mockMvc.perform(MockMvcRequestBuilders.get(WebConstantUtil.RAPPORT_MOTS_CLES_PAR_ACTIVITE_PATH+"/all")
                .with(SecurityMockMvcRequestPostProcessors.user(du)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(new JSONValueResultMatcher("lignesRapport[*].codeUnite") {
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
        this.mockMvc.perform(MockMvcRequestBuilders.get(WebConstantUtil.RAPPORT_MOTS_CLES_PAR_ACTIVITE_PATH+"/all")
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
        this.mockMvc.perform(MockMvcRequestBuilders.get(WebConstantUtil.RAPPORT_MOTS_CLES_PAR_ACTIVITE_PATH+"/all")
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
        this.mockMvc.perform(MockMvcRequestBuilders.get(WebConstantUtil.RAPPORT_MOTS_CLES_PAR_ACTIVITE_PATH+"/all")
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
        this.mockMvc.perform(MockMvcRequestBuilders.get(WebConstantUtil.RAPPORT_MOTS_CLES_PAR_ACTIVITE_PATH+"/all")
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
        this.mockMvc.perform(MockMvcRequestBuilders.get(WebConstantUtil.RAPPORT_MOTS_CLES_PAR_ACTIVITE_PATH+"/all")
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
        final List<Integer> listeCodeMc = new ArrayList<Integer>();
        listeCodeMc.add(appartientDu.getIdentifiant());
        listeCodeMc.add(appartientPasDu.getIdentifiant());
        listeCodeMc.add(appartientCd.getIdentifiant());
        listeCodeMc.add(appartientPasCd.getIdentifiant());
        listeCodeMc.add(appartientDar.getIdentifiant());
        listeCodeMc.add(appartientPasDar.getIdentifiant());
        listeCodeMc.add(ace.getIdentifiant());
        listeCodeMc.add(aca.getIdentifiant());
        listeCodeMc.add(acs.getIdentifiant());
        listeCodeMc.add(acr.getIdentifiant());
        
        this.mockMvc.perform(MockMvcRequestBuilders.get(WebConstantUtil.RAPPORT_MOTS_CLES_PAR_ACTIVITE_PATH+"/all")
                .with(SecurityMockMvcRequestPostProcessors.user(admin)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(new JSONValueResultMatcher("lignesRapport") {
                //On retrouve toutes les AC
                @Override
                public void assertValue(JsonValue actualJsonValue) {
                    int found = 0;
                    for(Integer code : listeCodeMc) {
                        for(JsonValue item : (JsonArray)actualJsonValue){
                            if(code == ((JsonObject)item).getInt("identifiant")){
                                ++found;
                            }
                        }
                    }
                    Assert.assertEquals(listeCodeMc.size(), found);
                }
            });
    }
    
    @Test
    public void testAccess() throws Exception{
        //Accès interdit
        this.mockMvc.perform(MockMvcRequestBuilders.get(WebConstantUtil.RAPPORT_MOTS_CLES_PAR_ACTIVITE_PATH+"/all"))
            .andExpect(MockMvcResultMatchers.status().isUnauthorized());
        this.mockMvc.perform(MockMvcRequestBuilders.get(WebConstantUtil.RAPPORT_MOTS_CLES_PAR_ACTIVITE_PATH+"/all")
                .with(SecurityMockMvcRequestPostProcessors.user(user)))
            .andExpect(MockMvcResultMatchers.status().isForbidden());
    }
    
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    
    public void setRapportMotsClesService(
    		IRapportMotsClesParActiviteService rapportMotsClesParActiviteService) {
        this.rapportMotsClesParActiviteService = rapportMotsClesParActiviteService;
    }
}
