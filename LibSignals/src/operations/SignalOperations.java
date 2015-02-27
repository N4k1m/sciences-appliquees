/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package operations;

import exceptions.SignalOperationException;
import math.Nombre;
import signaux.Discretiseur;
import signaux.Signal;

/**
 * Cette classe encapsule plusieurs méthodes statiques qui réalisent les opérations de base pouvant être
 * appliquées à des signaux quelconques.
 * 
 * @see signaux.Signal
 * @see operations.MathOperations
 * @see operations.Convolution
 * @see operations.Fourier
 * 
 * @author lion
 */
public class SignalOperations
{
    /**
     * Cette méthode calcule l'opposé du signal passé en paramètre.
     * Le signal obtenu est donc constitué des valeurs opposées de celles du signal de départ.
     * 
     * @param signal Le signal de départ.
     * 
     * @return Le signal opposé.
     */
    public static Signal oppose(Signal signal)
    {
        int N = signal.getDiscretiseur().getSample();
        Nombre[] x = signal.getValeurs();
        Nombre[] newVal = new Nombre[N];
        
        for (int i = 0; i < N; i++)
        {
            newVal[i] = x[i].oppose();
        }
        
        Signal oppose = new Signal(signal.getDiscretiseur(), newVal);
        return oppose;
    }
    
    /**
     * Cette méthode calcule l'inverse du signal passé en paramètre.
     * Le signal obtenu est donc constitué des valeurs inverses de celles du signal de départ.
     * 
     * @param signal Le signal de départ.
     * 
     * @return Le signal inverse.
     */
    public static Signal inverse(Signal signal)
    {
        int N = signal.getDiscretiseur().getSample();
        Nombre[] x = signal.getValeurs();
        Nombre[] newVal = new Nombre[N];
        
        for (int i = 0; i < N; i++)
        {
            if (!x[i].equals(Nombre.ZERO))
            {
                newVal[i] = Nombre.UN.quotient(x[i]);
            }
            else
            {
                newVal[i] = Nombre.ZERO;
            }
        }
        
        Signal inverse = new Signal(signal.getDiscretiseur(), newVal);
        return inverse;
    }
    
    /**
     * Cette méthode calcule la somme des deux signaux passés en paramètres.
     * 
     * <p>
     * Ces deux signaux ne doivent pas obligatoirement être définis sur le même domaine, moyennant certaines
     * conditions:
     * 
     * <ul>
     * <li>les deux signaux doivent présenter le même pas de discrétisation;</li>
     * <li>l'écart entre les origines des deux signaux doit être un multiple entier de ce pas.</li>
     * </ul>
     * </p>
     * 
     * @param signal1 Le premier signal.
     * @param signal2 Le deuxième signal.
     * 
     * @return La somme de ces deux signaux.
     * 
     * @throws SignalOperationException Si les signaux ne respectent pas les conditions décrites ci-dessus.
     */
    public static Signal somme(Signal signal1, Signal signal2) throws SignalOperationException
    {
        double t0x = signal1.getDiscretiseur().getOrigine();
        double t0y = signal2.getDiscretiseur().getOrigine();
        double deltaTx = signal1.getDiscretiseur().pas();
        double deltaTy = signal2.getDiscretiseur().pas();
        double deltaT;
        double Tx = signal1.getDiscretiseur().getDuree();
        double Ty = signal2.getDiscretiseur().getDuree();
        int Nx = signal1.getDiscretiseur().getSample();
        int Ny = signal2.getDiscretiseur().getSample();
        Nombre[] x = signal1.getValeurs();
        Nombre[] y = signal2.getValeurs();
        
        // conditions pour pouvoir effectuer l'operation:
        // 1. pas egaux
        // 2. ecart des origines = multiple entier du pas
        if (deltaTx != deltaTy)
        {
            throw new SignalOperationException("signaux incompatibles");
        }
        deltaT = deltaTx;
        double ecart = Math.abs(t0x-t0y)/deltaT;
        if (ecart%1 != 0)
        {
            throw new SignalOperationException("signaux incompatibles");
        }
        
        double t0 = Math.min(t0x, t0y);
        double T = Math.max(t0x + Tx, t0y + Ty) - t0;
        int N = (int)Math.round(T/deltaT); // !!!!
        Discretiseur newDiscr = new Discretiseur(N, t0, T);
        Nombre[] newVal = new Nombre[N];
        
        int i, ind;
        for (i = 0; i < N; i++)
        {
            newVal[i] = Nombre.ZERO;
        }
        for (i = 0; i < Nx; i++)
        {
            ind = (int)((t0x-t0)/deltaT) + i;
            newVal[ind] = newVal[ind].somme(x[i]);
        }
        for (i = 0; i < Ny; i++)
        {
            ind = (int)((t0y-t0)/deltaT) + i;
            newVal[ind] = newVal[ind].somme(y[i]);
        }
        
        Signal newSignal = new Signal(newDiscr, newVal);
        return newSignal;
    }
    
    /**
     * Cette méthode calcule la différence des deux signaux passés en paramètres.
     * 
     * <p>
     * Ces deux signaux ne doivent pas obligatoirement être définis sur le même domaine, moyennant certaines
     * conditions:
     * 
     * <ul>
     * <li>les deux signaux doivent présenter le même pas de discrétisation;</li>
     * <li>l'écart entre les origines des deux signaux doit être un multiple entier de ce pas.</li>
     * </ul>
     * </p>
     * 
     * @param signal1 Le premier signal.
     * @param signal2 Le deuxième signal.
     * 
     * @return La différence de ces deux signaux.
     * 
     * @throws SignalOperationException Si les signaux ne respectent pas les conditions décrites ci-dessus.
     */
    public static Signal difference(Signal signal1, Signal signal2) throws SignalOperationException
    {
        return somme(signal1, oppose(signal2));
    }
    
    /**
     * Cette méthode calcule le produit des deux signaux passés en paramètres.
     * 
     * <p>
     * Ces deux signaux ne doivent pas obligatoirement être définis sur le même domaine, moyennant certaines
     * conditions:
     * 
     * <ul>
     * <li>les deux signaux doivent présenter le même pas de discrétisation;</li>
     * <li>l'écart entre les origines des deux signaux doit être un multiple entier de ce pas.</li>
     * </ul>
     * </p>
     * 
     * @param signal1 Le premier signal.
     * @param signal2 Le deuxième signal.
     * 
     * @return Le produit de ces deux signaux.
     * 
     * @throws SignalOperationException Si les signaux ne respectent pas les conditions décrites ci-dessus.
     */
    public static Signal produit(Signal signal1, Signal signal2) throws SignalOperationException
    {
        double t0x = signal1.getDiscretiseur().getOrigine();
        double t0y = signal2.getDiscretiseur().getOrigine();
        double deltaTx = signal1.getDiscretiseur().pas();
        double deltaTy = signal2.getDiscretiseur().pas();
        double deltaT;
        double Tx = signal1.getDiscretiseur().getDuree();
        double Ty = signal2.getDiscretiseur().getDuree();
        int Nx = signal1.getDiscretiseur().getSample();
        int Ny = signal2.getDiscretiseur().getSample();
        Nombre[] x = signal1.getValeurs();
        Nombre[] y = signal2.getValeurs();
        
        // conditions pour pouvoir effectuer l'operation:
        // 1. pas egaux
        // 2. ecart des origines = multiple entier du pas
        if (deltaTx != deltaTy)
        {
            throw new SignalOperationException("signaux incompatibles");
        }
        deltaT = deltaTx;
        double ecart = Math.abs(t0x-t0y)/deltaT;
        if (ecart%1 != 0)
        {
            throw new SignalOperationException("signaux incompatibles");
        }
        
        double t0 = Math.min(t0x, t0y);
        double T = Math.max(t0x + Tx, t0y + Ty) - t0;
        int N = (int)Math.round(T/deltaT); // !!!!
        Discretiseur newDiscr = new Discretiseur(N, t0, T);
        Nombre[] newVal = new Nombre[N];
        
        int i, ind;
        for (i = 0; i < Nx; i++)
        {
            ind = (int)((t0x-t0)/deltaT) + i;
            newVal[ind] = x[i];
        }
        boolean[] tab = new boolean[N];
        for (i = 0; i < Ny; i++)
        {
            ind = (int)((t0y-t0)/deltaT) + i;
            if (newVal[ind] != null)
            {
                newVal[ind] = newVal[ind].produit(y[i]);
                tab[ind] = true;
            }
        }
        for (i = 0; i < N; i++)
        {
            if (!tab[i])
            {
                newVal[i] = Nombre.ZERO;
            }
        }
        
        Signal newSignal = new Signal(newDiscr, newVal);
        return newSignal;
    }
    
    /**
     * Cette méthode calcule le quotient des deux signaux passés en paramètres.
     * 
     * <p>
     * Ces deux signaux ne doivent pas obligatoirement être définis sur le même domaine, moyennant certaines
     * conditions:
     * 
     * <ul>
     * <li>les deux signaux doivent présenter le même pas de discrétisation;</li>
     * <li>l'écart entre les origines des deux signaux doit être un multiple entier de ce pas.</li>
     * </ul>
     * </p>
     * 
     * @param signal1 Le premier signal.
     * @param signal2 Le deuxième signal.
     * 
     * @return Le quotient de ces deux signaux.
     * 
     * @throws SignalOperationException Si les signaux ne respectent pas les conditions décrites ci-dessus.
     */
    public static Signal quotient(Signal signal1, Signal signal2)
    {
        return produit(signal1, inverse(signal2));
    }
}
