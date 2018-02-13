
package cohomology;

import java.awt.Dimension;
import java.awt.Polygon;
import java.util.ArrayList;
public class DoublePolygon 
{
    private int sides;
    ArrayList<DoubleDimension> dims = new ArrayList<>();
    
    public DoublePolygon()
    {
        sides = 0;
    }
    
    public DoublePolygon(DoubleDimension[] dims)
    {
        sides = dims.length;
        for(int i = 0; i < sides; i++)
        {
            this.dims.add(dims[i]);
        }
    }
    
    public DoublePolygon(double[][] dims)
    {
        sides = dims.length;
        for(int i = 0; i < sides; i++)
        {
            this.dims.add(new DoubleDimension(dims[i][0], dims[i][1]));
        }
    }
    
    public DoublePolygon(int[][] dims)
    {
        sides = dims.length;
        for(int i = 0; i < sides; i++)
        {
            this.dims.add(new DoubleDimension(dims[i][0], dims[i][1]));
        }
    }
    
    public String toString()
    {
        String s = "Number of sides: " + this.sides + "\n";
        for(int i = 0; i < dims.size(); i++)
        {
            s += "   ~" + dims.get(i).toString() + "\n";
        }
        return s;
    }
  
    public void show()
    {
        System.out.println(toString());
    }
    
    public DoubleDimension getDim(int node)
    {
        if(node < sides)
        {
            return(dims.get(node));
        }
        else
        {
            return null;
        }
    }
    
    public Dimension getIntDim(int node)
    {
        if(node < sides)
        {
            return(dims.get(node).getRound());
        }
        else
        {
            return null;
        }
    }
    
    public int[] getintX()
    {
        int[] ret = new int[sides];
        for(int i = 0; i < sides; i++)
        {
            ret[i] = getIntDim(i).width;
        }
        return ret;
    }
    
    public int[] getintXWithDrag(int DragX)
    {
        int[] ret = new int[sides];
        for(int i = 0; i < sides; i++)
        {
            ret[i] = getIntDim(i).width + DragX;
        }
        return ret;
    }
    
    public int[] getintY()
    {
        int[] ret = new int[sides];
        for(int i = 0; i < sides; i++)
        {
            ret[i] = -(getIntDim(i).height);
        }
        return ret;
    }
    
    public int[] getintYWithDrag(int DragY)
    {
        int[] ret = new int[sides];
        for(int i = 0; i < sides; i++)
        {
            ret[i] = -(getIntDim(i).height) + DragY;
        }
        return ret;
    }
    
    public Polygon getPolygon()
    {
        return new Polygon(getintX(), getintY(), sides);
    }
    
    public Polygon getPolygonWithDrag(int DragX, int DragY)
    {
        return new Polygon(getintXWithDrag(DragX), getintYWithDrag(DragY), sides);
    }
}
