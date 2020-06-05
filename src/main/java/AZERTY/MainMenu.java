package AZERTY;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.concurrent.TimeUnit;

public class MainMenu extends JFrame implements ActionListener{
    //private JtextField name;
    private Font fuente;
    private JLabel backgroundGIF;
    private JLabel backgroundImg;
    private JButton botonJugar;
    private JButton botonSalir;

    public MainMenu(){
        setLayout(null);
        setTitle("Trucardo");
        this.fuenteMinecraft();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        botonJugar = new JButton("JUGAR");
        botonJugar.setHorizontalTextPosition(botonJugar.CENTER);
        botonJugar.setVerticalTextPosition(botonJugar.CENTER);
        botonJugar.setBounds(320-85,260-15,170,40);
        botonJugar.setFont(this.fuente);
        botonJugar.setForeground(new Color(50,50,50));
        botonJugar.setBackground(new Color(249,234,195));
        botonJugar.addActionListener(this);
        botonJugar.setContentAreaFilled(true);
        botonJugar.setFocusPainted(false);
        add(botonJugar);

        botonSalir = new JButton("SALIR");
        botonSalir.setHorizontalTextPosition(botonSalir.CENTER);
        botonSalir.setVerticalAlignment(botonSalir.CENTER);
        botonSalir.setBounds(320-85, 330-15, 170, 40);
        botonSalir.setFont(this.fuente);
        botonSalir.setForeground(new Color(50,50,50));
        botonSalir.setBackground(new Color(249,234,195));
        botonSalir.addActionListener(this);
        botonSalir.setContentAreaFilled(true);
        botonSalir.setFocusPainted(false);
        add(botonSalir);

        ImageIcon back = new ImageIcon("images/GIFARDO.gif");
        backgroundGIF = new JLabel(back);
        backgroundGIF.setBounds(0,0,640,960);
        add(backgroundGIF);

        ImageIcon back_img = new ImageIcon("images/MENUU.png");
        backgroundImg = new JLabel(back_img);
        backgroundImg.setBounds(0,0,640,960);
        add(backgroundImg);
    }

    public void actionPerformed(ActionEvent e){
        JButton b = (JButton)e.getSource();
        if(b.equals(botonJugar)){
            String titleBar = "JUGAR";
            String infoMessage = "El boton JUGAR funciona";
            JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
        }
        else if(b.equals(botonSalir)){
            int confirmed = JOptionPane.showConfirmDialog(null,
                    "¿Estas seguro que queres salir?", "Salir",
                    JOptionPane.YES_NO_OPTION);
            if (confirmed == JOptionPane.YES_OPTION) {
                dispose();
            }
        }
    }

    public void fuenteMinecraft() {
        try {
            this.fuente = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/minecraft_font.ttf")).deriveFont(1, 30);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

            ge.registerFont(fuente);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String args[]){
        MainMenu menu = new MainMenu();
        menu.setBounds(0,0,640,960);
        menu.setVisible(true);
        menu.setLocationRelativeTo(null);
        menu.setResizable(false);
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        menu.backgroundGIF.setVisible(false);
    }
}