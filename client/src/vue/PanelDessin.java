package client.src.vue;

import client.src.Controleur;
import client.src.commons.IDessin;
import client.src.commons.MaLigne;
import client.src.commons.MonEllipse;
import client.src.commons.MonRectangle;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;


public class PanelDessin extends JPanel {
    
    private Controleur ctrl;

    private Shape shapeCreation;
    private boolean bCreation;
    private Point pntDebut;
    private Graphics2D g2d;


    public PanelDessin(Controleur ctrl) { 
        this.ctrl = ctrl;
        
        this.shapeCreation = null;
        
        this.bCreation = false;

        gereMouvementSouris gms = new gereMouvementSouris();

        this.addMouseListener(gms);
        this.addMouseMotionListener(gms);

        this.setLayout(null);
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        ArrayList<Shape> alFormes = this.ctrl.getAlShape();

        for (Shape forme : alFormes) {
            Graphics2D g2d = (Graphics2D) g;

            g2d.setColor(((IDessin) forme).getCouleur());

            g2d.setStroke(new BasicStroke(5));
            g2d.draw(forme);

            if (((IDessin) forme).getRemplissage()) {
                g2d.fill(forme);
            }
        }
    }

    public void maj(ArrayList<Shape> alFormes)
    {
        this.repaint();
    }

    public Shape getShape(Point pntFin) {
        switch (this.ctrl.getActionCourante()) {
            case "Cercle":
                MonEllipse oval = (MonEllipse) new MonEllipse(this.pntDebut.getX(), this.pntDebut.getY(), Math.min(pntFin.getX(),this.pntDebut.getX()), Math.min(pntFin.getY(),this.pntDebut.getY()));
                oval.setCouleur(this.ctrl.getCouleurCourante());
                oval.setRemplissage(this.ctrl.getCBremplissage());
                return oval;
            case "Rectangle":
                MonRectangle rectangle = (MonRectangle) new MonRectangle(this.pntDebut.getX(), this.pntDebut.getY(), pntFin.getX(), pntFin.getY());
                rectangle.setCouleur(this.ctrl.getCouleurCourante());
                rectangle.setRemplissage(this.ctrl.getCBremplissage());
                return rectangle;
            case "Ligne":
                MaLigne ligne = new MaLigne(this.pntDebut.getX(), this.pntDebut.getY(), pntFin.getX(), pntFin.getY());
                ligne.setCouleur(this.ctrl.getCouleurCourante());
                ligne.setRemplissage(bCreation);
                return ligne;
            default: 
                return null;
        }
    }

    public void setPointFin(Point pntFin) {
        if (PanelDessin.this.shapeCreation instanceof MonEllipse ) {
            Double diametre = Math.min(Math.abs(pntFin.getX()-this.pntDebut.getX()), Math.abs(pntFin.getY()-this.pntDebut.getY()));

            if (pntFin.getX() < this.pntDebut.getX() && pntFin.getY() < this.pntDebut.getY()) {
                ((MonEllipse) this.shapeCreation ).setFrame(pntFin.getX(), pntFin.getY(), Math.abs(pntFin.getX()-this.pntDebut.getX()), Math.abs(pntFin.getY()-this.pntDebut.getY()));
                return;
            }
            else if (pntFin.getX() < this.pntDebut.getX()) {
                ((MonEllipse) this.shapeCreation ).setFrame(pntFin.getX(), this.pntDebut.getY(), Math.abs(pntFin.getX()-this.pntDebut.getX()), Math.abs(pntFin.getY()-this.pntDebut.getY()));
                return;
            }
            else if (pntFin.getY() < this.pntDebut.getY()) {
                ((MonEllipse) this.shapeCreation ).setFrame(this.pntDebut.getX(), pntFin.getY(), Math.abs(pntFin.getX()-this.pntDebut.getX()), Math.abs(pntFin.getY()-this.pntDebut.getY()));
                return;
            }
            else {
                ((MonEllipse) this.shapeCreation ).setFrame(this.pntDebut.getX(), this.pntDebut.getY(), Math.abs(pntFin.getX()-this.pntDebut.getX()), Math.abs(pntFin.getY()-this.pntDebut.getY()));
                return;
            }      
        }

        if (PanelDessin.this.shapeCreation instanceof MonRectangle ) {
            if (pntFin.getX() < this.pntDebut.getX() && pntFin.getY() < this.pntDebut.getY())
                ((MonRectangle) this.shapeCreation ).setFrame(pntFin.getX(), pntFin.getY(), Math.abs(pntFin.getX()-this.pntDebut.getX()), Math.abs(pntFin.getY()-this.pntDebut.getY()));
            else if (pntFin.getX() < this.pntDebut.getX())
                ((MonRectangle) this.shapeCreation ).setFrame(pntFin.getX(), this.pntDebut.getY(), Math.abs(pntFin.getX()-this.pntDebut.getX()), Math.abs(pntFin.getY()-this.pntDebut.getY()));
            else if (pntFin.getY() < this.pntDebut.getY())
                ((MonRectangle) this.shapeCreation ).setFrame(this.pntDebut.getX(), pntFin.getY(), Math.abs(pntFin.getX()-this.pntDebut.getX()), Math.abs(pntFin.getY()-this.pntDebut.getY()));
            else
                ((MonRectangle) this.shapeCreation ).setFrame(this.pntDebut.getX(), this.pntDebut.getY(), Math.abs(pntFin.getX()-this.pntDebut.getX()), Math.abs(pntFin.getY()-this.pntDebut.getY()));
                return;
        }
        if (PanelDessin.this.shapeCreation instanceof MaLigne ) {
                ((MaLigne) this.shapeCreation ).setLine(this.pntDebut.getX(), this.pntDebut.getY(), pntFin.getX(), pntFin.getY());
                return;
        }
    }

    class gereMouvementSouris extends MouseAdapter {

        public void mousePressed(MouseEvent e) {
            PanelDessin.this.pntDebut = e.getPoint();
            if ( PanelDessin.this.ctrl.getActionCourante() != "Effacer") {
                PanelDessin.this.bCreation = true;
                PanelDessin.this.shapeCreation = getShape(e.getPoint());
                PanelDessin.this.ctrl.ajouterForme(PanelDessin.this.shapeCreation);
            }
        }

        public void mouseReleased(MouseEvent e) {
            PanelDessin.this.bCreation = false;
            PanelDessin.this.repaint();
        }

        public void mouseDragged(MouseEvent e) {
            if(PanelDessin.this.bCreation) {
                PanelDessin.this.setPointFin(e.getPoint());
                PanelDessin.this.repaint();
            }
        }

        public void mouseMoved(MouseEvent e) {
            if(PanelDessin.this.bCreation) {
                PanelDessin.this.setPointFin(e.getPoint());
                PanelDessin.this.repaint();

            }
        }
    }
}