package client.src.commons;

import java.awt.geom.Ellipse2D;
import java.awt.Color;

public class MonEllipse extends Ellipse2D.Double implements IDessin{
    
    private Color   couleur;
    private boolean bRemplissage;
    
    public MonEllipse(double x, double y, double width, double height) {
        this(x, y, width, height, null, false);
    }

    public MonEllipse(double x, double y, double width, double height, Color couleur, boolean bRemplissage) {
        super(x, y, width, height);

        this.couleur = couleur;
        this.bRemplissage = bRemplissage;
    }

    public Color getCouleur() {
        return this.couleur;
    }

    public boolean getRemplissage() {
        return this.bRemplissage;
    }

    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }

    public void setRemplissage(boolean bRemplissage) {
        this.bRemplissage = bRemplissage;
    }

}
