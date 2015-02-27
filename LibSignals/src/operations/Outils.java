/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package operations;

import exceptions.MathException;
import math.Nombre;

/**
 * Cette classe encapsule quelques méthodes statiques qui réalisent certaines opérations mathématiques utiles.
 * 
 * @author lion
 */
public class Outils
{
    /**
     * Cette méthode calcule la factorielle du nombre naturel passé en paramètre.
     * 
     * <p>
     * Par convention: 0! = 1
     * </p>
     * 
     * @param param Le naturel dont la factorielle doit être calculée.
     * 
     * @return La factorielle du naturel passé en paramètre.
     * 
     * @throws MathException Si le nombre passé en paramètre est négatif.
     */
    public static long factorielle(int param) throws MathException
    {
        if (param < 0)
            throw new MathException("impossible de calculer la factorielle d'un nombre negatif");
        
        long resultat;
        if (param == 0)
            resultat = 1;
        else
        {
            resultat = param;
            for (int i = param-1; i > 0; i--)
                resultat = resultat*i;
        }
        
        return resultat;
    }
    
    /**
     * Cette méthode calcule la distance entre les deux points du plan passés en paramètres.
     * Ces points sont représentés au moyen d'un objet Nombre (nombre complexe) dont les parties réelle et
     * imaginaire représentent les coordonnées des points en question.
     * 
     * @param n1 Le premier point.
     * @param n2 Le deuxième point.
     * 
     * @return La distance entre ces deux points.
     */
    public static double distance(Nombre n1, Nombre n2)
    {
        double x1 = n1.partieReelle();
        double x2 = n2.partieReelle();
        double y1 = n1.partieImaginaire();
        double y2 = n2.partieImaginaire();
        
        return (Math.sqrt(Math.pow(x2-x1, 2) + Math.pow(y2-y1, 2)));
    }
}
