package fr.inra.dsi.reporting.dataservice.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.inra.dsi.reporting.dao.IRapportDescriptionDao;
import fr.inra.dsi.reporting.dataservice.IRapportDescriptionService;
import fr.inra.dsi.reporting.model.RapportDescription;

/**
 * Implémentation de IRapportDescriptionService
 * @author gugau
 *
 */
@Service
public class RapportDescriptionServiceImpl  implements IRapportDescriptionService {

	@Autowired
	protected transient IRapportDescriptionDao rapportDescriptionDao;

	/**
	 * Constructeur.
	 */
	public RapportDescriptionServiceImpl(){
	    super();
	}
	
	/**
     * {@inheritDoc}
     */
    @Override
    public List<RapportDescription> getAllRapportDescriptions() {
        return rapportDescriptionDao.findAll();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<RapportDescription> getAllActiveRapportDescriptions() {
        return rapportDescriptionDao.findAllActive();
    }
	
    /**
     * {@inheritDoc}
     */
	@Override
	public void saveOrUpdate(RapportDescription rapportDescription) {
		rapportDescriptionDao.saveOrUpdate(rapportDescription);
		
	}
	
    /**
     * {@inheritDoc}
     */
	@Override
	public void saveAll(List<RapportDescription> rapportsDescription) {
		rapportDescriptionDao.saveAll(rapportsDescription);
	}

    /**
     * {@inheritDoc}
     */	
	@Override
	public void updateRapportsActif(List<Integer> ids, boolean value) {
		rapportDescriptionDao.updateRapportsActif(ids, value);
	}

	/**
	 * {@inheritDoc}
	 */
    @Override
    public List<RapportDescription> getAllRapportDescriptionsForClazz(
            String clazz) {
        return rapportDescriptionDao.findByClazz(clazz);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(RapportDescription rapportDescription) {
        rapportDescriptionDao.deleteRapportDescription(rapportDescription);
    }
    
    /**
	 * {@inheritDoc}
	 */
    @Override
    public RapportDescription getRapportDescriptionForClazzAndRestCall(
            String clazz, String restCall) {
        return rapportDescriptionDao.findByClazzAndRestCall(clazz, restCall);
    }

}
