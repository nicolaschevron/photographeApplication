package fr.inra.dsi.reporting.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import fr.inra.dsi.reporting.dto.InfosAgent;
import fr.inra.dsi.reporting.dto.RoleAgent;
import fr.inra.dsi.reporting.exception.ServiceException;
import fr.inra.dsi.reporting.service.IAuthenticationService;

/**
 * Service de création des utilisateurs.
 * @author gugau
 *
 */
public class UserCasServiceImpl implements UserDetailsService {

	private static final Logger LOGGER = Logger.getLogger(UserCasServiceImpl.class);
	
    /**
     * Service IAuthenticationService
     */
    @Autowired
    private transient IAuthenticationService authentService;
    
    @Value("${configuration.user.admin}")
    private String[] listAdminUsers;
    private List<String> adminUsers;
    
    /**
     * Constructeur par défaut.
     */
    public UserCasServiceImpl(){
        super();
    }
    
    /**
     * Postconstruct
     */
    @PostConstruct
    public void postConstruct(){
        adminUsers = Arrays.asList(listAdminUsers);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public UserDetails loadUserByUsername(String username) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        
        authorities.add(new SimpleGrantedAuthority(Constant.ROLE_AUTHENTICATED));
        
        InfosAgent userInfos;
        try {
            userInfos = authentService.getInfosAgentByLogin(username);
        } catch (ServiceException e) {
            throw new UsernameNotFoundException("Impossible d'accéder aux informations de l'utilisateur", e);
        }
        //On ajoute le role admin si l'utilisateur est présent dans la liste des matricules d utilisateurs
        if (adminUsers.contains(userInfos.getMatricule())){
        	LOGGER.info("Admin en cours de connection sur l application");
        	authorities.add(new SimpleGrantedAuthority(Constant.ROLE_ADMIN));
        }
    	LOGGER.debug("matricule de l utilisateur connecte : " + userInfos.getMatricule());
    	
    	
        
        List<RoleAgent> rolesInra;
        try {
            rolesInra = authentService.getRolesByMatricule(userInfos.getMatricule());
        } catch (ServiceException e) {
            throw new UsernameNotFoundException("Impossible d'accéder aux roles de l'utilisateur", e);
        }

        if(INRAUser.hasRoleINRA(rolesInra,RoleAgent.CODE_DU)){
            authorities.add(new SimpleGrantedAuthority(Constant.ROLE_DU));
            LOGGER.debug("Ajout d'une authorites DU pour l'user");
        }
        if(INRAUser.hasRoleINRA(rolesInra,RoleAgent.CODE_CD)){
            authorities.add(new SimpleGrantedAuthority(Constant.ROLE_CD));
            LOGGER.debug("Ajout d'une authorites CD pour l'user");
        }
        if(INRAUser.hasRoleINRA(rolesInra,RoleAgent.CODE_CNOC)){
            authorities.add(new SimpleGrantedAuthority(Constant.ROLE_CNOC));
            LOGGER.debug("Ajout d'une authorites CNOC pour l'user");
        }
        if(INRAUser.hasRoleINRA(rolesInra,RoleAgent.CODE_CNUE)){
            authorities.add(new SimpleGrantedAuthority(Constant.ROLE_CNUE));
            LOGGER.debug("Ajout d'une authorites CNUE pour l'user");
        }
        if(INRAUser.hasRoleINRA(rolesInra,RoleAgent.CODE_DAR)){
            authorities.add(new SimpleGrantedAuthority(Constant.ROLE_DAR));
            LOGGER.debug("Ajout d'une authorites DAR pour l'user");
        }
        if(INRAUser.hasRoleINRA(rolesInra,RoleAgent.CODE_PC)){
            authorities.add(new SimpleGrantedAuthority(Constant.ROLE_PC));
            LOGGER.debug("Ajout d'une authorites PC pour l'user");
        }
        INRAUser result = new INRAUser(username, "", authorities);
        result.setRoles(rolesInra);
        result.setMatricule(userInfos.getMatricule());
        result.setNom(userInfos.getNom());
        result.setPrenom(userInfos.getPrenom());
        
        
        return result;
    }

    /**
     * Affecte le service IAuthenticationService
     * @param authentService
     */
    public void setAuthentService(IAuthenticationService authentService) {
        this.authentService = authentService;
    }
    
}
