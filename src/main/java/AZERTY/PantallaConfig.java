package AZERTY;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.concurrent.TimeUnit;

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

    public PantallaConfig() {
        //this.fuente = fuente;
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);

        puntaje15 = new JRadioButton("15");
        puntaje30 = new JRadioButton("30");
        puntaje = new ButtonGroup();
        puntMax = new JLabel("Puntaje Maximo: ");
        puntaje15.setBounds(120, 10, 120, 50);
        puntaje30.setBounds(250, 10, 80, 50);
        puntMax.setBounds(20, 10, 150, 50);
        puntaje.add(puntaje15);
        puntaje.add(puntaje30);

        florSi = new JRadioButton("Con Flor");
        florNo = new JRadioButton("Sin Flor");
        flor = new ButtonGroup();
        florSelect = new JLabel("Juega: ");
        florSi.setBounds(120,60,80,30);
        florNo.setBounds(250,60,80,30);
        florSelect.setBounds(20,60,80,30);
        flor.add(florSi);
        flor.add(florNo);

        nombre = new JTextField();
        nombre.setBounds(120, 110, 200,30);
        ingNombre = new JLabel("Nombre: ");
        ingNombre.setBounds(20, 110, 80,30);

        aceptar = new JButton("ACEPTAR");
        aceptar.setBounds(150, 160, 100, 30);
        aceptar.addActionListener(this);


        this.add(nombre);
        this.add(ingNombre);
        this.add(puntaje15);
        this.add(puntaje30);
        this.add(puntMax);
        this.add(florSelect);
        this.add(florSi);
        this.add(florNo);
        this.add(aceptar);
    }

    public static void main(){
        PantallaConfig ventana= new PantallaConfig();
        ventana.setBounds(500,500,400,250);
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
                new Juego(puntajeMax,name, florBool);
                //crear ventana de juego y cerrar las otras 2
                //
                //
                ///////////////////////////////////
            }
        }
    }


}
