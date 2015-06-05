package fr.inra.dsi.reporting.bean;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.security.util.FieldUtils;

import fr.inra.dsi.reporting.dto.RapportColonne;
import fr.inra.dsi.reporting.dto.RapportDto;

/**
 * Classe qui réalise la sérialisation en CSV d'un rapport
 * @author gugau
 *
 */
public class RapportToCsvMessageConverter extends AbstractHttpMessageConverter<RapportDto> {
    
	public static final char CSV_SEPARATOR_LINE = '\n';

	public static final char CSV_SEPARATOR = ';';

	/**
     * Media type
     */
    private static final MediaType MEDIA_TYPE = new MediaType("text", "csv", Charset.forName("utf-8"));
    
    /**
     * Constante
     */
    public static final CSVFormat CSV_FILE_FORMAT = CSVFormat.DEFAULT.withRecordSeparator(CSV_SEPARATOR_LINE).withDelimiter(CSV_SEPARATOR);
    
    /**
     * Constructeur
     */
    public RapportToCsvMessageConverter(){
        super(MEDIA_TYPE);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canRead(Class<?> clazz, MediaType mediaType){
        return false;//Ne gère par la désérialisation
    }
    
    /**
     * Non implémenté.
     */
    @Override
    protected RapportDto readInternal(Class<? extends RapportDto> clazz,
            HttpInputMessage inputMessage) throws IOException {
        throw new UnsupportedOperationException("Non implémenté");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean supports(Class<?> clazz) {
        return RapportDto.class.equals(clazz);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void writeInternal(RapportDto rapport, HttpOutputMessage outputMessage)
            throws IOException {
        outputMessage.getHeaders().setContentType(MEDIA_TYPE);
        outputMessage.getHeaders().set("Content-Disposition", "attachment; filename=export.csv");
        OutputStream out = outputMessage.getBody();
        
        CSVPrinter csvFilePrinter = new CSVPrinter(new PrintWriter(out), CSV_FILE_FORMAT);
        
        //Ajout des entêtes du CSV
        for(RapportColonne colonne : rapport.getColonnesRapport()){
            csvFilePrinter.print(colonne.getLibelle());
        }
        csvFilePrinter.println();
        
        //Ajout des données
        for(Object ligne : rapport.getLignesRapport()){//Parcours des lignes du rapport
            for(RapportColonne colonne : rapport.getColonnesRapport()){//Itération sur les colonnes
                try {
                    //On print la valeur de la colonne (attribut ayant même nom que la clé de la colonne)
                    Object fieldValue = FieldUtils.getFieldValue(ligne, colonne.getCle());
                    if(fieldValue instanceof Date) {
                        DateFormat dateFormatFr = new SimpleDateFormat("dd/MM/yyyy", Locale.FRENCH);
                        csvFilePrinter.print(dateFormatFr.format(fieldValue));
                    } else {
                        csvFilePrinter.print(fieldValue);
                    }
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    throw new HttpMessageNotWritableException("Erreur de lecture de l'attribut pour la colonne '"+colonne.getCle()+"'", e);
                }
            }
            csvFilePrinter.println();
        }
        
        csvFilePrinter.close();
        out.close();
    }

    
}
