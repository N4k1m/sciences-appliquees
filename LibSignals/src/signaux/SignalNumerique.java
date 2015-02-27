/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package signaux;

import exceptions.BadIndexAccessException;
import exceptions.BadTypeException;
import math.LoiDeProbabilite;
import math.Nombre;

/**
 * Cette classe modélise la notion de signal numérique.
 * 
 * @author lion
 */
public class SignalNumerique extends Signal
{
    /**
     * Cette variable statique est utilisée pour instancier un signal numérique aléatoire.
     */
    public static final int ALEATOIRE = 0;
    
    /**
     * Constructeur par défaut.
     * Le vecteur de valeurs est initialisé à 0. Le discrétiseur est instancié avec ses valeurs par défaut (voir
     * {@link signaux.Discretiseur#Discretiseur()}).
     */
    public SignalNumerique()
    {
        super();
    }
    
    /**
     * Constructeur d'initialisation.
     * 
     * @param discretiseur L'objet Discretiseur à utiliser pour discrétiser le signal.
     * @param valeurs Le vecteur de valeurs du signal.
     */
    public SignalNumerique(Discretiseur discretiseur, Nombre[] valeurs)
    {
        super(discretiseur, valeurs);
    }
    
    /**
     * Cette méthode retourne le nombre de bauds contenus dans ce signal numérique.
     * 
     * @return Le nombre de bauds du signal numérique.
     */
    public double bauds()
    {
        return 1 / discretiseur.pas();
    }
    
    /**
     * Cette méthode permet de fixer la valeur de ce signal à l'indice spécifié.
     * L'indice en question est l'indice de la valeur dans le vecteur de valeurs du signal. Il doit donc être
     * compris entre 0 et N-1 si N est le sample du signal.
     * 
     * @param indice L'indice de la nouvelle valeur dans le vecteur de valeurs du signal.
     * @param valeur La nouvelle valeur.
     * 
     * @throws BadIndexAccessException Si l'indice spécifié est hors des limites du vecteur de valeurs du signal.
     */
    public void setValeur(int indice, Nombre valeur) throws BadIndexAccessException
    {
        try
        {
            valeurs[indice] = valeur;
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            throw new BadIndexAccessException("ERREUR: indice hors limites (indices acceptes: 0 - "
                    + (discretiseur.getSample() - 1), e);
        }
    }
    
    /**
     * Cette méthode retourne la valeur de ce signal numérique à l'indice spécifié.
     * L'indice en question est l'indice de la valeur dans le vecteur de valeurs du signal. Il doit donc être
     * compris entre 0 et N-1 si N est le sample du signal.
     * 
     * @param indice L'indice dans le vecteur de valeurs du signal.
     * 
     * @return La valeur du signal à cet indice.
     * 
     * @throws BadIndexAccessException Si l'indice spécifié est hors des limites du vecteur de valeurs du signal.
     */
    public Nombre getValeur(int indice) throws BadIndexAccessException
    {
        try
        {
            Nombre valeur = valeurs[indice];
            return valeur;
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            throw new BadIndexAccessException("ERREUR: indice hors limites (indices acceptes: 0 - "
                    + (discretiseur.getSample() - 1), e);
        }
    }
    
    /**
     * Cette factory permet d'instancier un signal numérique aléatoire au moyen d'une loi de probabilité fournie
     * par l'utilisateur.
     * 
     * @param type Le type du signal numérique à instancier (le seul choix possible est {@link #ALEATOIRE}).
     * @param loi La loi de probabilité à utiliser pour générer les valeurs aléatoires.
     * @param discretiseur L'objet Discretiseur à utiliser pour discrétiser le signal.
     * 
     * @return Le signal numérique généré.
     * 
     * @throws BadTypeException Si le type spécifié est inconnu.
     */
    public static SignalNumerique getInstance(int type, LoiDeProbabilite loi, Discretiseur discretiseur) throws BadTypeException
    {
        SignalNumerique signal = null;
        switch (type)
        {
            case ALEATOIRE:
                // calcul du nouveau tableau de valeurs
                Nombre[] valeurs = new Nombre[discretiseur.getSample()];
                for (int i = 0; i < discretiseur.getSample(); i++)
                {
                    valeurs[i] = new Nombre(loi.getRealisation(), 0);
                }
                signal = new SignalNumerique(discretiseur, valeurs);
                break;
                
            default:
                throw new BadTypeException("ERREUR: type inconnu");
        }
        
        return signal;
    }
    
    /**
     * Cette méthode permet de modifier l'instant initial d'observation de ce signal numérique.
     * 
     * @param origine Le nouvel instant initial d'observation.
     */
    public void setOrigineTemporelle(double origine)
    {
        discretiseur.setOrigine(origine);
    }
    
    /**
     * Cette méthode permet de fixer le nombre de bauds de ce signal numérique en modifiant l'intervalle
     * d'observation du discrétiseur.
     * 
     * @param bauds Le nouveau nombre de bauds.
     */
    public void setBauds(double bauds)
    {
        discretiseur.setDuree(discretiseur.getSample() / bauds);
    }
}
