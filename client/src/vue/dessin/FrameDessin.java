package client.src.vue.dessin;

import client.src.Controleur;
import client.src.commons.IDessin;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FrameDessin extends JFrame {
    
    private Controleur ctrl;
    private PanelChoixActions panelHaut;
    private PanelDessin panelDessin;
    private PanelChoixCouleurs panelBas;

    public FrameDessin(Controleur ctrl) {

        this.ctrl = ctrl;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        this.setLayout(new BorderLayout());
        this.panelHaut = new PanelChoixActions(ctrl);
        this.panelDessin = new PanelDessin(ctrl);
        this.panelBas = new PanelChoixCouleurs(ctrl);

        this.add(this.panelHaut, BorderLayout.NORTH);
        this.add(this.panelDessin, BorderLayout.CENTER);
        this.add(this.panelBas, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    public boolean getCBremplissage() {
        return this.panelHaut.getCBremplissage();
    }

    public int getEpaisseur() {
        return this.panelHaut.getEpaisseur();
    }

    public void maj()
    {
        this.panelDessin.repaint();
    }
}