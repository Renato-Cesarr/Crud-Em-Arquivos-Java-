package com.RPG.crud.Repositories;

import com.RPG.crud.Controler.MenuDaTelaMain;
import com.RPG.crud.Controler.desenhoDeTelas;
import com.RPG.crud.Service.ArquivoUtils;
import com.RPG.crud.Service.Constantes;
import com.RPG.crud.Service.Validacoes;

import java.io.*;
import java.util.logging.Logger;

public class CrudDelete {

    private static final Logger logger = Logger.getLogger(CrudDelete.class.getName());

    public static void deletarArquivo() throws IOException, InterruptedException {
        Validacoes.limparTela();
        desenhoDeTelas.exibirTelaDelete();
        ArquivoUtils.consultaOpcional();

        int idDeletar = ArquivoUtils.solicitarId("Informe o ID a ser deletado: ");
        Constantes.SCAN.nextLine();

        File arquivoOriginal = new File(Constantes.CAMINHO_DIRETORIO, Constantes.NOME_ARQUIVO);
        File arquivoCopia = new File(Constantes.CAMINHO_DIRETORIO, Constantes.NOME_ARQUIVO_COPIA);

        if (!arquivoOriginal.exists()) {
            logger.severe("O arquivo original não existe. Operação cancelada.");
            return;
        }

        boolean registroDeletado = processarDelecao(arquivoOriginal, arquivoCopia, idDeletar);

        if (registroDeletado) {
            renomearArquivo(arquivoOriginal, arquivoCopia);
        } else {
            logger.warning("ID não encontrado. Nenhum registro foi deletado.");
        }

        logger.info("Salvando ...");
        Thread.sleep(3000);
        MenuDaTelaMain.chamarMenu();
    }

    private static boolean processarDelecao(File arquivoOriginal, File arquivoCopia, int idDeletar) throws IOException {
        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivoOriginal));
             FileWriter escritor = new FileWriter(arquivoCopia)) {

            boolean registroDeletado = false;
            String linha;

            while ((linha = leitor.readLine()) != null) {
                String[] dados = linha.split(",");
                int idAtual = Integer.parseInt(dados[0]);

                if (idAtual == idDeletar) {
                    logger.info("Registro com ID " + idDeletar + " foi deletado.");
                    registroDeletado = true;
                } else {
                    escritor.write(linha + "\n");
                }
            }

            return registroDeletado;
        }
    }

    private static void renomearArquivo(File arquivoOriginal, File arquivoCopia) {
        if (arquivoOriginal.delete()) {
            if (arquivoCopia.renameTo(arquivoOriginal)) {
                logger.info("Operação concluída com sucesso.");
            } else {
                logger.severe("Erro ao renomear o arquivo de cópia.");
            }
        } else {
            logger.severe("Erro ao deletar o arquivo original.");
        }
    }
}
