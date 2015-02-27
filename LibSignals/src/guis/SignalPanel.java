/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guis;

import exceptions.ComplexSignalException;
import exceptions.ExistingItemException;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JColorChooser;
import javax.swing.JRadioButton;
import math.Nombre;
import modulation.Constellation;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.Range;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import signaux.Discretiseur;
import signaux.Signal;
import signaux.SignalNumerique;

/**
 * Panel permettant l'affichage des signaux générés à l'aide de la librairie.
 * Ce panel propose aussi des contrôles permettant de zoomer sur le graphique (selon les deux axes ou selon l'axe
 * horizontal uniquement) et de changer la couleur des signaux présents sur le graphique.
 * Il est également possible d'afficher les diagrammes de constellation créés au moyen d'une classe qui hérite de
 * la classe {@link modulation.Constellation}.
 * 
 * @author lion
 */
public class SignalPanel extends javax.swing.JPanel
{
    /**
     * Titre du graphique.
     */
    private String titre;
    
    /**
     * Label de l'axe horizontal.
     */
    private String labelX;
    
    /**
     * Label de l'axe vertical.
     */
    private String labelY;
    
    /**
     * Table de hachage contenant les signaux présents sur le graphique.
     */
    private HashMap signaux;
    
    /**
     * Variable contenant les différents ensemble de points correspondant aux signaux affichés.
     */
    private XYSeriesCollection courbes;
    
    /**
     * Le graphique proprement dit (avec ses axes).
     */
    private JFreeChart graphique;
    
    /**
     * Le panel contenant le graphique, ses légendes et son titre.
     */
    private ChartPanel panelGraphique;
    
    /**
     * Variable spécifiant si le graphique affiche des nuages de points (diagrammes de constellation) ou des
     * signaux.
     */
    private boolean nuage;
    
    /**
     * Variable servant à mémoriser l'intervalle de valeur affiché sur l'axe horizontal.
     * Cette variable est utile pour le zoom sur un diagramme de constellation.
     */
    private Range echelleX;
    
    /**
     * Variable servant à mémoriser l'intervalle de valeur affiché sur l'axe vertical.
     * Cette variable est utile pour le zoom sur un diagramme de constellation.
     */
    private Range echelleY;
    
    /**
     * Constructeur par défaut.
     * Le graphique ne comportera pas de titre ni de label sur ses axes.
     */
    public SignalPanel()
    {
        initComponents();
        
        signaux = new HashMap();
        courbes = new XYSeriesCollection();
        
        graphique = ChartFactory.createXYLineChart(titre, labelX, labelY, null, PlotOrientation.VERTICAL, true, true, true);
        
        panelGraphique = new ChartPanel(graphique);
        panelGraphique.setPreferredSize(new java.awt.Dimension(400, 250));
        panelSignal.setLayout(new FlowLayout());
        panelSignal.add(panelGraphique);
        
        radioX.setSelected(true);
        panelGraphique.setRangeZoomable(false);
        
        nuage = false;
        
        echelleX = graphique.getXYPlot().getRangeAxis().getRange();
        echelleY = graphique.getXYPlot().getDomainAxis().getRange();
        
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setPreferredSize(null); // ajuste la taille pour contenir tous les composants
    }
    
    /**
     * Constructeur d'initialisation.
     * 
     * @param titre Le titre du graphique.
     * @param labelX Le label de l'axe horizontal.
     * @param labelY Le label de l'axe vertical.
     */
    public SignalPanel(String titre, String labelX, String labelY)
    {
        initComponents();
        
        signaux = new HashMap();
        courbes = new XYSeriesCollection();
        this.titre = titre;
        this.labelX = labelX;
        this.labelY = labelY;
        
        graphique = ChartFactory.createXYLineChart(titre, labelX, labelY, null, PlotOrientation.VERTICAL, true, true, true);
        
        panelGraphique = new ChartPanel(graphique);
        panelGraphique.setPreferredSize(new java.awt.Dimension(400, 250));
        panelSignal.setLayout(new FlowLayout());
        panelSignal.add(panelGraphique);
        
        radioX.setSelected(true);
        panelGraphique.setRangeZoomable(false);
        
        nuage = false;
        
        echelleX = graphique.getXYPlot().getRangeAxis().getRange();
        echelleY = graphique.getXYPlot().getDomainAxis().getRange();
        
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setPreferredSize(null); // ajuste la taille pour contenir tous les composants
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        groupeZoom = new javax.swing.ButtonGroup();
        radioXY = new javax.swing.JRadioButton();
        radioX = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        panelSignal = new javax.swing.JPanel();
        boutonReset = new javax.swing.JButton();
        boutonCouleur = new javax.swing.JButton();
        comboSignaux = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();

        groupeZoom.add(radioXY);
        radioXY.setText("X-Y");
        radioXY.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                radioXYStateChanged(evt);
            }
        });

        groupeZoom.add(radioX);
        radioX.setText("X");

        jLabel1.setText("Zoom:");

        org.jdesktop.layout.GroupLayout panelSignalLayout = new org.jdesktop.layout.GroupLayout(panelSignal);
        panelSignal.setLayout(panelSignalLayout);
        panelSignalLayout.setHorizontalGroup(
            panelSignalLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 0, Short.MAX_VALUE)
        );
        panelSignalLayout.setVerticalGroup(
            panelSignalLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 258, Short.MAX_VALUE)
        );

        boutonReset.setText("Reset");
        boutonReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boutonResetActionPerformed(evt);
            }
        });

        boutonCouleur.setText("Changer");
        boutonCouleur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boutonCouleurActionPerformed(evt);
            }
        });

        jLabel2.setText("Couleur:");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(panelSignal, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .add(layout.createSequentialGroup()
                        .add(jLabel1)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(radioXY)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(radioX)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(boutonReset)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(jLabel2)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(comboSignaux, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(boutonCouleur))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(panelSignal, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(radioXY)
                    .add(radioX)
                    .add(jLabel1)
                    .add(boutonReset)
                    .add(boutonCouleur)
                    .add(comboSignaux, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel2))
                .add(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void radioXYStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_radioXYStateChanged
        if (((JRadioButton)evt.getSource()).isSelected())
        {
            panelGraphique.setRangeZoomable(true);
        }
        else
        {
            panelGraphique.setRangeZoomable(false);
        }
    }//GEN-LAST:event_radioXYStateChanged

    private void boutonResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boutonResetActionPerformed
        if (!nuage)
            panelGraphique.restoreAutoBounds();
        else
        {
            XYPlot plot = graphique.getXYPlot();
            NumberAxis axeY = (NumberAxis)plot.getRangeAxis();
            NumberAxis axeX = (NumberAxis)plot.getDomainAxis();
            axeX.setRange(echelleX);
            axeY.setRange(echelleY);
        }
    }//GEN-LAST:event_boutonResetActionPerformed

    private void boutonCouleurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boutonCouleurActionPerformed
        Color couleur = JColorChooser.showDialog(this, "Nouvelle couleur du signal", null);
        if (couleur != null)
        {
            int cle = comboSignaux.getSelectedIndex();
            if (cle != -1)
            {
                XYPlot plot = (XYPlot)graphique.getPlot();
                plot.getRenderer().setSeriesPaint(cle, couleur);
            }
        }
    }//GEN-LAST:event_boutonCouleurActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton boutonCouleur;
    private javax.swing.JButton boutonReset;
    private javax.swing.JComboBox comboSignaux;
    private javax.swing.ButtonGroup groupeZoom;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel panelSignal;
    private javax.swing.JRadioButton radioX;
    private javax.swing.JRadioButton radioXY;
    // End of variables declaration//GEN-END:variables

    /**
     * Permet d'ajouter un signal au graphique.
     * 
     * @param signal Le signal à afficher.
     * @param nom Le nom du signal tel qu'il apparaîtra dans la légende du graphique.
     * @param clear Permet d'effacer les signaux éventuellement présents sur le graphique.
     * 
     * @throws ComplexSignalException Si le signal à afficher est à valeurs complexes.
     * @throws ExistingItemException Si le nom spécifié est déjà utilisé sur le graphique.
     */
    public void addSignal(Signal signal, String nom, boolean clear) throws ComplexSignalException, ExistingItemException
    {
        if (clear)
        {
            clear();
        }
        if (!signal.estReel())
        {
            Nombre[] valeurs = signal.getValeurs();
            int i = 0;
            while (valeurs[i].estReel())
                i++;
            throw new ComplexSignalException("impossible d'afficher un signal complexe", nom, i, valeurs[i]);
        }
        if (nom == null)
        {
            nom = "signal" + signaux.size();
        }
        else if (signaux.containsKey(nom))
        {
            throw new ExistingItemException("nom de signal deja utilise");
        }
        
        signaux.put(nom, signal);
        Nombre[] valeurs = signal.getValeurs();
        Discretiseur discr = signal.getDiscretiseur();
        
        XYSeries serie = new XYSeries(nom);
        
        for (int j = 0; j < valeurs.length; j++)
        {
            serie.add(discr.getOrigine() + j*discr.pas(), valeurs[j].partieReelle());
        }
        
        courbes.addSeries(serie);
        XYPlot plot = graphique.getXYPlot();
        plot.setDataset(courbes);
        if (signal instanceof SignalNumerique)
        {
            XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer)plot.getRenderer();
            renderer.setSeriesLinesVisible(courbes.getSeriesCount()-1, false);
            renderer.setSeriesShapesVisible(courbes.getSeriesCount()-1, true);
        }
        panelGraphique.restoreAutoBounds();
        
        nuage = false;
        
        comboSignaux.addItem(nom);
    }
    
    /**
     * Permet d'ajouter un diagramme de constellation au graphique.
     * 
     * @param nuage Le nuage de points à afficher.
     * @param nom Le nom du nuage de points tel qu'il apparaîtra dans la légende du graphique.
     * @param clear Permet d'effacer les signaux éventuellement présents sur le graphique.
     * 
     * @throws ExistingItemException Si le nom spécifié est déjà utilisé sur le graphique.
     */
    public void addNuage(Constellation nuage, String nom, boolean clear) throws ExistingItemException
    {
        if (clear)
        {
            clear();
        }
        if (nom == null)
        {
            nom = "nuage" + signaux.size();
        }
        else if (signaux.containsKey(nom))
        {
            throw new ExistingItemException("nom deja utilise");
        }
        
        signaux.put(nom, nuage);
        XYSeries serie = new XYSeries(nom);
        
        double x, y;
        Iterator it = nuage.getNuage().entrySet().iterator();
        Map.Entry paire;
        while (it.hasNext())
        {
            paire = (Map.Entry)it.next();
            x = ((Nombre)paire.getValue()).partieReelle();
            y = ((Nombre)paire.getValue()).partieImaginaire();
            serie.add(x, y);
        }
        
        courbes.addSeries(serie);
        
        XYPlot plot = graphique.getXYPlot();
        plot.setDataset(courbes);
        XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer)plot.getRenderer();
        renderer.setSeriesLinesVisible(courbes.getSeriesCount()-1, false);
        renderer.setSeriesShapesVisible(courbes.getSeriesCount()-1, true);
        NumberAxis axeY = (NumberAxis)plot.getRangeAxis();
        NumberAxis axeX = (NumberAxis)plot.getDomainAxis();
        axeY.setAutoRange(false);
        axeY.setRange(axeX.getRange());
        echelleX = axeX.getRange();
        echelleY = axeY.getRange();
        
        comboSignaux.addItem(nom);
        
        this.nuage = true;
    }
    
    /**
     * Permet d'effacer les signaux présents sur le graphique.
     */
    public void clear()
    {
        signaux.clear();
        courbes.removeAllSeries();
        graphique.getXYPlot().setDataset(courbes);
        comboSignaux.removeAllItems();
        nuage = false;
    }
}
