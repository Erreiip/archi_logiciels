package server.src;

import java.awt.Color;
import java.awt.Shape;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;

import client.src.commons.MaLigne;
import client.src.commons.MonEllipse;
import client.src.commons.MonRectangle;
import client.src.commons.MonTexte;

public class ThreadRecepteur extends Thread
{
    private Serveur server;

    private MulticastSocket ms;

    public ThreadRecepteur(Serveur serv, MulticastSocket ms) 
    {
        this.server = serv;

        this.ms = ms;
    } 


    public void run()
    {
        while( true )
        {
            try 
            {
                DatagramPacket dp = new DatagramPacket(new byte[512], 512);

                ms.receive(dp);

                traiter(new String(dp.getData()));

            } catch(Exception e) { e.printStackTrace(); }
        }
        
    }

    public void traiter(String s) 
    {
        System.out.println(s);

        String[] tabInfos = s.split(";");

        String nomClasse = tabInfos[0];
        Double x = null;
        Double y = null;
        Double w = null;
        Double h = null;
        String remplissage = null;
        Integer epaisseur = null;
        Integer couleur = null;

                
        for (int cpt = 1; cpt < tabInfos.length; cpt++) {
            String[] split = tabInfos[cpt].split(":");

            String type = split[0];
            String valeur = split[1];

            if (type.equals("e")) {
                epaisseur = Integer.parseInt(valeur);
            }

            if (type.equals("r")) {
                remplissage = valeur;
            }

            if (type.equals("c")) {
                couleur = Integer.parseInt(valeur);
            }

            if (type.equals("x")) {
                x = Double.parseDouble(valeur);
            }

            if (type.equals("y")) {
                y = Double.parseDouble(valeur);
            }

            if (type.equals("w")) {
                w = Double.parseDouble(valeur);
            }

            if (type.equals("h")) {
                h = Double.parseDouble(valeur);
            }
        }
        

        Shape shape = null;

        if (nomClasse.equals(MonEllipse.class.getSimpleName())) {
            shape = new MonEllipse(x, y, w, h, new Color(couleur), Boolean.parseBoolean(remplissage), epaisseur);
        }

        if (nomClasse.equals(MonRectangle.class.getSimpleName())) {
            shape = new MonRectangle(x, y, w, h, new Color(couleur), Boolean.parseBoolean(remplissage), epaisseur);
        }

        if (nomClasse.equals(MaLigne.class.getSimpleName())) {
            shape = new MaLigne(x, y, w, h, new Color(couleur), Boolean.parseBoolean(remplissage), epaisseur);
        }

        if (nomClasse.equals(MonTexte.class.getSimpleName())) {
            // faut faire une conditipon spéciale au début shape = new MonTexte(x, y, w, h, new Color(couleur), Boolean.parseBoolean(remplissage), epaisseur);
        }

        System.out.println(shape);

    }
}
