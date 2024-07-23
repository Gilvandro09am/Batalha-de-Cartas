package JogoDeCartas;

import java.util.Random;

public class Tabuleiro {
private Cartas[][] matriz = new Cartas[3][3];
	
	public Tabuleiro() {
		for(int i = 0; i<3; i++) {
			for(int j = 0; j < 3; j++) {
				matriz[i][j] = new Cartas(0,0,0,0,null);
			}
		}
	}
	
	public Cartas[] getLinha(int i) {
		return matriz[i];
	}

	public boolean jogarCarta(Cartas carta, int i, int j) {
		matriz[i][j] = carta;
		analisarCartasProximas(carta, i, j);
		return true;
	}
	
	public void analisarCartasProximas(Cartas carta, int i, int j) {
		if(i-1 >= 0 && carta.getCima() > matriz[i-1][j].getFundo()) {
			tomarCartaProxima(carta, matriz[i-1][j]);
		}
		if(i+1 <= 2 && carta.getFundo() > matriz[i+1][j].getCima()) {
			tomarCartaProxima(carta, matriz[i+1][j]);
		}
		if(j-1 >= 0 && carta.getEsquerda() > matriz[i][j-1].getDireita()) {
			tomarCartaProxima(carta, matriz[i][j-1]);
		}
		if(j+1 <= 2 && carta.getDireita() > matriz[i][j+1].getEsquerda()) {
			tomarCartaProxima(carta, matriz[i][j+1]);
		}
	}
	
	private void tomarCartaProxima(Cartas cartaJogada, Cartas cartaProxima) {
	    if(cartaProxima.getJogador() != null) {
	        cartaJogada.getJogador().ganhouPontos();
	        cartaProxima.getJogador().perdeuPontos();
	        cartaProxima.setJogador(cartaJogada.getJogador());
	    }
	}
	
	public void distribuirCartas(Jogador Jogador) {
		Random random = new Random();
		for(int i = 0; i < 5; i++) {
			int cima = random.nextInt(10) +1;
			int fundo = random.nextInt(10) +1;
			int esquerda = random.nextInt(10) +1;
			int direita= random.nextInt(10) +1;
			Cartas carta = new Cartas(cima,fundo,esquerda,direita,Jogador);
			Jogador.adicionarCarta(carta);
		}
	}
	
	public boolean isIndexValid(int i, int j) {
		if(i <= 2 && i >= 0 && j <= 2 && j >= 0) {
			return true;
		}
		System.out.println("Posição do tabuleiro inválida!");
		return false;
	}
	
	public boolean isPlaceEmpty( int i, int j) {
		if(matriz[i][j].getCima() != 0) {
			System.out.println("O jogador não pode jogar uma carta em cima de outra!");
			return false;
		} 
		return true;
	}
	

}
