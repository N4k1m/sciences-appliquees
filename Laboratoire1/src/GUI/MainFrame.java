package GUI;

import Utils.MessageBoxes;
import math.Nombre;
import operations.Fourier;
import operations.SignalOperations;
import signaux.Discretiseur;
import signaux.ImpulsionDirac;
import signaux.ImpulsionRectangulaire;
import signaux.Signal;
import signaux.SignalAnalogique;
import signaux.SignalPeriodique;

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
    
    private Signal lastSignal;
    private Signal lastSinc;

    //<editor-fold defaultstate="collapsed" desc="Constructor">
    public MainFrame()
    {
        // GUI Configuration
        initComponents();
        this.updatePanelSignalOptions();

        // Tab Signals
        this.plotSignal = new SignalPanel("Signaux", null, null);
        this.panelPlotsSignals.add(this.plotSignal);
        this.plotSpectre = new SignalPanel("Spectres", null, null);
        this.panelPlotsSignals.add(this.plotSpectre);
        this.plotPhase = new SignalPanel("Phases", null, null);
        this.panelPlotsSignals.add(this.plotPhase);
        
        // Tab Sinc
        this.plotSincTemp = new SignalPanel("Signal", null, null);
        this.panelPlotsSinc.add(this.plotSincTemp);
        this.plotSincFreq = new SignalPanel("Spectre", null, null);
        this.panelPlotsSinc.add(this.plotSincFreq);

        this.pack();
        
        this.lastSignal = null;
        this.lastSinc = null;
    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        splitPaneSignals = new javax.swing.JSplitPane();
        tabbedPanePlots = new javax.swing.JTabbedPane();
        panelPlotsSignals = new javax.swing.JPanel();
        panelPlotsSinc = new javax.swing.JPanel();
        panelPlotsConvolution = new javax.swing.JPanel();
        panelPlotsFiltrage = new javax.swing.JPanel();
        panelOptions = new javax.swing.JPanel();
        buttonClearAll = new javax.swing.JButton();
        panelSincOptions = new javax.swing.JPanel();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sciences Appliquées - Laboratoire 1");

        splitPaneSignals.setOneTouchExpandable(true);

        panelPlotsSignals.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 3, 3, 3));
        panelPlotsSignals.setLayout(new java.awt.GridLayout(2, 2));
        tabbedPanePlots.addTab("Signaux", panelPlotsSignals);

        panelPlotsSinc.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 3, 3, 3));
        panelPlotsSinc.setLayout(new java.awt.GridLayout(1, 2));
        tabbedPanePlots.addTab("Sinc", panelPlotsSinc);
        tabbedPanePlots.addTab("Convolution", panelPlotsConvolution);
        tabbedPanePlots.addTab("Filtrage", panelPlotsFiltrage);

        splitPaneSignals.setRightComponent(tabbedPanePlots);

        panelOptions.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 3, 3, 3));
        java.awt.GridBagLayout panelSignalOptionsLayout = new java.awt.GridBagLayout();
        panelSignalOptionsLayout.columnWidths = new int[] {0};
        panelSignalOptionsLayout.rowHeights = new int[] {0, 3, 0, 3, 0, 3, 0};
        panelOptions.setLayout(panelSignalOptionsLayout);

        buttonClearAll.setText("Tout effacer");
        buttonClearAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonClearAllActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelOptions.add(buttonClearAll, gridBagConstraints);

        panelSincOptions.setBorder(javax.swing.BorderFactory.createTitledBorder("Paramètres du sinc"));
        java.awt.GridBagLayout panelSincLayout = new java.awt.GridBagLayout();
        panelSincLayout.columnWidths = new int[] {0, 5, 0};
        panelSincLayout.rowHeights = new int[] {0, 3, 0, 3, 0, 3, 0};
        panelSincOptions.setLayout(panelSincLayout);

        labelAmplitudeSinc.setText("Amplitude :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelSincOptions.add(labelAmplitudeSinc, gridBagConstraints);

        spinnerAmplitudeSinc.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelSincOptions.add(spinnerAmplitudeSinc, gridBagConstraints);

        labelDureeSinc.setText("Durée :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelSincOptions.add(labelDureeSinc, gridBagConstraints);

        spinnerDureeSinc.setModel(new javax.swing.SpinnerNumberModel(Double.valueOf(0.0d), Double.valueOf(0.0d), null, Double.valueOf(0.1d)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelSincOptions.add(spinnerDureeSinc, gridBagConstraints);

        labelOffsetSinc.setText("Offset :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelSincOptions.add(labelOffsetSinc, gridBagConstraints);

        spinnerOffsetSinc.setModel(new javax.swing.SpinnerNumberModel(Double.valueOf(0.0d), null, null, Double.valueOf(0.1d)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelSincOptions.add(spinnerOffsetSinc, gridBagConstraints);

        buttonGenerateSinc.setText("Générer");
        buttonGenerateSinc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGenerateSincActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelSincOptions.add(buttonGenerateSinc, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelOptions.add(panelSincOptions, gridBagConstraints);

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
        comboBoxSig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
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

        spinnerAmplitudeSig.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
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

        spinnerFrequenceSig.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
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

        checkBoxComposanteContinueSig.setText("Composante continue :");
        checkBoxComposanteContinueSig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
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
        buttonGenerateSignal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
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
        panelDiscretiseur.setLayout(new java.awt.GridBagLayout());

        labelSamplesDisc.setText("Samples :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelDiscretiseur.add(labelSamplesDisc, gridBagConstraints);

        spinnerSamplesDisc.setModel(new javax.swing.SpinnerNumberModel(4096, 64, 4096, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelDiscretiseur.add(spinnerSamplesDisc, gridBagConstraints);

        labelOrigineDisc.setText("origine :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelDiscretiseur.add(labelOrigineDisc, gridBagConstraints);

        spinnerOrigineDisc.setModel(new javax.swing.SpinnerNumberModel(Double.valueOf(0.0d), null, null, Double.valueOf(0.1d)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelDiscretiseur.add(spinnerOrigineDisc, gridBagConstraints);

        labelDureeDisc.setText("Durée :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelDiscretiseur.add(labelDureeDisc, gridBagConstraints);

        spinnerDureeDisc.setModel(new javax.swing.SpinnerNumberModel(Double.valueOf(1.0d), Double.valueOf(0.0d), null, Double.valueOf(0.1d)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelDiscretiseur.add(spinnerDureeDisc, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelOptions.add(panelDiscretiseur, gridBagConstraints);

        splitPaneSignals.setLeftComponent(panelOptions);

        getContentPane().add(splitPaneSignals, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //<editor-fold defaultstate="collapsed" desc="Private functions">
    private void updatePanelSignalOptions()
    {
        String signalName = String.valueOf(this.comboBoxSig.getSelectedItem());

        // Update the signal name textfield
        this.textFieldNomSig.setText(signalName);

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
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Events handlers">
    private void comboBoxSigActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_comboBoxSigActionPerformed
    {//GEN-HEADEREND:event_comboBoxSigActionPerformed
        this.updatePanelSignalOptions();

    }//GEN-LAST:event_comboBoxSigActionPerformed

    private void buttonGenerateSignalActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_buttonGenerateSignalActionPerformed
    {//GEN-HEADEREND:event_buttonGenerateSignalActionPerformed
        try
        {
            String signalName = this.textFieldNomSig.getText();
            if (signalName.isEmpty())
                throw new Exception("Nom du signal invalide");

            int sample = (int)spinnerSamplesDisc.getValue();
            int amplitude = (int)spinnerAmplitudeSig.getValue();
            Discretiseur discretiseur = new Discretiseur(sample,0.0,1.0);

            String signalType = String.valueOf(this.comboBoxSig.getSelectedItem());
            switch(signalType)
            {
                case "Sinusoidale":
                {
                    int frequence = (int)spinnerFrequenceSig.getValue();
                    lastSignal = SignalPeriodique.getInstance(SignalPeriodique.SINUS, amplitude, frequence, 0.0, discretiseur);
                    break;
                }
                case "Triangulaire":
                {
                    int frequence = (int)spinnerFrequenceSig.getValue();
                    lastSignal = SignalPeriodique.getInstance(SignalPeriodique.TRIANGLE, amplitude, frequence, 0.0, discretiseur);
                    break;
                }
                case "Rectangulaire":
                {
                    int frequence = (int)spinnerFrequenceSig.getValue();
                    lastSignal = SignalPeriodique.getInstance(SignalPeriodique.CARRE, amplitude, frequence, 0.0, discretiseur);
                    break;
                }
                case "Dents de scie":
                {
                    int frequence = (int)spinnerFrequenceSig.getValue();
                    lastSignal = SignalPeriodique.getInstance(SignalPeriodique.SCIE, amplitude, frequence, 0.0, discretiseur);
                    break;
                }
                case "DC ou continu":
                {
                    lastSignal = SignalAnalogique.getInstance(SignalAnalogique.CONSTANT, new Nombre(amplitude, 0), discretiseur);
                    break;
                }
                case "Impulsion rectangulaire":
                {
                    double retard = (double)spinnerOffsetSig.getValue();
                    double duree  = (double)spinnerDureeSig.getValue();
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
                    lastSignal = new ImpulsionRectangulaire(discretiseur, (byteString.charAt(0) == '0') ? 0 : 1, 0.0, 0.125);
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
        }
        catch (Exception e)
        {
            MessageBoxes.ShowError(this, e.getMessage(), "Une erreur s'est produite");
        }
    }//GEN-LAST:event_buttonGenerateSignalActionPerformed

    private void buttonClearAllActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_buttonClearAllActionPerformed
    {//GEN-HEADEREND:event_buttonClearAllActionPerformed
        this.plotSignal.clear();
        this.plotSpectre.clear();
        this.plotPhase.clear();
    }//GEN-LAST:event_buttonClearAllActionPerformed

    private void checkBoxComposanteContinueSigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxComposanteContinueSigActionPerformed
        this.spinnerComposanteContinueSig.setEnabled(this.checkBoxComposanteContinueSig.isSelected());
    }//GEN-LAST:event_checkBoxComposanteContinueSigActionPerformed

    private void buttonGenerateSincActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGenerateSincActionPerformed
        double amplitude = (double)this.spinnerAmplitudeSinc.getValue();
        double duree = (double)this.spinnerDureeSinc.getValue();
        double offset = (double)this.spinnerOffsetSinc.getValue();
    }//GEN-LAST:event_buttonGenerateSincActionPerformed
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
    private javax.swing.JButton buttonGenerateSignal;
    private javax.swing.JButton buttonGenerateSinc;
    private javax.swing.JCheckBox checkBoxComposanteContinueSig;
    private javax.swing.JComboBox comboBoxSig;
    private javax.swing.JLabel labelAmplitudeSig;
    private javax.swing.JLabel labelAmplitudeSinc;
    private javax.swing.JLabel labelByteSig;
    private javax.swing.JLabel labelDureeDisc;
    private javax.swing.JLabel labelDureeSig;
    private javax.swing.JLabel labelDureeSinc;
    private javax.swing.JLabel labelFrequenceSig;
    private javax.swing.JLabel labelNomSig;
    private javax.swing.JLabel labelOffsetSig;
    private javax.swing.JLabel labelOffsetSinc;
    private javax.swing.JLabel labelOrigineDisc;
    private javax.swing.JLabel labelSamplesDisc;
    private javax.swing.JLabel labelTypeSig;
    private javax.swing.JPanel panelDiscretiseur;
    private javax.swing.JPanel panelOptions;
    private javax.swing.JPanel panelPlotsConvolution;
    private javax.swing.JPanel panelPlotsFiltrage;
    private javax.swing.JPanel panelPlotsSignals;
    private javax.swing.JPanel panelPlotsSinc;
    private javax.swing.JPanel panelSignal;
    private javax.swing.JPanel panelSincOptions;
    private javax.swing.JSpinner spinnerAmplitudeSig;
    private javax.swing.JSpinner spinnerAmplitudeSinc;
    private javax.swing.JSpinner spinnerComposanteContinueSig;
    private javax.swing.JSpinner spinnerDureeDisc;
    private javax.swing.JSpinner spinnerDureeSig;
    private javax.swing.JSpinner spinnerDureeSinc;
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
