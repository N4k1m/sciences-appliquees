/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

/**
 * Cette classe abstraite modélise la notion de fonction périodique correspondant aux signaux périodiques.
 * Elle rassemble les caractéristiques communes à toutes les fonctions périodiques mais n'est pas destinée à être
 * instanciée directement.
 * 
 * @author lion
 */
public abstract class FonctionPeriodique implements Fonction
{
    /**
     * Amplitude de la fonction périodique.
     * Il s'agit de la valeur maximum prise par la fonction sur une période.
     */
    protected double amplitude;
    
    /**
     * Fréquence de la fonction périodique.
     * C'est la principale caractéristique d'une fonction périodique. La période peut être obtenue via la méthode
     * {@link #periode()}.
     */
    protected double frequence;
    
    /**
     * Déphasage présenté par la fonction périodique.
     */
    protected double phase;
    
    /**
     * Constructeur par défaut.
     * L'amplitude et la fréquence sont fixées à 1, le déphasage est nul.
     */
    public FonctionPeriodique()
    {
        amplitude = 1.0;
        frequence = 1.0;
        phase = 0.0;
    }
    
    /**
     * Constructeur d'initialisation.
     * 
     * @param amplitude L'amplitude de la fonction périodique.
     * @param frequence La fréquence de la fonction périodique.
     * @param phase La phase de la fonction périodique.
     */
    public FonctionPeriodique(double amplitude, double frequence, double phase)
    {
        this.amplitude = amplitude;
        this.frequence = frequence;
        this.phase = phase;
    }
    
    /**
     * Cette méthode permet d'obtenir la période de la fonction.
     * La période est simplement l'inverse de la fréquence.
     * 
     * @return La période de la fonction.
     */
    public double periode()
    {
        return 1 / frequence;
    }
}
