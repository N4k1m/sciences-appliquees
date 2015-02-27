/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

/**
 * Cette classe modélise une fonction "carrée" (correspondant à un signal carré).
 * 
 * @author lion
 */
public class FonctionCarree extends FonctionPeriodique
{
    /**
     * Constructeur par défaut.
     * L'amplitue et la fréquence sont fixées à 1, la phase est nulle.
     */
    public FonctionCarree()
    {
        super();
    }
    
    /**
     * Constructeur d'initialisation.
     * 
     * @param amplitude L'amplitude du signal carré correspondant.
     * @param frequence La fréquence du signal carré correspondant.
     * @param phase La phase du signal carré correspondant.
     */
    public FonctionCarree(double amplitude, double frequence, double phase)
    {
        super(amplitude, frequence, phase);
    }

    @Override
    public Nombre getValeur(double param)
    {
        // convention pour l'intervalle [-T/2, T/2[ :
        // f(-T/2) = -A
        // f(0) = A
        // f(T/2) = -A
        
        // phase?
        if (phase != 0)
        {
            return new FonctionCarree(amplitude, frequence, 0).getValeur(param - phase/(2*Math.PI*frequence));
        }
        else
        {
            if (param < (-1)*periode()/2)
            {
                return getValeur(param + periode());
            }
            else if (param >= periode()/2)
            {
                return getValeur(param - periode());
            }
            else
            {
                if (param < 0)
                {
                    return new Nombre((-1) * amplitude, 0.0);
                }
                else
                {
                    return new Nombre(amplitude, 0.0);
                }
            }
        }
    }
}
