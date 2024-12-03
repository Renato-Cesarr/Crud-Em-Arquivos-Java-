package CrudBasico;

import java.util.HashSet;

public class Validacoes {
	static Crud c = new Crud();

	public boolean ValidarEntradaInserir() {
		
			HashSet<String> dadosDePersonagens = new HashSet<String>();
			HashSet<String> dadosDeArmas = new HashSet<String>();
			
			dadosDePersonagens.add("mago");
			dadosDePersonagens.add("guerreiro");
			dadosDePersonagens.add("assasino");
			dadosDePersonagens.add("bardo");
			dadosDePersonagens.add("lutador");
			dadosDePersonagens.add("atirador");
			dadosDePersonagens.add("invocador");

			dadosDeArmas.add("cajado");
			dadosDeArmas.add("espada");
			dadosDeArmas.add("adaga");
			dadosDeArmas.add("cetro");
			dadosDeArmas.add("luvas");
			dadosDeArmas.add("pistola");
			dadosDeArmas.add("grimorio");

			if (c.nomeDoPersonagem.length() >25) {
				System.out.println("nome muito grande");
				return false;
			}else if (c.nomeDoPersonagem.isEmpty() || c.nomeDoPersonagem == null) {
				System.out.println("nome vazio");
				return false;
			}
			String classeUsuario = c.nomeDaClasse.toLowerCase().trim();
			if (!dadosDePersonagens.contains(classeUsuario)) {
				System.out.println("sua classe não existe!");
				return false;
			}
			String armaUsuario = c.tipoDeArma.toLowerCase().trim();
			if (!dadosDeArmas.contains(armaUsuario)) {
				System.out.println("arma invalida");
				return false;
			}
			return true;
		
		
		
/*			if (c.nomeDoPersonagem.length() > 25){
				System.out.println("nome muito grande");
				dadosIncorretos = true;
			}
			else if (c.nomeDoPersonagem.isEmpty()|| c.nomeDoPersonagem == null) {
				System.out.println("insira um nome!");
				dadosIncorretos = true;

			}
			if (c.nomeDaClasse.toLowerCase().trim().equals("mago")    || c.nomeDaClasse.toLowerCase().trim().equals("guerreiro") ||
				c.nomeDaClasse.toLowerCase().trim().equals("assasino")|| c.nomeDaClasse.toLowerCase().trim().equals("bardo") ||
				c.nomeDaClasse.toLowerCase().trim().equals("lutador") || c.nomeDaClasse.toLowerCase().trim().equals("druida")) {
				dadosIncorretos = false;
			}
			else {
				System.out.println("classe invalida");
				dadosIncorretos = true;
			}

			
			if (c.tipoDeArma.toLowerCase().trim().equals("espada") || c.tipoDeArma.toLowerCase().trim().equals("arco") || 
				c.tipoDeArma.toLowerCase().trim().equals("cajado") || c.tipoDeArma.toLowerCase().trim().equals("espadão") || 
				c.tipoDeArma.toLowerCase().trim().equals("tomo")   || c.tipoDeArma.toLowerCase().trim().equals("adaga")){
				System.out.println(c.tipoDeArma);
				dadosIncorretos = false;
			}
			else {
				System.out.println("arma invalida");
				dadosIncorretos = true;
			}
			return dadosIncorretos; */
	}
}
