import controller.Controller;

import view.TelaAdmin;
import view.TelaLogin;

import credencial.*;
import produtos.BancoDadosProdutos;

public class Main {
    public static void main(String[] args) {
        // // Credenciais:
        Credenciais credenciais = new Credenciais("res/credenciais.txt");
        BancoDadosProdutos bancoDadosProdutos = new BancoDadosProdutos("res/produtos.txt");
        // // VIEWS:
        TelaLogin loginView = new TelaLogin();
        TelaAdmin adminView = new TelaAdmin();

        Controller controller = new Controller(loginView, adminView, credenciais, bancoDadosProdutos);
        controller.execute();
    }
}
