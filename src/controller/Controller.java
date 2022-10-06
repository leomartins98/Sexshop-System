package controller;

import controller.listener_facade.LoginListenerFacade;
import controller.listener_facade.PopupListenerFacade;
import controller.listener_facade.RemoveListenerFacade;
import controller.listener_facade.TableListenerFacade;

import serialization.CredentialManager;
import serialization.ItemManager;
import credencial.*;
import loja.Item;

import view.TelaAdmin;
import view.TelaLogin;

public class Controller {

	private TelaLogin loginView;
	private TelaAdmin adminView;

	private CredentialManager credenciais;
	private ItemManager itemsLoja;

	// Facades:
	private RemoveListenerFacade removeListenerFacade;
	private TableListenerFacade tableListenerFacade;
	private LoginListenerFacade loginListenerFacade;
	private PopupListenerFacade popupListenerFacade;

	public Controller(TelaLogin login, TelaAdmin adminView, CredentialManager credenciais, ItemManager itemsLoja) {
		this.loginView = login;
		this.adminView = adminView;

		this.itemsLoja = itemsLoja;
		this.credenciais = credenciais;

		this.initializeViews();
		this.initializeModels();

		// Facades:
		removeListenerFacade = new RemoveListenerFacade(this.adminView, this.credenciais, this.itemsLoja);
		tableListenerFacade = new TableListenerFacade(this.adminView, this.credenciais, this.itemsLoja);
		loginListenerFacade = new LoginListenerFacade(this.loginView, this.adminView, this.credenciais);
		popupListenerFacade = new PopupListenerFacade(this.adminView, this.loginView, this.credenciais, this.itemsLoja);

		removeListenerFacade.execute();
		tableListenerFacade.execute();
		loginListenerFacade.execute();
		popupListenerFacade.execute();
	}

	private void initializeViews() {
		this.adminView.setLocationRelativeTo(null);
	}

	// Initializers:
	private void initializeModels() {
		for (Credencial c : this.credenciais.get())
			this.adminView.addToWorkerTable(c.usuario, c.usuario, c.senha, c.administrador);

		for (Item item : this.itemsLoja.get())
		{
			var produto = item.getProduto();
			this.adminView.addToProductTable(produto.getID(), produto.getNome(), produto.getPreco(), produto.getDescricao(), item.getQuantidade());
		}
	}

	public void execute() {
		this.loginView.setVisible(true);
	}
}
