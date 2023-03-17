package client.src;

import java.net.DatagramPacket;
import java.net.MulticastSocket;
import java.util.ArrayList;

import java.awt.Shape;


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
                    int taille = Integer.parseInt(message);

                    dp = new DatagramPacket(new byte[taille], taille);

                    this.ms.receive(dp);

                    //le convertien en ArrayLIst

                    ArrayList<Shape> alFormes = null;

                    client.maj( alFormes );
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    private boolean estEntier( String entier )
    {
        try{
            Integer.parseInt(entier);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
