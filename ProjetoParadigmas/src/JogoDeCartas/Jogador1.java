package JogoDeCartas;

public class Jogador1 extends Jogador {
	public Jogador1(String nome) {
        super(nome);
    }

    @Override
    public void mensagemVitoria() {
        System.out.println("Jogador 1 " + nome + " é o grande vencedor!");
    }

}
