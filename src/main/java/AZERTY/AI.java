package AZERTY;


import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.*;

public class AI extends Jugador {
    private Mesa mesa;
    private Partida partida;
    private int valorCartas;

    private IRandomWrapper random = new RandomWrapper();

    private long intervalo;

    public AI(Mesa m) {
        super("AI");
        mesa = m;

    }

    public void setRandomWrapper(IRandomWrapper random){
        this.random = random;
    }

    public void setPartida(Partida p){partida = p;}

    public void truco() {
        if (valorCartas >= 18 && valorCartas < 23) {
            if(partida.jugada("QUIERO", this)){
                mesa.log("AI: QUIERO TRUCO");
            }

        }
        else if (valorCartas >= 23) {
            if(partida.jugada("RETRUCO", this)){
                mesa.log("AI: QUIERO RETRUCO!!");
            }
        }
        else{
            if(partida.jugada("NO QUIERO",this)){
                mesa.log("AI: NO QUIERO TRUCO");
            }
        }
    }

    public void retruco(){
        if (valorCartas >= 22 && valorCartas < 25) {
            if(partida.jugada("QUIERO", this)){
                mesa.log("AI: QUIERO ESE RETURCO");
            }

        }
        else if (valorCartas >= 25) {
            if(partida.jugada("VALE CUATRO", this)){
                mesa.log("AI: QUIERO VALE CUATRO PA!!");
            }

        }
        else{
            if(partida.jugada("NO QUIERO",this)){
                mesa.log("AI: NO QUIERO");
            }
        }
    }

    public void jugarCarta(){
        if(!partida.getCantos().contains("ENVIDO") && !partida.getCantos().contains("REAL ENVIDO") && !partida.getCantos().contains("FALTA ENVIDO") && partida.getRonda() == 1){
            if(random.getFloat() > 0.5 && getPuntos() >= 24){
                if(partida.jugada("ENVIDO", this)){
                    mesa.log("AI: ENVIDO");
                }
                return;
            }
            else if(random.getFloat() > 0.4 && getPuntos() >= 27 ){
                if(partida.jugada("REAL ENVIDO", this)){
                    mesa.log("AI: REAL ENVIDO");
                }
                return;
            }
            else if(random.getFloat() > 0.5 && getPuntos() >= 32){
                if(partida.jugada("FALTA ENVIDO", this)){
                    mesa.log("AI: FAAAALTA ENVIDO");
                }
                return;
            }
            else if (random.getFloat() > 0.9){
                if(partida.jugada("ENVIDO", this)){
                    mesa.log("AI: Y SI TE CANTO ENVIDO?");
                }
                return;
            }
        }
        if(!partida.getCantos().contains("TRUCO")){
            if(random.getFloat() > 0.5 && valorCartas > 20){
                if(partida.jugada("TRUCO", this)){
                    mesa.log("AI: TRUCO CARAJO");
                }
                return;
            }
        }

        if(partida.getCartasJugadas() == 0 || partida.getJugador0().getPila().size() == 0){
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

    public void envido () {
        if (getPuntos() >= 23 && getPuntos() < 28) {
            partida.jugada("QUIERO", this);
            mesa.log("AI: QUIERO ENVIDO");
        }
        else if (getPuntos() >= 28) {
            partida.jugada("ENVIDO", this);
            mesa.log("AI: ENVIDO DIJO LA MUDA!!");

        }
        else{
            partida.jugada("NO QUIERO",this);
            mesa.log("AI: NO QUIERO ENVIDO PA");
        }

    }

    public void envidoEnvido () {
        if (getPuntos() >= 27 && getPuntos() < 30) {
            partida.jugada("QUIERO", this);
            mesa.log("AI: QUIERO");
        }
        else if (getPuntos() >= 29 && getPuntos() < 33) {
            partida.jugada("REAL ENVIDO", this);
            mesa.log("AI: REAL ENVIDO DIJO EL GAUCHO!!");

        }
        else if (getPuntos() >= 33) {
            partida.jugada("FALTA ENVIDO", this);
            mesa.log("AI: FALTA ENVIDO CARAJO!");

        }

        else{
            partida.jugada("NO QUIERO",this);
            mesa.log("AI: NO QUIERO");
        }

    }

    public void realEnvido () {
        if (getPuntos() >= 26 && getPuntos() < 30) {
            partida.jugada("QUIERO", this);
            mesa.log("AI: QUIERO");
        }

        else if (getPuntos() >= 30) {
            partida.jugada("FALTA ENVIDO", this);
            mesa.log("AI: FALTA ENVIDO CARAJO!");

        }

        else{
            partida.jugada("NO QUIERO",this);
            mesa.log("AI: QUE BUA QUERE, NO QUIERO");
        }

    }

    public void envidoRealEnvido () {
        if (getPuntos() >= 27 && getPuntos() < 31) {
            partida.jugada("QUIERO", this);
            mesa.log("AI: QUIERO");
        }

        else if (getPuntos() >= 31) {
            partida.jugada("FALTA ENVIDO", this);
            mesa.log("AI: FALTA ENVIDO CARAJO!");

        }

        else{
            partida.jugada("NO QUIERO",this);
            mesa.log("AI: QUE BUA QUERE, NO QUIERO");
        }

    }

    public void envidoEnvidoRealEnvido () {
        if (getPuntos() >= 29 && getPuntos() < 32) {
            partida.jugada("QUIERO", this);
            mesa.log("AI: EPA CHE, QUIERO");
        }

        else if (getPuntos() >= 32 ) {
            partida.jugada("FALTA ENVIDO", this);
            mesa.log("AI: FALTA ENVIDO CARAJO!");

        }

        else{
            partida.jugada("NO QUIERO",this);
            mesa.log("AI: TAS RE LOCO, NO QUIERO");
        }

    }

    public void faltaEnvido () {
        if (getPuntos() >= 29) {
            partida.jugada("QUIERO", this);
            mesa.log("AI: ME LA RE BANCO, QUIERO!");
        }

        else{
            partida.jugada("NO QUIERO",this);
            mesa.log("AI: QUE?!... NO QUIERO");
        }

    }


    public void setValor(int valorC) {
        Carta aux;
//        for(int i  = 0; i < getMano().size(); i++){
//            System.out.println(getMano().get(i).toString());
//        }
        for(int i = 0; i<getMano().size()-1;i++){
            for(int j = 0; j < getMano().size()-1; j++) {
                if (partida.getReglas().mayorCarta(getMano().get(j), getMano().get(j + 1)) == 1) {
                    aux = getMano().get(j);
                    getMano().set(j, getMano().get(j+1));
                    getMano().set(j+1,aux);
                }
            }
        }
//        for(int i  = 0; i < getMano().size(); i++){
//            System.out.println(getMano().get(i).toString());
//        }
        valorCartas = valorC;
    }
}

