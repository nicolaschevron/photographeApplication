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
 * Classe Model/Entity pour les rapports ListeActivites enrichie avec les unites
 * 
 * @author cebri
 */
@Entity
@Table(name = "RAPPORT_LISTE_ACTIVITES_PAR_UNITE")
public class RapportListeActivitesParUnite implements IRapport {

    public static final int LIBELLE_LIMIT = 1000;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rapport_liste_activites_par_unite_seq_gen")
	@SequenceGenerator(name = "rapport_liste_activites_par_unite_seq_gen", sequenceName = "rapport_liste_activites_par_unite_id_seq")
	private int identifiant;
	
	@Column(name = "code_activite_collective")
	private String codeActiviteCollective;
	
	@Column(name = "libelle_ac", length = LIBELLE_LIMIT)
	private String libelleAc;
	
	@Column(name = "libelle_type_ac")
	private String libelleTypeAc;
	
	@Column(name = "abreviation_type_ac")
	private String abreviationTypeAc;

	@Column(name = "date")
	private Date date;

	@Column(name = "date_fin_prevue")
	private Date dateFinPrevue;

	@Column(name = "date_fin_effective")
	private Date dateFinEffective;
	
	@Column(name = "code_unite")
	private String codeUnite;
	
	@Column(name = "sigle_unite")
	private String sigleUnite;
	
	@Column(name = "type_unite")
	private String typeUnite;
	
	@Column(name = "caracteristique_unite")
	private String caracteristiqueUnite;
	
	@Column(name = "unite_leader")
	private Boolean uniteLeader;
	
	@Column(name = "codique_departement")
	private String codiqueDepartement;
	
	@Column(name = "intitule_departement")
	private String intituleDepartement;
	
	@Column(name = "sigle_departement")
	private String sigleDepartement;
	
	@Column(name = "code_centre")
	private String codeCentre;
	
	@Column(name = "libelle_centre")
	private String libelleCentre;
	
	@Column(name = "sigle_centre")
	private String sigleCentre;
	
	@Column(name = "site_principal_centre")
	private String sitePrincipalCentre;
	
	@Column(name = "liste_departement_copilote")
	private String listeDepartementCoPilote;

	@Column(name = "matricule_animateur_principal")
	private String matriculeAp;

	@Column(name = "nom_animateur_principal")
	private String nomAp;

	@Column(name = "prenom_animateur_principal")
	private String prenomAp;

	@Column(name = "grade_animateur_principal")
	private String gradeAp;

	@Column(name = "type_personne_animateur_principal")
	private String typePersonneAp;

	@Column(name = "pourcentage_temps_travail_animateur_principal")
	private String pourcentageTempsTravailAp;

	@Column(name = "mail_animateur_principal")
	private String mailAp;

	@Column(name = "date_nomination_animateur_principal")
	private Date dateNominationAp;
	
	/**
	 * Constructeur.
	 */
	public RapportListeActivitesParUnite() {
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
	    if ((libelleAc != null) && (libelleAc.length() > LIBELLE_LIMIT)) {
            this.libelleAc = libelleAc.substring(0, LIBELLE_LIMIT);
        } else {
            this.libelleAc = libelleAc;
        }
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
     * Setter
     */
	public void setAbreviationTypeAc(String abreviationTypeAc) {
		this.abreviationTypeAc = abreviationTypeAc;
	}
	
    /**
     * Getter. 
     * @return
     */	
	public String getAbreviationTypeAc() {
		return abreviationTypeAc;
	}
	
    /**
     * Getter. 
     * @return
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
     * Getter. 
     * @return
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
     * Getter. 
     * @return
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

    /**
     * Getter. 
     * @return
     */
	public String getSigleUnite() {
		return sigleUnite;
	}

    /**
     * Setter
     */
	public void setSigleUnite(String sigleUnite) {
		this.sigleUnite = sigleUnite;
	}

    /**
     * Getter. 
     * @return
     */
	public String getTypeUnite() {
		return typeUnite;
	}

    /**
     * Setter
     */
	public void setTypeUnite(String typeUnite) {
		this.typeUnite = typeUnite;
	}

    /**
     * Getter. 
     * @return
     */
	public String getCaracteristiqueUnite() {
		return caracteristiqueUnite;
	}

    /**
     * Setter
     */
	public void setCaracteristiqueUnite(String caracteristiqueUnite) {
		this.caracteristiqueUnite = caracteristiqueUnite;
	}

    /**
     * Getter. 
     * @return
     */
	public Boolean isUniteLeader() {
		return uniteLeader;
	}

    /**
     * Setter
     */
	public void setUniteLeader(Boolean uniteLeader) {
		this.uniteLeader = uniteLeader;
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
	public String getIntituleDepartement() {
		return intituleDepartement;
	}

    /**
     * Setter
     */
	public void setIntituleDepartement(String intituleDepartement) {
		this.intituleDepartement = intituleDepartement;
	}

    /**
     * Getter. 
     * @return
     */
	public String getSigleDepartement() {
		return sigleDepartement;
	}

    /**
     * Setter
     */
	public void setSigleDepartement(String sigleDepartement) {
		this.sigleDepartement = sigleDepartement;
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
	public String getLibelleCentre() {
		return libelleCentre;
	}

    /**
     * Setter
     */
	public void setLibelleCentre(String libelleCentre) {
		this.libelleCentre = libelleCentre;
	}

    /**
     * Getter. 
     * @return
     */
	public String getSigleCentre() {
		return sigleCentre;
	}

    /**
     * Setter
     */
	public void setSigleCentre(String sigleCentre) {
		this.sigleCentre = sigleCentre;
	}

    /**
     * Getter. 
     * @return
     */
	public String getSitePrincipalCentre() {
		return sitePrincipalCentre;
	}

    /**
     * Setter
     */
	public void setSitePrincipalCentre(String sitePrincipalCentre) {
		this.sitePrincipalCentre = sitePrincipalCentre;
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
	public String getMatriculeAp() {
		return matriculeAp;
	}

	/**
     * Setter
     */
	public void setMatriculeAp(String matriculeAp) {
		this.matriculeAp = matriculeAp;
	}

    /**
     * Getter. 
     * @return
     */
	public String getNomAp() {
		return nomAp;
	}

	/**
     * Setter
     */
	public void setNomAp(String nomAp) {
		this.nomAp = nomAp;
	}

    /**
     * Getter. 
     * @return
     */
	public String getPrenomAp() {
		return prenomAp;
	}

	/**
     * Setter
     */
	public void setPrenomAp(String prenomAp) {
		this.prenomAp = prenomAp;
	}

    /**
     * Getter. 
     * @return
     */
	public String getGradeAp() {
		return gradeAp;
	}

	/**
     * Setter
     */
	public void setGradeAp(String gradeAp) {
		this.gradeAp = gradeAp;
	}

    /**
     * Getter. 
     * @return
     */
	public String getTypePersonneAp() {
		return typePersonneAp;
	}

	/**
     * Setter
     */
	public void setTypePersonneAp(String typePersonneAp) {
		this.typePersonneAp = typePersonneAp;
	}

    /**
     * Getter. 
     * @return
     */
	public String getPourcentageTempsTravailAp() {
		return pourcentageTempsTravailAp;
	}

	/**
     * Setter
     */
	public void setPourcentageTempsTravailAp(String pourcentageTempsTravailAp) {
		this.pourcentageTempsTravailAp = pourcentageTempsTravailAp;
	}

    /**
     * Getter. 
     * @return
     */
	public String getMailAp() {
		return mailAp;
	}

	/**
     * Setter
     */
	public void setMailAp(String mailAp) {
		this.mailAp = mailAp;
	}

    /**
     * Getter. 
     * @return
     */
	public Date getDateNominationAp() {
		return dateNominationAp;
	}

	/**
     * Setter
     */
	public void setDateNominationAp(Date dateNominationAp) {
		this.dateNominationAp = dateNominationAp;
	}
	
	
}
