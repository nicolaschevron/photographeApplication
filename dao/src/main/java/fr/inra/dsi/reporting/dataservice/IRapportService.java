package fr.inra.dsi.reporting.dataservice;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import fr.inra.dsi.reporting.dto.SearchCriteriaDto;
import fr.inra.dsi.reporting.dto.SearchDto;
import fr.inra.dsi.reporting.model.IRapport;

/**
 * Interface du service de traitement
 * des objets de Rapport.
 * 
 * @author lepra
 *
 * @param <T>
 */
public interface IRapportService<T extends IRapport> {

    /**
     * Retourne la liste de tous les Rapports de type IRapport.
     * 
     * @param clazz Type du rapport.
     * @return List
     */
    @Transactional
    List<T> getAllRapports(Class<T> clazz);
    
    /**
     * Creation ou mise a jour d'un rapport de type
     * IRapport.
     * 
     * @param rapport
     */
    @Transactional
    void saveOrUpdate(T rapport);
    
    /**
     * Enregistrement d'une liste de rapport
     * de type IRapport.
     * 
     * @param rapports
     */
    void saveAll(List<T> rapports);
    
    /**
     * Purge de la table contenant les rapports
     * A appeler avant le lancement de l'import
     * @param clazz Type du rapport.
     */
	void deleteAll(Class<T> clazz);

    /**
     * Effectue une recherche (like) sur tous les attributs de l'entity
     * @param search les paramètres de recherche
     * @param clazz la classe de l'entity
     * @return
     */
    @Transactional(readOnly=true)
    List<T> search(SearchDto search, Class<T> clazz);
    
    /**
     * Effectue une recherche (like).
     * @param search les paramètres de recherche
     * @param clazz la classe de l'entity
     * @param propertyNames les attributs de l'entity sur lesquels faire la recherche 
     * @return
     */
    @Transactional(readOnly=true)
    List<T> search(SearchDto search, Class<T> clazz, List<String> propertyNames);
    
    /**
     * Renvoie des données filtrées sur un champ parmis une liste de valeur possible
     * @param clazz
     * @param propertyName le champ sur lequel filtrer
     * @param propertyValues les valeurs possibles
     * @return
     */
    @Transactional(readOnly=true)
    List<T> select(Class<T> clazz, String propertyName, List<?> propertyValues);
    
    /**
     * Renvoie les données filtrées en fonction des roles de l'utilisateurs
     * @param clazz
     * @param listCriteria
     * @return
     */
    @Transactional(readOnly=true)
    List<T> selectByCriteria(Class<T> clazz, List<SearchCriteriaDto> listCriteria);

    /**
     * Effectue une recherche (like) et filtre en fonction des roles de l'utilisateurs
     * @param search
     * @param clazz
     * @param propertyNames
     * @param listCriteria
     * @return
     */
    @Transactional(readOnly=true)
    List<T> searchByCriteria(Class<T> clazz, List<SearchCriteriaDto> listCriteria, SearchDto search, List<String> propertyNames);

}
