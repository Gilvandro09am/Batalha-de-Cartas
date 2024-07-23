package JogoDeCartas;

public class Cartas {
	private int cima, fundo, esquerda, direita;
	private Jogador jogador;
	
	public Cartas(int cima, int fundo, int esquerda, int direita, Jogador jogador) {
		this.cima = cima;
		this.fundo = fundo;
		this.esquerda = esquerda;
		this.direita = direita;
		this.jogador = jogador;
	}
	
	public int getCima() {
		return cima;
	}
	
	public void setCima(int cima) {
		this.cima = cima;
	}
	
	public int getFundo() {
		return fundo;
	}
	
	
	public void setFundo(int baixo) {
		this.fundo = baixo;
	}
	
	public int getEsquerda() {
		return esquerda;
	}
	
	public void setEsquerda(int esquerda) {
		this.esquerda = esquerda;
	}
	
	public int getDireita() {
		return direita;
	}
	
	public void setDireita(int direita) {
		this.direita = direita;
	}
	
	public Jogador getJogador() {
		return jogador;
	}
 	
	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}

}
