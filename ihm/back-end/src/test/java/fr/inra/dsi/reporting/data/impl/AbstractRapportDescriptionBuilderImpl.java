package fr.inra.dsi.reporting.data.impl;

import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.util.List;

import fr.inra.dsi.reporting.dao.IRapportDescriptionDao;
import fr.inra.dsi.reporting.data.IRapportDesciptionBuilder;
import fr.inra.dsi.reporting.model.RapportDescription;

public abstract class AbstractRapportDescriptionBuilderImpl
		implements IRapportDesciptionBuilder {

	@Autowired
	protected transient IRapportDescriptionDao rapportDescriptionDao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void createRapports() throws ParseException {
		rapportDescriptionDao.saveAll(dataSampleConstruction());
	}

	/**
	 * Methode abstraite permettant de generer les donnees a inserer dans
	 * l'unite de persistence.
	 * @param <RapportDescription>
	 * 
	 * @return List
	 * @throws ParseException
	 */
	protected abstract List<RapportDescription> dataSampleConstruction()
			throws ParseException;

}
