/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package signaux;

import math.FonctionEchelon;
import math.Nombre;

/**
 * Cette classe modélise un signal "échelon".
 * 
 * @see math.FonctionEchelon
 * 
 * @author lion
 */
public class Echelon extends Impulsion
{
    /**
     * Constructeur par défaut.
     * Le retard est fixé à 0 (l'échelon est donc centré sur l'origine des axes).
     */
    public Echelon()
    {
        super();
        fonction = new FonctionEchelon();
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
     * @param retard Le retard présenté par l'échelon.
     */
    public Echelon(Discretiseur discretiseur, double retard)
    {
        this.discretiseur = discretiseur;
        this.retard = retard;
        this.fonction = new FonctionEchelon(retard);
        
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
