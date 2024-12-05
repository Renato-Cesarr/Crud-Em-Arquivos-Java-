package CrudValidações;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import CrudBasico.CrudInsert;

public class Validacoes {
	static CrudInsert c = new CrudInsert();
	static Scanner scan = new Scanner(System.in);
	static Constantes cores = new Constantes();
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
		} else if (dadosDePersonagens.contains(nome.toLowerCase().trim())) {
			System.out.println("O nome do personagem não pode ser nome de Classes");
			return false;
		} else if (dadosDeArmas.contains(nome.toLowerCase().trim())) {
			System.out.println("O nome do personagem não pode ser o nome das armas");
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
			System.out.println(cores.AMARELO + "Deseja realizar outra consulta? (sim/nao): " + cores.RESET);
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
			System.out.println(cores.AMARELO + "Fazer consulta por ID ou dados do personagem? " + cores.RESET);
			consulta = scan.nextLine().toLowerCase().trim();

			if (consulta.equals("id")) {
				System.out.println("Informe o ID");
				verficicado = true;
			} else if (consulta.equals("dados do personagem") || consulta.equals("dados")
					|| consulta.equals("dado do personagem")) {
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
				System.out.println(cores.VERMELHO + "Entrada inválida. Tente novamente." + cores.RESET);
				entrada = scan.nextLine();
			}
		}
	}

}
