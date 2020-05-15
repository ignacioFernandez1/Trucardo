package AZERTY;

import javax.swing.*;

public class App {

    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new Ventana1();
                frame.setSize(600,400);
                frame.setVisible(true);
            }
        });
    }
}
