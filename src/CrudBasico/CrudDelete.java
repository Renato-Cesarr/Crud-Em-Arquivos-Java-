package CrudBasico;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import CrudValidações.Constantes;
import CrudValidações.Validacoes;
import Main.MenuDaTelaMain;

public class CrudDelete {
    static String caminhoParaDiretorio = "/home/almaviva-linux/eclipse-workspace/CrudEmArquivos/ArquivoDoCrud";
    static File diretorio = new File(caminhoParaDiretorio);
    static String nomeArquivo = "CrudBasico.txt";
    static Constantes c = new Constantes();
    static Scanner scan = new Scanner(System.in);

    public static void deletarArquivo() throws IOException, InterruptedException {
        System.out.println("\033[1;35m✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨\033[0m");
        System.out.println("\033[1;35m✨    " + Constantes.CIANO + "Deletar Registro" + "\033[1;35m   ✨");
        System.out.println("\033[1;35m✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨\033[0m");

        System.out.println("\n" + Constantes.AMARELO + "🔴 Informe o ID a ser deletado (⚡): " + Constantes.RESET);
     
        FileReader fileReader = null;  
        FileWriter fileWriter = null;   
        BufferedReader leitor = null; 
        consultaOpcional();
        
        String copiaArquivo = "CrudBasicoCopia.txt";
        String linha = "";
        System.out.println("Informe o ID a ser deletado:");
        int idAserExcluido = scan.nextInt();
        scan.nextLine();
        
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }

        File arquivoOriginal = new File(diretorio, nomeArquivo);
        File arquivoCopia = new File(diretorio, copiaArquivo);            
        
        fileReader = new FileReader(arquivoOriginal);  
        fileWriter = new FileWriter(arquivoCopia);
        leitor = new BufferedReader(fileReader);
        
        while ((linha = leitor.readLine()) != null) {  
            String[] dados = linha.split(",");
            int idNovoArquivo = Integer.parseInt(dados[0]);
            if (idNovoArquivo != idAserExcluido) {
                fileWriter.write(linha + "\r\n");
            }
        }

        leitor.close();
        fileWriter.close();
        fileReader.close();
        
        File original = new File(caminhoParaDiretorio, nomeArquivo);
        File copia = new File(caminhoParaDiretorio, copiaArquivo);
        if (original.delete()) {
            copia.renameTo(original);
            System.out.println("Registro deletado com sucesso!");
            consultaOpcional();

        } else {
            System.out.println("Erro ao deletar o registro.");
        }
        Thread.sleep(3000);
        MenuDaTelaMain.chamarMenu();
    }

    public static void consultaOpcional() throws IOException {
        System.out.println(Constantes.AMARELO + "Deseja realizar uma consulta geral antes de deletar? (sim/nao): " + Constantes.RESET);
        String mostrarConsulta = scan.nextLine();
        mostrarConsulta = Validacoes.verificarEntrada(mostrarConsulta);
        if(mostrarConsulta.toLowerCase().equals("sim")){
            CrudSelect.consultaGeral();
        }
    }
}

