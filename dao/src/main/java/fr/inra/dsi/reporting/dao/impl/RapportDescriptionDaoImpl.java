package fr.inra.dsi.reporting.dao.impl;

import java.util.ArrayList;
import java.util.List;

//import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.inra.dsi.reporting.dao.IRapportDescriptionDao;
import fr.inra.dsi.reporting.model.RapportDescription;

/**
 * Implémentation de IRapportDescriptionDao
 * @author gugau
 *
 */
@Service
public class RapportDescriptionDaoImpl implements IRapportDescriptionDao {

	@Autowired
	private transient SessionFactory sessionFactory;

	/**
	 * Constructeur.
	 */
	public RapportDescriptionDaoImpl() {
	    super();
    }
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<RapportDescription> findAll() {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria query = session.createCriteria(RapportDescription.class);
		List<?> tempList = query.list();
		return castToListRapportDesc(tempList);
	}
	
	/**
     * {@inheritDoc}
     */
    @Override
    public List<RapportDescription> findAllActive() {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria query = session.createCriteria(RapportDescription.class);
        query.add(Restrictions.eq("actif", true));
        List<?> tempList = query.list();
        return castToListRapportDesc(tempList);
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void saveOrUpdate(RapportDescription rapportDescription) {
		sessionFactory.getCurrentSession().saveOrUpdate(rapportDescription);
	}

	/**
     * {@inheritDoc}
     */
    @Override
    public void deleteRapportDescription(RapportDescription rapportDescription) {
        sessionFactory.getCurrentSession().delete(rapportDescription);
    }
    
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void saveAll(List<RapportDescription> rapportsDescription) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			for (RapportDescription rapportDescription : rapportsDescription) {
				session.save(rapportDescription);
			}
			commit(session);
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateRapportsActif(List<Integer> ids, boolean value) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			for (Integer id : ids) {
				RapportDescription rapportDescription = (RapportDescription) session
						.get(RapportDescription.class, id);
				rapportDescription.setActif(value);
				session.save(rapportDescription);
			}
			commit(session);
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	/**
	 * {@inheritDoc}
	 */
    @Override
    public List<RapportDescription> findByClazz(String clazz) {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria query = session.createCriteria(RapportDescription.class);
        query.add(Restrictions.eq("clazz", clazz));
        List<?> tempList = query.list();
        return castToListRapportDesc(tempList);
    }
    
    /**
	 * {@inheritDoc}
	 */
    @Override
    public RapportDescription findByClazzAndRestCall(String clazz, String restCall) {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria query = session.createCriteria(RapportDescription.class);
        query.add(Restrictions.eq("clazz", clazz)).add(Restrictions.eq("restCall", restCall));
        return (RapportDescription) query.uniqueResult();
    }
    
    /**
     * Commit transaction.
     * 
     * @param session
     */
    private void commit(Session session) {
        session.getTransaction().commit();
        session.flush();
        session.clear();
    }
    
    /**
     * Convertit une liste d'objets unknown en liste
     * d'objets RapportDescription.
     * A UTILISER AVEC ATTENTION !
     * 
     * @param tempList
     * @return {@link List}
     */
    private List<RapportDescription> castToListRapportDesc(List<?> tempList) {
        List<RapportDescription> rapports = new ArrayList<>();
        for (Object tempObj : tempList) {
            rapports.add((RapportDescription) (tempObj));
        }
        return rapports;
    }

}
