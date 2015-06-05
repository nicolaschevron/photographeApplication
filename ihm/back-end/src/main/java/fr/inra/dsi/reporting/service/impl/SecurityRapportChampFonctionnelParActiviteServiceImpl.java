package fr.inra.dsi.reporting.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.inra.dsi.reporting.dataservice.IRapportChampFonctionnelParActiviteService;
import fr.inra.dsi.reporting.dataservice.IRapportDescriptionService;
import fr.inra.dsi.reporting.dataservice.IRapportService;
import fr.inra.dsi.reporting.dto.SearchCriteriaDto;
import fr.inra.dsi.reporting.model.RapportChampsFonctionnelParActivite;
import fr.inra.dsi.reporting.model.RapportDescription;
import fr.inra.dsi.reporting.security.INRAUser;
import fr.inra.dsi.reporting.service.ISecurityManagerService;
import fr.inra.dsi.reporting.service.ISecurityRapportChampFonctionnelParActiviteService;
import fr.inra.dsi.reporting.util.WebConstantUtil;

/**
 * Implementation de ISecurityRapportParticipationAgentSurActiviteService .
 * 
 * @author lepra
 */
@Service
public class SecurityRapportChampFonctionnelParActiviteServiceImpl 
        extends AbstractSecurityLayerServiceImpl<RapportChampsFonctionnelParActivite> 
        implements ISecurityRapportChampFonctionnelParActiviteService {

    @Autowired
    private ISecurityManagerService securityManagerService;
    
    @Autowired
    private IRapportDescriptionService rapportDescriptionService;
    
    @Autowired
    private IRapportChampFonctionnelParActiviteService rapportService;
    
    /**
     * Constructeur.
     */
    public SecurityRapportChampFonctionnelParActiviteServiceImpl() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SearchCriteriaDto> createCriterias(INRAUser user) {
        RapportDescription rapportDesc = rapportDescriptionService.getRapportDescriptionForClazzAndRestCall(RapportChampsFonctionnelParActivite.class.getSimpleName(),
                WebConstantUtil.RAPPORT_LISTE_CF_PATH);
        
        List<SearchCriteriaDto> listCriteria = new ArrayList<SearchCriteriaDto>();
        
        listCriteria.addAll(createCriteriaForCD(user, rapportDesc, "codiqueDepartement", "listeDepartementCoPilote"));
        
        listCriteria.addAll(createCriteriaForDAR(user, rapportDesc, "codiqueDepartement"));
        
        listCriteria.addAll(createCriteriaForCnueCnoc(user, rapportDesc, "abreviationTypeAc"));
        
        listCriteria.addAll(createCriteriaForDU(user, rapportDesc, "codeUnite"));

        listCriteria.addAll(createCriteriaForPC(user, rapportDesc, "codeCentre"));
        
        return listCriteria;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IRapportService<RapportChampsFonctionnelParActivite> getRapportService() {
        return rapportService;
    }

    /**
     * Setter.
     * @param securityManagerService
     */
    public void setSecurityManagerService(
            ISecurityManagerService securityManagerService) {
        this.securityManagerService = securityManagerService;
    }

    /**
     * Setter.
     * @param rapportDescriptionService
     */
    public void setRapportDescriptionService(
            IRapportDescriptionService rapportDescriptionService) {
        this.rapportDescriptionService = rapportDescriptionService;
    }

    /**
     * Setter.
     * @param rapportService
     */
    public void setRapportService(
            IRapportChampFonctionnelParActiviteService rapportService) {
        this.rapportService = rapportService;
    }


}
