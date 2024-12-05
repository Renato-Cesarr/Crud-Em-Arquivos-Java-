package CrudValidações;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import CrudBasico.CrudInsert;

public class Validacoes {
	static CrudInsert c = new CrudInsert();
	static Scanner scan = new Scanner(System.in);
    private static final String CIANO = "\033[36m";
    private static final String VERMELHO = "\033[31m";
    private static final String RESET = "\033[0m";
    private static final String AMARELO = "\033[33m";
    static boolean verficicado = false;
    
    
    	
        public static boolean validarEntradaUpdate(String nome, String classe, String arma, int vida, int mana) {
            HashSet<String> dadosDePersonagens = new HashSet<>();
            HashSet<String> dadosDeArmas = new HashSet<>();
            int pontosDeHabilidadesTotal = 200;

            dadosDePersonagens.add("mago");
            dadosDePersonagens.add("guerreiro");
            dadosDePersonagens.add("assasino");
            dadosDePersonagens.add("bardo");
            dadosDePersonagens.add("lutador");
            dadosDePersonagens.add("atirador");
            dadosDePersonagens.add("invocador");

            dadosDeArmas.add("cajado");
            dadosDeArmas.add("espada");
            dadosDeArmas.add("adaga");
            dadosDeArmas.add("cetro");
            dadosDeArmas.add("luvas");
            dadosDeArmas.add("pistola");
            dadosDeArmas.add("grimorio");
            dadosDeArmas.add("arco");

            if (nome.length() > 25) {
                System.out.println("Nome muito grande!");
                return false;
            } else if (nome.isEmpty()) {
                System.out.println("Nome não pode estar vazio!");
                return false;
            }
            if (!dadosDePersonagens.contains(classe.toLowerCase().trim())) {
                System.out.println("Classe inválida!");
                return false;
            }
            if (!dadosDeArmas.contains(arma.toLowerCase().trim())) {
                System.out.println("Arma inválida!");
                return false;
            }
            if (vida + mana != pontosDeHabilidadesTotal) {
                System.out.println("Distribua os 200 pontos corretamente entre vida e mana!");
                return false;
            }
            return true;
        }
	public String verificaRespostaSimples(String continuarConsulta) {
	    continuarConsulta = "";
	    do {
	        System.out.println(AMARELO + "Deseja realizar outra consulta? (sim/nao): " + RESET);
	        String resposta = scan.nextLine().toLowerCase().trim(); 
	        if (resposta.equals("sim")) {  
	            continuarConsulta = "sim";
	        } else if (resposta.equals("não") || resposta.equals("nao")) {
	            continuarConsulta = "não";
	        } else {
	            System.out.println("Resposta inválida! Responda com 'sim' ou 'não'.");
	        }
	    } while (!continuarConsulta.equals("sim") && !continuarConsulta.equals("não"));
	      return continuarConsulta;
	}

	public static String validarIdouPersonagem(String tipoConsulta) {
	    boolean verficicado = false;
	    String consulta = "";

	    do {
	        System.out.println(AMARELO + "Fazer consulta por ID ou dados do personagem? " + RESET);
	        consulta = scan.nextLine().toLowerCase().trim();
	        
	        if (consulta.equals("id")) {
	            System.out.println("Informe o ID");
	            verficicado = true;  
	        } else if (consulta.equals("dados do personagem") ||
	                   consulta.equals("dados") ||
	                   consulta.equals("dado do personagem")) {
	            System.out.println("Informe algum dado, como NOME, CLASSE,TIPO DE ARMA ou TUDO");
	            verficicado = true; 
	        } else {
	            System.out.println("Informe corretamente os dados");
	            verficicado = false;  
	        }

	    } while (!verficicado); 

	    return consulta;  
	}
	public static int verificaEntradaDeTrocaDeTela(int numero) {
		 Scanner scanner = new Scanner(System.in);
	        boolean valido = false;

	        while (!valido) {
	            System.out.print("Digite um número de 1 a 5: ");
	            if (scanner.hasNextInt()) {
	                numero = scanner.nextInt();
	                if (numero >= 1 && numero <= 5) {
	                    valido = true; 
	                } else {
	                    System.out.println("Número fora do intervalo. Tente novamente.");
	                }
	            } else {
	                System.out.println("Entrada inválida. Digite apenas números.");
	                scanner.next(); 
	            }
	    }
			return numero;
	}
    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    public static String verificarEntrada(String entrada) {
    	System.out.println(entrada);
        while (true) {
            if (entrada.trim().toLowerCase().equals("sim") || entrada.trim().toLowerCase().equals("nao")) {
                return entrada;
            } else {
                System.out.println(VERMELHO+"Entrada inválida. Tente novamente."+RESET);
                entrada = scan.nextLine();
            }
        }
    }
    public static boolean validarEntrada(String nomePersonagem, String classePersonagem, String armaPersonagem,
            int pontosVida, int pontosMana) {
        HashSet<String> dadosDePersonagens = new HashSet<>(List.of(
            "mago", "guerreiro", "assasino", "bardo", "lutador", "atirador", "invocador"));
        HashSet<String> dadosDeArmas = new HashSet<>(List.of(
            "cajado", "espada", "adaga", "cetro", "luvas", "pistola", "grimorio", "arco"));

        if (nomePersonagem.length() > 25) {
            System.out.println("Nome muito grande.");
            return false;
        } else if (nomePersonagem.isEmpty()) {
            System.out.println("Nome vazio.");
            return false;
        }

        String classeUsuario = classePersonagem.toLowerCase().trim();
        if (!dadosDePersonagens.contains(classeUsuario)) {
            System.out.println("Classe inválida.");
            return false;
        }

        String armaUsuario = armaPersonagem.toLowerCase().trim();
        if (!dadosDeArmas.contains(armaUsuario)) {
            System.out.println("Arma inválida.");
            return false;
        }

        if ((pontosVida + pontosMana) != 200) {
            System.out.println("Distribuição de pontos inválida. Total deve ser 200.");
            return false;
        }

        return true;
    }
    public static void consulta() {
    	
    }
}
