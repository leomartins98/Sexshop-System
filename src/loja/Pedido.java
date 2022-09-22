package loja;

import java.util.ArrayList;

public class Pedido {

    private TipoPagamento tipoDePagamento;
    private double totalPedido = 0;
    private ArrayList<Item> itens;

    public Pedido(TipoPagamento tipoPagamento) {
        itens = new ArrayList<Item>();
        this.tipoDePagamento = tipoPagamento;
    }

    public TipoPagamento getTipoPagamento() {
        return this.tipoDePagamento;
    }

    public void setTipoPagamento(TipoPagamento tipoPagamento) {
        this.tipoDePagamento = tipoPagamento;
    }

    public double getTotalPedido() {
        return this.totalPedido;
    }

    public void setTotalPedido(double totalPedido) {
        this.totalPedido = totalPedido;
    }

    public ArrayList<Item> getItens() {
        return this.itens;
    }

    public void addItem(Item item) {
        this.itens.add(item);
    }
}
