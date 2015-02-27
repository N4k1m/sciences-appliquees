/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package signaux;

import math.FonctionConstante;
import math.Nombre;

/**
 * Cette classe modélise la notion de signal constant.
 * 
 * @author lion
 */
public class SignalConstant extends SignalAnalogique
{
    /**
     * Valeur (constante) du signal.
     */
    private Nombre valeur;
    
    /**
     * Constructeur par défaut.
     * La valeur du signal est fixée à 0.
     */
    public SignalConstant()
    {
        super();
        valeur = Nombre.ZERO;
        fonction = new FonctionConstante();
    }
    
    /**
     * Constructeur d'initialisation.
     * 
     * @param discr L'objet Discretiseur à utiliser pour discrétiser le signal.
     * @param valeur La valeur (constante) du signal.
     */
    public SignalConstant(Discretiseur discr, Nombre valeur)
    {
        discretiseur = discr;
        valeurs = new Nombre[discr.getSample()];
        for (int i = 0; i < discr.getSample(); i++)
            valeurs[i] = valeur;
        fonction = new FonctionConstante(valeur);
        this.valeur = valeur;
    }
}
