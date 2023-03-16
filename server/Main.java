package server;

import com.formdev.flatlaf.FlatLightLaf;

import server.src.Serveur;

public class Main {

    public static void main(String[] args) {
        FlatLightLaf.setup();

        try{
            new Serveur();
        } catch(Exception e) { e.printStackTrace(); }
    }
    
}
