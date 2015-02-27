/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

/**
 * Cette classe modélise une fonction "échelon" (correspondant à un signal "échelon").
 * La fonction vaut:
 * <ul>
 * <li>0 pour les valeurs de x négatives;</li>
 * <li>1/2 en 0;</li>
 * <li>1 pour les valeurs de x positives.</li>
 * </ul>
 * Elle peut présenter un décalage représenté par le paramètre "retard".
 * 
 * @author lion
 */
public class FonctionEchelon extends FonctionImpulsion
{
    /**
     * Constructeur par défaut.
     * Le retard est nul (la fonction est centrée sur 0).
     */
    public FonctionEchelon()
    {
        super();
    }
    
    /**
     * Constructeur d'initialisation.
     * 
     * @param retard La valeur sur laquelle l'échelon sera centré.
     */
    public FonctionEchelon(double retard)
    {
        this.retard = retard;
    }

    @Override
    public Nombre getValeur(double param)
    {
        // convention:
        // x < retard: f(x) = 0
        // x = retard: f(x) = 1/2
        // x > retard: f(x) = 1
        
        Nombre ret;
        if (param < retard)
        {
            ret = Nombre.ZERO;
        }
        else if (param == retard)
        {
            ret = new Nombre(0.5, 0.0);
        }
        else
        {
            ret = Nombre.UN;
        }
        
        return ret;
    }
}
