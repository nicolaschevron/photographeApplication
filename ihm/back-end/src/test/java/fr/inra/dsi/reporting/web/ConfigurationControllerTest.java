package fr.inra.dsi.reporting.web;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import fr.inra.dsi.reporting.dto.ConfigurationDto;
import fr.inra.dsi.reporting.resultmatcher.JSONResultMatcher;
import fr.inra.dsi.reporting.util.WebConstantUtil;
import fr.inra.dsi.reporting.ws.mock.AuthenticationWebServiceMock;

public class ConfigurationControllerTest extends AbstractControllerTest {

    @Value("${configuration.feedback.mail}")
    private String feedbackMail;
    
    @Value("${configuration.feedback.subject}")
    private String feedbackSubject;
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Test
    public void testConfiguration() throws Exception {
        ConfigurationDto expected = new ConfigurationDto();
        expected.setFeedbackEmail(feedbackMail);
        expected.setFeedbackSubject(feedbackSubject);
        
        UserDetails user = userDetailsService.loadUserByUsername(AuthenticationWebServiceMock.MOCK_LOGIN);
        
        this.mockMvc.perform(MockMvcRequestBuilders.get(WebConstantUtil.CONF_PATH))
            .andExpect(MockMvcResultMatchers.status().isUnauthorized());
        
        this.mockMvc.perform(MockMvcRequestBuilders.get(WebConstantUtil.CONF_PATH)
                .with(SecurityMockMvcRequestPostProcessors.user(user)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(JSONResultMatcher.contains(expected));
    }

}
