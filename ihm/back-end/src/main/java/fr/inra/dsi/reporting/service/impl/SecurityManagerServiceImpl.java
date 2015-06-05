package fr.inra.dsi.reporting.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import fr.inra.dsi.reporting.dao.IRapportDescriptionDao;
import fr.inra.dsi.reporting.dataservice.IRapportListeActivitesParUniteService;
import fr.inra.dsi.reporting.model.RapportDescription;
import fr.inra.dsi.reporting.security.INRAUser;
import fr.inra.dsi.reporting.service.ISecurityManagerService;

/**
 * Implémentation de ISecurityManagerService
 * @author gugau
 *
 */
@Service
public class SecurityManagerServiceImpl implements ISecurityManagerService{
	
	@Autowired
    private transient IRapportListeActivitesParUniteService listeActivitesParUniteService;

    @Autowired
    private transient IRapportDescriptionDao rapportDescriptionDao;

	/**
     * {@inheritDoc}
     */
    @Override
    public boolean hasPermissionReadRapport(INRAUser inraUser, String clazz, String restCall) {
        List<String> roleNames = inraUser.getRoleNames();
        RapportDescription rapportDesc = rapportDescriptionDao.findByClazzAndRestCall(clazz, restCall);
        return reportRestrictionAllowAnyOfRoles(rapportDesc, roleNames);
    }
    
    /**
     * Test si un rapport autorise au moins un des rôles de la liste donnée
     * @param rapportDesc le rapport
     * @param roleNames la liste des rôles
     * @return
     */
    private boolean reportRestrictionAllowAnyOfRoles(RapportDescription rapportDesc, List<String> roleNames){
        if (StringUtils.isEmpty(rapportDesc.getRestrictionRoles())) {
            return true;
        } else {
            String[] rapportRoleNames = rapportDesc.getRestrictionRoles().split(",");
            for(String rapportRole : rapportRoleNames){
                if(roleNames.contains(rapportRole)){
                    return true; 
                }
            }
        }
        return false;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<RapportDescription> getAllRapportDescription(INRAUser inraUser) {
        List<RapportDescription> allReports = rapportDescriptionDao.findAllActive();
        List<String> roleNames = inraUser.getRoleNames();
        
        List<RapportDescription> result = new ArrayList<RapportDescription>();
        for(RapportDescription rapportDesc : allReports){
            if(reportRestrictionAllowAnyOfRoles(rapportDesc, roleNames)){
                result.add(rapportDesc);
            }
        }
        
        return result ;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isRoleAllowed(RapportDescription rapport, String role) {
        String[] rapportRoleNames = rapport.getRestrictionRoles().split(",");
        for(String rapportRole : rapportRoleNames){
            if(role.equals(rapportRole)){
                return true; 
            }
        }
        return false;
    }

    /**
     * Setter
     */
    public void setListeActivitesParUniteService(
            IRapportListeActivitesParUniteService listeActivitesParUniteService) {
        this.listeActivitesParUniteService = listeActivitesParUniteService;
    }

    /**
     * Setter
     */
    public void setRapportDescriptionDao(
            IRapportDescriptionDao rapportDescriptionDao) {
        this.rapportDescriptionDao = rapportDescriptionDao;
    }

}
