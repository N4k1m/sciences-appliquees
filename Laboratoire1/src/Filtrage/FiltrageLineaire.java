package Filtrage;

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
}
