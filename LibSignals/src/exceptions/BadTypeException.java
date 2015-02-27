/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 * Cette exception est lancée lorsqu'un type inconnu est passé en paramètre à une factory.
 * 
 * @author lion
 */
public class BadTypeException extends RuntimeException
{
    /**
     * Constructeur par défaut.
     */
    public BadTypeException()
    {
        super();
    }
    
    /**
     * Constructeur d'initialisation.
     * 
     * @param message Message qui sera affiché au lancement de l'exception.
     */
    public BadTypeException(String message)
    {
        super(message);
    }
    
    /**
     * Constructeur d'initialisation.
     * 
     * @param message Message qui sera affiché au lancement de l'exception.
     * @param cause Cause de l'exception (si celle-ci est lancée à partir du handler d'une autre exception).
     */
    public BadTypeException(String message, Throwable cause)
    {
        super (message, cause);
    }
    
    /**
     * Constructeur d'initialisation.
     * 
     * @param cause Cause de l'exception (si celle-ci est lancée à partir du handler d'une autre exception).
     */
    public BadTypeException(Throwable cause)
    {
        super(cause);
    }
}
