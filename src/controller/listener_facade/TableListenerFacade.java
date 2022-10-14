package controller.listener_facade;

import javax.swing.event.TableModelListener;
import javax.swing.event.TableModelEvent;
import view.TelaAdmin;

import credencial.Credencial;
import loja.Produto;
import loja.Cliente;
import loja.Item;
import serialization.ClientManager;
import serialization.CredentialManager;
import serialization.ItemManager;
import serialization.ProvedorManager;

public class TableListenerFacade {

    private TelaAdmin adminView;

    protected CredentialManager credenciais;
    protected ItemManager items;
	protected ProvedorManager provedores;
	protected ClientManager clientes;

    public TableListenerFacade(TelaAdmin adminView, CredentialManager credenciais, ItemManager items, ProvedorManager provedores, ClientManager clientes) {
        this.adminView = adminView;
        this.credenciais = credenciais;
        this.items = items;
		this.provedores = provedores;
		this.clientes = clientes;
    }

    public void execute() {
        this.adminView.addWorkerTableModelListener(new WorkerTableModelListener());
		this.adminView.addProductTableModelListener(new ProductTableModelListener());
		this.adminView.addClientTableModelListener(new ClientTableModelListener());
    }

    class WorkerTableModelListener implements TableModelListener {

		@Override
		public void tableChanged(TableModelEvent e) {
			int row = adminView.getColabTable().getSelectedRow();
			if (row < 0)
				return;

			String[] rowAtual = adminView.getWorkerRowAt(row);
			if(rowAtual == null)
				return;

			boolean administrador = rowAtual[3].toLowerCase().equals("administrador") ? true : false;

			var nome = rowAtual[0];
			var username = rowAtual[1];
			var password = rowAtual[2];

			credenciais.update("usuario", nome, new Credencial(nome, username, password, administrador));
			credenciais.save();
		}
	
	}

	class ProductTableModelListener implements TableModelListener {

		@Override
		public void tableChanged(TableModelEvent e) {
			int row = adminView.getProductTable().getSelectedRow();
			if (row < 0)
				return;

			String[] rowAtual = adminView.getProductRowAt(row);
			if(rowAtual == null)
				return;

			var id = Integer.parseInt(rowAtual[0]);
			var nome = rowAtual[1];
			var preco =  Float.parseFloat(rowAtual[2]);
			var desc = rowAtual[3];
			var qtd = Integer.parseInt(rowAtual[4]);

			for(var c : items.getClass().getFields()) {
				System.out.println(c.getName());
			}

			items.update("id", rowAtual[0], new Item(new Produto(id, nome, preco, desc), qtd));
			items.save();
		}
	
	}

	class ClientTableModelListener implements TableModelListener {

		@Override
		public void tableChanged(TableModelEvent e) {
			int row = adminView.getProductTable().getSelectedRow();
			if (row < 0)
				return;

			String[] rowAtual = adminView.getProductRowAt(row);
			if(rowAtual == null)
				return;

			var nome = rowAtual[1];
			var cpf =  rowAtual[2];
			var phone =  rowAtual[3];

			clientes.update("nome", rowAtual[1], new Cliente(nome, cpf, phone));
			clientes.save();
		}
	
	}
}
