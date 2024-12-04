package CrudBasico;

import java.io.IOException;
import java.util.Scanner;

import CrudValidações.Validacoes;

public class CrudDelete {
    private static final String CIANO = "\033[36m";
    private static final String RESET = "\033[0m";
    private static final String VERMELHO = "\033[31m";
    private static final String AMARELO = "\033[33m";
    static Scanner scan = new Scanner(System.in);
    
    
	public static void deletarArquivo() throws IOException {
		consulta();
		System.out.println("informe o ID a ser deletado:");
	}
	public static void consulta() throws IOException {
        System.out.println(AMARELO + "Deseja realizar uma consulta geral antes de deletar? (sim/nao): " + RESET);
        String mostrarConsulta = scan.nextLine();
        mostrarConsulta = Validacoes.verificarEntrada(mostrarConsulta);
        if(mostrarConsulta.toLowerCase().equals("sim")){
        	CrudSelect.consultaGeral();}
        }
}
