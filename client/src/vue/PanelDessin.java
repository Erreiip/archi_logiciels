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

    public Shape getShape(Point pointFin) {
        Shape shape;
        switch (this.ctrl.getActionCourante()) {
            case "Cercle":
                shape = new Ellipse2D.Double(this.pntDebut.getX(), this.pntDebut.getY(), Math.min(pointFin.getX(),this.pntDebut.getX()), Math.min(pointFin.getY(),this.pntDebut.getY()));
                break;
            case "Rectangle":
                shape = new Rectangle2D.Double(this.pntDebut.getX(), this.pntDebut.getY(), pointFin.getX(), pointFin.getY());
                break;
            case "Ligne":
                shape = new Line2D.Double(this.pntDebut.getX(), this.pntDebut.getY(), pointFin.getX(), pointFin.getY());
                break;
            default: 
                shape = null;
                break;
        }

        return shape;
    }

    public void setPointFin(Point pointFin) {
        if (PanelDessin.this.shapeCreation instanceof Ellipse2D ) {
                ((Ellipse2D) this.shapeCreation ).setFrame(this.pntDebut.getX(), this.pntDebut.getY(), Math.min(pointFin.getX(),this.pntDebut.getX()), Math.min(pointFin.getY(),this.pntDebut.getY()));
                return;
        }
        if (PanelDessin.this.shapeCreation instanceof Rectangle2D ) {
                ((Rectangle2D) this.shapeCreation ).setFrame(this.pntDebut.getX(), this.pntDebut.getY(), Math.abs(pointFin.getX()-this.pntDebut.getX()), Math.abs(pointFin.getY()-this.pntDebut.getY()));
                return;
        }
        if (PanelDessin.this.shapeCreation instanceof Line2D ) {
                ((Line2D) this.shapeCreation ).setLine(this.pntDebut.getX(), this.pntDebut.getY(), pointFin.getX(), pointFin.getY());
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