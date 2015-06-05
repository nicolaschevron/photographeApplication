package fr.inra.dsi.reporting.dataservice;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import fr.inra.dsi.reporting.model.RapportDescription;

/**
 * Interface d'acces au RapportDescriptionService.
 * 
 * @author cebri
 */
public interface IRapportDescriptionService {
	/**
	 * Retourne la liste de toutes les descriptions des rapports.
	 * 
	 * @return {@link List}
	 */
	@Transactional
	List<RapportDescription> getAllRapportDescriptions();

	/**
	 * Retourne la liste de toutes les descriptions des rapports
	 * dont la clazz est 'clazz'.
	 * 
	 * @param clazz
	 * @return {@link List}
	 */
	@Transactional
	List<RapportDescription> getAllRapportDescriptionsForClazz(String clazz);
	
	/**
	 * Creation ou mise a jour d'une description de rapport
	 * 
	 * @param rapportDescription
	 */
	@Transactional
	void saveOrUpdate(RapportDescription rapportDescription);

	/**
	 * Supprime un rapport description.
	 * 
	 * @param rapportDescription
	 */
	@Transactional
	void delete(RapportDescription rapportDescription);
	
	/**
	 * Enregistrement d'une liste de description de rapport
	 * 
	 * @param rapportsDescription
	 */
	void saveAll(List<RapportDescription> rapportsDescription);

	/**
	 * Active/desactive une liste de rapport
	 * 
	 * @param ids Liste des id des rapport a activer/desactiver
	 * @param value
	 */
	void updateRapportsActif(List<Integer> ids, boolean value);

	/**
	 * Retourne le rapport dont la clazz est 'clazz' et l appel REST est restCall.
	 * 
	 * @param clazz
	 * @param restCall
	 * @return {@link RapportDescription}
	 */
	@Transactional
	RapportDescription getRapportDescriptionForClazzAndRestCall(
			String clazz, String restCall);

	/**
	 * Retourne la liste de toutes les descriptions des rapports actifs.
	 * 
	 * @return {@link List}
	 */
	@Transactional
    List<RapportDescription> getAllActiveRapportDescriptions();

}
