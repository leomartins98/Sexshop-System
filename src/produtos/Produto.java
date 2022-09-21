package produtos;

public class Produto {

	private static int incremento = 0;
	private int id;
	private String nome;
	private float preco;
	private String descricao;

	public Produto(String nome, float preco, String descricao) {
		this.id = incremento++;
		this.nome = nome;
		this.preco = preco;
		this.descricao = descricao;
	}

	public Produto(int id, String nome, float preco, String descricao) {
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.descricao = descricao;
	}

	public int getID() {
		return id;
	}

	public void setID(int id) {
		this.id = id;
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

	public static void setIncremento(int incremento) {
		Produto.incremento = incremento;
	}

	public static int getIncremento() {
		return incremento;
	}
}
