package com.RPG.crud.Repositories;

import com.RPG.crud.Controler.MenuDaTelaMain;
import com.RPG.crud.Controler.desenhoDeTelas;
import com.RPG.crud.Service.ArquivoUtils;
import com.RPG.crud.Service.Constantes;
import com.RPG.crud.Service.Validacoes;

import java.io.*;

public class CrudDelete {

    public static void deletarArquivo() throws IOException, InterruptedException {
        Validacoes.limparTela();
        desenhoDeTelas.exibirTelaDelete();
        ArquivoUtils.consultaOpcional();
        
        int idDeletar = ArquivoUtils.solicitarId("Informe o ID a ser deletado: ");
        Constantes.SCAN.nextLine();

        File arquivoOriginal = new File(Constantes.CAMINHO_DIRETORIO, Constantes.NOME_ARQUIVO);
        File arquivoCopia = new File(Constantes.CAMINHO_DIRETORIO, Constantes.NOME_ARQUIVO_COPIA);

        if (!arquivoOriginal.exists()) {
            System.out.println("O arquivo original não existe. Operação cancelada.");
            return;
        }

        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivoOriginal));
             FileWriter escritor = new FileWriter(arquivoCopia)) {

            boolean encontrado = false;
            String linha;

            while ((linha = leitor.readLine()) != null) {
                String[] dados = linha.split(",");
                int idAtual = Integer.parseInt(dados[0]);

                if (idAtual == idDeletar) {
                    encontrado = true;
                    System.out.println("Registro com ID " + idDeletar + " foi deletado.");
                } else {
                    escritor.write(linha + "\n");
                }
            }

            if (!encontrado) {
                System.out.println("ID não encontrado. Nenhum registro foi deletado.");
            }
        }

        if (arquivoOriginal.delete() && arquivoCopia.renameTo(arquivoOriginal)) {
            System.out.println("Operação concluída com sucesso.");
        } else {
            System.out.println("Erro ao concluir a operação de exclusão.");
        }

        System.out.println("Salvando ...");
        Thread.sleep(3000);
        MenuDaTelaMain.chamarMenu();
    }

}