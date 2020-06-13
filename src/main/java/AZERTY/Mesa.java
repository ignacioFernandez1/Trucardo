package AZERTY;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Mesa extends JFrame implements ActionListener {
    private JButton quiero;
    private JButton noQuiero;
    private JButton truco;
    private JButton retruco;
    private JButton valeCuatro;
    private JButton envido;
    private JButton realEnvido;
    private JButton faltaEnvido;
    private JButton meVoy;
    private JButton carta1;
    private JButton carta2;
    private JButton carta3;
    private JButton detalles;
    private JButton abandonar;
    private JLabel tabla;
    private JLabel puntajeJ;
    private JLabel puntajeAI;


    private Partida partida;

    public Mesa(Partida j) {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(null);

        partida = j;

        puntajeJ = new JLabel("0");
        puntajeJ.setBounds(900,140,100,50);
        puntajeJ.setForeground(new Color(255,255,255));
        puntajeJ.setFont(new Font("Arial",1,50));

        puntajeAI = new JLabel("0");
        puntajeAI.setBounds(1035,140,100,50);
        puntajeAI.setForeground(new Color(255,255,255));
        puntajeAI.setFont(new Font("Arial",1,50));

        ImageIcon backTabla = new ImageIcon("images/CONFIG.png");
        tabla = new JLabel(backTabla);
        tabla.setBounds(800, 50-8, 360, 242);



        quiero = new JButton("QUIERO");
        quiero.setBounds(800 + 70, 600, 120, 40);
        quiero.addActionListener(this);

        noQuiero = new JButton("NO QUIERO");
        noQuiero.setBounds(935 + 70, 600, 120, 40);
        noQuiero.addActionListener(this);

        valeCuatro = new JButton("VALE CUATRO");
        valeCuatro.setBounds(800, 600 + 75, 120, 30);
        valeCuatro.addActionListener(this);

        retruco = new JButton("RETRUCO");
        retruco.setBounds(800, 650 + 75, 120, 30);
        retruco.addActionListener(this);

        truco = new JButton("TRUCO");
        truco.setBounds(800, 700 + 75, 120, 30);
        truco.addActionListener(this);

        envido = new JButton("ENVIDO");
        envido.setBounds(935, 700 + 75, 120, 30);
        envido.addActionListener(this);

        realEnvido = new JButton("REAL ENVIDO");
        realEnvido.setBounds(935, 650 + 75, 120, 30);
        realEnvido.addActionListener(this);

        faltaEnvido = new JButton("FALTA ENVIDO");
        faltaEnvido.setBounds(935, 600 + 75, 120, 30);
        faltaEnvido.addActionListener(this);

        meVoy = new JButton("ME VOY");
        meVoy.setBounds(1070, 650 + 75, 95, 30);
        meVoy.addActionListener(this);

        detalles = new JButton("DETALLES");
        detalles.setBounds(800, 300, 120, 30);
        detalles.addActionListener(this);

        abandonar = new JButton("ABANDONAR");
        abandonar.setBounds(15, 15, 120, 50);
        abandonar.addActionListener(this);

        this.add(puntajeJ);
        this.add(puntajeAI);
        this.add(tabla);

        this.add(quiero);
        this.add(noQuiero);
        this.add(valeCuatro);
        this.add(retruco);
        this.add(truco);
        this.add(envido);
        this.add(realEnvido);
        this.add(faltaEnvido);
        this.add(meVoy);
        this.add(detalles);
        this.add(abandonar);

        //crear();

    }

    public static void main(String[] args){
        Mesa mesa = new Mesa(new Partida(0,"a",false));
        mesa.setBounds(0,0,1200,900);
        mesa.setLocationRelativeTo(null);
        mesa.setVisible(true);
        mesa.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {


    }
}


