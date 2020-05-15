package AZERTY;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Mazo {
    private ArrayList<Carta> mazo;

    public Mazo() {
        mazo = new ArrayList<Carta>();
        for(int i = 0; i < 4; i++){
            for(int j = 1; j < 10; j++){
                if(i == 0){
                    mazo.add(new Carta(j, "Espada"));
                }
                else if(i == 1){
                    mazo.add(new Carta(j, "Basto"));
                }
                else if(i == 2){
                    mazo.add(new Carta(j, "Copa"));
                }
                else{
                    mazo.add(new Carta(j, "Oro"));
                }
            }
        }
        mezclar();
    }

    public void mezclar(){
        Collections.shuffle(mazo);
    }

    @Override
    public String toString() {
        String cartas = "";
        for(Carta carta : mazo){
            cartas += carta.getNumero() + " " + carta.getPalo() + " ; ";
        }
        return cartas;
    }

    public ArrayList<Carta> getMazo() {
        return mazo;
    }

    public ArrayList<Carta> repartir3(int jugador) {
        if(jugador == 0){return new ArrayList(mazo.subList(0,3));}
        if(jugador == 1){return new ArrayList(mazo.subList(3,6));}
        else {return new ArrayList(Collections.emptyList());}
    }


}


