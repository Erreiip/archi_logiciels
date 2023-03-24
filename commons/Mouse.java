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

    private boolean drag;

    private static int nbCurseurs = 0;
    private int curseurs;

    public Mouse(String nom, int x, int y, boolean drag)
    {
        this.nom = nom;

        this.x = x;
        this.y = y;

        this.drag = drag;

        this.curseurs = Mouse.nbCurseurs++ % 4;
    }

    public String getNom() {
        return this.nom;
    }

    public int getOrientation(){
        if ( this.drag ) {
            return  ((int) (Math.random() * 60)) - 30;
        } else {
            return 0;
        }
    }

    public Image getImage() {
        try{
            BufferedImage bi = ImageIO.read(new File("./images/cursor" + (this.curseurs + 1) + ".png"));

            return bi.getScaledInstance(20, 30, Image.SCALE_DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
