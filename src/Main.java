import serialization.SerializationManager;
import controller.Controller;
import credencial.*;
import loja.Item;

import view.TelaAdmin;
import view.TelaLogin;

public class Main {
	public static void main(String[] args) {
		// Bancos de dados:
		SerializationManager<Credencial> credenciais = new SerializationManager<Credencial>("res/credentials.obj", "credentials");
		SerializationManager<Item> produtos = new SerializationManager<Item>("res/produtos.obj", "products");

		// Views:
		TelaLogin loginView = new TelaLogin();
		TelaAdmin adminView = new TelaAdmin();

		Controller controller = new Controller(
			loginView,
			adminView,
			credenciais,
			produtos);
			
		controller.execute();
	}
}
