package fr.inra.dsi.reporting.data.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import fr.inra.dsi.reporting.model.RapportChampThematique;
import fr.inra.dsi.reporting.model.RapportChampThematiqueParActivite;
import fr.inra.dsi.reporting.model.RapportChampsFonctionnelParActivite;
import fr.inra.dsi.reporting.model.RapportDescription;
import fr.inra.dsi.reporting.model.RapportListeActivites;
import fr.inra.dsi.reporting.model.RapportListeActivitesParUnite;
import fr.inra.dsi.reporting.model.RapportMotsClesParActivite;
import fr.inra.dsi.reporting.model.RapportParticipationAgentSurActivite;
import fr.inra.dsi.reporting.model.RapportParticipationAgtChampThematique;
import fr.inra.dsi.reporting.security.Constant;
import fr.inra.dsi.reporting.util.WebConstantUtil;

@Component
public class RapportDescriptionBuilderImpl extends AbstractRapportDescriptionBuilderImpl{
	
    public static final int RAPPORT_ACT_ID = 50;
    public static final String RAPPORT_ACT_CLAZZ = RapportListeActivites.class.getSimpleName();
    public static final String RAPPORT_ACT_TITRE = "Liste activites";
    public static final String  RAPPORT_ACT_DESC = "Rapport contenant la liste des activites";
    public static final boolean RAPPORT_ACT_ACTIF = true;
    public static final String RAPPORT_ACT_REST_CALL = "/liste_activites";

    public static final int RAPPORT_CT_ID = 51;
    public static final String RAPPORT_CT_CLAZZ = RapportChampThematique.class.getSimpleName();
    public static final String RAPPORT_CT_TITRE = "Liste champs thématiques";
    public static final String  RAPPORT_CT_DESC = "Rapport contenant la liste des champs thématiques";
    public static final boolean RAPPORT_CT_ACTIF = true;
    public static final String RAPPORT_CT_REST_CALL = "/liste_ct";

    public static final int RAPPORT_ACT_BY_UNITE_ID = 52;
    public static final String RAPPORT_ACT_BY_UNITE_CLAZZ = RapportListeActivitesParUnite.class.getSimpleName();
    public static final String RAPPORT_ACT_BY_UNITE_TITRE = "Liste activites par unite";
    public static final String  RAPPORT_ACT_BY_UNITE_DESC = "Rapport contenant la liste des activites par unité";
    public static final boolean RAPPORT_ACT_BY_UNITE_ACTIF = true;
    
    public static final int RAPPORT_CT_PARTICIPATION_UNITE_ID = 53;
    public static final String RAPPORT_CT_PARTICIPATION_UNITE_CLAZZ = RapportChampThematiqueParActivite.class.getSimpleName();
    public static final String RAPPORT_CT_PARTICIPATION_UNITE_TITRE = "Liste champs thématiques avec participation des unités";
    public static final String  RAPPORT_CT_PARTICIPATION_UNITE_DESC = "Rapport contenant la liste des champs thématiques avec la participation des unités";
    public static final boolean RAPPORT_CT_PARTICIPATION_UNITE_ACTIF = true;
    public static final String RAPPORT_CT_REST_PARTICIPATION_UNITE_CALL = WebConstantUtil.RAPPORT_LISTE_CT_PARTICIPATION_UNITE_PATH;
    
    public static final int RAPPORT_PARTICIPATION_AGENT_ID = 53;
    public static final String RAPPORT_PARTICIPATION_AGENT_CLAZZ = RapportParticipationAgentSurActivite.class.getSimpleName();
    public static final String RAPPORT_PARTICIPATION_AGENT_TITRE = "Liste des participations des agents par unité";
    public static final String  RAPPORT_PARTICIPATION_AGENT_DESC = "Rapport contenant la liste des ...";
    public static final boolean RAPPORT_PARTICIPATION_AGENT_ACTIF = true;
    public static final String RAPPORT_PARTICIPATION_AGENT_REST_CALL = WebConstantUtil.RAPPORT_LISTE_PARTICIPATION_AGENT_PATH;
    
    public static final int RAPPORT_CF_PARTICIPATION_UNITE_ID = 53;
    public static final String RAPPORT_CF_PARTICIPATION_UNITE_CLAZZ = RapportChampsFonctionnelParActivite.class.getSimpleName();
    public static final String RAPPORT_CF_PARTICIPATION_UNITE_TITRE = "Liste des champs fonctionnels par activité";
    public static final String  RAPPORT_CF_PARTICIPATION_UNITE_DESC = "Rapport contenant la liste des ...";
    public static final boolean RAPPORT_CF_PARTICIPATION_UNITE_ACTIF = true;
    public static final String RAPPORT_CF_PARTICIPATION_UNITE_REST_CALL = WebConstantUtil.RAPPORT_LISTE_CF_PATH;
	
    public static final int RAPPORT_MOTS_CLES_PAR_ACTIVITE_ID = 53;
    public static final String RAPPORT_MOTS_CLES_PAR_ACTIVITE_CLAZZ = RapportMotsClesParActivite.class.getSimpleName();
    public static final String RAPPORT_MOTS_CLES_PAR_ACTIVITE_TITRE = "Mes mots cles";
    public static final String  RAPPORT_MOTS_CLES_PAR_ACTIVITE_DESC = "Rapport contenant la liste des ...";
    public static final boolean RAPPORT_MOTS_CLES_PAR_ACTIVITE_ACTIF = true;
    public static final String RAPPORT_MOTS_CLES_PAR_ACTIVITE_REST_CALL = WebConstantUtil.RAPPORT_MOTS_CLES_PAR_ACTIVITE_PATH;

    public static final int RAPPORT_LISTE_PARTICIPATION_AGENT_CT_ID = 54;
    public static final String RAPPORT_LISTE_PARTICIPATION_AGENT_CT_CLAZZ = RapportParticipationAgtChampThematique.class.getSimpleName();
    public static final String RAPPORT_LISTE_PARTICIPATION_AGENT_CT_TITRE = "Participations agents sur les CT";
    public static final String  RAPPORT_LISTE_PARTICIPATION_AGENT_CT_DESC = "Rapport contenant la liste des ...";
    public static final boolean RAPPORT_LISTE_PARTICIPATION_AGENT_CT_ACTIF = true;
    public static final String RAPPORT_LISTE_PARTICIPATION_AGENT_CT_REST_CALL = WebConstantUtil.RAPPORT_PARTICIPATION_AGT_CT_PATH;
    
	@Override
	protected List<RapportDescription> dataSampleConstruction()
			throws ParseException {
		List<RapportDescription> listRapportDescription = new ArrayList<>();
		RapportDescription rapportDescription = new RapportDescription();
		rapportDescription.setId(RAPPORT_ACT_ID);
		rapportDescription.setClazz(RAPPORT_ACT_CLAZZ);
		rapportDescription.setTitre(RAPPORT_ACT_TITRE);
		rapportDescription.setDescription(RAPPORT_ACT_DESC);
		rapportDescription.setActif(RAPPORT_ACT_ACTIF);
		rapportDescription.setRestCall(RAPPORT_ACT_REST_CALL);
        rapportDescription.setRestrictionRoles(null);
		listRapportDescription.add(rapportDescription);
		
		rapportDescription = new RapportDescription();
		rapportDescription.setId(RAPPORT_CT_ID);
		rapportDescription.setClazz(RAPPORT_CT_CLAZZ);
		rapportDescription.setDescription(RAPPORT_CT_DESC);
		rapportDescription.setActif(RAPPORT_CT_ACTIF);
		rapportDescription.setRestCall(RAPPORT_CT_REST_CALL);
		rapportDescription.setTitre(RAPPORT_CT_TITRE);
		rapportDescription.setRestrictionRoles(null);
		listRapportDescription.add(rapportDescription);
		
		rapportDescription = new RapportDescription();
        rapportDescription.setId(RAPPORT_ACT_BY_UNITE_ID);
        rapportDescription.setClazz(RAPPORT_ACT_BY_UNITE_CLAZZ);
        rapportDescription.setTitre(RAPPORT_ACT_BY_UNITE_TITRE);
        rapportDescription.setDescription(RAPPORT_ACT_BY_UNITE_DESC);
        rapportDescription.setActif(RAPPORT_ACT_BY_UNITE_ACTIF);
        rapportDescription.setRestCall(WebConstantUtil.RAPPORT_LISTE_ACT_BY_UNITE_PATH);
        rapportDescription.setRestrictionRoles(Constant.ROLE_CD+','+Constant.ROLE_DU+','
                +Constant.ROLE_CNOC+','+Constant.ROLE_CNUE+','+Constant.ROLE_DAR);
        listRapportDescription.add(rapportDescription);
		
        rapportDescription = new RapportDescription();
        rapportDescription.setId(RAPPORT_CT_PARTICIPATION_UNITE_ID);
        rapportDescription.setClazz(RAPPORT_CT_PARTICIPATION_UNITE_CLAZZ);
        rapportDescription.setDescription(RAPPORT_CT_PARTICIPATION_UNITE_DESC);
        rapportDescription.setActif(RAPPORT_CT_PARTICIPATION_UNITE_ACTIF);
        rapportDescription.setRestCall(RAPPORT_CT_REST_PARTICIPATION_UNITE_CALL);
        rapportDescription.setTitre(RAPPORT_CT_PARTICIPATION_UNITE_TITRE);
        rapportDescription.setRestrictionRoles(Constant.ROLE_CD+','+Constant.ROLE_DU+','
                +Constant.ROLE_CNOC+','+Constant.ROLE_CNUE+','+Constant.ROLE_DAR+','+Constant.ROLE_PC);
        listRapportDescription.add(rapportDescription);
        
        rapportDescription = new RapportDescription();
        rapportDescription.setId(RAPPORT_PARTICIPATION_AGENT_ID);
        rapportDescription.setClazz(RAPPORT_PARTICIPATION_AGENT_CLAZZ);
        rapportDescription.setDescription(RAPPORT_PARTICIPATION_AGENT_DESC);
        rapportDescription.setActif(RAPPORT_PARTICIPATION_AGENT_ACTIF);
        rapportDescription.setRestCall(RAPPORT_PARTICIPATION_AGENT_REST_CALL);
        rapportDescription.setTitre(RAPPORT_PARTICIPATION_AGENT_TITRE);
        rapportDescription.setRestrictionRoles(Constant.ROLE_CD+','+Constant.ROLE_DU+','
                +Constant.ROLE_CNOC+','+Constant.ROLE_CNUE+','+Constant.ROLE_DAR+','+Constant.ROLE_PC);
        listRapportDescription.add(rapportDescription);
        
        rapportDescription = new RapportDescription();
        rapportDescription.setId(RAPPORT_CF_PARTICIPATION_UNITE_ID);
        rapportDescription.setClazz(RAPPORT_CF_PARTICIPATION_UNITE_CLAZZ);
        rapportDescription.setDescription(RAPPORT_CF_PARTICIPATION_UNITE_DESC);
        rapportDescription.setActif(RAPPORT_CF_PARTICIPATION_UNITE_ACTIF);
        rapportDescription.setRestCall(RAPPORT_CF_PARTICIPATION_UNITE_REST_CALL);
        rapportDescription.setTitre(RAPPORT_CF_PARTICIPATION_UNITE_TITRE);
        rapportDescription.setRestrictionRoles(Constant.ROLE_CD+','+Constant.ROLE_DU+','
                +Constant.ROLE_CNOC+','+Constant.ROLE_CNUE+','+Constant.ROLE_DAR+','+Constant.ROLE_PC);
        listRapportDescription.add(rapportDescription);
        
        rapportDescription = new RapportDescription();
        rapportDescription.setId(RAPPORT_MOTS_CLES_PAR_ACTIVITE_ID);
        rapportDescription.setClazz(RAPPORT_MOTS_CLES_PAR_ACTIVITE_CLAZZ);
        rapportDescription.setDescription(RAPPORT_MOTS_CLES_PAR_ACTIVITE_DESC);
        rapportDescription.setActif(RAPPORT_MOTS_CLES_PAR_ACTIVITE_ACTIF);
        rapportDescription.setRestCall(RAPPORT_MOTS_CLES_PAR_ACTIVITE_REST_CALL);
        rapportDescription.setTitre(RAPPORT_MOTS_CLES_PAR_ACTIVITE_TITRE);
        rapportDescription.setRestrictionRoles(Constant.ROLE_CD+','+Constant.ROLE_DU+','
                +Constant.ROLE_CNOC+','+Constant.ROLE_CNUE+','+Constant.ROLE_DAR+','+Constant.ROLE_PC);
        listRapportDescription.add(rapportDescription);
        
        rapportDescription = new RapportDescription();
        rapportDescription.setId(RAPPORT_LISTE_PARTICIPATION_AGENT_CT_ID);
        rapportDescription.setClazz(RAPPORT_LISTE_PARTICIPATION_AGENT_CT_CLAZZ);
        rapportDescription.setDescription(RAPPORT_LISTE_PARTICIPATION_AGENT_CT_DESC);
        rapportDescription.setActif(RAPPORT_LISTE_PARTICIPATION_AGENT_CT_ACTIF);
        rapportDescription.setRestCall(RAPPORT_LISTE_PARTICIPATION_AGENT_CT_REST_CALL);
        rapportDescription.setTitre(RAPPORT_LISTE_PARTICIPATION_AGENT_CT_TITRE);
        rapportDescription.setRestrictionRoles(Constant.ROLE_CD+','+Constant.ROLE_DU+','
                +Constant.ROLE_DAR+','+Constant.ROLE_PC);
        listRapportDescription.add(rapportDescription);
        
		return listRapportDescription;
	}
	
}
