package fr.inra.dsi.reporting.dto;

import java.util.List;

import fr.inra.dsi.reporting.dto.RapportColonne;
import fr.inra.dsi.reporting.model.RapportDescription;



/**
 * Model envoye a la vue permettant l affichage du tableau
 * @author niche
 *
 */
public class RapportDto {

	
	/**
	 * lignes du rapport (contiend l objet persiste en BDD) 
	 */
	private List<?> lignesRapport;
	
	/**
	 * colonnes du rapport
	 */
	private List<RapportColonne> colonnesRapport; 
	
	/**
	 * rapport description
	 */
	private RapportDescription rapportDescription;
	
	/**
	 * Constructeur.
	 */
	public RapportDto() {
		super();
	}


	public List<?> getLignesRapport() {
		return lignesRapport;
	}


	public void setLignesRapport(List<?> lignesRapport) {
		this.lignesRapport = lignesRapport;
	}


	public List<RapportColonne> getColonnesRapport() {
		return colonnesRapport;
	}


	public void setColonnesRapport(List<RapportColonne> colonnesRapport) {
		this.colonnesRapport = colonnesRapport;
	}


	public RapportDescription getRapportDescription() {
		return rapportDescription;
	}


	public void setRapportDescription(RapportDescription rapportDescription) {
		this.rapportDescription = rapportDescription;
	}
}
