package controller;

import view.TelaLogin;
import view.TelaAdmin;

import java.awt.event.*;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import credencial.*;

public class Controller {
    private TelaLogin loginView;
    private TelaAdmin adminView;

    private Credenciais credenciais;

    public Controller(TelaLogin login, TelaAdmin adminView, Credenciais credenciais) {
        this.loginView = login;
        this.adminView = adminView;

        this.credenciais = credenciais;
        
        this.loginView.addLoginListener(new LoginListener());
        this.loginView.addRegisterListener(new RegisterListener());

        for(Credencial c : this.credenciais.getCredenciais())
            this.adminView.adicionarFuncionarioNaTabela(c.usuario, c.usuario, c.senha, c.administrador);

        this.adminView.addListenerToTable(new FuncionarioListener());
    }

    public void execute() {
        this.adminView.setVisible(true);
    }
        
    class LoginListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String username = loginView.getUsername();
            String password = loginView.getPassword();
            
            var c = credenciais.find(username);

            if(c == null || !c.isValid())
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
            if(c == null)
                return;

            if(c.isValid())
            {
                // TODO: View-usuario encontrado.
                System.out.println("Usuário já cadastrado.");
                return;
            }
        
            // Gerentes devem ser setados manualmente:
            credenciais.adicionarCredencial(username, username, password, false);
            credenciais.salvarCredenciais();

            // Após o cadastro, passa diretamente para tela de funcionário:
            // loginView.setVisible(false);
            // funcionarioView.setVisible(true);

            System.out.println("Usuário inserido com sucesso. Navegando para View de funcionários.");
        }
    }

    class FuncionarioListener implements TableModelListener {

        @Override
        public void tableChanged(TableModelEvent e) {
            int row = adminView.getColabTable().getSelectedRow();

            String[] rowAtual = adminView.getRowAt(row);
            boolean administrador = rowAtual[3].toLowerCase() == "administrador" ? true : false;
            credenciais.update(rowAtual[0], new Credencial(rowAtual[0], rowAtual[1], rowAtual[2], administrador));
            credenciais.salvarCredenciais();
        }

    }
}
