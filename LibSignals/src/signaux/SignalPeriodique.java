/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package signaux;

import exceptions.BadTypeException;

/**
 * Cette classe abstraite modélise la notion de signal périodique.
 * 
 * @author lion
 */
public abstract class SignalPeriodique extends SignalAnalogique
{
    /**
     * Cette variable statique est utilisée pour instancier un signal sinusoïdal.
     */
    public static final int SINUS = 0;
    
    /**
     * Cette variable statique est utilisée pour instancier un signal carré.
     */
    public static final int CARRE = 1;
    
    /**
     * Cette variable statique est utilisée pour instancier un signal triangulaire.
     */
    public static final int TRIANGLE = 2;
    
    /**
     * Cette variable statique est utilisée pour instancier un signal en dents de scie.
     */
    public static final int SCIE = 3;
    
    /**
     * Cette variable statique est utilisée pour instancier un signal cosinusoïdal.
     */
    public static final int COSINUS = 4;
    
    /**
     * Amplitude du signal périodique.
     */
    protected double amplitude;
    
    /**
     * Fréquence du signal périodique.
     */
    protected double frequence;
    
    /**
     * Déphasage présenté par le signal périodique.
     */
    protected double phase;
    
    /**
     * Constructeur par défaut.
     * La fréquence et l'amplitude sont fixées à 1, le déphasage est nul.
     */
    public SignalPeriodique()
    {
        super();
        frequence = 1.0;
        amplitude = 1.0;
        phase = 0.0;
    }
    
    /**
     * Cette factory permet d'instancier un signal périodique particulier en précisant son amplitude, sa fréquence
     * et son déphasage.
     * Les choix possibles sont {@link #SINUS}, {@link #COSINUS}, {@link #CARRE}, {@link #SCIE} et {@link #TRIANGLE}.
     * 
     * @param type Le type de signal périodique à instancier.
     * @param amplitude L'amplitude du signal.
     * @param frequence La fréquence du signal.
     * @param phase Le déphasage présenté par le signal.
     * @param discretiseur L'objet Discretiseur à utiliser pour discrétiser le signal.
     * 
     * @return Le signal périodique généré.
     * 
     * @throws BadTypeException Si le type spécifié est inconnu.
     */
    public static SignalAnalogique getInstance(int type, double amplitude, double frequence, double phase, Discretiseur discretiseur)
            throws BadTypeException
    {
        SignalAnalogique signal;
        
        switch (type)
        {
            case SINUS:
                signal = new SignalSinusoidal(discretiseur, amplitude, frequence, phase);
                break;
            case COSINUS:
                signal = new SignalSinusoidal(discretiseur, amplitude, frequence, phase);
                break;
            case CARRE:
                signal = new SignalCarre(discretiseur, amplitude, frequence, phase);
                break;
            case TRIANGLE:
                signal = new SignalTriangulaire(discretiseur, amplitude, frequence, phase);
                break;
            case SCIE:
                signal = new SignalDentsDeScie(discretiseur, amplitude, frequence, phase);
                break;
            default:
                throw new BadTypeException("ERREUR: type inconnu");
        }
        
        return signal;
    }
}
