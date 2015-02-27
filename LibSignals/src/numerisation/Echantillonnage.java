/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package numerisation;

import exceptions.BadParameterException;
import math.Nombre;
import signaux.SignalAnalogique;

/**
 * Cette classe encapsule des méthodes qui réalisent différents types d'échantillonnage d'un signal analogique.
 * Ces méthodes statiques sont au nombre de trois et réalisent les trois grands types d'échantillonnage suivants:
 * 
 * <ul>
 * <li>échantillonnage idéal;</li>
 * <li>échantillonnage par "circuit commutateur";</li>
 * <li>échantillonnage par circuit "échantillonneur/bloqueur"</li>
 * </ul>
 * 
 * @author lion
 */
public class Echantillonnage // interface Echantillonneur ???!!!
{
    /**
     * Cette méthode implémente l'échantillonnage idéal d'un signal analogique.
     * 
     * @param signal Le signal analogique à échantillonner.
     * @param debut L'"instant" initial d'échantillonnage.
     * @param periode La période d'échantillonnage.
     * 
     * @return Le signal obtenu après échantillonnage.
     * 
     * @throws BadParameterException Si l'instant initial spécifié ne tombe pas pile sur un échantillon du signal
     * de départ, ou si la période d'échantillonnage spécifiée n'est pas un multiple entier du pas du signal de
     * départ.
     */
    public static SignalAnalogique ideal(SignalAnalogique signal, double debut, double periode)
            throws BadParameterException
    {
        double ti = debut;
        double Ts = periode;
        int N = signal.getDiscretiseur().getSample();
        double t0 = signal.getDiscretiseur().getOrigine();
        double deltaT = signal.getDiscretiseur().pas();
        int Ni = (int)((ti - t0)/deltaT);
        int Nk = (int)(Ts/deltaT);
        Nombre[] f = signal.getValeurs();
        
        // conditions d'echantillonnage:
        // - (ti-t0) multiple du pas
        // - Ts multiple du pas
        if (((ti-t0)/deltaT)%1 != 0)
        {
            throw new BadParameterException("l'instant initial doit etre un mutliple entier du pas");
        }
        if ((Ts/deltaT)%1 != 0)
        {
            throw new BadParameterException("la periode d'echantillonnage doit etre un mutliple entier du pas");
        }
        
        Nombre[] echantillons = new Nombre[N];
        int k;
        for (k = 0; k < Ni; k++)
        {
            echantillons[k] = Nombre.ZERO;
        }
        for (; k < N; k++)
        {
            if (k%Nk == 0)
            {
                echantillons[k] = f[k];
            }
            else
            {
                echantillons[k] = Nombre.ZERO;
            }
        }
        
        SignalAnalogique resultat = new SignalAnalogique(signal.getDiscretiseur(), echantillons, null);
        return resultat;
    }
    
    /**
     * Cette méthode implémente l'échantillonnage par "circuit échantillonneur-bloqueur" d'un signal analogique.
     * 
     * @param signal Le signal analogique à échantillonner.
     * @param debut L'"instant" initial d'échantillonnage.
     * @param periode La période d'échantillonnage.
     * @param dureeBlocage La durée de blocage des valeurs du signal.
     * 
     * @return Le signal obtenu après échantillonnage.
     * 
     * @throws BadParameterException Si l'instant initial spécifié ne tombe pas pile sur un échantillon du signal
     * de départ, ou si la période d'échantillonnage spécifiée n'est pas un multiple entier du pas du signal de
     * départ.
     */
    public static SignalAnalogique blocage(SignalAnalogique signal, double debut, double periode, double dureeBlocage)
            throws BadParameterException
    {
        double ti = debut;
        double Ts = periode;
        double Tb = dureeBlocage;
        double deltaT = signal.getDiscretiseur().pas();
        double t0 = signal.getDiscretiseur().getOrigine();
        int N = signal.getDiscretiseur().getSample();
        int Ni = (int)((ti - t0)/deltaT); // indice de depart
        int Nk = (int)(Ts/deltaT); // ecart (nb samples) entre deux echantillons
        int Nb = (int)(Tb/deltaT); // nb samples de blocage
        Nombre[] f = signal.getValeurs();
        
        // conditions d'echantillonnage:
        // - (ti-t0) multiple du pas
        // - Ts multiple du pas
        if (((ti-t0)/deltaT)%1 != 0)
        {
            throw new BadParameterException("temps initial incorrect");
        }
        if ((Ts/deltaT)%1 != 0)
        {
            throw new BadParameterException("periode d'echantillonnage incorrecte");
        }
        
        Nombre[] echantillons = new Nombre[N];
        Nombre fe; // valeur du signal au point d'echantillonnage
        int k, borne;
        for (k = 0; k < Ni; k++)
        {
            echantillons[k] = Nombre.ZERO;
        }
        for (; k < N; k++)
        {
            if (k%Nk == 0)
            {
                borne = k+Nb;
                fe = f[k];
                for (; k < borne && k < N; k++)
                {
                    echantillons[k] = fe;
                }
                if (k < N)
                {
                    echantillons[k] = Nombre.ZERO;
                }
            }
            else
            {
                echantillons[k] = Nombre.ZERO;
            }
        }
        
        SignalAnalogique resultat = new SignalAnalogique(signal.getDiscretiseur(), echantillons, null);
        return resultat;
    }
    
    /**
     * Cette méthode implémente l'échantillonnage par "circuit commutateur" d'un signal analogique.
     * 
     * @param signal Le signal analogique à échantillonner.
     * @param debut L'"instant" initial d'échantillonnage.
     * @param periode La période d'échantillonnage.
     * @param dureeBlocage La durée de "blocage" des valeurs du signal.
     * 
     * @return Le signal obtenu après échantillonnage.
     * 
     * @throws BadParameterException Si l'instant initial spécifié ne tombe pas pile sur un échantillon du signal
     * de départ, ou si la période d'échantillonnage et/ou la durée de blocage spécifiée n'est pas un multiple
     * entier du pas du signal de départ.
     */
    public static SignalAnalogique commutateur(SignalAnalogique signal, double debut, double periode, double dureeBlocage)
            throws BadParameterException
    {
        double ti = debut;
        double Ts = periode;
        double Tb = dureeBlocage;
        double deltaT = signal.getDiscretiseur().pas();
        double t0 = signal.getDiscretiseur().getOrigine();
        int N = signal.getDiscretiseur().getSample();
        int Ni = (int)((ti - t0)/deltaT); // indice de depart
        int Nk = (int)(Ts/deltaT); // ecart (nb samples) entre deux echantillons
        int Nb = (int)(Tb/deltaT); // nb samples de blocage
        Nombre[] f = signal.getValeurs();
        
        // conditions d'echantillonnage:
        // - (ti-t0) multiple du pas
        // - Ts multiple du pas
        // - Tb multiple du pas
        if (((ti-t0)/deltaT)%1 != 0)
        {
            throw new BadParameterException("temps initial incorrect");
        }
        if ((Ts/deltaT)%1 != 0)
        {
            throw new BadParameterException("periode d'echantillonnage incorrecte");
        }
        if ((Tb/deltaT)%1 != 0)
        {
            throw new BadParameterException("duree blocage incorrecte");
        }
        
        int k, borne;
        Nombre[] echantillons = new Nombre[N];
        for (k = 0; k < Ni; k++)
        {
            echantillons[k] = Nombre.ZERO;
        }
        for (; k < N; k++)
        {
            if (k%Nk == 0)
            {
                borne = k+Nb;
                for (; k < borne && k < N; k++)
                {
                    echantillons[k] = f[k];
                }
                if (k < N)
                {
                    echantillons[k] = Nombre.ZERO;
                }
            }
            else
            {
                echantillons[k] = Nombre.ZERO;
            }
        }
        
        SignalAnalogique resultat = new SignalAnalogique(signal.getDiscretiseur(), echantillons, null);
        return resultat;
    }
}
