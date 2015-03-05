package Filtrage;

import exceptions.BadParameterException;
import exceptions.ComplexSignalException;
import java.util.Arrays;
import math.Nombre;
import operations.Fourier;
import operations.SignalOperations;
import signaux.Signal;

/**
 *
 * @author Nakim
 */
public class FiltrageLineaire
{
    /**
     * Filtre un signal temporel à l'aide d'un filtre passe-bas pour une fréquence donnée
     * @param signalTemporel le signal temporel à filtrer
     * @param frequence la fréquence limite (W)
     * @return Le fourier du signal filtré
     */
    public static Signal filtrePasseBasFourier(Signal signalTemporel, double frequence)
    {
        if (!signalTemporel.estReel())
            throw new ComplexSignalException("Le signal est complexe. Signal temporel attendu");

        // Create array with filtering Nombres
        int sample = signalTemporel.getDiscretiseur().getSample();
        Nombre[] filterNombres = new Nombre[sample];
        Arrays.fill(filterNombres, Nombre.ZERO);

        int limitFreq = (int)Math.round(frequence);
        int startIndex = (sample/2) - limitFreq;
        int endIndex = (sample/2) + limitFreq;

        // Accepted freq
        for (int i = startIndex; i <= endIndex; ++i)
            filterNombres[i] = Nombre.UN;

        Signal fourier = Fourier.fourier(signalTemporel);
        Signal filterSignal = new Signal(fourier.getDiscretiseur(), filterNombres);

        return SignalOperations.produit(fourier, filterSignal);
    }

    /**
     * Filtre un signal temporel à l'aide d'un filtre passe-bas pour une fréquence donnée
     * @param signalTemporel le signal temporel à filtrer
     * @param frequence la fréquence limite (W)
     * @return Le signal filtré (temporel)
     */
    public static Signal filtrePasseBas(Signal signalTemporel, double frequence)
    {
        return Fourier.fourierI(filtrePasseBasFourier(signalTemporel, frequence), 0);
    }

    /**
     * Filtre un signal temporel à l'aide d'un filtre passe-haut pour une fréquence donnée
     * @param signalTemporel le signal temporel à filtrer
     * @param frequence la fréquence de coupure
     * @return Le fourier du signal filtré
     */
    public static Signal filtrePasseHautFourier(Signal signalTemporel, double frequence)
    {
        if (!signalTemporel.estReel())
            throw new ComplexSignalException("Le signal est complexe. Signal temporel attendu");

        // Create array with filtering Nombres
        int sample = signalTemporel.getDiscretiseur().getSample();
        Nombre[] filterNombres = new Nombre[sample];
        Arrays.fill(filterNombres, Nombre.UN);

        int limitFreq = (int)Math.round(frequence);
        int startIndex = (sample/2) - limitFreq;
        int endIndex = (sample/2) + limitFreq;

        // Rejected freq
        for (int i = startIndex; i <= endIndex; ++i)
            filterNombres[i] = Nombre.ZERO;

        Signal fourier = Fourier.fourier(signalTemporel);
        Signal filterSignal = new Signal(fourier.getDiscretiseur(), filterNombres);

        return SignalOperations.produit(fourier, filterSignal);
    }

    /**
     * Filtre un signal temporel à l'aide d'un filtre passe-haut pour une fréquence donnée
     * @param signalTemporel le signal temporel à filtrer
     * @param frequence la fréquence de coupure
     * @return Le signal filtré (temporel)
     */
    public static Signal filtrePasseHaut(Signal signalTemporel, double frequence)
    {
        return Fourier.fourierI(filtrePasseHautFourier(signalTemporel, frequence), 0);
    }

    /**
     * Filtre un signal temporel à l'aide d'un filtre passe-bande pour une fréquence donnée
     * @param signalTemporel le signal temporel à filtrer
     * @param freqCoupureInf la fréquence de coupure inférieure
     * @param freqCoupureSup la fréquence de coupure supérieure
     * @return Le fourier du signal filtré
     */
    public static Signal filtrePasseBandeFourier(Signal signalTemporel, double freqCoupureInf, double freqCoupureSup)
    {
        if (!signalTemporel.estReel())
            throw new ComplexSignalException("Le signal est complexe. Signal temporel attendu");

        if (freqCoupureInf > freqCoupureSup)
            throw new BadParameterException("La fréquence de coupure inférieure est supérieure à la fréquence de coupure supérieure");

        // Create array with filtering Nombres
        int sample = signalTemporel.getDiscretiseur().getSample();
        Nombre[] filterNombres = new Nombre[sample];
        Arrays.fill(filterNombres, Nombre.ZERO);

        int roundedFreqCoupureInf = (int)Math.round(freqCoupureInf);
        int roundedFreqCoupureSup = (int)Math.round(freqCoupureSup);

        // Accepted freq - part 1
        int startIndex = (sample/2) - roundedFreqCoupureSup;
        int endIndex = (sample/2) - roundedFreqCoupureInf;
        for (int i = startIndex; i <= endIndex; ++i)
            filterNombres[i] = Nombre.UN;

        // Accepted freq - part 2
        startIndex = (sample/2) + roundedFreqCoupureInf;
        endIndex = (sample/2) + roundedFreqCoupureSup;
        for (int i = startIndex; i <= endIndex; ++i)
            filterNombres[i] = Nombre.UN;

        Signal fourier = Fourier.fourier(signalTemporel);
        Signal filterSignal = new Signal(fourier.getDiscretiseur(), filterNombres);

        return SignalOperations.produit(fourier, filterSignal);
    }

    /**
     * Filtre un signal temporel à l'aide d'un filtre passe-bande pour une fréquence donnée
     * @param signalTemporel le signal temporel à filtrer
     * @param freqCoupureInf la fréquence de coupure inférieure
     * @param freqCoupureSup la fréquence de coupure supérieure
     * @return Le signal filtré (temporel)
     */
    public static Signal filtrePasseBande(Signal signalTemporel, double freqCoupureInf, double freqCoupureSup)
    {
        return Fourier.fourierI(filtrePasseBandeFourier(signalTemporel, freqCoupureInf, freqCoupureSup), 0);
    }
}
