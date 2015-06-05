package fr.inra.dsi.reporting.dataservice.impl;

import org.springframework.stereotype.Service;

import fr.inra.dsi.reporting.dataservice.IRapportMotsClesParActiviteService;
import fr.inra.dsi.reporting.model.RapportMotsClesParActivite;

/**
 * Implémentation de IRapportMotsClesParActiviteService
 * @author gugau
 *
 */
@Service
public class RapportMotsClesParActiviteServiceImpl
    extends RapportServiceImpl<RapportMotsClesParActivite>
    implements IRapportMotsClesParActiviteService {

    /**
     * Constructeur
     */
    public RapportMotsClesParActiviteServiceImpl() {
        super();
    }


}
