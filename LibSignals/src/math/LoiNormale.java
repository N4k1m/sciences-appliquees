/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

/**
 * Cette classe modélise une loi de probabilité "normale" ou "gaussienne".
 * Cette loi normale est caractérisée par une moyenne et un écart type. La courbe correspondant à une telle loi
 * prend la forme d'une cloche, également appelée "courbe de Gauss".
 * 
 * L'utilisateur peut instancier une loi normale en spécifiant la valeur de la moyenne et de l'écart type.
 * 
 * @author lion
 */
public class LoiNormale implements LoiDeProbabilite
{
    /**
     * Moyenne de la loi normale.
     */
    private double moyenne;
    
    /**
     * Ecart type de la loi normale.
     */
    private double ecartType;
    
    /**
     * Constructeur par défaut.
     * La moyenne est fixée à 0, l'écart type à 1.
     */
    public LoiNormale()
    {
        moyenne = 0.0;
        ecartType = 1.0;
    }
    
    /**
     * Constructeur d'initialisation.
     * 
     * @param moyenne La moyenne de la loi normale.
     * @param ecartType L'écart type de la loi normale.
     */
    public LoiNormale(double moyenne, double ecartType)
    {
        this.moyenne = moyenne;
        this.ecartType = ecartType;
    }

    @Override
    public double getRealisation()
    {
        // methode Kuty
        double theta = 2 * Math.PI * Math.random();
        double rho = Math.sqrt(-2 * Math.log(1 - Math.random()));
        double scale = ecartType * rho;
        double x = moyenne + scale * Math.cos(theta);
        //double y = moyenne + scale * Math.sin(theta);
        
        return x; // et y???
    }
}
