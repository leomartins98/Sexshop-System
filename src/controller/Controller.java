package controller;

import java.awt.event.*;
import view.TelaLogin;
import credencial.*;

public class Controller {
    private TelaLogin loginView;
    private Credenciais credenciais;

    public Controller(TelaLogin login, Credenciais credenciais) {
        this.loginView = login;
        this.credenciais = credenciais;
        
        this.loginView.addLoginListener(new LoginListener());
        this.loginView.addRegisterListener(new RegisterListener());
    }

    public void execute() {
        this.loginView.setVisible(true);
    }
        
    class LoginListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String username = loginView.getUsername();
            String password = loginView.getPassword();
            
            var c = credenciais.find(username);
            if(!c.isValid())
            {
                // TODO: View-usuario nao encontrado.
                System.out.println("Usuario nao encontrado");
                return;
            }
            
            if(!c.senha.equals(password))
            {
                // TODO: View-Senha incorreta.
                System.out.println("Senha incorreta");
                return;
            }
            
            // Esconde a view de login:
            //loginView.setVisible(false);
            
            if(c.administrador == true) {
                //gerenteView.setVisible(true);
                System.out.println("Acesso concedido. Alterando para view de Gerente.");
            } else { 
                //funcionarioView.setVisible(true);
                System.out.println("Acesso concedido. Alterando para view de Funcionário.");
            }
        }
    }

    class RegisterListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String username = loginView.getUsername();
            String password = loginView.getPassword();

            var c = credenciais.find(username);
            if(c.isValid())
            {
                // TODO: View-usuario encontrado.
                System.out.println("Usuário já cadastrado.");
                return;
            }
        
            // Gerentes devem ser setados manualmente:
            credenciais.adicionarCredencial(username, password, false);
            credenciais.salvarCredenciais();

            // Após o cadastro, passa diretamente para tela de funcionário:
            // loginView.setVisible(false);
            // funcionarioView.setVisible(true);

            System.out.println("Usuário inserido com sucesso. Navegando para View de funcionários.");
        }
    }
}
