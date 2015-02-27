/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 * Cette exception est lancée lors de l'instanciation d'un objet {@link signaux.SignalBinaire} si le paramètre de
 * type {@link signaux.Discretiseur} possède un sample trop petit pour la taille du message.
 * 
 * @author lion
 */
public class SampleTooSmallException extends RuntimeException
{
    /**
     * Constructeur par défaut.
     */
    public SampleTooSmallException()
    {
        super();
    }
    
    /**
     * Constructeur d'initialisation.
     * 
     * @param message Message qui sera affiché au lancement de l'exception.
     */
    public SampleTooSmallException(String message)
    {
        super(message);
    }
    
    /**
     * Constructeur d'initialisation.
     * 
     * @param message Message qui sera affiché au lancement de l'exception.
     * @param cause Cause de l'exception (si celle-ci est lancée à partir du handler d'une autre exception).
     */
    public SampleTooSmallException(String message, Throwable cause)
    {
        super(message, cause);
    }
    
    /**
     * Constructeur d'initialisation.
     * 
     * @param cause Cause de l'exception (si celle-ci est lancée à partir du handler d'une autre exception).
     */
    public SampleTooSmallException(Throwable cause)
    {
        super(cause);
    }
}
