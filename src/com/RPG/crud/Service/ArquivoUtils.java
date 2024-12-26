package com.RPG.crud.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.logging.Logger;

import com.RPG.crud.Repositories.CrudSelect;
import com.RPG.model.Personagem;

public class ArquivoUtils {

	private static final Logger logger = Logger.getLogger(ArquivoUtils.class.getName());

	public static void salvarNoArquivo(String texto, String nomeArquivo) {
		try {
			if (!Constantes.DIRETORIO.exists()) {
				Constantes.DIRETORIO.mkdirs();
			}

			File arquivo = new File(Constantes.DIRETORIO, nomeArquivo);
			try (FileWriter writer = new FileWriter(arquivo, true)) {
				writer.write(texto);
				logger.info("Dados salvos no arquivo: " + arquivo.getAbsolutePath());
			}
		} catch (IOException e) {
			logger.severe("Erro ao salvar o arquivo: " + e.getMessage());
		}
	}

	public static int carregarUltimoId(String nomeArquivo) {
		int id = 1;
		File arquivo = new File(Constantes.DIRETORIO, nomeArquivo);
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
			} catch (IOException e) {
				logger.severe("Erro ao carregar o último ID: " + e.getMessage());
			}
		}
		return id;
	}

	public static String formatarTextoParaArquivo(int id, Personagem personagem) {
		return id + "," + personagem.getNomeDoPersonagem() + "," + personagem.getNomeDaClasse() + ","
				+ personagem.getTipoDeArma() + "," + personagem.getPontosDeHp() + "," + personagem.getPontosDeMana()
				+ "\n";
	}

	public static void consultaOpcional() throws IOException {
		logger.info("Deseja consultar os registros antes de deletar? (S/N):");
		boolean respostaValida = false;
		do {
			String resposta = Constantes.SCAN.nextLine().toUpperCase().trim();
			if (resposta.equals("S")) {
				CrudSelect.consultaGeral();
				respostaValida = true;
			} else if (resposta.equals("N")) {
				respostaValida = true;
			} else {
				logger.warning(Constantes.VERMELHO + "Resposta Invalida" + Constantes.RESET);
			}
		} while (!respostaValida);
	}

	public static int solicitarId(String mensagem) {
		int idAlterar = -1;
		boolean idValido = false;

		while (!idValido) {
			try {
				logger.info(mensagem);
				idAlterar = Constantes.SCAN.nextInt();
				Constantes.SCAN.nextLine();
				idValido = true;
			} catch (InputMismatchException e) {
				logger.warning("Erro: O ID deve ser um número inteiro. Tente novamente.");
				Constantes.SCAN.nextLine();
			}
		}

		return idAlterar;
	}
}
