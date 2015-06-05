package fr.inra.dsi.reporting.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.inra.dsi.reporting.dataservice.IRapportDescriptionService;
import fr.inra.dsi.reporting.dataservice.IRapportMotsClesParActiviteService;
import fr.inra.dsi.reporting.dataservice.IRapportService;
import fr.inra.dsi.reporting.dto.SearchCriteriaDto;
import fr.inra.dsi.reporting.model.RapportDescription;
import fr.inra.dsi.reporting.model.RapportMotsClesParActivite;
import fr.inra.dsi.reporting.security.INRAUser;
import fr.inra.dsi.reporting.service.ISecurityManagerService;
import fr.inra.dsi.reporting.service.ISecurityRapportMotsClesParActiviteService;
import fr.inra.dsi.reporting.util.WebConstantUtil;

/**
 * Implementation de ISecurityRapportMotsClesParActiviteService .
 * 
 * @author cebri
 */
@Service
public class SecurityRapportMotsClesParActiviteServiceImpl extends
		AbstractSecurityLayerServiceImpl<RapportMotsClesParActivite> implements
		ISecurityRapportMotsClesParActiviteService {

    //private static final Logger LOGGER = Logger.getLogger(SecurityRapportMotsClesParActiviteServiceImpl.class);

    @Autowired
    private ISecurityManagerService securityManagerService;
    
    @Autowired
    private IRapportDescriptionService rapportDescriptionService;
    
    @Autowired
    private IRapportMotsClesParActiviteService rapportService;
    
    /**
     * Constructeur.
     */
	public SecurityRapportMotsClesParActiviteServiceImpl() {
		super();
	}

	@Override
	public List<SearchCriteriaDto> createCriterias(INRAUser user) {
		RapportDescription rapportDesc = rapportDescriptionService.getRapportDescriptionForClazzAndRestCall(RapportMotsClesParActivite.class.getSimpleName(),
                WebConstantUtil.RAPPORT_MOTS_CLES_PAR_ACTIVITE_PATH);

        List<SearchCriteriaDto> listCriteria = new ArrayList<SearchCriteriaDto>();
        
        listCriteria.addAll(createCriteriaForDU(user, rapportDesc, "codeUnite", "listeUniteParticipante"));
        
        listCriteria.addAll(createCriteriaForCD(user, rapportDesc, "codiqueDepartement", "listeDepartementCopilote"));
        
        listCriteria.addAll(createCriteriaForDAR(user, rapportDesc, "codiqueDepartement"));
        
        listCriteria.addAll(createCriteriaForPC(user, rapportDesc, "codeCentre"));
        
        listCriteria.addAll(createCriteriaForCnueCnoc(user,rapportDesc, "abreviationTypeAc"));
        
        return listCriteria;
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public IRapportService<RapportMotsClesParActivite> getRapportService() {
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
    		IRapportMotsClesParActiviteService rapportService) {
        this.rapportService = rapportService;
    }

}
