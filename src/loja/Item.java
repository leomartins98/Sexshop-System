package loja;

public class Item {
    Produto prod;
    private Integer qtdItens;

    public Item(Produto produto, int quantidade) {
        this.prod = produto;
        this.qtdItens = quantidade;
    }

    public Produto getProd() {
        return prod;
    }

    public void setProd(Produto prod) {
        this.prod = prod;
    }

    public int getQtdItens() {
        return qtdItens;
    }

    public void setQtdItens(int qtdItens) {
        this.qtdItens = qtdItens;
    }
}

