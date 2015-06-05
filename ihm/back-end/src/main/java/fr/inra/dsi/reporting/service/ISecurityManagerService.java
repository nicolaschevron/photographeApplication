package fr.inra.dsi.reporting.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import fr.inra.dsi.reporting.model.RapportDescription;
import fr.inra.dsi.reporting.security.INRAUser;

/**
 * Interface du service de gestion des droits/sécurité en fonction
 * des roles de l'utilisateur
 * @author cebri
 *
 */
public interface ISecurityManagerService {
	
	/**
	 * Vérifie qu'un utilisateur à le droit de consulter les données d'un rapport
	 * @param inraUser l'utilisateur
	 * @param clazz le nom de la classe de l'entity du rapport
	 * @param restCall l'url d'action du rapport
	 * @return
	 */
	@Transactional
	boolean hasPermissionReadRapport(INRAUser inraUser, String clazz, String restCall);
	
	/**
	 * Indique si oui ou non un rôle peut consulter un rapport
	 * @param rapport le rapport
	 * @param role le rôle
	 * @return
	 */
	boolean isRoleAllowed(RapportDescription rapport, String role);
	
	/**
	 * Renvoie tous les rapports auxquels inraUser à accès en fonction de ses rôles
	 * @param inraUser
	 * @return
	 */
    @Transactional
	List<RapportDescription> getAllRapportDescription(INRAUser inraUser);
}
