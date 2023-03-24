package commons;

import java.awt.Color;
import java.awt.Graphics2D;

public interface IDessin 
{
    public boolean getRemplissage();
    
    public Color getCouleur();

    public int getEpaisseur();

    public void draw(Graphics2D g2d);
}
