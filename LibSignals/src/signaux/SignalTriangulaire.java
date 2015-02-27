/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package signaux;

import math.FonctionTriangulaire;
import math.Nombre;

/**
 * Cette classe modélise un signal triangulaire.
 * 
 * @author lion
 */
public class SignalTriangulaire extends SignalPeriodique
{
    /**
     * Constructeur par défaut.
     * La fréquence et l'amplitude sont fixées à 1, le déphasage est nul.
     */
    public SignalTriangulaire()
    {
        super();
        fonction = new FonctionTriangulaire();
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
     * @param amplitude L'amplitude du signal.
     * @param frequence La fréquence du signal.
     * @param phase Le déphasage présenté par le signal.
     */
    public SignalTriangulaire(Discretiseur discretiseur, double amplitude, double frequence, double phase)
    {
        this.discretiseur = discretiseur;
        this.frequence = frequence;
        this.amplitude = amplitude;
        this.phase = phase;
        this.fonction = new FonctionTriangulaire(amplitude, frequence, phase);
        this.valeurs = new Nombre[discretiseur.getSample()];
        double indice = discretiseur.getOrigine();
        for (int i = 0; i < discretiseur.getSample(); i++)
        {
            valeurs[i] = fonction.getValeur(indice);
            indice += discretiseur.pas();
        }
    }
}
