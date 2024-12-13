package com.RPG.crud.Repositories;

import com.RPG.crud.Controler.MenuDaTelaMain;
import com.RPG.crud.Controler.desenhoDeTelas;
import com.RPG.crud.Service.Constantes;
import com.RPG.crud.Service.Validacoes;
import com.RPG.crud.Service.*;
import com.RPG.model.Personagem;

import java.io.*;

public class CrudUpdate {

    public static void alterarArquivo() throws IOException, InterruptedException {
        Validacoes.limparTela();
        desenhoDeTelas.exibirTelaUpdate();
        ArquivoUtils.consultaOpcional();

        int idAlterar = ArquivoUtils.solicitarId("Informe o ID a ser alterado: ");

        if (!Constantes.DIRETORIO.exists()) {
            Constantes.DIRETORIO.mkdirs();
        } 

        realizarAlteracao(idAlterar);

        File arquivoOriginal = new File(Constantes.CAMINHO_DIRETORIO, Constantes.NOME_ARQUIVO);
        File arquivoCopia = new File(Constantes.CAMINHO_DIRETORIO, Constantes.NOME_ARQUIVO_COPIA);

        if (arquivoOriginal.delete() && arquivoCopia.renameTo(arquivoOriginal)) {
            System.out.println("Registro alterado com sucesso!");
        } else {
            System.out.println("Erro ao alterar o registro.");
        }

        System.out.println("Salvando ...");
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
                        System.out.println("Registro encontrado: " + linha);
                        System.out.println("Informe os novos dados para o registro com ID " + idAlterar);

                        Personagem personagem = obterNovosDados();
                        escritor.write(ArquivoUtils.formatarTextoParaArquivo(idAtual, personagem));
                    } else {
                        escritor.write(linha + "\n");
                    }
                }

                if (!encontrado) {
                    System.out.println("ID não encontrado. Tente novamente.");
                    idAlterar = ArquivoUtils.solicitarId("Informe o ID a ser alterado: ");  
                }
            }
        }
    }

    private static Personagem obterNovosDados() {
        boolean dadosValidos;
        String nomePersonagem = "", nomeClasse = "", tipoArma = "";
        int pontosHp = 0, pontosMana = 0;

        do {
            try {
                System.out.println("Digite o novo nome do personagem:");
                nomePersonagem = Constantes.SCAN.nextLine();
                System.out.println("Digite qual sua classe:");
                nomeClasse = Constantes.SCAN.nextLine();
                System.out.println("Escolha sua arma:");
                tipoArma = Constantes.SCAN.nextLine();
                System.out.println("Distribua 200 pontos entre \033[31mVIDA\033[0m e \033[34mMana\033[0m");
                pontosHp = Constantes.SCAN.nextInt();
                pontosMana = Constantes.SCAN.nextInt();
                Constantes.SCAN.nextLine();

                dadosValidos = Validacoes.validarEntradaUpdate(nomePersonagem, nomeClasse, tipoArma, pontosHp, pontosMana);
            } catch (Exception e) {
                System.out.println("Erro: Não digite texto no lugar dos números! Por favor, insira os dados novamente.");
                Constantes.SCAN.nextLine();
                dadosValidos = false;
            }
        } while (!dadosValidos);

        return new Personagem(nomePersonagem, nomeClasse, tipoArma, pontosHp, pontosMana);
    }


}
