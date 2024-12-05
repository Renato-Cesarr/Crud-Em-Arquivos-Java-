package com.RPG.model;

public class Personagem {
	private String nomeDoPersonagem;
	private String nomeDaClasse;
	private String tipoDeArma;
	private int pontosDeHp;
	private int pontosDeMana;

	public Personagem(String nomeDoPersonagem, String nomeDaClasse, String tipoDeArma, int pontosDeHp,
			int pontosDeMana) {
		this.nomeDoPersonagem = nomeDoPersonagem;
		this.nomeDaClasse = nomeDaClasse;
		this.tipoDeArma = tipoDeArma;
		this.pontosDeHp = pontosDeHp;
		this.pontosDeMana = pontosDeMana;
	}

	public String getNomeDoPersonagem() {
		return nomeDoPersonagem;
	}

	public String getNomeDaClasse() {
		return nomeDaClasse;
	}

	public String getTipoDeArma() {
		return tipoDeArma;
	}

	public int getPontosDeHp() {
		return pontosDeHp;
	}

	public int getPontosDeMana() {
		return pontosDeMana;
	}
}