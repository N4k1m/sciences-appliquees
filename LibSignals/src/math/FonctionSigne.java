/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

/**
 * Cette classe modélise la fonction "signe".
 * La fonction signe est une fonction mathématique qui extrait le signe d'un nombre réel, c'est-à-dire que l'image
 * d'un nombre par cette application est 1 si le nombre est strictement positif, 0 si le nombre est nul, et -1 si
 * le nombre est strictement négatif.
 * 
 * @author lion
 */
public class FonctionSigne implements Fonction
{
    @Override
    public Nombre getValeur(double param)
    {
        Nombre valeur;
        if (param < 0)
        {
            valeur = new Nombre(-1, 0);
        }
        else if (param > 0)
        {
            valeur = Nombre.UN;
        }
        else
        {
            valeur = Nombre.ZERO;
        }
        
        return valeur;
    }
}
