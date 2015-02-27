/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 * Cette exception est lancée au sein de la classe {@link modulation.Constellation} pour un symbole non présent
 * dans le diagramme.
 * 
 * @author lion
 */
public class BadSymbolException extends RuntimeException
{
    /**
     * Le symbole en question.
     */
    private String symbole;
    
    /**
     * Constructeur par défaut.
     */
    public BadSymbolException()
    {
        super();
    }
    
    /**
     * Constructeur d'initialisation.
     * 
     * @param message Message qui sera affiché au lancement de l'exception.
     */
    public BadSymbolException(String message)
    {
        super(message);
    }
    
    /**
     * Constructeur d'initialisation.
     * 
     * @param message Message qui sera affiché au lancement de l'exception.
     * @param symbole Le symbole qui n'a pas été trouvé.
     */
    public BadSymbolException(String message, String symbole)
    {
        super(message);
        this.symbole = symbole;
    }
    
    /**
     * Constructeur d'initialisation.
     * 
     * @param message Message qui sera affiché au lancement de l'exception.
     * @param cause Cause de l'exception (si celle-ci est lancée à partir du handler d'une autre exception).
     */
    public BadSymbolException(String message, Throwable cause)
    {
        super (message, cause);
    }
    
    /**
     * Constructeur d'initialisation.
     * 
     * @param cause Cause de l'exception (si celle-ci est lancée à partir du handler d'une autre exception).
     */
    public BadSymbolException(Throwable cause)
    {
        super(cause);
    }
    
    /**
     * Retourne le symbole qui est la cause de l'erreur.
     * 
     * @return Le symbole en question.
     */
    public String getSymbole()
    {
        return symbole;
    }
}
