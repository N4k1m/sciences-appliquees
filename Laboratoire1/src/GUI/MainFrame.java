package GUI;

import Filtrage.FiltrageLineaire;
import Utils.MessageBoxes;
import java.awt.GridBagConstraints;
import math.Nombre;
import operations.Convolution;
import operations.Fourier;
import operations.SignalOperations;
import signaux.Discretiseur;
import signaux.ImpulsionDirac;
import signaux.ImpulsionRectangulaire;
import signaux.Signal;
import signaux.SignalAnalogique;
import signaux.SignalPeriodique;
import signaux.SinusCardinal;

/**
 *
 * @author Nakim
 */
public class MainFrame extends javax.swing.JFrame
{
    // Tab Signals
    private final SignalPanel plotSignal;
    private final SignalPanel plotSpectre;
    private final SignalPanel plotPhase;

    // Tab Sinc
    private final SignalPanel plotSincTemp;
    private final SignalPanel plotSincFreq;

    // Tab Convolution
    private final SignalPanel plotConvolutionTemp;
    private final SignalPanel plotConvolutionFreq;

    // Tab Filtrage
    private final SignalPanel plotFiltragePasseBasTemp;
    private final SignalPanel plotFiltragePasseBasFreq;
    private final SignalPanel plotFiltragePasseHautTemp;
    private final SignalPanel plotFiltragePasseHautFreq;
    private final SignalPanel plotFiltragePasseBandeTemp;
    private final SignalPanel plotFiltragePasseBandeFreq;

    private int signalCount;
    private Signal lastSignal;
    private String lastSignalName;
    private Signal lastSinc;

    //<editor-fold defaultstate="collapsed" desc="Constructor">
    public MainFrame()
    {
        // GUI Configuration
        initComponents();
        this.updatePanelSignal();

        // Tab Signals
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH; // Maximum width and height
        gbc.weightx = 1;
        gbc.weighty = 1;

        this.plotSignal = new SignalPanel("Signaux", null, null);
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.panelPlotsSignals.add(this.plotSignal, gbc);

        this.plotSpectre = new SignalPanel("Spectres", null, null);
        gbc.gridx = 1;
        gbc.gridy = 0;
        this.panelPlotsSignals.add(this.plotSpectre, gbc);

        this.plotPhase = new SignalPanel("Phases", null, null);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 2;
        this.panelPlotsSignals.add(this.plotPhase, gbc);

        // Tab Sinc & convolution
        this.plotSincTemp = new SignalPanel("Sinc : Domaine temporel", null, null);
        this.panelPlotsSincConv.add(this.plotSincTemp);
        this.plotSincFreq = new SignalPanel("Sinc : Domaine fréquentiel (spectre)", null, null);
        this.panelPlotsSincConv.add(this.plotSincFreq);
        this.plotConvolutionTemp = new SignalPanel("Convolution : Domaine temporel", null, null);
        this.panelPlotsSincConv.add(this.plotConvolutionTemp);
        this.plotConvolutionFreq = new SignalPanel("Convolution : Domaine fréquentiel (spectre)", null, null);
        this.panelPlotsSincConv.add(this.plotConvolutionFreq);

        // Tab Filtrage
        this.plotFiltragePasseBasTemp = new SignalPanel("Passe-bas : Domaine temporel", null, null);
        this.panelPlotsFiltrage.add(this.plotFiltragePasseBasTemp);
        this.plotFiltragePasseHautTemp = new SignalPanel("Passe-haut : Domaine temporel", null, null);
        this.panelPlotsFiltrage.add(this.plotFiltragePasseHautTemp);
        this.plotFiltragePasseBandeTemp = new SignalPanel("Passe-bande : Domaine temporel", null, null);
        this.panelPlotsFiltrage.add(this.plotFiltragePasseBandeTemp);
        this.plotFiltragePasseBasFreq = new SignalPanel("Passe-bas : Domaine fréquentiel", null, null);
        this.panelPlotsFiltrage.add(this.plotFiltragePasseBasFreq);
        this.plotFiltragePasseHautFreq = new SignalPanel("Passe-haut : Domaine fréquentiel", null, null);
        this.panelPlotsFiltrage.add(this.plotFiltragePasseHautFreq);
        this.plotFiltragePasseBandeFreq = new SignalPanel("Passe-bande : Domaine fréquentiel", null, null);
        this.panelPlotsFiltrage.add(this.plotFiltragePasseBandeFreq);

        this.pack();

        this.signalCount = 0;
        this.lastSignal = null;
        this.lastSignalName = "";
        this.lastSinc = null;
    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {
        java.awt.GridBagConstraints gridBagConstraints;

        splitPaneSignals = new javax.swing.JSplitPane();
        tabbedPanePlots = new javax.swing.JTabbedPane();
        panelPlotsSignals = new javax.swing.JPanel();
        panelPlotsSincConv = new javax.swing.JPanel();
        panelPlotsFiltrage = new javax.swing.JPanel();
        panelOptions = new javax.swing.JPanel();
        buttonClearAll = new javax.swing.JButton();
        panelSinc = new javax.swing.JPanel();
        labelAmplitudeSinc = new javax.swing.JLabel();
        spinnerAmplitudeSinc = new javax.swing.JSpinner();
        labelDureeSinc = new javax.swing.JLabel();
        spinnerDureeSinc = new javax.swing.JSpinner();
        labelOffsetSinc = new javax.swing.JLabel();
        spinnerOffsetSinc = new javax.swing.JSpinner();
        buttonGenerateSinc = new javax.swing.JButton();
        panelSignal = new javax.swing.JPanel();
        labelTypeSig = new javax.swing.JLabel();
        comboBoxSig = new javax.swing.JComboBox();
        labelNomSig = new javax.swing.JLabel();
        textFieldNomSig = new javax.swing.JTextField();
        labelAmplitudeSig = new javax.swing.JLabel();
        spinnerAmplitudeSig = new javax.swing.JSpinner();
        labelFrequenceSig = new javax.swing.JLabel();
        spinnerFrequenceSig = new javax.swing.JSpinner();
        labelOffsetSig = new javax.swing.JLabel();
        spinnerOffsetSig = new javax.swing.JSpinner();
        labelDureeSig = new javax.swing.JLabel();
        spinnerDureeSig = new javax.swing.JSpinner();
        labelByteSig = new javax.swing.JLabel();
        textFieldByteSig = new javax.swing.JTextField();
        checkBoxComposanteContinueSig = new javax.swing.JCheckBox();
        spinnerComposanteContinueSig = new javax.swing.JSpinner();
        buttonGenerateSignal = new javax.swing.JButton();
        panelDiscretiseur = new javax.swing.JPanel();
        labelSamplesDisc = new javax.swing.JLabel();
        spinnerSamplesDisc = new javax.swing.JSpinner();
        labelOrigineDisc = new javax.swing.JLabel();
        spinnerOrigineDisc = new javax.swing.JSpinner();
        labelDureeDisc = new javax.swing.JLabel();
        spinnerDureeDisc = new javax.swing.JSpinner();
        panelConvolution = new javax.swing.JPanel();
        checkBoxMakeConvolution = new javax.swing.JCheckBox();
        panelFiltrage = new javax.swing.JPanel();
        checkBoxPasseBas = new javax.swing.JCheckBox();
        labelFrequencePasseBas = new javax.swing.JLabel();
        spinnerFrequencePasseBas = new javax.swing.JSpinner();
        checkBoxPasseHaut = new javax.swing.JCheckBox();
        labelFrequencePasseHaut = new javax.swing.JLabel();
        spinnerFrequencePasseHaut = new javax.swing.JSpinner();
        buttonFilterLastSignal = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sciences Appliquées - Laboratoire 1");

        splitPaneSignals.setOneTouchExpandable(true);

        tabbedPanePlots.addChangeListener(new javax.swing.event.ChangeListener()
        {
            public void stateChanged(javax.swing.event.ChangeEvent evt)
            {
                tabbedPanePlotsStateChanged(evt);
            }
        });

        panelPlotsSignals.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 3, 3, 3));
        panelPlotsSignals.setLayout(new java.awt.GridBagLayout());
        tabbedPanePlots.addTab("Signaux", panelPlotsSignals);

        panelPlotsSincConv.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 3, 3, 3));
        panelPlotsSincConv.setLayout(new java.awt.GridLayout(2, 2));
        tabbedPanePlots.addTab("Sinc et convolution", panelPlotsSincConv);

        panelPlotsFiltrage.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 3, 3, 3));
        panelPlotsFiltrage.setLayout(new java.awt.GridLayout(2, 3));
        tabbedPanePlots.addTab("Filtrage", panelPlotsFiltrage);

        splitPaneSignals.setRightComponent(tabbedPanePlots);

        panelOptions.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 3, 3, 3));
        java.awt.GridBagLayout panelSignalOptionsLayout = new java.awt.GridBagLayout();
        panelSignalOptionsLayout.columnWidths = new int[] {0, 5, 0};
        panelSignalOptionsLayout.rowHeights = new int[] {0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0};
        panelOptions.setLayout(panelSignalOptionsLayout);

        buttonClearAll.setText("Tout effacer");
        buttonClearAll.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                buttonClearAllActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelOptions.add(buttonClearAll, gridBagConstraints);

        panelSinc.setBorder(javax.swing.BorderFactory.createTitledBorder("Paramètres du sinc"));
        java.awt.GridBagLayout panelSincLayout = new java.awt.GridBagLayout();
        panelSincLayout.columnWidths = new int[] {0, 5, 0};
        panelSincLayout.rowHeights = new int[] {0, 3, 0, 3, 0, 3, 0};
        panelSinc.setLayout(panelSincLayout);

        labelAmplitudeSinc.setText("Amplitude :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelSinc.add(labelAmplitudeSinc, gridBagConstraints);

        spinnerAmplitudeSinc.setModel(new javax.swing.SpinnerNumberModel(Double.valueOf(1.0d), Double.valueOf(1.0d), null, Double.valueOf(1.0d)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelSinc.add(spinnerAmplitudeSinc, gridBagConstraints);

        labelDureeSinc.setText("Durée :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelSinc.add(labelDureeSinc, gridBagConstraints);

        spinnerDureeSinc.setModel(new javax.swing.SpinnerNumberModel(Double.valueOf(0.0d), Double.valueOf(0.0d), null, Double.valueOf(0.1d)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelSinc.add(spinnerDureeSinc, gridBagConstraints);

        labelOffsetSinc.setText("Offset :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelSinc.add(labelOffsetSinc, gridBagConstraints);

        spinnerOffsetSinc.setModel(new javax.swing.SpinnerNumberModel(Double.valueOf(0.0d), null, null, Double.valueOf(0.1d)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelSinc.add(spinnerOffsetSinc, gridBagConstraints);

        buttonGenerateSinc.setText("Générer");
        buttonGenerateSinc.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                buttonGenerateSincActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelSinc.add(buttonGenerateSinc, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelOptions.add(panelSinc, gridBagConstraints);

        panelSignal.setBorder(javax.swing.BorderFactory.createTitledBorder("Paramètres des signaux"));
        java.awt.GridBagLayout paneSignalsOptionsLayout = new java.awt.GridBagLayout();
        paneSignalsOptionsLayout.columnWidths = new int[] {0, 5, 0};
        paneSignalsOptionsLayout.rowHeights = new int[] {0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0};
        panelSignal.setLayout(paneSignalsOptionsLayout);

        labelTypeSig.setText("Type de signal :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelSignal.add(labelTypeSig, gridBagConstraints);

        comboBoxSig.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sinusoidale", "Triangulaire", "Rectangulaire", "Dents de scie", "DC ou continu", "Impulsion rectangulaire", "Impulsion de Dirac", "Byte" }));
        comboBoxSig.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                comboBoxSigActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelSignal.add(comboBoxSig, gridBagConstraints);

        labelNomSig.setText("Nom :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelSignal.add(labelNomSig, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelSignal.add(textFieldNomSig, gridBagConstraints);

        labelAmplitudeSig.setText("Amplitude :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelSignal.add(labelAmplitudeSig, gridBagConstraints);

        spinnerAmplitudeSig.setModel(new javax.swing.SpinnerNumberModel(Double.valueOf(1.0d), Double.valueOf(1.0d), null, Double.valueOf(1.0d)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelSignal.add(spinnerAmplitudeSig, gridBagConstraints);

        labelFrequenceSig.setText("Fréquence (Hz) :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelSignal.add(labelFrequenceSig, gridBagConstraints);

        spinnerFrequenceSig.setModel(new javax.swing.SpinnerNumberModel(Double.valueOf(1.0d), Double.valueOf(1.0d), null, Double.valueOf(1.0d)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelSignal.add(spinnerFrequenceSig, gridBagConstraints);

        labelOffsetSig.setText("Offset :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelSignal.add(labelOffsetSig, gridBagConstraints);

        spinnerOffsetSig.setModel(new javax.swing.SpinnerNumberModel(Double.valueOf(0.0d), null, null, Double.valueOf(0.1d)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelSignal.add(spinnerOffsetSig, gridBagConstraints);

        labelDureeSig.setText("Durée :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelSignal.add(labelDureeSig, gridBagConstraints);

        spinnerDureeSig.setModel(new javax.swing.SpinnerNumberModel(Double.valueOf(0.0d), Double.valueOf(0.0d), null, Double.valueOf(0.1d)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelSignal.add(spinnerDureeSig, gridBagConstraints);

        labelByteSig.setText("Byte :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelSignal.add(labelByteSig, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelSignal.add(textFieldByteSig, gridBagConstraints);

        checkBoxComposanteContinueSig.setText("Composante DC :");
        checkBoxComposanteContinueSig.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                checkBoxComposanteContinueSigActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelSignal.add(checkBoxComposanteContinueSig, gridBagConstraints);

        spinnerComposanteContinueSig.setModel(new javax.swing.SpinnerNumberModel());
        spinnerComposanteContinueSig.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelSignal.add(spinnerComposanteContinueSig, gridBagConstraints);

        buttonGenerateSignal.setText("Générer");
        buttonGenerateSignal.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                buttonGenerateSignalActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelSignal.add(buttonGenerateSignal, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelOptions.add(panelSignal, gridBagConstraints);

        panelDiscretiseur.setBorder(javax.swing.BorderFactory.createTitledBorder("Paramètres du discretiseur"));
        java.awt.GridBagLayout panelDiscretiseurLayout = new java.awt.GridBagLayout();
        panelDiscretiseurLayout.columnWidths = new int[] {0, 5, 0};
        panelDiscretiseurLayout.rowHeights = new int[] {0, 3, 0, 3, 0};
        panelDiscretiseur.setLayout(panelDiscretiseurLayout);

        labelSamplesDisc.setText("Samples :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelDiscretiseur.add(labelSamplesDisc, gridBagConstraints);

        spinnerSamplesDisc.setModel(new javax.swing.SpinnerNumberModel(4096, 64, 4096, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelDiscretiseur.add(spinnerSamplesDisc, gridBagConstraints);

        labelOrigineDisc.setText("origine :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelDiscretiseur.add(labelOrigineDisc, gridBagConstraints);

        spinnerOrigineDisc.setModel(new javax.swing.SpinnerNumberModel(Double.valueOf(0.0d), null, null, Double.valueOf(0.1d)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelDiscretiseur.add(spinnerOrigineDisc, gridBagConstraints);

        labelDureeDisc.setText("Durée :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelDiscretiseur.add(labelDureeDisc, gridBagConstraints);

        spinnerDureeDisc.setModel(new javax.swing.SpinnerNumberModel(Double.valueOf(1.0d), Double.valueOf(0.0d), null, Double.valueOf(0.1d)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelDiscretiseur.add(spinnerDureeDisc, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelOptions.add(panelDiscretiseur, gridBagConstraints);

        panelConvolution.setBorder(javax.swing.BorderFactory.createTitledBorder("Paramètres de la convolution"));
        panelConvolution.setLayout(new java.awt.GridLayout(1, 0));

        checkBoxMakeConvolution.setText("<html>Réaliser la convolution temporelle<br>entre le dernier signal généré et le sinc</html>");
        panelConvolution.add(checkBoxMakeConvolution);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelOptions.add(panelConvolution, gridBagConstraints);

        panelFiltrage.setBorder(javax.swing.BorderFactory.createTitledBorder("Paramètres des filtrages"));
        java.awt.GridBagLayout panelFiltrageLayout = new java.awt.GridBagLayout();
        panelFiltrageLayout.columnWidths = new int[] {0, 5, 0};
        panelFiltrageLayout.rowHeights = new int[] {0, 3, 0, 3, 0, 3, 0, 3, 0};
        panelFiltrage.setLayout(panelFiltrageLayout);

        checkBoxPasseBas.setText("Filtrage passe-bass idéal");
        checkBoxPasseBas.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                checkBoxPasseBasActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 2.0;
        panelFiltrage.add(checkBoxPasseBas, gridBagConstraints);

        labelFrequencePasseBas.setText("Fréquence limite (Hz) :");
        labelFrequencePasseBas.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelFiltrage.add(labelFrequencePasseBas, gridBagConstraints);

        spinnerFrequencePasseBas.setModel(new javax.swing.SpinnerNumberModel(Double.valueOf(1.0d), Double.valueOf(1.0d), null, Double.valueOf(1.0d)));
        spinnerFrequencePasseBas.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelFiltrage.add(spinnerFrequencePasseBas, gridBagConstraints);

        checkBoxPasseHaut.setText("Filtrage passe-haut idéal");
        checkBoxPasseHaut.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                checkBoxPasseHautActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 2.0;
        panelFiltrage.add(checkBoxPasseHaut, gridBagConstraints);

        labelFrequencePasseHaut.setText("Fréquence limite (Hz) :");
        labelFrequencePasseHaut.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelFiltrage.add(labelFrequencePasseHaut, gridBagConstraints);

        spinnerFrequencePasseHaut.setModel(new javax.swing.SpinnerNumberModel(Double.valueOf(1.0d), Double.valueOf(1.0d), null, Double.valueOf(1.0d)));
        spinnerFrequencePasseHaut.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelFiltrage.add(spinnerFrequencePasseHaut, gridBagConstraints);

        buttonFilterLastSignal.setText("Filtrer dernier signal généré");
        buttonFilterLastSignal.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                buttonFilterLastSignalActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 2.0;
        panelFiltrage.add(buttonFilterLastSignal, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelOptions.add(panelFiltrage, gridBagConstraints);

        splitPaneSignals.setLeftComponent(panelOptions);

        getContentPane().add(splitPaneSignals, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //<editor-fold defaultstate="collapsed" desc="Private functions">
    private void updateTextFieldSignalName()
    {
        // Update the signal name textfield
        this.textFieldNomSig.setText(
            String.valueOf(this.comboBoxSig.getSelectedItem())
                + " " + String.valueOf(signalCount + 1));
    }

    private void updatePanelSignal()
    {
        String signalName = String.valueOf(this.comboBoxSig.getSelectedItem());

        this.updateTextFieldSignalName();

        boolean rectImpulseSelected  = signalName.equalsIgnoreCase("Impulsion rectangulaire");
        boolean diracImpulseSelected = signalName.equalsIgnoreCase("Impulsion de Dirac");
        boolean periodicSignalSelected = signalName.equalsIgnoreCase("Sinusoidale") ||
                                         signalName.equalsIgnoreCase("Triangulaire") ||
                                         signalName.equalsIgnoreCase("Rectangulaire") ||
                                         signalName.equalsIgnoreCase("Dents de scie");
        boolean byteSelected = signalName.equalsIgnoreCase("Byte");

        // Change widgets visibility
        this.labelFrequenceSig.setVisible(periodicSignalSelected);
        this.spinnerFrequenceSig.setVisible(periodicSignalSelected);

        this.labelDureeSig.setVisible(rectImpulseSelected);
        this.spinnerDureeSig.setVisible(rectImpulseSelected);

        this.labelOffsetSig.setVisible(rectImpulseSelected || diracImpulseSelected);
        this.spinnerOffsetSig.setVisible(rectImpulseSelected || diracImpulseSelected);

        this.labelAmplitudeSig.setVisible(!diracImpulseSelected);
        this.spinnerAmplitudeSig.setVisible(!diracImpulseSelected);

        this.labelByteSig.setVisible(byteSelected);
        this.textFieldByteSig.setVisible(byteSelected);
    }

    private void updatePanelConvolution()
    {
        if (this.signalCount > 0)
        {
            this.panelConvolution.setVisible(true);
        }
        else
        {
            this.checkBoxMakeConvolution.setSelected(false);
            this.panelConvolution.setVisible(false);
        }
    }

    private void updatePanelFiltrage()
    {
        if (this.signalCount > 0)
        {
            this.panelFiltrage.setVisible(true);
        }
        else
        {
            this.checkBoxPasseBas.setSelected(false);
            this.checkBoxPasseHaut.setSelected(false);
            this.panelFiltrage.setVisible(false);
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Events handlers">
    private void comboBoxSigActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_comboBoxSigActionPerformed
    {//GEN-HEADEREND:event_comboBoxSigActionPerformed
        this.updatePanelSignal();
    }//GEN-LAST:event_comboBoxSigActionPerformed

    private void buttonGenerateSignalActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_buttonGenerateSignalActionPerformed
    {//GEN-HEADEREND:event_buttonGenerateSignalActionPerformed
        try
        {
            String signalName = this.textFieldNomSig.getText();
            if (signalName.isEmpty())
                throw new Exception("Nom du signal invalide");

            // Get discretiseur parameters
            int samples = (int)spinnerSamplesDisc.getValue();
            double origine = (double)this.spinnerOrigineDisc.getValue();
            double duree = (double)this.spinnerDureeDisc.getValue();
            Discretiseur discretiseur = new Discretiseur(samples, origine, duree);

            double amplitude = (double)spinnerAmplitudeSig.getValue();

            String signalType = String.valueOf(this.comboBoxSig.getSelectedItem());
            switch(signalType)
            {
                case "Sinusoidale":
                {
                    double frequence = (double)spinnerFrequenceSig.getValue();
                    lastSignal = SignalPeriodique.getInstance(
                        SignalPeriodique.SINUS, amplitude, frequence, 0.0, discretiseur);
                    break;
                }
                case "Triangulaire":
                {
                    double frequence = (double)spinnerFrequenceSig.getValue();
                    lastSignal = SignalPeriodique.getInstance(
                        SignalPeriodique.TRIANGLE, amplitude, frequence, 0.0, discretiseur);
                    break;
                }
                case "Rectangulaire":
                {
                    double frequence = (double)spinnerFrequenceSig.getValue();
                    lastSignal = SignalPeriodique.getInstance(
                        SignalPeriodique.CARRE, amplitude, frequence, 0.0, discretiseur);
                    break;
                }
                case "Dents de scie":
                {
                    double frequence = (double)spinnerFrequenceSig.getValue();
                    lastSignal = SignalPeriodique.getInstance(
                        SignalPeriodique.SCIE, amplitude, frequence, 0.0, discretiseur);
                    break;
                }
                case "DC ou continu":
                {
                    lastSignal = SignalAnalogique.getInstance(
                        SignalAnalogique.CONSTANT, new Nombre(amplitude, 0), discretiseur);
                    break;
                }
                case "Impulsion rectangulaire":
                {
                    double retard = (double)spinnerOffsetSig.getValue();
                    duree  = (double)spinnerDureeSig.getValue();
                    lastSignal = new ImpulsionRectangulaire(discretiseur, amplitude, retard, duree);
                    break;
                }
                case "Impulsion de Dirac":
                {
                    double retard = (double)spinnerOffsetSig.getValue();
                    lastSignal = new ImpulsionDirac(discretiseur, 1, retard);
                    break;
                }
                case "Byte":
                {
                    String byteString = this.textFieldByteSig.getText();
                    if (byteString.length() != 8)
                        throw new Exception("Le nombre de bits est différent de 8");

                    // Create byte signal
                    lastSignal = new ImpulsionRectangulaire(discretiseur, (byteString.charAt(0) == '0') ? 0 : amplitude, 0.0, 0.125);
                    for(int i = 1; i < 8; i++)
                        lastSignal = SignalOperations.somme(lastSignal, new ImpulsionRectangulaire(discretiseur, (byteString.charAt(i) == '0') ? 0 : 1, i/8.0, 0.125));
                    break;
                }
                default:
                    throw new Exception("Signal non reconnu");
            }

            // Ajout d'une composante continue
            if (this.checkBoxComposanteContinueSig.isSelected())
            {
                int DC = (int)this.spinnerComposanteContinueSig.getValue();
                Signal composanteContinue = SignalAnalogique.getInstance(
                        SignalAnalogique.CONSTANT, new Nombre(DC, 0), discretiseur);
                lastSignal = SignalOperations.somme(lastSignal, composanteContinue);
            }

            // Ajout du signal
            plotSignal.addSignal(lastSignal, signalName, false);

            // Calcul de la transformée de Fourier
            Signal fourier = Fourier.fourier(lastSignal);

            // Ajout du module (spectre)
            plotSpectre.addSignal(fourier.module(), signalName, false);
            // Ajout de l'argument (phase)
            this.plotPhase.addSignal(fourier.argument(), signalName, false);

            this.signalCount++;
            this.lastSignalName = signalName;
            this.updateTextFieldSignalName();
        }
        catch (Exception e)
        {
            MessageBoxes.ShowError(this, e.getMessage(), "Une erreur s'est produite");
        }
    }//GEN-LAST:event_buttonGenerateSignalActionPerformed

    private void buttonClearAllActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_buttonClearAllActionPerformed
    {//GEN-HEADEREND:event_buttonClearAllActionPerformed
        this.signalCount = 0;

        // Signal
        this.plotSignal.clear();
        this.plotSpectre.clear();
        this.plotPhase.clear();

        // Sinc
        this.plotSincTemp.clear();
        this.plotSincFreq.clear();

        switch (this.tabbedPanePlots.getSelectedIndex())
        {
            case 0:
                this.updateTextFieldSignalName();
                break;
            case 1:
                this.updatePanelConvolution();
            case 2:
                this.updatePanelFiltrage();
            default:;
        }
    }//GEN-LAST:event_buttonClearAllActionPerformed

    private void checkBoxComposanteContinueSigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxComposanteContinueSigActionPerformed
        this.spinnerComposanteContinueSig.setEnabled(this.checkBoxComposanteContinueSig.isSelected());
    }//GEN-LAST:event_checkBoxComposanteContinueSigActionPerformed

    private void buttonGenerateSincActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGenerateSincActionPerformed

        // Get discretiseur parameters
        int samples = (int)spinnerSamplesDisc.getValue();
        double origine = (double)this.spinnerOrigineDisc.getValue();
        double duree = (double)this.spinnerDureeDisc.getValue();
        Discretiseur discretiseur = new Discretiseur(samples, origine, duree);

        // Get sinc parameters
        double amplitudeSinc = (double)this.spinnerAmplitudeSinc.getValue();
        double dureeSinc = (double)this.spinnerDureeSinc.getValue();
        double offsetSinc = (double)this.spinnerOffsetSinc.getValue();

        // Create sinc
        this.lastSinc = new SinusCardinal(discretiseur, amplitudeSinc, offsetSinc, dureeSinc);

        // Sinus cardinal dans le domaine temporel
        this.plotSincTemp.addSignal(this.lastSinc, "Sinus Cardinal", true);

        // Sinus cardinal dans le domaine fréquentiel
        Signal fourier = Fourier.fourier(this.lastSinc);
        this.plotSincFreq.addSignal(fourier.module(), "Sinus Cardinal", true);

        // Convolution
        if (this.checkBoxMakeConvolution.isSelected())
        {
            Signal convolution = Convolution.convolution(
                this.lastSignal, this.lastSinc);
            this.plotConvolutionTemp.addSignal(convolution, "Convolution", true);

            fourier = Fourier.fourier(convolution);
            this.plotConvolutionFreq.addSignal(fourier.module(), "Convolution", true);
        }
    }//GEN-LAST:event_buttonGenerateSincActionPerformed

    private void tabbedPanePlotsStateChanged(javax.swing.event.ChangeEvent evt)//GEN-FIRST:event_tabbedPanePlotsStateChanged
    {//GEN-HEADEREND:event_tabbedPanePlotsStateChanged
        switch(this.tabbedPanePlots.getSelectedIndex())
        {
            // Tab "Signaux"
            case 0:
                this.panelDiscretiseur.setVisible(true);
                this.panelSignal.setVisible(true);
                this.updatePanelSignal();
                this.panelSinc.setVisible(false);
                this.panelConvolution.setVisible(false);
                this.panelFiltrage.setVisible(false);
                break;
            // Tab "Sinc et Convolution"
            case 1:
                this.panelDiscretiseur.setVisible(true);
                this.panelSignal.setVisible(false);
                this.panelSinc.setVisible(true);
                this.updatePanelConvolution();
                this.panelFiltrage.setVisible(false);
                break;
            // Tab "Filtrage"
            case 2:
                this.panelDiscretiseur.setVisible(false);
                this.panelSignal.setVisible(false);
                this.panelSinc.setVisible(false);
                this.panelConvolution.setVisible(false);
                this.updatePanelFiltrage();
                break;
        }
    }//GEN-LAST:event_tabbedPanePlotsStateChanged

    private void checkBoxPasseBasActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_checkBoxPasseBasActionPerformed
    {//GEN-HEADEREND:event_checkBoxPasseBasActionPerformed
        boolean enable = this.checkBoxPasseBas.isSelected();
        this.labelFrequencePasseBas.setEnabled(enable);
        this.spinnerFrequencePasseBas.setEnabled(enable);
    }//GEN-LAST:event_checkBoxPasseBasActionPerformed

    private void checkBoxPasseHautActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_checkBoxPasseHautActionPerformed
    {//GEN-HEADEREND:event_checkBoxPasseHautActionPerformed
        boolean enable = this.checkBoxPasseHaut.isSelected();
        this.labelFrequencePasseHaut.setEnabled(enable);
        this.spinnerFrequencePasseHaut.setEnabled(enable);
    }//GEN-LAST:event_checkBoxPasseHautActionPerformed

    private void buttonFilterLastSignalActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_buttonFilterLastSignalActionPerformed
    {//GEN-HEADEREND:event_buttonFilterLastSignalActionPerformed
        // Filtre passe-bas
        if (this.checkBoxPasseBas.isSelected())
        {
            double frequence = (double)this.spinnerFrequencePasseBas.getValue();

            // Domaine frequentiel
            Signal fourierFiltre = FiltrageLineaire.filtrePasseBasFourier(lastSignal, frequence);
            this.plotFiltragePasseBasFreq.addSignal(fourierFiltre.module(), lastSignalName, true);

            // Domaine temporel
            Signal signalFiltre = Fourier.fourierI(fourierFiltre, 0);
            this.plotFiltragePasseBasTemp.addSignal(signalFiltre, lastSignalName, true);
        }

        // Filtre passe-haut
        if (this.checkBoxPasseHaut.isSelected())
        {

        }
    }//GEN-LAST:event_buttonFilterLastSignalActionPerformed
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Main">
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() ->
        {
            new MainFrame().setVisible(true);
        });
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Variables declarations">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonClearAll;
    private javax.swing.JButton buttonFilterLastSignal;
    private javax.swing.JButton buttonGenerateSignal;
    private javax.swing.JButton buttonGenerateSinc;
    private javax.swing.JCheckBox checkBoxComposanteContinueSig;
    private javax.swing.JCheckBox checkBoxMakeConvolution;
    private javax.swing.JCheckBox checkBoxPasseBas;
    private javax.swing.JCheckBox checkBoxPasseHaut;
    private javax.swing.JComboBox comboBoxSig;
    private javax.swing.JLabel labelAmplitudeSig;
    private javax.swing.JLabel labelAmplitudeSinc;
    private javax.swing.JLabel labelByteSig;
    private javax.swing.JLabel labelDureeDisc;
    private javax.swing.JLabel labelDureeSig;
    private javax.swing.JLabel labelDureeSinc;
    private javax.swing.JLabel labelFrequencePasseBas;
    private javax.swing.JLabel labelFrequencePasseHaut;
    private javax.swing.JLabel labelFrequenceSig;
    private javax.swing.JLabel labelNomSig;
    private javax.swing.JLabel labelOffsetSig;
    private javax.swing.JLabel labelOffsetSinc;
    private javax.swing.JLabel labelOrigineDisc;
    private javax.swing.JLabel labelSamplesDisc;
    private javax.swing.JLabel labelTypeSig;
    private javax.swing.JPanel panelConvolution;
    private javax.swing.JPanel panelDiscretiseur;
    private javax.swing.JPanel panelFiltrage;
    private javax.swing.JPanel panelOptions;
    private javax.swing.JPanel panelPlotsFiltrage;
    private javax.swing.JPanel panelPlotsSignals;
    private javax.swing.JPanel panelPlotsSincConv;
    private javax.swing.JPanel panelSignal;
    private javax.swing.JPanel panelSinc;
    private javax.swing.JSpinner spinnerAmplitudeSig;
    private javax.swing.JSpinner spinnerAmplitudeSinc;
    private javax.swing.JSpinner spinnerComposanteContinueSig;
    private javax.swing.JSpinner spinnerDureeDisc;
    private javax.swing.JSpinner spinnerDureeSig;
    private javax.swing.JSpinner spinnerDureeSinc;
    private javax.swing.JSpinner spinnerFrequencePasseBas;
    private javax.swing.JSpinner spinnerFrequencePasseHaut;
    private javax.swing.JSpinner spinnerFrequenceSig;
    private javax.swing.JSpinner spinnerOffsetSig;
    private javax.swing.JSpinner spinnerOffsetSinc;
    private javax.swing.JSpinner spinnerOrigineDisc;
    private javax.swing.JSpinner spinnerSamplesDisc;
    private javax.swing.JSplitPane splitPaneSignals;
    private javax.swing.JTabbedPane tabbedPanePlots;
    private javax.swing.JTextField textFieldByteSig;
    private javax.swing.JTextField textFieldNomSig;
    // End of variables declaration//GEN-END:variables
    //</editor-fold>
}
