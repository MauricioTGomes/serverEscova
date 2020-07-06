import Jogo.Jogador;
import Jogo.Jogo;
import Jogo.Jogada;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    private static Jogo jogo;

    public static void main(String[] args) throws Exception {
        ServerSocket socket = new ServerSocket(6789);

        Socket socketJogadorUm = socket.accept();
        ObjectInputStream recebeJogadorUm = new ObjectInputStream(socketJogadorUm.getInputStream());
        ObjectOutputStream enviaJogadorUm = new ObjectOutputStream(socketJogadorUm.getOutputStream());

        Jogador jogadorUm = (Jogador) recebeJogadorUm.readObject();
        System.out.println("Jogador um conectou-se " + jogadorUm.getNome() + ". Aguardando jogadores...");

        Socket socketJogadorDois = socket.accept();
        ObjectInputStream recebeJogadosDois = new ObjectInputStream(socketJogadorDois.getInputStream());
        ObjectOutputStream enviaJogadorDois = new ObjectOutputStream(socketJogadorDois.getOutputStream());

        Jogador jogadorDois = (Jogador) recebeJogadosDois.readObject();
        System.out.println("Jogador dois conectou-se " + jogadorDois.getNome() + ". A partida sera iniciada em instantes.");

        jogo = new Jogo(jogadorUm, jogadorDois);
        jogadorUm.setAdversario(jogadorDois);
        jogadorDois.setAdversario(jogadorUm);

        int partida = 1;
        while (partida <= 2) {
            System.out.println(jogadorUm.getNome() + ": " + jogadorUm.getPontos());
            System.out.println(jogadorDois.getNome() + ": " + jogadorDois.getPontos());
            jogo.novaMao();

            while (!jogo.partidaAcabou()) {
                jogo.novaRodada();
                while (!jogo.rodadaAcabou()) {
                    pedirJogada(enviaJogadorUm, jogadorUm);
                    recebeJogada(recebeJogadorUm, jogadorUm);
                    pedirJogada(enviaJogadorDois, jogadorDois);
                    recebeJogada(recebeJogadosDois, jogadorDois);
                }
            }
            partida++;
        }

        enviaJogadorUm.reset();
        enviaJogadorUm.writeObject("Terminou");
        enviaJogadorDois.reset();
        enviaJogadorDois.writeObject("Terminou");

        jogo.exibeResultados();

        socketJogadorUm.close();
        socketJogadorDois.close();
        socket.close();
    }

    public static void pedirJogada(ObjectOutputStream paraJogador, Jogador jogador) {
        try {
            paraJogador.reset();
            paraJogador.writeObject("OK");
            paraJogador.reset();
            paraJogador.writeObject(jogo.getMesa());
            paraJogador.reset();
            paraJogador.writeObject(jogador);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void recebeJogada(ObjectInputStream doJogador, Jogador jogador) {
        try {
            Jogada jogada = (Jogada) doJogador.readObject();

            jogo.setMesa(jogada.getMesa());
            jogador.setMao(jogada.getMao());

            if (jogada.getCapturadas().size() > 0) {
                jogador.insereNaPilha(jogada.getCapturadas());

                if (jogo.getMesa().size() == 0) jogador.setEscovas(jogador.getEscovas() + 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
