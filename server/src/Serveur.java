package server.src;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;

import commons.IDessin;
import commons.Mouse;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

public class Serveur 
{
    public static String IP_MCAST = "225.4.3.1";
    public static int PORT = 2009;


    //-----------------//
    //    DONNEES      //
    //-----------------//

    private MulticastSocket ms;

    private InetAddress mcast;

    ArrayList<IDessin> tableau;
    private ArrayList<Mouse> alSouris;

    private Controleur ctrl;

    public Serveur(Controleur ctrl) throws Exception
    {
        this.ctrl = ctrl;

        this.tableau  = new ArrayList<IDessin>();
        this.alSouris  = new ArrayList<Mouse>();

        ms = new MulticastSocket(Serveur.PORT);

        mcast = InetAddress.getByName(Serveur.IP_MCAST);

        this.ms.joinGroup(this.mcast);

        ThreadRecepteur tr = new ThreadRecepteur(this, ms);
        tr.start();
    }

    public void delete()
    {
        if ( this.tableau.size() > 0)
        {
            this.tableau.remove(this.tableau.size()-1);
            
            try{ this.send(); } catch (Exception e) { e.printStackTrace(); }
        }
    }

    public void send(IDessin forme) 
    {
        this.tableau.add(forme);

        this.ctrl.ajouterLogs(forme);

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

        dp = new DatagramPacket(byteArray,byteArray.length, mcast, Serveur.PORT);

        ms.send(dp);

        this.ctrl.maj();
    }

    public MulticastSocket getMultiCastSocket()
    {
        return this.ms;
    }

    public InetAddress getInetAddress()
    {
        return this.mcast;
    }

    public ArrayList<IDessin> getAlShape() {
        return this.tableau;
    }

    public void ajouterForme(IDessin s) {
        this.tableau.add(s);
    }

    public void clear() {
        this.tableau.clear();
        try {
            this.send();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Mouse> getAlMouse() {
        return this.alSouris;
    }
    
    public void creerSouris(String nom, int x, int y) {
        for ( Mouse m : this.alSouris )
        {
            if (m.getNom().equals(nom)) {
                m.x = x;
                m.y = y;
                return;
            }
        }

        Mouse m = new Mouse(nom, x, y, false);
        this.alSouris.add(m);
        
    }
}
