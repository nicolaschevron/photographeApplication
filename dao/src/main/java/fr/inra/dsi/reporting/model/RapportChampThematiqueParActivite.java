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
 * Classe Model/Entity pour les rapports ChampThematique.
 * 
 * @author lepra
 */
@Entity
@Table(name = "RAPPORT_CHAMP_THEMATIQUE_PAR_ACTIVITE")
public class RapportChampThematiqueParActivite implements IRapport {

	public static final int LIBELLE_LIMIT = 1000;
	
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rapport_champ_thematique_par_activite_seq_gen")
    @SequenceGenerator(name = "rapport_champ_thematique_par_activite_seq_gen", sequenceName = "rapport_champ_thematique_par_activite_id_seq")
    private int identifiant;
	
	@Column(name = "code_ct")
    private int codeCt;
    
	@Column(name = "sigle_ct")
    private String sigleCt;
    
	@Column(name = "sigle_num")
    private int sigleNum;
	
    @Column(name = "sigle_num_ct")
    private String sigleNumCt;
    
    @Column(name = "date_debut_rattachement")
    private Date dateDebutRattachement;
    
    @Column(name = "date_fin_rattachement")
    private Date dateFinRattachement;
    
    @Column(name = "code_activite_collective")
	private String codeActiviteCollective;
    
    @Column(name = "abreviation_type_ac")
	private String abreviationTypeAc;
	
    @Column(name = "libelle_ac", length = LIBELLE_LIMIT)
	private String libelleAc;
	
	@Column(name = "date_ac_debut")
	// equivalent date_debut
	private Date dateAc;
	
	@Column(name = "date_ac_fin_prevue")
	private Date dateAcFinPrevue;
	
	@Column(name = "date_ac_fin_effective")
	private Date dateAcFinEffective;
	
	@Column(name = "code_unite")
	private String codeUnite;
	
	@Column(name = "sigle_unite")
	private String sigleUnite;
	
	@Column(name = "nom_unite")
    private String nomUnite;
	
	@Column(name = "type_unite")
	private String typeUnite;
	
	@Column(name = "type_participation")
	private String typeParticipation;
	
	@Column(name = "sigle_autorite_leader")
	private String sigleAutoriteLeader;    
	
	@Column(name = "pourcentage_rattachement_autorite_leader")
	private int pourcentageRattachementAutorite;
	
	@Column(name = "code_departement")
	private String codiqueDepartement;
	
	@Column(name = "liste_departement_copilote")
	private String listeDepartementCoPilote;
	
	@Column(name = "code_centre")
	private String codeCentre;
	
	/**
	 * Constructeur.
	 */
	public RapportChampThematiqueParActivite() {
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
	public int getCodeCt() {
		return codeCt;
	}

    /**
     * Setter
     */
	public void setCodeCt(int codeCt) {
		this.codeCt = codeCt;
	}
	
	/**
     * Getter. 
     * @return
     */
	public String getSigleCt() {
		return sigleCt;
	}

    /**
     * Setter
     */
	public void setSigleCt(String sigleCt) {
		this.sigleCt = sigleCt;
	}

	/**
     * Getter. 
     * @return
     */
	public String getSigleNumCt() {
		return sigleNumCt;
	}

    /**
     * Setter
     */
	public void setSigleNumCt(String sigleNumCt) {
		this.sigleNumCt = sigleNumCt;
	}

	/**
     * Getter. 
     * @return
     */
	public Date getDateDebutRattachement() {
		return dateDebutRattachement;
	}

    /**
     * Setter
     */
	public void setDateDebutRattachement(Date dateDebutRattachement) {
		this.dateDebutRattachement = dateDebutRattachement;
	}

	/**
     * Getter. 
     * @return
     */
	public Date getDateFinRattachement() {
		return dateFinRattachement;
	}

    /**
     * Setter
     */
	public void setDateFinRattachement(Date dateFinRattachement) {
		this.dateFinRattachement = dateFinRattachement;
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
	public String getSigleAutoriteLeader() {
		return sigleAutoriteLeader;
	}

	/**
     * Setter
     */
	public void setSigleAutoriteLeader(String sigleAutoriteLeader) {
		this.sigleAutoriteLeader = sigleAutoriteLeader;
	}

	/**
     * Getter. 
     * @return
     */
	public int getPourcentageRattachementAutorite() {
		return pourcentageRattachementAutorite;
	}

	/**
     * Setter
     */
	public void setPourcentageRattachementAutorite(
			int pourcentageRattachementAutorite) {
		this.pourcentageRattachementAutorite = pourcentageRattachementAutorite;
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

	public String getCodeCentre() {
		return codeCentre;
	}

	public void setCodeCentre(String codeCentre) {
		this.codeCentre = codeCentre;
	}

    public String getNomUnite() {
        return nomUnite;
    }

    public void setNomUnite(String nomUnite) {
        this.nomUnite = nomUnite;
    }

	public int getSigleNum() {
		return sigleNum;
	}

	public void setSigleNum(int sigleNum) {
		this.sigleNum = sigleNum;
	}

	public String getTypeParticipation() {
		return typeParticipation;
	}

	public void setTypeParticipation(String typeParticipation) {
		this.typeParticipation = typeParticipation;
	}
    
    
}
