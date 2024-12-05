package CrudBasico;

import java.io.*;
import java.util.*;

public class CrudArquivo {

	static String caminhoParaDiretorio = "/home/almaviva-linux/eclipse-workspace/CrudEmArquivos/ArquivoDoCrud";
	static File diretorio = new File(caminhoParaDiretorio);
	static String nomeArquivo = "CrudBasico.txt";
	static String copiaArquivo = "CrudBasicoCopia.txt";
	static Scanner scan = new Scanner(System.in);

	protected static List<String> lerArquivo() throws IOException {
		List<String> linhas = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(new File(diretorio, nomeArquivo)))) {
			String linha;
			while ((linha = reader.readLine()) != null) {
				linhas.add(linha);
			}
		}
		return linhas;
	}

	protected static void salvarArquivo(List<String> linhas) throws IOException {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(diretorio, nomeArquivo)))) {
			for (String linha : linhas) {
				writer.write(linha);
				writer.newLine();
			}
		}
	}

	protected static void criarCopiaArquivo() throws IOException {
		List<String> linhas = lerArquivo();
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(diretorio, copiaArquivo)))) {
			for (String linha : linhas) {
				writer.write(linha);
				writer.newLine();
			}
		}
	}
}
