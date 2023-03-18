package client.src;

import java.awt.Color;
import java.awt.Shape;
import java.util.ArrayList;

import client.src.metier.Client;
import client.src.metier.Metier;
import client.src.vue.FrameDessin;

public class Controleur {
    
    private FrameDessin ihm;

    private Metier metier;

    
    public Controleur() {
        this.ihm = new FrameDessin(this);
        this.metier = new Metier(this);
    }


    public String getActionCourante() {
        return this.metier.getActionCourante();
    }

    public Color getCouleurCourante() {
        return this.metier.getCouleurCourante();
    }

    public boolean getCBremplissage() {
        return this.ihm.getCBremplissage();
    }


    public void setCouleurCourante(Color couleurCourante) {
        this.metier.setCouleurCourante(couleurCourante);
    }
    
    public void setActionCourante(String actionCourante) {
        this.metier.setActionCourante(actionCourante);
    }

    public void maj(ArrayList<Shape> alFormes)
    {
        this.ihm.maj(alFormes);
    }
}