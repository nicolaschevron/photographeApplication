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
@Table(name = "RAPPORT_PARTICIPATION_AGENT_SUR_ACTIVITE")
public class RapportParticipationAgentSurActivite implements IRapport {
	
	public static final int LIBELLE_LIMIT = 1000;
	
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rapport_participation_agent_sur_activite_seq_gen")
    @SequenceGenerator(name = "rapport_participation_agent_sur_activite_seq_gen", sequenceName = "rapport_participation_agent_sur_activite_id_seq")
    private int identifiant;
	
	@Column(name = "code_activite_collective")
	private String codeActiviteCollective;
	
	@Column(name = "libelle_ac", length = LIBELLE_LIMIT)
	private String libelleAc;
	
	@Column(name = "libelle_type_ac")
	private String libelleTypeAc;
	
	@Column(name = "abreviation_type_ac")
	private String abreviationTypeAc;
	
	@Column(name = "code_unite_leader")
	private String codeUniteLeader;
	
	@Column(name = "sigle_unite_leader")
	private String sigleUniteLeader;
	
	@Column(name = "code_departement")
    private String codiqueDepartement;
	
	@Column(name = "code_centre")
    private String codeCentre;
	
	@Column(name = "liste_departement_copilote")
	private String listeDepartementCoPilote;
	
	@Column(name = "liste_unite_participante")
	private String listeUniteParticipante;
	
	@Column(name = "date_ac_debut")
	// equivalent date_debut
	private Date dateAc;
	
	@Column(name = "date_ac_fin_prevue")
	private Date dateAcFinPrevue;
	
	@Column(name = "date_ac_fin_effective")
	private Date dateAcFinEffective;
	
	@Column(name = "matricule_personne")
	private String matriculePersonne;
	
	@Column(name = "nom_personne")
	private String nomPersonne;
	
	@Column(name = "prenom_personne")
	private String prenomPersonne;
	
	@Column(name = "unite_personne")
	private String unitePersonne;
	
	@Column(name = "type_personne")
	private String typePersonne;
	
	@Column(name = "categorie_personne")
	private String categoriePersonne;
	
	@Column(name = "code_grade_personne")
	private String codeGradePersonne;
	
	@Column(name = "pourcentage_temps_travail_personne")
	private String pourcentageTempsTravailPersonne;
	
	@Column(name = "date_debut_rattachement_personne_ac")
	private Date dateDebutRattachementPersonneAc;
	
	@Column(name = "date_fin_rattachement_personne_ac")
	private Date dateFinRattachementPersonneAc;
	
	@Column(name = "pourcentage_participation_personne_ac")
	private int pourcentageParticipationPersonneAc;
	
	@Column(name = "pourcentage_disponibilite_personne")
	private int pourcentageDisponibilitePersonne;

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
     * Getter. 
     * @return
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
     * Getter. 
     * @return
     */
	public String getCodeUniteLeader() {
		return codeUniteLeader;
	}

    /**
     * Setter
     */
	public void setCodeUniteLeader(String codeUniteLeader) {
		this.codeUniteLeader = codeUniteLeader;
	}

	/**
     * Getter. 
     * @return
     */
	public String getSigleUniteLeader() {
		return sigleUniteLeader;
	}

    /**
     * Setter
     */
	public void setSigleUniteLeader(String sigleUniteLeader) {
		this.sigleUniteLeader = sigleUniteLeader;
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
	public String getListeDepartementCoPilote() {
		return listeDepartementCoPilote;
	}

    /**
     * Setter
     */
	public void setListeDepartementCoPilote(String listeDepartementCoPilote) {
		this.listeDepartementCoPilote = listeDepartementCoPilote;
	}

	/**
     * Getter. 
     * @return
     */
	public Date getDateAc() {
		return dateAc;
	}

    /**
     * Setter
     */
	public void setDateAc(Date dateAc) {
		this.dateAc = dateAc;
	}

	/**
     * Getter. 
     * @return
     */
	public Date getDateAcFinPrevue() {
		return dateAcFinPrevue;
	}

    /**
     * Setter
     */
	public void setDateAcFinPrevue(Date dateAcFinPrevue) {
		this.dateAcFinPrevue = dateAcFinPrevue;
	}

	/**
     * Getter. 
     * @return
     */
	public Date getDateAcFinEffective() {
		return dateAcFinEffective;
	}

    /**
     * Setter
     */
	public void setDateAcFinEffective(Date dateAcFinEffective) {
		this.dateAcFinEffective = dateAcFinEffective;
	}

	/**
     * Getter. 
     * @return
     */
	public String getMatriculePersonne() {
		return matriculePersonne;
	}

    /**
     * Setter
     */
	public void setMatriculePersonne(String matriculePersonne) {
		this.matriculePersonne = matriculePersonne;
	}

	/**
     * Getter. 
     * @return
     */
	public String getNomPersonne() {
		return nomPersonne;
	}

    /**
     * Setter
     */
	public void setNomPersonne(String nomPersonne) {
		this.nomPersonne = nomPersonne;
	}

	/**
     * Getter. 
     * @return
     */
	public String getPrenomPersonne() {
		return prenomPersonne;
	}

    /**
     * Setter
     */
	public void setPrenomPersonne(String prenomPersonne) {
		this.prenomPersonne = prenomPersonne;
	}

	/**
     * Getter. 
     * @return
     */
	public String getUnitePersonne() {
		return unitePersonne;
	}

    /**
     * Setter
     */
	public void setUnitePersonne(String unitePersonne) {
		this.unitePersonne = unitePersonne;
	}

	/**
     * Getter. 
     * @return
     */
	public String getTypePersonne() {
		return typePersonne;
	}

    /**
     * Setter
     */
	public void setTypePersonne(String typePersonne) {
		this.typePersonne = typePersonne;
	}
	
	/**
     * Getter. 
     * @return
     */
	public String getCategoriePersonne() {
		return categoriePersonne;
	}

    /**
     * Setter
     */
	public void setCategoriePersonne(String categoriePersonne) {
		this.categoriePersonne = categoriePersonne;
	}

	/**
     * Getter. 
     * @return
     */
	public String getCodeGradePersonne() {
		return codeGradePersonne;
	}

    /**
     * Setter
     */
	public void setCodeGradePersonne(String codeGradePersonne) {
		this.codeGradePersonne = codeGradePersonne;
	}

	/**
     * Getter. 
     * @return
     */
	public String getPourcentageTempsTravailPersonne() {
		return pourcentageTempsTravailPersonne;
	}

    /**
     * Setter
     */
	public void setPourcentageTempsTravailPersonne(
			String pourcentageTempsTravailPersonne) {
		this.pourcentageTempsTravailPersonne = pourcentageTempsTravailPersonne;
	}

	/**
     * Getter. 
     * @return
     */
	public Date getDateDebutRattachementPersonneAc() {
		return dateDebutRattachementPersonneAc;
	}

    /**
     * Setter
     */
	public void setDateDebutRattachementPersonneAc(
			Date dateDebutRattachementPersonneAc) {
		this.dateDebutRattachementPersonneAc = dateDebutRattachementPersonneAc;
	}

	/**
     * Getter. 
     * @return
     */
	public Date getDateFinRattachementPersonneAc() {
		return dateFinRattachementPersonneAc;
	}

    /**
     * Setter
     */
	public void setDateFinRattachementPersonneAc(
	        Date dateFinRattachementPersonneAc) {
		this.dateFinRattachementPersonneAc = dateFinRattachementPersonneAc;
	}

	/**
     * Getter. 
     * @return
     */
	public int getPourcentageParticipationPersonneAc() {
		return pourcentageParticipationPersonneAc;
	}

    /**
     * Setter
     */
	public void setPourcentageParticipationPersonneAc(
	        int pourcentageParticipationPersonneAc) {
		this.pourcentageParticipationPersonneAc = pourcentageParticipationPersonneAc;
	}

	/**
     * Getter. 
     * @return
     */
	public int getPourcentageDisponibilitePersonne() {
		return pourcentageDisponibilitePersonne;
	}

    /**
     * Setter
     */
	public void setPourcentageDisponibilitePersonne(
	        int pourcentageDisponibilitePersonne) {
		this.pourcentageDisponibilitePersonne = pourcentageDisponibilitePersonne;
	}

    public String getCodeCentre() {
        return codeCentre;
    }

    public void setCodeCentre(String codeCentre) {
        this.codeCentre = codeCentre;
    }

	public String getListeUniteParticipante() {
		return listeUniteParticipante;
	}

	public void setListeUniteParticipante(String listeUniteParticipante) {
		this.listeUniteParticipante = listeUniteParticipante;
	}
	
	
}
