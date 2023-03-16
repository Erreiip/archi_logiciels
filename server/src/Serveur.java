package server.src;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;
import java.awt.Shape;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

public class Serveur 
{
    public static String IP_MCAST = "225.1.1.1";


    private MulticastSocket ms;

    private InetAddress mcast;

    ArrayList<Shape> tableau;

    public Serveur() throws Exception
    {
        ms = new MulticastSocket();

        mcast = InetAddress.getByName(IP_MCAST);
    }

    public void send() throws Exception
    {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();

        ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);

        objectStream.writeObject(tableau);

        byte[] byteArray = byteStream.toByteArray();

        String longueur = byteArray.length + "";

        DatagramPacket dp = new DatagramPacket(longueur.getBytes(),longueur.length(), mcast,2009);

        ms.send(dp);

        Thread.sleep(1000);

        dp = new DatagramPacket(byteArray,byteArray.length, mcast,2009);

        ms.send(dp);
    }

    public MulticastSocket getMultiCastSocket()
    {
        return this.ms;
    }

    public InetAddress getInetAddress()
    {
        return this.mcast;
    }
    
}
