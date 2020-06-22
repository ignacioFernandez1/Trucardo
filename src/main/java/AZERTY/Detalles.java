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
    private ArrayList<JLabel> envido0;
    private ArrayList<JLabel> total0;
    private ArrayList<JLabel> truco1;
    private ArrayList<JLabel> envido1;
    private ArrayList<JLabel> total1;
    private ArrayList<JLabel> rondas;
    private Detalles d;
    private JLabel tr01;

    public Detalles(Partida p) {

        truco0 = new ArrayList<>();
        envido0 = new ArrayList<>();
        total0 = new ArrayList<>();
        rondas = new ArrayList<>();
        truco1 = new ArrayList<>();
        envido1 = new ArrayList<>();
        total1 = new ArrayList<>();

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
        nombreAI = new JLabel("AI");
        nombreAI.setBounds(70 + 255, 20, 150, 50);
        nombreAI.setFont(new Font("Arial",0,20));
        nombreAI.setForeground(new Color(0,0,0));

        ImageIcon fondo_img = new ImageIcon("images/fondoDetalles.png");
        fondo = new JLabel(fondo_img);
        fondo.setBounds(-10,0,470,600);

        this.add(nombreJugador);
        this.add(nombreAI);
        this.add(fondo);

        setBounds(0,0,465,600);
        setLocationRelativeTo(null);
        setVisible(false);
        setResizable(false);

    }

    public void abrir(){
        this.setVisible(true);
        this.toFront();
    }
    @Override
    public synchronized void actualizar() {
        if(partida.getManos() > i){
            i++;

            this.remove(fondo);

            this.truco0.add(new JLabel(""+partida.getEstadisticas().getTruco0()));
            this.truco0.get(i-2).setBounds(4, 105 + (i-2)*20, 50, 50);
            this.truco0.get(i-2).setFont(new Font("Arial",0,15));
            this.truco0.get(i-2).setForeground(new Color(0,0,0));
            this.truco0.get(i-2).setVisible(false);
            this.add(truco0.get(i-2));
            this.truco0.get(i-2).setVisible(true);

            this.add(fondo);
            this.remove(fondo);
            this.envido0.add(new JLabel(""+partida.getEstadisticas().getEnvido0()));
            this.envido0.get(i-2).setBounds(70, 105 + (i-2)*20, 50, 50);
            this.envido0.get(i-2).setFont(new Font("Arial",0,15));
            this.envido0.get(i-2).setForeground(new Color(0,0,0));
            this.envido0.get(i-2).setVisible(false);
            this.add(envido0.get(i-2));
            this.envido0.get(i-2).setVisible(true);
            this.add(fondo);
            this.remove(fondo);


            this.total0.add(new JLabel(""+partida.getEstadisticas().getPuntos0()));
            this.total0.get(i-2).setBounds(140, 105 + (i-2)*20, 50, 50);
            this.total0.get(i-2).setFont(new Font("Arial",0,15));
            this.total0.get(i-2).setForeground(new Color(0,0,0));
            this.total0.get(i-2).setVisible(false);
            this.add(total0.get(i-2));
            this.total0.get(i-2).setVisible(true);
            this.add(fondo);
            this.remove(fondo);

            this.rondas.add(new JLabel(""+(partida.getManos()-1)));
            this.rondas.get(i-2).setBounds(206, 105 + (i-2)*20, 50, 50);
            this.rondas.get(i-2).setFont(new Font("Arial",0,15));
            this.rondas.get(i-2).setForeground(new Color(0,0,0));
            this.rondas.get(i-2).setVisible(false);
            this.add(rondas.get(i-2));
            this.rondas.get(i-2).setVisible(true);
            this.add(fondo);
            this.remove(fondo);


            this.truco1.add(new JLabel(""+partida.getEstadisticas().getTruco1()));
            this.truco1.get(i-2).setBounds(258, 105 + (i-2)*20, 50, 50);
            this.truco1.get(i-2).setFont(new Font("Arial",0,15));
            this.truco1.get(i-2).setForeground(new Color(0,0,0));
            this.truco1.get(i-2).setVisible(false);
            this.add(truco1.get(i-2));
            this.truco1.get(i-2).setVisible(true);
            this.add(fondo);
            this.remove(fondo);

            this.envido1.add(new JLabel(""+partida.getEstadisticas().getEnvido1()));
            this.envido1.get(i-2).setBounds(325, 105 + (i-2)*20, 50, 50);
            this.envido1.get(i-2).setFont(new Font("Arial",0,15));
            this.envido1.get(i-2).setForeground(new Color(0,0,0));
            this.envido1.get(i-2).setVisible(false);
            this.add(envido1.get(i-2));
            this.envido1.get(i-2).setVisible(true);

            this.add(fondo);
            this.remove(fondo);
            this.total1.add(new JLabel(""+partida.getEstadisticas().getPuntos1()));
            this.total1.get(i-2).setBounds(395, 105 + (i-2)*20, 50, 50);
            this.total1.get(i-2).setFont(new Font("Arial",0,15));
            this.total1.get(i-2).setForeground(new Color(0,0,0));
            this.total1.get(i-2).setVisible(false);
            this.add(total1.get(i-2));
            this.total1.get(i-2).setVisible(true);

            this.add(fondo);
        }
    }

}
