package com.RPG.crud.Repositories;

import com.RPG.crud.Controler.MenuDaTelaMain;
import com.RPG.crud.Service.Constantes;
import com.RPG.crud.Service.Validacoes;
import java.io.*;

public class CrudSelect {
 
	public static void consultaGeral() throws IOException {
		try (BufferedReader lerArquivo = new BufferedReader(
				new FileReader(new File(Constantes.CAMINHO_DIRETORIO, Constantes.NOME_ARQUIVO)))) {
			String linha;
			System.out.println("Registros disponíveis:");
			while ((linha = lerArquivo.readLine()) != null) {
				System.out.println(linha);
			}
		} catch (IOException e) {
			System.out.println("Erro ao ler os registros: " + e.getMessage());
		}
	} 

	public static void consultaID(String id) throws IOException {
		String identificadorDeId = ",";
		boolean registroEncontrado = false;

		try (BufferedReader lerArquivo = new BufferedReader(
				new FileReader(new File(Constantes.CAMINHO_DIRETORIO, Constantes.NOME_ARQUIVO)))) {
			String linha;
			id = id.concat(identificadorDeId);
			System.out.println("Buscando ID: " + id);

			while ((linha = lerArquivo.readLine()) != null) {
				if (linha.startsWith(id)) {
					System.out.println("Registro encontrado: " + linha);
					registroEncontrado = true;
				}
			}
		}

		if (!registroEncontrado) {
			throw new IllegalArgumentException("ID inválido ou não encontrado!");
		}
	}

	public static void consultaGerald() throws IOException, InterruptedException {
		Validacoes.limparTela();
		boolean continua;

		do {
			System.out.println("Escolha o tipo de consulta (ID/TUDO):");
			String tipoConsulta = Constantes.SCAN.nextLine().toUpperCase();

			if (tipoConsulta.equals("ID")) {
				boolean idValido = false;
				while (!idValido) {
					System.out.println("Informe o ID para consulta:");
					String idConsulta = Constantes.SCAN.nextLine();
					try {
						consultaID(idConsulta);
						idValido = true;
					} catch (IllegalArgumentException e) {
						System.out.println(e.getMessage());
						System.out.println("Por favor, insira um ID válido.");
					}
				}
			} else if (tipoConsulta.equals("TUDO")) {
				consultaGeral();
			} else {
				System.out.println("Opção inválida. Tente novamente.");
			}

			System.out.println("Deseja realizar outra consulta? (S/N):");
			String opcaoContinuar = Constantes.SCAN.nextLine().toUpperCase();
			continua = (opcaoContinuar.equalsIgnoreCase("S") || opcaoContinuar.equalsIgnoreCase("SIM"));
			continua = !(opcaoContinuar.equalsIgnoreCase("N") || opcaoContinuar.equalsIgnoreCase("NAO")
					|| opcaoContinuar.equalsIgnoreCase("NÂO"));

		} while (continua);

		System.out.println("Salvando ...");
		Thread.sleep(3000);
		Validacoes.limparTela();
		MenuDaTelaMain.chamarMenu();
	}
}
