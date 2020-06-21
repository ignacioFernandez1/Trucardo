package AZERTY;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Detalles extends JFrame implements Observador {
    private JLabel nombreJugador;
    private JLabel nombreAI;
    private JLabel fondo;
    private Partida partida;
    private int i;
    private ArrayList<JLabel> truco0;
    private Detalles d;

    public Detalles(Partida p) {

        truco0 = new ArrayList<>();
        partida = p;
        partida.registrar(this);
        i = 1;
        String nombre = partida.getNombreJugador();
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);

        nombreJugador = new JLabel(nombre);
        nombreJugador.setBounds(70, 20, 150, 50);
        nombreJugador.setFont(new Font("Arial",0,20));
        nombreJugador.setForeground(new Color(0,0,0));
        nombreAI = new JLabel("Troy");
        nombreAI.setBounds(70 + 255, 20, 150, 50);
        nombreAI.setFont(new Font("Arial",0,20));
        nombreAI.setForeground(new Color(0,0,0));

        ImageIcon fondo_img = new ImageIcon("images/fondoDetalles.png");
        fondo = new JLabel(fondo_img);
        fondo.setBounds(-10,0,470,600);

        this.add(nombreJugador);
        this.add(nombreAI);
        this.add(fondo);

       // this.crear();

    }


    public void crear() {
        d = new Detalles(partida);
        d.setBounds(0,0,465,600);
        d.setLocationRelativeTo(null);
        d.setVisible(true);
        d.setResizable(false);
    }

    public void abrir(){d.setVisible(true);}
    @Override
    public void actualizar() {
        if(partida.getManos() > i){
            i++;
            truco0.add(new JLabel(""+partida.getEstadisticas().getTruco0()));
            truco0.get(i-2).setBounds(5, 110, 50, 50);
            truco0.get(i-2).setFont(new Font("Arial",0,5));
            truco0.get(i-2).setForeground(new Color(0,0,0));

    }


    }
}