package com.RPG.crud.Repositories;

import com.RPG.crud.Controler.MenuDaTelaMain;
import com.RPG.crud.Controler.desenhoDeTelas;
import com.RPG.crud.Service.Constantes;
import com.RPG.crud.Service.Validacoes;
import com.RPG.crud.Service.*;
import com.RPG.model.Personagem;

import java.io.*;
import java.util.HashMap;
import java.util.logging.Logger;

public class CrudInsert {

    private static final Logger logger = Logger.getLogger(CrudInsert.class.getName());
    public static final HashMap<Integer, Personagem> ID_USUARIO_MAP = new HashMap<>();
    public static int id;

    public static void inserir() throws IOException, InterruptedException {
        desenhoDeTelas.exibirTelaInsert();
        id = ArquivoUtils.carregarUltimoId(Constantes.NOME_ARQUIVO);

        Personagem personagem = obterDadosUsuario();
        ID_USUARIO_MAP.put(id, personagem);

        logger.info("Salvando personagem com ID: " + id);

        ArquivoUtils.salvarNoArquivo(ArquivoUtils.formatarTextoParaArquivo(id, personagem), Constantes.NOME_ARQUIVO);

        id++;
        logger.info("Personagem salvo. ID incrementado para: " + id);
        System.out.println("Salvando ...");
        Thread.sleep(3000);
        MenuDaTelaMain.chamarMenu();
    }

    public static Personagem obterDadosUsuario() {
        Validacoes validacoes = new Validacoes();
        boolean dadosValidos;
        String nomePersonagem = "", nomeClasse = "", tipoArma = "";
        int pontosHp = 0, pontosMana = 0;

        do {
            try {
                System.out.println("Insira os dados:");
                System.out.println("Digite o nome do personagem: ");
                nomePersonagem = Constantes.SCAN.nextLine().toLowerCase();
                System.out.println("Digite qual sua classe: ");
                nomeClasse = Constantes.SCAN.nextLine().toLowerCase();
                System.out.println("Escolha sua arma: ");
                tipoArma = Constantes.SCAN.nextLine().toLowerCase();
                System.out.println("Distribua 200 pontos entre \033[31mVIDA\033[0m e \033[34mMana\033[0m");
                pontosHp = Constantes.SCAN.nextInt();
                pontosMana = Constantes.SCAN.nextInt();
                Constantes.SCAN.nextLine();

                dadosValidos = validacoes.validarEntradaUpdate(nomePersonagem, nomeClasse, tipoArma, pontosHp, pontosMana);

                if (dadosValidos) {
                    logger.info("Dados válidos para personagem: " + nomePersonagem);
                } else {
                    logger.warning("Dados inválidos fornecidos para o personagem " + nomePersonagem);
                }
            } catch (Exception e) {
                logger.severe("Erro ao inserir dados do personagem: " + e.getMessage());
                System.out.println("Não digite texto no lugar dos números! Por favor, insira os dados novamente.");
                Constantes.SCAN.nextLine();
                dadosValidos = false;
            }
        } while (!dadosValidos);

        return new Personagem(nomePersonagem, nomeClasse, tipoArma, pontosHp, pontosMana);
    }
}
