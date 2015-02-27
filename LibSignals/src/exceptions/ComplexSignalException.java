/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

import math.Nombre;

/**
 * Cette exception est lancée par la classe {@link guis.SignalPanel} lors de la tentative d'affichage d'un signal
 * à valeurs complexes.
 * 
 * @author lion
 */
public class ComplexSignalException extends RuntimeException
{
    /**
     * Le nom de l'objet Signal à valeurs complexes qui voulait être affiché.
     */
    private String signal;
    
    /**
     * Indice de la première valeur complexe trouvée dans le tableau de valeurs du signal.
     */
    private int indice;
    
    /**
     * Valeur complexe correspondant à l'indice.
     */
    private Nombre valeur;
    
    /**
     * Constructeur par défaut.
     */
    public ComplexSignalException()
    {
        super();
    }
    
    /**
     * Constructeur d'initialisation.
     * 
     * @param message Message qui sera affiché au lancement de l'exception.
     */
    public ComplexSignalException(String message)
    {
        super(message);
    }
    
    /**
     * Constructeur d'initialisation.
     * 
     * @param message Message qui sera affiché au lancement de l'exception.
     * @param cause Cause de l'exception (si celle-ci est lancée à partir du handler d'une autre exception).
     */
    public ComplexSignalException(String message, Throwable cause)
    {
        super(message, cause);
    }
    
    /**
     * Constructeur d'initialisation.
     * 
     * @param cause Cause de l'exception (si celle-ci est lancée à partir du handler d'une autre exception).
     */
    public ComplexSignalException(Throwable cause)
    {
        super(cause);
    }
    
    /**
     * Constructeur d'initialisation.
     * 
     * @param message Message qui sera affiché au lancement de l'exception.
     * @param signal Le nom de l'objet Signal à valeurs complexes qui voulait être affiché.
     * @param indice Indice de la première valeur complexe trouvée dans le tableau de valeurs du signal.
     * @param valeur La valeur correspondant à l'indice.
     */
    public ComplexSignalException(String message, String signal, int indice, Nombre valeur)
    {
        super(message);
        this.signal = signal;
        this.indice = indice;
        this.valeur = valeur;
    }
    
    /**
     * Retourne le nom de l'objet Signal qui devait être affiché.
     * 
     * @return Le nom du signal.
     */
    public String getSignal()
    {
        return signal;
    }
    
    /**
     * Retourne l'indice de la première valeur complexe trouvée dans le tableau de valeurs du signal.
     * 
     * @return L'indice de cette valeur.
     */
    public int getIndice()
    {
        return indice;
    }
    
    /**
     * Retourne la première valeur complexe trouvée dans le tableau de valeurs du signal.
     * 
     * @return La valeur en question.
     */
    public Nombre getValeur()
    {
        return valeur;
    }
}
