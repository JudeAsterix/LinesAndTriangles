
package cohomology;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Graph 
{
    double epsilion;
    ArrayList<DoubleDimension> points = new ArrayList<>();
    ArrayList<ArrayList<Boolean>> lines = new ArrayList<>();
    
    public Graph()
    {
        this.epsilion = 50;
        this.points.add(new DoubleDimension(500, 670));
        this.points.add(new DoubleDimension(530, 600));
    }
    
    public String toString()
    {
        String s = ("Epsilon = " + epsilion + "\n");
        s += ("Number of points: " + points.size() + "\n");
        
        for(int i = 0; i < points.size(); i++)
        {
            s += ("  ~" + points.get(i).toString() + "\n");
        }
        
        return s;
    }
    
    public int epsilionRound()
    {
        return (int)(Math.round(epsilion));
    }
    
    public void show()
    {
        System.out.println(toString());
    }
    
    public void findIntersections()
    {
        for(int i = 0; i < points.size(); i++)
        {
            for(int j = i + 1; j < points.size(); j++)
            {
                if(Math.abs(points.get(i).getX() - points.get(j).getX()) < epsilion && Math.abs(points.get(i).getY() - points.get(j).getY()) < epsilion)
                {
                    //TODO connect two 
                }
            }
        }
    }
    
    public void paint(Graphics g)
    {
        for(int i = 0; i < points.size(); i++)
        {
            g.setColor(Color.black);
            g.fillOval(points.get(i).getXRound() - 2, points.get(i).getYRound() - 2, 5, 5);
            g.drawRect(points.get(i).getXRound() - epsilionRound(), points.get(i).getYRound() - epsilionRound(), 2 * epsilionRound(), 2 * epsilionRound());
        }
    }
}
