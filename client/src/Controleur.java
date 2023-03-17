package client.src;

import java.awt.Color;
import java.awt.Shape;
import java.util.ArrayList;

import client.src.vue.FrameDessin;

public class Controleur {
    
    private FrameDessin ihm;
    private Color couleurCourante;
    private String actionCourante;
    
    public Controleur() {
        this.ihm = new FrameDessin(this);
        this.couleurCourante = Color.BLACK;
    }


    public String getActionCourante() {
        return this.actionCourante;
    }

    public Color getCouleurCourante() {
        return this.couleurCourante;
    }

    public boolean getCBremplissage() {
        return this.ihm.getCBremplissage();
    }


    public void setCouleurCourante(Color couleurCourante) {
        this.couleurCourante = couleurCourante;
        System.out.println("Couleur courante : " + this.couleurCourante.toString());
    }
    
    public void setActionCourante(String actionCourante) {
        this.actionCourante = actionCourante;
        System.out.println("Action courante : " + this.actionCourante);
    }

    public void maj(ArrayList<Shape> alFormes)
    {
        this.ihm.maj(alFormes);
    }
}