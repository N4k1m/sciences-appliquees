/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 * Cette exception est lancée par la méthode {@link signaux.SignalAnalogique#setDiscretiseur(signaux.Discretiseur)}
 * si le signal en question ne possède pas d'objet {@link math.Fonction}.
 * Le "zoom infini" n'est pas possible si la variable membre {@link signaux.SignalAnalogique#fonction} vaut null.
 * 
 * @author lion
 */
public class NoFunctionException extends Exception
{
    /**
     * Constructeur par défaut.
     */
    public NoFunctionException()
    {
        super();
    }
    
    /**
     * Constructeur d'initialisation.
     * 
     * @param message Message qui sera affiché au lancement de l'exception.
     */
    public NoFunctionException(String message)
    {
        super(message);
    }
    
    /**
     * Constructeur d'initialisation.
     * 
     * @param message Message qui sera affiché au lancement de l'exception.
     * @param cause Cause de l'exception (si celle-ci est lancée à partir du handler d'une autre exception).
     */
    public NoFunctionException(String message, Throwable cause)
    {
        super (message, cause);
    }
    
    /**
     * Constructeur d'initialisation.
     * 
     * @param cause Cause de l'exception (si celle-ci est lancée à partir du handler d'une autre exception).
     */
    public NoFunctionException(Throwable cause)
    {
        super(cause);
    }
}
