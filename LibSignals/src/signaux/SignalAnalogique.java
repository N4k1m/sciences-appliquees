/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package signaux;

import exceptions.BadTypeException;
import exceptions.NoFunctionException;
import math.Fonction;
import math.LoiDeProbabilite;
import math.LoiNormale;
import math.LoiUniforme;
import math.Nombre;

/**
 * Cette classe modélise la notion de signal analogique.
 * 
 * @see signaux.SignalNumerique
 * 
 * @author lion
 */
public class SignalAnalogique extends Signal
{
    /**
     * Cette variable est utilisée pour instancier un bruit blanc gaussien.
     */
    public static final int BRUIT_BLANC_GAUSSIEN = 0;
    
    /**
     * Cette variable est utilisée pour instancier un bruit blanc uniforme.
     */
    public static final int BRUIT_BLANC_UNIFORME = 1;
    
    /**
     * Cette variable est utilisée pour instancier un signal constant.
     */
    public static final int CONSTANT = 2;
    
    /**
     * Fonction mathématique correspondant à ce signal analogique.
     * Cette variable peut éventuellement valoir null. Elle est utilisée pour le "zoom infini".
     */
    protected Fonction fonction;
    
    /**
     * Constructeur par défaut.
     * Le vecteur de valeurs est initialisé à 0. Le discrétiseur est instancié avec ses valeurs par défaut (voir
     * {@link signaux.Discretiseur#Discretiseur()}). La variable Fonction est mise à null.
     */
    public SignalAnalogique()
    {
        super();
        fonction = null;
    }
    
    /**
     * Constructeur d'initialisation.
     * Le vecteur de valeurs sera initialisé à 0, la variable Fonction vaudra null.
     * 
     * @param discr L'objet Discretiseur à utiliser pour discrétiser le signal.
     */
    public SignalAnalogique(Discretiseur discr)
    {
        super(discr);
        fonction = null;
    }
    
    /**
     * Constructeur d'initialisation.
     * 
     * @param discr L'objet Discretiseur à utiliser pour discrétiser le signal.
     * @param val Le vecteur de valeurs du signal.
     * @param fct La fonction mathématique correspondant au signal.
     */
    public SignalAnalogique(Discretiseur discr, Nombre[] val, Fonction fct)
    {
        super(discr, val);
        fonction = fct;
    }
    
    /**
     * Constructeur d'initialisation.
     * 
     * <p>
     * Ce constructeur doit être utilisé pour caster un objet Signal en objet SignalAnalogique. En effet, le
     * downcasting ne peut pas être effectué selon la syntaxe classique de Java sous peine d'obtenir une exception
     * à l'exécution.
     * </p>
     * <p>
     * La variable Fonction est mise à null.
     * </p>
     * 
     * @param signal L'objet Signal à caster.
     */
    public SignalAnalogique(Signal signal)
    {
        super(signal.getDiscretiseur(), signal.getValeurs());
        fonction = null;
    }
    
    /**
     * Cette factory peut être utilisée pour instancier un signal analogique en fournissant la fonction
     * mathématique et le discrétiseur utilisés.
     * 
     * @param fonction La fonction mathématique correspondant au signal.
     * @param discretiseur L'objet Discretiseur à utiliser pour discrétiser le signal.
     * 
     * @return Le signal analogique créé.
     */
    public static SignalAnalogique getInstance(Fonction fonction, Discretiseur discretiseur)
    {
        Nombre[] valeurs = new Nombre[discretiseur.getSample()];
        double indice = discretiseur.getOrigine();
        for (int i = 0; i < discretiseur.getSample(); i++)
        {
            valeurs[i] = fonction.getValeur(indice);
            indice += discretiseur.pas();
        }
        
        return new SignalAnalogique(discretiseur, valeurs, fonction);
    }
    
    /**
     * Cette factory peut être utilisée pour instancier certains signaux analogiques particuliers.
     * Les choix possibles sont représentés par les variables statiques de la classe, celles-ci devant être
     * passées à la factory via le paramètre "type".
     * 
     * @param type Le type de signal analogique à instancier (BRUIT_BLANC_GAUSSIEN, BRUIT_BLANC_UNIFORME ou
     * CONSTANT).
     * @param amplitude L'amplitude du signal analogique (valeur maximum dans le cas du bruit, valeur de la
     * constante dans le cas d'un signal constant).
     * @param discretiseur L'objet Discretiseur à utiliser pour discrétiser le signal.
     * 
     * @return Le signal analogique créé.
     * 
     * @throws BadTypeException Si le type spécifié est inconnu.
     */
    public static SignalAnalogique getInstance(int type, Nombre amplitude, Discretiseur discretiseur) throws BadTypeException
    {
        SignalAnalogique signal = null;
        Nombre[] valeurs = new Nombre[discretiseur.getSample()];
        LoiDeProbabilite loi;
        switch (type)
        {
            case BRUIT_BLANC_GAUSSIEN:
                loi = new LoiNormale(0, amplitude.partieReelle());
                for (int i = 0; i < discretiseur.getSample(); i++)
                {
                    valeurs[i] = new Nombre(loi.getRealisation(), 0);
                }
                signal = new SignalAnalogique(discretiseur, valeurs, null);
                break;
                
            case BRUIT_BLANC_UNIFORME:
                loi = new LoiUniforme(-amplitude.partieReelle(), amplitude.partieReelle());
                for (int i = 0; i < discretiseur.getSample(); i++)
                {
                    valeurs[i] = new Nombre(loi.getRealisation(), 0);
                }
                signal = new SignalAnalogique(discretiseur, valeurs, null);
                break;
                
            case CONSTANT:
                signal = new SignalConstant(discretiseur, amplitude);
                break;
            
            default:
                throw new BadTypeException("type inconnu");
        }
        return signal;
    }
    
    /**
     * Cette méthode permet de modifier le discrétiseur de ce signal analogique.
     * Elle est notamment utilisée pour implémenter le "zoom infini".
     * 
     * @param discretiseur Le nouveau discrétiseur à utiliser.
     * 
     * @throws NoFunctionException Si la variable Fonction de ce signal analogique vaut null. Le "zoom infini" ne
     * peut pas être effectué sans cette variable.
     */
    public void setDiscretiseur(Discretiseur discretiseur) throws NoFunctionException
    {
        if (fonction == null)
        {
            throw new NoFunctionException("pas d'objet Fonction trouve. Ce signal ne peut pas etre 'rediscretise'");
        }
        else
        {
            this.discretiseur = discretiseur;
            
            // calcul du nouveau tableau de valeurs
            valeurs = new Nombre[discretiseur.getSample()];
            double indice = discretiseur.getOrigine();
            for (int i = 0; i < discretiseur.getSample(); i++)
            {
                valeurs[i] = fonction.getValeur(indice);
                indice += discretiseur.pas();
            }
        }
    }
}
