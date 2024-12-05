package CrudBasico;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import CrudValidações.Constantes;
import CrudValidações.Validacoes;
import Main.MenuDaTelaMain;

public class CrudSelect {

	static String nomeArquivo = "CrudBasico.txt";
	static String caminhoParaDiretorio = "/home/almaviva-linux/eclipse-workspace/CrudEmArquivos/ArquivoDoCrud/CrudBasico.txt";
	static String linha;
	static File diretorio = new File(caminhoParaDiretorio);
	static Scanner scan = new Scanner(System.in);
	static Validacoes validar = new Validacoes();
	static ArrayList<String> listaDeDados = new ArrayList<>();
	static boolean registroEncontrado = false;
	static Validacoes v = new Validacoes();

	public static void consultaGerald() throws IOException, InterruptedException {
		v.limparTela();
		String continuarConsulta;
		boolean continua;
		do {
			String tipoConsulta = "";
			tipoConsulta = v.validarIdouPersonagem(tipoConsulta);

			BufferedReader lerArquivo = new BufferedReader(new FileReader(caminhoParaDiretorio));
			boolean registroEncontrado = false;
			String dadoDaconsulta = scan.nextLine();
			String linha;
			if (tipoConsulta.equals("id")) {
				consultaID(dadoDaconsulta);
			} else if (dadoDaconsulta.toLowerCase().equals("tudo")) {
				consultaGeral();
			} else {
				while ((linha = lerArquivo.readLine()) != null) {
					if (linha.contains(dadoDaconsulta)) {
						System.out.println(Constantes.CIANO + "Registro encontrado: " + linha + Constantes.RESET);
						registroEncontrado = true;
						listaDeDados.add(linha);
					}
				} 
			}
			lerArquivo.close();

			if (!registroEncontrado && !dadoDaconsulta.equals("tudo") && !tipoConsulta.equals("id")) {
				System.out.println(Constantes.VERMELHO + "Nenhum registro encontrado para: " + dadoDaconsulta + Constantes.RESET);
			}
			continuarConsulta = "";
			continua = (validar.verificaRespostaSimples(continuarConsulta).equals("sim"));

		} while (continua);
		Thread.sleep(3000);
		MenuDaTelaMain.chamarMenu();

	}

	public static void consultaGeral() throws IOException {
		BufferedReader lerArquivo = new BufferedReader(new FileReader(caminhoParaDiretorio));
		while ((linha = lerArquivo.readLine()) != null) {
			System.out.println(Constantes.CIANO + "Registro encontrado: " + linha + Constantes.RESET);
		}
	}

	public static void consultaID(String id) throws IOException {
		String identificadorDeId = ",";
		boolean registroEncontrado = false;
		List<String> listaDeDados = new ArrayList<>();

		id = id.concat(identificadorDeId);
		System.out.println("Buscando ID: " + id);

		try (BufferedReader lerArquivo = new BufferedReader(new FileReader(caminhoParaDiretorio))) {
			String linha;
			while ((linha = lerArquivo.readLine()) != null) {
				if (linha.startsWith(id)) {
					System.out.println(Constantes.CIANO + "Registro encontrado: " + linha + Constantes.RESET);
					registroEncontrado = true;
					listaDeDados.add(linha);
				}
			}
		}
		if (!registroEncontrado)

		{
			System.out.println(Constantes.VERMELHO + "ID inválido ou não encontrado!" + Constantes.RESET);
		}
	}

}
