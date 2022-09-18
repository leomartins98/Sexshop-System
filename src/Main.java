import controller.Controller;
import view.TelaLogin;
import credencial.*;

public class Main {
    public static void main(String[] args) {
        Credenciais credenciais = new Credenciais("res/credenciais.txt");

        // DEBUG:
        for(Credencial c : credenciais.getCredenciais()) {
            System.out.println(c.toString());
        }
        
        TelaLogin loginView = new TelaLogin();
        loginView.setVisible(true);

        Controller controller = new Controller(loginView, credenciais);
    }
}
