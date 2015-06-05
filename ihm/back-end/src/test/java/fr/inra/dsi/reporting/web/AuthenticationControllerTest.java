package fr.inra.dsi.reporting.web;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import fr.inra.dsi.reporting.resultmatcher.JSONResultMatcher;
import fr.inra.dsi.reporting.util.WebConstantUtil;
import fr.inra.dsi.reporting.ws.mock.AuthenticationWebServiceMock;

/**
 * Test du controleur d'authentification
 * @author gugau
 *
 */
public class AuthenticationControllerTest extends AbstractControllerTest {

    /**
     * Service spring security UserDetailsService
     */
    @Autowired
    private UserDetailsService userDetailsService;
    
    /**
     * Constructeur
     */
    public AuthenticationControllerTest(){
        super();
    }
    
    /**
     * Test l'URL d'authentification
     * @throws Exception
     */
    @Test
    public void testAuthentication() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders.get(WebConstantUtil.AUTHENTICATION_CHECK_PATH))
            .andExpect(MockMvcResultMatchers.status().isUnauthorized());
        
        this.mockMvc.perform(MockMvcRequestBuilders.get(WebConstantUtil.AUTHENTICATION_CHECK_PATH)
                .with(SecurityMockMvcRequestPostProcessors.user(userDetailsService.loadUserByUsername("test"))))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    /**
     * Test l'accès aux informations de l'utilisateur
     * @throws Exception
     */
    @Test
    public void testUtilisateurInformations() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders.get(WebConstantUtil.USER_INFOS_PATH))
            .andExpect(MockMvcResultMatchers.status().isUnauthorized());
        
        this.mockMvc.perform(MockMvcRequestBuilders.get(WebConstantUtil.USER_INFOS_PATH)
                .with(SecurityMockMvcRequestPostProcessors.user(userDetailsService.loadUserByUsername("test"))))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(new JSONResultMatcher("{\"prenom\":\""
                    +AuthenticationWebServiceMock.MOCK_FIRSTNAME
                    +"\", \"nom\":\""
                    +AuthenticationWebServiceMock.MOCK_NAME
                    +"\", \"matricule\":\""
                    +AuthenticationWebServiceMock.MOCK_MATRICULE
                    +"\"}"));
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

}
