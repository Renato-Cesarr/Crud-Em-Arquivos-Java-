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

public class CrudDelete {

    private static final String caminhoParaoDiretorio = "/home/almaviva-linux/eclipse-workspace/CrudEmArquivos/resources";
    private static final File diretorio = new File(caminhoParaoDiretorio);
    private static final String nomeArquivo = "CrudBasico.txt";
    private static final String copiaArquivo = "CrudBasicoCopia.txt";
    private static final Scanner scan = new Scanner(System.in);

    public static void deletarArquivo() throws IOException, InterruptedException {
    	Validacoes.limparTela();
        desenhoDeTelas.exibirTelaDelete();
        consultaOpcional();

        System.out.println("Informe o ID a ser deletado:");
        int idASerExcluido = scan.nextInt();
        scan.nextLine();

        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }

        if (fazerDelete(idASerExcluido)) {
            File original = new File(caminhoParaoDiretorio, nomeArquivo);
            File copia = new File(caminhoParaoDiretorio, copiaArquivo);

            if (original.delete() && copia.renameTo(original)) {
                System.out.println("Registro deletado com sucesso!");
            } else {
                System.out.println("Erro ao substituir o arquivo original.");
            }
        } else {
            System.out.println("Nenhum registro foi encontrado com o ID informado.");
        }
        System.out.println("Salvando ...");
        Thread.sleep(3000);
        Validacoes.limparTela();
        MenuDaTelaMain.chamarMenu();
    }

    private static boolean fazerDelete(int idASerExcluido) throws IOException {
        File arquivoOriginal = new File(diretorio, nomeArquivo);
        File arquivoCopia = new File(diretorio, copiaArquivo);

        boolean registroEncontrado = false;

        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivoOriginal));
             FileWriter fileWriter = new FileWriter(arquivoCopia)) {

            String linha;
            while ((linha = leitor.readLine()) != null) {
                String[] dados = linha.split(",");
                int idAtual = Integer.parseInt(dados[0]);

                if (idAtual != idASerExcluido) {
                    fileWriter.write(linha + System.lineSeparator());
                } else {
                    registroEncontrado = true;
                }
            }

        } catch (IOException | NumberFormatException e) {
            System.out.println("Erro durante o processamento: " + e.getMessage());
        }

        return registroEncontrado;
    }

    public static void consultaOpcional() throws IOException {
        System.out.println(Constantes.AMARELO + "Deseja realizar uma consulta geral antes de deletar? (sim/nao): " + Constantes.RESET);
        String mostrarConsulta = scan.nextLine();
        mostrarConsulta = Validacoes.verificarEntrada(mostrarConsulta);

        if (mostrarConsulta.equalsIgnoreCase("sim")) {
            CrudSelect.consultaGeral();
        }
    }
}
