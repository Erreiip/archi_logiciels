package client.src.vue;

import client.src.Controleur;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;


public class PanelDessin extends JPanel {
    
    private Controleur ctrl;
    private ArrayList<Shape> alFormes;
    private Shape shapeCreation;
    private boolean bCreation;
    private Point pntDebut;
    private Graphics2D g2d;


    public PanelDessin(Controleur ctrl) { 
        this.ctrl = ctrl;

        this.alFormes = new ArrayList<Shape>();
        
        this.shapeCreation = null;
        
        this.bCreation = false;

        gereMouvementSouris gms = new gereMouvementSouris();

        this.addMouseListener(gms);
        this.addMouseMotionListener(gms);

        this.setLayout(null);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for(Shape forme : this.alFormes) {
            Graphics2D g2d = (Graphics2D) g;

            g2d.setColor(this.ctrl.getCouleurCourante());
            g2d.setStroke(new BasicStroke(5));
            g2d.draw(forme);

            if(this.ctrl.getCBremplissage()) {
                g2d.fill(forme);
            }
        }
    }

    public void dessinerForme(Shape forme) {
        //System.out.println(this.alFormes);
        this.repaint();
    }

    public Shape getShape(Point pntFin) {
        Shape shape;
        switch (this.ctrl.getActionCourante()) {
            case "Cercle":
                shape = new Ellipse2D.Double(this.pntDebut.getX(), this.pntDebut.getY(), Math.min(pntFin.getX(),this.pntDebut.getX()), Math.min(pntFin.getY(),this.pntDebut.getY()));
                break;
            case "Rectangle":
                shape = new Rectangle2D.Double(this.pntDebut.getX(), this.pntDebut.getY(), pntFin.getX(), pntFin.getY());
                break;
            case "Ligne":
                shape = new Line2D.Double(this.pntDebut.getX(), this.pntDebut.getY(), pntFin.getX(), pntFin.getY());
                break;
            default: 
                shape = null;
                break;
        }

        return shape;
    }

    public void setPointFin(Point pntFin) {
        if (PanelDessin.this.shapeCreation instanceof Ellipse2D ) {
            Double diametre = Math.min(Math.abs(pntFin.getX()-this.pntDebut.getX()), Math.abs(pntFin.getY()-this.pntDebut.getY()));

            if (pntFin.getX() < this.pntDebut.getX() && pntFin.getY() < this.pntDebut.getY()) {
                ((Ellipse2D) this.shapeCreation ).setFrame(pntFin.getX(), pntFin.getY(), Math.abs(pntFin.getX()-this.pntDebut.getX()), Math.abs(pntFin.getY()-this.pntDebut.getY()));
                return;
            }
            else if (pntFin.getX() < this.pntDebut.getX()) {
                ((Ellipse2D) this.shapeCreation ).setFrame(pntFin.getX(), this.pntDebut.getY(), Math.abs(pntFin.getX()-this.pntDebut.getX()), Math.abs(pntFin.getY()-this.pntDebut.getY()));
                return;
            }
            else if (pntFin.getY() < this.pntDebut.getY()) {
                ((Ellipse2D) this.shapeCreation ).setFrame(this.pntDebut.getX(), pntFin.getY(), Math.abs(pntFin.getX()-this.pntDebut.getX()), Math.abs(pntFin.getY()-this.pntDebut.getY()));
                return;
            }
            else {
                ((Ellipse2D) this.shapeCreation ).setFrame(this.pntDebut.getX(), this.pntDebut.getY(), Math.abs(pntFin.getX()-this.pntDebut.getX()), Math.abs(pntFin.getY()-this.pntDebut.getY()));
                return;
            }      
        }

        if (PanelDessin.this.shapeCreation instanceof Rectangle2D ) {
            if (pntFin.getX() < this.pntDebut.getX() && pntFin.getY() < this.pntDebut.getY())
                ((Rectangle2D) this.shapeCreation ).setFrame(pntFin.getX(), pntFin.getY(), Math.abs(pntFin.getX()-this.pntDebut.getX()), Math.abs(pntFin.getY()-this.pntDebut.getY()));
            else if (pntFin.getX() < this.pntDebut.getX())
                ((Rectangle2D) this.shapeCreation ).setFrame(pntFin.getX(), this.pntDebut.getY(), Math.abs(pntFin.getX()-this.pntDebut.getX()), Math.abs(pntFin.getY()-this.pntDebut.getY()));
            else if (pntFin.getY() < this.pntDebut.getY())
                ((Rectangle2D) this.shapeCreation ).setFrame(this.pntDebut.getX(), pntFin.getY(), Math.abs(pntFin.getX()-this.pntDebut.getX()), Math.abs(pntFin.getY()-this.pntDebut.getY()));
            else
                ((Rectangle2D) this.shapeCreation ).setFrame(this.pntDebut.getX(), this.pntDebut.getY(), Math.abs(pntFin.getX()-this.pntDebut.getX()), Math.abs(pntFin.getY()-this.pntDebut.getY()));
                return;
        }
        if (PanelDessin.this.shapeCreation instanceof Line2D ) {
                ((Line2D) this.shapeCreation ).setLine(this.pntDebut.getX(), this.pntDebut.getY(), pntFin.getX(), pntFin.getY());
                return;
        }
    }

    class gereMouvementSouris extends MouseAdapter {

        public void mousePressed(MouseEvent e) {
            PanelDessin.this.pntDebut = e.getPoint();
            if ( PanelDessin.this.ctrl.getActionCourante() != "Effacer") {
                PanelDessin.this.bCreation = true;
                PanelDessin.this.shapeCreation = getShape(e.getPoint());
                PanelDessin.this.alFormes.add(PanelDessin.this.shapeCreation);
            }
        }

        public void mouseReleased(MouseEvent e) {
            PanelDessin.this.bCreation = false;
            PanelDessin.this.dessinerForme(PanelDessin.this.shapeCreation);
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