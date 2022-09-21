package produtos;

import java.util.ArrayList;
import loja.Produto;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

public class BancoDadosProdutos {

    private ArrayList<Produto> produtos;
    private String caminhoDoArquivo;

    public BancoDadosProdutos(String caminhoDoArquivo) {
        this.produtos = this.lerProdutos(caminhoDoArquivo);
        this.caminhoDoArquivo = caminhoDoArquivo;
    }
    
    public ArrayList<Produto> getProdutos() { return produtos; }

    public void adicionarProduto(String nome, Float preco, String descricao)
    {
        this.produtos.add(new Produto(nome, preco, descricao));
    }

    public Produto find(String nomeProduto) {
        for(Produto produto : produtos) {
            if(produto.getNome().equals(nomeProduto)) {
                return produto;
            }
        }
        return null;
    }

    public void update(String produtoNome, Produto novoProduto) {
        int index = 0;
        for(Produto produto : this.produtos) {
            if(produto.getNome().equals(produtoNome)) {
                produtos.set(index, novoProduto);
                return;
            }
            index++;
        }
        System.out.println("Usuário não encontrado ao tentar editar produto");
    }

    public void salvarProdutos()
    {
        try {
            FileWriter writter = new FileWriter(this.caminhoDoArquivo);

            for(Produto produto : this.produtos) {
                writter.write(produto.getNome() + "," + Float.toString(produto.getPreco()) + "," + produto.getDescricao() + "\n");
            }

            writter.close();
            System.out.println("Arquivo produto modificado.");
                       
        } catch (IOException e) {
            System.out.println("Um erro ocorreu ao tentar salvar os produtos.");
            e.printStackTrace();
        }
    }

    private ArrayList<Produto> lerProdutos(String caminhoDoArquivo) {
        ArrayList<Produto> resultados = new ArrayList<Produto>();

        try {
            File file = new File(caminhoDoArquivo);
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {
                String data = reader.nextLine();

                if(data.startsWith("#"))
                    continue;

                String[] args = data.split(",");

                resultados.add(new Produto(args[0], Float.parseFloat(args[1]), args[2]));
            }

            reader.close();

        } catch(FileNotFoundException e) {
            System.out.println("Um erro ocorreu ao abrir o arquivo: " + caminhoDoArquivo);
            e.printStackTrace();
        }

        return resultados;
    }
}
