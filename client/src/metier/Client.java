package client.src.metier;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;

import client.src.Controleur;
import client.src.commons.IDessin;
import client.src.commons.MonTexte;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Line2D;


public class Client 
{

    public static int PORT = 2009;
    public static String IP_MCAST = "225.4.3.1";

    //-----------------//
    //    DONNEES      //
    //-----------------//

    private Controleur ctrl;

    private MulticastSocket ms;

    private InetAddress mcast; 
    
    public Client(Controleur ctrl)
    {
        try{
            this.ctrl = ctrl;

            this.ms = new MulticastSocket(Client.PORT);

            this.mcast = InetAddress.getByName(IP_MCAST);

            this.ms.joinGroup(this.mcast);

            ThreadClientRecepteur tcr = new ThreadClientRecepteur(this, ms);
            tcr.start();
        }catch(Exception e) { e.printStackTrace(); }
    }


    public void send( String msg )
    {
        try{
            DatagramPacket dp = new DatagramPacket(msg.getBytes(), msg.length(), this.mcast, PORT);

            this.ms.send(dp);
        }catch(Exception e) { e.printStackTrace(); }
        
    }

    public void delete()
    {
        this.send("DEL");
    }

    public void send(IDessin forme)
    {
        if ( forme == null ) 
        {
            this.send("JVL");
            return;
        }

        
        Color couleurForme = forme.getCouleur();
        String envoie = "";
        if ( forme instanceof Line2D)
        {
            Shape shape = (Shape) forme;
            Line2D ligne = (Line2D) shape;
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
        } else 
        {
            Shape shape = (Shape) forme;
            envoie = forme.getClass().getSimpleName() + ";x:" + shape.getBounds2D().getX() +
            ";y:" + shape.getBounds2D().getY() + ";w:" + shape.getBounds2D().getWidth() +
            ";h:" + shape.getBounds2D().getHeight() + ";r:" + forme.getRemplissage() +
            ";e:" + forme.getEpaisseur() + ";c:" + couleurForme.getRGB() + ";";
        }
       

       this.send(envoie);
    }

    public void maj(ArrayList<IDessin> alFormes)
    {
        this.ctrl.maj(alFormes);
    }
}
