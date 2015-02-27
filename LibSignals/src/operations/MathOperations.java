/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package operations;

import exceptions.BadParameterException;
import math.FonctionSigne;
import math.Nombre;
import signaux.Discretiseur;
import signaux.Signal;
import signaux.SignalAnalogique;

/**
 * Cette classe encapsule quelques méthodes statiques qui réalisent certaines opérations mathématiques pouvant
 * être appliquées à un signal.
 * 
 * @author lion
 */
public class MathOperations
{
    /**
     * Cette méthode calcule une approximation de la dérivée du signal spécifié.
     * 
     * @param signal Le signal à dériver.
     * 
     * @return La dérivée du signal de départ.
     */
    public static Signal derivation(Signal signal)
    {
        int N = signal.getDiscretiseur().getSample();
        double t0 = signal.getDiscretiseur().getOrigine();
        double T = signal.getDiscretiseur().getDuree();
        Nombre h = new Nombre(signal.getDiscretiseur().pas(), 0);
        Nombre[] f = signal.getValeurs();
        Nombre[] df = new Nombre[N];
        
        // valeurs limites
        df[0] = f[1].difference(f[0]).quotient(h);
        df[N-1] = f[N-1].difference(f[N-2]).quotient(h);
        
        for (int k = 1; k < N-1; k++)
        {
            df[k] = f[k+1].difference(f[k-1]).quotient(h.produit(new Nombre(2, 0)));
        }
        
        Discretiseur discr = new Discretiseur(N, t0, T);
        Signal derivee = new Signal(discr, df);
        return derivee;
    }
    
    /**
     * Cette méthode calcule une approximation de l'intégrale du signal spécifié.
     * Il est possible d'intégrer soit sur tout le domaine du signal, soit sur une période choisie par
     * l'utilisateur en remettant éventuellement à 0 l'intégrateur au bout de chaque période d'intégration.
     * 
     * <p>
     * Attention: si la période d'intégration spécifiée n'est pas un multiple entier du pas du signal à intégrer,
     * des imprécisions vont apparaître durant l'intégration. Il est vivement conseillé de choisir une période
     * d'intégration qui soit un multiple entier du pas du signal à intégrer.
     * </p>
     * 
     * @param signal Le signal à intégrer.
     * @param debut L'"instant" initial d'intégration.
     * @param dureeIntegration La période d'intégration. Cette valeur peut être le domaine entier du signal.
     * @param remiseAZero Valeur booléenne spécifiant si l'intégrateur doit être remis à 0 ou non au bout de
     * chaque période d'intégration.
     * 
     * @return Le signal obtenu après intégration du signal de départ.
     */
    public static Signal integration(Signal signal, double debut, double dureeIntegration, boolean remiseAZero)
    {
        int N = signal.getDiscretiseur().getSample();
        double t0 = signal.getDiscretiseur().getOrigine();
        double deltaT = signal.getDiscretiseur().pas();
        double ti = debut;
        double Ti = dureeIntegration;
        int Ni = (int)((ti-t0)/deltaT); // ecart (nb samples) entre ti et t0
        int Nk = (int)(Ti/deltaT); // nb samples de la duree d'integration
        Nombre[] f = signal.getValeurs();
        
        Nombre[] S = new Nombre[N];
        Nombre somme = new Nombre();
        int k, borne;
        for (k = 0; k < Ni; k++)
        {
            S[k] = Nombre.ZERO;
        }
        while (k < N)
        {
            borne = k+Nk;
            while (k < borne && k < N)
            {
                somme = somme.somme(f[k].produit(new Nombre(deltaT, 0)));
                S[k] = somme;
                k++;
            }
            if (k < N)
            {
                if (remiseAZero)
                {
                    S[k] = Nombre.ZERO;
                    somme = Nombre.ZERO;
                }
                else
                {
                    somme = somme.somme(f[k].produit(new Nombre(deltaT, 0)));
                    S[k] = somme;
                }
            }
        }
        
        Signal integrale = new Signal(signal.getDiscretiseur(), S);
        return integrale;
    }
    
    /**
     * Cette méthode réalise le calcul de la transformée de Hilbert d'un signal quelconque.
     * 
     * @param signal Le signal de départ.
     * 
     * @return La transformée de Hilbert du signal de départ.
     */
    public static Signal hilbert(Signal signal)
    {
        Signal fourier = Fourier.fourier(signal);
        Signal _j = SignalAnalogique.getInstance(SignalAnalogique.CONSTANT, new Nombre(0, -1),
                fourier.getDiscretiseur());
        Signal fctSigne = SignalAnalogique.getInstance(new FonctionSigne(), fourier.getDiscretiseur());
        
        // calcul transformee de Hilbert
        Signal fourierH = SignalOperations.produit(_j, SignalOperations.produit(fctSigne, fourier));
        Signal hilbert = Fourier.fourierI(fourierH, signal.getDiscretiseur().getOrigine());
        
        return hilbert;
    }
}
