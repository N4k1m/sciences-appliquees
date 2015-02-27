/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

/**
 * Cette classe modélise une fonction "impulsion rectangulaire" (correspondant à une impulsion rectangulaire).
 * 
 * @author lion
 */
public class FonctionImpulsionRectangulaire extends FonctionImpulsion
{
    /**
     * Largeur de l'impulsion.
     */
    private double largeur;
    
    /**
     * Constructeur par défaut.
     * L'amplitude et la largeur sont fixées à 1, le retard est nul.
     */
    public FonctionImpulsionRectangulaire()
    {
        super();
        largeur = 1.0;
    }
    
    /**
     * Constructeur d'initialisation.
     * 
     * @param amplitude L'amplitude de l'impulsion.
     * @param retard Le retard présenté par l'impulsion.
     * @param largeur La largeur de l'impulsion.
     */
    public FonctionImpulsionRectangulaire(double amplitude, double retard, double largeur)
    {
        super(amplitude, retard);
        this.largeur = largeur;
    }

    @Override
    public Nombre getValeur(double param)
    {
        Nombre ret;
        if (param < retard || param >= retard + largeur)
        {
            ret = Nombre.ZERO;
        }
        else
        {
            ret = new Nombre(amplitude, 0);
        }
        
        return ret;
    }
}
