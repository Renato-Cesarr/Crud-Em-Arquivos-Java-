package com.RPG.crud.Repositories;
import com.RPG.crud.Controler.MenuDaTelaMain;
import com.RPG.crud.Controler.desenhoDeTelas;
import com.RPG.crud.Service.*;
import com.RPG.crud.main.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class CrudUpdate {
    private static final String caminhoParaODiretorio = "/home/almaviva-linux/eclipse-workspace/CrudEmArquivos/resources";
    private static final File diretorio = new File(caminhoParaODiretorio);
    private static final String nomeArquivo = "CrudBasico.txt";
    private static final String copiaArquivo = "CrudBasicoCopia.txt";
    private static final Scanner scan = new Scanner(System.in);
    
    private static int idAserAlterado;
    
    public static void alterarArquivo() throws IOException, InterruptedException {
    	Validacoes.limparTela();
        desenhoDeTelas.exibirTelaUpdate();
        CrudDelete.consultaOpcional();

        System.out.println("Informe o ID a ser alterado:");
        idAserAlterado = scan.nextInt();
        scan.nextLine();

        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }
        
        chamarMudanca();

        File original = new File(caminhoParaODiretorio, nomeArquivo);
        File copia = new File(caminhoParaODiretorio, copiaArquivo);
        
        if (original.delete()) {
            copia.renameTo(original);
            System.out.println("Registro alterado com sucesso!");
        } else {
            System.out.println("Erro ao alterar o registro.");
        }
        
        System.out.println("Salvando ...");
        Thread.sleep(3000);
        Validacoes.limparTela();
        MenuDaTelaMain.chamarMenu();
    }

    public static void chamarMudanca() throws IOException {
        File arquivoOriginal = new File(diretorio, nomeArquivo);
        File arquivoCopia = new File(diretorio, copiaArquivo);

        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivoOriginal));
             FileWriter fileWriter = new FileWriter(arquivoCopia)) {
             
            boolean encontrado = false;
            String linha;

            while ((linha = leitor.readLine()) != null) {
                String[] dados = linha.split(",");
                int idNovoArquivo = Integer.parseInt(dados[0]);
                
                if (idNovoArquivo == idAserAlterado) {
                    encontrado = true;
                    System.out.println("Registro encontrado: " + linha);
                    System.out.println("Informe os novos dados para o registro com ID " + idAserAlterado);
                    
                    String novoNome = "", novaClasse = "", novaArma = "";
                    int novosPontosVida = 0, novosPontosMana = 0;
                    boolean entradaValida;
                    
                    do {
                        try {
                            System.out.println("Digite o novo nome do personagem:");
                            novoNome = scan.nextLine();
                            System.out.println("Digite qual sua classe:");
                            novaClasse = scan.nextLine();
                            System.out.println("Escolha sua arma:");
                            novaArma = scan.nextLine();
                            System.out.println("Distribua 200 pontos entre " + "\033[31mVIDA\033[0m" + " e " + "\033[34mMana\033[0m");
                            novosPontosVida = scan.nextInt();
                            novosPontosMana = scan.nextInt();
                            scan.nextLine();

                            entradaValida = Validacoes.validarEntradaUpdate(novoNome, novaClasse, novaArma, novosPontosVida, novosPontosMana);
                        } catch (Exception e) {
                            System.out.println("Erro: Não digite texto no lugar dos números! Por favor, insira os dados novamente.");
                            scan.nextLine();
                            entradaValida = false;
                        }
                    } while (!entradaValida);

                    String linhaAtualizada = idAserAlterado + "," + novoNome + "," + novaClasse + "," + novaArma + ","
                            + novosPontosVida + "," + novosPontosMana;
                    fileWriter.write(linhaAtualizada + "\r\n");
                } else {
                    fileWriter.write(linha + "\r\n");
                }
            }
        }
    }
    
}
