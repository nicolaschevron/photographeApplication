package fr.inra.dsi.reporting.data.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Component;

import fr.inra.dsi.reporting.model.RapportListeActivitesParUnite;

@Component
public class RapportListeActivitesParUniteBuilderImpl  extends AbstractRapportBuilderImpl<RapportListeActivitesParUnite> {

	public static final String AC_U_CODE_ACTIVITE_COLLECTIVE = "123";
	public static final String AC_U_LIBELLE_AC = "LIBELLE_AC";
	public static final String AC_U_LIBELLE_TYPE_AC = "LIBELLE_TYPE_AC";
	public static final String AC_U_DATE = "2011-12-01T11:32:00Z";
	public static final String AC_U_DATE_FIN_PREVUE = "2014-12-01T11:32:00Z";
	public static final String AC_U_DATE_FIN_EFFECTIVE = "2014-12-01T11:32:00Z";

	public static final String AC_U_CODE_UNITE = "CODE_UNITE";
	public static final String AC_U_SIGLE_UNITE = "SIGLE_UNITE";
	public static final String AC_U_TYPE_UNITE = "TYPE_UNITE";
	public static final String AC_U_CARACTERISTIQUE_UNITE = "CARACTERISTIQUE_UNITE";
	public static final boolean AC_U_UNITE_LEADER = true;

	public static final String AC_U_CODIQUE_DEPARTEMENT = "CODIQUE_DEPARTEMENT";
	public static final String AC_U_INTITULE_DEPARTEMENT = "INTITULE_DEPARTEMENT";
	public static final String AC_U_SIGLE_DEPARTEMENT = "SIGLE_DEPARTEMENT";
	public static final String AC_U_CODE_CENTRE = "CODE_CENTRE";
	public static final String AC_U_LIBELLE_CENTRE = "LIBELLE_CENTRE";
	public static final String AC_U_SIGLE_CENTRE = "SIGLE_CENTRE";
	public static final String AC_U_SITE_PRINCIPAL_CENTRE = "SITE_PRINCIPAL_CENTRE";
	
	public static final String AC_U_AP_MATRICULE = "MATRICULE";
	public static final String AC_U_AP_NOM = "NOM";
	public static final String AC_U_AP_PRENOM = "PRENOM";
	public static final String AC_U_AP_GRADE = "GRADE";
	public static final String AC_U_AP_MAIL = "MAIL";
	public static final String AC_U_AP_DATE_NOMINATION = "2014-12-01T11:32:00Z";
	public static final String AC_U_AP_POURCENTAGE_TRAVAIL = "100.0";
	
	@Override
	protected List<RapportListeActivitesParUnite> dataSampleConstruction()
			throws ParseException {
		List<RapportListeActivitesParUnite> rapports = new ArrayList<>();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.FRANCE);
		
    	RapportListeActivitesParUnite rapport = new RapportListeActivitesParUnite();
    	rapport.setCodeActiviteCollective(AC_U_CODE_ACTIVITE_COLLECTIVE);
    	rapport.setLibelleAc(AC_U_LIBELLE_AC);
    	rapport.setLibelleTypeAc(AC_U_LIBELLE_TYPE_AC);
    	rapport.setDate(dateFormat.parse(AC_U_DATE));
    	rapport.setDateFinPrevue(dateFormat.parse(AC_U_DATE_FIN_PREVUE));
    	rapport.setDateFinEffective(dateFormat.parse(AC_U_DATE_FIN_EFFECTIVE));
    	
    	rapport.setCodeUnite(AC_U_CODE_UNITE);
    	rapport.setSigleUnite(AC_U_SIGLE_UNITE);
    	rapport.setTypeUnite(AC_U_TYPE_UNITE);
    	rapport.setCaracteristiqueUnite(AC_U_CARACTERISTIQUE_UNITE);
    	rapport.setUniteLeader(AC_U_UNITE_LEADER);
    	
    	rapport.setCodiqueDepartement(AC_U_CODIQUE_DEPARTEMENT);
    	rapport.setIntituleDepartement(AC_U_INTITULE_DEPARTEMENT);
    	rapport.setSigleDepartement(AC_U_SIGLE_DEPARTEMENT);

    	rapport.setCodeCentre(AC_U_CODE_CENTRE);
    	rapport.setLibelleCentre(AC_U_LIBELLE_CENTRE);
    	rapport.setSigleCentre(AC_U_SIGLE_CENTRE);
    	rapport.setSitePrincipalCentre(AC_U_SITE_PRINCIPAL_CENTRE);
    	
    	rapport.setMatriculeAp(AC_U_AP_MATRICULE);
    	rapport.setNomAp(AC_U_AP_NOM);
    	rapport.setPrenomAp(AC_U_AP_PRENOM);
    	rapport.setDateNominationAp(dateFormat.parse(AC_U_AP_DATE_NOMINATION));
    	rapport.setMailAp(AC_U_AP_MAIL);
    	rapport.setGradeAp(AC_U_AP_GRADE);
    	rapport.setPourcentageTempsTravailAp(AC_U_AP_POURCENTAGE_TRAVAIL);
    	
    	rapports.add(rapport);
        
        return rapports;
		
		
	}

}
