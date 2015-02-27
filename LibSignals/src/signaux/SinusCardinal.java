/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package signaux;

import math.FonctionSinusCardinal;
import math.Nombre;

/**
 * Cette classe modélise un sinus cardinal.
 * 
 * @author lion
 */
public class SinusCardinal extends Impulsion
{
    /**
     * Largeur du lobe principal.
     */
    private double largeur;
    
    /**
     * Constructeur par défaut.
     * L'amplitude et la largeur du lobe principal sont fixées à 1 et le retard à 0.
     */
    public SinusCardinal()
    {
        super();
        fonction = new FonctionSinusCardinal();
        largeur = 1;
        double indice = discretiseur.getOrigine();
        for (int i = 0; i < discretiseur.getSample(); i++)
        {
            valeurs[i] = fonction.getValeur(indice);
            indice += discretiseur.pas();
        }
    }
    
    /**
     * Constructeur d'initialisation.
     * 
     * @param discretiseur L'objet Discretiseur à utiliser pour discrétiser le signal.
     * @param amplitude L'amplitude du lobe principal.
     * @param retard Le retard présenté par l'impulsion.
     * @param largeur La largeur du lobe principal.
     */
    public SinusCardinal(Discretiseur discretiseur, double amplitude, double retard, double largeur)
    {
        this.discretiseur = discretiseur;
        this.amplitude = amplitude;
        this.retard = retard;
        this.largeur = largeur;
        this.fonction = new FonctionSinusCardinal(amplitude, retard, largeur);
        
        // calcul du vecteur valeurs
        valeurs = new Nombre[discretiseur.getSample()];
        double indice = discretiseur.getOrigine();
        for (int i = 0; i < discretiseur.getSample(); i++)
        {
            valeurs[i] = fonction.getValeur(indice);
            indice += discretiseur.pas();
        }
    }
}
