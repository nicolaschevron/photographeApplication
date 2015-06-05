package fr.inra.dsi.reporting.data.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.text.DateFormat;

import org.springframework.stereotype.Component;

import fr.inra.dsi.reporting.model.RapportChampThematique;

@Component
public class RapportListeChampsThematiquesBuilderImpl  extends AbstractRapportBuilderImpl<RapportChampThematique> {

	public static final int CT_ID = 50;
	public static final int CT_CODE = 10;
	public static final String CT_SIGLE = "MICA-CT";
	public static final int CT_SIGLE_NUM = 2;
	public static final String CT_DESC = "DESCRIPTION DE LA CT";
	public static final String CT_INTITULE = "BIODIVERSITE";
	public static final String CT_DATE_DEBUT = "2015-01-10T00:00:00Z";
	public static final String CT_DATE_FIN = "2015-01-10T00:00:00Z";
	public static final String CT_DATE_EFFECTIVE = "2015-01-10T00:00:00Z";
	
    /**
     * {@inheritDoc}
     */
	@Override
	protected List<RapportChampThematique> dataSampleConstruction()
			throws ParseException {
		List<RapportChampThematique> rapports = new ArrayList<>();
		RapportChampThematique rapport = new RapportChampThematique();
		rapport.setIdentifiant(CT_ID);
		rapport.setCode(CT_CODE);
		rapport.setSigle(CT_SIGLE);
		rapport.setSigleNum(CT_SIGLE_NUM);
		rapport.setDescription(CT_DESC);
		rapport.setIntitule(CT_INTITULE);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.FRANCE);
		
		rapport.setDateDebut(dateFormat.parse(CT_DATE_DEBUT));
		rapport.setDateFin(dateFormat.parse(CT_DATE_FIN));
		rapport.setDateFinEffective(dateFormat.parse(CT_DATE_EFFECTIVE));

		rapports.add(rapport);
		return rapports;
	}

}
