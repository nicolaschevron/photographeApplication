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

import fr.inra.dsi.reporting.dataservice.IRapportChampThematiqueParActiviteService;
import fr.inra.dsi.reporting.model.RapportChampThematiqueParActivite;
import fr.inra.dsi.reporting.resultmatcher.JSONValueResultMatcher;
import fr.inra.dsi.reporting.security.INRAUser;
import fr.inra.dsi.reporting.util.TestUtil;
import fr.inra.dsi.reporting.util.WebConstantUtil;
import fr.inra.dsi.reporting.ws.mock.AuthenticationWebServiceMock;

public class ListeChampThematiqueParActiviteControllerTest extends
        AbstractControllerTest {
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
    private IRapportChampThematiqueParActiviteService rapportCTParAcService;

    //Utilisateurs mock
    private INRAUser user;
    private INRAUser admin;
    private INRAUser du;
    private INRAUser dar;
    private INRAUser cd;
    private INRAUser pc;
    private INRAUser cnue;
    private INRAUser cnoc;

    private final RapportChampThematiqueParActivite appartientDu = new RapportChampThematiqueParActivite();
    private final RapportChampThematiqueParActivite appartientPasDu = new RapportChampThematiqueParActivite();
    
    private final RapportChampThematiqueParActivite appartientDar = new RapportChampThematiqueParActivite();
    private final RapportChampThematiqueParActivite appartientPasDar = new RapportChampThematiqueParActivite();
    
    private final RapportChampThematiqueParActivite appartientCd = new RapportChampThematiqueParActivite();
    private final RapportChampThematiqueParActivite appartientPasCd = new RapportChampThematiqueParActivite();
    
    private final RapportChampThematiqueParActivite appartientPc = new RapportChampThematiqueParActivite();
    private final RapportChampThematiqueParActivite appartientPasPc = new RapportChampThematiqueParActivite();

    private final RapportChampThematiqueParActivite acr = new RapportChampThematiqueParActivite();
    private final RapportChampThematiqueParActivite acs = new RapportChampThematiqueParActivite();
    private final RapportChampThematiqueParActivite ace = new RapportChampThematiqueParActivite();
    private final RapportChampThematiqueParActivite aca = new RapportChampThematiqueParActivite();
    
    @Before
    public void initTest() throws Exception{
        user = (INRAUser)userDetailsService.loadUserByUsername(AuthenticationWebServiceMock.MOCK_LOGIN);
        admin = (INRAUser)userDetailsService.loadUserByUsername(AuthenticationWebServiceMock.MOCK_LOGIN_ADMIN);
        
        du = (INRAUser)userDetailsService.loadUserByUsername(AuthenticationWebServiceMock.MOCK_LOGIN_DU);
        org.springframework.util.Assert.notEmpty(du.getCodesUnite(), "Le DU utilisé pour le test n'est DU d'aucune unité");
        appartientDu.setCodeUnite(du.getCodesUnite().get(0));
        appartientDu.setCodeCt(TestUtil.getNextCodeInt());
        String codeUniteAppartientPasDu = TestUtil.getNextCodeString();
        Assert.assertFalse(du.getCodesUnite().contains(codeUniteAppartientPasDu));
        appartientPasDu.setCodeUnite(codeUniteAppartientPasDu);
        appartientPasDu.setCodeCt(TestUtil.getNextCodeInt());
        
        dar = (INRAUser)userDetailsService.loadUserByUsername(AuthenticationWebServiceMock.MOCK_LOGIN_DAR);
        org.springframework.util.Assert.notEmpty(dar.getCodesDirectionAppui(), "Le DAR utilisé pour le test n'est DAR d'aucune direction d'appui");
        appartientDar.setCodiqueDepartement(dar.getCodesDirectionAppui().get(0));
        appartientDar.setCodeCt(TestUtil.getNextCodeInt());
        String codeDepartementAppartientPasDar = TestUtil.getNextCodeString();
        Assert.assertFalse(dar.getCodesDirectionAppui().contains(codeDepartementAppartientPasDar));
        appartientPasDar.setCodiqueDepartement(codeDepartementAppartientPasDar);
        appartientPasDar.setCodeCt(TestUtil.getNextCodeInt());
        
        cd = (INRAUser)userDetailsService.loadUserByUsername(AuthenticationWebServiceMock.MOCK_LOGIN_CD);
        org.springframework.util.Assert.notEmpty(cd.getCodesDepartement(), "Le CD utilisé pour le test n'est CD d'aucun département");
        appartientCd.setCodiqueDepartement(cd.getCodesDepartement().get(0));
        appartientCd.setCodeCt(TestUtil.getNextCodeInt());
        String codeDepartementAppartientPasCd = TestUtil.getNextCodeString();
        Assert.assertFalse(cd.getCodesDepartement().contains(codeDepartementAppartientPasCd));
        appartientPasCd.setCodiqueDepartement(codeDepartementAppartientPasCd);
        appartientPasCd.setCodeCt(TestUtil.getNextCodeInt());
        
        pc = (INRAUser)userDetailsService.loadUserByUsername(AuthenticationWebServiceMock.MOCK_LOGIN_PC);
        org.springframework.util.Assert.notEmpty(pc.getCodesCentre(), "Le PC utilisé pour le test n'est PC d'aucun centre");
        appartientPc.setCodeCentre(pc.getCodesCentre().get(0));
        appartientPc.setCodeCt(TestUtil.getNextCodeInt());
        String codeDepartementAppartientPasPc = TestUtil.getNextCodeString();
        Assert.assertFalse(cd.getCodesDepartement().contains(codeDepartementAppartientPasPc));
        appartientPasPc.setCodeCentre(codeDepartementAppartientPasPc);
        appartientPasPc.setCodeCt(TestUtil.getNextCodeInt());

        acr.setAbreviationTypeAc("ACR");
        acr.setCodeCt(TestUtil.getNextCodeInt());
        acs.setAbreviationTypeAc("ACS");
        acs.setCodeCt(TestUtil.getNextCodeInt());
        ace.setAbreviationTypeAc("ACE");
        ace.setCodeCt(TestUtil.getNextCodeInt());
        aca.setAbreviationTypeAc("ACA");
        aca.setCodeCt(TestUtil.getNextCodeInt());
        
        cnue = (INRAUser)userDetailsService.loadUserByUsername(AuthenticationWebServiceMock.MOCK_LOGIN_CNUE);
        cnoc = (INRAUser)userDetailsService.loadUserByUsername(AuthenticationWebServiceMock.MOCK_LOGIN_CNOC);

        rapportCTParAcService.saveOrUpdate(appartientDu);
        rapportCTParAcService.saveOrUpdate(appartientPasDu);
        rapportCTParAcService.saveOrUpdate(appartientCd);
        rapportCTParAcService.saveOrUpdate(appartientPasCd);
        rapportCTParAcService.saveOrUpdate(appartientDar);
        rapportCTParAcService.saveOrUpdate(appartientPasDar);
        rapportCTParAcService.saveOrUpdate(appartientPc);
        rapportCTParAcService.saveOrUpdate(appartientPasPc);
        rapportCTParAcService.saveOrUpdate(acr);
        rapportCTParAcService.saveOrUpdate(acs);
        rapportCTParAcService.saveOrUpdate(ace);
        rapportCTParAcService.saveOrUpdate(aca);
    }

    @Test
    public void testDU() throws Exception{
        final INRAUser finalDu = du;
        this.mockMvc.perform(MockMvcRequestBuilders.get(WebConstantUtil.RAPPORT_LISTE_CT_PARTICIPATION_UNITE_PATH+"/all")
                .with(SecurityMockMvcRequestPostProcessors.user(du)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(new JSONValueResultMatcher("lignesRapport[*].codeUnite") {
                //Tous le CT affiché sont affectés à une unité du DU
                @Override
                public void assertValue(JsonValue actualJsonValue) {
                    Assert.assertTrue(finalDu.getCodesUnite().contains(((JsonString)actualJsonValue).getString()));
                }
            })
            .andExpect(new JSONValueResultMatcher("lignesRapport") {
                //La ligne appartientDu est dans le résultat
                @Override
                public void assertValue(JsonValue actualJsonValue) {
                    boolean found = false;
                    for(JsonValue item : ((JsonArray)actualJsonValue)){
                        if(appartientDu.getCodeCt() == ((JsonObject)item).getInt("codeCt")){
                            found = true;
                            break;
                        }
                    }
                    Assert.assertTrue(found);
                }
            })
            .andExpect(new JSONValueResultMatcher("lignesRapport[*].codeCt") {
                //La ligne appartientPasDu n'est pas dans le résultat
                @Override
                public void assertValue(JsonValue actualJsonValue) {
                    Assert.assertFalse(appartientPasDu.getCodeCt() == ((JsonNumber)actualJsonValue).intValue());
                }
            });
    }

    @Test
    public void testCD() throws Exception{
        final INRAUser finalCd = cd;
        this.mockMvc.perform(MockMvcRequestBuilders.get(WebConstantUtil.RAPPORT_LISTE_CT_PARTICIPATION_UNITE_PATH+"/all")
                .with(SecurityMockMvcRequestPostProcessors.user(cd)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(new JSONValueResultMatcher("lignesRapport[*].codiqueDepartement") {
                //Tous le CT affiché sont affectés à département du CD
                @Override
                public void assertValue(JsonValue actualJsonValue) {
                    Assert.assertTrue(finalCd.getCodesDepartement().contains(((JsonString)actualJsonValue).getString()));
                }
            })
            .andExpect(new JSONValueResultMatcher("lignesRapport") {
                //La ligne appartientCd est dans le résultat
                @Override
                public void assertValue(JsonValue actualJsonValue) {
                    boolean found = false;
                    for(JsonValue item : ((JsonArray)actualJsonValue)){
                        if(appartientCd.getCodeCt() == ((JsonObject)item).getInt("codeCt")){
                            found = true;
                            break;
                        }
                    }
                    Assert.assertTrue(found);
                }
            })
            .andExpect(new JSONValueResultMatcher("lignesRapport[*].codeCt") {
                //La ligne appartientPasCd n'est pas dans le résultat
                @Override
                public void assertValue(JsonValue actualJsonValue) {
                    Assert.assertFalse(appartientPasCd.getCodeCt() == ((JsonNumber)actualJsonValue).intValue());
                }
            });
    }

    @Test
    public void testDAR() throws Exception{
        final INRAUser finalDar = dar;
        this.mockMvc.perform(MockMvcRequestBuilders.get(WebConstantUtil.RAPPORT_LISTE_CT_PARTICIPATION_UNITE_PATH+"/all")
                .with(SecurityMockMvcRequestPostProcessors.user(dar)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(new JSONValueResultMatcher("lignesRapport[*].codiqueDepartement") {
                //Tous le CT affiché sont affectés à une direction du DAR
                @Override
                public void assertValue(JsonValue actualJsonValue) {
                    Assert.assertTrue(finalDar.getCodesDirectionAppui().contains(((JsonString)actualJsonValue).getString()));
                }
            })
            .andExpect(new JSONValueResultMatcher("lignesRapport") {
                //La ligne appartientDar est dans le résultat
                @Override
                public void assertValue(JsonValue actualJsonValue) {
                    boolean found = false;
                    for(JsonValue item : ((JsonArray)actualJsonValue)){
                        if(appartientDar.getCodeCt() == ((JsonObject)item).getInt("codeCt")){
                            found = true;
                            break;
                        }
                    }
                    Assert.assertTrue(found);
                }
            })
            .andExpect(new JSONValueResultMatcher("lignesRapport[*].codeCt") {
                //La ligne appartientPasDar n'est pas dans le résultat
                @Override
                public void assertValue(JsonValue actualJsonValue) {
                    Assert.assertFalse(appartientPasDar.getCodeCt() == ((JsonNumber)actualJsonValue).intValue());
                }
            });
    }

    @Test
    public void testPC() throws Exception{
        final INRAUser finalPc = pc;
        this.mockMvc.perform(MockMvcRequestBuilders.get(WebConstantUtil.RAPPORT_LISTE_CT_PARTICIPATION_UNITE_PATH+"/all")
                .with(SecurityMockMvcRequestPostProcessors.user(pc)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(new JSONValueResultMatcher("lignesRapport[*].codeCentre") {
                //Tous le CT affiché sont affectés à un centre du PC
                @Override
                public void assertValue(JsonValue actualJsonValue) {
                    Assert.assertTrue(finalPc.getCodesCentre().contains(((JsonString)actualJsonValue).getString()));
                }
            })
            .andExpect(new JSONValueResultMatcher("lignesRapport[?].codeCt") {
                //La ligne appartientPc est dans le résultat
                @Override
                public void assertValue(JsonValue actualJsonValue) {
                    Assert.assertTrue((appartientPc.getCodeCt() == ((JsonNumber)actualJsonValue).intValue()));
                }
            })
            .andExpect(new JSONValueResultMatcher("lignesRapport[*].codeCt") {
                //La ligne appartientPasDar n'est pas dans le résultat
                @Override
                public void assertValue(JsonValue actualJsonValue) {
                    Assert.assertFalse(appartientPasPc.getCodeCt() == ((JsonNumber)actualJsonValue).intValue());
                }
            });
    }

    @Test
    public void testCNOC() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders.get(WebConstantUtil.RAPPORT_LISTE_CT_PARTICIPATION_UNITE_PATH+"/all")
                .with(SecurityMockMvcRequestPostProcessors.user(cnoc)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(new JSONValueResultMatcher("lignesRapport[*].abreviationTypeAc") {
                //Tous le CT affiché sont affectés à une ACS
                @Override
                public void assertValue(JsonValue actualJsonValue) {
                    Assert.assertEquals("ACS", ((JsonString)actualJsonValue).getString());
                }
            })
            .andExpect(new JSONValueResultMatcher("lignesRapport") {
                //La ligne acs est dans le résultat
                @Override
                public void assertValue(JsonValue actualJsonValue) {
                    boolean found = false;
                    for(JsonValue item : ((JsonArray)actualJsonValue)){
                        if(acs.getCodeCt() == ((JsonObject)item).getInt("codeCt")){
                            found = true;
                            break;
                        }
                    }
                    Assert.assertTrue(found);
                }
            });
    }

    @Test
    public void testCNUE() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders.get(WebConstantUtil.RAPPORT_LISTE_CT_PARTICIPATION_UNITE_PATH+"/all")
                .with(SecurityMockMvcRequestPostProcessors.user(cnue)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(new JSONValueResultMatcher("lignesRapport[*].abreviationTypeAc") {
                //Tous le CT affiché sont affectés à une ACE
                @Override
                public void assertValue(JsonValue actualJsonValue) {
                    Assert.assertEquals("ACE", ((JsonString)actualJsonValue).getString());
                }
            })
            .andExpect(new JSONValueResultMatcher("lignesRapport") {
                //La ligne ace est dans le résultat
                @Override
                public void assertValue(JsonValue actualJsonValue) {
                    boolean found = false;
                    for(JsonValue item : ((JsonArray)actualJsonValue)){
                        if(ace.getCodeCt() == ((JsonObject)item).getInt("codeCt")){
                            found = true;
                            break;
                        }
                    }
                    Assert.assertTrue(found);
                }
            });
    }

    @Test
    public void testADMIN() throws Exception{
        final List<Integer> listeCodeCt = new ArrayList<Integer>();
        listeCodeCt.add(appartientDu.getCodeCt());
        listeCodeCt.add(appartientPasDu.getCodeCt());
        listeCodeCt.add(appartientCd.getCodeCt());
        listeCodeCt.add(appartientPasCd.getCodeCt());
        listeCodeCt.add(appartientDar.getCodeCt());
        listeCodeCt.add(appartientPasDar.getCodeCt());
        listeCodeCt.add(ace.getCodeCt());
        listeCodeCt.add(aca.getCodeCt());
        listeCodeCt.add(acs.getCodeCt());
        listeCodeCt.add(acr.getCodeCt());
        
        this.mockMvc.perform(MockMvcRequestBuilders.get(WebConstantUtil.RAPPORT_LISTE_CT_PARTICIPATION_UNITE_PATH+"/all")
                .with(SecurityMockMvcRequestPostProcessors.user(admin)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(new JSONValueResultMatcher("lignesRapport") {
                //On retrouve toutes les AC
                @Override
                public void assertValue(JsonValue actualJsonValue) {
                    int found = 0;
                    for(Integer code : listeCodeCt) {
                        for(JsonValue item : (JsonArray)actualJsonValue){
                            if(code == ((JsonObject)item).getInt("codeCt")){
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
        this.mockMvc.perform(MockMvcRequestBuilders.get(WebConstantUtil.RAPPORT_LISTE_CT_PARTICIPATION_UNITE_PATH+"/all"))
            .andExpect(MockMvcResultMatchers.status().isUnauthorized());
        this.mockMvc.perform(MockMvcRequestBuilders.get(WebConstantUtil.RAPPORT_LISTE_CT_PARTICIPATION_UNITE_PATH+"/all")
                .with(SecurityMockMvcRequestPostProcessors.user(user)))
            .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public void setRapportCTParAcService(
            IRapportChampThematiqueParActiviteService rapportCTParAcService) {
        this.rapportCTParAcService = rapportCTParAcService;
    }
    
    
}
