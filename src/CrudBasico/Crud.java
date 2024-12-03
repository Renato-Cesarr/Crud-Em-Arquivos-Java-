package CrudBasico;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Crud {
	
	static Scanner scan = new Scanner(System.in);
	
	static int id;
	static  String nomeDoPersonagem;
	static String nomeDaClasse;
	static String tipoDeArma;
	static int pontosDeHp;
	static int pontosDeMana;
	
	static String caminhoParaDiretorio = "/home/almaviva-linux/eclipse-workspace/CrudEmArquivos/ArquivoDoCrud";
	static String nomeArquivo ="CrudBasico.txt";
    static File diretorio = new File(caminhoParaDiretorio);
 

	public static void Inserir () {
		Validacoes v = new Validacoes();
		boolean ValidacaoDeInformacoes;
		do {
			System.out.println("Insera os dados:");
			nomeDoPersonagem = scan.nextLine();
			nomeDaClasse = scan.nextLine();
			tipoDeArma = scan.nextLine();
			pontosDeHp = scan.nextInt();
			pontosDeMana = scan.nextInt();
			scan.nextLine();
			ValidacaoDeInformacoes = v.ValidarEntradaInserir();
		} while (!ValidacaoDeInformacoes);

		
		String textoDoArquivo =nomeDoPersonagem+"\n"+nomeDaClasse+"\n"+tipoDeArma+"\n"+pontosDeHp+"\n"+pontosDeMana;

		try {
			if (!diretorio.exists()) {
				diretorio.mkdirs();
			}
			File CrudBasico = new File (diretorio,nomeArquivo);
			try (FileWriter writer = new FileWriter(CrudBasico)){
				writer.write(textoDoArquivo);
                System.out.println("Dados salvos no arquivo: " + CrudBasico.getAbsolutePath());
			}
		} catch (Exception e) {
				System.err.println("Erro ao salvar o arquivo: " + e.getMessage());

		}

		
	}
	public static void Consultar() {
		
	}
	public static void Update() {
		
	}
	public static void Deletar() {
		
	}
}
