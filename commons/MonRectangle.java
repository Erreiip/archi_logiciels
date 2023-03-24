package commons;

import java.awt.geom.Rectangle2D;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.BasicStroke;

public class MonRectangle extends Rectangle2D.Double implements IDessin{
    
    private Color   couleur;
    private boolean bRemplissage;
    private int     epaisseur;
    
    public MonRectangle(double x, double y, double width, double height) {
        this(x, y, width, height, null, false, 0);
    }

    public MonRectangle(double x, double y, double width, double height, Color couleur, boolean bRemplissage, int epaisseur) {
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

    public void draw(Graphics2D g2d) {
        g2d.setColor(this.couleur);
        g2d.setStroke(new BasicStroke(this.epaisseur));
        if (this.bRemplissage)
            g2d.fill(this);
        else
            g2d.draw(this);
    }
}
