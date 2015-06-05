package fr.inra.dsi.reporting.dto;

/**
 * Représente une recherche.
 * @author gugau
 *
 */
public class SearchDto {

    private SearchOrder order;
    private String orderField;
    private String keywords;
    
    /**
     * Constructeur
     */
    public SearchDto() {
        super();
    }

    /**
     * Getter.
     */
    public SearchOrder getOrder() {
        return order;
    }

    /**
     * Setter.
     */
    public void setOrder(SearchOrder order) {
        this.order = order;
    }

    /**
     * Getter.
     */
    public String getOrderField() {
        return orderField;
    }

    /**
     * Setter.
     */
    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }

    /**
     * Getter.
     */
    public String getKeywords() {
        return keywords;
    }
    
    /**
     * Setter.
     */
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
}
