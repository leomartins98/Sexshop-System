import serialization.ClientManager;
import serialization.CredentialManager;
import serialization.ItemManager;
import serialization.ProvedorManager;
import controller.Controller;

import view.TelaAdmin;
import view.TelaLogin;

public class Main {
	public static void main(String[] args) {
		// Bancos de dados:
		CredentialManager credenciais = new CredentialManager("res/credentials.obj");
		ItemManager produtos = new ItemManager("res/produtos.obj");
		ProvedorManager provedores = new ProvedorManager("res/provedores.obj");
		ClientManager clientes = new ClientManager("res/clientes.obj");

		// Views:
		TelaLogin loginView = new TelaLogin();
		TelaAdmin adminView = new TelaAdmin();

		Controller controller = new Controller(loginView, adminView, credenciais, produtos, provedores, clientes);
		controller.execute();
	}
}
