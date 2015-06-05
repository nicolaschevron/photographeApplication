package fr.inra.dsi.reporting.data.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Component;

import fr.inra.dsi.activites.endpoint.ValeurEtatAC;
import fr.inra.dsi.reporting.model.RapportListeActivites;
import fr.inra.dsi.reporting.util.TestUtil;

/**
 * Implemente la classe abstraite RapportBuilderImpl
 * en indiquant le jeu de donnees a ajouter dans
 * l'unite de persistence.
 * 
 * @author lepra
 */
@Component
public class RapportListeActivitesBuilderImpl extends AbstractRapportBuilderImpl<RapportListeActivites> {

    public static final String ACC_ABREV = "TEST";
    public static final String ACC_LIBELLE = "AC TEST REPORTING";
    public static final String ACC_TYPE_LIBELLE = "TYPE AC TEST REPORTING";
    
    public static final String ACC_ID_FONC = TestUtil.getNextCodeActivite();
    public static final String ACC_CODE_UNITE = "UNITE TEST REPORTING";
    public static final String ACC_DATE_DEBUT = "2015-01-10T00:00:00Z";
    public static final String ACC_DATE_FIN_PREVUE = "2015-01-11T00:00:00Z";
    public static final String ACC_DATE_FIN_EFFECTIVE = "2015-01-12T00:00:00Z";
    public static final String ACC_ETAT = ValeurEtatAC.VALIDE.value();
    /**
     * Ne pas utiliser de constante pour les id générées par la base de données.
     */
    @Deprecated
    public static final int ACC_IDENTIFIANT = 50;
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected List<RapportListeActivites> dataSampleConstruction() throws ParseException {
        List<RapportListeActivites> rapports = new ArrayList<>();
        RapportListeActivites rapport = new RapportListeActivites();
        rapport.setAbreviationTypeAc(ACC_ABREV);
        rapport.setCodeActiviteCollective(ACC_ID_FONC);
        rapport.setCodeUnite(ACC_CODE_UNITE);
        rapport.setIdentifiant(ACC_IDENTIFIANT);
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.FRANCE);
        
        rapport.setDate(dateFormat.parse(ACC_DATE_DEBUT));
        rapport.setDateFinEffective(dateFormat.parse(ACC_DATE_FIN_EFFECTIVE));
        rapport.setDateFinPrevue(dateFormat.parse(ACC_DATE_FIN_PREVUE));
        
        rapport.setEtatAc(ACC_ETAT);
        rapport.setLibelleAc(ACC_LIBELLE);
        rapport.setLibelleTypeAc(ACC_TYPE_LIBELLE);
        
        rapports.add(rapport);
        
        return rapports;
    }

}
