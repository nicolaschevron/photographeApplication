package fr.inra.dsi.reporting.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.inra.dsi.reporting.dataservice.IRapportDescriptionService;
import fr.inra.dsi.reporting.dataservice.IRapportParticipationAgtChampThematiqueService;
import fr.inra.dsi.reporting.dataservice.IRapportService;
import fr.inra.dsi.reporting.dto.SearchCriteriaDto;
import fr.inra.dsi.reporting.model.RapportDescription;
import fr.inra.dsi.reporting.model.RapportParticipationAgentSurActivite;
import fr.inra.dsi.reporting.model.RapportParticipationAgtChampThematique;
import fr.inra.dsi.reporting.security.INRAUser;
import fr.inra.dsi.reporting.service.ISecurityRapportParticipationAgtChampThematiqueService;
import fr.inra.dsi.reporting.util.WebConstantUtil;

/**
 * Implémentation de ISecurityRapportParticipationAgtChampThematiqueService
 * @author gugau
 *
 */
@Service
public class SecurityRapportParticipationAgtChampThematiqueServiceImpl
        extends AbstractSecurityLayerServiceImpl<RapportParticipationAgtChampThematique>
        implements ISecurityRapportParticipationAgtChampThematiqueService {
    
    @Autowired
    private IRapportDescriptionService rapportDescriptionService;
    
    @Autowired
    private IRapportParticipationAgtChampThematiqueService rapportService;
    
    /**
     * Constructeur
     */
    public SecurityRapportParticipationAgtChampThematiqueServiceImpl() {
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
        
        listCriteria.addAll(createCriteriaForCD(user, rapportDesc, "codiqueDepartementAgentRpCt"));
        
        listCriteria.addAll(createCriteriaForDAR(user, rapportDesc, "codiqueDepartementAgentRpCt"));
        
        listCriteria.addAll(createCriteriaForDU(user, rapportDesc, "codeUniteAgentRpCt"));

        listCriteria.addAll(createCriteriaForPC(user, rapportDesc, "codeCentreRpCt"));
        
        return listCriteria;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IRapportService<RapportParticipationAgtChampThematique> getRapportService() {
        return rapportService;
    }

}
