/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

import exceptions.MathException;

/**
 * Cette classe modélise la notion de nombre complexe.
 * Elle est utilisée partout dans la librairie pour représenter les nombres complexes.
 * L'utilisateur peut instancier un nombre complexe de deux manières:
 * 
 * <ul>
 * <li>en spécifiant les parties réelle et imaginaire via la constructeur;</li>
 * <li>en spécifiant le module et l'argument via la factory {@link #getInstance(double, double)}.</li>
 * </ul>
 * 
 * @see signaux.Signal
 * 
 * @author lion
 */
public class Nombre
{
    /**
     * Cette variable représente le complexe particulier 0.
     */
    public static final Nombre ZERO = new Nombre(0, 0);
    
    /**
     * Cette variable représente le complexe particulier 1.
     */
    public static final Nombre UN = new Nombre(1, 0);
    
    /**
     * Cette variable représente le seuil en dessous duquel un nombre de type double sera considéré comme nul.
     * Elle est utilisée dans le constructeur de la classe pour éviter la propagation de valeurs très proches de
     * 0, qui pourraient entrainer des erreurs de précision.
     */
    public static double PRECISION = 0.000000001;
    
    /**
     * Partie réelle du nombre complexe.
     */
    private double partieReelle;
    
    /**
     * Partie imaginaire du nombre complexe.
     */
    private double partieImaginaire;
    
    /**
     * Constructeur par défaut.
     * Les parties réelle et imaginaire sont fixées à 0.
     */
    public Nombre()
    {
        partieReelle = 0;
        partieImaginaire = 0;
    }
    
    /**
     * Constructeur d'initialisation.
     * 
     * @param re La partie réelle du nombre complexe.
     * @param im La partie imaginaire du nombre complexe.
     */
    public Nombre(double re, double im)
    {
        if (Math.abs(re) - PRECISION < 0)
        {
            partieReelle = 0;
        }
        else
        {
            partieReelle = re;
        }
        
        if (Math.abs(im) - PRECISION < 0)
        {
            partieImaginaire = 0;
        }
        else
        {
            partieImaginaire = im;
        }
    }
    
    /**
     * Cette méthode factory permet d'instancier un complexe au moyen de son module et de son argument.
     * 
     * @param module Le module du nombre complexe.
     * @param argument L'argument du nombre complexe.
     * 
     * @return Le nombre complexe généré.
     * 
     * @throws MathException Si le module spécifié est négatif.
     */
    public static Nombre getInstance(double module, double argument) throws MathException
    {
        if (module < 0)
        {
            throw new MathException("module negatif");
        }
        
        return new Nombre(module*Math.cos(argument), module*Math.sin(argument));
    }
    
    /**
     * Cette méthode permet d'obtenir la partie réelle du nombre complexe.
     * 
     * @return La partie réelle du nombre complexe.
     */
    public double partieReelle()
    {
        return partieReelle;
    }
    
    /**
     * Cette méthode permet d'obtenir la partie imaginaire du nombre complexe.
     * 
     * @return La partie imaginaire du nombre complexe.
     */
    public double partieImaginaire()
    {
        return partieImaginaire;
    }
    
    /**
     * Cette méthode permet d'additionner à ce nombre complexe un autre nombre complexe passé en paramètre.
     * 
     * @param nb Le nombre complexe à ajouter à celui-ci.
     * 
     * @return La somme des deux nombres complexes.
     */
    public Nombre somme(Nombre nb)
    {
        return new Nombre(partieReelle + nb.partieReelle(), partieImaginaire + nb.partieImaginaire());
    }
    
    /**
     * Cette méthode permet de soustraire à ce nombre complexe un autre nombre complexe passé en paramètre.
     * 
     * @param nb Le nombre complexe à soustraire à celui-ci.
     * 
     * @return La différence des deux nombres complexes.
     */
    public Nombre difference(Nombre nb)
    {
        return new Nombre(partieReelle - nb.partieReelle(), partieImaginaire - nb.partieImaginaire());
    }
    
    /**
     * Cette méthode permet de multiplier ce nombre complexe par autre nombre complexe passé en paramètre.
     * 
     * @param nb Le nombre complexe par lequel multiplier celui-ci.
     * 
     * @return Le produit des deux nombres complexes.
     */
    public Nombre produit(Nombre nb)
    {
        double re = partieReelle * nb.partieReelle() - partieImaginaire * nb.partieImaginaire();
        double im = partieReelle * nb.partieImaginaire() + partieImaginaire * nb.partieReelle();
        return new Nombre(re, im);
    }
    
    /**
     * Cette méthode permet de diviser ce nombre complexe par autre nombre complexe passé en paramètre.
     * 
     * @param nb Le nombre complexe par lequel diviser celui-ci.
     * 
     * @return Le quotient des deux nombres complexes.
     * 
     * @throws MathException Si le nombre complexe passé en paramètre vaut 0.
     */
    public Nombre quotient(Nombre nb) throws MathException
    {
        if (nb.equals(Nombre.ZERO))
        {
            throw new MathException("division par 0");
        }
        double denom = nb.partieReelle() * nb.partieReelle() + nb.partieImaginaire() * nb.partieImaginaire();
        double re = (partieReelle * nb.partieReelle() + partieImaginaire * nb.partieImaginaire()) / denom;
        double im = (partieImaginaire * nb.partieReelle() - partieReelle * nb.partieImaginaire()) / denom;
        return new Nombre(re, im);
    }
    
    /**
     * Cette méthode permet d'obtenir le module de ce nombre complexe.
     * 
     * @return Le module du nombre complexe.
     */
    public double module()
    {
        double somme = partieReelle * partieReelle + partieImaginaire * partieImaginaire;
        double racine = Math.sqrt(somme);
        return racine;
    }
    
    /**
     * Cette méthode permet d'obtenir l'argument de ce nombre complexe.
     * 
     * @return L'argument du nombre complexe.
     */
    public double argument()
    {
        double arg = Math.atan2(partieImaginaire, partieReelle);
        return arg;
    }
    
    /**
     * Cette méthode permet d'obtenir le complexe conjugué de ce nombre complexe.
     * 
     * @return Le complexe conjugué du nombre complexe.
     */
    public Nombre conjugue()
    {
        return new Nombre(partieReelle, partieImaginaire * (-1));
    }
    
    @Override
    public boolean equals(Object obj)
    {
        boolean ret = false;
        if (obj == null)
        {
            return ret;
        }
        if (obj == this)
        {
            ret = true;
        }
        else if (obj instanceof Nombre)
        {
            Nombre obj2 = (Nombre)obj;
            if (Math.abs(obj2.partieReelle() - partieReelle) < PRECISION && Math.abs(obj2.partieImaginaire() - partieImaginaire) < PRECISION)
            {
                ret = true;
            }
        }
        
        return ret;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.partieReelle) ^ (Double.doubleToLongBits(this.partieReelle) >>> 32));
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.partieImaginaire) ^ (Double.doubleToLongBits(this.partieImaginaire) >>> 32));
        return hash;
    }
    
    /**
     * Cette méthode permet de déterminer si ce nombre complexe est réel ou pas.
     * Un nombre réel est caractérisé par une partie imaginaire nulle.
     * 
     * @return La valeur booléenne spécifiant si le nombre est réel ou non.
     */
    public boolean estReel()
    {
        boolean ret = false;
        if (Math.abs(partieImaginaire) - PRECISION < 0)
        {
            ret = true;
        }
        
        return ret;
    }
    
    /**
     * Cette méthode permet d'obtenir une représentation algébrique de ce nombre complexe sous forme d'une chaine
     * de caractères du type "a+bi".
     * 
     * @return La forme algébrique du nombre complexe.
     */
    public String formeAlgebrique()
    {
        String rep = String.valueOf(partieReelle);
        rep += " + ";
        rep += "(" + String.valueOf(partieImaginaire) + ")i";
        
        return rep;
    }
    
    /**
     * Cette méthode permet d'obtenir l'opposé de ce nombre complexe.
     * 
     * @return L'opposé du nombre complexe.
     */
    public Nombre oppose()
    {
        return new Nombre(-partieReelle, -partieImaginaire);
    }
}
