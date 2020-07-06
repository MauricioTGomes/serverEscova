package Jogo;

import java.util.ArrayList;
import java.util.Collections;

public class Jogo {
    private Jogador jogadorUm;
    private Jogador jogadorDois;
    private Baralho baralho;
    private ArrayList<Carta> mesa = new ArrayList<Carta>();

    public Jogo(Jogador jUm, Jogador jDois) {
        this.jogadorUm = jUm;
        this.jogadorDois = jDois;
    }

    public void novaMao() {
        this.mesa = new ArrayList<Carta>();
        this.baralho = new Baralho();
        Collections.shuffle(baralho.getCartas());
        this.distribuiMesa();
    }

    public void novaRodada() {
        this.distribuiCartasJogadores();
    }

    public void distribuiCartasJogadores() {
        int countCartas = baralho.getCartas().size() < 6 ? baralho.getCartas().size() / 2 : 3;

        for (int i = 0; i < countCartas; i++) {
            jogadorUm.getMao().add(baralho.getCartas().remove(baralho.getCartas().size() - 1));
            jogadorDois.getMao().add(baralho.getCartas().remove(baralho.getCartas().size() - 1));
        }
    }

    public void distribuiMesa() {
        int countCartas = baralho.getCartas().size() < 4 ? baralho.getCartas().size() : 4;

        for (int i = 0; i < countCartas; i++) {
            this.getMesa().add(baralho.getCartas().remove(baralho.getCartas().size() - 1));
        }
    }

    public boolean partidaAcabou() {
        return this.getBaralho().getCartas().size() == 0;
    }

    public boolean rodadaAcabou() {
        return jogadorUm.getMao().size() == 0 && jogadorDois.getMao().size() == 0;
    }

    public Jogador getVencedor() {
        Jogador vencedor = jogadorUm;
        if (jogadorDois.getPontos() > jogadorUm.getPontos())
            vencedor = jogadorDois;
        return vencedor;
    }

    public Baralho getBaralho() {
        return baralho;
    }

    public ArrayList<Carta> getMesa() {
        return mesa;
    }

    public void setMesa(ArrayList<Carta> mesa) {
        this.mesa = mesa;
    }

    public void exibeResultados() {
        System.out.println(
                "Jogo.Jogo terminou!\n" +
                "Jogo.Jogador " + this.getVencedor().getNome() + " foi o vencedor!\n" +
                "Pontuacao final:\n" +
                this.getVencedor().getNome() + ": " + this.getVencedor().getPontos() + " pontos." +
                this.getVencedor().getAdversario().getNome() + ": " + this.getVencedor().getAdversario().getPontos() + " pontos."
        );
    }
}
