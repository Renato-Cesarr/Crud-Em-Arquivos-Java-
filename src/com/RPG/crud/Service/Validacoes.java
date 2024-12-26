package com.RPG.crud.Service;

import java.util.HashSet;
import java.util.Scanner;
import java.util.logging.Logger;

public class Validacoes {
    private static final Logger logger = Logger.getLogger(Validacoes.class.getName());
    private static final Scanner scan = new Scanner(System.in);
    private static final Constantes cores = new Constantes();

    private static final HashSet<String> DADOS_DE_PERSONAGENS = new HashSet<>();
    private static final HashSet<String> DADOS_DE_ARMAS = new HashSet<>();
    private static final int PONTOS_DE_HABILIDADES_TOTAL = 200;

    static {
        DADOS_DE_PERSONAGENS.add("mago");
        DADOS_DE_PERSONAGENS.add("guerreiro");
        DADOS_DE_PERSONAGENS.add("assasino");
        DADOS_DE_PERSONAGENS.add("bardo");
        DADOS_DE_PERSONAGENS.add("lutador");
        DADOS_DE_PERSONAGENS.add("atirador");
        DADOS_DE_PERSONAGENS.add("invocador");

        DADOS_DE_ARMAS.add("cajado");
        DADOS_DE_ARMAS.add("espada");
        DADOS_DE_ARMAS.add("adaga");
        DADOS_DE_ARMAS.add("cetro");
        DADOS_DE_ARMAS.add("luvas");
        DADOS_DE_ARMAS.add("pistola");
        DADOS_DE_ARMAS.add("grimorio");
        DADOS_DE_ARMAS.add("arco");
    }

    public static boolean validarEntradaUpdate(String nome, String classe, String arma, int vida, int mana) {
        if (!validarNome(nome)) return false;
        if (!validarClasse(classe)) return false;
        if (!validarArma(arma)) return false;
        if (!validarDistribuicaoPontos(vida, mana)) return false;

        return true;
    }

    private static boolean validarNome(String nome) {
        if (nome.length() > 25) {
            logger.warning("Nome do personagem muito grande.");
            return false;
        } else if (nome.isEmpty()) {
            logger.warning("Nome vazio fornecido.");
            return false;
        } else if (DADOS_DE_PERSONAGENS.contains(nome.toLowerCase().trim())) {
            logger.warning("Nome de personagem inválido, corresponde a uma classe.");
            return false;
        } else if (DADOS_DE_ARMAS.contains(nome.toLowerCase().trim())) {
            logger.warning("Nome de personagem inválido, corresponde a uma arma.");
            return false;
        }
        return true;
    }

    private static boolean validarClasse(String classe) {
        if (!DADOS_DE_PERSONAGENS.contains(classe.toLowerCase().trim())) {
            logger.warning("Classe inválida fornecida.");
            return false;
        }
        return true;
    }

    private static boolean validarArma(String arma) {
        if (!DADOS_DE_ARMAS.contains(arma.toLowerCase().trim())) {
            logger.warning("Arma inválida fornecida.");
            return false;
        }
        return true;
    }

    private static boolean validarDistribuicaoPontos(int vida, int mana) {
        if (vida + mana != PONTOS_DE_HABILIDADES_TOTAL) {
            logger.warning("A distribuição de pontos entre vida e mana está incorreta.");
            return false;
        }
        return true;
    }

    public String verificaRespostaSimples(String continuarConsulta) {
        continuarConsulta = "";
        do {
            logger.info(cores.AMARELO + "Deseja realizar outra consulta? (sim/nao): " + cores.RESET);
            String resposta = scan.nextLine().toLowerCase().trim();
            if (resposta.equals("sim")) {
                continuarConsulta = "sim";
            } else if (resposta.equals("não") || resposta.equals("nao")) {
                continuarConsulta = "não";
            } else {
                logger.warning("Resposta inválida fornecida. Responda com 'sim' ou 'não'.");
            }
        } while (!continuarConsulta.equals("sim") && !continuarConsulta.equals("não"));
        return continuarConsulta;
    }

    public static String validarIdouPersonagem() {
        String consulta = "";
        while (true) {
            logger.info(Constantes.AMARELO + "Fazer consulta por ID ou dados do personagem? " + Constantes.RESET);
            consulta = scan.nextLine().toLowerCase().trim();

            if (consulta.equals("id")) {
                logger.info("Informe o ID");
                return consulta;
            } else if (consulta.equals("dados do personagem") || consulta.equals("dados") || consulta.equals("dado do personagem")) {
                logger.info("Informe algum dado, como NOME, CLASSE, TIPO DE ARMA ou TUDO");
                return consulta;
            } else {
                logger.warning("Entrada inválida fornecida para consulta por ID ou dados do personagem.");
            }
        }
    }

    public static int verificaEntradaDeTrocaDeTela(int numero) {
        while (true) {
            logger.info("Digite um número de 1 a 5: ");
            if (scan.hasNextInt()) {
                numero = scan.nextInt();
                if (numero >= 1 && numero <= 5) {
                    return numero;
                } else {
                    logger.warning("Número fora do intervalo para troca de tela.");
                }
            } else {
                logger.warning("Entrada não numérica fornecida para escolha de número de tela.");
                scan.next();
            }
        }
    }

    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        logger.info("Tela limpa.");
    }

    public static String verificarEntrada(String entrada) {
        while (true) {
            logger.info(entrada);
            entrada = scan.nextLine().trim().toLowerCase();
            if (entrada.equals("sim") || entrada.equals("não") || entrada.equals("nao")) {
                return entrada;
            } else {
                logger.info(Constantes.VERMELHO + "Entrada inválida. Tente novamente." + Constantes.RESET);
                logger.warning("Entrada inválida fornecida para confirmação de sim ou não.");
            }
        }
    }
}
