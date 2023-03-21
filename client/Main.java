package client;

import com.formdev.flatlaf.FlatLightLaf;

import client.src.Controleur;

public class Main {

    public static void main(String[] args) {
        FlatLightLaf.setup();

        try{ new Controleur(); } catch(Exception e) {}
    }
    
}
    