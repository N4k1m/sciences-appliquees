/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

/**
 * Cette classe modélise une fonction constante (correspondant à un signal constant).
 * 
 * @author lion
 */
public class FonctionConstante implements Fonction
{
    /**
     * La valeur (constante) de la fonction.
     * Cette valeur peut être complexe.
     */
    private Nombre valeur;
    
    /**
     * Constructeur par défaut.
     * La valeur de la fonction est fixée à 0.
     */
    public FonctionConstante()
    {
        valeur = Nombre.ZERO;
    }
    
    /**
     * Constructeur d'initialisation.
     * 
     * @param valeur La valeur de la fonction.
     */
    public FonctionConstante(Nombre valeur)
    {
        this.valeur = valeur;
    }
    
    @Override
    public Nombre getValeur(double param)
    {
        return valeur;
    }
}
