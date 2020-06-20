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
            if(!partida.jugada(b.getName(),partida.getJugador0())){
                mesa.log("NO SE PUEDE CANTAR "+ b.getName());
            }
        }
        else if(b.getName().equals("NO QUIERO")){
            if(!partida.jugada(b.getName(),partida.getJugador0())){
                mesa.log("NO SE PUEDE CANTAR " + b.getName());
            }
        }
        else if(b.getName().equals("VALE CUATRO")){
            if(!partida.jugada(b.getName(),partida.getJugador0())){
                mesa.log("NO SE PUEDE CANTAR " + b.getName());
            }
        }
        else if(b.getName().equals("RETRUCO")){
            if(!partida.jugada(b.getName(),partida.getJugador0())){
                mesa.log("NO SE PUEDE CANTAR " + b.getName());
            }
        }
        else if(b.getName().equals("TRUCO")){
            if(!partida.jugada(b.getName(),partida.getJugador0())){
                mesa.log("NO SE PUEDE CANTAR " + b.getName());
            }
        }
        else if(b.getName().equals("ENVIDO")){
            if(!partida.jugada(b.getName(),partida.getJugador0())){
                mesa.log("NO SE PUEDE CANTAR " + b.getName());
            }
        }
        else if(b.getName().equals("REAL ENVIDO")){
            if(!partida.jugada(b.getName(),partida.getJugador0())){
                mesa.log("NO SE PUEDE CANTAR " + b.getName());
            }
        }
        else if(b.getName().equals("FALTA ENVIDO")){
            if(!partida.jugada(b.getName(),partida.getJugador0())){
                mesa.log("NO SE PUEDE CANTAR " + b.getName());
            }
        }
        else if(b.getName().equals("ME VOY")){
            if(!partida.jugada(b.getName(),partida.getJugador0())){
                mesa.log("NO SE PUEDE CANTAR " + b.getName());
            }

        }
        else if(b.getName().equals("DETALLES")){
            if(!partida.jugada(b.getName(),partida.getJugador0())){
                mesa.log("NO SE PUEDE CANTAR " + b.getName());
            }
        }
        else if(b.getName().equals("carta1")){
            if(!partida.jugada(b.getName(),partida.getJugador0())){
                mesa.log("NO SE PUEDE JUGAR UNA CARTA");
            }
        }
        else if(b.getName().equals("carta2")){
            if(!partida.jugada(b.getName(),partida.getJugador0())){
                mesa.log("NO SE PUEDE JUGAR UNA CARTA");
            }
        }
        else if(b.getName().equals("carta3")){
            if(!partida.jugada(b.getName(),partida.getJugador0())){
                mesa.log("NO SE PUEDE JUGAR UNA CARTA");
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
