/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

/**
 * Cet interface représente une loi de probabilité quelconque.
 * Il doit être implémenté par toute classe qui souhaite modéliser une loi de probabilité particulière.
 * 
 * Une loi de probabilité décrit le comportement aléatoire d'un phénomène dépendant du hasard. Dans le cadre de
 * cette librairie, une telle loi définit une distribution statistique qui fournit la fréquence d'apparition d'un
 * nombre aléatoire généré au moyen de cette loi.
 * 
 * Par exemple, une loi uniforme définit une probabilité d'apparition égale pour chaque élément d'un ensemble de
 * nombres. Une loi normale (ou gaussienne) est par contre caractérisée par des fréquences d'apparition
 * différentes pour les éléments de l'ensemble.
 * 
 * @see signaux.SignalAnalogique#getInstance(int, math.Nombre, signaux.Discretiseur)
 * @see signaux.SignalNumerique#getInstance(int, math.LoiDeProbabilite, signaux.Discretiseur)
 * 
 * @author lion
 */
public interface LoiDeProbabilite
{
    /**
     * Cette méthode renvoie un nombre aléatoire généré au moyen de la loi de probabilité correspondante.
     * La probabilité d'apparition des nombres aléatoires dépend de la loi utilisée et des caractéristiques de
     * celle-ci.
     * 
     * @return Le nombre aléatoire généré.
     */
    public double getRealisation();
}
