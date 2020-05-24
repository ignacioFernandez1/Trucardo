package AZERTY;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class MainMenu extends JFrame implements ActionListener{
    //private JtextField name;
    private Font fuente;
    private JLabel background;
    private JLabel background_img;
    private JButton boton;
    private JLabel pika;

    public MainMenu(){
        setLayout(null);
        setTitle("Trucardo");
        this.fuenteMinecraft();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        boton = new JButton("JUGAR");
        boton.setHorizontalTextPosition(boton.CENTER);
        boton.setVerticalTextPosition(boton.CENTER);
        boton.setBounds(320-55,290-15,110,30);
        boton.setFont(this.fuente);
        boton.setForeground(new Color(50,50,50));
        boton.addActionListener(this);
        //boton.setOpaque(false);
        boton.setContentAreaFilled(false);
        //boton.setBorderPainted(false);
        boton.setFocusPainted(false);
        add(boton);

        ImageIcon back = new ImageIcon("images/GIFARDO.gif");
        background = new JLabel(back);
        background.setBounds(0,0,640,960);
        add(background);

        ImageIcon back_img = new ImageIcon("images/MENUU.png");
        background_img = new JLabel(back_img);
        background_img.setBounds(0,0,640,960);
        add(background_img);
    }

    public void actionPerformed(ActionEvent e){
        String titleBar = "JUGAR";
        String infoMessage = "El boton JUGAR funciona";
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }

    public void fuenteMinecraft() {
        try {
            this.fuente = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/minecraft_font.ttf")).deriveFont(1, 20);
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
        menu.background.setVisible(false);
    }
}