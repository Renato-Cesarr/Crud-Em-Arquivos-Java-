package CrudBasico;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import CrudValidações.Constantes;
import CrudValidações.Validacoes;
import Main.MenuDaTelaMain;
import Main.desenhoDeTelas;

public class CrudDelete {
	static String caminhoParaDiretorio = "/home/almaviva-linux/eclipse-workspace/CrudEmArquivos/ArquivoDoCrud";
	static File diretorio = new File(caminhoParaDiretorio);
	static String nomeArquivo = "CrudBasico.txt";
	static Scanner scan = new Scanner(System.in);
	static FileReader fileReader = null;
	static FileWriter fileWriter = null;
	static BufferedReader leitor = null;
	static String copiaArquivo = "CrudBasicoCopia.txt";
	static String linha = "";
	static int idAserExcluido = 0;

	public static void deletarArquivo() throws IOException, InterruptedException {
		desenhoDeTelas.exibirTelaDelete();

		consultaOpcional();

		System.out.println("Informe o ID a ser deletado:");
		int idASerExcluido = scan.nextInt();
		scan.nextLine();

		if (!diretorio.exists()) {
			diretorio.mkdirs();
		}

		if (fazerDelete(idASerExcluido)) {
			File original = new File(caminhoParaDiretorio, nomeArquivo);
			File copia = new File(caminhoParaDiretorio, copiaArquivo);

			if (original.delete() && copia.renameTo(original)) {
				System.out.println("Registro deletado com sucesso!");
			} else {
				System.out.println("Erro ao substituir o arquivo original.");
			}
		} else {
			System.out.println("Nenhum registro foi encontrado com o ID informado.");
		}

		Thread.sleep(3000);
		MenuDaTelaMain.chamarMenu();
	}

	public static boolean fazerDelete(int idASerExcluido) throws IOException {
		File arquivoOriginal = new File(diretorio, nomeArquivo);
		File arquivoCopia = new File(diretorio, copiaArquivo);

		boolean registroEncontrado = false;

		try (FileReader fileReader = new FileReader(arquivoOriginal);
				BufferedReader leitor = new BufferedReader(fileReader);
				FileWriter fileWriter = new FileWriter(arquivoCopia)) {
			String linha;
			while ((linha = leitor.readLine()) != null) {
				String[] dados = linha.split(",");
				int idAtual = Integer.parseInt(dados[0]);

				if (idAtual != idASerExcluido) {
					fileWriter.write(linha + System.lineSeparator());
				} else {
					registroEncontrado = true;
				}
				
			}
		} catch (IOException | NumberFormatException e) {
			System.out.println("Erro durante o processamento: " + e.getMessage());
		}

		return registroEncontrado;
	}

	public static void consultaOpcional() throws IOException {
		System.out.println(Constantes.AMARELO + "Deseja realizar uma consulta geral antes de deletar? (sim/nao): "
				+ Constantes.RESET);
		String mostrarConsulta = scan.nextLine();
		mostrarConsulta = Validacoes.verificarEntrada(mostrarConsulta);
		if (mostrarConsulta.equalsIgnoreCase("sim")) {
			CrudSelect.consultaGeral();
		}
	}
}
