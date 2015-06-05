package fr.inra.dsi.reporting.dto;

/**
 * Représente les informations d'un agent de l'INRA
 * @author gugau
 *
 */
public class InfosAgent {

    /**
     * Matricule
     */
    private String matricule;
    /**
     * Nom
     */
    private String nom;
    /**
     * Prénom
     */
    private String prenom;
    /**
     * isAdmin
     */
    private Boolean isAdmin;
    

    /**
     * Constructeur.
     */
    public InfosAgent(){
        super();
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
	public Boolean getIsAdmin() {
		return isAdmin;
	}

	/**
     * Setter.
     */
	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
    
}
