/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 * Cette exception est lancée lors d'une opération sur deux signaux dont les pas sont incompatibles.
 * 
 * @author lion
 */
public class BadStepsException extends RuntimeException
{
    /**
     * Constructeur par défaut.
     */
    public BadStepsException()
    {
        super();
    }
    
    /**
     * Constructeur d'initialisation.
     * 
     * @param message Message qui sera affiché au lancement de l'exception.
     */
    public BadStepsException(String message)
    {
        super(message);
    }
    
    /**
     * Constructeur d'initialisation.
     * 
     * @param message Message qui sera affiché au lancement de l'exception.
     * @param cause Cause de l'exception (si celle-ci est lancée à partir du handler d'une autre exception).
     */
    public BadStepsException(String message, Throwable cause)
    {
        super (message, cause);
    }
    
    /**
     * Constructeur d'initialisation.
     * 
     * @param cause Cause de l'exception (si celle-ci est lancée à partir du handler d'une autre exception).
     */
    public BadStepsException(Throwable cause)
    {
        super(cause);
    }
}
