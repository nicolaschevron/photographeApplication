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
 * Classe Model/Entity pour les rapports ChampFonctionnel par activite.
 * 
 * @author niche
 */
@Entity
@Table(name = "RAPPORT_CHAMP_FONCTIONNEL_PAR_ACTIVITE")
public class RapportChampsFonctionnelParActivite implements IRapport {
	
		public static final int LIBELLE_LIMIT = 1000;
	
		@Id
	    @Column(name = "id")
	    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rapport_champ_fonctionnel_par_activite_seq_gen")
	    @SequenceGenerator(name = "rapport_champ_fonctionnel_par_activite_seq_gen", sequenceName = "rapport_champ_fonctionnel_par_activite_id_seq")
	    private int identifiant;
	
		@Column(name = "code_champs_fonctionnel")
		private String codeChampsFonctionnel;
		
		@Column(name = "code_sous_champs_fonctionnel")
		private String codeSousChampsFonctionnel;
		
		@Column(name = "type_champs_fonctionnel")
		private String typeChampsFonctionnel;
	 
	 	@Column(name = "code_activite_collective")
		private String cfCodeActiviteCollective;
	    
	    @Column(name = "abreviation_type_ac")
		private String abreviationTypeAc;
		
	    @Column(name = "libelle_ac", length = LIBELLE_LIMIT)
		private String cfLibelleAc;
		
		@Column(name = "date_ac_debut")
		// equivalent date_debut
		private Date cfDateAc;
		
		@Column(name = "date_ac_fin_prevue")
		private Date cfDateAcFinPrevue;
		
		@Column(name = "date_ac_fin_effective")
		private Date cfDateAcFinEffective;
		
		@Column(name = "code_unite")
		private String codeUnite;
		
		@Column(name = "sigle_unite")
		private String cfSigleUnite;
		
		@Column(name = "nom_unite")
	    private String cfNomUnite;
		
		@Column(name = "type_unite")
		private String cfTypeUnite;
		
		@Column(name = "type_participation")
		private String cfTypeParticipation;
		
		@Column(name = "sigle_autorite_leader")
		private String cfSigleAutoriteLeader;
	 
	 	@Column(name = "code_departement")
		private String codiqueDepartement;
	 
	 	@Column(name = "liste_departement_copilote")
		private String listeDepartementCoPilote;
		
		@Column(name = "code_centre")
		private String codeCentre;

		/**
		 * Constructeur.
		 */
		public RapportChampsFonctionnelParActivite() {
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
		public String getCodeChampsFonctionnel() {
			return codeChampsFonctionnel;
		}

		/**
	     * Setter
	     */
		public void setCodeChampsFonctionnel(String codeChampsFonctionnel) {
			this.codeChampsFonctionnel = codeChampsFonctionnel;
		}

		/**
	     * Getter. 
	     * @return
	     */
		public String getCodeSousChampsFonctionnel() {
			return codeSousChampsFonctionnel;
		}

		/**
	     * Setter
	     */
		public void setCodeSousChampsFonctionnel(String codeSousChampsFonctionnel) {
			this.codeSousChampsFonctionnel = codeSousChampsFonctionnel;
		}

		/**
	     * Getter. 
	     * @return
	     */
		public String getTypeChampsFonctionnel() {
			return typeChampsFonctionnel;
		}

		/**
	     * Setter
	     */
		public void setTypeChampsFonctionnel(String typeChampsFonctionnel) {
			this.typeChampsFonctionnel = typeChampsFonctionnel;
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
		public String getCfCodeActiviteCollective() {
			return cfCodeActiviteCollective;
		}

		/**
	     * Setter
	     */
		public void setCfCodeActiviteCollective(String cfCodeActiviteCollective) {
			this.cfCodeActiviteCollective = cfCodeActiviteCollective;
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
		public String getCfLibelleAc() {
			return cfLibelleAc;
		}

		/**
	     * Setter
	     */
		public void setCfLibelleAc(String cfLibelleAc) {
			this.cfLibelleAc = cfLibelleAc;
		}

		/**
	     * Getter. 
	     * @return
	     */
		public Date getCfDateAc() {
			return cfDateAc;
		}

		/**
	     * Setter
	     */
		public void setCfDateAc(Date cfDateAc) {
			this.cfDateAc = cfDateAc;
		}

		/**
	     * Getter. 
	     * @return
	     */
		public Date getCfDateAcFinPrevue() {
			return cfDateAcFinPrevue;
		}

		/**
	     * Setter
	     */
		public void setCfDateAcFinPrevue(Date cfDateAcFinPrevue) {
			this.cfDateAcFinPrevue = cfDateAcFinPrevue;
		}

		/**
	     * Getter. 
	     * @return
	     */
		public Date getCfDateAcFinEffective() {
			return cfDateAcFinEffective;
		}

		/**
	     * Setter
	     */
		public void setCfDateAcFinEffective(Date cfDateAcFinEffective) {
			this.cfDateAcFinEffective = cfDateAcFinEffective;
		}

		/**
	     * Getter. 
	     * @return
	     */
		public String getCfSigleUnite() {
			return cfSigleUnite;
		}

		/**
	     * Setter
	     */
		public void setCfSigleUnite(String cfSigleUnite) {
			this.cfSigleUnite = cfSigleUnite;
		}

		/**
	     * Getter. 
	     * @return
	     */
		public String getCfNomUnite() {
			return cfNomUnite;
		}

		/**
	     * Setter
	     */
		public void setCfNomUnite(String cfNomUnite) {
			this.cfNomUnite = cfNomUnite;
		}

		/**
	     * Getter. 
	     * @return
	     */
		public String getCfTypeUnite() {
			return cfTypeUnite;
		}

		/**
	     * Setter
	     */
		public void setCfTypeUnite(String cfTypeUnite) {
			this.cfTypeUnite = cfTypeUnite;
		}

		/**
	     * Getter. 
	     * @return
	     */
		public String getCfTypeParticipation() {
			return cfTypeParticipation;
		}

		/**
	     * Setter
	     */
		public void setCfTypeParticipation(String cfTypeParticipation) {
			this.cfTypeParticipation = cfTypeParticipation;
		}

		/**
	     * Getter. 
	     * @return
	     */
		public String getCfSigleAutoriteLeader() {
			return cfSigleAutoriteLeader;
		}

		/**
	     * Setter
	     */
		public void setCfSigleAutoriteLeader(String cfSigleAutoriteLeader) {
			this.cfSigleAutoriteLeader = cfSigleAutoriteLeader;
		}
	
	
}
