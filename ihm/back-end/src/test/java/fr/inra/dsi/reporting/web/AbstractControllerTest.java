package fr.inra.dsi.reporting.web;

import java.text.ParseException;

import javax.servlet.Filter;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.test.context.support.WithSecurityContextTestExcecutionListener;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.ServletTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import fr.inra.dsi.reporting.data.impl.DataSampleBuilder;

/**
 * Classe de base pour tester les controlleurs
 * @author gugau
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration (value = "classpath:test-servlet-context.xml")
@WebAppConfiguration
@TestExecutionListeners(listeners={ServletTestExecutionListener.class,
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        WithSecurityContextTestExcecutionListener.class})
public abstract class AbstractControllerTest {

    /**
     * Contexte de l'application
     */
    @Autowired
    protected WebApplicationContext wac;
    
    /**
     * Couche spring security
     */
    @Autowired
    private Filter springSecurityFilterChain;
    
    /**
     * Source de message
     */
    @Autowired
    protected MessageSource messageSource;
    
    /**
     * Database creator.
     */
    @Autowired
    private DataSampleBuilder dataSampleCreator;
    
    protected MockMvc mockMvc;
    
    /**
     * Constructeur.
     */
    public AbstractControllerTest(){
        super();
    }
    
    @Before
    public void mockMvcSetup() throws ParseException {
        dataSampleCreator.createDataset();
        
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
                            .addFilter(springSecurityFilterChain)
                            .build();
        
    }

    public void setWac(WebApplicationContext wac) {
        this.wac = wac;
    }

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public void setSpringSecurityFilterChain(Filter springSecurityFilterChain) {
        this.springSecurityFilterChain = springSecurityFilterChain;
    }
    
}
