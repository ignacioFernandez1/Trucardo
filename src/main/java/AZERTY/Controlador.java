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
        if(b.getText().equals("QUIERO")){
            System.out.println("QUIERO");
        }
        else if(b.getText().equals("NO QUIERO")){
            System.out.println("NO QUIERO");
        }
        else if(b.getText().equals("VALE CUATRO")){
            System.out.println("VALE CUATRO");
        }
        else if(b.getText().equals("RETRUCO")){
            System.out.println("RETRUCO");
        }
        else if(b.getText().equals("TRUCO")){
            System.out.println("TRUCO");
        }
        else if(b.getText().equals("ENVIDO")){
            System.out.println("ENVIDO");
        }
        else if(b.getText().equals("REAL ENVIDO")){
            System.out.println("REAL ENVIDO");
        }
        else if(b.getText().equals("FALTA ENVIDO")){
            System.out.println("FALTA ENVIDO");
        }
        else if(b.getText().equals("ME VOY")){
            System.out.println("ME VOY");
        }
        else if(b.getText().equals("DETALLES")){
            System.out.println("DETALLES");
        }
        else if(b.getText().equals("ABANDONAR")){
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
