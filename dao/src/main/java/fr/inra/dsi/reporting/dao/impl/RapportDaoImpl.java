package fr.inra.dsi.reporting.dao.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Table;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import fr.inra.dsi.reporting.dao.IRapportDao;
import fr.inra.dsi.reporting.dto.SearchCriteriaDto;
import fr.inra.dsi.reporting.dto.SearchDto;
import fr.inra.dsi.reporting.dto.SearchOrder;
import fr.inra.dsi.reporting.model.IRapport;

/**
 * Classe générique
 * @author lepra
 *
 * @param <T>
 */
@Repository
public class RapportDaoImpl<T extends IRapport> implements IRapportDao<T> {

    private static final int BATCH_LIMIT = 1000;
    
    private static final Logger LOGGER = Logger.getLogger(RapportDaoImpl.class);
    
    @Autowired
    private transient SessionFactory sessionFactory;

    /**
     * Constructeur.
     */
    public RapportDaoImpl() {
        super();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> findAll(Class<T> clazz) {
        List<T> rapports = new ArrayList<>();
        Session session = this.sessionFactory.getCurrentSession();
        Criteria query = session.createCriteria(clazz);
        List<?> tempList = query.list();
        for (Object tempObj : tempList) {
            rapports.add(clazz.cast(tempObj));
        }

        return rapports;
    }    
   
    /**
     * {@inheritDoc}
     */
    @Override
    public void saveOrUpdate(T rapport) {
        sessionFactory.getCurrentSession().saveOrUpdate(rapport);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void saveAll(final List<T> rapports) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();            
            int processCnt = 0;
            for (T rapport : rapports) {
                session.save(rapport);
                if (processCnt % BATCH_LIMIT == 0) {
                    commit(session);
                    session.beginTransaction();
                }
                processCnt = processCnt + 1;
            }
            commit(session);
        } finally {
            if (session != null) {
                session.close();
            }
        }
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
     * {@inheritDoc}
     */
	@Override
	public void deleteAll(Class<T> clazz) {
		Session session = null;
		Table table = clazz.getAnnotation(Table.class);
		String tableName = table.name();
		LOGGER.info("Purge de la table " + tableName);
		try {
			session = sessionFactory.openSession();
	        session.beginTransaction(); 
	        String sql ="truncate table " + tableName;
			LOGGER.info("Execution de la requete : " + sql);
			Query  query=session.createSQLQuery(sql);
			query.executeUpdate();
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
    public List<T> search(SearchDto search, Class<T> clazz) {
        return search(search, clazz, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> search(SearchDto search, Class<T> clazz, List<String> propertyNames) {
        List<T> rapports = new ArrayList<>();
        Session session = this.sessionFactory.getCurrentSession();
        Criteria query = createSearchCriterion(session, search, clazz, propertyNames);
        
        List<?> tempList = query.list();
        for (Object tempObj : tempList) {
            rapports.add(clazz.cast(tempObj));
        }

        return rapports;
    }
    
    private Criteria createSearchCriterion(Session session, SearchDto search, Class<T> clazz, List<String> propertyNames){
        Criteria query = session.createCriteria(clazz);
        
        if(!StringUtils.isEmpty(search.getKeywords())){
            String[] keywords = search.getKeywords().split(" ");
            List<Criterion> andCriterion = new ArrayList<Criterion>();
            for(String kwd : keywords){
                if(propertyNames == null){
                    andCriterion.add(createCriterionFromType(kwd, clazz));
                } else {
                    andCriterion.add(createCriterionFromPropterties(kwd, clazz, propertyNames));
                }
            }
            Criterion[] tmp = new Criterion[andCriterion.size()];
            query = query.add(Restrictions.and(andCriterion.toArray(tmp)));
        }
        
        if(!StringUtils.isEmpty(search.getOrderField()) && search.getOrder() != null){
            Order order;
            if(search.getOrder().equals(SearchOrder.ASC)){
                order = Order.asc(search.getOrderField()); 
            } else {
                order = Order.desc(search.getOrderField());
            }
            query.addOrder(order);
        }
        return query;
    }
    
    /**
     * Crée un Criterion pour un mot clé de recherche sur certains attributs de l'entity.
     * @param kwd le mot clé
     * @param clazz
     * @param propertyNames les attributs sur lesquels faire la recherche
     * @return
     */
    private Disjunction createCriterionFromPropterties(String kwd, Class<T> clazz, List<String> propertyNames) {
        List<Criterion> oneKeywordCriterion = new ArrayList<Criterion>();
        for (String propName : propertyNames) {
            try {
                Field field = clazz.getDeclaredField(propName);

                if (field.getType() == String.class) {
                    oneKeywordCriterion.add(Restrictions.ilike(field.getName(), "%"+kwd +"%"));
                } else {
                    Column colAnno = field.getAnnotation(Column.class);
                    String colName;
                    if ((colAnno == null) || (colAnno.name() == null)) {
                        colName = field.getName();
                    } else {
                        colName = colAnno.name();
                    }
                    oneKeywordCriterion.add(Restrictions.sqlRestriction(colName+"::text ilike '%"+kwd.replace("'", "\\'")+"%'"));
                }
            } catch (NoSuchFieldException | SecurityException e) {
                LOGGER.error("Propriété '"+propName+"' inexistante sur la classe "+clazz.getName(), e);
            }
        }
        Criterion[] tmp = new Criterion[oneKeywordCriterion.size()];
        return Restrictions.or(oneKeywordCriterion.toArray(tmp));
    }
    
    /**
     * Crée un Criterion pour un mot clé de recherche.
     * 
     * Le Criterion est un OR sur tous les attributs de l'entity
     * @param kwd le mot clé
     * @param clazz
     * @return
     */
    private Disjunction createCriterionFromType(String kwd, Class<T> clazz){
        List<Criterion> oneKeywordCriterion = new ArrayList<Criterion>();
        Field[] clazzFields = clazz.getDeclaredFields();
        for (int index = 0; index < clazzFields.length; index = index + 1) {
            Field field = clazzFields[index];
            if(field.getType() == String.class){
                oneKeywordCriterion.add(Restrictions.ilike(field.getName(), "%"+kwd +"%"));
            } else {
                Column colAnno = field.getAnnotation(Column.class);
                String colName;
                if(colAnno==null){
                    LOGGER.debug("La colonne n'existe pas : " + field);
                }else{
                    if(colAnno.name() == null){
                        colName = field.getName();
                    } else {
                        colName = colAnno.name();
                    }
                    oneKeywordCriterion.add(Restrictions.sqlRestriction(colName+"::text ilike '%"+kwd.replace("'", "\\'")+"%'"));
                }
            }
        }
        Criterion[] tmp = new Criterion[oneKeywordCriterion.size()];
        return Restrictions.or(oneKeywordCriterion.toArray(tmp));
    }

    @Override
    public List<T> select(Class<T> clazz, String propertyName,
            List<?> propertyValues) {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria query = session.createCriteria(clazz);
        
        query.add(Restrictions.in(propertyName, propertyValues));
        
        List<T> rapports = new ArrayList<>();
        List<?> tempList = query.list();
        for (Object tempObj : tempList) {
            rapports.add(clazz.cast(tempObj));
        }

        return rapports;
    }


    @Override
    public List<T> searchByCriteria(SearchDto search, Class<T> clazz,
    		List<SearchCriteriaDto> listCriteria,
            List<String> propertyNames) {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria query = createSearchCriterion(session, search, clazz, propertyNames);
        
        Disjunction disjunction = Restrictions.disjunction();

        for(SearchCriteriaDto crit : listCriteria) {
        	createCriterions(disjunction, crit);
        }
        query.add(disjunction);
        
        List<T> rapports = new ArrayList<>();
        List<?> tempList = query.list();
        for (Object tempObj : tempList) {
            rapports.add(clazz.cast(tempObj));
        }

        return rapports;
    }


	@Override
	public List<T> selectByCriteria(
			Class<T> clazz,
			List<SearchCriteriaDto> listCriteria) {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria query = session.createCriteria(clazz);
        Disjunction disjunction = Restrictions.disjunction();

        for(SearchCriteriaDto crit : listCriteria) {
        	createCriterions(disjunction, crit);
        }
        query.add(disjunction);
        
        List<T> rapports = new ArrayList<>();
        List<?> tempList = query.list();
        for (Object tempObj : tempList) {
            rapports.add(clazz.cast(tempObj));
        }

        return rapports;
	}

	/**
	 * Methode qui cree les criterions
	 */
	private void createCriterions(Disjunction disjunction,
			SearchCriteriaDto crit) {
		if(crit.isLike()){
			for (String code : crit.getCodes()){
				LOGGER.debug("like ajouter | crit.getField() " + crit.getField() + "| code =" + code);
				disjunction.add(Restrictions.like(crit.getField(), code));
			}
		}else{
			LOGGER.debug("in ajouter | crit.getField() " + crit.getField() + "| crit.getCodes() " );
			disjunction.add(Restrictions.in(crit.getField(), crit.getCodes()));
		}
	}
	
    
}
