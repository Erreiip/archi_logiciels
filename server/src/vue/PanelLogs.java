package server.src.vue;

import javax.swing.*;
import java.awt.*;
import server.src.Controleur;

public class PanelLogs extends JPanel
{
    private Controleur ctrl;
    private JLabel[] logs;

    public PanelLogs(Controleur ctrl)
    {
        this.ctrl = ctrl;

        this.setLayout(new GridLayout(1, 5));
        this.logs = new JLabel[5];
        for (int i = 0; i < 5; i++)
        {
            this.logs[i] = new JLabel();
            this.logs[i].setText("");
            this.add(this.logs[i]);
        }
    }

    public void ajouterLog(String log)
    {
        for (int i = 4; i > 0; i--)
        {
            this.logs[i].setText(this.logs[i - 1].getText());
        }
        this.logs[0].setText(log);
    }

    public void clearLogs() {
        for (int i = 0; i < 5; i++)
        {
            this.logs[i].setText("");
        }
    }

}
