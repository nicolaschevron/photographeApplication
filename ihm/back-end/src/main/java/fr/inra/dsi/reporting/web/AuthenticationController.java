package fr.inra.dsi.reporting.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.inra.dsi.reporting.dto.InfosAgent;
import fr.inra.dsi.reporting.security.Constant;
import fr.inra.dsi.reporting.security.INRAUser;
import fr.inra.dsi.reporting.util.WebConstantUtil;

/**
 * Classe Controlleur permettant l'authentification.
 * 
 * @author gugau
 */
@Controller
public class AuthenticationController {

//	private static final Logger LOGGER = Logger.getLogger(AuthenticationWebServiceImpl.class);

	
    /**
     * Constructeur par défaut.
     */
    public AuthenticationController(){
        super();
    }
    
    /**
     * Verification de l'authentification.
     * 
     * @param request
     * @param response
     */
    @RequestMapping(value = WebConstantUtil.AUTHENTICATION_CHECK_PATH, method=RequestMethod.GET)
    public void checkAuthentication(HttpServletRequest request, HttpServletResponse response){
        //If we get here then the user is authentificated
        response.setStatus(HttpStatus.OK.value());
    }
    
    /**
     * Recuperation des informations de l'utilisateur.
     * 
     * @param request
     * @return {@link InfosAgent}
     */
    @RequestMapping(value = WebConstantUtil.USER_INFOS_PATH, method=RequestMethod.GET)
    public @ResponseBody InfosAgent getUserInfos(HttpServletRequest request){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        INRAUser user = (INRAUser) auth.getPrincipal();
        
        InfosAgent result = new InfosAgent();
        
        result.setMatricule(user.getMatricule());
        result.setPrenom(user.getPrenom());
        result.setNom(user.getNom());
        if (user.getAuthorities().contains(new SimpleGrantedAuthority (Constant.ROLE_ADMIN))){
        	result.setIsAdmin(true);
        }else{
        	result.setIsAdmin(false);
        }
        
        return result;
    }
    
}
