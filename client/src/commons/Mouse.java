package client.src.commons;

public class Mouse 
{
    private String nom;
    public int x;
    public int y;

    public Mouse(String nom, int x, int y )
    {
        this.nom = nom;

        this.x = x;
        this.y = y;
    }

    public String getNom() {
        return this.nom;
    }

}
