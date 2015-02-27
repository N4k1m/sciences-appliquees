/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package signaux;

/**
 * Cette classe abstraite modélise la notion d'impulsion.
 * 
 * @author lion
 */
public abstract class Impulsion extends SignalAnalogique
{
    /**
     * Amplitude de l'impulsion.
     */
    protected double amplitude;
    
    /**
     * Retard présenté par l'impulsion.
     */
    protected double retard;
    
    /**
     * Constructeur par défaut.
     * L'amplitude est fixée à 1 et le retard à 0.
     */
    public Impulsion()
    {
        super();
        amplitude = 1.0;
        retard = 0.0;
    }
}
