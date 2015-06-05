package fr.inra.dsi.reporting.dataservice.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import fr.inra.dsi.reporting.dao.IRapportDao;
import fr.inra.dsi.reporting.dataservice.IRapportService;
import fr.inra.dsi.reporting.dto.SearchCriteriaDto;
import fr.inra.dsi.reporting.dto.SearchDto;
import fr.inra.dsi.reporting.model.IRapport;

/**
 * Implementation du service de traitement
 * des objets de Rapport.
 * 
 * @author lepra
 *
 * @param <T>
 */
public class RapportServiceImpl<T extends IRapport> 
        implements IRapportService<T>{
    
    @Autowired
    protected transient IRapportDao<T> rapportDao;
    
    /**
     * Constructeur.
     */
    public RapportServiceImpl() {
        super();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> getAllRapports(Class<T> clazz) {
        return rapportDao.findAll(clazz);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveOrUpdate(T rapport) {
        rapportDao.saveOrUpdate(rapport);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void saveAll(List<T> rapports) {
        rapportDao.saveAll(rapports);
    }

    /**
     * {@inheritDoc}
     */
	@Override
	public void deleteAll(Class<T> clazz) {
		rapportDao.deleteAll(clazz);
	}

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> search(SearchDto search, Class<T> clazz) {
        return rapportDao.search(search, clazz);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> search(SearchDto search, Class<T> clazz,
            List<String> propertyNames) {
        return rapportDao.search(search, clazz, propertyNames);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> select(Class<T> clazz, String propertyName,
            List<?> propertyValues) {
        return rapportDao.select(clazz, propertyName, propertyValues);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> selectByCriteria(Class<T> clazz, List<SearchCriteriaDto> listCriteria) {
        return rapportDao.selectByCriteria(clazz, listCriteria);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> searchByCriteria(
            Class<T> clazz,
            List<SearchCriteriaDto> listCriteria, SearchDto search,
            List<String> propertyNames) {
        return rapportDao.searchByCriteria(search, clazz, listCriteria, propertyNames);
    }
    
}
