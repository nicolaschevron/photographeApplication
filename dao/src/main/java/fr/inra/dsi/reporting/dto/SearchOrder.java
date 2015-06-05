package fr.inra.dsi.reporting.dto;

/**
 * Représente un ordre de tri d'une recherche
 * @author gugau
 *
 */
public enum SearchOrder {
    
    /**
     * Tri ascendant
     */
    ASC("asc"),
    /**
     * Tri descendant
     */
    DESC("desc");
    
    private String order;
    
    /**
     * Constructeur
     * @param order
     */
    SearchOrder(String order){
        this.order = order;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString(){
        return order;
    }
}
