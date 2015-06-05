package fr.inra.dsi.reporting.web;

import java.util.ArrayList;
import java.util.List;

import javax.json.JsonObject;
import javax.json.JsonValue;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.StringUtils;

import fr.inra.dsi.reporting.data.impl.RapportDescriptionBuilderImpl;
import fr.inra.dsi.reporting.dataservice.IRapportDescriptionService;
import fr.inra.dsi.reporting.dto.RapportDto;
import fr.inra.dsi.reporting.model.RapportDescription;
import fr.inra.dsi.reporting.resultmatcher.JSONResultMatcher;
import fr.inra.dsi.reporting.resultmatcher.JSONValueResultMatcher;
import fr.inra.dsi.reporting.security.INRAUser;
import fr.inra.dsi.reporting.service.ISecurityManagerService;
import fr.inra.dsi.reporting.util.WebConstantUtil;
import fr.inra.dsi.reporting.ws.mock.AuthenticationWebServiceMock;

public class RapportDescriptionControllerTest extends AbstractControllerTest{
	
    @Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private IRapportDescriptionService rapportDescriptionService;
	
	@Autowired
	private transient MessageSource messageSource;
    
    @Autowired
    private ISecurityManagerService managerService;
	
    public RapportDescriptionControllerTest() {
		super();
	}

	/**
     * Test de la methode find findListeRapportDescription
     * @throws Exception
     */
    @Test
	public void testFindListeRapportDescription() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get(WebConstantUtil.RAPPORT_LISTE_RAP_DESC_FIND_PATH))
        .andExpect(MockMvcResultMatchers.status().isUnauthorized());

        StringBuilder expectedJson = new StringBuilder();
        expectedJson.append("\"colonnesRapport\":[{")
        .append("\"libelle\":\"").append(messageSource.getMessage("rapportdescription.label.colonne.titre", null , null)).append("\",\"cle\":\"titre")
        .append("\"},{\"libelle\":\"").append(messageSource.getMessage("rapportdescription.label.colonne.description", null , null)).append("\",\"cle\":\"description")
        .append("\"},{\"libelle\":\"").append(messageSource.getMessage("rapportdescription.label.colonne.restCall", null , null)).append("\",\"cle\":\"restCall")
        .append("\"}]}"); 
        
  		this.mockMvc.perform(MockMvcRequestBuilders.get(WebConstantUtil.RAPPORT_LISTE_RAP_DESC_FIND_PATH)
                .with(SecurityMockMvcRequestPostProcessors.user(userDetailsService.loadUserByUsername("test"))))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(new JSONResultMatcher("{\"lignesRapport\":[{\"id\":"
                    + RapportDescriptionBuilderImpl.RAPPORT_ACT_ID + ",\"titre\":\""
            		+ RapportDescriptionBuilderImpl.RAPPORT_ACT_TITRE
            		+"\",\"description\":\""
            		+ RapportDescriptionBuilderImpl.RAPPORT_ACT_DESC
            		+ "\",\"restCall\":\""
            		+ RapportDescriptionBuilderImpl.RAPPORT_ACT_REST_CALL
            		+ "\"}"
                    
            		+ ",{\"id\":"
                    + RapportDescriptionBuilderImpl.RAPPORT_CT_ID  + ",\"clazz\":\""
                    + RapportDescriptionBuilderImpl.RAPPORT_CT_CLAZZ
                    + "\",\"titre\":\""
                    + RapportDescriptionBuilderImpl.RAPPORT_CT_TITRE
                    +"\",\"description\":\""
                    + RapportDescriptionBuilderImpl.RAPPORT_CT_DESC
                    + "\",\"restCall\":\""
                    + RapportDescriptionBuilderImpl.RAPPORT_CT_REST_CALL
                    + "\"}],"
                    
            		+ expectedJson));
    }
	
    @Test
    public void testDisableRapport() throws Exception {
        //Utilisateurs mock
        INRAUser user = (INRAUser)userDetailsService.loadUserByUsername(AuthenticationWebServiceMock.MOCK_LOGIN);
        INRAUser admin = (INRAUser)userDetailsService.loadUserByUsername(AuthenticationWebServiceMock.MOCK_LOGIN_ADMIN);
        
        //Résultat attendu
        RapportDto expected = new RapportDto();
        List<RapportDescription> descList = new ArrayList<RapportDescription>();
        final RapportDescription targetDesc = new RapportDescription();//Le rapport avec le quel on fait le test
        targetDesc.setId(rapportDescriptionService.getAllRapportDescriptions().get(0).getId());//on prend le premier rapport en bdd
        descList.add(targetDesc);
        expected.setLignesRapport(descList);
        
        //Activation par l'admin
        this.mockMvc.perform(MockMvcRequestBuilders.get(WebConstantUtil.RAPPORT_DESC_UPDATE_AVAILABILITY_PATH+"/"+targetDesc.getId()+"/true")
                .with(SecurityMockMvcRequestPostProcessors.user(admin)))
            .andExpect(MockMvcResultMatchers.status().isOk());
        
        //On vérifie que l'user et l'admin ait accès
        targetDesc.setActif(true);//Le rapport doit être actif
        this.mockMvc.perform(MockMvcRequestBuilders.get(WebConstantUtil.RAPPORT_LISTE_RAP_DESC_FIND_PATH)
                .with(SecurityMockMvcRequestPostProcessors.user(admin)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(new JSONResultMatcher(expected));
        this.mockMvc.perform(MockMvcRequestBuilders.get(WebConstantUtil.RAPPORT_LISTE_RAP_DESC_FIND_PATH)
                .with(SecurityMockMvcRequestPostProcessors.user(user)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(new JSONResultMatcher(expected));
        
        //Désactivation par l'admin
        this.mockMvc.perform(MockMvcRequestBuilders.get(WebConstantUtil.RAPPORT_DESC_UPDATE_AVAILABILITY_PATH+"/"+targetDesc.getId()+"/false")
                .with(SecurityMockMvcRequestPostProcessors.user(admin)))
            .andExpect(MockMvcResultMatchers.status().isOk());
        
        //L'admin voit tous les rapports
        expected.setLignesRapport(rapportDescriptionService.getAllRapportDescriptions());
        this.mockMvc.perform(MockMvcRequestBuilders.get(WebConstantUtil.RAPPORT_LISTE_RAP_DESC_FIND_PATH)
                .with(SecurityMockMvcRequestPostProcessors.user(admin)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(new JSONResultMatcher(expected));
        //L'user ne voit le rapport targetDesc et les autres rapports actifs pour lesqels il n'y a pas de restriction sur les rôles
        List<RapportDescription> rapports = managerService.getAllRapportDescription(user);
        RapportDescription rapportToremove = null;
        for (RapportDescription rapportDescription : rapports) {//On enlève à la main le rapport du DU
            if(rapportDescription.getClazz().equals("RapportListeActivitesParUnite")){
                rapportToremove  = rapportDescription;
            }
        }
        if (rapportToremove != null){
            rapports.remove(rapportToremove);
        }
        expected.setLignesRapport(rapports);
        this.mockMvc.perform(MockMvcRequestBuilders.get(WebConstantUtil.RAPPORT_LISTE_RAP_DESC_FIND_PATH)
                .with(SecurityMockMvcRequestPostProcessors.user(user)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            //.andExpect(new JSONResultMatcher(expected))
            .andExpect(new JSONValueResultMatcher("lignesRapport[*]") {
                @Override
                public void assertValue(JsonValue actualJsonValue) {
                    JsonObject jsonRapportDesc = (JsonObject)actualJsonValue;
                    RapportDescription rapportDesc = rapportDescriptionService.getRapportDescriptionForClazzAndRestCall(jsonRapportDesc.getString("clazz"),
                            jsonRapportDesc.getString("restCall"));
                    Assert.assertTrue("Le rapport affiché à l'utilisateur est restreint à certains rôles",
                            StringUtils.isEmpty(rapportDesc.getRestrictionRoles()));
                }
            });
        
        //Réactivation par l'admin
        this.mockMvc.perform(MockMvcRequestBuilders.get(WebConstantUtil.RAPPORT_DESC_UPDATE_AVAILABILITY_PATH+"/"+targetDesc.getId()+"/true")
                .with(SecurityMockMvcRequestPostProcessors.user(admin)))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
