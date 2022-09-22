import controller.Controller;
import credencial.*;
import produtos.BancoDadosProdutos;
import serialization.SerializationManager;
import view.TelaAdmin;
import view.TelaLogin;

public class Main {
	public static void main(String[] args) {
		// Bancos de dados:
		SerializationManager<Credencial> credenciais = new SerializationManager<Credencial>("res/credentials.obj");
		//SerializationManager<Credencial> produtos = new SerializationManager<Credencial>("res/produtos.obj");

		// Views:
		TelaLogin loginView = new TelaLogin();
		TelaAdmin adminView = new TelaAdmin();

		// Controller controller = new Controller(
		// 		loginView,
		// 		adminView,
		// 		credenciais,
		// 		produtos);
			
		//controller.execute();
	}
}
