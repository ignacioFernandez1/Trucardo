package AZERTY;

import java.util.Random;

public class Task implements Runnable{
    private AI ai;
    private int queHago;

    public Task(AI ai) {
        this.ai = ai;
        queHago = -1;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(queHago == 0){
            ai.truco();
        }
    }


    public void setQueHago(int queHago){this.queHago = queHago;}
}
