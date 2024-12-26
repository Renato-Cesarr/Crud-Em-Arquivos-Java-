package com.RPG.crud.Repositories;

import com.RPG.crud.Controler.MenuDaTelaMain;
import com.RPG.crud.Service.Constantes;
import com.RPG.crud.Service.Validacoes;

import java.io.*;
import java.util.logging.Logger;

public class CrudSelect {

    private static final Logger logger = Logger.getLogger(CrudSelect.class.getName());

    public static void consultaGeral() {
        try (BufferedReader lerArquivo = new BufferedReader(
                new FileReader(new File(Constantes.CAMINHO_DIRETORIO, Constantes.NOME_ARQUIVO)))) {
            String linha;
            System.out.println("Registros disponíveis:");
            while ((linha = lerArquivo.readLine()) != null) {
                System.out.println(linha);
            }
        } catch (IOException e) {
            logger.severe("Erro ao ler os registros: " + e.getMessage());
            System.out.println("Erro ao ler os registros: " + e.getMessage());
        }
    }

    public static void consultaID(String id) {
        String identificadorDeId = ",";
        boolean registroEncontrado = false;

        try (BufferedReader lerArquivo = new BufferedReader(
                new FileReader(new File(Constantes.CAMINHO_DIRETORIO, Constantes.NOME_ARQUIVO)))) {
            id = id.concat(identificadorDeId);
            logger.info("Buscando ID: " + id);

            String linha;
            while ((linha = lerArquivo.readLine()) != null) {
                if (linha.startsWith(id)) {
                    System.out.println("Registro encontrado: " + linha);
                    logger.info("Registro encontrado: " + linha);
                    registroEncontrado = true;
                    break;
                }
            }
        } catch (IOException e) {
            logger.severe("Erro ao ler os registros: " + e.getMessage());
            System.out.println("Erro ao ler os registros: " + e.getMessage());
        }

        if (!registroEncontrado) {
            logger.warning("ID inválido ou não encontrado: " + id);
            System.out.println("ID inválido ou não encontrado!");
        }
    }

    public static void consultaGerald() throws IOException, InterruptedException {
        Validacoes.limparTela();
        boolean continua;

        do {
            String tipoConsulta = obterTipoConsulta();

            if (tipoConsulta.equals("ID")) {
                consultarPorID();
            } else if (tipoConsulta.equals("TUDO")) {
                consultaGeral();
            } else {
                logger.warning("Opção de consulta inválida: " + tipoConsulta);
                System.out.println("Opção inválida. Tente novamente.");
            }

            continua = perguntarSeDesejaContinuar();
        } while (continua);

        logger.info("Finalizando consulta e retornando ao menu.");
        System.out.println("Salvando ...");
        Thread.sleep(3000);
        Validacoes.limparTela();
        MenuDaTelaMain.chamarMenu();
    }

    private static String obterTipoConsulta() {
        System.out.println("Escolha o tipo de consulta (ID/TUDO):");
        return Constantes.SCAN.nextLine().toUpperCase();
    }

    private static void consultarPorID() {
        boolean idValido = false;
        while (!idValido) {
            System.out.println("Informe o ID para consulta:");
            String idConsulta = Constantes.SCAN.nextLine();
            try {
                consultaID(idConsulta);
                idValido = true;
            } catch (IllegalArgumentException e) {
                logger.warning("Consulta de ID falhou: " + e.getMessage());
                System.out.println(e.getMessage());
                System.out.println("Por favor, insira um ID válido.");
            }
        }
    }

    private static boolean perguntarSeDesejaContinuar() {
        System.out.println("Deseja realizar outra consulta? (S/N):");
        String opcaoContinuar = Constantes.SCAN.nextLine().toUpperCase();
        return opcaoContinuar.equalsIgnoreCase("S") || opcaoContinuar.equalsIgnoreCase("SIM");
    }
}
