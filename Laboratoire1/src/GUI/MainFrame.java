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
import signaux.SignalBinaire;
import signaux.SignalPeriodique;

/**
 *
 * @author Nakim
 */
public class MainFrame extends javax.swing.JFrame
{
    private final SignalPanel panelSignal;
    private final SignalPanel panelSpectre;
    private final SignalPanel panelPhase;

    //<editor-fold defaultstate="collapsed" desc="Constructor">
    public MainFrame()
    {
        // GUI Configuration
        initComponents();
        this.updatePanelSignalOptions();

        this.panelSignal = new SignalPanel("Signaux", null, null);
        this.panelSpectre = new SignalPanel("Spectre", null, null);
        this.panelPhase = new SignalPanel("Phase", null, null);

        this.panelPlots.add(this.panelSignal);
        this.panelPlots.add(this.panelSpectre);
        this.panelPlots.add(this.panelPhase);

        this.pack();
    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        mainSplitPane = new javax.swing.JSplitPane();
        panelSignalOptions = new javax.swing.JPanel();
        labelSignal = new javax.swing.JLabel();
        comboBoxSignals = new javax.swing.JComboBox();
        labelSamples = new javax.swing.JLabel();
        spinnerSamples = new javax.swing.JSpinner();
        labelAmplitude = new javax.swing.JLabel();
        spinnerAmplitude = new javax.swing.JSpinner();
        labelFrequence = new javax.swing.JLabel();
        spinnerFrequence = new javax.swing.JSpinner();
        labelDuree = new javax.swing.JLabel();
        spinnerDuree = new javax.swing.JSpinner();
        labelOffset = new javax.swing.JLabel();
        spinnerOffset = new javax.swing.JSpinner();
        buttonGenerateSignal = new javax.swing.JButton();
        buttonClearAll = new javax.swing.JButton();
        labelNom = new javax.swing.JLabel();
        textFieldNom = new javax.swing.JTextField();
        spinnerComposanteContinue = new javax.swing.JSpinner();
        labelByte = new javax.swing.JLabel();
        textFieldByte = new javax.swing.JTextField();
        checkBoxComposanteContinue = new javax.swing.JCheckBox();
        panelPlots = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sciences Appliquées - Laboratoire 1");

        mainSplitPane.setOneTouchExpandable(true);

        panelSignalOptions.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 3, 3, 3));
        java.awt.GridBagLayout panelSignalOptionsLayout = new java.awt.GridBagLayout();
        panelSignalOptionsLayout.columnWidths = new int[] {0, 5, 0};
        panelSignalOptionsLayout.rowHeights = new int[] {0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0};
        panelSignalOptions.setLayout(panelSignalOptionsLayout);

        labelSignal.setText("Type de signal :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelSignalOptions.add(labelSignal, gridBagConstraints);

        comboBoxSignals.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sinusoidale", "Triangulaire", "Rectangulaire", "Dents de scie", "DC ou continu", "Impulsion rectangulaire", "Impulsion de Dirac", "Byte" }));
        comboBoxSignals.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxSignalsActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelSignalOptions.add(comboBoxSignals, gridBagConstraints);

        labelSamples.setText("Samples :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelSignalOptions.add(labelSamples, gridBagConstraints);

        spinnerSamples.setModel(new javax.swing.SpinnerNumberModel(4096, 64, 4096, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelSignalOptions.add(spinnerSamples, gridBagConstraints);

        labelAmplitude.setText("Amplitude :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelSignalOptions.add(labelAmplitude, gridBagConstraints);

        spinnerAmplitude.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelSignalOptions.add(spinnerAmplitude, gridBagConstraints);

        labelFrequence.setText("Fréquence (Hz) :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelSignalOptions.add(labelFrequence, gridBagConstraints);

        spinnerFrequence.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelSignalOptions.add(spinnerFrequence, gridBagConstraints);

        labelDuree.setText("Durée :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelSignalOptions.add(labelDuree, gridBagConstraints);

        spinnerDuree.setModel(new javax.swing.SpinnerNumberModel(Double.valueOf(0.0d), Double.valueOf(0.0d), null, Double.valueOf(0.1d)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelSignalOptions.add(spinnerDuree, gridBagConstraints);

        labelOffset.setText("Offset :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelSignalOptions.add(labelOffset, gridBagConstraints);

        spinnerOffset.setModel(new javax.swing.SpinnerNumberModel(Double.valueOf(0.0d), null, null, Double.valueOf(0.1d)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelSignalOptions.add(spinnerOffset, gridBagConstraints);

        buttonGenerateSignal.setText("Générer");
        buttonGenerateSignal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGenerateSignalActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelSignalOptions.add(buttonGenerateSignal, gridBagConstraints);

        buttonClearAll.setText("Tout effacer");
        buttonClearAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonClearAllActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelSignalOptions.add(buttonClearAll, gridBagConstraints);

        labelNom.setText("Nom :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelSignalOptions.add(labelNom, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelSignalOptions.add(textFieldNom, gridBagConstraints);

        spinnerComposanteContinue.setModel(new javax.swing.SpinnerNumberModel());
        spinnerComposanteContinue.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelSignalOptions.add(spinnerComposanteContinue, gridBagConstraints);

        labelByte.setText("Byte :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelSignalOptions.add(labelByte, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelSignalOptions.add(textFieldByte, gridBagConstraints);

        checkBoxComposanteContinue.setText("Composante continue :");
        checkBoxComposanteContinue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxComposanteContinueActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelSignalOptions.add(checkBoxComposanteContinue, gridBagConstraints);

        mainSplitPane.setLeftComponent(panelSignalOptions);

        panelPlots.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 3, 3, 3));
        panelPlots.setLayout(new java.awt.GridLayout(2, 2));
        mainSplitPane.setRightComponent(panelPlots);

        getContentPane().add(mainSplitPane, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //<editor-fold defaultstate="collapsed" desc="Private functions">
    private void updatePanelSignalOptions()
    {
        String signalName = String.valueOf(this.comboBoxSignals.getSelectedItem());

        // Update the signal name textfield
        this.textFieldNom.setText(signalName);

        boolean rectImpulseSelected  = signalName.equalsIgnoreCase("Impulsion rectangulaire");
        boolean diracImpulseSelected = signalName.equalsIgnoreCase("Impulsion de Dirac");
        boolean periodicSignalSelected = signalName.equalsIgnoreCase("Sinusoidale") ||
                                         signalName.equalsIgnoreCase("Triangulaire") ||
                                         signalName.equalsIgnoreCase("Rectangulaire") ||
                                         signalName.equalsIgnoreCase("Dents de scie");
        boolean byteSelected = signalName.equalsIgnoreCase("Byte");

        // Change widgets visibility
        this.labelFrequence.setVisible(periodicSignalSelected);
        this.spinnerFrequence.setVisible(periodicSignalSelected);
        
        this.labelDuree.setVisible(rectImpulseSelected);
        this.spinnerDuree.setVisible(rectImpulseSelected);

        this.labelOffset.setVisible(rectImpulseSelected || diracImpulseSelected);
        this.spinnerOffset.setVisible(rectImpulseSelected || diracImpulseSelected);
        
        this.labelAmplitude.setVisible(!diracImpulseSelected);
        this.spinnerAmplitude.setVisible(!diracImpulseSelected);
        
        this.labelByte.setVisible(byteSelected);
        this.textFieldByte.setVisible(byteSelected);
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Events handlers">
    private void comboBoxSignalsActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_comboBoxSignalsActionPerformed
    {//GEN-HEADEREND:event_comboBoxSignalsActionPerformed
        this.updatePanelSignalOptions();

    }//GEN-LAST:event_comboBoxSignalsActionPerformed

    private void buttonGenerateSignalActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_buttonGenerateSignalActionPerformed
    {//GEN-HEADEREND:event_buttonGenerateSignalActionPerformed
        try
        {
            String signalName = this.textFieldNom.getText();
            if (signalName.isEmpty())
                throw new Exception("Nom du signal invalide");

            int sample = (int)spinnerSamples.getValue();
            int amplitude = (int)spinnerAmplitude.getValue();
            Discretiseur discretiseur = new Discretiseur(sample,0.0,1.0);
            Signal signal = null;

            String signalType = String.valueOf(this.comboBoxSignals.getSelectedItem());
            switch(signalType)
            {
                case "Sinusoidale":
                {
                    int frequence = (int)spinnerFrequence.getValue();
                    signal = SignalPeriodique.getInstance(SignalPeriodique.SINUS, amplitude, frequence, 0.0, discretiseur);
                    break;
                }
                case "Triangulaire":
                {
                    int frequence = (int)spinnerFrequence.getValue();
                    signal = SignalPeriodique.getInstance(SignalPeriodique.TRIANGLE, amplitude, frequence, 0.0, discretiseur);
                    break;
                }
                case "Rectangulaire":
                {
                    int frequence = (int)spinnerFrequence.getValue();
                    signal = SignalPeriodique.getInstance(SignalPeriodique.CARRE, amplitude, frequence, 0.0, discretiseur);
                    break;
                }
                case "Dents de scie":
                {
                    int frequence = (int)spinnerFrequence.getValue();
                    signal = SignalPeriodique.getInstance(SignalPeriodique.SCIE, amplitude, frequence, 0.0, discretiseur);
                    break;
                }
                case "DC ou continu":
                {
                    signal = SignalAnalogique.getInstance(SignalAnalogique.CONSTANT, new Nombre(amplitude, 0), discretiseur);
                    break;
                }
                case "Impulsion rectangulaire":
                {
                    double retard = (double)spinnerOffset.getValue();
                    double duree  = (double)spinnerDuree.getValue();
                    signal = new ImpulsionRectangulaire(discretiseur, amplitude, retard, duree);
                    break;
                }
                case "Impulsion de Dirac":
                {
                    int retard = (int)spinnerOffset.getValue();
                    signal = new ImpulsionDirac(discretiseur, 1, retard);
                    break;
                }
                case "Byte":
                {
                    String byteString = this.textFieldByte.getText();
                    if (byteString.length() != 8)
                        throw new Exception("Le nombre de bits est différent de 8");
                    
                    // Create byte signal
                    signal = new ImpulsionRectangulaire(discretiseur, (byteString.charAt(0) == '0') ? 0 : 1, 0.0, 0.125);
                    for(int i = 1; i < 8; i++)
                        signal = SignalOperations.somme(signal, new ImpulsionRectangulaire(discretiseur, (byteString.charAt(i) == '0') ? 0 : 1, i/8.0, 0.125));
                    break;
                }
                default:
                    throw new Exception("Signal non reconnu");
            }
            
            // Ajout d'une composante continue
            
            if (this.checkBoxComposanteContinue.isSelected())
            {
                int DC = (int)this.spinnerComposanteContinue.getValue();
                Signal composanteContinue = SignalAnalogique.getInstance(
                        SignalAnalogique.CONSTANT, new Nombre(DC, 0), discretiseur);
                signal = SignalOperations.somme(signal, composanteContinue);
            }

            // Ajout du signal
            panelSignal.addSignal(signal, signalName, false);

            // Calcul de la transformée de Fourier
            Signal fourier = Fourier.fourier(signal);

            // Ajout du module (spectre)
            panelSpectre.addSignal(fourier.module(), signalName, false);
            // Ajout de l'argument (phase)
            this.panelPhase.addSignal(fourier.argument(), signalName, false);
        }
        catch (Exception e)
        {
            MessageBoxes.ShowError(this, e.getMessage(), "Une erreur s'est produite");
        }
    }//GEN-LAST:event_buttonGenerateSignalActionPerformed

    private void buttonClearAllActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_buttonClearAllActionPerformed
    {//GEN-HEADEREND:event_buttonClearAllActionPerformed
        this.panelSignal.clear();
        this.panelSpectre.clear();
        this.panelPhase.clear();
    }//GEN-LAST:event_buttonClearAllActionPerformed

    private void checkBoxComposanteContinueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxComposanteContinueActionPerformed
        this.spinnerComposanteContinue.setEnabled(this.checkBoxComposanteContinue.isSelected());
    }//GEN-LAST:event_checkBoxComposanteContinueActionPerformed
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
    private javax.swing.JCheckBox checkBoxComposanteContinue;
    private javax.swing.JComboBox comboBoxSignals;
    private javax.swing.JLabel labelAmplitude;
    private javax.swing.JLabel labelByte;
    private javax.swing.JLabel labelDuree;
    private javax.swing.JLabel labelFrequence;
    private javax.swing.JLabel labelNom;
    private javax.swing.JLabel labelOffset;
    private javax.swing.JLabel labelSamples;
    private javax.swing.JLabel labelSignal;
    private javax.swing.JSplitPane mainSplitPane;
    private javax.swing.JPanel panelPlots;
    private javax.swing.JPanel panelSignalOptions;
    private javax.swing.JSpinner spinnerAmplitude;
    private javax.swing.JSpinner spinnerComposanteContinue;
    private javax.swing.JSpinner spinnerDuree;
    private javax.swing.JSpinner spinnerFrequence;
    private javax.swing.JSpinner spinnerOffset;
    private javax.swing.JSpinner spinnerSamples;
    private javax.swing.JTextField textFieldByte;
    private javax.swing.JTextField textFieldNom;
    // End of variables declaration//GEN-END:variables
    //</editor-fold>
}
