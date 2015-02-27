/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package numerisation;

import exceptions.BadTypeException;
import java.util.BitSet;
import math.Nombre;
import signaux.Discretiseur;
import signaux.SignalAnalogique;
import signaux.SignalBinaire;
import signaux.SignalNumerique;

/**
 * Cette classe réalise les différentes opérations nécessaires à la "dénumérisation" (conversion numérique ->
 * analogique) d'un signal binaire.
 * <p>
 * Les opérations de décodage et de conversion numérique/analogique peuvent être réalisées indépendamment l'une
 * de l'autre, en veillant à appliquer d'abord le décodage puis la conversion N/A au signal obtenu.
 * </p>
 * 
 * @see signaux.SignalAnalogique
 * @see signaux.SignalBinaire
 * @see signaux.SignalNumerique
 * @see numerisation.Codec
 * 
 * @author lion
 */
public class DAC
{
    /**
     * Variable statique spécifiant une conversion numérique/analogique de type "blocage".
     * Pour ce type de conversion, les valeurs situées entre deux valeurs quantifiées du signal numérique obtenu
     * après décodage sont fixées à la première de ces deux valeurs (interpolation "horizontale").
     */
    public static final int BLOCAGE = 0;
    
    /**
     * Variable statique spécifiant une conversion numérique/analogique de type "linéaire".
     * Pour ce type de conversion, les valeurs situées entre deux valeurs quantifiées du signal numérique obtenu
     * après décodage sont fixées selon la droite joignant les deux points (interpolation linéaire).
     */
    public static final int LINEAIRE = 1;
    
    /**
     * Cette méthode réalise l'opération de décodage d'un signal binaire.
     * 
     * @param signal Le signal binaire à décoder.
     * @param codec Le codec à utiliser pour le décodage.
     * 
     * @return Le signal numérique obtenu.
     */
    public SignalNumerique decodage(SignalBinaire signal, Codec codec)
    {
        int R = codec.getNbBitsParEchantillon();
        int Nx = signal.getDiscretiseur().getSample();
        int Ny = Nx/R;
        double ti = signal.getDiscretiseur().getOrigine();
        double T = signal.getDiscretiseur().getDuree();
        Nombre[] x = signal.getValeurs();
        Nombre[] y = new Nombre[Ny];
        BitSet bits;
        
        int i, j, indice = 0;
        for (i = 0; i < Ny; i++)
        {
            bits = new BitSet(R);
            for (j = R-1; j >= 0; j--)
            {
                if (x[indice].equals(Nombre.UN))
                {
                    bits.set(j);
                }
                indice++;
            }
            System.out.println("Bits lus: " + bits.toString());
            y[i] = codec.decode(bits);
        }
        
        Discretiseur discr = new Discretiseur(Ny, ti, T);
        SignalNumerique signalQuant = new SignalNumerique(discr, y);
        return signalQuant;
    }
    
    /**
     * Cette méthode réalise la conversion numérique/analogique d'un signal numérique obtenu après décodage.
     * 
     * @param signalNum Le signal numérique à convertir.
     * @param type Le type de conversion (ou d'interpolation) à utiliser: BLOCAGE ou LINEAIRE.
     * @param sample Le sample souhaité pour le signal analogique de sortie.
     * 
     * @return Le signal analogique obtenu.
     * 
     * @throws BadTypeException Si le type spécifié n'est pas défini.
     */
    public SignalAnalogique conversionAnalog(SignalNumerique signalNum, int type, int sample) throws BadTypeException
    {
        int Nx = signalNum.getDiscretiseur().getSample();
        int Ny = sample;
        double t0 = signalNum.getDiscretiseur().getOrigine();
        double T = signalNum.getDiscretiseur().getDuree();
        double Ts = T/Nx; // periode d'echantillonnage (intervalle entre les echantillons du signal numerique)
        double deltaT = T/Ny; // pas du signal analogique reconstitue
        int N = Ny/Nx; // nb d'echantillons a inserer entre chaque valeur du signal numerique
        Discretiseur discr = new Discretiseur(Ny, t0, T);
        Nombre[] x = signalNum.getValeurs();
        Nombre[] y = new Nombre[Ny];
        
        int i, j;
        Nombre a, b, xa, xb, ya, yb; // parametres de la droite pour interpolation lineaire
        switch (type)
        {
            case BLOCAGE:
                for (i = 0; i < Nx; i++)
                {
                    for (j = 0; j < N; j++)
                    {
                        y[i*N+j] = x[i];
                    }
                }
                break;
                
            case LINEAIRE:
                for (i = 0; i < Nx-1; i++)
                {
                    // recherche equation droite
                    xa = new Nombre(t0+i*Ts, 0);
                    xb = new Nombre(t0+(i+1)*Ts, 0);
                    ya = x[i];
                    yb = x[i+1];
                    a = yb.difference(ya).quotient(xb.difference(xa));
                    b = ya.produit(xb).difference(xa.produit(yb)).quotient(xb.difference(xa));
                    for (j = 0; j < N; j++)
                    {
                        y[i*N+j] = a.produit(new Nombre(t0+(i*N+j)*deltaT, 0)).somme(b);
                    }
                }
                for (i = (Nx-1)*N; i < Ny; i++) // completion du vecteur (signal analogique)
                {
                    y[i] = x[Nx-1];
                }
                break;
                
            default:
                throw new BadTypeException("type inconnu");
        }
        
        SignalAnalogique signal = new SignalAnalogique(discr, y, null);
        return signal;
    }
}
