package controller;

import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.TableModelEvent;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import java.awt.event.*;

import serialization.SerializationManager;
import produtos.Produto;
import credencial.*;
import loja.Item;

import view.TelaCadastroInvent;
import view.TelaCadastroColab;
import view.TelaAdmin;
import view.TelaLogin;

public class Controller {

	private TelaCadastroInvent cadastroProduto;
	private TelaCadastroColab cadastroView;
	private TelaLogin loginView;
	private TelaAdmin adminView;

	private SerializationManager<Credencial> credenciais;
	private SerializationManager<Item> itemsLoja;

	public Controller(TelaLogin login, TelaAdmin adminView, SerializationManager<Credencial> credenciais, SerializationManager<Item> itemsLoja) {
		this.loginView = login;
		this.adminView = adminView;

		this.itemsLoja = itemsLoja;
		this.credenciais = credenciais;

		this.initializeViews();

		this.initializeModels();

		this.initializeViewListeners();
	}

	private void initializeViews() {
		this.adminView.setLocationRelativeTo(null);

		this.cadastroView = new TelaCadastroColab();
		this.cadastroView.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.cadastroView.setLocationRelativeTo(null);

		this.cadastroProduto = new TelaCadastroInvent();
		this.cadastroProduto.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.cadastroProduto.setLocationRelativeTo(null);
	}

	private void initializeViewListeners() {
		this.loginView.addLoginListener(new LoginListener());

		this.adminView.addListenerToTable(new FuncionarioModelListener());
		this.adminView.addCredentialRegisterListener(new CredentialRegisterListener());

		this.cadastroView.addCadastrarListener(new CadastrarListener());

		this.adminView.addProductToTable(new ProductModelListener());
		this.adminView.addProductRegisterListener(new RegisterProductListener());
		this.adminView.addRemoveUserListener(new RemoveUserListener());
		this.adminView.addRemoveProductListener(new RemoveProductListener());
		
		this.cadastroProduto.addCadastrarProduto(new CadastrarProdutoListener());
	}

	private void initializeModels() {
		for (Credencial c : this.credenciais.get())
			this.adminView.adicionarFuncionarioNaTabela(c.usuario, c.usuario, c.senha, c.administrador);

		for (Item item : this.itemsLoja.get())
		{
			var produto = item.getProduto();
			this.adminView.adicionarProdutoNaTabela(produto.getID(), produto.getNome(), produto.getPreco(), produto.getDescricao(), item.getQuantidade());
		}
	}

	public void execute() {
		this.loginView.setVisible(true);
	}

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
				System.out.println("Acesso concedido. Alterando para view de Funcionário.");
		}
	}

	// Table listeners:
	class FuncionarioModelListener implements TableModelListener {

		@Override
		public void tableChanged(TableModelEvent e) {
			int row = adminView.getColabTable().getSelectedRow();
			if (row < 0)
				return;

			String[] rowAtual = adminView.getRowAt(row);
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

	class ProductModelListener implements TableModelListener {

		@Override
		public void tableChanged(TableModelEvent e) {
			int row = adminView.getProductTable().getSelectedRow();
			if (row < 0)
				return;

			String[] rowAtual = adminView.getRowProductAt(row);
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
	class CredentialRegisterListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			cadastroView.setVisible(true);
		}

	}

	class RegisterProductListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			cadastroProduto.setVisible(true);
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
	class RemoveUserListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int row = adminView.getColabTable().getSelectedRow();
			if (row < 0)
				return;

			String[] rowAtual = adminView.getRowAt(row);

			credenciais.remove("usuario", rowAtual[0]);
			credenciais.save();
			
			((DefaultTableModel)adminView.getColabTable().getModel()).removeRow(row);
		}

	}

	class RemoveProductListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int row = adminView.getProductTable().getSelectedRow();
			if (row < 0)
				return;

			String[] rowAtual = adminView.getRowProductAt(row);
			
			itemsLoja.remove("id", rowAtual[0]);
			itemsLoja.save();
			
			DefaultTableModel model = (DefaultTableModel)adminView.getProductTable().getModel();
			model.removeRow(row);
		}
	}
}
