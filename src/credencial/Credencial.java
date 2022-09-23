package credencial;

import java.io.Serializable;

public class Credencial implements Serializable
{
    private static final long serialVersionUID = 49L;

    public String nome;
    public String usuario;
    public String senha;
    public boolean administrador;

    public Credencial(String nome, String usuario, String senha, boolean administrador)
    {
        this.nome = nome;
        this.usuario = usuario;
        this.senha = senha;
        this.administrador = administrador;
    }

    public String toString() {
        String isAdm = this.administrador ? "Sim" : "Nao";
        return "Nome: "  + this.nome + " | Usuario: " + this.usuario + " | Senha: " + this.senha + " | Administrador: " + isAdm;
    }
}