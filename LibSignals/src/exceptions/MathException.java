/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 * Cette exception est lancée lorsqu'une erreur mathématique survient.
 * Exemples:
 * <ul>
 * <li>division par 0;
 * <li>accès à une valeur hors du domaine de définition d'un objet {@link math.Fonction};
 * <li>instanciation d'un objet {@link math.Nombre} avec un module négatif;
 * <li>...
 * </ul>
 * 
 * @author lion
 */
public class MathException extends RuntimeException
{
    /**
     * Constructeur par défaut.
     */
    public MathException()
    {
        super();
    }
    
    /**
     * Constructeur d'initialisation.
     * 
     * @param message Message qui sera affiché au lancement de l'exception.
     */
    public MathException(String message)
    {
        super(message);
    }
    
    /**
     * Constructeur d'initialisation.
     * 
     * @param message Message qui sera affiché au lancement de l'exception.
     * @param cause Cause de l'exception (si celle-ci est lancée à partir du handler d'une autre exception).
     */
    public MathException(String message, Throwable cause)
    {
        super (message, cause);
    }
    
    /**
     * Constructeur d'initialisation.
     * 
     * @param cause Cause de l'exception (si celle-ci est lancée à partir du handler d'une autre exception).
     */
    public MathException(Throwable cause)
    {
        super(cause);
    }
}
