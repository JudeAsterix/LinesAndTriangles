package cohomology;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

public class Graph 
{
    double epsilion;
    ArrayList<DoubleDimension> points = new ArrayList<>();
    ArrayList<ArrayList<Boolean>> lines = new ArrayList<>();
    ArrayList<DoublePolygon> tris = new ArrayList<>();
    
    boolean showBounds = false;
    
    public Graph()
    {
        this.epsilion = 150;
        this.points.add(new DoubleDimension(500, 670));
        this.points.add(new DoubleDimension(530, 600));
        createLineChart();
        findIntersections();
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
    
    public void reset()
    {
        points.clear();
        lines.clear();
    }
    
    public void addPoint(DoubleDimension d)
    {
        points.add(d);
        lines.clear();
        tris.clear();
        createLineChart();
        findIntersections();
    }
    
    public void createLineChart() 
    {
        for(int i = 0; i < points.size(); i++)
        {
            lines.add(new ArrayList());
            for(int j = 0; j < points.size(); j++)
            {
                if(j <= i)
                {
                    lines.get(i).add(null);
                }
                else
                {
                    lines.get(i).add(false);   
                }
            }
        }
    }
    
    public void findIntersections()
    {
        long startTime = System.nanoTime();
        System.out.println("Calculating...");
        for(int i = 0; i < points.size(); i++)
        {
            for(int j = i + 1; j < points.size(); j++)
            {
                if(Math.abs(points.get(i).getX() - points.get(j).getX()) < 2 * epsilion && Math.abs(points.get(i).getY() - points.get(j).getY()) < 2 * epsilion) // |x1 - x2| < 2 * ep && |y1 - y2| < 2 * ep => intersection
                {
                    this.lines.get(i).set(j, true);
                }
            }
        }
        
        for(int l = 0; l < lines.size(); l++)
        {
            for(int m = l + 1; m < lines.size(); m++)
            {
                if(lines.get(l).get(m))
                {
                    for(int n = m + 1; n < lines.size(); n++)
                    {
                        if(lines.get(l).get(n) && lines.get(m).get(n))
                        {
                            DoubleDimension[] d = {points.get(l), points.get(n), points.get(m)};
                            tris.add(new DoublePolygon(d));
                        }
                    }
                }
            }
        }
        long endTime = System.nanoTime();
        System.out.println("Time to calculate: " + ((endTime - startTime) / 1000000));
    }
    
    public void paint(Graphics g)
    {
        long startTime = System.nanoTime();
        for(int i = 0; i < points.size(); i++)
        {
            g.setColor(Color.black);
            g.fillOval(points.get(i).getXRound() - 2, points.get(i).getYRound() - 2, 5, 5);
            if(showBounds)
            {
                g.drawRect(points.get(i).getXRound() - epsilionRound(), points.get(i).getYRound() - epsilionRound(), 2 * epsilionRound(), 2 * epsilionRound());
            }
        }
        
        for(int i = 0; i < points.size(); i++)
        {
            for(int j = i + 1; j < points.size(); j++)
            {
                if(lines.get(i).get(j))
                {
                    g.drawLine(points.get(i).getXRound(), points.get(i).getYRound(), points.get(j).getXRound(), points.get(j).getYRound());
                }
            }
        }
        
        g.setColor(new Color(100, 100, 100, 25));
        for(int i = 0; i < tris.size(); i++)
        {
            g.fillPolygon(tris.get(i).getPolygon());
        }
        long endTime = System.nanoTime();
        System.out.println("Time to paint: " + ((endTime - startTime) / 1000000));
    }
}
