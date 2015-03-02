package GUI;

import operations.Fourier;
import signaux.Discretiseur;
import signaux.Signal;
import signaux.SignalPeriodique;

/**
 *
 * @author Nakim
 */
public class MainFrame extends javax.swing.JFrame
{
    private final SignalPanel panelSignal;
    private final SignalPanel panelSpectre;

    //<editor-fold defaultstate="collapsed" desc="Constructor">
    public MainFrame()
    {
        // GUI Configuration
        initComponents();

        this.panelSignal = new SignalPanel("Signaux", null, null);
        this.panelSpectre = new SignalPanel("Spectres", null, null);

        this.add(panelSignal);
        this.add(panelSpectre);

        this.pack();
    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {
        java.awt.GridBagConstraints gridBagConstraints;

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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sciences Appliquées - Laboratoire 1");
        getContentPane().setLayout(new java.awt.GridLayout());

        panelSignalOptions.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 3, 3, 3));
        java.awt.GridBagLayout panelSignalOptionsLayout = new java.awt.GridBagLayout();
        panelSignalOptionsLayout.columnWidths = new int[] {0, 5, 0, 5, 0};
        panelSignalOptionsLayout.rowHeights = new int[] {0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0};
        panelSignalOptions.setLayout(panelSignalOptionsLayout);

        labelSignal.setText("Signal:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelSignalOptions.add(labelSignal, gridBagConstraints);

        comboBoxSignals.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Triangulaire", "Rectangulaire", "Dents de scie", "DC ou continu", "Impulsion rectangulaire", "Impulsion de Dirac", "Byte" }));
        comboBoxSignals.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
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
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelSignalOptions.add(labelSamples, gridBagConstraints);

        spinnerSamples.setModel(new javax.swing.SpinnerNumberModel(4096, 64, 4096, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelSignalOptions.add(spinnerSamples, gridBagConstraints);

        labelAmplitude.setText("Amplitude :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelSignalOptions.add(labelAmplitude, gridBagConstraints);

        spinnerAmplitude.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelSignalOptions.add(spinnerAmplitude, gridBagConstraints);

        labelFrequence.setText("Fréquence (Hz) :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelSignalOptions.add(labelFrequence, gridBagConstraints);

        spinnerFrequence.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelSignalOptions.add(spinnerFrequence, gridBagConstraints);

        labelDuree.setText("Durée :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelSignalOptions.add(labelDuree, gridBagConstraints);

        spinnerDuree.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
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
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelSignalOptions.add(spinnerOffset, gridBagConstraints);

        buttonGenerateSignal.setText("Générer & ajouter");
        buttonGenerateSignal.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                buttonGenerateSignalActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 2.0;
        panelSignalOptions.add(buttonGenerateSignal, gridBagConstraints);

        getContentPane().add(panelSignalOptions);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //<editor-fold defaultstate="collapsed" desc="Events handlers">
    private void comboBoxSignalsActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_comboBoxSignalsActionPerformed
    {//GEN-HEADEREND:event_comboBoxSignalsActionPerformed
        int indexSignal = this.comboBoxSignals.getSelectedIndex();

        boolean rectImpulseSelected  = (indexSignal == 4);
        boolean diracImpulseSelected = (indexSignal == 5);

        // Change widgets visibility
        this.labelDuree.setVisible(rectImpulseSelected);
        this.spinnerDuree.setVisible(rectImpulseSelected);

        this.labelOffset.setVisible(rectImpulseSelected | diracImpulseSelected);
        this.spinnerOffset.setVisible(rectImpulseSelected | diracImpulseSelected);

    }//GEN-LAST:event_comboBoxSignalsActionPerformed

    private void buttonGenerateSignalActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_buttonGenerateSignalActionPerformed
    {//GEN-HEADEREND:event_buttonGenerateSignalActionPerformed
        int sample = (int)spinnerSamples.getValue();
        Discretiseur discretiseur = new Discretiseur(sample,0.0,1.0);

        int amplitude = (int)spinnerAmplitude.getValue();
        int frequence = (int)spinnerFrequence.getValue();
        Signal signal = SignalPeriodique.getInstance(SignalPeriodique.SINUS,amplitude,frequence,0.0,discretiseur);
        panelSignal.addSignal(signal, "Sinusoide", true);

        // Calcul de la transformée de Fourier
        Signal fourier = Fourier.fourier(signal);
        Signal spectre = fourier.module();
        panelSpectre.addSignal(spectre, "Spectre", true);
    }//GEN-LAST:event_buttonGenerateSignalActionPerformed
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
    private javax.swing.JButton buttonGenerateSignal;
    private javax.swing.JComboBox comboBoxSignals;
    private javax.swing.JLabel labelAmplitude;
    private javax.swing.JLabel labelDuree;
    private javax.swing.JLabel labelFrequence;
    private javax.swing.JLabel labelOffset;
    private javax.swing.JLabel labelSamples;
    private javax.swing.JLabel labelSignal;
    private javax.swing.JPanel panelSignalOptions;
    private javax.swing.JSpinner spinnerAmplitude;
    private javax.swing.JSpinner spinnerDuree;
    private javax.swing.JSpinner spinnerFrequence;
    private javax.swing.JSpinner spinnerOffset;
    private javax.swing.JSpinner spinnerSamples;
    // End of variables declaration//GEN-END:variables
    //</editor-fold>
}
