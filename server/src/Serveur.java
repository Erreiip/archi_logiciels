package server.src;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;

import client.src.commons.IDessin;

import java.awt.Shape;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

public class Serveur 
{
    public static String IP_MCAST = "225.1.1.1";
    public static int PORT = 2009;


    //-----------------//
    //    DONNEES      //
    //-----------------//

    private MulticastSocket ms;

    private InetAddress mcast;

    ArrayList<IDessin> tableau;

    public Serveur() throws Exception
    {
        this.tableau  = new ArrayList<IDessin>();

        ms = new MulticastSocket(Serveur.PORT);

        mcast = InetAddress.getByName(Serveur.IP_MCAST);

        this.ms.joinGroup(this.mcast);

        ThreadRecepteur tr = new ThreadRecepteur(this, ms);
        tr.start();
    }

    public void send(IDessin forme) 
    {
        this.tableau.add(forme);

        try{ this.send(); } catch (Exception e) { e.printStackTrace(); }
    }

    public void send() throws Exception
    {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();

        ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);

        objectStream.writeObject(tableau);

        byte[] byteArray = byteStream.toByteArray();

        String longueur = byteArray.length + "";

        DatagramPacket dp = new DatagramPacket(longueur.getBytes(),longueur.length(), mcast, Serveur.PORT);

        ms.send(dp);

        //Thread.sleep(100);

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
