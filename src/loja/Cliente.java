package loja;

import java.io.Serializable;

public class Cliente implements Serializable {
    private static final long serialVersionUID = 95L;

	public String nome;
    public String cpf;
    public String phone;

    public Cliente(String nome, String cpf, String phone) {
        this.nome = nome;
        this.cpf = cpf;
        this.phone = phone;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
}
