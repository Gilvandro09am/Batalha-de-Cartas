package JogoDeCartas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BatalhaDeCartasIF extends JFrame {
    private Tabuleiro tabuleiro;
    private Jogador jogador1;
    private Jogador jogador2;
    private JTextArea registroDeJogo;
    private JButton[][] tabuleiroButoes;
    private JPanel jogador1Panel;
    private JPanel jogador2Panel;
    private JPanel boardPanel;
    private JLabel placarLabel1;
    private JLabel placarLabel2;
    private int indiceJogadorAtual = 0;
 
    public BatalhaDeCartasIF() {
        setTitle("Batalha de Cartas");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        tabuleiro = new Tabuleiro();
        jogador1 = new Jogador("Jogador 1");
        jogador2 = new Jogador("Jogador 2");
        tabuleiro.distribuirCartas(jogador1);
        tabuleiro.distribuirCartas(jogador2);

        registroDeJogo = new JTextArea(10, 50);
        registroDeJogo.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(registroDeJogo);

        tabuleiroButoes = new JButton[3][3];
        boardPanel = new JPanel(new GridLayout(3, 3));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton button = new JButton("");
                button.addActionListener(new TabuleiroBotaoListener(i, j));
                tabuleiroButoes[i][j] = button;
                boardPanel.add(button);
            }
        }

        jogador1Panel = new JPanel();
        jogador1Panel.setBorder(BorderFactory.createTitledBorder("Jogador 1 Cartas"));
        atualizarPlayerPanel(jogador1Panel, jogador1, 1);

        jogador2Panel = new JPanel();
        jogador2Panel.setBorder(BorderFactory.createTitledBorder("Jogador 2 Cartas"));
        atualizarPlayerPanel(jogador2Panel, jogador2, 2);

        JPanel placarPanel = new JPanel(new GridLayout(1, 2));
        placarPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        placarLabel1 = new JLabel(jogador1.getNome() + ": " + jogador1.getPontos(), SwingConstants.CENTER);
        placarLabel2 = new JLabel(jogador2.getNome() + ": " + jogador2.getPontos(), SwingConstants.CENTER);

        placarLabel1.setFont(new Font("Arial", Font.BOLD, 20));
        placarLabel2.setFont(new Font("Arial", Font.BOLD, 20));

        placarPanel.add(placarLabel1);
        placarPanel.add(placarLabel2);

        add(jogador1Panel, BorderLayout.WEST);
        add(jogador2Panel, BorderLayout.EAST);
        add(boardPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);
        add(placarPanel, BorderLayout.NORTH);

        setVisible(true);
    }

    private void atualizarPlayerPanel(JPanel panel, Jogador jogador, int jogadorNum) {
        panel.removeAll();
        panel.setLayout(new GridLayout(5, 1));
        for (int i = 0; i < jogador.getCartas().length; i++) {
            Cartas carta = jogador.getCarta(i);
            JButton button = new JButton(formatoCartaText(carta, jogadorNum));
            button.addActionListener(new CartaBotaoListener(jogador, carta, i));
            panel.add(button);
        }
        panel.revalidate();
        panel.repaint();
    }

    private String formatoCartaText(Cartas carta, int jogadorNum) {
        return "<html>" +
               "<div style='border:3px solid black; padding:4px; width:80px; height:60px; text-align:center; background-color:black; color:red;'>" +
               "<div style='text-align:center;'><b>C:</b> " + carta.getCima() + "</div>" +
               "<div>" +
               "<table style='width:100%;'>" +
               "<tr>" +
               "<td style='text-align:left;'><b>E:</b> " + carta.getEsquerda() + "</td>" +
               "<td style='text-align:right;'><b>D:</b> " + carta.getDireita() + "</td>" +
               "</tr>" +
               "</table>" +
               "</div>" +
               "<div style='text-align:center;'><b>B:</b> " + carta.getFundo() + "</div>" +
               "<div style='text-align:center;'><b>Jogador:</b> " + jogadorNum + "</div>" +
               "</div>" +
               "</html>";
    }



    
    private void atualizarTabuleiro() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Cartas carta = tabuleiro.getLinha(i)[j];
                if (carta.getJogador() != null) {
                    int jogadorNum = carta.getJogador().equals(jogador1) ? 1 : 2;
                    tabuleiroButoes[i][j].setText(formatoCartaText(carta, jogadorNum));
                    tabuleiroButoes[i][j].setBackground(Color.WHITE);
                    tabuleiroButoes[i][j].setForeground(Color.RED);
                } else {
                    tabuleiroButoes[i][j].setText("");
                    tabuleiroButoes[i][j].setBackground(Color.WHITE);
                    tabuleiroButoes[i][j].setForeground(Color.BLACK);
                }
            }
        }
    }

    private void atualizarPlacar() {
        placarLabel1.setText(jogador1.getNome() + ": " + jogador1.getPontos());
        placarLabel2.setText(jogador2.getNome() + ": " + jogador2.getPontos());
    }

    private void verificarGameOver() {
        if (TabuleiroFull()) {
            String winnerMessage;
            String title;
            int messageType;

            if (jogador1.getPontos() > jogador2.getPontos()) {
                winnerMessage = "<html><div style='text-align: center;'><b style='font-size:14px;color:green;'>Fim do jogo!</b><br><b style='font-size:16px;color:blue;'>O vencedor é: " + jogador1.getNome() + "</b></div></html>";
                title = "Vitória!";
                messageType = JOptionPane.INFORMATION_MESSAGE;
            } else if (jogador2.getPontos() > jogador1.getPontos()) {
                winnerMessage = "<html><div style='text-align: center;'><b style='font-size:14px;color:green;'>Fim do jogo!</b><br><b style='font-size:16px;color:blue;'>O vencedor é: " + jogador2.getNome() + "</b></div></html>";
                title = "Vitória!";
                messageType = JOptionPane.INFORMATION_MESSAGE;
            } else {
                winnerMessage = "<html><div style='text-align: center;'><b style='font-size:14px;color:orange;'>Fim do jogo!</b><br><b style='font-size:16px;color:red;'>Partida Empatada</b></div></html>";
                title = "Empate!";
                messageType = JOptionPane.WARNING_MESSAGE;
            }

            JOptionPane.showMessageDialog(this, winnerMessage, title, messageType);
            atualizarPlacar();
        }
    }

    private boolean TabuleiroFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiro.getLinha(i)[j].getJogador() == null) {
                    return false;
                }
            }
        }
        return true;
    }


    private class CartaBotaoListener implements ActionListener {
        private Jogador jogador;
        private Cartas carta;
        private int cartaIndex;

        public CartaBotaoListener(Jogador jogador, Cartas carta, int cartaIndex) {
            this.jogador = jogador;
            this.carta = carta;
            this.cartaIndex = cartaIndex;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (jogador.equals(getCurrentPlayer())) {
                registroDeJogo.append(jogador.getNome() + " selecionou uma carta.\n");
                setCartaSelecionada(jogador, carta, cartaIndex);
            } else {
                registroDeJogo.append("Não é a vez de " + jogador.getNome() + ".\n");
            }
        }
    }

    private class TabuleiroBotaoListener implements ActionListener {
        private int i, j;

        public TabuleiroBotaoListener(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Jogador currentPlayer = getCurrentPlayer();
            Cartas cartaSelecionada = getCartaSelecionada(currentPlayer);
            if (cartaSelecionada != null && tabuleiro.isIndexValid(i, j) && tabuleiro.isPlaceEmpty(i, j)) {
                if (tabuleiro.jogarCarta(cartaSelecionada, i, j)) {
                    registroDeJogo.append(currentPlayer.getNome() + " jogou uma carta na posição (" + (i + 1) + "," + (j + 1) + ").\n");
                    currentPlayer.removerCarta(getCartaSelecionadaIndex(currentPlayer));
                    switchPlayer();
                    atualizarPlayerPanel(jogador1Panel, jogador1, 1);
                    atualizarPlayerPanel(jogador2Panel, jogador2, 2);
                    atualizarTabuleiro();
                    atualizarPlacar();
                    verificarGameOver();
                }
            } else {
                registroDeJogo.append("Jogada inválida. Tente novamente.\n");
            }
        }
    }

    private Jogador getCurrentPlayer() {
        return indiceJogadorAtual == 0 ? jogador1 : jogador2;
    }

    private void switchPlayer() {
        indiceJogadorAtual = 1 - indiceJogadorAtual;
    }

    private Cartas getCartaSelecionada(Jogador jogador) {
        return jogador.getCartaSelecionada();
    }

    private int getCartaSelecionadaIndex(Jogador jogador) {
        return jogador.getCartaSelecionadaIndex();
    }

    private void setCartaSelecionada(Jogador jogador, Cartas carta, int index) {
        jogador.setCartaSelecionada(carta, index);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BatalhaDeCartasIF::new);
    }
}