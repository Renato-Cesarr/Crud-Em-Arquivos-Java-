package Main;
import java.io.IOException;
import java.util.Scanner;

import CrudBasico.CrudDelete;
import CrudBasico.CrudInsert;
import CrudBasico.CrudSelect;
import CrudValidações.Validacoes;

public class MenuDaTelaMain {
	

    public static final String RESET = "\u001B[0m";
    public static final String CYAN = "\u001B[36m";
    public static final String YELLOW = "\u001B[33m";
    public static final String GREEN = "\u001B[32m";
    public static final String RED = "\u001B[31m";
    public static final String PURPLE = "\u001B[35m";
    public static final String WHITE = "\u001B[37m";
    public static final String BLUE = "\u001B[34m";

    public static void chamarMenu() throws InterruptedException, IOException {
        limparTela();
        mostrarTitulo();
        desenhaTelaInicial();
    }

    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void mostrarTitulo() throws InterruptedException {
        String[] titulo = {
                RED + "      ██╗ " + YELLOW + "█████╗ " + GREEN + "██╗   ██╗" + BLUE + " █████╗ " + PURPLE,
                RED + "      ██║" + YELLOW + "██╔══██╗" + GREEN + "██║   ██║" + BLUE + "██╔══██╗" + PURPLE,
                RED + "      ██║" + YELLOW + "███████║" + GREEN + "██║   ██║" + BLUE + "███████║" + PURPLE,
                RED + " ██   ██║" + YELLOW + "██╔══██║" + GREEN + "╚██╗ ██╔╝" + BLUE + "██╔══██║" + PURPLE,
                RED + " ╚█████╔╝" + YELLOW + "██║  ██║" + GREEN + " ╚████╔╝ " + BLUE + "██║  ██║" + PURPLE,
                RED + "  ╚════╝ " + YELLOW + "╚═╝  ╚═╝" + GREEN + "  ╚═══╝  " + BLUE + "╚═╝  ╚═╝" + PURPLE
        };

        System.out.println(PURPLE + "═════════════════════════════════════════════════════════════════" + RESET);
        for (String linha : titulo) {
            System.out.println(linha);
            Thread.sleep(200);
        }
        System.out.println(PURPLE + "═════════════════════════════════════════════════════════════════" + RESET);
        Thread.sleep(500);
        System.out.println(YELLOW + "                 Bem-vindo ao lendário RPG JAVA! 🌟" + RESET);
        Thread.sleep(1000);
        System.out.println();
    }

    public static void desenhaTelaInicial() throws IOException, InterruptedException {
        System.out.println(PURPLE + "╔══════════════════════════════════════════════════════════════════════════════════════╗" + RESET);
        System.out.println(PURPLE + "║" + RED + "        🗡️  PREPARE-SE PARA A MAIOR AVENTURA DO RPG JAVA!  🛡️" + PURPLE + "║" + RESET);
        System.out.println(PURPLE + "╠══════════════════════════════════════════════════════════════════════════════════════╣" + RESET);
        System.out.println(PURPLE + "║" + CYAN + " 🌟 Escolha uma opção e prepare-se para a aventura épica!" + PURPLE +    "║" + RESET);
        System.out.println(PURPLE + "║                                                                                      ║" + RESET);
        System.out.println(PURPLE + "║   " + GREEN + "1️⃣ Criar novo personagem                             🛠️               ║" + RESET);
        System.out.println(PURPLE + "║   " + YELLOW + "2️⃣ Visualizar personagens criados                    👁️              ║" + RESET);
        System.out.println(PURPLE + "║   " + CYAN + "3️⃣ Alterar personagem                                  ✏️              ║" + RESET);
        System.out.println(PURPLE + "║   " + RED + "4️⃣ Deletar personagem                                   ❌              ║" + RESET);
        System.out.println(PURPLE + "║   " + WHITE + "5️⃣ Sair do jogo                                       🚪              ║" + RESET);
        System.out.println(PURPLE + "║                                                                                      ║" + RESET);
        System.out.println(PURPLE + "╚══════════════════════════════════════════════════════════════════════════════════════╝" + RESET);
        System.out.println();
        System.out.println(YELLOW + "⚔️  Digite o número correspondente e pressione ENTER. Boa sorte! ⚔️" + RESET);
        Scanner scan = new Scanner (System.in);
        int m =Validacoes.verificaEntradaDeTrocaDeTela(0);
        
       switch (m) {
	   case 1:{Validacoes.limparTela();
	           CrudInsert.inserir();};
	   case 2:{System.out.print("\033[H\033[2J");
		       System.out.flush();
	           CrudSelect.consultaGerald();}
	   case 3:{System.out.println("em Produção");}
	   case 4:{Validacoes.limparTela();
       			CrudDelete.deletarArquivo();};
	default:
		throw new IllegalArgumentException("Unexpected value: " + m);
	}
    }
}
