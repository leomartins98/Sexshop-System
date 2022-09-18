package loja;

import java.util.ArrayList;

public class Inventario {
    private ArrayList<Item> itensDisponiveis;

    public Inventario() {
        itensDisponiveis = new ArrayList<Item>();
    }

    public void addItem(Produto produto, int quantidade) {
        itensDisponiveis.add(new Item(produto, quantidade));
    }

    public boolean emEstoque(String nomeProduto, int quantidade) {
        for(Item estoque : itensDisponiveis) {
            if(estoque.getProduto().getNome().equals(nomeProduto)) {
                return quantidade > estoque.getQuantidade();
            }
        }
        return false;
    }
}
