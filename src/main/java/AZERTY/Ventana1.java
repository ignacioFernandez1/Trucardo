package AZERTY;

import javax.swing.*;
import java.awt.*;

public class Ventana1 extends JFrame{
    private JPanel panel1;
    private JButton JUGARButton;
    private JButton SALIRButton;
    private JLabel ImageLogo;


    public Ventana1() {
        super("TRUCARDO");

        setContentPane(panel1);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        ImageLogo = new JLabel(new ImageIcon("/Images/images.jpg"));
    }
}
