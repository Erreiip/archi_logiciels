package client.src.vue.dessin;

import client.src.Controleur;
import client.src.commons.IDessin;
import client.src.commons.MaLigne;
import client.src.commons.MonEllipse;
import client.src.commons.MonRectangle;
import client.src.commons.MonTexte;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;


public class PanelDessin extends JPanel {
    
    private Controleur ctrl;

    private IDessin shapeCreation;
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

        this.ctrl.send(null);
        this.repaint();
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        ArrayList<IDessin> alFormes = this.ctrl.getAlShape();

        if ( this.shapeCreation != null && !alFormes.contains(this.shapeCreation)) alFormes.add(this.shapeCreation);

        for (IDessin forme : alFormes) {
            Graphics2D g2d = (Graphics2D) g;

            g2d.setColor(((IDessin) forme).getCouleur());

            g2d.setStroke(new BasicStroke(((IDessin) forme).getEpaisseur()));
            if (forme instanceof MonTexte) {
                if (forme.getRemplissage()) {
                    g2d.setFont(new Font("TimesRoman", Font.BOLD, 11 + forme.getEpaisseur()));
                } else {
                    g2d.setFont(new Font("TimesRoman", Font.PLAIN, 11 + forme.getEpaisseur()));
                }

                g2d.drawString(((MonTexte) forme).getTexte(), (int) Math.round(((MonTexte) forme).getX()),
                        (int) Math.round(((MonTexte) forme).getY()));
            }
            else if (forme instanceof Shape) {
                g2d.draw((Shape) forme);
                if(forme.getRemplissage())
                    g2d.fill((Shape) forme);
            }
        }        
    }

    public void send()
    {
        if ( shapeCreation != null )
        {
            this.ctrl.send(this.shapeCreation);
            this.shapeCreation = null;
        }
    }

    public void maj(ArrayList<IDessin> alFormes)
    {
        this.repaint();
    }

    public IDessin getShape(Point pntFin) {
        switch (this.ctrl.getActionCourante()) {
            case "Cercle":
                MonEllipse oval = (MonEllipse) new MonEllipse(this.pntDebut.getX(), this.pntDebut.getY(), 0, 0);
                oval.setCouleur(this.ctrl.getCouleurCourante());
                oval.setRemplissage(this.ctrl.getCBremplissage());
                oval.setEpaisseur(this.ctrl.getEpaisseur());
                return oval;
            case "Rectangle":
                MonRectangle rectangle = (MonRectangle) new MonRectangle(this.pntDebut.getX(), this.pntDebut.getY(), 0, 0);
                rectangle.setCouleur(this.ctrl.getCouleurCourante());
                rectangle.setRemplissage(this.ctrl.getCBremplissage());
                rectangle.setEpaisseur(this.ctrl.getEpaisseur());
                return rectangle;
            case "Ligne":
                MaLigne ligne = new MaLigne(this.pntDebut.getX(), this.pntDebut.getY(), pntFin.getX(), pntFin.getY());
                ligne.setCouleur(this.ctrl.getCouleurCourante());
                ligne.setRemplissage(this.ctrl.getCBremplissage());
                ligne.setEpaisseur(this.ctrl.getEpaisseur());
                return ligne;
            case "Texte" :
                String input = JOptionPane.showInputDialog(null,"Entrez un texte : ",null);
                MonTexte texte = new MonTexte(input, this.pntDebut.getX(), this.pntDebut.getY());
                texte.setCouleur(this.ctrl.getCouleurCourante());
                texte.setRemplissage(this.ctrl.getCBremplissage());
                texte.setEpaisseur(this.ctrl.getEpaisseur());
                this.repaint();
                return texte;
            default: 
                return null;
        }
    }

    public void setPointFin(Point pntFin) {
        if (PanelDessin.this.shapeCreation instanceof MonEllipse ) {
            Double diametre = Math.max(Math.abs(pntFin.getX()-this.pntDebut.getX()), Math.abs(pntFin.getY()-this.pntDebut.getY()));            
            ((MonEllipse) this.shapeCreation ).setFrame(this.pntDebut.getX()-diametre/2, this.pntDebut.getY()-diametre/2, diametre*1.5, diametre*1.5);
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
            if ( PanelDessin.this.ctrl.getActionCourante().equals("Texte")) {
                PanelDessin.this.shapeCreation = getShape(e.getPoint());
                PanelDessin.this.send();
                return;
            } 
            if ( !PanelDessin.this.ctrl.getActionCourante().equals("Effacer")) {
                PanelDessin.this.bCreation = true;
                PanelDessin.this.shapeCreation = getShape(e.getPoint());
            }
        }

        public void mouseReleased(MouseEvent e) {
            PanelDessin.this.bCreation = false;
            PanelDessin.this.send();
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