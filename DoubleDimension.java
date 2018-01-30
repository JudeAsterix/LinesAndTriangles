package cohomology;

import java.awt.Dimension;

public class DoubleDimension 
{
    double x, y;
    
    public DoubleDimension()
    {
        this.x = 0.0;
        this.y = 0.0;
    }
    
    public DoubleDimension(double x, double y)
    {
        this.x = x;
        this.y = y;
    }
    
    public DoubleDimension(int x, int y)
    {
        this.x = (double)(x);
        this.y = (double)(y);
    }
    
    public String toString()
    {
        return ("(" + x + ", " + y + ")");
    }
    
    public void show()
    {
        System.out.println(toString());
    }
    
    public void set(double x, double y)
    {
        this.x = x;
        this.y = y;
    }
    
    public void set(int x, int y)
    {
        this.x = (double)(x);
        this.y = (double)(y);
    }
    
    public void set(Dimension d)
    {
        this.x = (double)(d.width);
        this.y = (double)(d.height);
    }
    
    public Dimension getRound()
    {
        return new Dimension((int)(Math.round(x)), (int)(Math.round(y)));
    }
    
    public double getX()
    {
        return x;
    }
    
    public double getY()
    {
        return y;
    }
    
    public int getXRound()
    {
        return (int)(Math.round(x));
    }
    
    public int getYRound()
    {
        return (int)(Math.round(y));
    }
}
