import controller.Controller;
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

        Controller controller = new Controller(loginView, credenciais);
        controller.execute();
    }
}
