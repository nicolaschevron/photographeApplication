package fr.inra.dsi.reporting.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Classe Model/Entity pour les rapports mots cles par activite
 * 
 * @author cebri
 */
@Entity
@Table(name = "RAPPORT_MOTS_CLES_PAR_ACTIVITE")
public class RapportMotsClesParActivite implements IRapport {

    private static final int LIBELLE_LIMIT = 5000;
    private static final int MC_LIMIT = 10000;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rapport_mots_cles_par_activite_seq_gen")
	@SequenceGenerator(name = "rapport_mots_cles_par_activite_seq_gen", sequenceName = "rapport_mots_cles_par_activite_id_seq")
	private int identifiant;
	
	@Column(name = "abreviation_type_ac")
	private String abreviationTypeAc;

	@Column(name = "code_activite_collective")
	private String codeActiviteCollective;

	@Column(name = "libelle_ac", length = LIBELLE_LIMIT)
	private String libelleAc;

    @Column(name = "liste_unite_participante")
	private String listeUniteParticipante;

	@Column(name = "liste_departement_copilote")
	private String listeDepartementCopilote;
	
	@Column(name = "liste_mots_cles", length = MC_LIMIT)
	private String listeMotsCles;

	@Column(name = "code_departement")
	private String codiqueDepartement;

	@Column(name = "code_centre")
	private String codeCentre;
	
	@Column(name = "code_unite")
	private String codeUnite;
	
	/**
	 * Constructeur.
	 */
	public RapportMotsClesParActivite() {
		super();
	}

    /**
     * Getter. 
     * @return
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
     * Getter. 
     * @return
     */
	public String getAbreviationTypeAc() {
		return abreviationTypeAc;
	}

	public void setAbreviationTypeAc(String abreviationTypeAc) {
		this.abreviationTypeAc = abreviationTypeAc;
	}

    /**
     * Getter. 
     * @return
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
     * Getter. 
     * @return
     */
	public String getLibelleAc() {
		return libelleAc;
	}

    /**
     * Setter
     */
	public void setLibelleAc(String libelleAc) {
		this.libelleAc = libelleAc;
	}

    /**
     * Getter. 
     * @return
     */
	public String getListeUniteParticipante() {
		return listeUniteParticipante;
	}

    /**
     * Setter
     */
	public void setListeUniteParticipante(String listeUniteParticipante) {
		this.listeUniteParticipante = listeUniteParticipante;
	}

    /**
     * Getter. 
     * @return
     */
	public String getListeMotsCles() {
		return listeMotsCles;
	}

    /**
     * Setter
     */
	public void setListeMotsCles(String listeMotsCles) {
		this.listeMotsCles = listeMotsCles;
	}

    /**
     * Getter. 
     * @return
     */
	public String getCodiqueDepartement() {
		return codiqueDepartement;
	}

    /**
     * Setter
     */
	public void setCodiqueDepartement(String codiqueDepartement) {
		this.codiqueDepartement = codiqueDepartement;
	}

    /**
     * Getter. 
     * @return
     */
	public String getCodeCentre() {
		return codeCentre;
	}

    /**
     * Setter
     */
	public void setCodeCentre(String codeCentre) {
		this.codeCentre = codeCentre;
	}

    /**
     * Getter. 
     * @return
     */
	public String getListeDepartementCopilote() {
		return listeDepartementCopilote;
	}

    /**
     * Setter
     */
	public void setListeDepartementCopilote(String listeDepartementCopilote) {
		this.listeDepartementCopilote = listeDepartementCopilote;
	}

    /**
     * Getter. 
     * @return
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
