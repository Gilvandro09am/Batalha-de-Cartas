package JogoDeCartas;

public class ClasseAux {
	public static void Menu() {
		System.out.println("Bem Vindo ao Yu-Gi-Oh do Paraguai");
		System.out.println("1 - Cada jogador inicia com 5 cartas e deve jogar uma a cada turno em um espaço vazio");
		System.out.println("2 - Cada carta possui 4 valores um em cima, em baixo, na direita e na esquerda");
		System.out.println("3 - Ganha a carta do oponente aquele cujo valor de uma das pontas da carta for maior do que o das cartas próximas do adversário ");
		System.out.println("4 - Ganha a batalha o jogador que possuir mais cartas no tabuleiro quando ele estiver cheio");
	}
	
	
	private static void linhaDoPlayer(Cartas carta) {
		int cima = carta.getCima();
	    String jogador = (cima == 0) ? "  " : carta.getJogador().getNome();
	    System.out.print(jogador + "         ");
	}
	
	private static void linhaDaBordaSuperior() {
		System.out.print(" _____ " + "    ");
	}
	
	private static void linhaDeCima(Cartas cartas) {
		int cima = cartas.getCima();
	    System.out.print("|  " + (cima == 10 ? "A" : cima) + "  |" + "    ");
	}
	
	private static void linhaDaEsquerdaParaDireita(Cartas cartas) {
		int esquerda = cartas.getEsquerda();
	    int direita = cartas.getDireita();
	    System.out.print("|" + (esquerda == 10 ? "A" : esquerda) + "   " + (direita == 10 ? "A" : direita) + "|" + "    ");
	}
	
	private static void linhaDoFundo(Cartas cartas) {
		int fundo = cartas.getFundo();
	    System.out.print("|  " + (fundo == 10 ? "A" : fundo) + "  |" + "    ");
	}
	private static void linhaDaBordaInferior() {
		System.out.print("|_____|" + "    ");
	}
	
	
	public static void imprimirCartas(Cartas[] cartas) {
		for (int i = 0; i < cartas.length; i++) {
			linhaDoPlayer(cartas[i]);
		}
		System.out.println();
		
		for (int i = 0; i < cartas.length; i++) {
			linhaDaBordaSuperior();
		}
		System.out.println();
		
		for (int i = 0; i < cartas.length; i++) {
			linhaDeCima(cartas[i]);
		}
		System.out.println();
		
		for (int i = 0; i < cartas.length; i++) {
			linhaDaEsquerdaParaDireita(cartas[i]);
		}
		System.out.println();
		
		for (int i = 0; i < cartas.length; i++) {
			linhaDoFundo(cartas[i]);
		}
		System.out.println();
		
		for (int i = 0; i < cartas.length; i++) {
			linhaDaBordaInferior();
		}
		System.out.println();
	}
	
	
	public static void imprimirTabuleiro(Tabuleiro tabuleiro) {
		for(int i = 0; i < 3; i++) {
			imprimirCartas(tabuleiro.getLinha(i));
		}
	}
	
	public static void imprimirPlacar(Jogador p1, Jogador p2) {
		
		if(p1.getPontos() > p2.getPontos()) {
			System.out.println("\n O vencedor da batalha foi o Jogador 1: " + p1.getNome());
			p1.mensagemVitoria();
		}else if(p1.getPontos() < p2.getPontos()){
			System.out.println("\n O vencedor da batalha foi o jogador 2: " + p2.getNome());
			p2.mensagemVitoria();
		} else {
			System.out.println("\n A Os jogadores empataram!!! ");
		}
	}

}
