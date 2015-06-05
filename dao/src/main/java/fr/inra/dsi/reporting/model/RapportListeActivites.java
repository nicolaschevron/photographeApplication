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
 * Classe Model/Entity pour les rapports ListeActivites.
 * 
 * @author cebri
 */
@Entity
@Table(name = "RAPPORT_LISTE_ACTIVITES")
public class RapportListeActivites implements IRapport {

    public static final int LIBELLE_LIMIT = 1000;
    
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rapport_liste_activites_seq_gen")
	@SequenceGenerator(name = "rapport_liste_activites_seq_gen", sequenceName = "rapport_liste_activites_id_seq")
	private int identifiant;

	@Column(name = "libelle_type_ac")
	private String libelleTypeAc;

	@Column(name = "abreviation_type_ac")
	private String abreviationTypeAc;

	@Column(name = "code_activite_collective")
	private String codeActiviteCollective;

	@Column(name = "libelle_ac", length = LIBELLE_LIMIT)
	private String libelleAc;

	@Column(name = "date")
	// equivalent date_debut
	private Date date;

	@Column(name = "date_fin_prevue")
	private Date dateFinPrevue;

	@Column(name = "date_fin_effective")
	private Date dateFinEffective;

	@Column(name = "etat_ac")
	private String etatAc;

	@Column(name = "code_unite")
	private String codeUnite;

	/**
	 * Constructeur.
	 */
	public RapportListeActivites() {
		super();
	}

    /**
     * Getter
     */
	public int getIdentifiant() {
		return identifiant;
	}

    /**
     * Setter
     */
	public void setIdentifiant(int identifiant) {
		this.identifiant = identifiant;
	}

    /**
     * Getter
     */
	public String getLibelleTypeAc() {
		return libelleTypeAc;
	}

    /**
     * Setter
     */
	public void setLibelleTypeAc(String libelleTypeAc) {
		this.libelleTypeAc = libelleTypeAc;
	}

    /**
     * Getter
     */
	public String getAbreviationTypeAc() {
		return abreviationTypeAc;
	}

    /**
     * Setter
     */
	public void setAbreviationTypeAc(String abreviationTypeAc) {
		this.abreviationTypeAc = abreviationTypeAc;
	}

    /**
     * Getter
     */
	public String getCodeActiviteCollective() {
		return codeActiviteCollective;
	}

    /**
     * Setter
     */
	public void setCodeActiviteCollective(String codeActiviteCollective) {
		this.codeActiviteCollective = codeActiviteCollective;
	}

    /**
     * Getter
     */
	public String getLibelleAc() {
		return libelleAc;
	}

    /**
     * Setter
     */
	public void setLibelleAc(String libelleAc) {
	    if ((libelleAc != null) && (libelleAc.length() > LIBELLE_LIMIT)) {
            this.libelleAc = libelleAc.substring(0, LIBELLE_LIMIT);
        } else {
            this.libelleAc = libelleAc;
        }
	}

    /**
     * Getter
     */
	public Date getDate() {
		return date;
	}

    /**
     * Setter
     */
	public void setDate(Date date) {
		this.date = date;
	}

    /**
     * Getter
     */
	public Date getDateFinPrevue() {
		return dateFinPrevue;
	}

    /**
     * Setter
     */
	public void setDateFinPrevue(Date dateFinPrevue) {
		this.dateFinPrevue = dateFinPrevue;
	}

    /**
     * Getter
     */
	public Date getDateFinEffective() {
		return dateFinEffective;
	}

    /**
     * Setter
     */
	public void setDateFinEffective(Date dateFinEffective) {
		this.dateFinEffective = dateFinEffective;
	}

    /**
     * Getter
     */
	public String getEtatAc() {
		return etatAc;
	}

    /**
     * Setter
     */
	public void setEtatAc(String etatAc) {
		this.etatAc = etatAc;
	}

    /**
     * Getter
     */
	public String getCodeUnite() {
		return codeUnite;
	}

    /**
     * Setter
     */
	public void setCodeUnite(String codeUnite) {
		this.codeUnite = codeUnite;
	}

}
