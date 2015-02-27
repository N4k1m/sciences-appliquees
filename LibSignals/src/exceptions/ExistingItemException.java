/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 * Cette exception est lancée par la classe {@link guis.SignalPanel} lorsque le nom du signal à afficher est déjà
 * utilisé sur le graphique.
 * 
 * @author lion
 */
public class ExistingItemException extends RuntimeException
{
    /**
     * Constructeur par défaut.
     */
    public ExistingItemException()
    {
        super();
    }
    
    /**
     * Constructeur d'initialisation.
     * 
     * @param message Message qui sera affiché au lancement de l'exception.
     */
    public ExistingItemException(String message)
    {
        super(message);
    }
    
    /**
     * Constructeur d'initialisation.
     * 
     * @param message Message qui sera affiché au lancement de l'exception.
     * @param cause Cause de l'exception (si celle-ci est lancée à partir du handler d'une autre exception).
     */
    public ExistingItemException(String message, Throwable cause)
    {
        super (message, cause);
    }
    
    /**
     * Constructeur d'initialisation.
     * 
     * @param cause Cause de l'exception (si celle-ci est lancée à partir du handler d'une autre exception).
     */
    public ExistingItemException(Throwable cause)
    {
        super(cause);
    }
}
