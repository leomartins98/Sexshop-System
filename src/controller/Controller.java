package controller;

import credencial.*;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import loja.Produto;
import produtos.BancoDadosProdutos;
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

  public Controller(
    TelaLogin login,
    TelaAdmin adminView,
    Credenciais credenciais,
    BancoDadosProdutos bancoDadosProdutos
  ) {
    this.loginView = login;
    this.adminView = adminView;
    this.cadastroView = new TelaCadastroColab();
    this.cadastroProduto = new TelaCadastroInvent();

    this.bancoDadosProdutos = bancoDadosProdutos;
    this.credenciais = credenciais;

    this.loginView.addLoginListener(new LoginListener());

    for (Credencial c : this.credenciais.getCredenciais()) this.adminView.adicionarFuncionarioNaTabela(
        c.usuario,
        c.usuario,
        c.senha,
        c.administrador
      );

    this.adminView.addListenerToTable(new FuncionarioModelListener());
    this.adminView.addCredentialRegisterListener(
        new CredentialRegisterListener()
      );
    this.cadastroView.addCadastrarListener(new CadastrarListener());

    this.adminView.addProductToTable(new ProductModelListener());
    this.adminView.addProductRegisterListener(new RegisterProductListener());
    this.cadastroView.addCadastrarListener(new CadastrarListener());
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
        // TODO: View-usuario nao encontrado.
        System.out.println("Usuario nao encontrado");
        return;
      }

      if (!c.senha.equals(password)) {
        // TODO: View-Senha incorreta.
        System.out.println("Senha incorreta");
        return;
      }

      // Esconde a view de login:
      loginView.setVisible(false);

      if (c.administrador == true) {
        adminView.setVisible(true);
        System.out.println("Acesso concedido. Alterando para view de Gerente.");
      } else {
        //funcionarioView.setVisible(true);
        System.out.println(
          "Acesso concedido. Alterando para view de Funcionário."
        );
      }
    }
  }

  class RegisterListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      String username = loginView.getUsername();
      String password = loginView.getPassword();

      var c = credenciais.find(username);
      if (c == null) return;

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
        "Usuário inserido com sucesso. Navegando para View de funcionários."
      );
    }
  }

  class FuncionarioModelListener implements TableModelListener {

    @Override
    public void tableChanged(TableModelEvent e) {
      int row = adminView.getColabTable().getSelectedRow();
      if (row < 0) return;

      String[] rowAtual = adminView.getRowAt(row);

      boolean administrador = rowAtual[3].toLowerCase().equals("administrador")
        ? true
        : false;

      credenciais.update(
        rowAtual[0],
        new Credencial(rowAtual[0], rowAtual[1], rowAtual[2], administrador)
      );
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
        }
      );

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
      if (row < 0) return;

      String[] rowAtual = adminView.getRowProductAt(row);

      bancoDadosProdutos.update(
        rowAtual[0],
        new Produto(
          Integer.parseInt(rowAtual[0]),
          rowAtual[1],
          Float.parseFloat(rowAtual[2]),
          rowAtual[3],
          Integer.parseInt(rowAtual[4])
        )
      );

      credenciais.salvarCredenciais();
    }
  }

  class RegisterProductListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      cadastroProduto.setVisible(true);
    }
  }
}
