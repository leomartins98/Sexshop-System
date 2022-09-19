package loja;

import java.util.ArrayList;

public class Inventario {
    private ArrayList<Item> itensDisponiveis;

    public Inventario() {
        itensDisponiveis = new ArrayList<Item>();
    }

    public void addItem(Produto produto, int quantidade) {
        for(Item item : itensDisponiveis) {
            if(produto.getNome().equals(item.getProduto().getNome())) {
                item.addQuantidade(quantidade);
                return;
            }
        }
        
        itensDisponiveis.add(new Item(produto, quantidade));
    }

    public void removeItem(String nomeProduto, int quantidade) {
        for(Item item : itensDisponiveis) {
            if(nomeProduto.equals(item.getProduto().getNome())) {
                item.subQuantidade(quantidade);
                return;
            }
        }
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
