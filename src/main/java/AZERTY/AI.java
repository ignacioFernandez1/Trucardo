package AZERTY;


import java.util.ArrayList;
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
        if (valorCartas >= 18 && valorCartas < 23) {
            partida.jugada("QUIERO", this);
            mesa.log("AI: QUIERO TRUCO");
        }
        else if (valorCartas >= 23) {
            partida.jugada("RETRUCO", this);
            mesa.log("AI: QUIERO RETRUCO!!");

        }
        else{
            partida.jugada("NO QUIERO",this);
            mesa.log("AI: NO QUIERO TRUCO");
        }
    }

    public void retruco(){
        if (valorCartas >= 22 && valorCartas < 25) {
            partida.jugada("QUIERO", this);
            mesa.log("AI: QUIERO RETRUCO");
        }
        else if (valorCartas >= 25) {
            partida.jugada("VALE CUATRO", this);
            mesa.log("AI: QUIERO!!");

        }
        else{
            partida.jugada("NO QUIERO",this);
            mesa.log("AI: NO QUIERO");
        }
    }

    public void jugarCarta(){
        if(partida.getCartasJugadas() == 0){
            partida.jugada("carta1",this);
        }
        else{
            int j = getMano().size()-1;
            for(int i = getMano().size()-1; i >=0 ; i--){
                if(partida.getReglas().mayorCarta(getMano().get(i),partida.getJugador0().getUltimaenPila()) == 0){
                    // si la carta en la mano de la IA es mayor a la ultima carta que jugo el jugador
                    j = i;
                    break;
                }
            }
            j += 1;
            partida.jugada("carta"+String.valueOf(j),this);
        }
    }

    public void valeCuatro(){
        if (valorCartas >= 27) {
            partida.jugada("QUIERO", this);
            mesa.log("AI: QUIERO VALE CUATRO!!");
        }
        else{
            partida.jugada("NO QUIERO",this);
            mesa.log("AI: NO QUIERO");
        }
    }




    public void setValor(int valorC) {
        Carta aux;
        for(int i  = 0; i < getMano().size(); i++){
            System.out.println(getMano().get(i).toString());
        }
        for(int i = 0; i<getMano().size()-1;i++){
            for(int j = 0; j < getMano().size()-1; j++) {
                if (partida.getReglas().mayorCarta(getMano().get(j), getMano().get(j + 1)) == 1) {
                    aux = getMano().get(j);
                    getMano().set(j, getMano().get(j+1));
                    getMano().set(j+1,aux);
                }
            }
        }
        for(int i  = 0; i < getMano().size(); i++){
            System.out.println(getMano().get(i).toString());
        }
        valorCartas = valorC;

    }
}

