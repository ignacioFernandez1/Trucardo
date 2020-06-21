package AZERTY;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Controlador implements ActionListener {

    private Partida partida;
    private Mesa mesa;
    private ThreadPoolExecutor executor;
    private Task task;

    public Controlador(Partida p, Mesa m) {
        partida = p;
        mesa = m;
        executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
        task = new Task(p.getJugador1());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton) e.getSource();
        if(b.getName().equals("QUIERO")){
            if(!partida.jugada(b.getName(),partida.getJugador0())){
                mesa.log("NO SE PUEDE CANTAR "+ b.getName());
            }
            else{mesa.log("QUIERO!");}
        }
        else if(b.getName().equals("NO QUIERO")){
            if(!partida.jugada(b.getName(),partida.getJugador0())){
                mesa.log("NO SE PUEDE CANTAR " + b.getName());
            }
            else{mesa.log("NO QUIERO!");}
        }
        else if(b.getName().equals("VALE CUATRO")){
            if(!partida.jugada(b.getName(),partida.getJugador0())){
                mesa.log("NO SE PUEDE CANTAR " + b.getName());
                System.out.println(partida.getCantos().toString());
            }
            else{
                mesa.log("QUIERO VALE CUATRO!");
                mesa.log("AI: ...");
                task.setQueHago(2);
                executor.execute(task);
            }
        }
        else if(b.getName().equals("RETRUCO")){
            if(!partida.jugada(b.getName(),partida.getJugador0())){
                mesa.log("NO SE PUEDE CANTAR " + b.getName());
            }
            else{
                mesa.log("QUIERO RETRUCO!");
                mesa.log("AI: ...");
                task.setQueHago(1);
                executor.execute(task);
            }
        }
        else if(b.getName().equals("TRUCO")){
            if(!partida.jugada(b.getName(),partida.getJugador0())){
                mesa.log("NO SE PUEDE CANTAR " + b.getName());
            }
            else{
                mesa.log("TRUCO!");
                mesa.log("AI: ...");
                task.setQueHago(0);
                executor.execute(task);
            }
        }
        else if(b.getName().equals("ENVIDO")){
            if(!partida.jugada(b.getName(),partida.getJugador0())){
                mesa.log("NO SE PUEDE CANTAR " + b.getName());
            }
            else{
                mesa.log("ENVIDO!");
                mesa.log("AI: ...");
                if(partida.getCantos().contains("ENVIDO TOPE")){task.setQueHago(5);}
                else{task.setQueHago(4);}
                executor.execute(task);
            }
        }
        else if(b.getName().equals("REAL ENVIDO")){
            if(!partida.jugada(b.getName(),partida.getJugador0())){
                mesa.log("NO SE PUEDE CANTAR " + b.getName());
            }
            else{
                mesa.log("REAL ENVIDO!");
                mesa.log("AI: ...");
                if(!partida.getCantos().contains("ENVIDO")){task.setQueHago(8);}
                else if(partida.getCantos().contains("ENVIDO") && !partida.getCantos().contains("ENVIDO TOPE")){task.setQueHago(6);}
                else if(partida.getCantos().contains("ENVIDO TOPE")){task.setQueHago(7);}
                executor.execute(task);
            }
        }
        else if(b.getName().equals("FALTA ENVIDO")){
            if(!partida.jugada(b.getName(),partida.getJugador0())){
                mesa.log("NO SE PUEDE CANTAR " + b.getName());
            }
            else{
                mesa.log("FALTA ENVIDO!");
                mesa.log("AI: ...");
                task.setQueHago(9);
                executor.execute(task);
            }
        }
        else if(b.getName().equals("ME VOY")){
            if(!partida.jugada(b.getName(),partida.getJugador0())){
                mesa.log("NO SE PUEDE CANTAR " + b.getName());
            }
            else{mesa.log("ME VOY AL MAZO!");}

        }
        else if(b.getName().equals("DETALLES")){
             //
        }
        else if(b.getName().equals("carta1")){
            if(!partida.jugada(b.getName(),partida.getJugador0())){
                mesa.log("NO ES TU TURNO");
            }
            else{
                mesa.log("AI: ...");
                task.setQueHago(3);
                executor.execute(task);
            }
        }
        else if(b.getName().equals("carta2")){
            if(!partida.jugada(b.getName(),partida.getJugador0())){
                mesa.log("NO ES TU TURNO");
            }
            else{
                mesa.log("AI: ...");
                task.setQueHago(3);
                executor.execute(task);
            }
        }
        else if(b.getName().equals("carta3")){
            if(!partida.jugada(b.getName(),partida.getJugador0())){
                mesa.log("NO ES TU TURNO");
            }
            else{
                mesa.log("AI: ...");
                task.setQueHago(3);
                executor.execute(task);
            }
        }
        else if(b.getName().equals("ABANDONAR")){
            System.out.println("ABANDONAR");
            int confirmed = JOptionPane.showConfirmDialog(null,
                    "¿Estas seguro que queres salir?", "Salir",
                    JOptionPane.YES_NO_OPTION);
            if (confirmed == JOptionPane.YES_OPTION) {
                String[] args = {};
                // No inicia el gif de trucardo
                System.exit(0);
            }
        }
    }
}
