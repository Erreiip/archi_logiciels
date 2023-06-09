package client.src.vue.dessin;

import client.src.Controleur;
import commons.IDessin;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class FrameDessin extends JFrame {
    
    private Controleur ctrl;
    private PanelChoixActions panelHaut;
    private PanelDessin panelDessin;
    private PanelChoixCouleurs panelBas;

    public FrameDessin(Controleur ctrl) {

        this.ctrl = ctrl;
        
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        this.setLayout(new BorderLayout());
        this.panelHaut = new PanelChoixActions(ctrl);
        this.panelDessin = new PanelDessin(ctrl);
        this.panelBas = new PanelChoixCouleurs(ctrl);

        this.add(this.panelHaut, BorderLayout.NORTH);
        this.add(this.panelDessin, BorderLayout.CENTER);
        this.add(this.panelBas, BorderLayout.SOUTH);

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                FrameDessin.this.ctrl.sendClose();
                FrameDessin.this.ctrl.dispose();
            }
        });

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setVisible(true);

        Toolkit tkit = Toolkit.getDefaultToolkit();

        try{
            BufferedImage bi = ImageIO.read(this.ctrl.getClass().getResource("../../images/cursor0.png"));

            Image img = bi.getScaledInstance(18, 30, Image.SCALE_DEFAULT);

            Cursor cursor = tkit.createCustomCursor(img, new Point(6, 3) , "curseurPerso");

            this.setCursor(cursor);
        }catch(Exception e) { e.printStackTrace(); }

        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

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