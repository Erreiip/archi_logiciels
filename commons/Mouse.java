package commons;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Mouse 
{
    private String nom;
    public int x;
    public int y;

    private static int nbCurseurs = 1;
    private int curseurs;

    public Mouse(String nom, int x, int y )
    {
        this.nom = nom;

        this.x = x;
        this.y = y;

        this.curseurs = Mouse.nbCurseurs++;
    }

    public String getNom() {
        return this.nom;
    }

    public Image getImage() {
        try{
            BufferedImage bi = ImageIO.read(new File(
                    "C:\\Users\\Pierre\\Documents\\iut\\s4\\r4.01_architect_logicielle\\archi_logiciels\\images\\cursor" + this.curseurs + ".png"));

            return bi.getScaledInstance(20, 30, Image.SCALE_DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
