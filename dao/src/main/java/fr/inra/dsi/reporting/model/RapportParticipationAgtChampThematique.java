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
 * Classe Model/Entity pour les rapports participation des agents sur les activites.
 * 
 * @author cebri
 */
@Entity
@Table(name = "RAPPORT_PARTICIPATION_AGENT_SUR_CHAMP_THEMATIQUE_ACTIVITE")
public class RapportParticipationAgtChampThematique implements IRapport {
	
	public static final int LIBELLE_LIMIT = 1000;
	
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rapport_participation_agt_sur_champ_thematique_activite_seq_gen")
    @SequenceGenerator(name = "rapport_participation_agt_sur_champ_thematique_activite_seq_gen", sequenceName = "rapport_participation_agt_sur_champ_thematique_activite_id_seq")
    private int identifiant;
	
	@Column(name = "code_ct")
    private int codeCtRpCt;
	
	@Column(name = "nom_ct")
	private String nomCtRpCt;
    
	@Column(name = "sigle_ct")
    private String sigleCtRpCt;
	
	@Column(name = "sigle_dept_pilote_ct")
    private String sigleDeptPiloteRpCt;
    
	@Column(name = "sigle_num")
    private int sigleNumRpCt;
	
    @Column(name = "sigle_num_ct")
    private String sigleNumCtRpCt;
	
	@Column(name = "code_unite_agent")
	private String codeUniteAgentRpCt;
	
	@Column(name = "code_departement_agent")
    private String codiqueDepartementAgentRpCt;
	
	@Column(name = "code_centre")
    private String codeCentreRpCt;
	
	@Column(name = "matricule_personne")
	private String matriculePersonneRpCt;
	
	@Column(name = "nom_personne")
	private String nomPersonneRpCt;
	
	@Column(name = "prenom_personne")
	private String prenomPersonneRpCt;
	
	@Column(name = "type_personne")
	private String typePersonneRpCt;
	
	@Column(name = "categorie_personne")
	private String categoriePersonneRpCt;
	
	@Column(name = "code_grade_personne")
	private String codeGradePersonneRpCt;
	
	@Column(name = "pourcentage_temps_travail_personne")
	private String pourcentageTempsTravailPersonneRpCt;
	

	/**
	 * Constructeur.
	 */
	public RapportParticipationAgtChampThematique() {
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
	public int getCodeCtRpCt() {
		return codeCtRpCt;
	}

    /**
     * Setter
     */
	public void setCodeCtRpCt(int codeCtRpCt) {
		this.codeCtRpCt = codeCtRpCt;
	}

	/**
     * Getter. 
     * @return
     */
	public String getNomCtRpCt() {
		return nomCtRpCt;
	}

    /**
     * Setter
     */
	public void setNomCtRpCt(String nomCtRpCt) {
		this.nomCtRpCt = nomCtRpCt;
	}

	/**
     * Getter. 
     * @return
     */
	public String getSigleCtRpCt() {
		return sigleCtRpCt;
	}

    /**
     * Setter
     */
	public void setSigleCtRpCt(String sigleCtRpCt) {
		this.sigleCtRpCt = sigleCtRpCt;
	}

	/**
     * Getter. 
     * @return
     */
	public int getSigleNumRpCt() {
		return sigleNumRpCt;
	}

    /**
     * Setter
     */
	public void setSigleNumRpCt(int sigleNumRpCt) {
		this.sigleNumRpCt = sigleNumRpCt;
	}

	/**
     * Getter. 
     * @return
     */
	public String getSigleNumCtRpCt() {
		return sigleNumCtRpCt;
	}

    /**
     * Setter
     */
	public void setSigleNumCtRpCt(String sigleNumCtRpCt) {
		this.sigleNumCtRpCt = sigleNumCtRpCt;
	}


	/**
     * Getter. 
     * @return
     */
	public String getCodeUniteAgentRpCt() {
		return codeUniteAgentRpCt;
	}

    /**
     * Setter
     */
	public void setCodeUniteAgentRpCt(String codeUniteAgentRpCt) {
		this.codeUniteAgentRpCt = codeUniteAgentRpCt;
	}

	/**
     * Getter. 
     * @return
     */
	public String getCodiqueDepartementAgentRpCt() {
		return codiqueDepartementAgentRpCt;
	}

    /**
     * Setter
     */
	public void setCodiqueDepartementAgentRpCt(String codiqueDepartementAgentRpCt) {
		this.codiqueDepartementAgentRpCt = codiqueDepartementAgentRpCt;
	}

	/**
     * Getter. 
     * @return
     */
	public String getCodeCentreRpCt() {
		return codeCentreRpCt;
	}

    /**
     * Setter
     */
	public void setCodeCentreRpCt(String codeCentreRpCt) {
		this.codeCentreRpCt = codeCentreRpCt;
	}

	/**
     * Getter. 
     * @return
     */
	public String getMatriculePersonneRpCt() {
		return matriculePersonneRpCt;
	}

    /**
     * Setter
     */
	public void setMatriculePersonneRpCt(String matriculePersonneRpCt) {
		this.matriculePersonneRpCt = matriculePersonneRpCt;
	}

	/**
     * Getter. 
     * @return
     */
	public String getNomPersonneRpCt() {
		return nomPersonneRpCt;
	}

    /**
     * Setter
     */
	public void setNomPersonneRpCt(String nomPersonneRpCt) {
		this.nomPersonneRpCt = nomPersonneRpCt;
	}

	/**
     * Getter. 
     * @return
     */
	public String getPrenomPersonneRpCt() {
		return prenomPersonneRpCt;
	}

    /**
     * Setter
     */
	public void setPrenomPersonneRpCt(String prenomPersonneRpCt) {
		this.prenomPersonneRpCt = prenomPersonneRpCt;
	}

	/**
     * Getter. 
     * @return
     */
	public String getTypePersonneRpCt() {
		return typePersonneRpCt;
	}

    /**
     * Setter
     */
	public void setTypePersonneRpCt(String typePersonneRpCt) {
		this.typePersonneRpCt = typePersonneRpCt;
	}

	/**
     * Getter. 
     * @return
     */
	public String getCategoriePersonneRpCt() {
		return categoriePersonneRpCt;
	}

    /**
     * Setter
     */
	public void setCategoriePersonneRpCt(String categoriePersonneRpCt) {
		this.categoriePersonneRpCt = categoriePersonneRpCt;
	}

	/**
     * Getter. 
     * @return
     */
	public String getCodeGradePersonneRpCt() {
		return codeGradePersonneRpCt;
	}

    /**
     * Setter
     */
	public void setCodeGradePersonneRpCt(String codeGradePersonneRpCt) {
		this.codeGradePersonneRpCt = codeGradePersonneRpCt;
	}

	/**
     * Getter. 
     * @return
     */
	public String getPourcentageTempsTravailPersonneRpCt() {
		return pourcentageTempsTravailPersonneRpCt;
	}

    /**
     * Setter
     */
	public void setPourcentageTempsTravailPersonneRpCt(
			String pourcentageTempsTravailPersonneRpCt) {
		this.pourcentageTempsTravailPersonneRpCt = pourcentageTempsTravailPersonneRpCt;
	}

	public String getSigleDeptPiloteRpCt() {
		return sigleDeptPiloteRpCt;
	}

	public void setSigleDeptPiloteRpCt(String sigleDeptPiloteRpCt) {
		this.sigleDeptPiloteRpCt = sigleDeptPiloteRpCt;
	}



	}
