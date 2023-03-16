package server.src;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;

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
        try 
        {
            DatagramPacket dp = new DatagramPacket(new byte[512], 512);

            ms.receive(dp);

            traiter(new String(dp.getData()));

        } catch(Exception e) { e.printStackTrace(); }
        
    }

    public void traiter(String s) 
    {
        System.out.println(s);
    }
}
