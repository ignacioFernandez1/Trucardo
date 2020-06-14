package AZERTY;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PantallaConfig extends JFrame implements ActionListener{
    private Font fuente;
    private JRadioButton puntaje15;
    private JRadioButton puntaje30;
    private JLabel puntMax;
    private JRadioButton florSi;
    private JRadioButton florNo;
    private JLabel florSelect;
    private ButtonGroup puntaje;
    private ButtonGroup flor;
    private JTextField nombre;
    private JLabel ingNombre;
    private JButton aceptar;
    private JLabel fondo;
    private MainMenu menu;

    public PantallaConfig(MainMenu m) {

        menu = m;

        //this.fuente = fuente;
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);

        puntaje15 = new JRadioButton("15");
        puntaje30 = new JRadioButton("30");
        puntaje = new ButtonGroup();
        puntMax = new JLabel("Puntaje Maximo: ");
        puntaje15.setBounds(130+5, 10, 120, 50);
        puntaje30.setBounds(260+5, 10, 80, 50);
        puntMax.setBounds(30+5, 10, 150, 50);
        puntaje15.setContentAreaFilled(false);
        puntaje30.setContentAreaFilled(false);
        puntaje15.setBorderPainted(false);
        puntaje30.setBorderPainted(false);
        puntaje15.setFocusPainted(false);
        puntaje30.setFocusPainted(false);
        puntaje30.setForeground(new Color(255,255,255));
        puntaje15.setForeground(new Color(255,255,255));
        puntMax.setForeground(new Color(255,255,255));
        puntaje.add(puntaje15);
        puntaje.add(puntaje30);

        florSi = new JRadioButton("Con Flor");
        florNo = new JRadioButton("Sin Flor");
        flor = new ButtonGroup();
        florSelect = new JLabel("Juega: ");
        florSi.setBounds(130+5,60,80,30);
        florNo.setBounds(260+5,60,80,30);
        florSelect.setBounds(30+5,60,80,30);
        florNo.setContentAreaFilled(false);
        florSi.setContentAreaFilled(false);
        florNo.setBorderPainted(false);
        florSi.setBorderPainted(false);
        florSi.setFocusPainted(false);
        florNo.setFocusPainted(false);
        florSi.setForeground(new Color(255,255,255));
        florNo.setForeground(new Color(255,255,255));
        florSelect.setForeground(new Color(255,255,255));
        flor.add(florSi);
        flor.add(florNo);

        nombre = new JTextField();
        nombre.setBounds(130+5, 110, 200,30);
        ingNombre = new JLabel("Nombre: ");
        ingNombre.setBounds(30+5, 110, 80,30);
        ingNombre.setForeground(new Color(255,255,255));

        aceptar = new JButton("ACEPTAR");
        aceptar.setBounds(160+5, 160, 100, 30);
        aceptar.addActionListener(this);

        ImageIcon fondo_img = new ImageIcon("images/CONFIG.png");
        fondo = new JLabel(fondo_img);
        fondo.setBounds(-20,-4,400,256);


        this.add(nombre);
        this.add(ingNombre);
        this.add(puntaje15);
        this.add(puntaje30);
        this.add(puntMax);
        this.add(florSelect);
        this.add(florSi);
        this.add(florNo);
        this.add(aceptar);
        this.add(fondo);
    }

    public void crear(){
        PantallaConfig ventana= new PantallaConfig(menu);
        ventana.setBounds(0,0,376,286);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton)e.getSource();
        if(b.equals(aceptar)){
            if(nombre.getText().isEmpty()){
                JLabel mensaje = new JLabel("Por favor ingrese un nombre");
                JOptionPane.showMessageDialog(null, mensaje);
            }
            if(!puntaje15.isSelected() && !puntaje30.isSelected()){
                JLabel mensaje = new JLabel("Por favor seleccione un puntaje maximo");
                JOptionPane.showMessageDialog(null, mensaje);
            }
            if(!florSi.isSelected() && !florNo.isSelected()){
                JLabel mensaje = new JLabel("Por favor seleccione un modo de juego");
                JOptionPane.showMessageDialog(null, mensaje);
            }
            else{
                String name = nombre.getText().trim();
                int puntajeMax = 0;
                boolean florBool = false;
                if(puntaje15.isSelected()){
                    puntajeMax = 15;
                }
                else{
                    puntajeMax = 30;
                }
                if(florSi.isSelected()){
                    florBool = true;
                }
                else {
                    florBool = false;
                }

                new Partida(puntajeMax,name, florBool);
                new Mesa(new Partida(puntajeMax,name, florBool));
                menu.dispose();
                this.dispose();

            }
        }
    }


}
