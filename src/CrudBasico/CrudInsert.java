package CrudBasico;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import CrudValidações.Validacoes;
import Main.MenuDaTelaMain;
import Main.Personagem;
import Main.desenhoDeTelas;

public class CrudInsert {

	static Scanner scan = new Scanner(System.in);
	static int id = 1;

	static String caminhoParaDiretorio = "/home/almaviva-linux/eclipse-workspace/CrudEmArquivos/ArquivoDoCrud";
	static String nomeArquivo = "CrudBasico.txt";
	static File diretorio = new File(caminhoParaDiretorio);
	static HashMap<Integer, Personagem> idDoUsuario = new HashMap<Integer, Personagem>();

	public static void inserir() throws IOException, InterruptedException {
		desenhoDeTelas.exibirTelaInsert();
		ordemIDs();

		Personagem personagem = inserirDadosUsuario();

		idDoUsuario.put(id, personagem);

		String textoDoArquivo = id + "," + personagem.getNomeDoPersonagem() + "," + personagem.getNomeDaClasse() + ","
				+ personagem.getTipoDeArma() + "," + personagem.getPontosDeHp() + "," + personagem.getPontosDeMana()
				+ "\n";

		verificarSeDiretorioExisteSalvar(textoDoArquivo);

		id++;

		Thread.sleep(3000);
		MenuDaTelaMain.chamarMenu();
	}

	public static Personagem inserirDadosUsuario() {
		Validacoes v = new Validacoes();
		boolean ValidacaoDeInformacoes = false;
		String nomeDoPersonagem = "";
		String nomeDaClasse = "";
		String tipoDeArma = "";
		int pontosDeHp = 0;
		int pontosDeMana = 0;

		do {
			try {
				System.out.println("Insira os dados:");
				System.out.println("Digite o nome do personagem: ");
				nomeDoPersonagem = scan.nextLine().toLowerCase();
				System.out.println("Digite qual sua classe: ");
				nomeDaClasse = scan.nextLine().toLowerCase();
				System.out.println("Escolha sua arma: ");
				tipoDeArma = scan.nextLine().toLowerCase();
				System.out
						.println("Distribua 200 pontos entre " + "\033[31mVIDA\033[0m" + " e " + "\033[34mMana\033[0m");
				pontosDeHp = scan.nextInt();
				pontosDeMana = scan.nextInt();
				scan.nextLine();
			} catch (Exception e) {
				System.out.println("Não digite texto no lugar dos números!");
				scan.nextLine();
			}
			ValidacaoDeInformacoes = Validacoes.validarEntradaUpdate(nomeDoPersonagem, nomeDaClasse, tipoDeArma,
					pontosDeHp, pontosDeMana);
		} while (!ValidacaoDeInformacoes);

		return new Personagem(nomeDoPersonagem, nomeDaClasse, tipoDeArma, pontosDeHp, pontosDeMana);
	}

	public static void verificarSeDiretorioExisteSalvar(String textoDoArquivo) {
		try {
			if (!diretorio.exists()) {
				diretorio.mkdirs();
			}
			File CrudBasico = new File(diretorio, nomeArquivo);
			try (FileWriter writer = new FileWriter(CrudBasico, true)) {
				writer.write(textoDoArquivo);
				System.out.println("Dados salvos no arquivo: " + CrudBasico.getAbsolutePath());
			}
		} catch (Exception e) {
			System.err.println("Erro ao salvar o arquivo: " + e.getMessage());
		}
	}

	public static void ordemIDs() throws FileNotFoundException, IOException {
		File arquivo = new File(diretorio, nomeArquivo);
		if (arquivo.exists()) {
			try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
				String linha;
				while ((linha = reader.readLine()) != null) {
					String[] dados = linha.split(",");
					int idAtual = Integer.parseInt(dados[0]);
					if (idAtual >= id) {
						id = idAtual + 1;
					}
				}
			}
		}
	}
}
