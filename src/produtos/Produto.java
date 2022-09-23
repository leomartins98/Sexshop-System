package produtos;

import java.io.Serializable;

public class Produto implements Serializable {

	private static final long serialVersionUID = 12L;

	private Integer id;

	private String nome;
	private float preco;
	private String descricao;

	public Produto(int id, String nome, float preco, String descricao) {
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String toString() {
		return this.nome + " | $" + this.preco + " | " + this.descricao;
	}

	public Integer getID() {
		return this.id;
	}
}
