package GUI;

import exceptions.ComplexSignalException;
import exceptions.ExistingItemException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
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
 * @author Nakim
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

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    /**
     * Constructeur par défaut.
     * Le graphique ne comportera pas de titre ni de label sur ses axes.
     */
    public SignalPanel()
    {
        // Gui configuration
        initComponents();

        signaux = new HashMap();
        courbes = new XYSeriesCollection();

        graphique = ChartFactory.createXYLineChart(titre, labelX, labelY, null, PlotOrientation.VERTICAL, true, true, true);
        graphique.setBackgroundPaint(null);

        panelGraphique = new ChartPanel(graphique);
        panelGraphique.setPreferredSize(new java.awt.Dimension(400, 250));
        this.add(panelGraphique, BorderLayout.CENTER);

        radioX.setSelected(true);
        panelGraphique.setRangeZoomable(false);

        nuage = false;

        echelleX = graphique.getXYPlot().getRangeAxis().getRange();
        echelleY = graphique.getXYPlot().getDomainAxis().getRange();
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
        // Gui configuration
        initComponents();

        signaux = new HashMap();
        courbes = new XYSeriesCollection();
        this.titre = titre;
        this.labelX = labelX;
        this.labelY = labelY;

        graphique = ChartFactory.createXYLineChart(titre, labelX, labelY, null, PlotOrientation.VERTICAL, true, true, true);
        graphique.setBackgroundPaint(null);

        panelGraphique = new ChartPanel(graphique);
        panelGraphique.setPreferredSize(new java.awt.Dimension(400, 250));
        this.add(panelGraphique, BorderLayout.CENTER);

        radioX.setSelected(true);
        panelGraphique.setRangeZoomable(false);

        nuage = false;

        echelleX = graphique.getXYPlot().getRangeAxis().getRange();
        echelleY = graphique.getXYPlot().getDomainAxis().getRange();
    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {
        java.awt.GridBagConstraints gridBagConstraints;

        groupeZoom = new javax.swing.ButtonGroup();
        panelFooter = new javax.swing.JPanel();
        labelZoom = new javax.swing.JLabel();
        radioXY = new javax.swing.JRadioButton();
        radioX = new javax.swing.JRadioButton();
        boutonReset = new javax.swing.JButton();
        labelCouleur = new javax.swing.JLabel();
        comboSignaux = new javax.swing.JComboBox();
        boutonCouleur = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 3, 3, 3));
        setLayout(new java.awt.BorderLayout());

        panelFooter.setLayout(new java.awt.GridBagLayout());

        labelZoom.setText("Zoom:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelFooter.add(labelZoom, gridBagConstraints);

        groupeZoom.add(radioXY);
        radioXY.setText("X-Y");
        radioXY.addChangeListener(new javax.swing.event.ChangeListener()
        {
            public void stateChanged(javax.swing.event.ChangeEvent evt)
            {
                radioXYStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelFooter.add(radioXY, gridBagConstraints);

        groupeZoom.add(radioX);
        radioX.setText("X");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelFooter.add(radioX, gridBagConstraints);

        boutonReset.setText("Reset");
        boutonReset.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                boutonResetActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelFooter.add(boutonReset, gridBagConstraints);

        labelCouleur.setText("Couleur:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelFooter.add(labelCouleur, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelFooter.add(comboSignaux, gridBagConstraints);

        boutonCouleur.setText("Changer");
        boutonCouleur.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                boutonCouleurActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelFooter.add(boutonCouleur, gridBagConstraints);

        add(panelFooter, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents

    //<editor-fold defaultstate="collapsed" desc="Events handlers">
    private void radioXYStateChanged(javax.swing.event.ChangeEvent evt)//GEN-FIRST:event_radioXYStateChanged
    {//GEN-HEADEREND:event_radioXYStateChanged
        if (((JRadioButton)evt.getSource()).isSelected())
        {
            panelGraphique.setRangeZoomable(true);
        }
        else
        {
            panelGraphique.setRangeZoomable(false);
        }
    }//GEN-LAST:event_radioXYStateChanged

    private void boutonResetActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_boutonResetActionPerformed
    {//GEN-HEADEREND:event_boutonResetActionPerformed
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

    private void boutonCouleurActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_boutonCouleurActionPerformed
    {//GEN-HEADEREND:event_boutonCouleurActionPerformed
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
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Public methods">
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
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Variables declaration">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton boutonCouleur;
    private javax.swing.JButton boutonReset;
    private javax.swing.JComboBox comboSignaux;
    private javax.swing.ButtonGroup groupeZoom;
    private javax.swing.JLabel labelCouleur;
    private javax.swing.JLabel labelZoom;
    private javax.swing.JPanel panelFooter;
    private javax.swing.JRadioButton radioX;
    private javax.swing.JRadioButton radioXY;
    // End of variables declaration//GEN-END:variables
    //</editor-fold>
}
