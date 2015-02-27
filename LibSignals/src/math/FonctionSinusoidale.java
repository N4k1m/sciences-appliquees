/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

/**
 * Cette classe modélise une fonction sinusoïdale (correspondant à un signal sinusoïdal).
 * 
 * @author lion
 */
public class FonctionSinusoidale extends FonctionPeriodique
{
    /**
     * Constructeur par défaut.
     * L'amplitude et la fréquence sont fixées à 1, le déphasage est nul.
     */
    public FonctionSinusoidale()
    {
        super();
    }
    
    /**
     * Constructeur d'initialisation.
     * 
     * @param amplitude L'amplitude de la sinusoïde.
     * @param frequence La fréquence de la sinusoïde.
     * @param phase Le déphasage de la sinusoïde.
     */
    public FonctionSinusoidale(double amplitude, double frequence, double phase)
    {
        super(amplitude, frequence, phase);
    }

    @Override
    public Nombre getValeur(double param)
    {
        double valeur = amplitude * Math.sin(2*(Math.PI)*frequence*param - phase);
        return new Nombre(valeur, 0.0);
    }
}
