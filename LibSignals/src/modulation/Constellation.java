/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modulation;

import exceptions.BadSymbolException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import math.Nombre;
import operations.Outils;

/**
 * Cette classe abstraite modélise la notion de "diagramme de constellation" dans le cadre des techniques de
 * modulation numérique.
 * Plus généralement, cette classe permet de représenter un "nuage de points" quelconque qui pourra être affiché
 * sur un graphique au moyen de la classe {@link guis.SignalPanel}.
 * 
 * <p>
 * Pour modéliser une constellation quelconque, il suffit de créer une classe qui hérite de celle-ci et de
 * définir, dans le constructeur de la nouvelle classe, les associations entre les symboles représentés et les
 * points du diagramme. Ces associations sont représentées au moyen d'une table de hachage (variable
 * {@link #nuage}) qu'il suffit d'initialiser. Il faut aussi initialiser la variable {@link #bits} destinée à
 * contenir le nombre de bits nécessaires pour représenter un symbole de la constellation.
 * </p>
 * 
 * <p>
 * Dans le cas d'un nuage de points quelconque (pas d'association entre les points du diagramme et des symboles),
 * il est inutile de définir les "clés" de la table de hachage. Seuls ses "valeurs" doivent être initialisées.
 * </p>
 * 
 * <p>
 * Les coordonnées d'un point sur le diagramme sont représentées par un objet Nombre, autrement dit un complexe,
 * dont les parties réelle et imaginaire désignent les coordonnées du point en question. Un symbole doit
 * nécessairement être un objet de type String.
 * </p>
 * 
 * Exemple (QPSK):<br><br>
 * 
 * <pre>
 * {@code
 * public QPSK()
 * {
 *      nuage = new HashMap();
 *      nuage.put("00", new Nombre(1, 0));
 *      nuage.put("01", new Nombre(0, 1));
 *      nuage.put("10", new Nombre(-1, 0));
 *      nuage.put("11", new Nombre(0, -1));
 *      bits = 2;
 * }
 * }
 * </pre>
 * 
 * @see guis.SignalPanel
 * @see math.Nombre
 * 
 * @author lion
 */
public abstract class Constellation
{
    /**
     * Table de hachage contenant les associations "points-symboles".
     */
    protected HashMap nuage;
    
    /**
     * Nombre de bits nécessaires pour coder un symbole dans cette constellation.
     */
    protected int bits;
    
    /**
     * Cette méthode retourne le nombre de bits nécessaires pour coder un symbole dans cette constellation.
     * 
     * @return Le nombre de bits par symbole.
     */
    public int getNbBits()
    {
        return bits;
    }
    
    /**
     * Cette méthode retourne le nuage de points.
     * 
     * @return Le nuage de points.
     */
    public HashMap getNuage()
    {
        return nuage;
    }
    
    /**
     * Cette méthode retourne le symbole correspondant aux coordonnées du point passé en paramètre.
     * Pour ce faire, la méthode calcule la distance du point fourni à tous les points de la constellation et
     * recherche la distance minimum parmi celles-ci. Le symbole retourné correspond donc au point le plus proche
     * du point passé en paramètre.
     * 
     * @param valeur Les coordonnées du point à replacer sur le diagramme.
     * 
     * @return Le symbole correspondant.
     */
    public String getSymbole(Nombre valeur)
    {
        double min = 0, dist;
        String symbole = null;
        
        Map.Entry paire;
        Iterator it = nuage.entrySet().iterator();
        if (it.hasNext())
        {
            paire = (Map.Entry)it.next();
            min = Outils.distance((Nombre)paire.getValue(), valeur);
            symbole = (String)paire.getKey();
        }
        
        while (it.hasNext())
        {
            paire = (Map.Entry)it.next();
            dist = Outils.distance((Nombre)paire.getValue(), valeur);
            if (dist < min)
            {
                min = dist;
                symbole = (String)paire.getKey();
            }
        }
        
        return symbole;
    }
    
    /**
     * Cette méthode retourne la distance entre le point de la constellation correspondant au symbole fourni et
     * l'origine des axes.
     * Elle est utile, en modulation numérique, pour calculer l'amplitude des impulsions du signal modulé.
     * 
     * @param symbole Le symbole en question.
     * 
     * @return La distance entre le point correspondant et l'origine des axes.
     * 
     * @throws BadSymbolException Si le symbole n'est pas présent dans cette constellation.
     */
    public double getAlpha(String symbole) throws BadSymbolException
    {
        if (!nuage.containsKey(symbole))
        {
            throw new BadSymbolException("symbole non present dans le diagramme de constellation", symbole);
        }
        
        return ((Nombre)nuage.get(symbole)).module();
    }
    
    /**
     * Cette méthode retourne l'angle entre, d'une part, la droite joignant le point de la constellation
     * correspondant au symbole fourni à l'origine des axes et, d'autre part, l'axe horizontal.
     * Elle est utile, en modulation numérique, pour calculer l'amplitude des impulsions du signal modulé.
     * 
     * @param symbole Le symbole en question.
     * 
     * @return L'angle recherché.
     * 
     * @throws BadSymbolException Si le symbole n'est pas présent dans cette constellation.
     */
    public double getTheta(String symbole) throws BadSymbolException
    {
        if (!nuage.containsKey(symbole))
        {
            throw new BadSymbolException("symbole non present dans le diagramme de constellation");
        }
        
        return ((Nombre)nuage.get(symbole)).argument();
    }
    
    /**
     * Cette méthode permet d'initialiser la variable {@link #nuage} avec un nouveau nuage de points (objet de
     * type {@link java.util.HashMap}).
     * 
     * @param nuage Le nouveau nuage de points.
     */
    public void setNuage(HashMap nuage)
    {
        this.nuage = nuage;
    }
    
    /*public void setAmplitude(double A)
    {
        HashMap newNuage = new HashMap();
        Map.Entry paire;
        Iterator it = nuage.entrySet().iterator();
        double rho, alpha;
        while (it.hasNext())
        {
            paire = (Map.Entry)it.next();
            rho = ((Nombre)paire.getValue()).module() * A;
            alpha = ((Nombre)paire.getValue()).argument();
            newNuage.put((String)paire.getKey(), Nombre.getInstance(rho, alpha));
        }
        
        this.nuage = newNuage;
    }*/
}
