package fr.inra.dsi.reporting.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.inra.dsi.reporting.dto.RapportDto;
import fr.inra.dsi.reporting.dto.SearchDto;
import fr.inra.dsi.reporting.exception.AbstractControllerException;
import fr.inra.dsi.reporting.exception.ValidationException;
import fr.inra.dsi.reporting.model.RapportChampsFonctionnelParActivite;
import fr.inra.dsi.reporting.security.Constant;
import fr.inra.dsi.reporting.service.ISecurityRapportChampFonctionnelParActiviteService;
import fr.inra.dsi.reporting.util.WebConstantUtil;

/**
 * Controlleur pour le rapport des participations des agents par activités.
 * 
 * @author lepra
 */
@Controller
@RequestMapping(WebConstantUtil.RAPPORT_LISTE_CF_PATH)
public class ListeChampsFonctionnelsParActiviteController
    extends AbstractRapportController<RapportChampsFonctionnelParActivite>{

    @Autowired
    private ISecurityRapportChampFonctionnelParActiviteService rapportChampFonctionnelService;
    
    /**
     * Constructeur.
     */
    public ListeChampsFonctionnelsParActiviteController() {
        super(RapportChampsFonctionnelParActivite.class, WebConstantUtil.RAPPORT_LISTE_CF_PATH,
                WebConstantUtil.CLE_PROP_RAPPORT_LISTE_CF,
                WebConstantUtil.RAPPORT_LISTE_CF_COLONNE_KEY);
    }
    
    /**
     * {@inheritDoc}
     */
    @PreAuthorize("hasRole('"+Constant.ROLE_ADMIN+"')"
            + " or hasPermission('"+WebConstantUtil.RAPPORT_LISTE_CF_PATH+"', 'RapportChampsFonctionnelParActivite', '"
            + Constant.PERMISSION_READ_REPORT+"')")
    public RapportDto getRapport(HttpServletRequest request) throws AbstractControllerException {
        return super.getRapport(request);
    }
    
    /**
     * {@inheritDoc}
     */
    @PreAuthorize("hasRole('"+Constant.ROLE_ADMIN+"')"
            + " or hasPermission('"+WebConstantUtil.RAPPORT_LISTE_CF_PATH+"', 'RapportChampsFonctionnelParActivite', '"
            + Constant.PERMISSION_READ_REPORT+"')")
    public RapportDto exportRapport(SearchDto search, HttpServletRequest request) throws AbstractControllerException {
        return super.exportRapport(search, request);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<RapportChampsFonctionnelParActivite> getAllData(
            HttpServletRequest request) throws ValidationException {
        return rapportChampFonctionnelService.getAllRapports(getUserInra(), RapportChampsFonctionnelParActivite.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RapportChampsFonctionnelParActivite> searchData(
            SearchDto search, HttpServletRequest request)
            throws ValidationException {
        return rapportChampFonctionnelService.search(getUserInra(), search, RapportChampsFonctionnelParActivite.class,
                WebConstantUtil.RAPPORT_LISTE_CF_COLONNE_KEY);
    }

}
