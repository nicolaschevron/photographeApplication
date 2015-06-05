package fr.inra.dsi.reporting.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;

import fr.inra.dsi.reporting.util.WebConstantUtil;

/**
 * Servlet utilisé pour faire la redirection vers l'application source après authentification
 * @author gugau
 *
 */
public class PostAuthenticationRedirectionServlet extends HttpServlet {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Constructeur.
     */
    public PostAuthenticationRedirectionServlet(){
        super();
    }

    /**
     * {@inheritDoc}
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        Object redirectUrl = request.getSession().getAttribute(WebConstantUtil.SESSION_REDIRECT_PARAM);
        if (redirectUrl == null) {
            response.setStatus(HttpStatus.OK.value());
        } else {
            try {
                response.sendRedirect(redirectUrl.toString());
            } catch (IOException e) {
                throw new ServletException("Unable to redirect", e);
            }
        }
    }
    
}
