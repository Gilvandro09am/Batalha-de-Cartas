package JogoDeCartas;

public class Jogador2 extends Jogador {
	public Jogador2(String nome) {
        super(nome);
    }

    @Override
    public void mensagemVitoria() {
        System.out.println("Jogador 2 " + nome + " é o grande vencedor!");
    }

}
