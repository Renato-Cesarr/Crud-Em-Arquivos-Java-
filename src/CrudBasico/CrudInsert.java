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
import Main.desenhoDeTelas;

public class CrudInsert {

	static Scanner scan = new Scanner(System.in);

	static int id = 1;
	public static String nomeDoPersonagem;
	public static String nomeDaClasse;
	public static String tipoDeArma;
	public static int pontosDeHp;
	public static int pontosDeMana;
	
	static String caminhoParaDiretorio = "/home/almaviva-linux/eclipse-workspace/CrudEmArquivos/ArquivoDoCrud";
	static String nomeArquivo = "CrudBasico.txt";
	static File diretorio = new File(caminhoParaDiretorio);
	static HashMap<Integer, String> idDoUsuario = new HashMap<Integer, String>();

	public static void inserir() throws IOException, InterruptedException {
		desenhoDeTelas.exibirTelaInsert();
		ordemIDs();
		
		inserirDadosUsuario();
		

		idDoUsuario.put(id, nomeDoPersonagem);

		String textoDoArquivo = id + "," + nomeDoPersonagem + "," + nomeDaClasse +"," + tipoDeArma + "," + pontosDeHp + "," + pontosDeMana + "\n";

		verificarSeDiretorioExisteSalvar(textoDoArquivo);

		id++;
		
        Thread.sleep(3000);
		MenuDaTelaMain.chamarMenu();

	}
	public static void inserirDadosUsuario() {

		Validacoes v = new Validacoes();
		boolean ValidacaoDeInformacoes = false;
		do {
			try {
				System.out.println("Insera os dados:");
				System.out.println("Digite o nome do personagem: ");
				nomeDoPersonagem = scan.nextLine().toLowerCase();
				System.out.println("Digite qual sua classe ");
				nomeDaClasse = scan.nextLine().toLowerCase();
				System.out.println("Escolha sua arma: ");
				tipoDeArma = scan.nextLine().toLowerCase();
				System.out.println("Distribua 200 pontos entre " + "\033[31mVIDA\033[0m" + " e " + "\033[34mMana\033[0m");
				pontosDeHp = scan.nextInt();
				pontosDeMana = scan.nextInt();
				scan.nextLine();
			} catch (Exception e) {
				System.out.println("nao digite texto no lugar dos numeros!");
				//ValidacaoDeInformacoes = true;
				scan.nextLine();
			}
			ValidacaoDeInformacoes = v.validarEntradaUpdate(nomeDoPersonagem,nomeDaClasse,tipoDeArma,pontosDeHp,pontosDeMana);

		} while (!ValidacaoDeInformacoes);
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
