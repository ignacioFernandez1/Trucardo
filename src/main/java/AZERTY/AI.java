package AZERTY;


import java.util.concurrent.TimeUnit;

public class AI extends Jugador {
    private Mesa mesa;
    private Partida partida;

    private long intervalo;

    public AI(Mesa m) {
        super("AI");
        mesa = m;
    }

    public void setPartida(Partida p){partida = p;}
    public void truco(){
        mesa.log("COSCU: ......");
        partida.jugada("QUIERO",this);
        mesa.log("QUIERO TRUCO");
    }










}

