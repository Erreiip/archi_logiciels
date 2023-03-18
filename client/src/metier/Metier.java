package client.src.metier;

import client.src.Controleur;

import java.awt.Color;
import java.awt.Shape;

import java.util.ArrayList;

public class Metier 
{
    private String nom;

    private Controleur ctrl;

    private Client client;

    private Color couleurCourante;
    private String actionCourante;

    private ArrayList<Shape> alShape;

    public Metier( Controleur ctrl )
    {
        this.ctrl = ctrl;

        this.alShape = new ArrayList<Shape>();
        this.nom = "guest";

        this.client = new Client(this.ctrl);

        this.couleurCourante = Color.BLACK;
        this.actionCourante = "Cercle";
    }

    public ArrayList<Shape> getAlShape() {
        return this.alShape;
    }

    public void ajouterForme(Shape s) {
        this.alShape.add(s);
    }
    
    public String getActionCourante() {
        return this.actionCourante;
    }

    public Color getCouleurCourante() {
        return this.couleurCourante;
    }

    public void setCouleurCourante(Color couleurCourante) {
        this.couleurCourante = couleurCourante;
    }
    
    public void setActionCourante(String actionCourante) {
        this.actionCourante = actionCourante;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }    
}
