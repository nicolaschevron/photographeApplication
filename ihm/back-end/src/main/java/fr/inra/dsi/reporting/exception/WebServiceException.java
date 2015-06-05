package fr.inra.dsi.reporting.exception;

/**
 * Exception de la couche d'appel aux web services
 * @author gugau
 *
 */
public class WebServiceException extends Exception {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructeur
     */
    public WebServiceException() {
        super();
    }

    /**
     * Constructeur.
     * @param arg0
     * @param arg1
     * @param arg2
     * @param arg3
     */
    public WebServiceException(String arg0, Throwable arg1, boolean arg2,
            boolean arg3) {
        super(arg0, arg1, arg2, arg3);
    }

    /**
     * Constructeur.
     * @param arg0
     * @param arg1
     */
    public WebServiceException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    /**
     * Constructeur.
     * @param arg0
     */
    public WebServiceException(String arg0) {
        super(arg0);
    }

    /**
     * Constructeur.
     * @param arg0
     */
    public WebServiceException(Throwable arg0) {
        super(arg0);
    }

}
