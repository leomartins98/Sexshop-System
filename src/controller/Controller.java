package controller;

import controller.listener_facade.LoginListenerFacade;
import controller.listener_facade.PopupListenerFacade;
import controller.listener_facade.RemoveListenerFacade;
import controller.listener_facade.TableListenerFacade;
import serialization.ClientManager;
import serialization.CredentialManager;
import serialization.ItemManager;
import serialization.ProvedorManager;
import credencial.*;
import loja.Cliente;
import loja.Item;
import loja.Provedor;
import view.TelaAdmin;
import view.TelaLogin;

public class Controller {

	// Views:
	private TelaLogin loginView;
	private TelaAdmin adminView;

	// Models:
	private CredentialManager credenciais;
	private ItemManager itemsLoja;
	private ProvedorManager provedores;
	private ClientManager clientes;

	// Facades:
	private RemoveListenerFacade removeListenerFacade;
	private TableListenerFacade tableListenerFacade;
	private LoginListenerFacade loginListenerFacade;
	private PopupListenerFacade popupListenerFacade;

	public Controller(TelaLogin login, TelaAdmin adminView, CredentialManager credenciais, ItemManager itemsLoja, ProvedorManager provedores, ClientManager clientes) {
		this.loginView = login;
		this.adminView = adminView;
		this.provedores = provedores;
		this.clientes = clientes;

		this.itemsLoja = itemsLoja;
		this.credenciais = credenciais;

		this.initializeViews();
		this.initializeModels();

		// Facades:
		removeListenerFacade = new RemoveListenerFacade(adminView, credenciais, itemsLoja, provedores, clientes);
		tableListenerFacade = new TableListenerFacade(adminView, credenciais, itemsLoja, provedores, clientes);
		loginListenerFacade = new LoginListenerFacade(loginView, adminView, credenciais);
		popupListenerFacade = new PopupListenerFacade(adminView, loginView, this.provedores, credenciais, itemsLoja, this.clientes);

		removeListenerFacade.execute();
		tableListenerFacade.execute();
		loginListenerFacade.execute();
		popupListenerFacade.execute();
	}

	// Initializers:
	private void initializeViews() {
		this.adminView.setLocationRelativeTo(null);
	}
	
	private void initializeModels() {
		for (Credencial c : this.credenciais.get())
			this.adminView.addToWorkerTable(c.usuario, c.usuario, c.senha, c.administrador);

		for (Item item : this.itemsLoja.get()) {
			var produto = item.getProduto();
			this.adminView.addToProductTable(produto.getID(), produto.getNome(), produto.getPreco(), produto.getDescricao(), item.getQuantidade());
		}

		for (Provedor provedor : this.provedores.get())
			this.adminView.addToProvedorTable(provedor.getNome());

		for (Cliente cliente : this.clientes.get())
			this.adminView.addToClientTable(cliente.nome, cliente.cpf, cliente.phone);
	}

	// Execute:
	public void execute() {
		this.loginView.setVisible(true);
	}
}
