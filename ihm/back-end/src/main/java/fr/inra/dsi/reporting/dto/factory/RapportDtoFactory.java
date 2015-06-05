package fr.inra.dsi.reporting.dto.factory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.MessageSource;

import fr.inra.dsi.reporting.dto.RapportColonne;
import fr.inra.dsi.reporting.dto.RapportDto;
import fr.inra.dsi.reporting.model.RapportDescription;

/**
 * Factory static creant des RapportDtos.
 * 
 * @author lepra
 */
public final class RapportDtoFactory {

    /**
     * Constructeur vide.
     */
    private RapportDtoFactory() {
        super();
    }
    
    /**
     * Retourne un rapport DTO.
     * @param messageSource
     * @param rapportColonnes
     * @param rapportKeyBase
     * @param rapports
     * @param rapportDescription
     * @param clazz
     * @return
     * @throws SecurityException 
     * @throws NoSuchFieldException 
     */
    public static RapportDto getRapportDto(MessageSource messageSource, List<String> rapportColonnes, 
            String rapportKeyBase, List<?> rapports, RapportDescription rapportDescription, Class<?> clazz) throws NoSuchFieldException, SecurityException {
        RapportDto rapportDto = new RapportDto();
        List<RapportColonne> colonnes = new ArrayList<RapportColonne>();
        for (String cle : rapportColonnes) {
            Field field = clazz.getDeclaredField(cle);
            String type = RapportColonne.TYPE_STRING;
            if(field.getType() == Date.class){
                type = RapportColonne.TYPE_DATE;
            }
            colonnes.add(new RapportColonne(messageSource.getMessage(rapportKeyBase + cle, null , null), cle, type));
        }
        rapportDto.setRapportDescription(rapportDescription);
        rapportDto.setColonnesRapport(colonnes);
        rapportDto.setLignesRapport(rapports);
        return rapportDto;
    }
    
}
