package AZERTY;

import java.util.List;
import java.util.ArrayList;

public class Jugador {
    private ArrayList<Carta> mano;
    private Mazo mazo;
    private int numero;

    public Jugador(int numero,Mazo mazo) {
        this.mazo = mazo;
        this.numero = numero;
        mano = mazo.repartir3(numero);

    }

    private List<Carta> getMano() {
        return mano;
    }

    public String toString() {
        String cartas = "";
        for(Carta carta : mano){
            cartas += carta.getNumero() + " " + carta.getPalo() + " ; ";
        }
        return cartas;
    }
}
