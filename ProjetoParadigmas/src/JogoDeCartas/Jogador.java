package JogoDeCartas;

import java.util.ArrayList;

public class Jogador {
	protected String nome;
	private int pontos = 5;
	private ArrayList<Cartas> cartas = new ArrayList<>();
	private Cartas cartaSelecionada;
    private int cartaSelecionadaIndex;
	
	public Jogador(String nome) {
		this.nome = nome;
	}
	
	
	public Cartas getCarta(int i) {
		return cartas.get(i);
	}
	
	public Cartas[] getCartas() {
		return cartas.toArray(new Cartas[0]);
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public void ganhouPontos() {
		pontos++;
	}
	
	public void perdeuPontos() {
		pontos--;
	}
	
	public void adicionarCarta(Cartas carta) {
		if(cartas.size() == 5) {
			System.out.println("O jogador: " + nome + " já possui 5 cartas na mão");
		} else{
			cartas.add(carta);
		}
	}
	
	public void removerCarta(int i) {
		cartas.remove(i);
	}

	public int getPontos	() {
		return pontos;
	}
	
	public boolean validarindex(int i) {
		if(i < 0 || i > cartas.size()-1) {
			System.out.println("Posição inválida!");
			return false;
		}
		return true;
	}
	
	public void mensagemVitoria() {
        System.out.println("Parabéns, " + nome + ", você venceu a partida!");
    }


	public Cartas getCartaSelecionada() {
        return cartaSelecionada;
    }


	public void setCartaSelecionada(Cartas carta, int index) {
        this.cartaSelecionada = carta;
        this.cartaSelecionadaIndex = index;
    }

    public int getCartaSelecionadaIndex() {
        return cartaSelecionadaIndex;
    }

}
