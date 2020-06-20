package AZERTY;


import java.util.concurrent.TimeUnit;

public class AI extends Jugador {
    private Mesa mesa;
    private Partida partida;
    private int valorCartas;

    private long intervalo;

    public AI(Mesa m) {
        super("AI");
        mesa = m;

    }

    public void setPartida(Partida p){partida = p;}
    public void truco() {
        mesa.log("" + valorCartas);
        if (valorCartas >= 18) {
            partida.jugada("QUIERO", this);
            mesa.log("COSCU: QUIERO TRUCO");
        }


        else{partida.jugada("NO QUIERO",this);
            mesa.log("COSCU: NO QUIERO TRUCO");}
    }


    public void setValor(int valorC) {
        for(int i = 0; i<getMano().size() ;i++){
            mesa.log(getMano().get(i).toString());
        }
        valorCartas = valorC;
    }
}

