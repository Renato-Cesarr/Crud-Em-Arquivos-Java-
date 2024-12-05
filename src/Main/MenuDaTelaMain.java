package Main;

import java.io.IOException;
import java.util.Scanner;

import CrudBasico.CrudDelete;
import CrudBasico.CrudInsert;
import CrudBasico.CrudSelect;
import CrudBasico.CrudUpdate;
import CrudValidações.Validacoes;

public class MenuDaTelaMain {

	public static void chamarMenu() throws InterruptedException, IOException {
		desenhaTelaInicial();
	}

	public static void desenhaTelaInicial() throws IOException, InterruptedException {
		
		desenhoDeTelas.tituloTelaInicial();
		desenhoDeTelas.telaMenuInicialOpcoes();
		Scanner scan = new Scanner(System.in);
		int m = Validacoes.verificaEntradaDeTrocaDeTela(0);
		try {
			switch (m) {
			case 1: {
				Validacoes.limparTela();
				CrudInsert.inserir();
			};
			case 2: {
				System.out.print("\033[H\033[2J");
				System.out.flush();
				CrudSelect.consultaGerald();
			}
			case 3: {
				System.out.print("\033[H\033[2J");
				System.out.flush();
				CrudUpdate.alterarArquivo();
				;
			}
			case 4: {
				Validacoes.limparTela();
				CrudDelete.deletarArquivo();
			}
				;
			case 5: {
				System.out.println("Adeus");
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + m);
			}
		} catch (Exception e) {
		}

	}
}
