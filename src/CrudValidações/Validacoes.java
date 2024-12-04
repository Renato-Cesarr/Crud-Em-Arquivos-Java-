package CrudValidações;

import java.util.HashSet;
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
    




	public boolean ValidarEntradaInserir() { 
		
			HashSet<String> dadosDePersonagens = new HashSet<String>();
			HashSet<String> dadosDeArmas = new HashSet<String>();
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


			if (c.nomeDoPersonagem.length() >25) {
				System.out.println("nome muito grande");
				return false;
			}else if (c.nomeDoPersonagem.isEmpty() || c.nomeDoPersonagem == null) {
				System.out.println("nome vazio");
				return false;
			}
			String classeUsuario = c.nomeDaClasse.toLowerCase().trim();
			if (!dadosDePersonagens.contains(classeUsuario)) {
				System.out.println("sua classe não existe!");
				return false;
			}
			String armaUsuario = c.tipoDeArma.toLowerCase().trim();
			if (!dadosDeArmas.contains(armaUsuario)) {
				System.out.println("arma invalida");
				return false;
			}
			if ((c.pontosDeHp + c.pontosDeMana > 200 || c.pontosDeHp + c.pontosDeMana <200 )) {
				System.out.println("Destribua os 200 pontos corretamente");
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
	            System.out.println("Informe algum dado, como NOME, CLASSE, ou TIPO DE ARMA");
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
	        numero = 0;
	        boolean valido = false;

	        while (!valido) {
	            System.out.print("Digite um número de 1 a 4: ");
	            if (scanner.hasNextInt()) {
	                numero = scanner.nextInt();
	                if (numero >= 1 && numero <= 4) {
	                    valido = true; 
	                } else {
	                    System.out.println("Número fora do intervalo. Tente novamente.");
	                }
	            } else {
	                System.out.println("Entrada inválida. Digite apenas números.");
	                scanner.next(); 
	            }
	        return numero;
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

}
