/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package operations;

import math.Nombre;
import signaux.Discretiseur;
import signaux.Signal;

/**
 * Cette classe encapsule les méthodes statiques qui réalisent le calcul de la transformée de Fourier d'un signal
 * quelconque.
 * Le calcul de la transformée de Fourier est réalisé en appelant la méthode {@link #fourier(signaux.Signal)}
 * tandis que le calcul de la transformée de Fourier inverse est réalisé par la méthode
 * {@link #fourierI(signaux.Signal, double)}.
 * 
 * <p>
 * Si le sample du signal de départ est une puissance de 2, c'est l'algorithme FFT qui est utilisé pour le calcul.
 * Si le sample n'est pas une puissance de 2, c'est l'algorithme classique (DFT) qui est utilisé. Cet algorithme
 * étant moins efficace que la FFT, il est recommandé de choisir comme sample pour le signal de départ une
 * puissance de 2.
 * </p>
 * 
 * @author lion
 */
public class Fourier
{
    /**
     * Cette méthode réalise le calcul de la transformée de Fourier (ou son inverse) du vecteur passé en
     * paramètre à l'aide de l'algorithme DFT.
     * Elle n'est pas destinée à être appelée directement par l'utilisateur.
     * 
     * @param g Le vecteur de nombres (complexes) de départ.
     * @param inverse La valeur booléenne permettant de spécifier si c'est la transformée directe ou inverse qui
     * doit être calculée.
     * 
     * @return Le vecteur de nombres (complexes) obtenu après transformée de Fourier.
     */
    private static Nombre[] dft(Nombre[] g, boolean inverse)
    {
        int N = g.length;
        Nombre[] X = new Nombre[N];
        
        double cos_n, sin_n, cos_prec_n, sin_prec_n, COS_INCR_N, SIN_INCR_N;
        double R_n, I_n;
        
        // calcul de X
        for (int m = 0; m < N; m++)
        {   
            COS_INCR_N = Math.cos(2*Math.PI*m/N);
            SIN_INCR_N = Math.sin(2*Math.PI*m/N);
            // cas n = 0
            R_n = g[0].partieReelle();
            I_n = g[0].partieImaginaire();
            cos_prec_n = 1;
            sin_prec_n = 0;
            // cas n = 1, ..., N-1
            for (int n = 1; n < N; n++)
            {
                cos_n = cos_prec_n*COS_INCR_N - sin_prec_n*SIN_INCR_N;
                sin_n = sin_prec_n*COS_INCR_N + cos_prec_n*SIN_INCR_N;
                
                // fourier ou fourier inverse?
                if (!inverse)
                {
                    R_n +=  g[n].partieReelle() * cos_n + g[n].partieImaginaire() * sin_n;
                    I_n += -g[n].partieReelle() * sin_n + g[n].partieImaginaire() * cos_n;
                }
                else
                {
                    R_n += g[n].partieReelle() * cos_n - g[n].partieImaginaire() * sin_n;
                    I_n += g[n].partieReelle() * sin_n + g[n].partieImaginaire() * cos_n;
                }
                cos_prec_n = cos_n;
                sin_prec_n = sin_n;
            }
            
            if (inverse)
            {
                R_n /= N;
                I_n /= N;
            }
            X[m] = new Nombre(R_n, I_n);
	}
        
        return X;
    }
    
    /**
     * Cette méthode réalise le calcul de la transformée de Fourier du vecteur passé en paramètre à l'aide de
     * l'algorithme FFT.
     * Elle n'est pas destinée à être appelée directement par l'utilisateur.
     * 
     * @param g Le vecteur de nombres (complexes) de départ.
     * 
     * @return Le vecteur de nombres (complexes) obtenu après transformée de Fourier.
     */
    private static Nombre[] fft(Nombre[] g)
    {
        int N = g.length;
        
        if (N == 1)
        {
            return new Nombre[] {g[0]};
        }
        
        // fft des indices pairs
        Nombre[] pairs = new Nombre[N/2];
        for (int k = 0; k < N/2; k++)
        {
            pairs[k] = g[2*k];
        }
        Nombre[] q = fft(pairs);
            
        // fft des indices impairs
        Nombre[] impairs = pairs;
        for (int k = 0; k < N/2; k++)
        {
            impairs[k] = g[2*k + 1];
        }
        Nombre[] r = fft(impairs);
            
        double COS_INCR = Math.cos(2*Math.PI/N);
        double SIN_INCR = Math.sin(2*Math.PI/N);
        double cos_prec, sin_prec, cos_k, sin_k;
        Nombre wk;
        Nombre[] X = new Nombre[N];
        
        // calcul de X
        // cas k = 0
        cos_prec = 1; sin_prec = 0;
        wk = new Nombre(1, 0);
        X[0] = q[0].somme(wk.produit(r[0]));
        X[N/2] = q[0].difference(wk.produit(r[0]));
        
        // cas k = 1, ..., N/2 - 1
        for (int k = 1; k < N/2; k++)
        {
            cos_k = cos_prec*COS_INCR + sin_prec*SIN_INCR;
            sin_k = sin_prec*COS_INCR - cos_prec*SIN_INCR;
            wk = new Nombre(cos_k, sin_k);
            X[k] = q[k].somme(wk.produit(r[k]));
            X[k + N/2] = q[k].difference(wk.produit(r[k]));
            
            cos_prec = cos_k; sin_prec = sin_k;
        }
        
        return X;
    }
    
    /**
     * Cette méthode réalise le calcul de la transformée de Fourier inverse du vecteur passé en paramètre à
     * l'aide de l'algorithme FFT.
     * Elle n'est pas destinée à être appelée directement par l'utilisateur.
     * 
     * @param G Le vecteur de nombres (complexes) de départ.
     * 
     * @return Le vecteur de nombres (complexes) obtenu après transformée de Fourier inverse.
     */
    private static Nombre[] ifft(Nombre[] G)
    {
        int N = G.length, i;
        Nombre[] y = new Nombre[N];

        for (i = 0; i < N; i++)
        {
            y[i] = G[i].conjugue();
        }

        y = fft(y);

        for (i = 0; i < N; i++)
        {
            y[i] = y[i].conjugue();
        }

        for (i = 0; i < N; i++)
        {
            y[i] = y[i].produit(new Nombre(1.0/N, 0));
        }

        return y;
    }
    
    /**
     * Cette méthode réalise le calcul de la transformée de Fourier du signal passé en paramètre.
     * Selon que le sample de ce signal est une puissance de 2 ou non, c'est l'algorithme FFT ou DFT qui sera
     * utilisé.
     * 
     * @param signal Le signal de départ.
     * 
     * @return La transformée de Fourier du signal de départ.
     */
    public static Signal fourier(Signal signal)
    {
        int N = signal.getDiscretiseur().getSample();
        double t0 = signal.getDiscretiseur().getOrigine();
        double deltaT = signal.getDiscretiseur().pas();
        double deltaF = 1/(N*deltaT);
        double cos_m, sin_m, cos_prec_m, sin_prec_m;
        double COS_INCR_M = Math.cos(2*Math.PI*t0*deltaF), SIN_INCR_M = Math.sin(2*Math.PI*t0*deltaF);
        Nombre[] G = new Nombre[N], X;
        Nombre temp, coeff;
        
        // calcul de X
        // N puissance de 2?
        if ((N != 0) && ((N & (N - 1)) == 0))
        {
            System.out.println("FFT");
            X = fft(signal.getValeurs());
        }
        else
        {
            System.out.println("DFT");
            X = dft(signal.getValeurs(), false);
        }
        
        // calcul de G
        // cas m = 0
        cos_prec_m = 1; sin_prec_m = 0;
        G[0] = X[0].produit(new Nombre(deltaT, 0.0));
        // cas m = 1, ..., N-1
        for (int m = 1; m < N; m++)
        {
            // calcul coefficient
            cos_m = cos_prec_m*COS_INCR_M - sin_prec_m*SIN_INCR_M;
            sin_m = sin_prec_m*COS_INCR_M + cos_prec_m*SIN_INCR_M;
            coeff = new Nombre(deltaT*cos_m, -deltaT*sin_m);
            cos_prec_m = cos_m;
            sin_prec_m = sin_m;
            
            // calcul de Gm
            G[m] = coeff.produit(X[m]);
        }
        
        // permutation du vecteur
        Nombre[] tab = new Nombre[N];
        if (N%2 == 0)
        {
            for (int k = 0; k < N/2; k++)
            {
                temp = G[k];
                G[k] = G[k + N/2];
                G[k + N/2] = temp;
            }
        }
        else
        {
            int i;
            for (i = 0; i <= N/2; i++)
            {
                tab[N/2+i] = G[i];
            }
            for (i = 0; i < N/2; i++)
            {
                tab[i] = G[N/2+1+i];
            }
            for (i = 0; i < N; i++)
            {
                G[i] = tab[i];
            }
        }
        
        Discretiseur discr = new Discretiseur(N, -1/(2*deltaT), 1/deltaT);
        Signal fourier = new Signal(discr, G);
        return fourier;
    }
    
    /**
     * Cette méthode réalise le calcul de la transformée de Fourier inverse du signal passé en paramètre.
     * Selon que le sample de ce signal est une puissance de 2 ou non, c'est l'algorithme FFT ou DFT qui sera
     * utilisé.
     * 
     * <p>
     * Dans le cas de la transformée de Fourier inverse, l'utilisateur doit spécifier l'instant initial du
     * signal obtenu après calcul. En effet, il n'est pas possible de déduire cet instant initial à partir du
     * spectre du signal en question.
     * </p>
     * 
     * @param signal Le signal de départ.
     * @param t0 L'instant initial du signal obtenu après transformée de Fourier inverse.
     * 
     * @return Le signal obtenu après transformée de Fourier inverse.
     */
    public static Signal fourierI(Signal signal, double t0)
    {
        int N = signal.getDiscretiseur().getSample();
        double deltaF = signal.getDiscretiseur().pas();
        Nombre[] G = new Nombre[N], g;
        Nombre temp;
        System.arraycopy(signal.getValeurs(), 0, G, 0, N);
        
        // depermutation vecteur
        Nombre[] tab = new Nombre[N];
        if (N%2 == 0) // N pair --> f = 0 est au debut de la partie droite du vecteur
        {
            for (int k = 0; k < N/2; k++)
            {
                temp = G[k];
                G[k] = G[k + N/2];
                G[k + N/2] = temp;
            }
        }
        else // N impair --> f = 0 est au milieu du vecteur
        {
            int i;
            for (i = 0; i < N/2; i++)
            {
                tab[N/2+1+i] = G[i];
            }
            for (i = 0; i <= N/2; i++)
            {
                tab[i] = G[N/2+i];
            }
            for (i = 0; i < N; i++)
            {
                G[i] = tab[i];
            }
        }
        
        // calcul de X a passer a dft/fft
        Nombre[] coeff = new Nombre[N];
        double COS_INCR = Math.cos(2*Math.PI*t0*deltaF), SIN_INCR = Math.sin(2*Math.PI*t0*deltaF);
        double cos_prec = 1, sin_prec = 0, cos_m, sin_m;
        coeff[0] = G[0].produit(new Nombre(deltaF*N, 0));
        Nombre numerateur, denominateur;
        for (int m = 1; m < N; m++)
        {
            cos_m = cos_prec*COS_INCR - sin_prec*SIN_INCR;
            sin_m = sin_prec*COS_INCR + cos_prec*SIN_INCR;
            numerateur = G[m].produit(new Nombre(deltaF*N, 0));
            denominateur = new Nombre(cos_m, -sin_m);
            coeff[m] = numerateur.quotient(denominateur);
            cos_prec = cos_m; sin_prec = sin_m;
        }
        
        // calcul de g
        // N puissance de 2?
        if ((N != 0) && ((N & (N - 1)) == 0))
        {
            System.out.println("IFFT");
            g = ifft(coeff);
        }
        else
        {
            System.out.println("IDFT");
            g = dft(coeff, true);
        }
        
        Discretiseur discr = new Discretiseur(N, t0, 1/deltaF);
        Signal fourierI = new Signal(discr, g);
        return fourierI;
    }
}
