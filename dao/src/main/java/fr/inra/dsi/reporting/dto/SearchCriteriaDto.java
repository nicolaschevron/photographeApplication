package fr.inra.dsi.reporting.dto;

import java.util.List;
/**
 * DTO permettant de constuire une liste de criteres a appliquer lors de la 
 * recuperation des rapports en fonction des roles de l'utilisateur
 * field correspond au nom du champ dans la base de donnees
 * codes est une liste contenant les valleurs a filter
 * @author cebri
 *
 */
public class SearchCriteriaDto {

	private String field;
	private List<String> codes;
	private boolean isLike;
	
	/**
	 * Constructeur.
	 */
	public SearchCriteriaDto() {
		super();
	}

	/**
	 * Constructeur.
	 * @param field
	 * @param codes
	 */
	public SearchCriteriaDto(String field, List<String> codes) {
		super();
		this.field = field;
		this.codes = codes;
	}
	
	public SearchCriteriaDto(String field, List<String> codes, boolean isLike) {
		super();
		this.field = field;
		this.codes = codes;
		this.isLike = isLike;
	}

	/**
	 * Getter.
	 */
	public String getField() {
		return field;
	}

	/**
     * Setter.
     */
	public void setField(String field) {
		this.field = field;
	}

	/**
     * Getter.
     */
	public List<String> getCodes() {
		return codes;
	}

	/**
     * Setter.
     */
    public void setCodes(List<String> codes) {
		this.codes = codes;
	}

    /**
     * getter
     */
	public boolean isLike() {
		return isLike;
	}

	/**
	 * setter
	 */
	public void setLike(boolean isLike) {
		this.isLike = isLike;
	}
	
	
}
