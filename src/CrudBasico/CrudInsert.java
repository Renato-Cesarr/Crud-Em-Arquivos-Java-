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

public class CrudInsert {

	static Scanner scan = new Scanner(System.in);

	static int id = 1;
	public static String nomeDoPersonagem;
	public static String nomeDaClasse;
	public static String tipoDeArma;
	public static int pontosDeHp;
	public static int pontosDeMana;
    private static final String RESET = "\033[0m";
    private static final String VERMELHO = "\033[31m";
    private static final String VERDE = "\033[32m";
    private static final String AMARELO = "\033[33m";
    private static final String AZUL = "\033[34m";
    private static final String ROXO = "\033[35m";
    private static final String CIANO = "\033[36m";

	static String caminhoParaDiretorio = "/home/almaviva-linux/eclipse-workspace/CrudEmArquivos/ArquivoDoCrud";
	static String nomeArquivo = "CrudBasico.txt";
	static File diretorio = new File(caminhoParaDiretorio);
	static HashMap<Integer, String> idDoUsuario = new HashMap<Integer, String>();

	public static void inserir() throws IOException, InterruptedException {
		exibirLayout();
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
				ValidacaoDeInformacoes = true;
				scan.nextLine();
			}
			ValidacaoDeInformacoes = v.ValidarEntradaInserir();
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
	public static void exibirLayout() {
        System.out.println(AZUL + "==============================================" + RESET);
        System.out.println(AMARELO + "                JOGO DE RPG                   " + RESET);
        System.out.println(AZUL + "==============================================" + RESET);
        System.out.println(VERDE + "Escolha uma classe de personagem:" + RESET);
        System.out.println(AZUL + "----------------------------------------------" + RESET);
        System.out.println(
            CIANO + " 1. Mago         " + VERDE + "5. Lutador" + RESET + "\n" +
            ROXO + " 2. Guerreiro    " + AMARELO + "6. Atirador" + RESET + "\n" +
            VERMELHO + " 3. Assassino    " + AZUL + "7. Invocador" + RESET + "\n" +
            ROXO + " 4. Bardo" + RESET
        );
        System.out.println(AZUL + "----------------------------------------------" + RESET);
        System.out.println(VERDE + "Escolha uma arma para o seu personagem:" + RESET);
        System.out.println(AZUL + "----------------------------------------------" + RESET);
        System.out.println(
            CIANO + " 1. Cajado       " + VERMELHO + "5. Luvas" + RESET + "\n" +
            ROXO + " 2. Espada       " + AMARELO + "6. Pistola" + RESET + "\n" +
            AZUL + " 3. Adaga        " + VERDE + "7. Grimório" + RESET + "\n" +
            VERMELHO + " 4. Cetro        " + CIANO + "8. Arco" + RESET
        );
        System.out.println(AZUL + "----------------------------------------------" + RESET);
        System.out.println(AMARELO + "Nota: Digite o nome correspondente à opção." + RESET);
        System.out.println(AZUL + "==============================================" + RESET);
    }
}
