package client.src;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;

import java.awt.Shape;


public class Client 
{

    public static int PORT = 2009;
    public static String IP_MCAST = "225.1.1.1";

    //-----------------//
    //    DONNEES      //
    //-----------------//

    private Controleur ctrl;

    private MulticastSocket ms;

    private InetAddress mcast; 
    
    public Client(Controleur ctrl) throws Exception
    {
        this.ctrl = ctrl;

        this.ms = new MulticastSocket(Client.PORT);

        this.mcast = InetAddress.getByName(IP_MCAST);

        this.ms.joinGroup(this.mcast);

        ThreadClientRecepteur tcr = new ThreadClientRecepteur(this, ms);
        tcr.start();

        String messageInit = "Je veux la liste";

        DatagramPacket dp = new DatagramPacket(messageInit.getBytes(), messageInit.length(), this.mcast, PORT);

        this.ms.send(dp);
    }

    public void send( String shape ) throws Exception
    {
        DatagramPacket dp = new DatagramPacket(shape.getBytes(), shape.length(), this.mcast, PORT);

        this.ms.send(dp);
    }

    public void maj(ArrayList<Shape> alFormes)
    {
        this.ctrl.maj(alFormes);
    }
}
