package fr.inra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Classe de configuration de Spring MVC.
 * 
 * @author lepra
 */
@Configuration
@ComponentScan(basePackages="fr.inra")
@EnableWebMvc
public class MvcConfiguration extends WebMvcConfigurerAdapter{

    /**
     * Constructeur.
     */
    public MvcConfiguration() {
        super();
    }
    
    /**
     * Recuperation du ViewResolver de Spring MVC.
     * 
     * @return ViewResolver
     */
	@Bean
	public ViewResolver getViewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
	/**
	 * Ajout du repertoire contenant les ressources.
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	
}
