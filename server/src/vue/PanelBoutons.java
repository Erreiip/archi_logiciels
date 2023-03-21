package server.src.vue;

import server.src.Controleur;
import server.src.ThreadRecepteur;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

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
            this.ctrl.clear();
        }
        else if (e.getSource() == this.btnStop) {
            System.out.println("Stop le serveur");
        }
        else if (e.getSource() == this.btnSauvegarder) {
            System.out.println("Sauvegarder");

            String filePath = JOptionPane.showInputDialog("Entrez le nom du fichier : ");

            try{
                File file = new File(filePath);
                ByteArrayOutputStream convert = new ByteArrayOutputStream();

                byte[] bytes = convert.toByteArray();

                if(!file.exists()){
                    file.createNewFile();

                    OutputStreamWriter ow = new FileWriter(file.getAbsoluteFile(), StandardCharsets.UTF_8);
			        BufferedWriter bw = new BufferedWriter(ow);
                }

			} catch(Exception error){error.printStackTrace();}
        }
        else if (e.getSource() == this.btnOuvrir) {
            System.out.println("Ouvrir");

            try{
				JFileChooser chooser = new JFileChooser(".");
                
                FileFilter filtre = new FileNameExtensionFilter("TABLEAU (*.tableau)", "tableau");
                chooser.setFileFilter(filtre);
                chooser.setAcceptAllFileFilterUsed(false);
				
				int res = chooser.showOpenDialog(this);
				if (res == JFileChooser.APPROVE_OPTION)
                {
                    Scanner sc = new Scanner(chooser.getSelectedFile());
                    while (sc.hasNextLine())
                    {
                        String line = sc.nextLine();
                        this.ctrl.ajouterForme(ThreadRecepteur.traiter(line));
                    }
                }

            }catch(Exception error){error.printStackTrace();}
            
            this.ctrl.send();
        }
    }
}
