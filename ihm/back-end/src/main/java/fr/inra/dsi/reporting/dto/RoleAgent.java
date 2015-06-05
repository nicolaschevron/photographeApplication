package fr.inra.dsi.reporting.dto;

import java.io.Serializable;

/**
 * Représente le rôle d'un agent
 * @author gugau
 *
 */
public class RoleAgent implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Code directeur d'unité
     */
    public static final String CODE_DU = "DU";

    /**
     * Code chef de departement
     */
    public static final String CODE_CD = "CD";
    
    /**
     * Code Commission Nationale des Outils Collectifs
     */
    public static final String CODE_CNOC = "RESPONSABLE_CNOC";

    /**
     * Code Commission Nationale des Unités Expérimentales
     */
    public static final String CODE_CNUE = "RESPONSABLE_CNUE";

    /**
     * Code directeur d'appui à la recherche
     */
    public static final String CODE_DAR = "DAR";

    /**
     * Code président de centre
     */
    public static final String CODE_PC = "PC";
    
    /**
     * Code du role
     */
    private String codeRole;
    
    /**
     * Code de la structure visé par ce role
     */
    private String codeStructure;
    
    /**
     * Constructeur
     */
    public RoleAgent(){
        super();
    }

    /**
     * Constructeur
     * @param codeRole
     * @param codeStructure
     */
    public RoleAgent(String codeRole, String codeStructure) {
        super();
        this.codeRole = codeRole;
        this.codeStructure = codeStructure;
    }

    /**
     * Getter
     * @return
     */
    public String getCodeRole() {
        return codeRole;
    }

    /**
     * Setter
     * @param codeRole
     */
    public void setCodeRole(String codeRole) {
        this.codeRole = codeRole;
    }

    /**
     * Getter
     * @return
     */
    public String getCodeStructure() {
        return codeStructure;
    }

    /**
     * Setter
     * @param codeRole
     */
    public void setCodeStructure(String codeStructure) {
        this.codeStructure = codeStructure;
    }
    
}
