package AZERTY;

import java.util.List;
import java.util.ArrayList;

public class Jugador {
    private ArrayList<Carta> mano;
    private ArrayList<Carta> pila;
    private int puntaje;
    private Mazo mazo;
    private String nombre;
    private boolean quieroT;
    private boolean quieroE;

    public Jugador(String nombre) {
        this.nombre = nombre;
        mano = new ArrayList<>();  // 3 cartas que tiene en una mano
        pila = new ArrayList<>(); // pila = cartas jugadas por el jugadorX
        quieroE = false;
        quieroT = false;
    }

    public boolean getQuieroT() {return quieroT;}

    public void switchQuieroT() {this.quieroT = !this.quieroT;}

    public boolean getQuieroE() {return quieroE;}

    public void switchQuieroE() {this.quieroE = !this.quieroE;}


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

    public List<Carta> getMano() {
        return mano;
    }

    public String toString() {
        return nombre;
    }

    public void actuar(int ronda){

    }

    public Carta getCartaMano(int i){
        return this.mano.get(i);
    }

    public Carta getCartaPila(int i){
        // SE PUEDE IMPLEMENTAR EXCEPCION SI I ES MAYOR QUE EL TAMA;O DE LA PILA

        return this.mano.get(i);
    }

    public int puntos(){
        int valor0 = 0;
        int valor1 = 0;
    if(mano.get(0).getPalo().equals(mano.get(1).getPalo()) && mano.get(0).getPalo().equals(mano.get(2).getPalo())){
        for(int i = 0; i<3;i++){
            if(mano.get(i).getValorint() > valor0){valor0 = mano.get(i).getValorint(); }
            else if(mano.get(i).getValorint() > valor1){valor1 = mano.get(i).getValorint();}
            else{}
        }
        return valor0 + valor1 + 20;

    }
    else if(mano.get(0).getPalo().equals(mano.get(1).getPalo())){
        valor0 = mano.get(0).getValorint();
        valor1 = mano.get(1).getValorint();
        return valor0 + valor1 + 20;

    }
    else if(mano.get(0).getPalo().equals(mano.get(2).getPalo())){
        valor0 = mano.get(0).getValorint();
        valor1 = mano.get(2).getValorint();
        return valor0 + valor1 + 20;

        }
    else if(mano.get(1).getPalo().equals(mano.get(2).getPalo())){
        valor0 = mano.get(1).getValorint();
        valor1 = mano.get(2).getValorint();
        return valor0 + valor1 + 20;

        }
    else{
        if(mano.get(0).getValorint() >= mano.get(1).getValorint() && mano.get(0).getValorint() >= mano.get(2).getValorint()) {
            return mano.get(0).getValorint();
        }
        else if(mano.get(1).getValorint() >= mano.get(2).getValorint()) {
            return mano.get(1).getValorint();
        }
        else{return mano.get(2).getValorint();}
        }

    }


}

