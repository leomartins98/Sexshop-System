import controller.Controller;

import view.TelaAdmin;
import view.TelaLogin;

import credencial.*;

public class Main {
    public static void main(String[] args) {
        // Credenciais:
        Credenciais credenciais = new Credenciais("res/credenciais.txt");

        // DEBUG:
        for(Credencial c : credenciais.getCredenciais())
            System.out.println(c.toString());
        
        // VIEWS:
        TelaLogin loginView = new TelaLogin();
        TelaAdmin adminView = new TelaAdmin();

        Controller controller = new Controller(loginView, adminView, credenciais);
        controller.execute();
    }
}
