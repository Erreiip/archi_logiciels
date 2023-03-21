package server.src.vue;

import server.src.Controleur;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PanelBoutons extends JPanel implements ActionListener{
    
    private Controleur ctrl;
    private JButton btnClear;
    private JButton btnStop;
    private JButton btnSauvegarder;
    private JButton btnOuvrir;

    public PanelBoutons(Controleur ctrl) {

        this.ctrl = ctrl;

        this.setLayout(new GridLayout(1, 2));

        this.btnClear = new JButton("Clear");
        this.btnStop = new JButton("Stop");
        this.btnSauvegarder = new JButton("Sauvegarder");
        this.btnOuvrir = new JButton("Ouvrir");

        this.btnStop.addActionListener(this);
        this.btnClear.addActionListener(this);
        this.btnSauvegarder.addActionListener(this);
        this.btnOuvrir.addActionListener(this);

        this.add(this.btnClear);
        this.add(this.btnStop);
        this.add(this.btnSauvegarder);
        this.add(this.btnOuvrir);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnClear) {
            this.ctrl.clearLogs();
        }
        else if (e.getSource() == this.btnStop) {
            System.out.println("Stop le serveur");
        }
        else if (e.getSource() == this.btnSauvegarder) {
            System.out.println("Sauvegarder");
        }
        else if (e.getSource() == this.btnOuvrir) {
            System.out.println("Ouvrir");
        }
    }
}
