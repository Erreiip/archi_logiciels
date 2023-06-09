package server.src.vue;

import server.src.Controleur;

import javax.swing.*;

import commons.IDessin;
import commons.MonTexte;
import commons.Mouse;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;


public class PanelDessin extends JPanel {
    
    private Controleur ctrl;


    public PanelDessin(Controleur ctrl) { 
        this.ctrl = ctrl;

        this.setLayout(null);

        this.repaint();
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        ArrayList<IDessin> alFormes = this.ctrl.getAlShape();

        for (IDessin forme : alFormes) {

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
            } else if (forme instanceof Shape) {
                g2d.draw((Shape) forme);
                if (forme.getRemplissage())
                    g2d.fill((Shape) forme);
            }
        }

        AffineTransform old = g2d.getTransform();
        
        ArrayList<Mouse> alSouris = new ArrayList<Mouse>(this.ctrl.getAlSouris());
        for (Mouse m : alSouris){
            g2d.rotate(Math.toRadians(m.getOrientation()), m.x, m.y);
            g2d.drawImage(m.getImage(), m.x, m.y, null);
            g2d.setTransform(old);
        }
    }

    public void maj(ArrayList<IDessin> alFormes)
    {
        this.repaint();
    }
}