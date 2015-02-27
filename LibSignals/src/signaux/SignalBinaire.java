/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package signaux;

import exceptions.BadTypeException;
import exceptions.SampleTooSmallException;
import java.util.BitSet;
import java.util.Random;
import math.Nombre;

/**
 * Cette classe modélise la notion de signal binaire.
 * 
 * @author lion
 */
public class SignalBinaire extends SignalNumerique
{
    /**
     * Constructeur par défaut.
     * Le vecteur de valeurs est initialisé à 0. Le discrétiseur est instancié avec ses valeurs par défaut (voir
     * {@link signaux.Discretiseur#Discretiseur()}).
     */
    public SignalBinaire()
    {
        super();
    }
    
    /**
     * Constructeur d'initialisation.
     * 
     * @param discretiseur L'objet Discretiseur à utiliser pour discrétiser le signal.
     * @param valeurs Le vecteur de valeurs du signal. Aucune vérification n'est faite quant à la nature binaire
     * de ces valeurs. A charge de l'utilisateur d'effectuer cette vérification.
     */
    public SignalBinaire(Discretiseur discretiseur, Nombre[] valeurs)
    {
        super(discretiseur, valeurs);
    }
    
    /**
     * Cette méthode retourne le nombre de bits par seconde dans ce signal binaire.
     * 
     * @return Le débit binaire de ce signal binaire.
     */
    public double debit()
    {
        return super.bauds();
    }
    
    /**
     * Cette factory peut être utilisée pour instancier un signal binaire en spécifiant éventuellement la
     * probabilité d'apparition de 1 dans la séquence binaire résultante.
     * 
     * @param type Le type de signal binaire à instancier (le seul choix possible pour l'instant est la variable
     * {@link signaux.SignalNumerique#ALEATOIRE}).
     * @param probabilite1 La probabilité de 1 dans la séquence binaire.
     * @param discretiseur L'objet Discretiseur à utiliser pour discrétiser le signal.
     * 
     * @return Le signal binaire généré.
     * 
     * @throws BadTypeException Si le type spécifié est inconnu.
     */
    public static SignalBinaire getInstance(int type, float probabilite1, Discretiseur discretiseur) throws BadTypeException
    {
        SignalBinaire signal;
        Nombre[] valeurs = new Nombre[discretiseur.getSample()];
        switch (type)
        {
            case ALEATOIRE:
                Random random = new Random();
                int nb1 = (int)(discretiseur.getSample() * probabilite1);
                int indice, i;
                for (i = 0; i < nb1; i++)
                {
                    indice = random.nextInt(discretiseur.getSample());
                    valeurs[indice] = Nombre.UN;
                }
                for (i = 0; i < discretiseur.getSample(); i++)
                {
                    if (valeurs[i] == null)
                    {
                        valeurs[i] = Nombre.ZERO;
                    }
                }
                break;
                
            default:
                throw new BadTypeException("ERREUR: type inconnu");
        }
        
        signal = new SignalBinaire(discretiseur, valeurs);
        return signal;
    }
    
    /**
     * Cette factory peut être utilisée pour instancier un signal binaire à partir d'une chaine de caractères.
     * Le charset utilisé pour l'encodage des caractères est le charset par défaut de la JVM utilisée,
     * c'est-à-dire le charset par défaut du système sur lequel celle-ci est exécutée. La plupart du temps, il
     * s'agit de l'UTF-8.
     * 
     * @param message La chaine de caractères à encoder.
     * @param discretiseur L'objet Discretiseur à utiliser pour discrétiser le signal.
     * 
     * @return Le signal binaire généré.
     * 
     * @throws SampleTooSmallException Si le sample fourni est trop petit pour contenir tous les bits de la
     * séquence correspondant à la chaine de caractères fournie.
     */
    public static SignalBinaire getInstance(String message, Discretiseur discretiseur) throws SampleTooSmallException
    {
        // CHARSET UTILISE: charset du systeme
        SignalBinaire signal;
        BitSet bits = BitSet.valueOf(message.getBytes());
        int N = 8 * message.getBytes().length; // nb de bits pour coder le msg
        if (N > discretiseur.getSample())
        {
            throw new SampleTooSmallException("message trop long pour le sample fourni");
        }
        else
        {
            Nombre[] valeurs = new Nombre[N];
            boolean bit;
            int i, j;
            // remplissage avec les bits du message
            for (i = 0; i < N; i++)
            {
                bit = bits.get(i);
                if (bit)
                {
                    valeurs[i] = Nombre.UN;
                }
                else
                {
                    valeurs[i] = Nombre.ZERO;
                }
            }
            // padding
            /*for (j = i; j < discretiseur.getSample(); j++)
            {
                valeurs[j] = Nombre.ZERO;
            }*/
            Discretiseur newDiscr = new Discretiseur(N, discretiseur.getOrigine(), discretiseur.getDuree());
            signal = new SignalBinaire(newDiscr, valeurs);
        }
        
        return signal;
    }
    
    /**
     * Cette méthode retourne la chaine de caractères correspondant à ce signal binaire.
     * Elle réalise donc l'opération inverse de la factory
     * {@link signaux.SignalBinaire#getInstance(java.lang.String, signaux.Discretiseur)}.
     * Le charset utilisé est le charset par défaut de la JVM utilisée,
     * c'est-à-dire le charset par défaut du système sur lequel celle-ci est exécutée. La plupart du temps, il
     * s'agit de l'UTF-8.
     * 
     * @return La chaine de caractères correspondant à ce signal binaire.
     */
    public String getMessage()
    {
        // utilisation du charset par defaut
        
        // vecteur valeurs --> BitSet
        BitSet bits = new BitSet(discretiseur.getSample());
        for (int i = 0; i < discretiseur.getSample(); i++)
        {
            if (valeurs[i].equals(Nombre.UN))
            {
                bits.set(i);
            }
        }
        
        // BitSet --> byte[]
        byte[] bytes = bits.toByteArray();
        
        // byte[] --> message
        String message = new String(bytes);
        return message;
    }
    
    @Override
    public String toString()
    {
        String chaine = new String();
        for (int i = 0; i < discretiseur.getSample(); i++)
            chaine = chaine.concat(String.valueOf((int)valeurs[i].partieReelle()));
        
        return chaine;
    }
}
