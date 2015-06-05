package fr.inra.dsi.reporting.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.inra.dsi.reporting.dto.InfosAgent;
import fr.inra.dsi.reporting.dto.RoleAgent;
import fr.inra.dsi.reporting.exception.ServiceException;
import fr.inra.dsi.reporting.exception.WebServiceException;
import fr.inra.dsi.reporting.service.IAuthenticationService;
import fr.inra.dsi.reporting.ws.IAuthenticationWebService;

/**
 * Implémentation de IAuthenticationService.
 * @author gugau
 *
 */
@Service
public class AuthenticationServiceImpl implements IAuthenticationService {
    
    /**
     * Web service d'authentification
     */
    @Autowired
    private transient IAuthenticationWebService authenticationWebService;
    
    /**
     * Constructeur par défaut.
     */
    public AuthenticationServiceImpl(){
       super();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<RoleAgent> getRolesByMatricule(String matricule) throws ServiceException {
        try {
            return authenticationWebService.getRolesByMatricule(matricule);
        } catch (WebServiceException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InfosAgent getInfosAgentByLogin(String login) throws ServiceException {
        try {
            return authenticationWebService.getInfosAgentByLogin(login);
        } catch (WebServiceException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Setter.
     */
    public void setAuthenticationWebService(
            IAuthenticationWebService authenticationWebService) {
        this.authenticationWebService = authenticationWebService;
    }

}
