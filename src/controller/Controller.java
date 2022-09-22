package controller;

import credencial.*;
import images.loja.Item;

import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import produtos.BancoDadosProdutos;
import produtos.Produto;
import view.TelaAdmin;
import view.TelaCadastroColab;
import view.TelaCadastroInvent;
import view.TelaLogin;

public class Controller {

	private TelaCadastroInvent cadastroProduto;
	private TelaCadastroColab cadastroView;
	private TelaLogin loginView;
	private TelaAdmin adminView;

	private Credenciais credenciais;
	private BancoDadosProdutos bancoDadosProdutos;

	public Controller(TelaLogin login, TelaAdmin adminView, Credenciais credenciais, BancoDadosProdutos bancoDadosProdutos) {
		this.loginView = login;
		this.adminView = adminView;

		this.cadastroView = new TelaCadastroColab();
		this.cadastroView.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		this.cadastroProduto = new TelaCadastroInvent();
		this.cadastroProduto.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		this.bancoDadosProdutos = bancoDadosProdutos;
		this.credenciais = credenciais;

		this.loginView.addLoginListener(new LoginListener());

		for (Credencial c : this.credenciais.getCredenciais())
			this.adminView.adicionarFuncionarioNaTabela(c.usuario, c.usuario, c.senha, c.administrador);

		for (Item item : this.bancoDadosProdutos.getItems())
		{
			var produto = item.getProduto();
			this.adminView.adicionarProdutoNaTabela(produto.getID(), produto.getNome(), produto.getPreco(), produto.getDescricao(), item.getQuantidade());
		}

		this.adminView.addListenerToTable(new FuncionarioModelListener());

		this.adminView.addCredentialRegisterListener(
				new CredentialRegisterListener());

		this.cadastroView.addCadastrarListener(new CadastrarListener());

		this.adminView.addProductToTable(new ProductModelListener());
		this.adminView.addProductRegisterListener(new RegisterProductListener());
		this.cadastroProduto.addCadastrarProduto(new CadastrarProdutoListener());
	}

	public void execute() {
		this.loginView.setVisible(true);
	}

	class LoginListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String username = loginView.getUsername();
			String password = loginView.getPassword();

			var c = credenciais.find(username);

			if (c == null || !c.isValid()) {
				JOptionPane.showMessageDialog(loginView,
				"O usuário" + username + " não está cadastrado no banco de dados. Verifique com um administrador.",
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
			} else {
				// funcionarioView.setVisible(true);
				System.out.println(
						"Acesso concedido. Alterando para view de Funcionário.");
			}
		}
	}

	class RegisterListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String username = loginView.getUsername();
			String password = loginView.getPassword();

			var c = credenciais.find(username);
			if (c == null)
				return;

			if (c.isValid()) {
				// TODO: View-usuario encontrado.
				System.out.println("Usuário já cadastrado.");
				return;
			}

			// Gerentes devem ser setados manualmente:
			credenciais.adicionarCredencial(username, username, password, false);
			credenciais.salvarCredenciais();

			// Após o cadastro, passa diretamente para tela de funcionário:
			// loginView.setVisible(false);
			// funcionarioView.setVisible(true);

			System.out.println(
					"Usuário inserido com sucesso. Navegando para View de funcionários.");
		}
	}

	class FuncionarioModelListener implements TableModelListener {

		@Override
		public void tableChanged(TableModelEvent e) {
			int row = adminView.getColabTable().getSelectedRow();
			if (row < 0)
				return;

			String[] rowAtual = adminView.getRowAt(row);

			boolean administrador = rowAtual[3].toLowerCase().equals("administrador")
					? true
					: false;

			credenciais.update(
					rowAtual[0],
					new Credencial(rowAtual[0], rowAtual[1], rowAtual[2], administrador));
			credenciais.salvarCredenciais();
		}
	}

	class CredentialRegisterListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			cadastroView.setVisible(true);
		}
	}

	class CadastrarListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			var nome = cadastroView.getUser();
			var usuario = cadastroView.getUsername();
			var senha = cadastroView.getPassword();
			var administrador = cadastroView.getAdm();

			DefaultTableModel model = (DefaultTableModel) adminView
					.getColabTable()
					.getModel();
			model.addRow(
					new Object[] {
							nome,
							usuario,
							senha,
							administrador ? "Administrador" : "Funcionário",
					});

			credenciais.adicionarCredencial(nome, usuario, senha, administrador);
			credenciais.salvarCredenciais();

			cadastroView.clearView();
			cadastroView.setVisible(false);
		}
	}

	class ProductModelListener implements TableModelListener {

		@Override
		public void tableChanged(TableModelEvent e) {
			int row = adminView.getProductTable().getSelectedRow();
			if (row < 0)
				return;

			String[] rowAtual = adminView.getRowProductAt(row);

			var id = Integer.parseInt(rowAtual[0]);
			var nome = rowAtual[1];
			var preco =  Float.parseFloat(rowAtual[2]);
			var desc = rowAtual[3];
			var qtd = Integer.parseInt(rowAtual[4]);

			bancoDadosProdutos.update(id, new Item(new Produto(nome, preco, desc), qtd));

			bancoDadosProdutos.salvarProdutos();
		}
	}

	class RegisterProductListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			cadastroProduto.setVisible(true);
		}
	}

	class CadastrarProdutoListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			var id = Produto.getIncremento();
			var nome = cadastroProduto.getNome();
			var preco = cadastroProduto.getPreco();
			var descricao = cadastroProduto.getDescricao();
			var qtd = cadastroProduto.getQuantidade();

			DefaultTableModel model = (DefaultTableModel) adminView
					.getProductTable()
					.getModel();
			model.addRow(new Object[] { id, nome, preco, descricao, qtd });

			bancoDadosProdutos.adicionarProduto(nome, preco, descricao, qtd);
			bancoDadosProdutos.salvarProdutos();

			cadastroProduto.clearView();
			cadastroProduto.setVisible(false);
		}
	}
}
