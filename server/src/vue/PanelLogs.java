package server.src.vue;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import server.src.Controleur;

public class PanelLogs extends JPanel
{
    private Controleur ctrl;
    private JLabel[] logs;

    private Border brd;

    public PanelLogs(Controleur ctrl)
    {
        this.ctrl = ctrl;

        this.setLayout(new GridLayout(1, 5));
        this.logs = new JLabel[5];
        for (int i = 0; i < 5; i++)
        {
            this.logs[i] = new JLabel("",JLabel.CENTER);
            this.add(this.logs[i]);
        }

        this.brd = BorderFactory.createLineBorder(Color.BLACK, 1);
    }

    public void ajouterLog(String log)
    {
        for (int i = 4; i > 0; i--)
        {
            this.logs[i].setText(this.logs[i - 1].getText());
            if ( !this.logs[i].getText().equals("") )
                this.logs[i].setBorder(this.brd);
        }

        this.logs[0].setText(log);
        this.logs[0].setBorder(this.brd);
    }

    public void clearLogs() {
        for (int i = 0; i < 5; i++)
        {
            this.logs[i].setText("");
            this.logs[i].setBorder(null);
        }
    }

}
