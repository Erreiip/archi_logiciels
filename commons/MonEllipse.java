package commons;

import java.awt.geom.Ellipse2D;
import java.awt.Color;

public class MonEllipse extends Ellipse2D.Double implements IDessin{
    
    private Color   couleur;
    private boolean bRemplissage;
    private int     epaisseur;
    
    public MonEllipse(double x, double y, double width, double height) {
        this(x, y, width, height, null, false, 0);
    }

    public MonEllipse(double x, double y, double width, double height, Color couleur, boolean bRemplissage, int epaisseur) {
        super(x, y, width, height);

        this.couleur = couleur;
        this.bRemplissage = bRemplissage;
        this.epaisseur = epaisseur;
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

    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }

    public void setRemplissage(boolean bRemplissage) {
        this.bRemplissage = bRemplissage;
    }

    public void setEpaisseur(int epaisseur) {
        this.epaisseur = epaisseur;
    }

}