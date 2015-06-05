package fr.inra.dsi.reporting.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import fr.inra.dsi.reporting.dto.SearchCriteriaDto;
import fr.inra.dsi.reporting.dto.SearchDto;
import fr.inra.dsi.reporting.model.IRapport;

/**
 * Interface de base de tous les DAO
 * traitant des objets de type IRapport.
 * 
 * @author lepra
 *
 * @param <T>
 */
public interface IRapportDao<T extends IRapport> {

    /**
     * Retourne toutes les occurrences du rapport de type T.
     * 
     * @param clazz Type de la classe.
     * @return List
     */
    List<T> findAll(Class<T> clazz);
    
    /**
     * Enregistre ou met a jour un rapport
     * dans l'unite de persistence.
     * 
     * @param rapport
     */
    void saveOrUpdate(T rapport);
    
    /**
     * Enregistre un lot de rapport en suivant une
     * strategie de mise a jour par lot.
     * 
     * @param rapports
     */
    void saveAll(List<T> rapports);
    
    /**
     * Purge de la table contenant les rapports
     * A appeler avant le lancement de l'import
     * 
     *  @param clazz Type de la classe.
     */
    void deleteAll(Class<T> clazz);
    
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
     * @see #search(SearchDto, Class, List)
     * @see #select(Class, String, List)
     * @param search
     * @param clazz
     * @param listCriteria
     * @param propertyNames
     * @return
     */
    @Transactional(readOnly=true)
    List<T> searchByCriteria(SearchDto search, Class<T> clazz, List<SearchCriteriaDto> listCriteria, List<String> propertyNames);

    /**
     * Renvoie des données filtrées sur un ou plusieurs champs en fonction des roles de l'utilisateur
     * @param clazz
     * @param listCriteria
     * @return
     */
    @Transactional(readOnly=true)
	List<T> selectByCriteria(Class<T> clazz, List<SearchCriteriaDto> listCriteria);
}
