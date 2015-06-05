package fr.inra.dsi.reporting.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.inra.dsi.reporting.dataservice.IRapportChampThematiqueParActiviteService;
import fr.inra.dsi.reporting.dataservice.IRapportDescriptionService;
import fr.inra.dsi.reporting.dataservice.IRapportService;
import fr.inra.dsi.reporting.dto.SearchCriteriaDto;
import fr.inra.dsi.reporting.model.RapportChampThematiqueParActivite;
import fr.inra.dsi.reporting.model.RapportDescription;
import fr.inra.dsi.reporting.security.INRAUser;
import fr.inra.dsi.reporting.service.ISecurityManagerService;
import fr.inra.dsi.reporting.service.ISecurityRapportChampThematiqueParActiviteService;
import fr.inra.dsi.reporting.util.WebConstantUtil;

/**
 * Implementation de ISecurityRapportChampThematiqueParActiviteService
 * @author gugau
 *
 */
@Service
public class SecurityRapportChampThematiqueParActiviteServiceImpl
    extends AbstractSecurityLayerServiceImpl<RapportChampThematiqueParActivite>
    implements ISecurityRapportChampThematiqueParActiviteService {

    @Autowired
    private ISecurityManagerService securityManagerService;
    
    @Autowired
    private IRapportDescriptionService rapportDescriptionService;
    
    @Autowired
    private IRapportChampThematiqueParActiviteService rapportService;
    
    /**
     * Constructeur.
     */
    public SecurityRapportChampThematiqueParActiviteServiceImpl() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SearchCriteriaDto> createCriterias(INRAUser user) {
        
        RapportDescription rapportDesc = rapportDescriptionService.getRapportDescriptionForClazzAndRestCall(RapportChampThematiqueParActivite.class.getSimpleName(),
                WebConstantUtil.RAPPORT_LISTE_CT_PARTICIPATION_UNITE_PATH);
        
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
    public IRapportService<RapportChampThematiqueParActivite> getRapportService() {
        return rapportService;
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

    /**
     * Setter
     */
    public void setRapportService(
            IRapportChampThematiqueParActiviteService rapportService) {
        this.rapportService = rapportService;
    }

}
