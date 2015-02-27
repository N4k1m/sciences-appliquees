/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

import java.util.Random;

/**
 * Cette classe modélise une loi de probabilité "uniforme".
 * Cette loi uniforme est caractérisée par des valeurs minimum et maximum pour les nombres générés. La courbe
 * correspondant à une telle loi est une droite horizontale qui symbolise la probabilité égale pour chaque élément
 * de l'ensemble de valeurs pouvant être générées.
 * 
 * L'utilisateur peut instancier une loi uniforme en spécifiant les valeurs minimum et maximum des nombres
 * aléatoires générés.
 * 
 * @author lion
 */
public class LoiUniforme implements LoiDeProbabilite
{
    /**
     * Générateur de nombres aléatoires utilisé.
     */
    private Random generateur;
    
    /**
     * Valeur minimum.
     */
    private double min;
    
    /**
     * Valeur maximum.
     */
    private double max;
    
    /**
     * Constructeur par défaut.
     * La valeur minimum est fixée à 0, la valeur maximum à 1.
     */
    public LoiUniforme()
    {
        min = 0;
        max = 1;
        generateur = new Random();
    }
    
    /**
     * Constructeur d'initialisation.
     * 
     * @param min La valeur minimum des nombres générés.
     * @param max La valeur maximum des nombres générés.
     */
    public LoiUniforme(double min, double max)
    {
        this.min = min;
        this.max = max;
        generateur = new Random();
    }
    
    @Override
    public double getRealisation()
    {
        double randomValue = min + (max - min)*generateur.nextDouble();
        return randomValue;
    }
}
