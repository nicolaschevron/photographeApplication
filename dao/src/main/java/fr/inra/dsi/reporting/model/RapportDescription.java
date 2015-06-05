package fr.inra.dsi.reporting.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Classe Model/Entity pour les differents types de rapport
 * 
 * @author cebri
 */
@Entity
@Table(name = "RAPPORT_DESCRIPTION")
public class RapportDescription {

	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rapport_description_seq_gen")
	@SequenceGenerator(name = "rapport_description_seq_gen", sequenceName = "rapport_description_id_seq")
	private int id;
	
	@Column(name = "class")
	private String clazz;
	
	@Column(name = "titre")
	private String titre;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "actif")
	private boolean actif;

	@Column(name = "rest_call")
	private String restCall;
	
	@Column(name = "date_dernier_import")
    private Date dateDernierImport;
	
	@Column(name="restriction_roles")
	private String restrictionRoles;
	
    /**
     * Constructeur.
     */
    public RapportDescription() {
        super();
    }
	
    /**
     * Constructeur.
     * @param titre
     * @param clazz
     * @param description
     * @param actif
     * @param restCall
     * @param restrictionRoles
     */
    public RapportDescription(String titre, String clazz, String description, boolean actif,
            String restCall, String restrictionRoles) {
        super();
        this.titre = titre;
        this.clazz = clazz;
        this.description = description;
        this.actif = actif;
        this.restCall = restCall;
        this.restrictionRoles = restrictionRoles;
    }

    /**
     * Getter
     */
	public int getId() {
		return id;
	}

    /**
     * Setter
     */
	public void setId(int id) {
		this.id = id;
	}

    /**
     * Getter
     */
	public String getTitre() {
		return titre;
	}

    /**
     * Setter
     */
	public void setTitre(String titre) {
		this.titre = titre;
	}

    /**
     * Getter
     */
	public String getDescription() {
		return description;
	}

    /**
     * Setter
     */
	public void setDescription(String description) {
		this.description = description;
	}

    /**
     * Getter
     */
	public boolean isActif() {
		return actif;
	}

    /**
     * Setter
     */
	public void setActif(boolean actif) {
		this.actif = actif;
	}

    /**
     * Getter
     */
	public String getRestCall() {
		return restCall;
	}

    /**
     * Setter
     */
	public void setRestCall(String restCall) {
		this.restCall = restCall;
	}

    /**
     * Getter
     */
    public Date getDateDernierImport() {
        return dateDernierImport;
    }

    /**
     * Setter
     */
    public void setDateDernierImport(Date dateDernierImport) {
        this.dateDernierImport = dateDernierImport;
    }

    /**
     * Getter
     */
    public String getClazz() {
        return clazz;
    }

    /**
     * Setter
     */
    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    /**
     * Getter
     */
    public String getRestrictionRoles() {
        return restrictionRoles;
    }

    /**
     * Setter
     */
    public void setRestrictionRoles(String restrictionRoles) {
        this.restrictionRoles = restrictionRoles;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj == this) {
            result = true;
        } else if (obj instanceof RapportDescription) {
            RapportDescription rapport = (RapportDescription) obj;
            if ((this.clazz != null) && (this.clazz.equals(rapport.clazz))
                    && (this.restCall != null) && (this.restCall.equals(rapport.restCall))) {
                result = true;
            }
        }
        return result;
    }
}
