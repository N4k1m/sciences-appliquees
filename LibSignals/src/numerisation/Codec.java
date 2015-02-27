/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package numerisation;

import java.util.BitSet;
import math.Nombre;

/**
 * Cet interface modélise la notion de "codec".
 * Il doit être implémenté par toute classe qui souhaite modéliser un codec particulier.
 * 
 * <p>
 * Une classe "codec" doit être capable de
 * <ul>
 * <li><i>quantifier</i> une valeur (nécessairement réelle), c'est-à-dire remplacer cette valeur par une autre en
 * fonction de l'intervalle de quantification dans lequel la valeur se trouve;</li>
 * <li><i>coder</i> une valeur, c'est-à-dire remplacer cette valeur par un code binaire si la valeur correspond
 * bien à l'une des valeurs de quantification admise par le codec;</li>
 * <li><i>décoder</i> une séquence binaire en retournant la valeur de quantification correspondante;</li>
 * <li>retourner le nombre de bits utilisés pour coder un symbole.</li>
 * </ul>
 * </p>
 * 
 * <p>
 * L'utilisateur est libre d'utiliser les structures de données de son choix pour représenter les tables de
 * quantification et de codage du codec modélisé. La classe {@link numerisation.CodecLineaire} peut éventuellement
 * servir d'exemple.
 * </p>
 * 
 * @see numerisation.CodecLineaire
 * @see math.Nombre
 * @see java.util.BitSet
 * 
 * @author lion
 */
public interface Codec
{
    /**
     * Cette méthode retourne la valeur de quantification correspondant à la valeur passée en paramètre.
     * 
     * @param valeur La valeur à quantifier.
     * 
     * @return La valeur de quantification correspondante.
     */
    public Nombre quantifie(Nombre valeur);
    
    /**
     * Cette méthode retourne le code binaire correspondant à la valeur passée en paramètre.
     * Cette valeur doit nécessairement faire partie des valeurs de quantification admises par le codec.
     * 
     * <p>
     * Note: la classe {@link java.util.BitSet} représente une séquence binaire sur 64 bits au minimum, sauf si la
     * taille de cette séquence est supérieure.
     * </p>
     * 
     * @param valeur La valeur à coder.
     * 
     * @return Le code binaire correspondant (sur 64 bits minimum).
     */
    public BitSet code(Nombre valeur);
    
    /**
     * Cette méthode retourne la valeur de quantification correspondant au code binaire passé en paramètre.
     * La séquence binaire spécifiée doit figurer dans la table de codage du codec en question.
     * 
     * @param bits Le code binaire.
     * 
     * @return La valeur de quantification correspondante.
     */
    public Nombre decode(BitSet bits);
    
    /**
     * Cette méthode retourne le nombre de bits nécessaires pour représenter un symbole avec ce codec.
     * 
     * @return Le nombre de bits par symbole.
     */
    public int getNbBitsParEchantillon();
}
