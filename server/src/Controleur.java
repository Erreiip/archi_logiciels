package server.src;

import java.util.ArrayList;

import commons.IDessin;
import commons.Mouse;
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
        if ( ihm != null)
            this.ihm.maj();
    }

    public void clear() {
        this.ihm.clearLogs();
        this.serv.clear();
    }
    
    public void ajouterForme(IDessin s) {
        this.serv.ajouterForme(s);
    }

    public void ajouterLogs(IDessin forme) {
        this.ihm.ajouterLogs(forme);
    }

    public ArrayList<Mouse> getAlSouris() {
        return this.serv.getAlMouse();
    } 

    public void send() {
        try {
            this.serv.send();
        } catch (Exception e) { e.printStackTrace(); }
    }
}
