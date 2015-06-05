package fr.inra.dsi.reporting.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.inra.dsi.reporting.dataservice.IRapportDescriptionService;
import fr.inra.dsi.reporting.dataservice.IRapportParticipationAgentSurActiviteService;
import fr.inra.dsi.reporting.dataservice.IRapportService;
import fr.inra.dsi.reporting.dto.SearchCriteriaDto;
import fr.inra.dsi.reporting.model.RapportDescription;
import fr.inra.dsi.reporting.model.RapportParticipationAgentSurActivite;
import fr.inra.dsi.reporting.security.INRAUser;
import fr.inra.dsi.reporting.service.ISecurityManagerService;
import fr.inra.dsi.reporting.service.ISecurityRapportParticipationAgentSurActiviteService;
import fr.inra.dsi.reporting.util.WebConstantUtil;

/**
 * Implémentation de ISecurityRapportParticipationAgentSurActiviteService .
 * @author gugau
 *
 */
@Service
public class SecurityRapportParticipationAgentSurActiviteServiceImpl
    extends AbstractSecurityLayerServiceImpl<RapportParticipationAgentSurActivite>
    implements ISecurityRapportParticipationAgentSurActiviteService {

    @Autowired
    private ISecurityManagerService securityManagerService;
    
    @Autowired
    private IRapportDescriptionService rapportDescriptionService;
    
    @Autowired
    private IRapportParticipationAgentSurActiviteService rapportService;
    
    /**
     * Constructeur.
     */
    public SecurityRapportParticipationAgentSurActiviteServiceImpl() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SearchCriteriaDto> createCriterias(INRAUser user) {
        RapportDescription rapportDesc = rapportDescriptionService.getRapportDescriptionForClazzAndRestCall(RapportParticipationAgentSurActivite.class.getSimpleName(),
                WebConstantUtil.RAPPORT_LISTE_PARTICIPATION_AGENT_PATH);
        
        List<SearchCriteriaDto> listCriteria = new ArrayList<SearchCriteriaDto>();
        
        listCriteria.addAll(createCriteriaForCD(user, rapportDesc, "codiqueDepartement", "listeDepartementCoPilote"));
        
        listCriteria.addAll(createCriteriaForDAR(user, rapportDesc, "codiqueDepartement"));
        
        listCriteria.addAll(createCriteriaForCnueCnoc(user, rapportDesc, "abreviationTypeAc"));
        
        listCriteria.addAll(createCriteriaForDU(user, rapportDesc, "codeUniteLeader", "listeUniteParticipante"));

        listCriteria.addAll(createCriteriaForPC(user, rapportDesc, "codeCentre"));
        
        return listCriteria;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IRapportService<RapportParticipationAgentSurActivite> getRapportService() {
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
            IRapportParticipationAgentSurActiviteService rapportService) {
        this.rapportService = rapportService;
    }


}
