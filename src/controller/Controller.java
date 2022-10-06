package controller;

import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import controller.listener_facade.RemoveListenerFacade;

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
	RemoveListenerFacade removeListenerFacade;

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

		this.cadastroVenda = new TelaCadastroVenda();
		this.cadastroVenda.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.cadastroVenda.setLocationRelativeTo(null);
	}

	private void initializeViewListeners() {
		this.loginView.addLoginListener(new LoginListener());

		// Table Model Listener:
		this.adminView.addWorkerTableModelListener(new WorkerTableModelListener());
		this.adminView.addProductTableModelListener(new ProductTableModelListener());

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

	// Login:
	class LoginListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String username = loginView.getUsername();
			String password = loginView.getPassword();

			Credencial c = credenciais.find("usuario", username);

			if (c == null) {
				JOptionPane.showMessageDialog(loginView,
				"O usuário " + username + " não está cadastrado no banco de dados. Verifique com um administrador.",
				"Erro de Autenticação",
				JOptionPane.ERROR_MESSAGE);
				return;
			}

			if (!c.senha.equals(password)) {
				JOptionPane.showMessageDialog(loginView,
				"Senha incorreta. Tente novamente.",
				"Erro de Autenticação",
				JOptionPane.ERROR_MESSAGE);
				return;
			}

			// Esconde a view de login:
			loginView.setVisible(false);

			if (c.administrador == true) {
				adminView.setVisible(true);
			} else 
				cadastroVenda.setVisible(true);
		}
	}

	// Table listeners:
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

			for(var c : itemsLoja.getClass().getFields()) {
				System.out.println(c.getName());
			}

			itemsLoja.update("id", rowAtual[0], new Item(new Produto(id, nome, preco, desc), qtd));
			itemsLoja.save();
		}
	
	}

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

	// Remove Actors:
	
}
