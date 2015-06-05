package fr.inra.dsi.reporting.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import fr.inra.dsi.reporting.dataservice.IRapportService;
import fr.inra.dsi.reporting.dto.SearchCriteriaDto;
import fr.inra.dsi.reporting.dto.SearchDto;
import fr.inra.dsi.reporting.model.IRapport;
import fr.inra.dsi.reporting.model.RapportDescription;
import fr.inra.dsi.reporting.security.Constant;
import fr.inra.dsi.reporting.security.INRAUser;
import fr.inra.dsi.reporting.service.ISecurityLayerService;
import fr.inra.dsi.reporting.service.ISecurityManagerService;

/**
 * Classe de base pour tous les "SecurityService".
 * 
 * @author gugau
 *
 * @param <T>
 */
public abstract class AbstractSecurityLayerServiceImpl<T extends IRapport> implements ISecurityLayerService<T> {
    
    private static final Logger LOGGER = Logger.getLogger(AbstractSecurityLayerServiceImpl.class);
    
    @Autowired
    private ISecurityManagerService securityManagerService;

    /**
     * Constructeur.
     */
    public AbstractSecurityLayerServiceImpl() {
        super();
    }

    /**
     * Renvoie les critères permettant de filtrer les données en fonction des rôles
     * de l'utilisateur.
     * @param user
     * @return
     */
    public abstract List<SearchCriteriaDto> createCriterias(INRAUser user);

    /**
     * Renvoie le IRapportService à utiliser pour récupérer les données
     * @return
     */
    public abstract IRapportService<T> getRapportService();
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> getAllRapports(INRAUser user, Class<T> clazz) {
        return getRapportService().selectByCriteria(clazz, this.createCriterias(user));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> search(INRAUser user, SearchDto search, Class<T> clazz,
            List<String> propertyNames) {
        return getRapportService().searchByCriteria(clazz, this.createCriterias(user), search, propertyNames);
    }
    
    /**
     * Crée des critères de recherche pour le rôle CD
     * @param user
     * @param rapportDesc
     * @param fieldCodiqueDepartement le nom du champ contenant le codique du département
     * @param fieldListeDepartementCoPilote le nom du champ contenant la liste des départements copilotes
     * @return
     */
    public List<SearchCriteriaDto> createCriteriaForCD(INRAUser user, RapportDescription rapportDesc,
            String fieldCodiqueDepartement, String fieldListeDepartementCoPilote) {
        List<SearchCriteriaDto> listCriteria = new ArrayList<>();
        
        listCriteria.addAll(createCriteriaForCD(user, rapportDesc, fieldCodiqueDepartement));
        
        if(user.isCD() && securityManagerService.isRoleAllowed(rapportDesc, Constant.ROLE_CD)) {
            List<String> temp = new ArrayList<String>();
            for (String codeDept : user.getCodesDepartement()) {
                temp.add("%,"+codeDept+",%");
            }
            listCriteria.add(new SearchCriteriaDto(fieldListeDepartementCoPilote,temp,true));
        }
        
        return listCriteria;
    }
    
    /**
     * Crée des critères de recherche pour le rôle CD
     * @param user
     * @param rapportDesc
     * @param fieldCodiqueDepartement le nom du champ contenant le codique du département
     * @return
     */
    public List<SearchCriteriaDto> createCriteriaForCD(INRAUser user, RapportDescription rapportDesc,
            String fieldCodiqueDepartement) {
        List<SearchCriteriaDto> listCriteria = new ArrayList<>();
        
        if(user.isCD() && securityManagerService.isRoleAllowed(rapportDesc, Constant.ROLE_CD)) {
            listCriteria.add(new SearchCriteriaDto(fieldCodiqueDepartement, user.getCodesDepartement()));
        }
        
        return listCriteria;
    }
    
    /**
     * Crée des critères de recherche pour le rôle DAR
     * @param user
     * @param rapportDesc
     * @param fieldCodiqueDepartement le nom du champ contenant le codique du département
     * @return
     */
    public List<SearchCriteriaDto> createCriteriaForDAR(INRAUser user, RapportDescription rapportDesc,
            String fieldCodiqueDepartement) {
        List<SearchCriteriaDto> listCriteria = new ArrayList<SearchCriteriaDto>();
        
        if(user.isDAR() && securityManagerService.isRoleAllowed(rapportDesc, Constant.ROLE_DAR)){
            listCriteria.add(new SearchCriteriaDto(fieldCodiqueDepartement, user.getCodesDirectionAppui()));
        }
        
        return listCriteria;
    }
    
    /**
     * Crée des critères de recherche pour le rôle DU
     * @param user
     * @param rapportDesc
     * @param fieldCodeUnite nom du champ contenant le code de l'unité
     * @param fieldListeUniteParticipante non du champ contenant la liste des unités participantes
     * @return
     */
    public List<SearchCriteriaDto> createCriteriaForDU(INRAUser user, RapportDescription rapportDesc,
            String fieldCodeUnite, String fieldListeUniteParticipante) {
        List<SearchCriteriaDto> listCriteria = new ArrayList<>();
        
        listCriteria.addAll(createCriteriaForDU(user, rapportDesc, fieldCodeUnite));
        
        if(user.isDU() && securityManagerService.isRoleAllowed(rapportDesc, Constant.ROLE_DU)) {
            List<String> temp = new ArrayList<String>();
            for(String unite : user.getCodesUnite()) {
                temp.add("%,"+unite+"%,");
            }
            listCriteria.add(new SearchCriteriaDto(fieldListeUniteParticipante,temp,true));
        }
        
        return listCriteria; 
    }
    
    /**
     * Crée des critères de recherche pour le rôle DU
     * @param user
     * @param rapportDesc
     * @param fieldCodeUnite nom du champ contenant le code de l'unité
     * @return
     */
    public List<SearchCriteriaDto> createCriteriaForDU(INRAUser user, RapportDescription rapportDesc,
            String fieldCodeUnite) {
        List<SearchCriteriaDto> listCriteria = new ArrayList<>();
        
        if(user.isDU() && securityManagerService.isRoleAllowed(rapportDesc, Constant.ROLE_DU)) {
            listCriteria.add(new SearchCriteriaDto(fieldCodeUnite, user.getCodesUnite()));
        }
        
        return listCriteria; 
    }
    
    /**
     * Crée des des critères de recherche pour le role CNUE et CNOC
     * @param user
     * @param fieldAbreviationTypeAc le champ contenant le type de l'AC
     * @return
     */
    public List<SearchCriteriaDto> createCriteriaForCnueCnoc(INRAUser user, RapportDescription rapportDesc,
            String fieldAbreviationTypeAc){
        List<SearchCriteriaDto> listCriteria = new ArrayList<>();
        
        if(user.isCNUE() && securityManagerService.isRoleAllowed(rapportDesc, Constant.ROLE_CNUE)) {
            String fieldValue = "ACE";
            SearchCriteriaDto criteria = new SearchCriteriaDto(fieldAbreviationTypeAc , Arrays.asList(new String[]{fieldValue}));
            LOGGER.debug("Ajout du criteria pour filtrer sur " + fieldAbreviationTypeAc + " avec la valeur : " + fieldValue);
            listCriteria.add(criteria);
        }
        
        if(user.isCNOC() && securityManagerService.isRoleAllowed(rapportDesc, Constant.ROLE_CNOC)) {
            String fieldValue = "ACS";
            SearchCriteriaDto criteria = new SearchCriteriaDto(fieldAbreviationTypeAc , Arrays.asList(new String[]{fieldValue}));
            LOGGER.debug("Ajout du criteria pour filtrer sur " + fieldAbreviationTypeAc + " avec la valeur : " + fieldValue);
            listCriteria.add(criteria);
        }
        
        return listCriteria;
    }
    
    /**
     * Crée des des critères de recherche pour le role PC
     * @param user
     * @param rapportDesc
     * @param fieldCodeCentre le champ contenant le code du centre
     * @return
     */
    public List<SearchCriteriaDto> createCriteriaForPC(INRAUser user, RapportDescription rapportDesc,
            String fieldCodeCentre) {
        List<SearchCriteriaDto> listCriteria = new ArrayList<>();
        
        if(user.isPC() && securityManagerService.isRoleAllowed(rapportDesc, Constant.ROLE_PC)) {
            listCriteria.add(new SearchCriteriaDto(fieldCodeCentre, user.getCodesCentre()));
        }
        
        return listCriteria; 
    }
    
}
