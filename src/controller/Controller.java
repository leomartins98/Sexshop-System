package controller;

import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import controller.listener_facade.LoginListenerFacade;
import controller.listener_facade.RemoveListenerFacade;
import controller.listener_facade.TableListenerFacade;

import javax.swing.event.TableModelEvent;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import java.awt.event.*;

import serialization.CredentialManager;
import serialization.ItemManager;
import serialization.ProvedorManager;
import serialization.SerializationManager;
import loja.Produto;
import credencial.*;
import loja.Item;

import view.TelaCadastroInvent;
import view.TelaCadastroVenda;
import view.TelaCadastroColab;
import view.TelaCadastroFornecedor;
import view.TelaAdmin;
import view.TelaLogin;

public class Controller {

	private TelaCadastroVenda cadastroVenda;
	private TelaCadastroInvent cadastroProduto;
	private TelaCadastroColab cadastroView;
	private TelaCadastroFornecedor cadastroFornecedor;

	private TelaLogin loginView;
	private TelaAdmin adminView;

	private CredentialManager credenciais;
	private ItemManager itemsLoja;

	// Facades:
	private RemoveListenerFacade removeListenerFacade;
	private TableListenerFacade tableListenerFacade;
	private LoginListenerFacade loginListenerFacade;

	public Controller(TelaLogin login, TelaAdmin adminView, CredentialManager credenciais, ItemManager itemsLoja) {
		this.loginView = login;
		this.adminView = adminView;

		this.itemsLoja = itemsLoja;
		this.credenciais = credenciais;

		this.initializeViews();
		this.initializeModels();
		this.initializeViewListeners();

		// Facades:
		removeListenerFacade = new RemoveListenerFacade(this.adminView, this.credenciais, this.itemsLoja);
		tableListenerFacade = new TableListenerFacade(this.adminView, this.credenciais, this.itemsLoja);
		loginListenerFacade = new LoginListenerFacade(this.loginView, this.adminView, this.credenciais);

		removeListenerFacade.execute();
		tableListenerFacade.execute();
		loginListenerFacade.execute();
	}

	private void initializeViews() {
		this.adminView.setLocationRelativeTo(null);

		this.cadastroView = new TelaCadastroColab();
		this.cadastroView.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.cadastroView.setLocationRelativeTo(null);

		this.cadastroFornecedor = new TelaCadastroFornecedor();
		this.cadastroFornecedor.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.cadastroFornecedor.setLocationRelativeTo(null);

		this.cadastroProduto = new TelaCadastroInvent();
		this.cadastroProduto.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.cadastroProduto.setLocationRelativeTo(null);
	}

	private void initializeViewListeners() {
		// Popup Listener:
		this.adminView.addCredentialPopupListener(new CredentialPopupListener());
		this.adminView.addProviderPopupListener(new ProviderPopupListener());
		this.adminView.addProductPopupListener(new ProductPopupListener());

		// Remove Listeners:
		

		// Cadastro View:
		this.cadastroView.addCadastrarListener(new CadastrarListener());
		this.cadastroProduto.addCadastrarProduto(new CadastrarProdutoListener());
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

	// **************** Listeners **************** \\

	// Popup listeners:
	class CredentialPopupListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			cadastroView.setVisible(true);
		}

	}

	class ProductPopupListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			cadastroProduto.setVisible(true);
		}
	
	}

	class ProviderPopupListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			cadastroFornecedor.setVisible(true);
		}

	}

	// Register listeners:
	class CadastrarListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			var nome = cadastroView.getUser();
			var usuario = cadastroView.getUsername();
			var senha = cadastroView.getPassword();
			var administrador = cadastroView.getAdm();

			if(nome.isBlank() || usuario.isBlank() || senha.isBlank()) {
				JOptionPane.showMessageDialog(loginView,
					"Por favor, preencha todos os dados do funcionário.",
					"Erro de Cadastro",
					JOptionPane.ERROR_MESSAGE);
				return;
			}

			DefaultTableModel model = (DefaultTableModel) adminView.getColabTable().getModel();
			model.addRow(new Object[] { nome, usuario, senha, administrador ? "Administrador" : "Funcionário" });

			credenciais.add(new Credencial(nome, usuario, senha, administrador));
			credenciais.save();

			cadastroView.clearView();
			cadastroView.setVisible(false);
		}
	}

	class CadastrarProdutoListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int id = adminView.getProductTable().getRowCount();
			String nome = cadastroProduto.getNome();
			String preco = cadastroProduto.getPreco();
			String descricao = cadastroProduto.getDescricao();
			int qtd = cadastroProduto.getQuantidade();

			if(nome.isBlank() || preco.isBlank() || descricao.isBlank()) {
				JOptionPane.showMessageDialog(loginView,
					"Por favor, preencha todos os dados do produto.",
					"Erro de Cadastro",
					JOptionPane.ERROR_MESSAGE);
				return;
			}

			DefaultTableModel model = (DefaultTableModel) adminView.getProductTable().getModel();
			model.addRow(new Object[] { id, nome, preco, descricao, qtd });

			itemsLoja.add(new Item(new Produto(id, nome, Float.parseFloat(preco), descricao), qtd));
			itemsLoja.save();

			cadastroProduto.clearView();
			cadastroProduto.setVisible(false);
		}
	}

}
