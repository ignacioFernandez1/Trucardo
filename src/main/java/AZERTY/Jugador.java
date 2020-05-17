package AZERTY;

import java.util.List;
import java.util.ArrayList;

public class Jugador {
    private ArrayList<Carta> mano;
    private Mazo mazo;
    private int numero;
    private boolean primero;

    public Jugador(int numero,Mazo mazo,boolean primero) {
        this.mazo = mazo;
        this.numero = numero;
        this.primero=primero;


    }

    private List<Carta> getMano() {
        return mano;
    }

    public String toString() {
        String cartas = "";
        for(Carta carta : mano){

        }
        return cartas;
    }
}