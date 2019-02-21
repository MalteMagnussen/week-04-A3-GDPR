package dk.cphbusiness.security.encryption;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**

 @author kasper
 */
public class Exercise3CryptoEdit extends javax.swing.JFrame {

    private static final String PATH = "/Users/kasper/cryptofile.foo";

    private String base64Iv;

    public Exercise3CryptoEdit() {
        initComponents();
    }

    /**
     This method is called from within the constructor to
     initialize the form.
     WARNING: Do NOT modify this code. The content of this method is
     always regenerated by the Form Editor.
     */
    @SuppressWarnings( "unchecked" )
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jLabel1 = new javax.swing.JLabel();
    pwField = new javax.swing.JTextField();
    jScrollPane1 = new javax.swing.JScrollPane();
    editText = new javax.swing.JTextArea();
    load = new javax.swing.JButton();
    save = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    jLabel1.setText("Amazing Crypto editor. Enter password:");

    pwField.setText("super secret");

    editText.setColumns(20);
    editText.setRows(5);
    jScrollPane1.setViewportView(editText);

    load.setText("Load");
    load.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        loadActionPerformed(evt);
      }
    });

    save.setText("Save");
    save.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        saveActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jScrollPane1)
          .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(pwField, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
              .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(18, 18, 18)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(load)
              .addComponent(save))
            .addGap(0, 0, Short.MAX_VALUE)))
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
          .addGroup(layout.createSequentialGroup()
            .addComponent(jLabel1)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(pwField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(16, 16, 16))
          .addGroup(layout.createSequentialGroup()
            .addComponent(load)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(save)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
        .addContainerGap())
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

    private void loadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadActionPerformed
        try {
            String all = new String( Files.readAllBytes( Paths.get( PATH ) ) );
        } catch ( IOException ex ) {
           JOptionPane.showMessageDialog( null, "Shit happened: " + ex.getLocalizedMessage() ); 
        }

        // Exercise: Implement the decryption part...
        String decryptedMsg = "This is the exercise to do this";
        editText.setText( decryptedMsg );

    }//GEN-LAST:event_loadActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        try {
            //Use non-blocking SecureRandom implementation for the new IV.
            final SecureRandom secureRandom = new SecureRandom();
            byte[] ivBytes = new byte[ 16 ];

            //Generate a new IV.
            secureRandom.nextBytes( ivBytes );
            byte[] keyBytes = String2Key.string2KeyArray( pwField.getText() );
            byte[] decryptedBytes = editText.getText().getBytes();

            String newStoredCipher = Base64.getEncoder().encodeToString( ivBytes )
                    + ":" + Base64.getEncoder().encodeToString( AESGoodImplementation.encrypt( keyBytes, ivBytes, decryptedBytes ) );
            Files.write( Paths.get( PATH ), newStoredCipher.getBytes() );
        } catch ( IOException | InvalidKeyException | InvalidAlgorithmParameterException ex ) {
            JOptionPane.showMessageDialog( null, "Shit happened: " + ex.getLocalizedMessage() );
        }
    }//GEN-LAST:event_saveActionPerformed

    /**
     @param args the command line arguments
     */
    public static void main( String args[] ) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for ( javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels() ) {
                if ( "Nimbus".equals( info.getName() ) ) {
                    javax.swing.UIManager.setLookAndFeel( info.getClassName() );
                    break;
                }
            }
        } catch ( ClassNotFoundException ex ) {
            java.util.logging.Logger.getLogger( Exercise3CryptoEdit.class.getName() ).log( java.util.logging.Level.SEVERE, null, ex );
        } catch ( InstantiationException ex ) {
            java.util.logging.Logger.getLogger( Exercise3CryptoEdit.class.getName() ).log( java.util.logging.Level.SEVERE, null, ex );
        } catch ( IllegalAccessException ex ) {
            java.util.logging.Logger.getLogger( Exercise3CryptoEdit.class.getName() ).log( java.util.logging.Level.SEVERE, null, ex );
        } catch ( javax.swing.UnsupportedLookAndFeelException ex ) {
            java.util.logging.Logger.getLogger( Exercise3CryptoEdit.class.getName() ).log( java.util.logging.Level.SEVERE, null, ex );
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater( new Runnable() {
            public void run() {
                new Exercise3CryptoEdit().setVisible( true );
            }
        } );
    }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JTextArea editText;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JButton load;
  private javax.swing.JTextField pwField;
  private javax.swing.JButton save;
  // End of variables declaration//GEN-END:variables
}
