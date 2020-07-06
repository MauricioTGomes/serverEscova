package Jogo;

import java.util.ArrayList;

public class Baralho {
    private static String[] naipes = { "Copas", "Espadas", "Ouros", "Paus" };
    private static String[] valores = { "As", "2", "3", "4", "5", "6", "7", "Q", "J", "K" };
    private ArrayList<Carta> cartas;

    public Baralho() {
        cartas = new ArrayList<Carta>();
        int id = 1;

        for (int j = 0; j < valores.length; j++) {
            for (int k = 0; k < naipes.length; k++) {
                cartas.add(new Carta(id, valores[j], naipes[k], j+1));
                id++;
            }
        }
    }

    public ArrayList<Carta> getCartas() {
        return this.cartas;
    }
}