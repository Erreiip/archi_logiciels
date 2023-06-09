package server.src;

import java.awt.Color;
import java.net.DatagramPacket;
import java.net.MulticastSocket;

import commons.IDessin;
import commons.MaLigne;
import commons.MonEllipse;
import commons.MonRectangle;
import commons.MonTexte;
import commons.MonTrace;

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

                IDessin shape = ThreadRecepteur.traiter(new String(dp.getData()));

                if ( shape != null ) {
                    this.server.send(shape);
                } else {
                    String message = new String(dp.getData());
                    
                    if (message.trim().equals("JVL"))
                    {
                        this.server.send();
                    }

                    if (message.trim().equals("DEL"))
                    {
                        this.server.delete();
                    }

                    if ( message.contains("Mouse") ) 
                    {
                        String[] tabInfos = message.split(";");

                        String  nom = null;
                        Integer x   = null;
                        Integer y   = null;

                        for (int cpt = 0; cpt < tabInfos.length - 1; cpt++ )
                        {
                            String[] tabSplit = tabInfos[cpt].split(":");

                            String key   = tabSplit[0];
                            String value = tabSplit[1];

                            if ( key.equals("Mouse")) {
                                nom = value;
                            }

                            if ( key.equals("x")) {
                                x = Integer.parseInt(value);
                            }

                            if ( key.equals("y")) {
                                y = Integer.parseInt(value);
                            }
                        }

                        this.server.creerSouris(nom,x,y);
                    }

                    if (message.contains("DECO")) 
                    {
                        String nom = null;
                        
                        String[] split = message.split(";");
                        nom = split[0].split(":")[1];

                        this.server.deco(nom);
                    }

                }

            } catch(Exception e) { e.printStackTrace(); }
        }
        
    }

    public static IDessin traiter(String s) 
    {
        if ( s.contains("Mouse") || s.charAt(0) != 'M') return null;

        String[] tabInfos = s.split(";");

        String nomClasse = tabInfos[0];
        Double x = null;
        Double y = null;
        Double w = null;
        Double h = null;
        String remplissage = null;
        Integer epaisseur = null;
        Integer couleur = null;
        String text = null;
        String nom = null;

        if ( nomClasse.equals(MonTexte.class.getSimpleName()) ) {

            for (int cpt = 1; cpt < tabInfos.length -1; cpt++) {
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

                if (type.equals("t")) {
                    text = valeur;
                }

                if (type.equals("n")) {
                    nom = valeur;
                }
            }

        } else {  

            for (int cpt = 1; cpt < tabInfos.length -1; cpt++) {
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

                if (type.equals("n")) {
                    nom = valeur;
                }
            }

        }
        

        IDessin shape = null;

        if (nomClasse.equals(MonEllipse.class.getSimpleName())) {
            shape = new MonEllipse(x, y, w, h, new Color(couleur), Boolean.parseBoolean(remplissage), epaisseur);
        }

        if (nomClasse.equals(MonRectangle.class.getSimpleName())) {
            shape = new MonRectangle(x, y, w, h, new Color(couleur), Boolean.parseBoolean(remplissage), epaisseur);
        }

        if (nomClasse.equals(MaLigne.class.getSimpleName())) {
            shape = new MaLigne(w, x, h, y, new Color(couleur), Boolean.parseBoolean(remplissage), epaisseur);
        }

        if (nomClasse.equals(MonTexte.class.getSimpleName())) {
            shape = new MonTexte(text, x, y, new Color(couleur), Boolean.parseBoolean(remplissage), epaisseur);
        }

        if (nomClasse.equals(MonTrace.class.getSimpleName())) {
            shape = new MonTrace();
            // Je te laisse t'en occuper
        }

        return shape;
    }
}
