package server;

import com.formdev.flatlaf.FlatLightLaf;

import server.src.Controleur;
import server.src.Serveur;

public class Main {

    public static void main(String[] args) {
        FlatLightLaf.setup();

        try{
            new Controleur();
        } catch(Exception e) { e.printStackTrace(); }
    }
    
}
