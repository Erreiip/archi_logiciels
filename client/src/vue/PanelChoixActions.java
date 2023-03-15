package client.src.vue;

import client.src.Controleur;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PanelChoixActions extends JPanel implements ActionListener {
    
    private Controleur ctrl;
    private JButton[] boutonsActions;
    private JCheckBox CBremplissage;
    private String[] actions;

    public PanelChoixActions(Controleur ctrl) {
        this.ctrl = ctrl;
        
        this.actions = new String[]{"Cercle", "Rectangle", "Ligne", "Texte", "Effacer"};
        this.boutonsActions = new JButton[this.actions.length];
        this.setLayout(new GridLayout(1, this.actions.length, 10, 10));

        for(int i = 0; i < this.actions.length; i++){
            this.boutonsActions[i] = new JButton(this.actions[i]);
            this.boutonsActions[i].setPreferredSize(new Dimension(30, 30));
            this.boutonsActions[i].addActionListener(this);
            this.add(this.boutonsActions[i]);
        }
        this.CBremplissage = new JCheckBox("Plein");
        this.add(this.CBremplissage);
    }


    public boolean getCBremplissage() {
        return this.CBremplissage.isSelected();
    }

    public void actionPerformed(ActionEvent e) {
        for(int i = 0; i < this.actions.length; i++){
            if(e.getSource() == this.boutonsActions[i]){
                this.ctrl.setActionCourante(this.actions[i]);
            }
        }
    }
}