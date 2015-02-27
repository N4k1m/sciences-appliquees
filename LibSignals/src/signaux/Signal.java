/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package signaux;

import exceptions.BadIndexAccessException;
import exceptions.ComplexSignalException;
import math.Nombre;

/**
 * Cette classe modélise la notion de signal.
 * 
 * <p>
 * C'est la classe de base de la hiérarchie. Elle peut être utilisée pour instancier n'importe quel type de signal
 * en fournissant le vecteur contenant les valeurs du nouveau signal ainsi que l'objet Discretiseur à utiliser
 * pour discrétiser le signal. Néanmoins, il peut être préférable d'utiliser les classes qui héritent de celle-ci
 * afin d'instancier certains signaux particuliers.
 * </p>
 * 
 * @see math.Nombre
 * @see signaux.Discretiseur
 * 
 * @author lion
 */
public class Signal
{
    /**
     * Discrétiseur relatif à ce signal.
     */
    protected Discretiseur discretiseur;
    
    /**
     * Vecteur contenant les valeurs de ce signal.
     * S'il s'agit d'un signal analogique, ces valeurs ne sont que des échantillons du signal réel. Dans le cas
     * d'un signal numérique, l'ensemble des valeurs peuvent être contenues dans le vecteur.
     */
    protected Nombre[] valeurs;
    
    /**
     * Constructeur par défaut.
     * Le vecteur de valeurs est initialisé à 0. Le discrétiseur est instancié avec ses valeurs par défaut (voir
     * {@link signaux.Discretiseur#Discretiseur()}).
     */
    public Signal()
    {
        discretiseur = new Discretiseur();
        valeurs = new Nombre[discretiseur.getSample()];
        for (int i = 0; i < discretiseur.getSample(); i++)
        {
            valeurs[i] = Nombre.ZERO;
        }
    }
    
    /**
     * Constructeur d'initialisation.
     * Le vecteur de valeurs sera initialisé à 0.
     * 
     * @param discretiseur L'objet Discretiseur à utiliser pour discrétiser le signal.
     */
    public Signal(Discretiseur discretiseur)
    {
        this.discretiseur = discretiseur;
        valeurs = new Nombre[discretiseur.getSample()];
        for (int i = 0; i < discretiseur.getSample(); i++)
        {
            valeurs[i] = Nombre.ZERO;
        }
    }
    
    /**
     * Constructeur d'initialisation.
     * 
     * @param discretiseur L'objet Discretiseur à utiliser pour discrétiser le signal.
     * @param valeurs Le vecteur de valeurs du signal.
     */
    public Signal(Discretiseur discretiseur, Nombre[] valeurs)
    {
        this.discretiseur = discretiseur;
        this.valeurs = valeurs;
    }
    
    /**
     * Cette méthode retourne le discrétiseur relatif à ce signal.
     * 
     * @return Le discrétiseur du signal.
     */
    public Discretiseur getDiscretiseur()
    {
        return discretiseur;
    }
    
    /**
     * Cette méthode retourne le vecteur de valeurs de ce signal.
     * 
     * @return Le vecteur de valeurs du signal.
     */
    public Nombre[] getValeurs()
    {
        return valeurs;
    }
    
    /**
     * Cette méthode permet de déterminer si ce signal est à valeurs réelles ou non.
     * Un signal est à valeurs réelles si toutes les valeurs qui le composent sont réelles.
     * 
     * @return La valeur booléenne spécifiant si le signal est réel ou non.
     */
    public boolean estReel()
    {
        boolean ret = true;
        for (int i = 0; i < valeurs.length; i++)
        {
            if (!valeurs[i].estReel())
            {
                ret = false;
                break;
            }
        }
        
        return ret;
    }
    
    /**
     * Cette méthode retourne le signal correspondant à la partie réelle de ce signal.
     * Le signal retourné est donc composé des parties réelles des valeurs de ce signal.
     * 
     * @return La partie réelle de ce signal.
     */
    public Signal partieReelle()
    {
        Nombre[] newValeurs = new Nombre[valeurs.length];
        for (int i = 0; i < valeurs.length; i++)
        {
            newValeurs[i] = new Nombre(valeurs[i].partieReelle(), 0);
        }
        
        Signal newSignal = new Signal(discretiseur, newValeurs);
        return newSignal;
    }
    
    /**
     * Cette méthode retourne le signal correspondant à la partie imaginaire de ce signal.
     * Le signal retourné est donc composé des parties imaginaires des valeurs de ce signal.
     * 
     * @return La partie imaginaire de ce signal.
     */
    public Signal partieImaginaire()
    {
        Nombre[] newValeurs = new Nombre[valeurs.length];
        for (int i = 0; i < valeurs.length; i++)
        {
            newValeurs[i] = new Nombre(valeurs[i].partieImaginaire(), 0);
        }
        
        Signal newSignal = new Signal(discretiseur, newValeurs);
        return newSignal;
    }
    
    /**
     * Cette méthode retourne le signal correspondant au module de ce signal.
     * Le signal retourné est donc composé des modules des valeurs de ce signal.
     * 
     * @return Le module de ce signal.
     */
    public Signal module()
    {
        Nombre[] newValeurs = new Nombre[valeurs.length];
        for (int i = 0; i < valeurs.length; i++)
        {
            newValeurs[i] = new Nombre(valeurs[i].module(), 0);
        }
        
        Signal newSignal = new Signal(discretiseur, newValeurs);
        return newSignal;
    }
    
    /**
     * Cette méthode retourne le signal correspondant à l'argument de ce signal.
     * Le signal retourné est donc composé des arguments des valeurs de ce signal.
     * 
     * @return L'argument de ce signal.
     */
    public Signal argument()
    {
        Nombre[] newValeurs = new Nombre[valeurs.length];
        for (int i = 0; i < valeurs.length; i++)
        {
            newValeurs[i] = new Nombre(valeurs[i].argument(), 0);
        }
        
        Signal newSignal = new Signal(discretiseur, newValeurs);
        return newSignal;
    }
    
    /**
     * Cette méthode retourne le signal correspondant au complexe conjugué de ce signal.
     * Le signal retourné est donc composé des complexes conjugués des valeurs de ce signal.
     * 
     * @return Le complexe conjugué de ce signal.
     */
    public Signal conjugue()
    {
        Nombre[] newValeurs = new Nombre[valeurs.length];
        for (int i = 0; i < valeurs.length; i++)
        {
            newValeurs[i] = valeurs[i].conjugue();
        }
        
        Signal newSignal = new Signal(discretiseur, newValeurs);
        return newSignal;
    }
    
    /**
     * Cette méthode permet de fixer la valeur de ce signal correspondant à l'indice spécifié.
     * L'indice en question est une valeur qui doit être comprise dans l'intervalle d'observation du signal. Il
     * s'agit donc bien d'une valeur de la variable de laquelle dépend le signal, et non d'un indice dans le
     * vecteur de valeurs du signal.
     * 
     * @param valeur La nouvelle valeur.
     * @param indice L'indice correspondant à cette nouvelle valeur.
     * 
     * @throws BadIndexAccessException Si l'indice spécifié se trouve en dehors de l'intervalle d'observation du
     * signal.
     */
    public void setValeurPonctuelle(Nombre valeur, double indice) throws BadIndexAccessException
    {
        double t0 = discretiseur.getOrigine();
        double T = discretiseur.getDuree();
        double deltaT = discretiseur.pas();
        
        if (indice < t0 || indice >= t0+T)
        {
            throw new BadIndexAccessException("indice (" + indice + ") hors limites");
        }
        
        int ind = (int)Math.round((indice-t0)/deltaT);
        valeurs[ind] = valeur;
        System.out.println("Harmonique ajoutee a l'indice: " + ind + " pour la frequence: " + indice);
    }
    
    /**
     * Cette méthode retourne la valeur minimum parmi les valeurs de ce signal.
     * 
     * @return La valeur minimum du signal.
     * 
     * @throws ComplexSignalException Si le signal est à valeurs complexes (les nombres complexes ne peuvent pas
     * être ordonnés).
     */
    public Nombre min() throws ComplexSignalException
    {
        if (!this.estReel())
        {
            throw new ComplexSignalException("valeurs complexes --> pas de minimum");
        }
        
        int ind = 0;
        double min = valeurs[0].partieReelle();
        for (int i = 1; i < valeurs.length; i++)
        {
            if (valeurs[i].partieReelle() < min)
            {
                min = valeurs[i].partieReelle();
                ind = i;
            }
        }
        
        return valeurs[ind];
    }
    
    /**
     * Cette méthode retourne la valeur maximum parmi les valeurs de ce signal.
     * 
     * @return La valeur maximum du signal.
     * 
     * @throws ComplexSignalException Si le signal est à valeurs complexes (les nombres complexes ne peuvent pas
     * être ordonnés).
     */
    public Nombre max() throws ComplexSignalException
    {
        if (!this.estReel())
        {
            throw new ComplexSignalException("valeurs complexes --> pas de maximum");
        }
        
        int ind = 0;
        double max = valeurs[0].partieReelle();
        for (int i = 1; i < valeurs.length; i++)
        {
            if (valeurs[i].partieReelle() > max)
            {
                max = valeurs[i].partieReelle();
                ind = i;
            }
        }
        
        return valeurs[ind];
    }
}
