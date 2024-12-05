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

public class CrudUpdate {
	static String caminhoParaDiretorio = "/home/almaviva-linux/eclipse-workspace/CrudEmArquivos/ArquivoDoCrud";
	static File diretorio = new File(caminhoParaDiretorio);
	static String nomeArquivo = "CrudBasico.txt";
	static Constantes c = new Constantes();
	static Scanner scan = new Scanner(System.in);
	static FileReader fileReader = null;
	static FileWriter fileWriter = null;
	static BufferedReader leitor = null;
	static int idAserAlterado = 0;
	static String copiaArquivo = "CrudBasicoCopia.txt";
	static String linha = "";

	public static void alterarArquivo() throws IOException, InterruptedException {

		desenhoDeTelas.exibirTelaUpdate();
		CrudDelete.consultaOpcional();

		System.out.println("Informe o ID a ser alterado:");
		idAserAlterado = scan.nextInt();
		scan.nextLine();

		if (!diretorio.exists()) {
			diretorio.mkdirs();
		}
		chamarMudanca();

		leitor.close();
		fileWriter.close();
		fileReader.close();

		File original = new File(caminhoParaDiretorio, nomeArquivo);
		File copia = new File(caminhoParaDiretorio, copiaArquivo);
		if (original.delete()) {
			copia.renameTo(original);
			System.out.println("Registro alterado com sucesso!");
		} else {
			System.out.println("Erro ao alterar o registro.");
		}

		Thread.sleep(3000);
		MenuDaTelaMain.chamarMenu();
	}

	public static void chamarMudanca() throws IOException {
		File arquivoOriginal = new File(diretorio, nomeArquivo);
		File arquivoCopia = new File(diretorio, copiaArquivo);

		fileReader = new FileReader(arquivoOriginal);
		fileWriter = new FileWriter(arquivoCopia);
		leitor = new BufferedReader(fileReader);
		boolean encontrado = false;

		while ((linha = leitor.readLine()) != null) {
			String[] dados = linha.split(",");
			int idNovoArquivo = Integer.parseInt(dados[0]);
			if (idNovoArquivo == idAserAlterado) {
				encontrado = true;
				System.out.println("Registro encontrado: " + linha);
				System.out.println("Informe os novos dados para o registro com ID " + idAserAlterado);

				String novoNome, novaClasse, novaArma;
				int novosPontosVida, novosPontosMana;
				boolean entradaValida;

				do {
					System.out.println("Digite o novo nome do personagem:");
					novoNome = scan.nextLine();
					System.out.println("Digite qual sua classe:");
					novaClasse = scan.nextLine();
					System.out.println("Escolha sua arma:");
					novaArma = scan.nextLine();
					System.out.println("Distribua 200 pontos entre VIDA e Mana:");
					novosPontosVida = scan.nextInt();
					novosPontosMana = scan.nextInt();
					scan.nextLine();

					entradaValida = Validacoes.validarEntradaUpdate(novoNome, novaClasse, novaArma,
							novosPontosVida, novosPontosMana);
					if (!entradaValida) {
						System.out.println("Por favor, insira os dados novamente.");
					}
				} while (!entradaValida);

				String linhaAtualizada = idAserAlterado + "," + novoNome + "," + novaClasse + "," + novaArma + ","
						+ novosPontosVida + "," + novosPontosMana;

				fileWriter.write(linhaAtualizada + "\r\n");
			} else {
				fileWriter.write(linha + "\r\n");
			}
		}
	}
}
