package com.RPG.crud.validacoes;

import java.util.Scanner;

public class Constantes {
	static int id = 1;
	public static String nomeDoPersonagem;
	public static String nomeDaClasse;
	public static String tipoDeArma;
	public static int pontosDeHp;
	public static int pontosDeMana;
	
    public static final String RESET = "\033[0m";
    public static final String VERMELHO = "\033[31m";
    public static final String VERDE = "\033[32m";
    public static final String AMARELO = "\033[33m";
    public static final String AZUL = "\033[34m";
    public static final String ROXO = "\033[35m";
    public static final String CIANO = "\033[36m";
    
    public static final Scanner scan = new Scanner(System.in);
}