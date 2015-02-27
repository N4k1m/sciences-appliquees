/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

/**
 * Cet interface doit être implémenté par toute classe qui souhaite modéliser une fonction mathématique.
 * Une classe qui implémente cet interface peut servir lors de l'instanciation d'un objet de type
 * {@link signaux.SignalAnalogique} pour permettre le "zoom infini" sur ce signal.
 * Cet interface permet ainsi de générer le signal analogique correspondant à la fonction mathématique souhaitée.
 * On peut alors appliquer tous les outils mis à disposition dans cette librairie à cette fonction mathématique.
 * 
 * @author lion
 */
public interface Fonction
{
    /**
     * Retourne la valeur de la fonction correspondant à l'indice fourni en paramètre.
     * 
     * @param param Valeur de l'indice souhaité.
     * @return La valeur (éventuellement complexe) correspondante.
     */
    public Nombre getValeur(double param);
}
