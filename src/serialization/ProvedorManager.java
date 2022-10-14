package serialization;

import loja.Provedor;

public class ProvedorManager extends SerializationManager<Provedor> {

    private static Integer databaseAmount = 1;

    public ProvedorManager(String filepath) {
        super(filepath, "provedor-" + databaseAmount.toString());
        ProvedorManager.databaseAmount++;
    }
    
}
