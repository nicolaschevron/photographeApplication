package fr.inra.dsi.reporting.util;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import fr.inra.dsi.reporting.security.Constant;

/**
 * Classe utilitaire pour les traitements récurrents des Controller.
 * 
 * @author lepra
 */
public final class ControllerUtil {
    
    /**
     * Constructeur.
     */
    private ControllerUtil() {
        super();
    }
    
    /**
     * N'appeler cette methode que la ou c'est architecturalement acceptable, 
     * a savoir dans les Controllers.
     * 
     * @return boolean
     */
    public static boolean isAdmin() {
        return ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                .getAuthorities().contains(new SimpleGrantedAuthority(Constant.ROLE_ADMIN));
    }
}
