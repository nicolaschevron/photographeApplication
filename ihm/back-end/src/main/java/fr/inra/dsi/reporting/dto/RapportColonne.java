package fr.inra.dsi.reporting.dto;

/**
 * collonne appartenant au rapport
 * @author niche
 *
 */
public class RapportColonne {

    public static final String TYPE_DATE = "date";
    public static final String TYPE_STRING = "string"; 
	
	/**
	 * libelle de la colonne
	 */
	private String libelle;
	
	/**
	 * cle de la colonne
	 */
	private String cle;
	
	/**
	 * Type de données de la colonne
	 */
	private String type;
	
	/**
	 * Constructeur
	 */
	public RapportColonne() {
		super();
	}
	
	/**
	 * Constructeur
	 * @param libelle
	 * @param cle
	 */
	public RapportColonne(String libelle, String cle, String type) {
		super();
		this.cle=cle;
		this.libelle=libelle;
		this.type=type;
	}

	/**
	 * Getter
	 */
	public String getLibelle() {
		return libelle;
	}

    /**
     * Setter
     */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}


    /**
     * Getter
     */
	public String getCle() {
		return cle;
	}

    /**
     * Setter
     */
	public void setCle(String cle) {
		this.cle = cle;
	}

    /**
     * Getter
     */
    public String getType() {
        return type;
    }

    /**
     * Setter
     */
    public void setType(String type) {
        this.type = type;
    }

}
