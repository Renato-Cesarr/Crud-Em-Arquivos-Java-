package com.RPG.crud.Repositories;

import com.RPG.crud.Controler.MenuDaTelaMain;
import com.RPG.crud.Controler.desenhoDeTelas;
import com.RPG.crud.Service.Constantes;
import com.RPG.crud.Service.Validacoes;
import com.RPG.crud.Service.ArquivoUtils;
import com.RPG.model.Personagem;

import java.io.*;
import java.util.logging.Logger;

public class CrudUpdate {

    private static final Logger logger = Logger.getLogger(CrudUpdate.class.getName());

    public static void alterarArquivo() throws IOException, InterruptedException {
        Validacoes.limparTela();
        desenhoDeTelas.exibirTelaUpdate();
        ArquivoUtils.consultaOpcional();

        int idAlterar = ArquivoUtils.solicitarId("Informe o ID a ser alterado: ");

        if (!Constantes.DIRETORIO.exists()) {
            Constantes.DIRETORIO.mkdirs();
            logger.info("Diretório criado: " + Constantes.DIRETORIO.getAbsolutePath());
        }

        realizarAlteracao(idAlterar);

        File arquivoOriginal = new File(Constantes.CAMINHO_DIRETORIO, Constantes.NOME_ARQUIVO);
        File arquivoCopia = new File(Constantes.CAMINHO_DIRETORIO, Constantes.NOME_ARQUIVO_COPIA);

        if (arquivoOriginal.delete() && arquivoCopia.renameTo(arquivoOriginal)) {
            logger.info("Registro alterado com sucesso no arquivo.");
        } else {
            logger.severe("Erro ao alterar o registro no arquivo.");
        }

        logger.info("Finalizando alteração e retornando ao menu.");
        logger.info("Salvando ...");
        Thread.sleep(3000);
        Validacoes.limparTela();
        MenuDaTelaMain.chamarMenu();
    }

    private static void realizarAlteracao(int idAlterar) throws IOException {
        File arquivoOriginal = new File(Constantes.DIRETORIO, Constantes.NOME_ARQUIVO);
        File arquivoCopia = new File(Constantes.DIRETORIO, Constantes.NOME_ARQUIVO_COPIA);

        boolean encontrado = false;
        while (!encontrado) {
            try (BufferedReader leitor = new BufferedReader(new FileReader(arquivoOriginal));
                 FileWriter escritor = new FileWriter(arquivoCopia)) {

                String linha;

                while ((linha = leitor.readLine()) != null) {
                    String[] dados = linha.split(",");
                    int idAtual = Integer.parseInt(dados[0]);

                    if (idAtual == idAlterar) {
                        encontrado = true;
                        logger.info("Registro encontrado: " + linha);
                        logger.info("Informe os novos dados para o registro com ID " + idAlterar);

                        Personagem personagem = CrudInsert.obterDadosUsuario();
                        escritor.write(ArquivoUtils.formatarTextoParaArquivo(idAtual, personagem));
                    } else {
                        escritor.write(linha + "\n");
                    }
                }

                if (!encontrado) {
                    logger.warning("ID não encontrado: " + idAlterar);
                    idAlterar = ArquivoUtils.solicitarId("Informe o ID a ser alterado: ");
                }
            }
        }
    }
}
