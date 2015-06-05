package fr.inra.dsi.reporting.exception;

/**
 * Exception de la couche service.
 * @author gugau
 *
 */
public class ServiceException extends Exception {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructeur.
     * @param arg0
     * @param arg1
     */
    public ServiceException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    /**
     * Constructeur.
     * @param arg0
     * @param arg1
     */
    public ServiceException(String arg0) {
        super(arg0);
    }

    /**
     * Constructeur.
     * @param arg0
     * @param arg1
     */
    public ServiceException(Throwable arg0) {
        super(arg0);
    }

}
