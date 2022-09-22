package produtos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import loja.Item;

public class BancoDadosProdutos {

	private ArrayList<Item> items;
	private String caminhoDoArquivo;

	public BancoDadosProdutos(String caminhoDoArquivo) {
		this.items = this.lerProdutos(caminhoDoArquivo);
		this.caminhoDoArquivo = caminhoDoArquivo;
		//Produto.setIncremento(this.items.size() + 1);
	}

	public ArrayList<Item> getItems() {
		return this.items;
	}

	public void adicionarProduto(String nome, Float preco, String descricao, int quantidade) {
		this.items.add(new Item(new Produto(nome, preco, descricao), quantidade));
	}

	public Item find(String nomeProduto) {
		for (Item item : items) {
			if (item.getProduto().getNome().equals(nomeProduto)) {
				return item;
			}
		}
		return null;
	}

	public void update(int id, Item novoItem) {
		int index = 0;
		for (Item item : this.items) {
			if (id == item.getProduto().getID()) {
				items.set(index, novoItem);
				return;
			}
			index++;
		}
		System.out.println("Usuário não encontrado ao tentar editar produto");
	}

	public void salvarProdutos() {
		try {
			FileWriter writter = new FileWriter(this.caminhoDoArquivo);

			for (Item item : this.items) {
				var produto = item.getProduto();

				writter.write(
					Integer.toString(produto.getID()) +
									"|" +
									produto.getNome() +
									"|" +
									Float.toString(produto.getPreco()) +
									"|" +
									produto.getDescricao() +
									"|" +
									item.getQuantidade() +
									"\n");
			}

			writter.close();
			System.out.println("Arquivo produto modificado.");
		} catch (IOException e) {
			System.out.println("Um erro ocorreu ao tentar salvar os produtos.");
			e.printStackTrace();
		}
	}

	private ArrayList<Item> lerProdutos(String caminhoDoArquivo) {
		ArrayList<Item> resultados = new ArrayList<Item>();

		try {
			File file = new File(caminhoDoArquivo);
			Scanner reader = new Scanner(file);

			while (reader.hasNextLine()) {
				String data = reader.nextLine();

				if (data.startsWith("#"))
					continue;

				String[] args = data.split("\\|");
				System.out.println(args[2]);

				var id = Integer.parseInt(args[0]);
				var nome = args[1];
				var preco = Float.parseFloat(args[2]);
				var descricao = args[3];
				var quantidade = Integer.parseInt(args[4]);

				resultados.add(new Item(new Produto(id, nome, preco, descricao), quantidade));
			}

			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println(
					"Um erro ocorreu ao abrir o arquivo: " + caminhoDoArquivo);
			e.printStackTrace();
		}

		return resultados;
	}
}
