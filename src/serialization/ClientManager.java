package serialization;

import credencial.Credencial;
import loja.Cliente;

public class ClientManager extends SerializationManager<Cliente> {

    private static Integer databaseAmount = 1;

    public ClientManager(String filepath) {
        super(filepath, "client-" + databaseAmount.toString());
        ClientManager.databaseAmount++;
    }
}
