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

public class CrudUpdate {
	static String caminhoParaDiretorio = "/home/almaviva-linux/eclipse-workspace/CrudEmArquivos/ArquivoDoCrud";
	static File diretorio = new File(caminhoParaDiretorio);
	static String nomeArquivo = "CrudBasico.txt";
	static Constantes c = new Constantes();
	static Scanner scan = new Scanner(System.in);


	public static void alterarArquivo() throws IOException, InterruptedException {
		
	    System.out.println("\033[1;35m✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨\033[0m");
	    System.out.println("\033[1;35m✨       " + Constantes.CIANO + "Atualizar Registro" + "\033[1;35m        ✨");
	    System.out.println("\033[1;35m✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨\033[0m");

	    System.out.println("\n" + Constantes.AMARELO + "🔴 Informe o ID a ser alterado (⚡): " + Constantes.RESET);

		FileReader fileReader = null;
		FileWriter fileWriter = null;
		BufferedReader leitor = null;
		CrudDelete.consultaOpcional();

		String copiaArquivo = "CrudBasicoCopia.txt";
		String linha = "";
		System.out.println("Informe o ID a ser alterado:");
		int idAserAlterado = scan.nextInt();
		scan.nextLine();

		if (!diretorio.exists()) {
			diretorio.mkdirs();
		}

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
				System.out.println("Digite o novo nome do personagem: ");
				String novoNome = scan.nextLine();
				System.out.println("Digite qual sua classe ");
				String novaClasse = scan.nextLine();
				System.out.println("Escolha sua arma: ");
				String novaArma = scan.nextLine();
				System.out.println("Distribua 200 pontos entre " + "\033[31mVIDA\033[0m" + " e " + "\033[34mMana\033[0m");
				int novosPontosVida = scan.nextInt();
				int novosPontosMana = scan.nextInt();
				scan.nextLine();

				String linhaAtualizada = idAserAlterado + "," + novoNome + "," + novaClasse + "," + novaArma + ","
						+ novosPontosVida + "," + novosPontosMana;

				fileWriter.write(linhaAtualizada + "\r\n");
			} else {
				fileWriter.write(linha + "\r\n");
			}
		}

		if (!encontrado) {
			System.out.println("ID não encontrado.");
		}

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
}
