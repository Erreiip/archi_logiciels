package src;

import java.awt.Color;

import src.ihm.FrameDessin;

public class Controleur {
    
    private FrameDessin ihm;
    private Color couleurCourante;
    private String actionCourante;
    
    public Controleur() {
        this.ihm = new FrameDessin(this);
        this.couleurCourante = Color.BLACK;
    }


    private String getActionCourante() {
        return this.actionCourante;
    }

    public Color getCouleurCourante() {
        return this.couleurCourante;
    }


    public void setCouleurCourante(Color couleurCourante) {
        this.couleurCourante = couleurCourante;
        System.out.println("Couleur courante : " + this.couleurCourante.toString());
    }
    
    public void setActionCourante(String actionCourante) {
        this.actionCourante = actionCourante;
        System.out.println("Action courante : " + this.actionCourante);
    }

    
    public static void main(String[] args) {
        new Controleur();
    }
}