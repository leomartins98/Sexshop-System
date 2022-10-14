package controller.listener_facade;

import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import view.TelaAdmin;
import serialization.ClientManager;
import serialization.CredentialManager;
import serialization.ItemManager;
import serialization.ProvedorManager;

public class RemoveListenerFacade {
    private TelaAdmin adminView;

    protected CredentialManager credenciais;
    protected ItemManager items;
	protected ProvedorManager provedores;
	protected ClientManager clientes;
    
    public RemoveListenerFacade(TelaAdmin adminView, CredentialManager credenciais, ItemManager items, ProvedorManager provedores, ClientManager clientes){
        this.adminView = adminView;
        this.credenciais = credenciais;
        this.items = items;
		this.provedores = provedores;
		this.clientes = clientes;
    }

    public void execute() {
        this.adminView.addUserRemoveListener(new UserRemoveListener());
		this.adminView.addProductRemoveListener(new ProductRemoveListener());
		this.adminView.addProviderRemoveListener(new ProviderRemoveListener());
		this.adminView.addClientRemoveListener(new ClientRemoveListener());
    }
    
    // Listeners:
    class UserRemoveListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int row = adminView.getColabTable().getSelectedRow();
			if (row < 0)
				return;

			String[] rowAtual = adminView.getWorkerRowAt(row);

			credenciais.remove("usuario", rowAtual[0]);
			credenciais.save();
			
			((DefaultTableModel)adminView.getColabTable().getModel()).removeRow(row);
		}

	}

	class ProviderRemoveListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int row = adminView.getProviderTable().getSelectedRow();
			if (row < 0)
				return;

			String[] rowAtual = adminView.getProviderRowAt(row);
			
			provedores.remove("nome", rowAtual[1]);
			provedores.save();
			
			DefaultTableModel model = (DefaultTableModel)adminView.getProviderTable().getModel();
			model.removeRow(row);
		}
	}

	class ProductRemoveListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int row = adminView.getProductTable().getSelectedRow();
			if (row < 0)
				return;

			String[] rowAtual = adminView.getProductRowAt(row);
			
			items.remove("id", rowAtual[0]);
			items.save();
			
			DefaultTableModel model = (DefaultTableModel)adminView.getProductTable().getModel();
			model.removeRow(row);
		}
	}

	class ClientRemoveListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int row = adminView.getClientTable().getSelectedRow();
			if (row < 0)
				return;

			String[] rowAtual = adminView.getClientRowAt(row);
			
			clientes.remove("nome", rowAtual[1]);
			clientes.save();
			
			DefaultTableModel model = (DefaultTableModel)adminView.getClientTable().getModel();
			model.removeRow(row);
		}
	}
}
