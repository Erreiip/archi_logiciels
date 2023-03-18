package client.src.metier;

import client.src.Controleur;

import java.awt.Color;

public class Metier 
{
    private String nom;

    private Controleur ctrl;

    private Client client;

    private Color couleurCourante;
    private String actionCourante;

    public Metier( Controleur ctrl )
    {
        this.ctrl = ctrl;

        this.nom = "guest";

        this.client = new Client(this.ctrl);

        this.couleurCourante = Color.BLACK;
        this.actionCourante = "Cercle";
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
