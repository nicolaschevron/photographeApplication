package fr.inra.dsi.reporting.security;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import fr.inra.dsi.reporting.service.ISecurityManagerService;

/**
 * PermissionEvaluator personnalisé.
 * 
 * Evalue les permissions personnalisées, par exemple pour l'accès aux rapports en fonction des rôles
 * @author gugau
 *
 */
public class CustomPermissionEvaluator implements PermissionEvaluator {

    private static final Logger LOGGER = Logger.getLogger(CustomPermissionEvaluator.class);
    
    @Autowired
    private ISecurityManagerService managerService;
    
    /**
     * Constructeur.
     */
    public CustomPermissionEvaluator() {
        super();
    }

    /**
     * Non implémenté.
     */
    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        LOGGER.error("Appel à la méthode 'hasPermission' non implémentée");
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        if(Constant.PERMISSION_READ_REPORT.equals(permission)){
            INRAUser user = (INRAUser)authentication.getPrincipal();
            return managerService.hasPermissionReadRapport(user, targetType, targetId.toString());
        }
        LOGGER.debug("Cet utilisateur n a pas les droits d acces");
        return false;
    }
    
    /**
     * Setter.
     */
    public void setManagerService(ISecurityManagerService managerService) {
        this.managerService = managerService;
    }

}
