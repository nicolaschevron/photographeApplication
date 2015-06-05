package fr.inra.dsi.reporting.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Classe Model/Entity pour les rapports ChampThematique.
 * 
 * @author lepra
 */
@Entity
@Table(name = "RAPPORT_CHAMP_THEMATIQUE")
public class RapportChampThematique implements IRapport {

    public static final int DESC_LIMIT = 16350;
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rapport_champ_thematique_seq_gen")
    @SequenceGenerator(name = "rapport_champ_thematique_seq_gen", sequenceName = "rapport_champ_thematique_id_seq")
    private int identifiant;
    
    @Column(name = "code")
    private int code;
    @Column(name = "sigle")
    private String sigle;
    @Column(name = "sigle_num")
    private int sigleNum;
    @Column(name = "description", length = DESC_LIMIT)
    private String description;
    @Column(name = "intitule")
    private String intitule;
    @Column(name = "date_debut_validite")
    private Date dateDebut;
    @Column(name = "date_fin_validite")
    private Date dateFin;
    @Column(name = "date_fin_effective")
    private Date dateFinEffective;
    
    /**
     * Constructeur.
     */
    public RapportChampThematique() {
        super();
    }

    /**
     * Getter
     */
    public int getIdentifiant() {
        return identifiant;
    }

    /**
     * Setter
     */
    public void setIdentifiant(int identifiant) {
        this.identifiant = identifiant;
    }

    /**
     * Getter
     */
    public int getCode() {
        return code;
    }

    /**
     * Setter
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * Getter
     */
    public String getSigle() {
        return sigle;
    }

    /**
     * Setter
     */
    public void setSigle(String sigle) {
        this.sigle = sigle;
    }

    /**
     * Getter
     */
    public int getSigleNum() {
        return sigleNum;
    }

    /**
     * Setter
     */
    public void setSigleNum(int num) {
        this.sigleNum = num;
    }

    /**
     * Getter
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter
     */
    public void setDescription(String description) {
        if ((description != null) && (description.length() > DESC_LIMIT)) {
            this.description = description.substring(0, DESC_LIMIT);
        } else {
            this.description = description;
        }
    }

    /**
     * Getter
     */
    public String getIntitule() {
        return intitule;
    }

    /**
     * Setter
     */
    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    /**
     * Getter
     */
    public Date getDateDebut() {
        return dateDebut;
    }

    /**
     * Setter
     */
    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    /**
     * Getter
     */
    public Date getDateFin() {
        return dateFin;
    }

    /**
     * Setter
     */
    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    /**
     * Getter
     */
    public Date getDateFinEffective() {
        return dateFinEffective;
    }

    /**
     * Setter
     */
    public void setDateFinEffective(Date dateFinEffective) {
        this.dateFinEffective = dateFinEffective;
    }
        
}
