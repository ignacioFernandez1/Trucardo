package AZERTY;

import java.util.Random;

public class Task implements Runnable{
    private AI ai;
    private int queHago;
    private Partida partida;

    public Task(AI ai, Partida partida) {
        this.ai = ai;
        queHago = -1;
        this.partida = partida;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(queHago == 0){
            ai.truco();
        }
        else if(queHago == 1){
            ai.retruco();
        }
        else if(queHago == 2){
            ai.valeCuatro();
        }
        else if(queHago == 3){
            ai.jugarCarta();
        }
        else if(queHago == 4){
            ai.envido();
        }
        else if(queHago == 5){
            ai.envidoEnvido();
        }
        else if(queHago == 6){
            ai.envidoRealEnvido();
        }
        else if(queHago == 7){
            ai.envidoEnvidoRealEnvido();
        }
        else if(queHago == 8){
            ai.realEnvido();
        }
        else if(queHago == 9){
            ai.faltaEnvido();
        }
        else if(queHago == 10){
            partida.iniciarMano();
        }
    }


    public void setQueHago(int queHago){this.queHago = queHago;}
}
