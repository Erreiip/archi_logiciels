package client.src.metier;

import java.net.DatagramPacket;
import java.net.MulticastSocket;
import java.util.ArrayList;

import client.src.commons.IDessin;

import java.awt.Shape;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;


public class ThreadClientRecepteur extends Thread 
{
    private Client client;

    private MulticastSocket ms;

    public ThreadClientRecepteur( Client client, MulticastSocket ms)
    {
        this.client = client;

        this.ms = ms;
    }
    
    public void run()
    {
        while (true) {
            try {
                DatagramPacket dp = new DatagramPacket(new byte[512], 512);

                this.ms.receive(dp);

                String message = new String(dp.getData());

                if ( this.estEntier(message) )
                {

                    int taille = Integer.parseInt(message.trim());

                    dp = new DatagramPacket(new byte[taille], taille);

                    this.ms.receive(dp);

                    ByteArrayInputStream byteStream = new ByteArrayInputStream(dp.getData());

                    ObjectInputStream objectStream = new ObjectInputStream(byteStream);

                    ArrayList<IDessin> alFormes = (ArrayList<IDessin>) objectStream.readObject();

                    client.maj( alFormes );
                } else 
                {
                    if ( message.contains("Mouse") ) 
                    {
                        String[] tabInfos = message.split(";");

                        String  nom = null;
                        Integer x   = null;
                        Integer y   = null;

                        for ( String s : tabInfos )
                        {
                            String[] tabSplit = s.split(":");

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

                        this.client.creerSouris(nom,x,y);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    private boolean estEntier( String entier )
    {
        try{
            Integer.parseInt(entier.trim());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
