package client.src.vue.dessin;

import client.src.Controleur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PanelChoixCouleurs extends JPanel implements ActionListener{
    
    private Controleur ctrl;
    private JButton[] boutonsCouleurs;
    private Color[] couleurs;

    public PanelChoixCouleurs(Controleur ctrl) {
        this.ctrl = ctrl;
        
        this.couleurs = new Color[]{Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE, Color.PINK, Color.CYAN, Color.MAGENTA};
        this.boutonsCouleurs = new JButton[this.couleurs.length];
        this.setLayout(new GridLayout(1, this.couleurs.length, 10, 10));

        for(int i = 0; i < this.couleurs.length; i++){
            this.boutonsCouleurs[i] = new JButton();
            this.boutonsCouleurs[i].setPreferredSize(new Dimension(30, 30));
            this.boutonsCouleurs[i].setBackground(this.couleurs[i]);
            this.boutonsCouleurs[i].addActionListener(this);
            this.add(this.boutonsCouleurs[i]);
        }
    }

    public void actionPerformed(ActionEvent e) {
        for(int i = 0; i < this.couleurs.length; i++){
            if(e.getSource() == this.boutonsCouleurs[i]){
                this.ctrl.setCouleurCourante(this.couleurs[i]);
            }
        }
    }
}