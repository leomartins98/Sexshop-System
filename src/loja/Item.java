package loja;

import produtos.Produto;
import java.io.Serializable;

public class Item implements Serializable {
    private static final long serialVersionUID = 10L;

    private Produto produto;
    private Integer quantidade;

    public Item(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto prod) {
        this.produto = prod;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void addQuantidade(int quantidade) {
        this.quantidade += quantidade;
    }

    public void subQuantidade(int quantidade) {
        this.quantidade -= quantidade;
    }

    public String toString() {
        return this.produto.toString() + " | Quantidade: " + this.getQuantidade();
    }
}
