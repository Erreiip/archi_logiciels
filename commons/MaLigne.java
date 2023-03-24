package commons;

import java.awt.geom.Line2D;
import java.awt.Color;

public class MaLigne extends Line2D.Double implements IDessin{
    
    private Color   couleur;
    private boolean bRemplissage;
    private int     epaisseur;
    
    public MaLigne(double x1, double y1, double x2, double y2) {
        this(x1, y1, x2, y2, null, false, 0);
    }

    public MaLigne(double x1, double y1, double x2, double y2, Color couleur, boolean bRemplissage, int epaisseur) {
        super(x1, y1, x2, y2);

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
