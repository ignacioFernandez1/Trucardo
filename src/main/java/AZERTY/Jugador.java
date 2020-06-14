package AZERTY;

import java.util.List;
import java.util.ArrayList;

public class Jugador {
    private ArrayList<Carta> mano;
    private ArrayList<Carta> pila;
    private int puntaje;
    private Mazo mazo;
    private String nombre;

    public Jugador(String nombre) {
        this.nombre = nombre;
        mano = new ArrayList<>();
        pila = new ArrayList<>(); // pila = cartas jugadas por el jugadorX
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void addCarta (Carta carta) {
        mano.add(carta);
    }

    public void jugarCarta (Carta carta){ pila.add(carta); }

    public void clearMano () {
        mano.clear();
        pila.clear();
    }

    private List<Carta> getMano() {
        return mano;
    }

    public String toString() {
        return nombre;
    }

    public void actuar(int ronda){

    }


}

