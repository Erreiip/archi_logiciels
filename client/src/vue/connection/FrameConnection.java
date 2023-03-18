package client.src.vue.connection;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import client.src.Controleur;

public class FrameConnection extends JFrame  implements ActionListener
{
    private Controleur ctrl;

    JTextField txtNom;

    public FrameConnection( Controleur ctrl )
    {
        this.ctrl = ctrl;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(new BorderLayout());

        JPanel pnl = new JPanel();
        pnl.setLayout(new BorderLayout());

        this.txtNom = new JTextField("");
        this.txtNom.addActionListener(this);

        this.setVisible(true);

        pnl.add(this.txtNom, BorderLayout.CENTER);
        this.add(pnl, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.ctrl.setNom(((JTextField) e.getSource()).getText());
    }
    
}
