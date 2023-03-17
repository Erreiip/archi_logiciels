package client.src.commons;

import java.awt.geom.Line2D;
import java.awt.Color;

public class MaLigne extends Line2D.Double {
    
    private Color   couleur;
    private boolean bRemplissage;
    
    public MaLigne(double x, double y, double width, double height) {
        this(x, y, width, height, null, false);
    }

    public MaLigne(double x, double y, double width, double height, Color couleur, boolean bRemplissage) {
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
