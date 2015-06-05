package fr.inra.dsi.reporting.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.inra.dsi.reporting.dataservice.IRapportDescriptionService;
import fr.inra.dsi.reporting.dataservice.IRapportListeActivitesParUniteService;
import fr.inra.dsi.reporting.dataservice.IRapportService;
import fr.inra.dsi.reporting.dto.SearchCriteriaDto;
import fr.inra.dsi.reporting.model.RapportDescription;
import fr.inra.dsi.reporting.model.RapportListeActivitesParUnite;
import fr.inra.dsi.reporting.security.Constant;
import fr.inra.dsi.reporting.security.INRAUser;
import fr.inra.dsi.reporting.service.ISecurityListeActivitesParUniteService;
import fr.inra.dsi.reporting.service.ISecurityManagerService;
import fr.inra.dsi.reporting.util.WebConstantUtil;

/**
 * Implémentation de ISecurityListeActivitesParUniteService pour les RapportListeActivitesParUnite
 * @author gugau
 *
 */
@Service
public class SecurityListeActivitesParUniteServiceImpl
    extends AbstractSecurityLayerServiceImpl<RapportListeActivitesParUnite>
    implements ISecurityListeActivitesParUniteService {

	private static final Logger LOGGER = Logger.getLogger(SecurityListeActivitesParUniteServiceImpl.class);

    @Autowired
    private IRapportListeActivitesParUniteService rapportService;
    
    @Autowired
    private ISecurityManagerService securityManagerService;
    
    @Autowired
    private IRapportDescriptionService rapportDescriptionService;
    
    /**
     * Constructeur.
     */
    public SecurityListeActivitesParUniteServiceImpl() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SearchCriteriaDto> createCriterias(INRAUser user) {
        
        RapportDescription rapportDesc = rapportDescriptionService.getRapportDescriptionForClazzAndRestCall(RapportListeActivitesParUnite.class.getSimpleName(),
                WebConstantUtil.RAPPORT_LISTE_ACT_BY_UNITE_PATH);

        List<SearchCriteriaDto> listCriteria = new ArrayList<SearchCriteriaDto>();
        
        listCriteria.addAll(createCriteriaForCD(user, rapportDesc, "codiqueDepartement", "listeDepartementCoPilote"));
        
        listCriteria.addAll(createCriteriaForDAR(user, rapportDesc, "codiqueDepartement"));
        
        listCriteria.addAll(createCriteriaForCnueCnoc(user, rapportDesc, "abreviationTypeAc"));
        
        if(user.isDU() && securityManagerService.isRoleAllowed(rapportDesc, Constant.ROLE_DU)) {
            String field = "codeUnite";
            SearchCriteriaDto criteria = new SearchCriteriaDto(field, user.getCodesUnite());
            LOGGER.debug("Ajout du criteria pour filtrer sur " + field + " avec la valeur : " + user.getCodesUnite());
            listCriteria.add(criteria);
        }
        
        return listCriteria;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IRapportService<RapportListeActivitesParUnite> getRapportService() {
        return rapportService;
    }

    /**
     * Setter
     */
    public void setRapportService(
            IRapportListeActivitesParUniteService rapportService) {
        this.rapportService = rapportService;
    }

    /**
     * Setter
     */
    public void setSecurityManagerService(
            ISecurityManagerService securityManagerService) {
        this.securityManagerService = securityManagerService;
    }

    /**
     * Setter
     */
    public void setRapportDescriptionService(
            IRapportDescriptionService rapportDescriptionService) {
        this.rapportDescriptionService = rapportDescriptionService;
    }

}
