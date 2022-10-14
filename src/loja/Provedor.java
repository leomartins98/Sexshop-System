package loja;

import java.io.Serializable;

public class Provedor implements Serializable {
    private static final long serialVersionUID = 95L;

	public String nome;

    public Provedor(String nome) {
        this.nome = nome;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
}
