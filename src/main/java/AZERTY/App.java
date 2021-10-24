package AZERTY;

import java.awt.*;

public class App {
    public static void main(String[] args) {

        new MainMenu().crear();
    }

    public static MainMenu showWindow(){
        MainMenu menu = new MainMenu();

        return menu.crear();

    }
}