package fr.inra.dsi.reporting.data.impl;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import fr.inra.dsi.reporting.dao.IRapportDao;
import fr.inra.dsi.reporting.data.IRapportBuilder;
import fr.inra.dsi.reporting.model.IRapport;

/**
 * Implementation generique de la creation des
 * rapports.
 * 
 * @author lepra
 *
 * @param <T>
 */
public abstract class AbstractRapportBuilderImpl<T extends IRapport> implements IRapportBuilder<IRapport> {

    @Autowired
    private transient IRapportDao<T> rapportDao;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void createRapports() throws ParseException {
        rapportDao.saveAll(dataSampleConstruction());
    }
    
    /**
     * Methode abstraite permettant de generer les 
     * donnees a inserer dans l'unite de persistence.
     * 
     * @return List
     * @throws ParseException
     */
    protected abstract List<T> dataSampleConstruction() throws ParseException;

}
