/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package signaux;

import math.FonctionImpulsionRectangulaire;
import math.Nombre;

/**
 * Cette classe modélise une impulsion rectangulaire.
 * 
 * @author lion
 */
public class ImpulsionRectangulaire extends Impulsion
{
    /**
     * Largeur (durée) de l'impulsion.
     */
    private double largeur;
    
    /**
     * Constructeur par défaut.
     * L'amplitude est fixée à 1 et le retard à 0.
     */
    public ImpulsionRectangulaire()
    {
        super();
        fonction = new FonctionImpulsionRectangulaire();
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
     * @param amplitude L'amplitude de l'impulsion.
     * @param retard Le retard présenté par l'impulsion.
     * @param largeur La largeur de l'impulsion (sa durée).
     */
    public ImpulsionRectangulaire(Discretiseur discretiseur, double amplitude, double retard, double largeur)
    {
        this.discretiseur = discretiseur;
        this.amplitude = amplitude;
        this.retard = retard;
        this.largeur = largeur;
        this.fonction = new FonctionImpulsionRectangulaire(amplitude, retard, largeur);
        
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
