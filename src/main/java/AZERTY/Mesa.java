package AZERTY;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Mesa extends JFrame implements Observador{
    private JButton quiero;
    private JButton noQuiero;
    private JButton truco;
    private JButton retruco;
    private JButton valeCuatro;
    private JButton envido;
    private JButton realEnvido;
    private JButton faltaEnvido;
    private JButton meVoy;
    private ArrayList<JButton> cartas;
    private JButton carta1;
    private JButton carta2;
    private JButton carta3;
    private JButton detalles;
    private JButton abandonar;
    private JLabel tabla;
    private JLabel puntajeJ;
    private JLabel puntajeIA;
    private Controlador controlador;
    private JLabel nombreJugador;
    private JLabel nombreIA;
    private JLabel puntajeMaximoTxt;
    private JLabel puntajeMaximoInt;
    private JLabel cartaj01;
    private JLabel cartaj02;
    private JLabel cartaj03;
    private JLabel cartaj11;
    private JLabel cartaj12;
    private JLabel cartaj13;
    private ArrayList<JLabel> cartasEnMesa;
    private JTextArea log;
    private JScrollPane scroll;
    private JRadioButton tradicional;
    private JRadioButton alternativo;
    private ButtonGroup modo;
    private JLabel background;


    private Partida partida;
    private Detalles detalle;

    public Mesa(int puntajeMax, String nombre, boolean flor) {
        AI ai = new AI(this);
        partida = new Partida (puntajeMax, nombre, flor, ai);
        detalle = new Detalles(partida);
        detalle.crear();
        ai.setPartida(partida);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(null);
        partida.registrar(this);

        cartas = new ArrayList<JButton>();
        cartasEnMesa = new ArrayList<JLabel>();

        controlador = new Controlador(partida, this);

        puntajeJ = new JLabel("0");
        puntajeJ.setBounds(900,140,100,50);
        puntajeJ.setForeground(new Color(255,255,255));
        puntajeJ.setFont(new Font("Arial",1,50));

        puntajeIA = new JLabel("0");
        puntajeIA.setBounds(1035,140,100,50);
        puntajeIA.setForeground(new Color(255,255,255));
        puntajeIA.setFont(new Font("Arial",1,50));

        ImageIcon backTabla = new ImageIcon("images/CONFIG.png");
        tabla = new JLabel(backTabla);
        tabla.setBounds(800, 50-8, 360, 242);

        quiero = new JButton("QUIERO");
        quiero.setName("QUIERO");
        quiero.setBackground(new Color(255,255,255));
        quiero.setBounds(800 + 70, 600, 120, 40);
        quiero.addActionListener(controlador);

        noQuiero = new JButton("NO QUIERO");
        noQuiero.setName("NO QUIERO");
        noQuiero.setBackground(new Color(255,255,255));
        noQuiero.setBounds(935 + 70, 600, 120, 40);
        noQuiero.addActionListener(controlador);

        valeCuatro = new JButton("VALE CUATRO");
        valeCuatro.setName("VALE CUATRO");
        valeCuatro.setBackground(new Color(255,255,255));
        valeCuatro.setBounds(800, 600 + 75, 120, 30);
        valeCuatro.addActionListener(controlador);

        retruco = new JButton("RETRUCO");
        retruco.setName("RETRUCO");
        retruco.setBackground(new Color(255,255,255));
        retruco.setBounds(800, 650 + 75, 120, 30);
        retruco.addActionListener(controlador);

        truco = new JButton("TRUCO");
        truco.setName("TRUCO");
        truco.setBackground(new Color(255,255,255));
        truco.setBounds(800, 700 + 75, 120, 30);
        truco.addActionListener(controlador);

        envido = new JButton("ENVIDO");
        envido.setName("ENVIDO");
        envido.setBackground(new Color(255,255,255));
        envido.setBounds(935, 700 + 75, 120, 30);
        envido.addActionListener(controlador);

        realEnvido = new JButton("REAL ENVIDO");
        realEnvido.setName("REAL ENVIDO");
        realEnvido.setBackground(new Color(255,255,255));
        realEnvido.setBounds(935, 650 + 75, 120, 30);
        realEnvido.addActionListener(controlador);

        faltaEnvido = new JButton("FALTA ENVIDO");
        faltaEnvido.setName("FALTA ENVIDO");
        faltaEnvido.setBackground(new Color(255,255,255));
        faltaEnvido.setBounds(935, 600 + 75, 120, 30);
        faltaEnvido.addActionListener(controlador);

        meVoy = new JButton("ME VOY");
        meVoy.setName("ME VOY");
        meVoy.setBackground(new Color(255,255,255));
        meVoy.setBounds(1070, 650 + 75, 95, 30);
        meVoy.addActionListener(controlador);

        detalles = new JButton("DETALLES");
        detalles.setName("DETALLES");
        detalles.setBackground(new Color(255,255,255));
        detalles.setBounds(800, 300, 120, 30);
        detalles.addActionListener(controlador);

        abandonar = new JButton("ABANDONAR");
        abandonar.setName("ABANDONAR");
        abandonar.setBackground(new Color(255,255,255));
        abandonar.setBounds(15, 15, 120, 50);
        abandonar.addActionListener(controlador);

        nombreJugador = new JLabel(partida.getNombreJugador());
        nombreJugador.setBounds(875,65,100,50);
        nombreJugador.setForeground(new Color(255,255,255));
        nombreJugador.setFont(new Font("Arial",1,15));

        nombreIA = new JLabel("IA");
        nombreIA.setBounds(1045,65,100,50);
        nombreIA.setForeground(new Color(255,255,255));
        nombreIA.setFont(new Font("Arial",1,15));

        puntajeMaximoTxt = new JLabel("Objetivo: ");
        puntajeMaximoTxt.setBounds(820,225,100,50);
        puntajeMaximoTxt.setForeground(new Color(255,255,255));
        puntajeMaximoTxt.setFont(new Font("Arial",1,10));

        puntajeMaximoInt = new JLabel(String.valueOf(partida.getPuntajeMaximo()) + " puntos");
        puntajeMaximoInt.setBounds(880,225,100,50);
        puntajeMaximoInt.setForeground(new Color(255,255,255));
        puntajeMaximoInt.setFont(new Font("Arial",1,10));

        carta1 = new JButton();
        carta1.setBorderPainted(false);
        carta1.setName("carta1");
        carta1.setBounds(120, 600, 160, 240);
        carta1.addActionListener(controlador);
        carta1.setVisible(false);
        carta1.setContentAreaFilled(false);

        carta2 = new JButton();
        carta2.setBorderPainted(false);
        carta2.setName("carta2");
        carta2.setBounds(295, 600, 160, 240);
        carta2.addActionListener(controlador);
        carta2.setVisible(false);
        carta2.setContentAreaFilled(false);

        carta3 = new JButton();
        carta3.setBorderPainted(false);
        carta3.setName("carta3");
        carta3.setBounds(470, 600, 160, 240);
        carta3.addActionListener(controlador);
        carta3.setVisible(false);
        carta3.setContentAreaFilled(false);

        cartaj01 = new JLabel();
        cartaj01.setBounds(190, 300, 160, 240);
        cartaj01.setVisible(false);

        cartaj02 = new JLabel();
        cartaj02.setBounds(370, 300, 160, 240);
        cartaj02.setVisible(false);

        cartaj03 = new JLabel();
        cartaj03.setBounds(550, 300, 160, 240);
        cartaj03.setVisible(false);

        cartaj11 = new JLabel();
        cartaj11.setBounds(190, 30, 160, 240);
        cartaj11.setVisible(false);

        cartaj12 = new JLabel();
        cartaj12.setBounds(370, 30, 160, 240);
        cartaj12.setVisible(false);

        cartaj13 = new JLabel();
        cartaj13.setBounds(550, 30, 160, 240);
        cartaj13.setVisible(false);

        cartasEnMesa.add(cartaj01);
        cartasEnMesa.add(cartaj02);
        cartasEnMesa.add(cartaj03);
        cartasEnMesa.add(cartaj11);
        cartasEnMesa.add(cartaj12);
        cartasEnMesa.add(cartaj13);

        cartas.add(carta1);
        cartas.add(carta2);
        cartas.add(carta3);

        log = new JTextArea();
        log.setEditable(false);
        scroll = new JScrollPane(log);
        scroll.setSize(360,220);
        scroll.setBounds(800,360,360,220);
        log.setWrapStyleWord(true);

        tradicional = new JRadioButton("Tradicional");
        tradicional.setName("TRADICIONAL");
        tradicional.setBounds(950,300,100,30);
        tradicional.setSelected(true);
        tradicional.setContentAreaFilled(false);
        tradicional.setBorderPainted(false);
        tradicional.setFocusPainted(false);

        alternativo = new JRadioButton("Alternativo");
        alternativo.setName("ALTERNATIVO");
        alternativo.setBounds(1050,300,100,30);
        alternativo.setContentAreaFilled(false);
        alternativo.setBorderPainted(false);
        alternativo.setFocusPainted(false);

        modo = new ButtonGroup();
        modo.add(tradicional);
        modo.add(alternativo);

        ImageIcon back = new ImageIcon("images/backgroundMesa.png");
        background = new JLabel(back);
        background.setBounds(0,0,1200,900);


        this.add(tradicional);
        this.add(alternativo);
        this.add(scroll);
        this.add(cartaj01);
        this.add(cartaj02);
        this.add(cartaj03);
        this.add(cartaj11);
        this.add(cartaj12);
        this.add(cartaj13);
        this.add(puntajeJ);
        this.add(puntajeIA);
        this.add(nombreJugador);
        this.add(nombreIA);
        this.add(puntajeMaximoTxt);
        this.add(puntajeMaximoInt);
        this.add(tabla);

        this.add(carta1);
        this.add(carta2);
        this.add(carta3);
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
        add(background);

        //crear();

        partida.iniciarPartida();
    }

    public static void main(String[] args){
        Mesa mesa = new Mesa(10, "nacho",true);
        mesa.setBounds(0,0,1200,900);
        mesa.setLocationRelativeTo(null);
        mesa.setVisible(true);
        mesa.setResizable(false);
    }

    @Override
    public void actualizar() {
        //setea imagenes de cartas disponibles

        for (int i = 0; i < 3; i++){
            if(i < partida.getJugador0().getMano().size()){
                ImageIcon img = partida.getJugador0().getMano().get(i).getImg();
                cartas.get(i).setVisible(true);
                cartas.get(i).setIcon(img);
            }
            else{
                cartas.get(i).setVisible(false);
            }
        }
        for(int i = 0; i < 3; i++){
            if(i < partida.getJugador0().getPila().size()){
                ImageIcon img = partida.getJugador0().getPila().get(i).getImg();
                cartasEnMesa.get(i).setVisible(true);
                cartasEnMesa.get(i).setIcon(img);
            }
            else {cartasEnMesa.get(i).setVisible(false);}
        }
        for(int i = 0; i < 3; i++){
            if(i < partida.getJugador1().getPila().size()){
                cartasEnMesa.get(i+3).setVisible(true);
                cartasEnMesa.get(i+3).setIcon(partida.getJugador1().getPila().get(i).getImg());
            }
            else {cartasEnMesa.get(i+3).setVisible(false);}
        }

        // actualizacion de puntos
        puntajeJ.setText(String.valueOf(partida.getEstadisticas().getPuntos0()));
        puntajeIA.setText(String.valueOf(partida.getEstadisticas().getPuntos1()));
    }

    public void setImgCarta1(ImageIcon i){
        carta1.setIcon(i);
    }

    public void setImgCarta2(ImageIcon i){
        carta2.setIcon(i);
    }

    public void setImgCarta3(ImageIcon i){
        carta3.setIcon(i);
    }

    public void log(String s){
        String b = log.getText();
        String n = b + "\n" + ">>" + s;
        log.setText(n);
    }
    public void abrirDetalles(){detalle.abrir();}

}


