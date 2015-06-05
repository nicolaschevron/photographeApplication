package fr.inra.dsi.reporting.security;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import fr.inra.dsi.reporting.util.WebConstantUtil;

/**
 * AuthenticationEntryPoint permettant d'utiliser un CasAuthenticationEntryPoint
 * seulement sur certaine URL.
 * 
 * Renvoie un code 403 si CasAuthenticationEntryPoint n'est pas appelé.
 */
public class Regex401CasAuthenticationEntryPoint implements AuthenticationEntryPoint {
    
    /**
     * CasAuthenticationEntryPoint à appeler
     */
    private CasAuthenticationEntryPoint casAuthenticationEntryPoint;
    
    /**
     * Expression régulière à utiliser pour filtrer les URL
     * 
     * Si l'URL correspond à l'expression régulière, alors le  CasAuthenticationEntryPoint
     * est appelé.
     */
    private String regexUrl;

    private transient Pattern urlPattern;
    
    /**
     * Constructeur.
     */
    public Regex401CasAuthenticationEntryPoint() {
        super();
    }
    
    /**
     * Initialisation
     */
    @PostConstruct
    public void postConstruct(){
        urlPattern = Pattern.compile(regexUrl);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void commence(HttpServletRequest servletRequest, HttpServletResponse response,
            AuthenticationException authenticationException) throws IOException, ServletException {
        Matcher matcher = urlPattern.matcher(servletRequest.getRequestURL().toString());
        if(matcher.matches()){
            String redirectUrl = servletRequest.getParameter(WebConstantUtil.SESSION_REDIRECT_PARAM);
            if(redirectUrl != null){
                servletRequest.getSession().setAttribute(WebConstantUtil.SESSION_REDIRECT_PARAM, redirectUrl);
            }
            casAuthenticationEntryPoint.commence(servletRequest, response, authenticationException);
        } else {
            response.sendError(HttpStatus.UNAUTHORIZED.value());
        }
    }

    /**
     * Getter.
     */
    public CasAuthenticationEntryPoint getCasAuthenticationEntryPoint() {
        return casAuthenticationEntryPoint;
    }

    /**
     * Setter.
     */
    public void setCasAuthenticationEntryPoint(
            CasAuthenticationEntryPoint casAuthenticationEntryPoint) {
        this.casAuthenticationEntryPoint = casAuthenticationEntryPoint;
    }

    /**
     * Getter.
     */
    public String getRegexUrl() {
        return regexUrl;
    }

    /**
     * Setter.
     */
    public void setRegexUrl(String regexUrl) {
        this.regexUrl = regexUrl;
    }
    
}
