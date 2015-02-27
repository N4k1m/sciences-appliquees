/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package operations;

import exceptions.BadStepsException;
import math.Nombre;
import signaux.Discretiseur;
import signaux.Signal;

/**
 * Cette classe encapsule la méthode statique qui réalise le calcul du produit de convolution de deux signaux.
 * 
 * @author lion
 */
public class Convolution
{
    /**
     * Cette méthode réalise le calcul du produit de convolution de deux signaux.
     * Pour que le calcul puisse être effectué, les deux signaux à convoluer doivent avoir le même pas de
     * discrétisation.
     * 
     * @param signal1 Le premier signal à convoluer.
     * @param signal2 Le deuxième signal à convoluer.
     * 
     * @return Le signal représentant le produit de convolution des deux signaux.
     * 
     * @throws BadStepsException Si les deux signaux à convoluer ne présentent pas le même pas de discrétisation.
     */
    public static Signal convolution(Signal signal1, Signal signal2) throws BadStepsException
    {
        double deltaT = signal1.getDiscretiseur().pas();
        int Nx = signal1.getDiscretiseur().getSample();
        int Nh = signal2.getDiscretiseur().getSample();
        int Ny = Nx + Nh;
        double t0y = signal1.getDiscretiseur().getOrigine() + signal2.getDiscretiseur().getOrigine();
        double Ty = signal1.getDiscretiseur().getDuree() + signal2.getDiscretiseur().getDuree();
        Nombre[] x = signal1.getValeurs();
        Nombre[] h = signal2.getValeurs();
        
        if (deltaT != signal2.getDiscretiseur().pas())
        {
            throw new BadStepsException("ERREUR: les signaux ont des pas differents");
        }
        
        Nombre[] y = new Nombre[Ny];
        for (int k = 0; k < Ny; k++)
        {
            Nombre sum = Nombre.ZERO;
            for (int i = 0; i < Nx; i++)
            {
                if (k-i >= 0 && k-i < Nh)
                    sum = sum.somme(x[i].produit(h[k-i]));
            }
            y[k] = sum.produit(new Nombre(deltaT, 0.0));
        }
        
        Discretiseur discr = new Discretiseur(Ny, t0y, Ty);
        Signal ret = new Signal(discr, y);
        return ret;
    }
}
