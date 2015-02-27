/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package numerisation;

import java.util.BitSet;
import java.util.HashMap;
import math.Nombre;

/**
 * Cette classe modélise la notion de codec linéaire.
 * Pour modéliser un codec linéaire particulier, il suffit d'instancier cette classe en initialisant les
 * paramètres suivants:
 * 
 * <ul>
 * <li>valeur minimum de l'intervalle de quantification;</li>
 * <li>valeur maximum de l'intervalle de quantification;</li>
 * <li>nombre de bits par symbole (le nombre de niveaux de quantification est donné par 2^R si R est le nombre de
 * bits par symbole).</li>
 * </ul>
 * 
 * @see math.Nombre
 * @see java.util.HashMap
 * @see java.util.BitSet
 * 
 * @author lion
 */
public class CodecLineaire implements Codec
{
    /**
     * Valeur minimum de l'intervalle de quantification.
     */
    private double min;
    
    /**
     * Valeur maximum de l'intervalle de quantification.
     */
    private double max;
    
    /**
     * Nombre de bits par symbole.
     */
    private int R;
    
    /**
     * Table de quantification.
     * Cette table contient les valeurs de quantification associées à chaque sous-intervalle de l'intervalle de
     * quantification.
     */
    private HashMap tableQuantification;
    
    /**
     * Table de codage.
     * Cette table contient les associations entre les valeurs de quantification et les symboles correspondants.
     */
    private HashMap tableCodage;
    
    /**
     * Constructeur par défaut.
     * L'intervalle de quantification est fixé à [0,4], le nombre de bits par symbole est fixé à 2.
     */
    public CodecLineaire()
    {
        min = 0;
        max = 4;
        R = 2;
        initTables();
    }
    
    /**
     * Constructeur d'initialisation.
     * 
     * @param min La valeur minimum de l'intervalle de quantification.
     * @param max La valeur maximum de l'intervalle de quantification.
     * @param R Le nombre de bits par symbole.
     */
    public CodecLineaire(double min, double max, int R)
    {
        this.min = min;
        this.max = max;
        this.R = R;
        initTables();
    }
    
    /**
     * Cette méthode retourne le nombre de niveaux de quantification définis par ce codec.
     * 
     * @return Le nombre de niveaux de quantification.
     */
    private int nbNiveaux()
    {
        return (int)Math.pow(2, R);
    }
    
    /**
     * Cette méthode retourne la largeur des sous-intervalles de l'intervalle de quantification.
     * Ces sous-intervalles ont tous la même largeur puisqu'il s'agit d'un codec <i>linéaire</i>.
     * 
     * @return La largeur des sous-intervalles.
     */
    private double ecart()
    {
        return (max-min)/nbNiveaux();
    }
    
    /**
     * Cette méthode initialise les tables de quantification et de codage en fonction des valeurs min, max et R.
     */
    private void initTables()
    {
        int L = nbNiveaux(), i;
        double ecart = ecart();
        // creation table de quantification
        tableQuantification = new HashMap(L);
        for (i = 0; i < L; i++)
        {
            tableQuantification.put(i, new Nombre(min + i*ecart + ecart/2, 0));
        }
        
        // creation table de codage
        tableCodage = new HashMap(L);
        long[] tab = new long[1];
        for (i = 0; i < L; i++)
        {
            tab[0] = i;
            tableCodage.put((Nombre)tableQuantification.get(i), BitSet.valueOf(tab));
        }
    }
    
    @Override
    public int getNbBitsParEchantillon()
    {
        return R;
    }
    
    @Override
    public Nombre quantifie(Nombre valeur)
    {
        int i = 0;
        double ecart = ecart();
        while (valeur.partieReelle() > min + (i+1)*ecart)
        {
            i++;
        }
        
        return (Nombre)tableQuantification.get(i);
    }
    
    @Override
    public BitSet code(Nombre valeur) // renvoie un BitSet de 64 bits sauf si plus grand
    {
        return (BitSet)tableCodage.get(valeur);
    }

    @Override
    public Nombre decode(BitSet bits)
    {
        Nombre q = null;
        BitSet set;
        int L = nbNiveaux();
        for (int i = 0; i < L; i++)
        {
            q = (Nombre)tableQuantification.get(i);
            set = (BitSet)tableCodage.get(q);
            if (set.equals(bits))
            {
                System.out.println("Valeur matchee: " + q.formeAlgebrique());
                break;
            }
        }
        
        return q;
    }
}
