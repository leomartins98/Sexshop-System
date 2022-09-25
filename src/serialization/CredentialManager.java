package serialization;

import credencial.Credencial;

public class CredentialManager extends SerializationManager<Credencial> {

    private static Integer databaseAmount = 1;

    public CredentialManager(String filepath) {
        super(filepath, "credentials-" + databaseAmount.toString());
        CredentialManager.databaseAmount++;
    }
}
