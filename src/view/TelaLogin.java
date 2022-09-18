/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.awt.event.*;

/**
 *
 * @author gugarauj07
 */
public class TelaLogin extends javax.swing.JFrame {

    /**
     * Creates new form TelaLogin
     */
    public TelaLogin() {
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

        sistemaPanel = new javax.swing.JPanel();
        acessoSistemaLabel = new javax.swing.JLabel();
        usernameTextFIeld = new javax.swing.JTextField();
        passwordTextField = new javax.swing.JTextField();
        loginButton = new javax.swing.JButton();
        usernameLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        registerButton = new javax.swing.JButton();
        jHorizontalSep = new javax.swing.JSeparator();
        jIcon = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 204, 204));
        setPreferredSize(new java.awt.Dimension(700, 428));
        setResizable(false);

        sistemaPanel.setBackground(new java.awt.Color(102, 102, 102));
        sistemaPanel.setForeground(new java.awt.Color(255, 204, 204));

        acessoSistemaLabel.setFont(new java.awt.Font("Ebrima", 1, 28)); // NOI18N
        acessoSistemaLabel.setForeground(new java.awt.Color(255, 255, 255));
        acessoSistemaLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        acessoSistemaLabel.setText("Acesso ao Sistema");
        acessoSistemaLabel.setToolTipText("");
        acessoSistemaLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        usernameTextFIeld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameTextFIeldActionPerformed(evt);
            }
        });

        loginButton.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        loginButton.setForeground(new java.awt.Color(102, 102, 102));
        loginButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        loginButton.setLabel("LOGIN");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        usernameLabel.setForeground(new java.awt.Color(255, 255, 255));
        usernameLabel.setText("Username:");

        passwordLabel.setForeground(new java.awt.Color(255, 255, 255));
        passwordLabel.setText("Password:");

        registerButton.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        registerButton.setForeground(new java.awt.Color(102, 102, 102));
        registerButton.setText("REGISTER");
        registerButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        registerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerButtonActionPerformed(evt);
            }
        });

        jIcon.setIcon(new javax.swing.ImageIcon("C:\\Users\\paulo\\Desktop\\Sexshop-System\\images\\logo.jpg")); // NOI18N

        javax.swing.GroupLayout sistemaPanelLayout = new javax.swing.GroupLayout(sistemaPanel);
        sistemaPanel.setLayout(sistemaPanelLayout);
        sistemaPanelLayout.setHorizontalGroup(
            sistemaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sistemaPanelLayout.createSequentialGroup()
                .addGroup(sistemaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sistemaPanelLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(sistemaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(passwordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(passwordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(usernameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(usernameTextFIeld, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(sistemaPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(acessoSistemaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(sistemaPanelLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(sistemaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(loginButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(registerButton, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jHorizontalSep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jIcon)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        sistemaPanelLayout.setVerticalGroup(
            sistemaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jHorizontalSep)
            .addGroup(sistemaPanelLayout.createSequentialGroup()
                .addComponent(acessoSistemaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usernameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usernameTextFIeld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(passwordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passwordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(registerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(sistemaPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sistemaPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void usernameTextFIeldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameTextFIeldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameTextFIeldActionPerformed

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        
    }//GEN-LAST:event_loginButtonActionPerformed

    private void registerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_registerButtonActionPerformed

    /*
     * Listeners:
     */
    public void addLoginListener(ActionListener e) {
        loginButton.addActionListener(e);
    }

    public void addRegisterListener(ActionListener e) {
        registerButton.addActionListener(e);
    }
    
    /*
     * Getters:
     */
    public String getUsername() { 
        return usernameTextFIeld.getText();
    }
    
    public String getPassword() { 
        return passwordTextField.getText();
    }
            
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel acessoSistemaLabel;
    private javax.swing.JSeparator jHorizontalSep;
    private javax.swing.JLabel jIcon;
    private javax.swing.JButton loginButton;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JTextField passwordTextField;
    private javax.swing.JButton registerButton;
    private javax.swing.JPanel sistemaPanel;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JTextField usernameTextFIeld;
    // End of variables declaration//GEN-END:variables
}
