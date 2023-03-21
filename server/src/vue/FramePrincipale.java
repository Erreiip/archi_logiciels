package server.src.vue;

import javax.swing.JFrame;
import javax.swing.plaf.BorderUIResource;

import java.awt.*;

import server.src.Controleur;

public class FramePrincipale extends JFrame
{
    private Controleur ctrl;

    private PanelLogs pnlLogs;
    private PanelDessin pnlDessin;
    
    public FramePrincipale(Controleur ctrl)
    {
        this.ctrl = ctrl;
        this.setLayout(new BorderLayout());


        this.pnlLogs = new PanelLogs(this.ctrl);
        this.pnlDessin = new PanelDessin(this.ctrl);


        this.add( this.pnlLogs, BorderLayout.NORTH);
        this.add( this.pnlDessin, BorderLayout.CENTER);


        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void maj() {
        this.pnlDessin.repaint();
    }
}
