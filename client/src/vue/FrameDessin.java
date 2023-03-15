package client.src.vue;

import client.src.Controleur;

import javax.swing.*;
import java.awt.*;

public class FrameDessin extends JFrame {
    
    private Controleur ctrl;
    private PanelChoixActions panelHaut;
    private JPanel panelDessin;
    private PanelChoixCouleurs panelBas;

    public FrameDessin(Controleur ctrl) {

        this.ctrl = ctrl;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        this.setLayout(new BorderLayout());
        this.panelHaut = new PanelChoixActions(ctrl);
        this.panelDessin = new JPanel();
        this.panelBas = new PanelChoixCouleurs(ctrl);

        this.add(this.panelHaut, BorderLayout.NORTH);
        this.add(this.panelDessin, BorderLayout.CENTER);
        this.add(this.panelBas, BorderLayout.SOUTH);
        this.setVisible(true);
    }
}