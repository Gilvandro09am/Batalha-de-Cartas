package JogoDeCartas;

import java.util.Scanner;

public class Main {
	public static boolean realizarJogada(Tabuleiro tabuleiro, Jogador jogador) {
	    Scanner leitor = new Scanner(System.in);
	    System.out.print(jogador.getNome() + " escolha uma carta para jogar: ");
	    int op = leitor.nextInt() -1;
	    if(!jogador.validarindex(op)) {
	        return false;
	    }
	    
	    System.out.print("Informe a linha do tabuleiro: ");
	    int l = leitor.nextInt() -1;
	    System.out.print("Informe a coluna do tabuleiro: ");
	    int c = leitor.nextInt() -1;
	    if(!tabuleiro.isIndexValid(l, c)) return false;
	    if(!tabuleiro.isPlaceEmpty(l, c)) return false;
	    
	    Cartas carta = jogador.getCarta(op);
	    jogador.removerCarta(op);
	    boolean jogadaValida = tabuleiro.jogarCarta(carta, l, c);
	    
	    if (jogadaValida) {
	        System.out.println("Pontuação atual de " + jogador.getNome() + ": " + jogador.getPontos());
	    }
	    
	    return jogadaValida;
	}


	public static void main(String[] args) {
		boolean jogarnovamente = true;
		Scanner leitor = new Scanner(System.in);
		
		while(jogarnovamente) {	
			ClasseAux.Menu();
			Jogador P1 = new Jogador("P1");
			Jogador P2 = new Jogador("P2");
			Tabuleiro tabuleiro = new Tabuleiro();
			
			tabuleiro.distribuirCartas(P1);
			tabuleiro.distribuirCartas(P2);
		
			for(int i = 0; i<9; i++) {
				Jogador jogadoratual;
				if(i%2 == 0) {
					jogadoratual = P1;
				}
			
				else {
					jogadoratual = P2;
				} 
				ClasseAux.imprimirTabuleiro(tabuleiro);
				ClasseAux.imprimirCartas(jogadoratual.getCartas());
				if(!realizarJogada(tabuleiro, jogadoratual)) {
					i--;
				} 
			}
			ClasseAux.imprimirTabuleiro(tabuleiro);
			ClasseAux.imprimirPlacar(P1, P2);
			
			  System.out.print("Deseja jogar novamente? (S/N): ");
		      String resposta = leitor.next().toUpperCase();
		      
		      if(!resposta.equals("S")) {
		    	  jogarnovamente = false;
		      } 
		      System.out.println("\n \n \n \n \n \n \n \n \n \n \n");
			}
		}
}
