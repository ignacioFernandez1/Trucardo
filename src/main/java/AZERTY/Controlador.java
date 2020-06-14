package AZERTY;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controlador implements ActionListener {

    private Partida partida;
    private Mesa mesa;

    public Controlador(Partida p, Mesa m) {
        partida = p;
        mesa = m;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton) e.getSource();
        if(b.getName().equals("QUIERO")){
            System.out.println("QUIERO");
        }
        else if(b.getName().equals("NO QUIERO")){
            System.out.println("NO QUIERO");
        }
        else if(b.getName().equals("VALE CUATRO")){
            System.out.println("VALE CUATRO");
        }
        else if(b.getName().equals("RETRUCO")){
            System.out.println("RETRUCO");
        }
        else if(b.getName().equals("TRUCO")){
            System.out.println("TRUCO");
        }
        else if(b.getName().equals("ENVIDO")){
            System.out.println("ENVIDO");
        }
        else if(b.getName().equals("REAL ENVIDO")){
            System.out.println("REAL ENVIDO");
        }
        else if(b.getName().equals("FALTA ENVIDO")){
            System.out.println("FALTA ENVIDO");
        }
        else if(b.getName().equals("ME VOY")){
            System.out.println("ME VOY");

        }
        else if(b.getName().equals("DETALLES")){
            System.out.println("DETALLES");
        }
        else if(b.getName().equals("carta1")){
            System.out.println("carta1");
            System.out.println(partida.getJugador0().getMano().size());
            partida.getJugador0().getMano().remove(0);
            System.out.println(partida.getJugador0().getMano().size());
            partida.notificar();
        }
        else if(b.getName().equals("carta2")){
            System.out.println("carta2");
            System.out.println(partida.getJugador0().getMano().size());
            partida.getJugador0().getMano().remove(1);
            System.out.println(partida.getJugador0().getMano().size());
            partida.notificar();
        }
        else if(b.getName().equals("carta3")){
            System.out.println("carta3");
            System.out.println(partida.getJugador0().getMano().size());
            partida.getJugador0().getMano().remove(2);
            System.out.println(partida.getJugador0().getMano().size());
            partida.notificar();
        }
        else if(b.getName().equals("ABANDONAR")){
            System.out.println("ABANDONAR");
            int confirmed = JOptionPane.showConfirmDialog(null,
                    "¿Estas seguro que queres salir?", "Salir",
                    JOptionPane.YES_NO_OPTION);
            if (confirmed == JOptionPane.YES_OPTION) {
                String[] args = {};
                // No inicia el gif de trucardo
                new App().main(args);
                mesa.dispose();
            }
        }
    }
}
