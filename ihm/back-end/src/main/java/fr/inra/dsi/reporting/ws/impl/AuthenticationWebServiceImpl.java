package fr.inra.dsi.reporting.ws.impl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.xml.namespace.QName;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import fr.inra.dsi.activites.endpoint.RAAuthentificationUtilisateur;
import fr.inra.dsi.activites.endpoint.RoleApplicatif;
import fr.inra.dsi.activites.endpoint.ServicesAuthentification;
import fr.inra.dsi.activites.endpoint.Utilisateur;
import fr.inra.dsi.activites.endpoint.UtilisateurInconnuException_Exception;
import fr.inra.dsi.activites.endpoint.WSMandatsIndisponibleException_Exception;
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
public class AuthenticationWebServiceImpl implements IAuthenticationWebService {
    
	private static final Logger LOGGER = Logger.getLogger(AuthenticationWebServiceImpl.class);
	
	@Autowired
	private IAuthenticationService authService;
	
    /**
     * Constructeur par défaut.
     */
    public AuthenticationWebServiceImpl(){
       super();
    }
    
    private transient RAAuthentificationUtilisateur authenticationService;
    @Value("${service.authent.wsdl.url}")
    private transient String authentServiceWsdlUrl;
    @Value("${service.authent.wsdl.namespace}")
    private transient String authentServiceWsdlNamespace;
    @Value("${service.authent.wsdl.localpart}")
    private transient String authentServiceWsdlLocalPart;
    
    /**
     * Initialisation.
     * @throws MalformedURLException
     */
    @PostConstruct
    public void postconstruct() throws MalformedURLException{
        authenticationService = new ServicesAuthentification(
                new URL(authentServiceWsdlUrl),
                new QName(authentServiceWsdlNamespace, authentServiceWsdlLocalPart))
                    .getRAAuthentificationUtilisateurPort();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<RoleAgent> getRolesByMatricule(String matricule) throws WebServiceException {
        List<RoleApplicatif> rolesApp;
        try {
            rolesApp = authenticationService.getRolesByMatricule(matricule);
        } catch (WSMandatsIndisponibleException_Exception e) {
            throw new WebServiceException("Erreur de la récupération des rôles de l'agent", e);
        }
        List<RoleAgent> result = new ArrayList<RoleAgent>();
        for(RoleApplicatif role : rolesApp){
            RoleAgent roleAgent = new RoleAgent(role.getRole().getRole(), role.getCodeEntite());
            result.add(roleAgent);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InfosAgent getInfosAgentByLogin(String login) throws WebServiceException {
        Utilisateur uti;
        try {
            uti = authenticationService.getUtilisateur(login);
            LOGGER.info("Utilisateur retourne : " + uti.toString());
        } catch (UtilisateurInconnuException_Exception
                | WSMandatsIndisponibleException_Exception e) {
            throw new WebServiceException("Erreur d'accès aux informations de l'agent", e);
        }
        InfosAgent result = new InfosAgent();
        result.setMatricule(uti.getMatricule());
        result.setNom(uti.getNom());
        result.setPrenom(uti.getPrenom());
        return result;
    }

}
