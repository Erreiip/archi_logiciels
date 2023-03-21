package client.src.metier;

import client.src.Controleur;
import client.src.commons.IDessin;

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

    private ArrayList<IDessin> alShape;

    public Metier( Controleur ctrl )
    {
        this.ctrl = ctrl;

        this.alShape = new ArrayList<IDessin>();
        this.nom = "guest";

        this.client = new Client(this.ctrl);

        this.couleurCourante = Color.BLACK;
        this.actionCourante = "Cercle";
    }

    public ArrayList<IDessin> getAlShape() {
        return this.alShape;
    }

    public void ajouterForme(IDessin s) {
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

    public void send(IDessin forme)
    {
        this.client.send(forme);
    }

    public void maj(ArrayList<IDessin> alFormes) 
    {
        this.alShape = alFormes;
    }

    public void delete()
    {
        this.client.delete();   
    }
}
