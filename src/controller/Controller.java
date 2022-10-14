package controller;

import controller.listener_facade.LoginListenerFacade;
import controller.listener_facade.PopupListenerFacade;
import controller.listener_facade.RemoveListenerFacade;
import controller.listener_facade.TableListenerFacade;

import serialization.CredentialManager;
import serialization.ItemManager;
import serialization.ProvedorManager;
import credencial.*;
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

	// Facades:
	private RemoveListenerFacade removeListenerFacade;
	private TableListenerFacade tableListenerFacade;
	private LoginListenerFacade loginListenerFacade;
	private PopupListenerFacade popupListenerFacade;

	public Controller(TelaLogin login, TelaAdmin adminView, CredentialManager credenciais, ItemManager itemsLoja, ProvedorManager provedores) {
		this.loginView = login;
		this.adminView = adminView;
		this.provedores = provedores;

		this.itemsLoja = itemsLoja;
		this.credenciais = credenciais;

		this.initializeViews();
		this.initializeModels();

		// Facades:
		removeListenerFacade = new RemoveListenerFacade(adminView, credenciais, itemsLoja, provedores);
		tableListenerFacade = new TableListenerFacade(adminView, credenciais, itemsLoja);
		loginListenerFacade = new LoginListenerFacade(loginView, adminView, credenciais);
		popupListenerFacade = new PopupListenerFacade(adminView, loginView, this.provedores, credenciais, itemsLoja);

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
	}

	// Execute:
	public void execute() {
		this.loginView.setVisible(true);
	}
}
