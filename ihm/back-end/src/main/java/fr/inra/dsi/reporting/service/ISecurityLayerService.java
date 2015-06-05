package fr.inra.dsi.reporting.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import fr.inra.dsi.reporting.dto.SearchDto;
import fr.inra.dsi.reporting.model.IRapport;
import fr.inra.dsi.reporting.security.INRAUser;

/**
 * Interface des services "sécurisés" pour les rapports.
 * 
 * "Sécurisé" car la gestion des droits d'accès aux données des rapports y est implémentée
 * (en fonction des rôles).
 * 
 * @author gugau
 *
 * @param <T>
 */
public interface ISecurityLayerService<T extends IRapport> {

    /**
     * Renvoie toutes les données du rapports auxquelles l'utilisateurs à droit.
     * @param user l'utilisateur
     * @param clazz
     * @return
     */
    @Transactional
    List<T> getAllRapports(INRAUser user, Class<T> clazz);
    
    /**
     * Effectu une recherche et renvoie toutes les données du rapports auxquelles l'utilisateurs à droit.
     * @param user l'utilisateur
     * @param search les critères de recherche
     * @param clazz
     * @param propertyNames les propriétés de l'entité sur lesquelles réaliser la recherche
     * @return
     */
    @Transactional(readOnly=true)
    List<T> search(INRAUser user, SearchDto search, Class<T> clazz, List<String> propertyNames);

}
