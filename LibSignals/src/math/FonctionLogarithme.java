/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

import exceptions.MathException;

/**
 * Cette classe modélise une fonction logarithme.
 * Cette classe permet d'instancier plusieurs fonctions logarithmes différentes en fonction de la base spécifiée.
 * La base d'un logarithme doit cependant être strictement positive et différente de 1.
 * 
 * Par exemple, pour instancier un logarithme népérien (ou naturel), il suffit de passer au constructeur la
 * variable statique {@link java.lang.Math#E} définie dans le JDK.
 * 
 * @author lion
 */
public class FonctionLogarithme implements Fonction
{
    // CE:
    // 1. base dans ]0, 1[ U ]1, +inf[
    // 2. arg dans ]0, +inf[
    
    /**
     * Base du logarithme.
     */
    private double base;
    
    /**
     * Constructeur par défaut.
     * La base est fixée à 10.
     */
    public FonctionLogarithme()
    {
        base = 10;
    }
    
    /**
     * Constructeur d'initialisation.
     * 
     * @param base La base du logarithme.
     * 
     * @throws MathException Si la base spécifiée est négative, nulle ou égale à 1.
     */
    public FonctionLogarithme(double base) throws MathException
    {
        if (base <= 0 || base == 1)
        {
            throw new MathException("base du log incorrecte");
        }
        
        this.base = base;
    }
    
    @Override
    public Nombre getValeur(double param) throws MathException
    {
        if (param <= 0)
        {
            throw new MathException("valeur hors domaine");
        }
        
        if (base == 10)
        {
            return new Nombre(Math.log10(param), 0);
        }
        else if (base == Math.E)
        {
            return new Nombre(Math.log(param), 0);
        }
        else
        {
            double quotient = Math.log(param)/Math.log(base);
            return new Nombre(quotient, 0);
        }
    }
}
