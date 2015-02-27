/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

/**
 * Cette classe modélise une fonction triangulaire (correspondant à un signal triangulaire).
 * 
 * @author lion
 */
public class FonctionTriangulaire extends FonctionPeriodique
{
    /**
     * Constructeur par défaut.
     * L'amplitude et la fréquence sont fixées à 1, le déphasage est nul.
     */
    public FonctionTriangulaire()
    {
        super();
    }
    
    /**
     * Constructeur d'initialisation.
     * 
     * @param amplitude L'amplitude de la fonction triangulaire.
     * @param frequence La fréquence de la fonction triangulaire.
     * @param phase Le déphasage de la fonction triangulaire.
     */
    public FonctionTriangulaire(double amplitude, double frequence, double phase)
    {
        super(amplitude, frequence, phase);
    }

    @Override
    public Nombre getValeur(double param)
    {
        // convention pour l'intervalle [-T/2, T/2[ :
        // f(-T/2) = 0
        // f(-T/4) = -A
        // f(0) = 0
        // f(T/4) = A
        // f(T/2) = 0
        
        // phase?
        if (phase != 0)
        {
            return new FonctionTriangulaire(amplitude, frequence, 0).getValeur(param - phase/(2*Math.PI*frequence));
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
                if (param <= (-1)*periode()/4)
                {
                    return new Nombre(((-4)*amplitude / periode()) * param - 2*amplitude, 0.0);
                }
                else if (param > (-1)*periode()/4 && param <= periode()/4)
                {
                    return new Nombre((4*amplitude / periode()) * param, 0.0);
                }
                else
                {
                    return new Nombre(((-4)*amplitude / periode()) * param + 2*amplitude , 0.0);
                }
            }
        }
    }
}
