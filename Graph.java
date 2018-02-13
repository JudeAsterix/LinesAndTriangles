package cohomology;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

public class Graph 
{
    private double epsilion;
    private ArrayList<DoubleDimension> points = new ArrayList<>();
    private ArrayList<ArrayList<Boolean>> lines = new ArrayList<>();
    private ArrayList<DoublePolygon> tris = new ArrayList<>();
    
    private boolean showBounds = true;
    private boolean showDots = true;
    private int DragX = 0;
    private int DragY = 0;
    
    public Graph()
    {
        this.epsilion = 150;
        createLineChart();
        findIntersections();
    }
    
    public void inputData(String s)
    {
        reset();
        double x = 0;
        double y = 0;
        int decPlace = 1;
        boolean makeX = true;
        boolean dec = false;
        boolean isNeg = false;
        
        for(int i = 0; i < s.length(); i++)
        {
            if(Character.isDigit(s.charAt(i)))
            {
                if(makeX)
                {
                    if(dec)
                    {
                        x = x + (Character.getNumericValue(s.charAt(i)) / Math.pow(10.0, decPlace));
                        decPlace++;
                    }
                    else
                    {
                        x = (x * 10) + Character.getNumericValue(s.charAt(i));
                    }
                }
                else
                {
                    if(dec)
                    {
                        y = y + (Character.getNumericValue(s.charAt(i)) / Math.pow(10.0, decPlace));
                        decPlace++;
                    }
                    else
                    {
                        y = (y * 10) + Character.getNumericValue(s.charAt(i));
                    }
                }
            }
            else if(s.charAt(i) == '.')
            {
                dec = true;
            }
            else if(s.charAt(i) == '-')
            {
                isNeg = true;
            }
            else if(s.charAt(i) != ',')
            {
                if(makeX)
                {
                    makeX = false;
                    if(isNeg)
                    {
                        x = -x;
                    }
                }
                else
                {
                    makeX = true;
                    if(isNeg)
                    {
                        y = -y;
                    }
                    
                    DoubleDimension d = new DoubleDimension(x, y);
                    boolean samePoint = false;
                    for(int j = 0; j < points.size(); j++)
                    {
                        if(d.hasSameCoords(points.get(j)))
                        {
                            samePoint = true;
                        }
                    }
                    
                    if(!samePoint)
                    {
                        points.add(d); 
                    }
                    
                    x = 0;
                    y = 0;
                }
                dec = false;
                decPlace = 1;
            }
        }
        newGraph();
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
        tris.clear();
    }
    
    public int getNumPoints()
    {
        return points.size();
    }
    
    public int getNumLines()
    {
        return lines.size();
    }
    
    public int getNumTriangles()
    {
        return tris.size();
    }
    
    public int getXDrag()
    {
        return DragX;
    }
    
    public int getYDrag()
    {
        return DragY;
    }
    
    public double getEpsilion()
    {
        return this.epsilion;
    }
    
    public int getEpsilionRound()
    {
        return (int)(Math.round(epsilion));
    }
    
    public void setXDrag(int x)
    {
        DragX = x;
    }
    
    public void setYDrag(int y)
    {
        DragY = y;
    }
    
    public void setEpsilion(double e)
    {
        epsilion = e;
        newGraph();
    }
    
    public void addPoint(DoubleDimension d)
    {
        points.add(d);
        newGraph();
    }
    
    public void newGraph()
    {
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
    
    public void toggleBounds()
    {
        this.showBounds = !this.showBounds;
    }
    
    public void findIntersections()
    {
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
    }
    
    public void paint(Graphics g)
    {
        g.fillRect((Cohomology.width / 2) - 1 + getXDrag(), (Cohomology.height / 2) - 1 + getYDrag(), 3, 3);
        g.setColor(Color.black);
        for(int i = 0; i < points.size(); i++)
        {
            
            if(showBounds)
            {
                g.fillOval(points.get(i).getXRound() - 2 + DragX, -points.get(i).getYRound() - 2 + DragY, 5, 5);
                g.drawRect(points.get(i).getXRound() - epsilionRound() + DragX, -(points.get(i).getYRound()) - epsilionRound() + DragY, 2 * epsilionRound(), 2 * epsilionRound());
            }
        }
        
        g.setColor(new Color(255, 0, 0, 5));
        for(int i = 0; i < tris.size(); i++)
        {
            g.fillPolygon(tris.get(i).getPolygonWithDrag(DragX, DragY));
        }
        
        g.setColor(Color.black);
        for(int i = 0; i < points.size(); i++)
        {
            for(int j = i + 1; j < points.size(); j++)
            {
                if(lines.get(i).get(j))
                {
                    g.drawLine(points.get(i).getXRound() + DragX, -(points.get(i).getYRound())+ DragY, points.get(j).getXRound() + DragX, -(points.get(j).getYRound()) + DragY);
                }
            }
        }
    }
}
