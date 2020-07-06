package Jogo;

import java.io.Serializable;
import java.util.ArrayList;

public class Jogada implements Serializable {
    private static final long serialVersionUID = 1L;
    private ArrayList<Carta> mao;
    private ArrayList<Carta> mesa;
    private ArrayList<Carta> capturadas = new ArrayList<Carta>();

    public Jogada(ArrayList<Carta> mao, ArrayList<Carta> mesa) {
        this.mao = mao;
        this.mesa = mesa;
    }

    public ArrayList<Carta> getMao() {
        return mao;
    }

    public ArrayList<Carta> getMesa() {
        return mesa;
    }

    public ArrayList<Carta> getCapturadas() {
        return capturadas;
    }

}