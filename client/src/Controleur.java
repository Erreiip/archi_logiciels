package client.src;

import java.awt.Color;
import java.awt.Shape;
import java.util.ArrayList;

import javax.swing.JFrame;

import client.src.metier.Metier;
import client.src.vue.connection.FrameConnection;
import client.src.vue.dessin.FrameDessin;
import client.src.commons.IDessin;

public class Controleur {
    
    private JFrame ihm;

    private Metier metier;

    
    public Controleur() {
        this.metier = new Metier(this);
        this.ihm = new FrameConnection(this);
    }

    public void setNom(String nom) {
        this.metier.setNom(nom);
        this.ihm.dispose();
        this.ihm = new FrameDessin(this);
    }

    public String getActionCourante() {
        return this.metier.getActionCourante();
    }

    public Color getCouleurCourante() {
        return this.metier.getCouleurCourante();
    }

    public boolean getCBremplissage() {
        return ((FrameDessin) this.ihm).getCBremplissage();
    }

    public int getEpaisseur() {
        return ((FrameDessin) this.ihm).getEpaisseur();
    }


    public void setCouleurCourante(Color couleurCourante) {
        this.metier.setCouleurCourante(couleurCourante);
    }
    
    public void setActionCourante(String actionCourante) {
        this.metier.setActionCourante(actionCourante);
    }

    public void ajouterForme(IDessin s) {
        this.metier.ajouterForme(s);
    }

    public ArrayList<IDessin> getAlShape() {
        return this.metier.getAlShape();
    }

    public void send(IDessin forme)
    {
        this.metier.send(forme);
    }

    public void maj(ArrayList<IDessin> alFormes)
    {
        this.metier.maj(alFormes);
        ((FrameDessin)this.ihm).maj();
    }
}