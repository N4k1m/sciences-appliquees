/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

/**
 * Cette classe modélise une fonction "sinus cardinal" (correspondant au signal du même nom).
 * 
 * @author lion
 */
public class FonctionSinusCardinal extends FonctionImpulsion
{
    /**
     * Largeur du lobe principal.
     */
    private double largeur;
    
    /**
     * Constructeur par défaut.
     * La largeur du lobe principal et son amplitude sont fixées à 1, le retard est nul.
     */
    public FonctionSinusCardinal()
    {
        super();
        largeur = 1.0;
    }
    
    /**
     * Constructeur d'initialisation.
     * 
     * @param amplitude L'amplitude du lobe principal.
     * @param retard Le retard présenté par l'impulsion.
     * @param largeur La largeur du lobe principal.
     */
    public FonctionSinusCardinal(double amplitude, double retard, double largeur)
    {
        super(amplitude, retard);
        this.largeur = largeur;
    }

    @Override
    public Nombre getValeur(double param)
    {
        // ne tient pas compte de la largeur
        Nombre ret;
        if (param == retard)
        {
            ret = new Nombre(amplitude, 0.0);
        }
        else
        {
            ret = new Nombre(amplitude*largeur/(2*Math.PI) * Math.sin(2*Math.PI/largeur*(param - retard)) / (param - retard), 0.0);
        }
        
        return ret;
    }
}
