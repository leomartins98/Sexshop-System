/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.awt.event.ActionListener;

/**
 *
 * @author LeonardoMartins
 */
public class TelaCadastroColab extends javax.swing.JFrame {

  /**
   * Creates new form TelaCadastroColab
   */
  public TelaCadastroColab() {
    initComponents();
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {
    jPanel2 = new javax.swing.JPanel();
    jLabel4 = new javax.swing.JLabel();
    jLabel5 = new javax.swing.JLabel();
    jTextField4 = new javax.swing.JTextField();
    jTextField5 = new javax.swing.JTextField();
    jLabel6 = new javax.swing.JLabel();
    jTextField6 = new javax.swing.JTextField();
    jRadioButton3 = new javax.swing.JRadioButton();
    jRadioButton4 = new javax.swing.JRadioButton();
    jButton1 = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setMinimumSize(new java.awt.Dimension(340, 130));
    setSize(new java.awt.Dimension(340, 130));

    jLabel4.setText("Nome completo:");

    jLabel5.setText("Nome de usuário: ");

    jLabel6.setText("Senha:");

    jTextField6.addActionListener(
      new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
          jTextField3ActionPerformed(evt);
        }
      }
    );

    jRadioButton3.setText("Funcionário");

    jRadioButton4.setText("Administrador");

    jButton1.setText("Cadastrar");
    jButton1.addActionListener(
      new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
          jButton1ActionPerformed(evt);
        }
      }
    );

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(
      jPanel2
    );
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
      jPanel2Layout
        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(
          jPanel2Layout
            .createSequentialGroup()
            .addGroup(
              jPanel2Layout
                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(
                  jPanel2Layout
                    .createSequentialGroup()
                    .addContainerGap()
                    .addGroup(
                      jPanel2Layout
                        .createParallelGroup(
                          javax.swing.GroupLayout.Alignment.TRAILING,
                          false
                        )
                        .addComponent(
                          jLabel6,
                          javax.swing.GroupLayout.DEFAULT_SIZE,
                          javax.swing.GroupLayout.DEFAULT_SIZE,
                          Short.MAX_VALUE
                        )
                        .addComponent(
                          jLabel4,
                          javax.swing.GroupLayout.Alignment.LEADING
                        )
                        .addComponent(
                          jLabel5,
                          javax.swing.GroupLayout.DEFAULT_SIZE,
                          javax.swing.GroupLayout.DEFAULT_SIZE,
                          Short.MAX_VALUE
                        )
                    )
                    .addPreferredGap(
                      javax.swing.LayoutStyle.ComponentPlacement.UNRELATED
                    )
                    .addGroup(
                      jPanel2Layout
                        .createParallelGroup(
                          javax.swing.GroupLayout.Alignment.LEADING,
                          false
                        )
                        .addComponent(
                          jTextField5,
                          javax.swing.GroupLayout.DEFAULT_SIZE,
                          211,
                          Short.MAX_VALUE
                        )
                        .addComponent(jTextField4)
                        .addComponent(jTextField6)
                    )
                )
                .addGroup(
                  jPanel2Layout
                    .createSequentialGroup()
                    .addGap(64, 64, 64)
                    .addComponent(jRadioButton3)
                    .addPreferredGap(
                      javax.swing.LayoutStyle.ComponentPlacement.RELATED
                    )
                    .addComponent(jRadioButton4)
                )
                .addGroup(
                  jPanel2Layout
                    .createSequentialGroup()
                    .addGap(120, 120, 120)
                    .addComponent(jButton1)
                )
            )
            .addContainerGap(
              javax.swing.GroupLayout.DEFAULT_SIZE,
              Short.MAX_VALUE
            )
        )
    );
    jPanel2Layout.setVerticalGroup(
      jPanel2Layout
        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(
          jPanel2Layout
            .createSequentialGroup()
            .addContainerGap()
            .addGroup(
              jPanel2Layout
                .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(
                  jLabel4,
                  javax.swing.GroupLayout.PREFERRED_SIZE,
                  24,
                  javax.swing.GroupLayout.PREFERRED_SIZE
                )
                .addComponent(
                  jTextField4,
                  javax.swing.GroupLayout.PREFERRED_SIZE,
                  javax.swing.GroupLayout.DEFAULT_SIZE,
                  javax.swing.GroupLayout.PREFERRED_SIZE
                )
            )
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(
              jPanel2Layout
                .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel5)
                .addComponent(
                  jTextField5,
                  javax.swing.GroupLayout.PREFERRED_SIZE,
                  javax.swing.GroupLayout.DEFAULT_SIZE,
                  javax.swing.GroupLayout.PREFERRED_SIZE
                )
            )
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(
              jPanel2Layout
                .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel6)
                .addComponent(
                  jTextField6,
                  javax.swing.GroupLayout.PREFERRED_SIZE,
                  javax.swing.GroupLayout.DEFAULT_SIZE,
                  javax.swing.GroupLayout.PREFERRED_SIZE
                )
            )
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(
              jPanel2Layout
                .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jRadioButton4)
                .addComponent(jRadioButton3)
            )
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jButton1)
            .addContainerGap(
              javax.swing.GroupLayout.DEFAULT_SIZE,
              Short.MAX_VALUE
            )
        )
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
      getContentPane()
    );
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout
        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(
          javax.swing.GroupLayout.Alignment.TRAILING,
          layout
            .createSequentialGroup()
            .addContainerGap(
              javax.swing.GroupLayout.DEFAULT_SIZE,
              Short.MAX_VALUE
            )
            .addComponent(
              jPanel2,
              javax.swing.GroupLayout.DEFAULT_SIZE,
              javax.swing.GroupLayout.DEFAULT_SIZE,
              Short.MAX_VALUE
            )
            .addContainerGap()
        )
    );
    layout.setVerticalGroup(
      layout
        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(
          javax.swing.GroupLayout.Alignment.TRAILING,
          layout
            .createSequentialGroup()
            .addContainerGap(
              javax.swing.GroupLayout.DEFAULT_SIZE,
              Short.MAX_VALUE
            )
            .addComponent(
              jPanel2,
              javax.swing.GroupLayout.DEFAULT_SIZE,
              javax.swing.GroupLayout.DEFAULT_SIZE,
              Short.MAX_VALUE
            )
            .addContainerGap()
        )
    );

    pack();
  } // </editor-fold>//GEN-END:initComponents

  private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {}

  private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_jButton1ActionPerformed
    // TODO add your handling code here:
  } //GEN-LAST:event_jButton1ActionPerformed

  public void addCadastrarListener(ActionListener l) {
    jButton1.addActionListener(l);
  }

  public void clearView() {
    jTextField4.setText("");
    jTextField5.setText("");
    jTextField6.setText("");
    jRadioButton3.setSelected(false);
    jRadioButton4.setSelected(false);
  }

  public String getUser() {
    return jTextField4.getText();
  }

  public String getUsername() {
    return jTextField5.getText();
  }

  public String getPassword() {
    return jTextField6.getText();
  }

  public boolean getAdm() {
    if (jRadioButton3.isSelected()) return false;

    if (jRadioButton4.isSelected()) return true;

    return false;
  }

  public void clear() {
    jTextField4.setText("");
    jTextField5.setText("");
    jTextField6.setText("");
    jRadioButton3.setSelected(false);
    jRadioButton4.setSelected(false);
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton jButton1;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JLabel jLabel5;
  private javax.swing.JLabel jLabel6;
  private javax.swing.JPanel jPanel2;
  private javax.swing.JRadioButton jRadioButton3;
  private javax.swing.JRadioButton jRadioButton4;
  private javax.swing.JTextField jTextField4;
  private javax.swing.JTextField jTextField5;
  private javax.swing.JTextField jTextField6;
  // End of variables declaration//GEN-END:variables
}
