package fr.inra.dsi.reporting.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.cxf.common.util.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import fr.inra.dsi.reporting.dto.RoleAgent;

/**
 * Implémentation de User utilisé dans la couche Spring Security 
 * @author gugau
 *
 */
public class INRAUser extends User {

//	private static final Logger LOGGER = Logger.getLogger(INRAUser.class);

	
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * Nom
     */
    private String nom;
    /**
     * Prénom 
     */
    private String prenom;
    /**
     * Matricule
     */
    private String matricule;
    
    /**
     * Roles de l'agent
     */
    private List<RoleAgent> roles = new ArrayList<RoleAgent>();
    
    /**
     * Constructeur
     * @param username
     * @param password
     * @param enabled
     * @param accountNonExpired
     * @param credentialsNonExpired
     * @param accountNonLocked
     * @param authorities
     */
    public INRAUser(String username, String password, boolean enabled,
            boolean accountNonExpired, boolean credentialsNonExpired,
            boolean accountNonLocked,
            Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired,
                accountNonLocked, authorities);
    }

    /**
     * Constructeur.
     * @param username
     * @param password
     * @param authorities
     */
    public INRAUser(String username, String password,
            Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    @Override
    public boolean equals(Object rhs) {
        return super.equals(rhs);
    }
    
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * Getter.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Setter.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Getter.
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Setter.
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Getter.
     */
    public String getMatricule() {
        return matricule;
    }

    /**
     * Setter.
     */
    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    /**
     * Getter
     * Renvoie les rôles récupérés du WS INRA
     * @return
     * @deprecated Ne jamais utiliser !
     */
    @Deprecated
    public List<RoleAgent> getRoles() {
        return roles;
    }

    /**
     * Setter
     * @param roles
     */
    public void setRoles(List<RoleAgent> roles) {
        this.roles = roles;
    }

    /**
     * Renvoie les noms des rôles (Spring Security) de cet utilisateur.
     * @return
     */
    public List<String> getRoleNames(){
        List<String> roleNames = new ArrayList<String>();
        for(GrantedAuthority auth : getAuthorities()){
            roleNames.add(auth.getAuthority());
        }
        return roleNames;
    }
    
    /**
     * Indique si cet utilisateur a le rôle ROLE_DU
     * @return
     */
    public boolean isDU(){
        return this.hasRole(Constant.ROLE_DU);
    }
    
    /**
     * Indique si cet utilisateur a le rôle ROLE_CNUE
     * @return
     */
    public boolean isCNUE(){
        return this.hasRole(Constant.ROLE_CNUE);
    }
    
    /**
     * Indique si cet utilisateur a le rôle ROLE_CNOC
     * @return
     */
    public boolean isCNOC(){
        return this.hasRole(Constant.ROLE_CNOC);
    }
    
    /**
     * Indique si cet utilisateur a le rôle ROLE_CD
     * @return
     */
    public boolean isCD(){
        return this.hasRole(Constant.ROLE_CD);
    }
    
    /**
     * Indique si cet utilisateur est directeur d'appui à la recherche
     * @return
     */
    public boolean isDAR(){
        return this.hasRole(Constant.ROLE_DAR);
    }
    
    /**
     * Indique si cet utilisateur est président de centre
     * @return
     */
    public boolean isPC(){
        return this.hasRole(Constant.ROLE_PC);
    }
    
    /**
     * Cette methode retourne la liste des unites d un DU
     * @return
     */
    public List<String> getCodesUnite(){
    	return this.getCodesStructure(RoleAgent.CODE_DU);
    }
    
    /**
     * Cette methode retourne la liste des departements d'un CD
     * @return
     */
    public List<String> getCodesDepartement(){
    	return this.getCodesStructure(RoleAgent.CODE_CD);
    }
    
    /**
     * Cette methode renvoie les codes des structures d'un DAR
     * @return
     */
    public List<String> getCodesDirectionAppui(){
        return this.getCodesStructure(RoleAgent.CODE_DAR);
    }
    
    /**
     * Cette methode renvoie les codes des centres d'un PC
     * @return
     */
    public List<String> getCodesCentre(){
        return this.getCodesStructure(RoleAgent.CODE_PC);
    }
    
    /**
     * Cette methode retourne la structure par ex pour un DU elle retourne le code de son unite  
     * 
     * @param codeRole
     * @return
     */
    private List<String> getCodesStructure (String codeRole){
    	List<String> codesStructure = new ArrayList<>();
    	for (RoleAgent roleAgent : this.getRoles()) {
    		if(!StringUtils.isEmpty(roleAgent.getCodeRole()) 
    				&& roleAgent.getCodeRole().equals(codeRole)){
    			codesStructure.add(roleAgent.getCodeStructure());
    		}
		}
    	return codesStructure;
    }
    
    /**
     * Test si l'utilisateur a un role (Spring Security)
     * @param roleName
     * @return
     */
    public boolean hasRole(String roleName){
        for(GrantedAuthority auth : getAuthorities()){
            if(roleName.equals(auth.getAuthority())){
            	return true;
            }
        }
        return false;
    }
    
    /**
     * Test si un rôle (INRA) est dans une liste de rôles.
     * 
     * @param roles la liste de rôle
     * @param code le code du rôle recherché
     * @return
     */
    protected static boolean hasRoleINRA(List<RoleAgent> roles, String code) {
        for(RoleAgent role : roles){
            if(code.equals(role.getCodeRole())){
                return true;
            }
        }
        return false;
    }
    
}
