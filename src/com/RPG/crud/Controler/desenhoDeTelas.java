package com.RPG.crud.Controler;

import com.RPG.crud.Service.*;

public class desenhoDeTelas {
	public static final String RESET = "\u001B[0m";
	public static final String CYAN = "\u001B[36m";
	public static final String YELLOW = "\u001B[33m";
	public static final String GREEN = "\u001B[32m";
	public static final String RED = "\u001B[31m";
	public static final String PURPLE = "\u001B[35m";
	public static final String WHITE = "\u001B[37m";
	public static final String BLUE = "\u001B[34m";
	
	
	public static void telaMenuInicialOpcoes() {
		System.out.println(PURPLE
				+ "╔══════════════════════════════════════════════════════════════════════════════════════╗" + RESET);
		System.out.println(PURPLE + "║" + RED + "        🗡️  PREPARE-SE PARA A MAIOR AVENTURA DO RPG JAVA!  🛡️"
				+ PURPLE + "                           ║" + RESET);
		System.out.println(PURPLE
				+ "╠══════════════════════════════════════════════════════════════════════════════════════╣" + RESET);
		System.out.println(PURPLE + "║" + CYAN + " 🌟 Escolha uma opção e prepare-se para a aventura épica!" + PURPLE
				+ "                             ║" + RESET);
		System.out.println(PURPLE
				+ "║                                                                                      ║" + RESET);
		System.out.println(PURPLE + "║   " + GREEN
				+ "1️⃣ Criar novo personagem                             🛠️                              ║" + RESET);
		System.out.println(PURPLE + "║   " + YELLOW
				+ "2️⃣ Visualizar personagens criados                    👁️                              ║" + RESET);
		System.out.println(PURPLE + "║   " + CYAN
				+ "3️⃣ Alterar personagem                                ✏️                              ║" + RESET);
		System.out.println(PURPLE + "║   " + RED
				+ "4️⃣ Deletar personagem                                ❌                             ║" + RESET);
		System.out.println(PURPLE + "║   " + WHITE
				+ "5️⃣ Sair do jogo                                      🚪                             ║" + RESET);
		System.out.println(PURPLE
				+ "║                                                                                      ║" + RESET);
		System.out.println(PURPLE
				+ "╚══════════════════════════════════════════════════════════════════════════════════════╝" + RESET);
		System.out.println();
		System.out.println(YELLOW
				+ "⚔️  Digite o número correspondente e pressione ENTER. Boa sorte nos campos de batalha!! ⚔️" + RESET);
	}
	
	public static void tituloTelaInicial() throws InterruptedException {
	     String[] titulo = {
	                RED + "         ██████╗ " + YELLOW + "██████╗  " + GREEN + "██████╗      " + BLUE + "██████╗" + PURPLE + "██╗  ██╗"+CYAN+" █████╗"+WHITE+" ██████╗ "+RED+"███████╗",
	                RED + "         ██╔══██╗" + YELLOW + "██╔══██╗" + GREEN + "██╔════╝     " + BLUE + "██╔════╝" + PURPLE + "██║  ██║"+CYAN+"██╔══██╗"+WHITE+"██╔══██╗"+RED+"██╔════╝",
	                RED + "         ██████╔╝" + YELLOW + "██████╔╝" + GREEN + "██║  ███╗    " + BLUE + "██║     " + PURPLE + "███████║"+CYAN+"███████║"+WHITE+"██████╔╝"+RED+"███████╗",
	                RED + "         ██╔══██╗" + YELLOW + "██╔═══╝ " + GREEN + "██║   ██║    " + BLUE + "██║     " + PURPLE + "██╔══██║"+CYAN+"██╔══██║"+WHITE+"██╔══██╗"+RED+"╚════██║",
	                RED + "         ██║  ██║" + YELLOW + "██║     " + GREEN + "╚██████╔╝    " + BLUE + "╚██████╗" + PURPLE + "██║  ██║"+CYAN+"██║  ██║"+WHITE+"██║  ██║"+RED+"███████║",
	                RED + "         ╚═╝  ╚═╝" + YELLOW + "╚═╝      " + GREEN + "╚═════╝      " + BLUE + "╚═════╝" + PURPLE + "╚═╝  ╚═╝"+CYAN+"╚═╝  ╚═╝╚"+WHITE+"═╝  ╚═╝"+RED+"╚══════╝"
	        };

	        System.out.println(PURPLE + "════════════════════════════════════════════════════════════════════════════════════════" + RESET);

	        for (String linha : titulo) {
	            System.out.println(linha);
	            Thread.sleep(200); 
	        }

	        System.out.println(PURPLE + "════════════════════════════════════════════════════════════════════════════════════════" + RESET);
	        
	        Thread.sleep(500);
	        System.out.println(YELLOW + "                    Bem-vindo ao lendário RPG JAVA! 🌟" + RESET);
	        Thread.sleep(1000);
	        System.out.println();
	    }
    

	public static void exibirTelaInsert() {
		System.out.println(BLUE + "==============================================" + RESET);
        System.out.println(YELLOW + "                JOGO DE RPG                   " + RESET);
        System.out.println(BLUE + "==============================================" + RESET);
        System.out.println(GREEN + "Escolha uma classe de personagem:" + RESET);
        System.out.println(BLUE + "----------------------------------------------" + RESET);
        System.out.println(
            CYAN + " 1. Mago         " + GREEN + "5. Lutador" + RESET + "\n" +
            PURPLE + " 2. Guerreiro    " + YELLOW + "6. Atirador" + RESET + "\n" +
            RED + " 3. Assassino    " + BLUE + "7. Invocador" + RESET + "\n" +
            PURPLE + " 4. Bardo" + RESET
        );
        System.out.println(BLUE + "----------------------------------------------" + RESET);
        System.out.println(GREEN + "Escolha uma arma para o seu personagem:" + RESET);
        System.out.println(BLUE + "----------------------------------------------" + RESET);
        System.out.println(
            CYAN + " 1. Cajado       " + RED + "5. Luvas" + RESET + "\n" +
            GREEN + " 2. Espada       " + YELLOW + "6. Pistola" + RESET + "\n" +
            BLUE + " 3. Adaga        " + GREEN + "7. Grimório" + RESET + "\n" +
            RED + " 4. Cetro        " + CYAN + "8. Arco" + RESET 
        );
        System.out.println(BLUE + "----------------------------------------------" + RESET);
        System.out.println(YELLOW + "Nota: Digite o nome correspondente à opção." + RESET);
        System.out.println(BLUE + "==============================================" + RESET);
	}
	public static void exibirTelaDelete() {

        System.out.println("\033[1;35m✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨\033[0m");
        System.out.println("\033[1;35m✨ " + Constantes.CIANO + "Deletar Registro" + "\033[1;35m   ✨");
        System.out.println("\033[1;35m✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨\033[0m");

        System.out.println("\n" + Constantes.AMARELO + "🔴 Informe o ID a ser deletado (⚡): " + Constantes.RESET);
     
	}
	public static void exibirTelaUpdate() {
	    System.out.println("\033[1;35m✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨\033[0m");
	    System.out.println("\033[1;35m✨       " + Constantes.CIANO + "Atualizar Registro" + "\033[1;35m        ✨");
	    System.out.println("\033[1;35m✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨\033[0m");

	    System.out.println("\n" + Constantes.AMARELO + "🔴 Informe o ID a ser alterado (⚡): " + Constantes.RESET);


	}
}
