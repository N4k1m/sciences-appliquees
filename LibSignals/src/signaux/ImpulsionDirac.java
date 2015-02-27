/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package signaux;

import math.Nombre;

/**
 * Cette classe modélise l'impulsion de Dirac.
 * 
 * @author lion
 */
public class ImpulsionDirac extends Impulsion
{
    /**
     * Constructeur par défaut.
     * L'amplitude est fixée à 1 et le retard à 0.
     */
    public ImpulsionDirac()
    {
        super();
        valeurs[0] = Nombre.UN;
    }
    
    /**
     * Constructeur d'initialisation.
     * 
     * @param discretiseur L'objet Discretiseur à utiliser pour discrétiser le signal.
     * @param amplitude L'amplitude de l'impulsion de Dirac.
     * @param retard Le retard présenté par l'impulsion de Dirac.
     */
    public ImpulsionDirac(Discretiseur discretiseur, double amplitude, double retard)
    {
        this.discretiseur = discretiseur;
        this.amplitude = amplitude;
        
        // calcul du retard (arrondi)
        double temps = discretiseur.getOrigine();
        int indice = 0;
        while (temps + discretiseur.pas() < retard)
        {
            temps += discretiseur.pas();
            indice++;
        }
        this.retard = temps;
        
        // calcul du vecteur valeurs
        valeurs = new Nombre[discretiseur.getSample()];
        for (int j = 0; j < discretiseur.getSample(); j++)
        {
            if (j != indice)
            {
                valeurs[j] = Nombre.ZERO;
            }
            else
            {
                valeurs[j] = new Nombre(amplitude, 0.0);
            }
        }
    }
}
