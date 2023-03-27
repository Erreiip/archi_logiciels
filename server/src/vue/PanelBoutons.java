package server.src.vue;

import server.src.Controleur;
import server.src.ThreadRecepteur;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import commons.IDessin;
import commons.*;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.io.PrintWriter;

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
            System.out.println("Arrêt du serveur");
            System.exit(0);
        }
        else if (e.getSource() == this.btnSauvegarder) {

            String filePath = JOptionPane.showInputDialog("Entrez le nom du fichier : ");

            try{
                File file = new File(filePath + ".tableau");
                
                PrintWriter pw = new PrintWriter(file);

                if(!file.exists()){
                    file.createNewFile();

                    for (IDessin forme : this.ctrl.getAlShape())
                    {
                        pw.println(formaterForme(forme));
                    }
                    pw.close();
                }

			} catch(Exception error){error.printStackTrace();}
        }
        else if (e.getSource() == this.btnOuvrir) {

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
                        String line = sc.nextLine().trim() + " ";
                        this.ctrl.ajouterForme(ThreadRecepteur.traiter(line));
                    }
                }

            }catch(Exception error){error.printStackTrace();}
            
            this.ctrl.send();
        }
    }
    
    public String formaterForme(IDessin forme) {
        if ( forme == null ) return "";
        
        Color couleurForme = forme.getCouleur();
        String envoie = "";
        if ( forme instanceof MaLigne)
        {
            Shape shape = (Shape) forme;
            MaLigne ligne = (MaLigne) shape;
            envoie = forme.getClass().getSimpleName() + ";x:" + ligne.getY1() +
            ";y:" + ligne.getY2() + ";w:" + ligne.getX1() +
            ";h:" + ligne.getX2() + ";r:" + forme.getRemplissage() +
            ";e:" + forme.getEpaisseur() + ";c:" + couleurForme.getRGB() + ";";
        } else if ( forme instanceof MonTexte)
        {
            MonTexte text = (MonTexte) forme;
            envoie =  forme.getClass().getSimpleName() + ";x:" + text.getX() +
            ";y:" + text.getY() + ";t:" + text.getTexte() +";r:" + forme.getRemplissage() +
            ";e:" + forme.getEpaisseur() + ";c:" + couleurForme.getRGB() + ";";
        } else if ( forme instanceof MonTrace)
        {
            envoie = forme.getClass().getSimpleName() + ";r:" + forme.getRemplissage() +
            ";e:" + forme.getEpaisseur() + ";c:" + couleurForme.getRGB() + ";";
            MonTrace trace = (MonTrace) forme;
            for ( int i = 0; i < trace.getAlPoint().size(); i++) {
                envoie += trace.getAlPoint().get(i).getX() + ":" + trace.getAlPoint().get(i).getY() + ";";
            }
        } else 
        {
            Shape shape = (Shape) forme;
            envoie = forme.getClass().getSimpleName() + ";x:" + shape.getBounds2D().getX() +
            ";y:" + shape.getBounds2D().getY() + ";w:" + shape.getBounds2D().getWidth() +
            ";h:" + shape.getBounds2D().getHeight() + ";r:" + forme.getRemplissage() +
            ";e:" + forme.getEpaisseur() + ";c:" + couleurForme.getRGB() + ";";
        }

        return envoie;
    }
}   

