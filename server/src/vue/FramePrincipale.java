package server.src.vue;

import javax.swing.JFrame;
import javax.swing.plaf.BorderUIResource;

import client.src.commons.IDessin;

import java.awt.*;

import server.src.Controleur;

public class FramePrincipale extends JFrame
{
    private Controleur ctrl;

    private PanelLogs pnlLogs;
    private PanelDessin pnlDessin;
    private PanelBoutons pnlBoutons;
    
    public FramePrincipale(Controleur ctrl)
    {
        this.ctrl = ctrl;
        this.setLayout(new BorderLayout());


        this.pnlLogs = new PanelLogs(this.ctrl);
        this.pnlDessin = new PanelDessin(this.ctrl);
        this.pnlBoutons = new PanelBoutons(this.ctrl);

        this.add( this.pnlLogs, BorderLayout.NORTH);
        this.add( this.pnlDessin, BorderLayout.CENTER);
        this.add( this.pnlBoutons, BorderLayout.SOUTH);


        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void ajouterLogs(IDessin forme) {
        this.pnlLogs.ajouterLog(forme.getClass().getSimpleName());
    }


    public void maj() {
        this.pnlDessin.repaint();
    }

    public void clearLogs() {
        this.pnlLogs.clearLogs();
    }
}
