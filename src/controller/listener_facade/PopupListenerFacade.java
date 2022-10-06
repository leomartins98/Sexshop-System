package controller.listener_facade;

import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import credencial.Credencial;
import loja.Item;
import loja.Produto;
import serialization.CredentialManager;
import serialization.ItemManager;
import view.TelaAdmin;
import view.TelaCadastroColab;
import view.TelaCadastroFornecedor;
import view.TelaCadastroInvent;
import view.TelaLogin;

public class PopupListenerFacade {

    protected TelaCadastroColab workerRegisterView;
    protected TelaCadastroInvent cadastroProduto;
    protected TelaCadastroFornecedor cadastroFornecedor;

    protected CredentialManager credenciais;
    protected ItemManager itemsLoja;

	protected TelaLogin loginView;
    protected TelaAdmin adminView;

    public PopupListenerFacade(TelaAdmin adminView, TelaLogin loginView, CredentialManager credenciais, ItemManager itemsLoja) {
        this.adminView = adminView;
        this.loginView = loginView;

        this.credenciais = credenciais;
        this.itemsLoja = itemsLoja;

		this.initializeViews();
    }

    public void execute() {
        this.adminView.addCredentialPopupListener(new CredentialPopupListener());
		this.adminView.addProviderPopupListener(new ProviderPopupListener());
		this.adminView.addProductPopupListener(new ProductPopupListener());
    }

    public void initializeViews() {
        // Colaboradores:
        this.workerRegisterView = new TelaCadastroColab();
		this.workerRegisterView.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.workerRegisterView.setLocationRelativeTo(null);
        this.workerRegisterView.addCadastrarListener(new CadastrarListener());

        // Cadastro:
        this.cadastroProduto = new TelaCadastroInvent();
		this.cadastroProduto.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.cadastroProduto.setLocationRelativeTo(null);
        this.cadastroProduto.addCadastrarProduto(new CadastrarProdutoListener());

        // Fornecedor:
        this.cadastroFornecedor = new TelaCadastroFornecedor();
		this.cadastroFornecedor.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.cadastroFornecedor.setLocationRelativeTo(null);
    }

    // Popup Listeners:
    class CredentialPopupListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			workerRegisterView.setVisible(true);
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

    // Register Listeners:
    class CadastrarListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			var nome = workerRegisterView.getUser();
			var usuario = workerRegisterView.getUsername();
			var senha = workerRegisterView.getPassword();
			var administrador = workerRegisterView.getAdm();

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

			workerRegisterView.clearView();
			workerRegisterView.setVisible(false);
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
