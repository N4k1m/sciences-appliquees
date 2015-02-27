/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package numerisation;

import exceptions.BadParameterException;
import exceptions.ComplexSignalException;
import java.util.BitSet;
import math.Nombre;
import signaux.Discretiseur;
import signaux.SignalAnalogique;
import signaux.SignalBinaire;
import signaux.SignalNumerique;

/**
 * Cette classe réalise les différentes opérations nécessaires à la numérisation d'un signal analogique.
 * <p>
 * L'utilisateur peut instancier cette classe en initialisant les paramètres suivants:
 * 
 * <ul>
 * <li>instant initial: point de départ de la numérisation sur le signal à numériser;</li>
 * <li>période d'échantillonnage: intervalle entre chaque échantillon du signal de départ;</li>
 * <li>nombre d'échantillons: le nombre d'échantillons du signal de départ souhaité.</li>
 * </ul>
 * </p>
 * 
 * Ensuite, les opérations d'échantillonnage, de quantification et de codage peuvent être réalisées indépendamment
 * les unes des autres, en veillant à appliquer une opération au signal renvoyé par l'opération précédente.<br>
 * L'ordre classique est: échantillonnage -> quantification -> codage.
 * 
 * @see signaux.SignalAnalogique
 * @see signaux.SignalNumerique
 * @see signaux.SignalBinaire
 * @see numerisation.Codec
 * 
 * @author lion
 */
public class ADC
{
    /**
     * "Instant" initial de numérisation.
     */
    private double t0;
    
    /**
     * Période d'échantillonnage.
     */
    private double Ts;
    
    /**
     * Nombre d'échantillons souhaité.
     */
    private int N;
    
    /**
     * Constructeur par défaut.
     * L'instant initial de numérisation est fixé à 0, la période d'échantillonnage à 1/4096 et le nombre
     * d'échantillons à 4096.
     */
    public ADC()
    {
        t0 = 0;
        Ts = 1.0/4096;
        N = 4096;
    }
    
    /**
     * Constructeur d'initialisation.
     * 
     * @param t0 L'"instant" initial de numérisation.
     * @param Ts La période d'échantillonnage.
     * @param N Le nombre d'échantillons souhaité.
     */
    public ADC(double t0, double Ts, int N)
    {
        this.t0 = t0;
        this.Ts = Ts;
        this.N = N;
    }
    
    /**
     * Cette méthode réalise l'opération d'échantillonnage du signal analogique de départ.
     * 
     * @param signal Le signal analogique à échantillonner.
     * 
     * @return Le signal numérique obtenu après échantillonnage.
     * 
     * @throws BadParameterException Si la durée du signal est trop courte par rapport au nombre d'échantillons
     * souhaité et à la période d'échantillonnage.
     */
    public SignalNumerique echantillonnage(SignalAnalogique signal) throws BadParameterException
    {
        if (N*Ts > signal.getDiscretiseur().getDuree())
        {
            throw new BadParameterException("signal trop court pour le nombre d'echantillons voulu");
        }
        SignalAnalogique signalEch = Echantillonnage.ideal(signal, t0, Ts);
        Nombre[] g = signalEch.getValeurs();
        double deltaT = signalEch.getDiscretiseur().pas();
        
        // construction signal numerique
        Discretiseur discr = new Discretiseur(N, t0, N*Ts);
        Nombre[] valeurs = new Nombre[N];
        int Ni = (int)(Ts/deltaT); // ecart (sample) entre deux echantillons
        for (int i = 0; i < N; i++)
        {
            valeurs[i] = g[i*Ni];
        }
        SignalNumerique signalNum = new SignalNumerique(discr, valeurs);
        return signalNum;
    }
    
    /**
     * Cette méthode réalise l'opération de quantification du signal numérique obtenu après échantillonnage.
     * 
     * @param signal Le signal numérique à quantifier.
     * @param codec Le codec à utiliser pour la quantification.
     * 
     * @return Le signal numérique obtenu après quantification.
     * 
     * @throws ComplexSignalException Si le signal numérique fourni est à valeurs complexes.
     */
    public SignalNumerique quantification(SignalNumerique signal, Codec codec) throws ComplexSignalException
    {
        if (!signal.estReel())
        {
            throw new ComplexSignalException("impossible de quantifier un signal complexe");
        }
        
        int sample = signal.getDiscretiseur().getSample();
        Nombre[] x = signal.getValeurs();
        Nombre[] y = new Nombre[sample];
        
        for (int i = 0; i < sample; i++)
        {
            y[i] = codec.quantifie(x[i]);
        }
        
        SignalNumerique signalQuantifie = new SignalNumerique(signal.getDiscretiseur(), y);
        return signalQuantifie;
    }
    
    /**
     * Cette méthode réalise l'opération de codage du signal numérique obtenu après quantification.
     * 
     * @param signal Le signal numérique qui doit être codé.
     * @param codec Le codec à utiliser pour le codage.
     * 
     * @return Le signal binaire obtenu après codage.
     */
    public SignalBinaire codage(SignalNumerique signal, Codec codec)
    {
        int R = codec.getNbBitsParEchantillon();
        int Nx = signal.getDiscretiseur().getSample();
        int Ny = Nx*R;
        double ti = signal.getDiscretiseur().getOrigine();
        double T = signal.getDiscretiseur().getDuree();
        Nombre[] x = signal.getValeurs();
        Nombre[] y = new Nombre[Ny];
        
        BitSet bits;
        int indice = 0, i, j;
        for (i = 0; i < Nx; i++)
        {
            bits = codec.code(x[i]);
            for (j = R-1; j >= 0; j--) // on transmet les bits de poids fort d'abord
            {
                if (bits.get(j))
                {
                    y[indice] = Nombre.UN;
                }
                else
                {
                    y[indice] = Nombre.ZERO;
                }
                indice++;
            }
        }
        
        Discretiseur discr = new Discretiseur(Ny, ti, T);
        SignalBinaire signalBin = new SignalBinaire(discr, y);
        return signalBin;
    }
}
