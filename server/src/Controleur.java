package server.src;

import java.util.ArrayList;

import client.src.commons.IDessin;
import server.src.vue.FramePrincipale;

public class Controleur {
    private Serveur serv;
    private FramePrincipale ihm;

    public Controleur() throws Exception
    {
        this.serv = new Serveur(this);
        this.ihm = new FramePrincipale(this);
    }

    public ArrayList<IDessin>  getAlShape() {
        return this.serv.getAlShape();
    }

    public void maj() {
        this.ihm.maj();
    }

    
    
}
