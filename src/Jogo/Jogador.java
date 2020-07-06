package Jogo;

import java.io.Serializable;
import java.util.ArrayList;

public class Jogador implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nome;
    private ArrayList<Carta> mao = new ArrayList<Carta>();
    private ArrayList<Carta> pilha = new ArrayList<Carta>();
    private int pontos = 0;
    private int escovas = 0;
    private int ouros = 0;
    private boolean seteOuro = false;
    private int qtdSete = 0;
    private boolean venceu = false;
    private Jogador adversario = null;

    public Jogador(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public Jogador getAdversario() {
        return adversario;
    }

    public void setAdversario(Jogador jogador) {
        this.adversario = jogador;
    }

    public ArrayList<Carta> getMao() {
        return mao;
    }

    public void setMao(ArrayList<Carta> mao) {
        this.mao = mao;
    }

    public ArrayList<Carta> getPilha() {
        return pilha;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public int getPontos() {
        setPontos(pontos + calculaPontos());
        return pontos;
    }

    public void insereNaPilha(ArrayList<Carta> cartas) {
        for (Carta carta : cartas) {
            pilha.add(carta);
            if (carta.getValor() == "7") {
                this.qtdSete += 1;
            }

            if (carta.getNaipe() == "Ouros") {
                this.setOuros(ouros + 1);
                if (carta.getValor() == "7") {
                    this.seteOuro = true;
                }
            }
        }
    }

    public int getEscovas() {
        return escovas;
    }

    public void setEscovas(int escovas) {
        this.escovas = escovas;
    }

    public int getOuros() {
        return ouros;
    }

    public void setOuros(int ouros) {
        this.ouros = ouros;
    }

    public int calculaPontos() {
        int pontos = getEscovas();

        if (this.getPilha().size() > adversario.getPilha().size()) {
            pontos += 1;
        }

        if (this.getOuros() > adversario.getOuros()) {
            pontos += 1;
        }

        if (this.seteOuro) {
            pontos += 1;
        }

        if (this.qtdSete == 4) {
            pontos += 3;
        }

        qtdSete = ouros = escovas = 0;
        seteOuro = false;
        return pontos;
    }
}
