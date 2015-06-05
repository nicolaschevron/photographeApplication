package fr.inra.dsi.reporting.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * Filter permettant l'ajout d'entête spécifique permettant les appels Ajax
 * depuis un hôte tiers.
 * @author gugau
 *
 */
@Component
public class CORSFilter implements Filter {

    /**
     * Constructeur par défaut.
     */
    public CORSFilter(){
        super();
    }
    
    /**
     * {@inheritDoc}
     */
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        String allowOriginHeader = request.getHeader("Origin");
        if(StringUtils.isEmpty(allowOriginHeader)){
            // Should not work, we must send as allow-origin the origin of the page which made the request
            allowOriginHeader = "*";
        } else {
            // Fix HTTP Response splitting vulnerability
            allowOriginHeader = allowOriginHeader.replaceAll("[\\r\\n]", ""); 
        }
        
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", allowOriginHeader);
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Cache-Control", "no-cache");
        chain.doFilter(req, res);
    }

    /**
     * {@inheritDoc}
     */
    public void init(FilterConfig filterConfig) {
        //Nothing to do
    }

    /**
     * {@inheritDoc}
     */
    public void destroy() {
      //Nothing to do
    }

}
