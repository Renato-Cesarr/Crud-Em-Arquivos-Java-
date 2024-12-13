package com.RPG.crud.Service;

import java.io.File;
import java.util.Scanner;

public class Constantes {

	public static final String CAMINHO_DIRETORIO = "/opt/dev/projects/Crud-Em-Arquivos-Java-/resources";
	public static final String NOME_ARQUIVO = "CrudBasico.txt";
	public static final String NOME_ARQUIVO_COPIA = "CrudBasicoCopia.txt";
	public static final File DIRETORIO = new File(CAMINHO_DIRETORIO);
	public static final Scanner SCAN = new Scanner(System.in);

	public static final String RESET = "\033[0m";
	public static final String VERMELHO = "\033[31m";
	public static final String VERDE = "\033[32m";
	public static final String AMARELO = "\033[33m";
	public static final String AZUL = "\033[34m";
	public static final String ROXO = "\033[35m";
	public static final String CIANO = "\033[36m";	

}