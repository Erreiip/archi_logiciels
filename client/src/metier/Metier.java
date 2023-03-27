package client.src.metier;

import client.src.Controleur;
import commons.IDessin;
import commons.Mouse;

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
    private ArrayList<Mouse> alSouris;

    public Metier( Controleur ctrl )
    {
        this.ctrl = ctrl;

        this.alShape  = new ArrayList<IDessin>();
        this.alSouris = new ArrayList<Mouse>();
        this.nom = "guest";

        this.client = new Client(this.ctrl);

        this.couleurCourante = Color.BLACK;
        this.actionCourante = "Cercle";
    }

    public ArrayList<IDessin> getAlShape() {
        return this.alShape;
    }

    public ArrayList<Mouse> getMouse() {
        return this.alSouris;
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

    public String getNom()
    {
        return this.nom;
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

    public void sendMouse(int x, int y, boolean drag) {
        this.client.sendMouse(x,y, drag);
    }

    public void creerSouris(String nom, int x, int y, boolean drag) {
        
        if (nom.equals(this.nom))
            return;

        for ( Mouse m : this.alSouris )
        {
            if (m.getNom().equals(nom)) {
                m.x = x;
                m.y = y;
                m.drag = drag;
                return;
            }
        }

        Mouse m = new Mouse(nom, x, y, drag);
        this.alSouris.add(m);
    }

    public void sendClose() {
        this.client.sendClose();
    }

    public void deco(String nom) {
        this.alSouris.removeIf( s ->(s.getNom().equals(nom)));
    }

}
