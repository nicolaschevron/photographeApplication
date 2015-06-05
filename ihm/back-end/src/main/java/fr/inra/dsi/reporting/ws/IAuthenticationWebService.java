package fr.inra.dsi.reporting.ws;

import java.util.List;

import fr.inra.dsi.reporting.dto.InfosAgent;
import fr.inra.dsi.reporting.dto.RoleAgent;
import fr.inra.dsi.reporting.exception.ServiceException;
import fr.inra.dsi.reporting.exception.WebServiceException;

/**
 * Interface des web services concernant l'authentification/l'identité des
 * utilisateur.
 * @author gugau
 *
 */
public interface IAuthenticationWebService {

    /**
     * Renvoie les rôles de l'agent.
     * @param matricule le matricule de l'agent
     * @return
     * @throws ServiceException
     */
    List<RoleAgent> getRolesByMatricule(String matricule) throws WebServiceException;
    
    /**
     * Renvoie les informations de l'agent (nom, prénom, matricule, ...)
     * @param login le login CAS de l'agent
     * @return
     * @throws ServiceException
     */
    InfosAgent getInfosAgentByLogin(String login) throws WebServiceException;
    
}
