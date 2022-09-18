package credencial;

public class Credencial
{
    public String usuario;
    public String senha;
    public boolean administrador;

    public Credencial(String usuario, String senha, boolean administrador)
    {
        this.usuario = usuario;
        this.senha = senha;
        this.administrador = administrador;
    }

    public String toString() {
        String isAdm = this.administrador ? "Sim" : "Nao";
        return "Usuario: " + this.usuario + " | Senha: " + this.senha + " | Administrador: " + isAdm;
    }
}