package commons;

import java.awt.Color;
import java.io.Serializable;
import java.awt.*;

public class MonTexte implements IDessin, Serializable{
    
    private String  texte;
    private Double  x;
    private Double  y;
    private Color   couleur;
    private boolean bRemplissage;
    private int     epaisseur;
    
    public MonTexte(String texte, Double x, Double y) {
        this(texte, x, y, null, false, 0);
    }

    public MonTexte(String texte, Double x, Double y, Color couleur, boolean bRemplissage, int epaisseur) {
        this.texte = texte;
        this.x = x;
        this.y = y;
        this.couleur = couleur;
        this.bRemplissage = bRemplissage;
        this.epaisseur = epaisseur;
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

    public int getEpaisseur() {
        return this.epaisseur;
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

    public void setEpaisseur(int epaisseur) {
        this.epaisseur = epaisseur;
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(this.couleur);
        g2d.setStroke(new BasicStroke(this.epaisseur));
        if (this.getRemplissage()) {
            g2d.setFont(new Font("TimesRoman", Font.BOLD, 11 + this.getEpaisseur()));
        } else {
            g2d.setFont(new Font("TimesRoman", Font.PLAIN, 11 + this.getEpaisseur()));
        }

        g2d.drawString(this.getTexte(), (int) Math.round(this.getX()), (int) Math.round((this.getY())));
    }
}
