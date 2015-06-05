package fr.inra.dsi.reporting.dao;

import java.util.List;

import fr.inra.dsi.reporting.model.RapportDescription;

/**
 * Interface du DAO pour les RapportDescription
 * @author gugau
 *
 */
public interface IRapportDescriptionDao {

    /**
     * Retourne les RapportDescriptions dont la classe est clazz.
     * @param clazz
     * @return {@link List}
     */
    List<RapportDescription> findByClazz(String clazz);
    
	/**
	 * Retourne toutes les occurrences de type RapportDescription.
	 * 
	 * @return {@link List}
	 */
	List<RapportDescription> findAll();

	/**
	 * Enregistre ou met a jour une description de rapport dans l'unite de
	 * persistence.
	 * 
	 * @param rapportDescription
	 */
	void saveOrUpdate(RapportDescription rapportDescription);

	/**
	 * Enregistre un lot de description de rapport en suivant une strategie de
	 * mise a jour par lot.
	 * 
	 * @param rapportsDescription
	 */
	void saveAll(List<RapportDescription> rapportsDescription);

	/**
	 * Active/desactive une liste de rapport
	 * 
	 * @param ids Liste des id des rapports a activer/desactiver
	 * @param value
	 */
	void updateRapportsActif(List<Integer> ids, boolean value);
	
	/**
	 * Supprime un rapportDescription.
	 * 
	 * @param rapportDescription
	 */
	void deleteRapportDescription(RapportDescription rapportDescription);

	/**
	 * Retourne le RapportDescription dont la classe est clazz et l appel REST est restCall.
	 * 
	 * @param clazz
	 * @param restCall
	 * @return {@link RapportDescription}
	 */
	RapportDescription findByClazzAndRestCall(String clazz, String restCall);

	/**
     * Retourne les RapportDescriptions de rapports actifs..
     * 
     * @return {@link List}
     */
    List<RapportDescription> findAllActive();

}
