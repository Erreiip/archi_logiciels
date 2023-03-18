package client.src.commons;

import java.awt.Color;

public class MonTexte implements IDessin {
    
    private String  texte;
    private Double  x;
    private Double  y;
    private Color   couleur;
    private boolean bRemplissage;
    
    public MonTexte(String texte, Double x, Double y) {
        this(texte, x, y, null, false);
    }

    public MonTexte(String texte, Double x, Double y, Color couleur, boolean bRemplissage) {
        this.texte = texte;
        this.x = x;
        this.y = y;
        this.couleur = couleur;
        this.bRemplissage = bRemplissage;
    }

    public String getTexte() {
        return this.texte;
    }

    public Double getX() {
        return this.x;
    }

    public Double getY() {
        return this.y;
    }

    public Color getCouleur() {
        return this.couleur;
    }

    public boolean getRemplissage() {
        return this.bRemplissage;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }

    public void setRemplissage(boolean bRemplissage) {
        this.bRemplissage = bRemplissage;
    }
}
