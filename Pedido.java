import java.util.ArrayList;

public class Pedido {
    
  private TipoPagamento tipoDePagamento;
  private double totalPedido = 0;
  private ArrayList<Item> itens;

  public Pedido(TipoPagamento tipoPgt) {
    itens = new ArrayList<Item>();
    this.tipoDePagamento = tipoPgt;
  }

  public TipoPagamento getTipoPgt() {
    return this.tipoDePagamento;
  }

  public void setTipoPgt(TipoPagamento tipoPgt) {
    this.tipoDePagamento = tipoPgt;
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

  public void setItens(ArrayList<Item> itens) {
    this.itens = itens;
  }
}
