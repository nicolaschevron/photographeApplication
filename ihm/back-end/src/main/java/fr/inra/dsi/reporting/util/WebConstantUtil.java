package fr.inra.dsi.reporting.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * Classe utilitaire stockant des variables static final.
 * 
 * @author lepra
 */
public final class WebConstantUtil {

    /**
     * Constructeur prive : il s'agit d'une approche singleton.
     */
    private WebConstantUtil() {
        super();
    }

    /*
     * Partie authentification et cas
     */
    public static final String SESSION_REDIRECT_PARAM = "postAuthRedirectUrl";
    public static final String AUTHENTICATION_CHECK_PATH = "/authentification/verifier";
    public static final String USER_INFOS_PATH = "/utilisateur/informations";
    
    /*
     * Gestion de la partie feedback de l application
     */
    public static final String CONF_FEEDBACK_EMAIL_PATH = "/conf/feedback";
    public static final String CONF_PATH = "/conf";
    
	/*
	 * R1 rapport liste activite
	 */
    public static final String RAPPORT_LISTE_ACT_PATH = "/liste_activites";
    public static final String RAPPORT_LISTE_ACT_FIND_PATH = RAPPORT_LISTE_ACT_PATH+"/all";
    public static final String RAPPORT_LISTE_ACT_EXPORT_PATH = RAPPORT_LISTE_ACT_PATH+"/export";
    public static final String CLE_PROP_RAPPORT_LISTE_ACT = "rapportlisteactivites.label.colonne.";
    public static final List<String> RAPPORT_LISTE_ACT_COLONNE_KEY = Collections.unmodifiableList(Arrays.asList(new String[] { 
            "libelleTypeAc", "abreviationTypeAc","codeActiviteCollective","libelleAc","date","dateFinPrevue",
            "dateFinEffective","etatAc","codeUnite" }));
    
    /*
     * 	Dashboard et page d accueil
     */
    public static final String RAPPORT_LISTE_RAP_DESC_FIND_PATH = "/liste_rapports_description/all";
    public static final String RAPPORT_DESC_UPDATE_AVAILABILITY = "/update_rapport/{ids}/{value}";
    public static final String RAPPORT_DESC_UPDATE_AVAILABILITY_PATH = "/update_rapport";
    public static final String CLE_PROP_RAPPORT_DESC = "rapportdescription.label.colonne.";
    public static final String CLE_PROP_RAPPORT_DESC_TITRE = "rapportdescription.label.titre";
    public static final List<String> RAPPORT_DESC_COLONNE_KEY = Collections.unmodifiableList(Arrays.asList(new String[] { 
            "titre", "description", "restCall"}));
    public static final List<String> RAPPORT_DESC_COLONNE_KEY_ADMIN = Collections.unmodifiableList(Arrays.asList(new String[] { 
            "titre", "description","actif","restCall"}));
    
    /*
     *  R2 rapport champ thematique
     */
    public static final String RAPPORT_LISTE_CT_PATH = "/liste_ct";
    public static final String RAPPORT_LISTE_CT_FIND_PATH = RAPPORT_LISTE_CT_PATH+"/all";
    public static final String RAPPORT_LISTE_CT_EXPORT_PATH = RAPPORT_LISTE_CT_PATH+"/export";
    public static final String CLE_PROP_RAPPORT_LISTE_CT = "rapportlistect.label.colonne.";
    public static final List<String> RAPPORT_LISTE_CT_COLONNE_KEY = Collections.unmodifiableList(Arrays.asList(new String[] { 
    		"code","sigle","sigleNum","description","intitule","dateDebut","dateFin","dateFinEffective"}));
    
    /*
     * R3 rapport liste activite par unite
     */
    public static final String RAPPORT_LISTE_ACT_BY_UNITE_PATH = "/liste_activites_unite";
    public static final String RAPPORT_LISTE_ACT_BY_UNITE_FIND_PATH = RAPPORT_LISTE_ACT_BY_UNITE_PATH+"/all";
    public static final String RAPPORT_LISTE_ACT_BY_UNITE_EXPORT_PATH = RAPPORT_LISTE_ACT_BY_UNITE_PATH+"/export";
    public static final String CLE_PROP_RAPPORT_LISTE_ACT_BY_UNITE = "rapportlisteactivitesparunite.label.colonne.";
    public static final List<String> RAPPORT_LISTE_ACT_BY_UNITE_COLONNE_KEY = Collections.unmodifiableList(Arrays.asList(new String[] { 
            "codeActiviteCollective", "libelleAc", "libelleTypeAc", "codeUnite", "sigleUnite", "typeUnite", "caracteristiqueUnite",
            "uniteLeader", "date", "dateFinPrevue", "dateFinEffective",
            "codiqueDepartement", "intituleDepartement", "sigleDepartement", "codeCentre", "libelleCentre", "sigleCentre", "sitePrincipalCentre", 
            "matriculeAp", "nomAp", "prenomAp","gradeAp","mailAp","dateNominationAp","pourcentageTempsTravailAp"}));
    
    /*
     * R4 rapport champ thématique avec participation des unités 
     */
    public static final String RAPPORT_LISTE_CT_PARTICIPATION_UNITE_PATH = "/liste_ct_participation_unite";
    public static final String CLE_PROP_RAPPORT_LISTE_CT_PARTICIPATION_UNITE = "rapportctparticipationunite.label.colonne.";
    public static final List<String> RAPPORT_LISTE_CT_PARTICIPATION_UNITE_COLONNE_KEY = Collections.unmodifiableList(Arrays.asList(new String[] {
            "sigleNumCt", "abreviationTypeAc", "codeActiviteCollective", "libelleAc", "dateAc", "dateAcFinEffective","typeParticipation","codeUnite",
            "sigleUnite","nomUnite","sigleAutoriteLeader", "pourcentageRattachementAutorite", "dateDebutRattachement", "dateFinRattachement"  
    }));

    /*
     * R5 rapport mots cles par activités
     */
    public static final String RAPPORT_MOTS_CLES_PAR_ACTIVITE_PATH = "/liste_mc_par_act";
    public static final String CLE_PROP_RAPPORT_MOTS_CLES_PAR_ACTIVITE = "rapportlistemcparact.label.colonne.";
    public static final List<String> RAPPORT_MOTS_CLES_PAR_ACTIVITE_COLONNE_KEY = Collections.unmodifiableList(Arrays.asList(new String[] {
            "codeActiviteCollective", "libelleAc", "listeMotsCles"}));
    
    /*
     * R6 rapport avec participation des agents sur les unités 
     */
    public static final String RAPPORT_LISTE_PARTICIPATION_AGENT_PATH = "/liste_part_agents_act";
    public static final String CLE_PROP_RAPPORT_LISTE_PARTICIPATION_AGENT = "rapportparticipationagent.label.colonne.";
    public static final List<String> RAPPORT_LISTE_PARTICIPATION_AGENT_COLONNE_KEY = Collections.unmodifiableList(Arrays.asList(new String[] {
            "codeActiviteCollective", "libelleAc", "libelleTypeAc", "codeUniteLeader",  "sigleUniteLeader",  "dateAc", "dateAcFinPrevue", "dateAcFinEffective", 
            "matriculePersonne",  "nomPersonne",  "prenomPersonne",  "unitePersonne",  "typePersonne",  "categoriePersonne", "codeGradePersonne", "pourcentageTempsTravailPersonne",
            "dateDebutRattachementPersonneAc", "dateFinRattachementPersonneAc", "pourcentageParticipationPersonneAc", "pourcentageDisponibilitePersonne"
    }));
    
    /*
     * R8 rapport des champs fonctionnels par activite
     */
    public static final String RAPPORT_LISTE_CF_PATH = "/liste_cf_participation_unite";
    public static final String CLE_PROP_RAPPORT_LISTE_CF = "rapportlistecf.label.colonne.";
    public static final List<String> RAPPORT_LISTE_CF_COLONNE_KEY = Collections.unmodifiableList(Arrays.asList(new String[] {
            "codeChampsFonctionnel", "typeChampsFonctionnel", "codeSousChampsFonctionnel", "cfCodeActiviteCollective", 
            "abreviationTypeAc", "cfLibelleAc", "cfDateAc", "cfDateAcFinPrevue", "cfSigleUnite", "cfNomUnite", "cfSigleAutoriteLeader"
    }));
    
    /*
     * R7 participation des agents sur les CT
     */
    public static final String RAPPORT_PARTICIPATION_AGT_CT_PATH = "/liste_part_agents_ct";
    public static final String CLE_RAPPORT_PARTICIPATION_AGT_CT = "rapportparticipationagtcf.label.colonne.";
    public static final List<String> RAPPORT_PARTICIPATION_AGT_CT_COLONNE_KEY = Collections.unmodifiableList(Arrays.asList(new String[] {
            "codeCtRpCt", "nomCtRpCt", "sigleNumCtRpCt", "codiqueDepartementAgentRpCt", "sigleDeptPiloteRpCt", "matriculePersonneRpCt",
            "nomPersonneRpCt", "prenomPersonneRpCt", "codeUniteAgentRpCt", "typePersonneRpCt", "categoriePersonneRpCt",
            "codeGradePersonneRpCt", "pourcentageTempsTravailPersonneRpCt"
    }));
}
