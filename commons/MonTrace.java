package commons;

import java.io.Serializable;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.awt.Graphics2D;

public class MonTrace implements IDessin, Serializable {
    
    private ArrayList<Point> alPoint;
        private Color              couleur;
        private boolean            bRemplissage;
        private int                epaisseur;

    public MonTrace() {
        this(new ArrayList<Point>(), null, false, 0);
    }

    public MonTrace(ArrayList<Point> alPoint, Color couleur, boolean bRemplissage, int epaisseur) {
        this.alPoint = alPoint;
        this.couleur = couleur;
        this.bRemplissage = bRemplissage;
        this.epaisseur = epaisseur;
    }

    public ArrayList<Point> getAlPoint() {
        return this.alPoint;
    }

    public void addPoint(Point p) {
        this.alPoint.add(p);
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
        for(int i = 0; i < this.alPoint.size() - 1; i++) {
            g2d.drawLine((int) Math.round(this.alPoint.get(i).getX()),
                         (int) Math.round(this.alPoint.get(i).getY()),
                         (int) Math.round(this.alPoint.get(i + 1).getX()),
                         (int) Math.round(this.alPoint.get(i + 1).getY()));
        }
    }
}
