package credencial;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.File;

public class Credenciais {
    private ArrayList<Credencial> credenciais;
    private String caminhoDoArquivo;

    public Credenciais(String caminhoDoArquivo)
    {
        credenciais = lerCredenciais(caminhoDoArquivo);
        this.caminhoDoArquivo = caminhoDoArquivo;
    }

    public ArrayList<Credencial> getCredenciais() { return credenciais; }

    public void adicionarCredencial(String nome, String usuario, String senha, boolean administrador)
    {
        this.credenciais.add(new Credencial(nome, usuario, senha, administrador));
    }

    public void salvarCredenciais()
    {
        try {
            FileWriter writter = new FileWriter(this.caminhoDoArquivo);

            for(Credencial credencial : this.credenciais) {
                String administrador = credencial.administrador ? "adm" : "fun";
                writter.write(credencial.nome + "," + credencial.usuario + "," + credencial.senha + "," + administrador + "\n");
            }

            writter.close();
            System.out.println("Credencial adicionada ao arquivo.");
                       
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public Credencial find(String nomeUsuario) {
        for(Credencial credencial : credenciais) {
            if(credencial.usuario.equals(nomeUsuario)) {
                return credencial;
            }
        }
        return null;
    }

    public void update(String usuario, Credencial novaCredencial) {
        int index = 0;
        for(Credencial credencial : credenciais) {
            if(credencial.usuario.equals(usuario)) {
                credenciais.set(index, novaCredencial);
                return;
            }
            index++;
        }
        System.out.println("Usuário não encontrado ao tentar editar credencial");
    }

    private ArrayList<Credencial> lerCredenciais(String caminhoDoArquivo) {
        ArrayList<Credencial> resultados = new ArrayList<Credencial>();

        try {
            File file = new File(caminhoDoArquivo);
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {
                String data = reader.nextLine();

                if(data.startsWith("#"))
                    continue;

                String[] args = data.split(",");

                boolean administrador = (args[3].equals("adm"));
                resultados.add(new Credencial(args[0], args[1], args[2], administrador));
            }

            reader.close();

        } catch(FileNotFoundException e) {
            System.out.println("Um erro ocorreu ao abrir o arquivo: " + caminhoDoArquivo);
            e.printStackTrace();
        }

        return resultados;
    }
}
