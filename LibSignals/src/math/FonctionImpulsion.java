/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

/**
 * Cette classe abstraite modélise la notion de fonction "impulsion" correspondant aux signaux impulsionnels.
 * Elle rassemble les caractéristiques communes à toutes les fonctions "impulsion" mais n'est pas destinée à être
 * instanciée directement.
 * 
 * @author lion
 */
public abstract class FonctionImpulsion implements Fonction
{
    /**
     * Amplitude de l'impulsion.
     */
    protected double amplitude;
    
    /**
     * Retard présenté par l'impulsion.
     */
    protected double retard;
    
    /**
     * Constructeur par défaut.
     * L'amplitude est fixée à 1 et le retard est nul.
     */
    public FonctionImpulsion()
    {
        amplitude = 1.0;
        retard = 0.0;
    }
    
    /**
     * Constructeur d'initialisation.
     * 
     * @param amplitude L'amplitude de l'impulsion.
     * @param retard Le retard présenté par l'impulsion.
     */
    public FonctionImpulsion(double amplitude, double retard)
    {
        this.amplitude = amplitude;
        this.retard = retard;
    }
}
