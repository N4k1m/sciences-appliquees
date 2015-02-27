/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

import exceptions.MathException;

/**
 * Cette classe modélise une fonction "puissance".
 * Une fonction "puissance" est définie comme suit: f(x) = (x+C)^p où p est l'exposant et C est une constante.
 * Ces deux paramètres peuvent être adaptés par l'utilisateur pour instancier une fonction "puissance" quelconque.
 * 
 * Exemple: g(x) = (x+3)^5
 * 
 * Conditions d'existence de la fonction:
 * <ul>
 * <li>si p est naturel: pas de CE</li>
 * <li>si p est entier et négatif: x doit être différent de -C</li>
 * <li>si p n'est pas entier: x doit être compris dans ]-C, +inf[</li>
 * </ul>
 * 
 * @author lion
 */
public class FonctionPuissance implements Fonction
{
    /**
     * Exposant de la fonction.
     */
    private double p;
    
    /**
     * Constante présente dans l'expression générale d'une fonction "puissance".
     */
    private double C;
    
    /**
     * Constructeur par défaut.
     * L'exposant est fixé à 1. La constante C n'est pas définie (elle vaut 0).
     */
    public FonctionPuissance()
    {
        p = 1;
    }
    
    /**
     * Constructeur d'initialisation.
     * 
     * @param exposant L'exposant de la fonction.
     * @param constante La constante C dans l'expression générale.
     */
    public FonctionPuissance(double exposant, double constante)
    {
        p = exposant;
        C = constante;
    }
    
    @Override
    public Nombre getValeur(double param)
    {
        if (p == Math.floor(p) && p < 0) // p entier negatif
        {
            if (param == -C)
            {
                throw new MathException("valeur hors domaine");
            }
        }
        else if (p != Math.floor(p)) // p non entier
        {
            if (param <= -C)
            {
                throw new MathException("valeur hors domaine");
            }
        }
        
        return new Nombre(Math.pow(param+C, p), 0);
    }
}
