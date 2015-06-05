package fr.inra.dsi.reporting.security;

/**
 * Classe regroupant des constants
 * @author gugau
 *
 */
public final class Constant {

    /**
     * Constructeur par défaut.
     */
    private Constant(){
        super();
    }
    
    /**
     * Rôle des utilisateurs authentifiés.
     */
    public static final String ROLE_AUTHENTICATED = "ROLE_AUTHENTICATED";
    
    /**
     * Role des utilisateurs administrateurs
     */
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    
    /**
     * Role directeur d'unité
     */
    public static final String ROLE_DU = "ROLE_DU";
    
    /**
     * Role chef de departement
     */
    public static final String ROLE_CD = "ROLE_CD";
    
    /**
     * Role chef de departement
     */
    public static final String ROLE_CNUE = "ROLE_CNUE";
    
    /**
     * Role chef de departement
     */
    public static final String ROLE_CNOC = "ROLE_CNOC";
    
    /**
     * Role directeur d'appui à la recherche
     */
    public static final String ROLE_DAR = "ROLE_DAR";
    
    /**
     * Role président de centre
     */
    public static final String ROLE_PC = "ROLE_PC";
    
    /**
     * Permission de lecture des données des rapports
     */
    public static final String PERMISSION_READ_REPORT = "readReport";
}
