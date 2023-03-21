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
        
        this.couleurs = new Color[]{Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE, Color.CYAN, Color.MAGENTA};
        this.boutonsCouleurs = new JButton[this.couleurs.length+1];
        this.setLayout(new GridLayout(1, this.couleurs.length, 10, 10));

        for(int i = 0; i < this.couleurs.length; i++){
            this.boutonsCouleurs[i] = new JButton();
            this.boutonsCouleurs[i].setPreferredSize(new Dimension(30, 30));
            this.boutonsCouleurs[i].setBackground(this.couleurs[i]);
            this.boutonsCouleurs[i].addActionListener(this);
            this.add(this.boutonsCouleurs[i]);
        }
        this.boutonsCouleurs[this.couleurs.length] = new JButton("Couleurs");
        this.boutonsCouleurs[this.couleurs.length].addActionListener(this);
        this.add(this.boutonsCouleurs[this.couleurs.length]);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.boutonsCouleurs[this.couleurs.length]){

            Color nouvelleCouleur = new JColorChooser().showDialog(this, "Choisir une couleur", this.ctrl.getCouleurCourante());
            for (int i = 0; i < this.couleurs.length; i++) {
                if (nouvelleCouleur.equals(this.couleurs[i]))
                    this.ctrl.setCouleurCourante(this.couleurs[i]);
            }

            if (!nouvelleCouleur.equals(this.ctrl.getCouleurCourante())) {
                this.ctrl.setCouleurCourante(nouvelleCouleur);

                for(int j = this.couleurs.length - 1; j > 0; j--){
                    this.couleurs[j] = this.couleurs[j - 1];
                    this.boutonsCouleurs[j].setBackground(this.couleurs[j]);
                }
                this.couleurs[0] = this.ctrl.getCouleurCourante();
                this.boutonsCouleurs[0].setBackground(this.couleurs[0]);
            }
        }
        else {
            for(int i = 0; i < this.couleurs.length; i++){
                if(e.getSource() == this.boutonsCouleurs[i]){
                    this.ctrl.setCouleurCourante(this.couleurs[i]);
                }
            }
        }

    }
}