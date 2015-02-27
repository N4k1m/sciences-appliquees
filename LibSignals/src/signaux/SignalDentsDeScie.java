/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package signaux;

import math.FonctionDentsDeScie;
import math.Nombre;

/**
 * Cette classe modélise un signal en dents de scie.
 * 
 * @author lion
 */
public class SignalDentsDeScie extends SignalPeriodique
{
    /**
     * Constructeur par défaut.
     * La fréquence et l'amplitude sont fixées à 1, le déphasage est nul.
     */
    public SignalDentsDeScie()
    {
        super();
        fonction = new FonctionDentsDeScie();
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
    public SignalDentsDeScie(Discretiseur discretiseur, double amplitude, double frequence, double phase)
    {
        this.discretiseur = discretiseur;
        this.frequence = frequence;
        this.amplitude = amplitude;
        this.phase = phase;
        this.fonction = new FonctionDentsDeScie(amplitude, frequence, phase);
        this.valeurs = new Nombre[discretiseur.getSample()];
        double indice = discretiseur.getOrigine();
        for (int i = 0; i < discretiseur.getSample(); i++)
        {
            valeurs[i] = fonction.getValeur(indice);
            indice += discretiseur.pas();
        }
    }
}
