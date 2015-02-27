/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package signaux;

/**
 * Cette classe encapsule les informations relatives à la discrétisation d'un objet Signal.
 * On peut dire qu'un objet Discretiseur correspond au "domaine de définition" du signal auquel il correspond.
 * 
 * <p>
 * Cette classe possède les caractéristiques suivantes:
 * <ul>
 * <li>sample: nombre d'échantillons du signal en question;</li>
 * <li>origine: "instant" initial d'observation du signal;</li>
 * <li>durée: fenêtre temporelle d'observation du signal.</li>
 * </ul>
 * </p>
 * 
 * @see signaux.Signal
 * 
 * @author lion
 */
public class Discretiseur
{
    /**
     * Nombre d'échantillons du signal en question.
     */
    private int sample;
    
    /**
     * "Instant" initial d'observation du signal en question.
     */
    private double origine;
    
    /**
     * Fenêtre temporelle d'observation du signal en question.
     */
    private double duree;
    
    /**
     * Constructeur par défaut.
     * Le sample est fixé à 4096, l'origine à 0 et la durée d'observation à 1.
     */
    public Discretiseur()
    {
        sample = 4096;
        origine = 0;
        duree = 1;
    }
    
    /**
     * Constructeur d'initialisation.
     * 
     * @param sample Le sample souhaité.
     * @param origine L'origine souhaitée.
     * @param duree La durée souhaitée.
     */
    public Discretiseur(int sample, double origine, double duree)
    {
        this.sample = sample;
        this.origine = origine;
        this.duree = duree;
    }
    
    /**
     * Cette méthode retourne le pas de discrétisation relatif à cet objet Discretiseur, qui est fonction de la
     * durée d'observation et du sample.
     * 
     * @return Le pas de discrétisation de cet objet Discretiseur.
     */
    public double pas()
    {
        return duree / sample;
    }
    
    /**
     * Cette méthode retourne le sample de cet objet Discretiseur.
     * 
     * @return Le sample de cet objet Discretiseur.
     */
    public int getSample()
    {
        return sample;
    }
    
    /**
     * Cette méthode retourne l'origine de cet objet Discretiseur.
     * 
     * @return L'origine de cet objet Discretiseur.
     */
    public double getOrigine()
    {
        return origine;
    }
    
    /**
     * Cette méthode permet de fixer l'origine de cet objet Discretiseur.
     * 
     * @param orig La nouvelle origine.
     */
    public void setOrigine(double orig)
    {
        origine = orig;
    }
    
    /**
     * Cette méthode retourne la durée d'observation de cet objet Discretiseur.
     * 
     * @return La durée d'observation de cet objet Discretiseur.
     */
    public double getDuree()
    {
        return duree;
    }
    
    /**
     * Cette méthode permet de fixer la durée d'observation de cet objet Discretiseur.
     * 
     * @param duree La nouvelle durée d'observation.
     */
    public void setDuree(double duree)
    {
        this.duree = duree;
    }
    
    @Override
    public boolean equals(Object obj)
    {
        boolean ret = false;
        if (obj == null)
        {
            return ret;
        }
        if (obj == this)
        {
            ret = true;
        }
        else if (obj instanceof Discretiseur)
        {
            Discretiseur obj2 = (Discretiseur)obj;
            if (obj2.getSample() == sample && obj2.getOrigine() == origine && obj2.getDuree() == duree)
            {
                ret = true;
            }
        }
        
        return ret;
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 83 * hash + this.sample;
        hash = 83 * hash + (int) (Double.doubleToLongBits(this.origine) ^ (Double.doubleToLongBits(this.origine) >>> 32));
        hash = 83 * hash + (int) (Double.doubleToLongBits(this.duree) ^ (Double.doubleToLongBits(this.duree) >>> 32));
        return hash;
    }
}
