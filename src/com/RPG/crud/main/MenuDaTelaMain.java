package com.RPG.crud.main;
import com.RPG.crud.basico.*;
import com.RPG.crud.validacoes.*;

import java.io.IOException;
import java.util.Scanner;

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
				break;
			}
			case 2: {
				System.out.print("\033[H\033[2J");
				System.out.flush();
				CrudSelect.consultaGerald();
				break;
			}
			case 3: {
				System.out.print("\033[H\033[2J");
				System.out.flush();
				CrudUpdate.alterarArquivo();
				break;
			}
			case 4: {
				Validacoes.limparTela();
				CrudDelete.deletarArquivo();
				break;
			}
			case 5: {
				System.out.println("Adeus");
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + m);
			}
		} catch (Exception e) {
		}

	}
}
