package controller.listener_facade;

import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import credencial.Credencial;
import serialization.CredentialManager;
import view.TelaAdmin;
import view.TelaCadastroVenda;
import view.TelaLogin;


public class LoginListenerFacade {

    protected TelaLogin loginView;
    protected TelaAdmin adminView;
    protected CredentialManager credenciais;

    private TelaCadastroVenda cadastroVenda;

    public LoginListenerFacade(TelaLogin loginView, TelaAdmin adminView, CredentialManager credenciais) {
        this.loginView = loginView;
        this.adminView = adminView;
        this.credenciais = credenciais;

        this.initializeView();
    }

    public void execute() {
        this.loginView.addLoginListener(new LoginListener());
    }

    private void initializeView() {
        this.cadastroVenda = new TelaCadastroVenda();
		this.cadastroVenda.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.cadastroVenda.setLocationRelativeTo(null);
    }

    private class LoginListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String username = loginView.getUsername();
			String password = loginView.getPassword();

			Credencial c = credenciais.find("usuario", username);

			if (c == null) {
				JOptionPane.showMessageDialog(loginView,
				"O usuário " + username + " não está cadastrado no banco de dados. Verifique com um administrador.",
				"Erro de Autenticação",
				JOptionPane.ERROR_MESSAGE);
				return;
			}

			if (!c.senha.equals(password)) {
				JOptionPane.showMessageDialog(loginView,
				"Senha incorreta. Tente novamente.",
				"Erro de Autenticação",
				JOptionPane.ERROR_MESSAGE);
				return;
			}

			// Esconde a view de login:
			loginView.setVisible(false);

			if (c.administrador == true) {
				adminView.setVisible(true);
				adminView.setCollaboratorName(toTitleCase(username));
				adminView.vendedor = toTitleCase(username);
			} else 
				cadastroVenda.setVisible(true);
		}
	}

	// TODO: move to a utils class.
	private static String toTitleCase(String input) {
		StringBuilder titleCase = new StringBuilder(input.length());
		boolean nextTitleCase = true;
	
		for (char c : input.toCharArray()) {
			if (Character.isSpaceChar(c)) {
				nextTitleCase = true;
			} else if (nextTitleCase) {
				c = Character.toTitleCase(c);
				nextTitleCase = false;
			}
	
			titleCase.append(c);
		}
	
		return titleCase.toString();
	}
}
