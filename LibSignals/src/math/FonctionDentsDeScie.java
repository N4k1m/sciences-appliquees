/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

/**
 * Cette classe modélise une fonction "en dents de scie" (correspondant à un signal en dents de scie).
 * 
 * @author lion
 */
public class FonctionDentsDeScie extends FonctionPeriodique
{
    /**
     * Constructeur par défaut.
     * L'amplitue et la fréquence sont fixées à 1, la phase est nulle.
     */
    public FonctionDentsDeScie()
    {
        super();
    }
    
    /**
     * Constructeur d'initialisation.
     * 
     * @param amplitude L'amplitude du signal en dents de scie correspondant.
     * @param frequence La fréquence du signal en dents de scie correspondant.
     * @param phase La phase du signal en dents de scie correspondant.
     */
    public FonctionDentsDeScie(double amplitude, double frequence, double phase)
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
            return new FonctionDentsDeScie(amplitude, frequence, 0).getValeur(param - phase/(2*Math.PI*frequence));
        }
        else
        {
            if (param < periode() * (-1) / 2)
            {
                return getValeur(param + periode());
            }
            else if (param >= periode() / 2)
            {
                return getValeur(param - periode());
            }
            else
            {
                return new Nombre((2*amplitude / periode()) * param, 0.0);
            }
        }
    }
}
