package cohomology;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Jandre
 */
public class CohomologyCanvas extends JPanel implements MouseListener, MouseMotionListener
{
    Graph graph = new Graph();
    boolean beingDragged;
    boolean[] overButton = {false, false, false, false, false};
    int originalDragX, originalDragY;
    
    int width = this.getWidth(), height = this.getHeight();
    public CohomologyCanvas()
    {
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        
    }
    
    public void paint(Graphics g)
    {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(Color.BLACK);
        graph.paint(g);
        
        g.setColor(new Color(100, 200, 100, 200));
        g.fillRect(10, 10, 200, 70);
        g.setColor(Color.black);
        g.drawString("Number of Points: " + graph.getNumPoints(), 15, 25);
        g.drawString("Number of Lines: " + graph.getNumLines(), 15, 40);
        g.drawString("Number of Triangles: " + graph.getNumTriangles(), 15, 55);
        g.drawString("Epsilion = " + graph.getEpsilion(), 15, 70);
        
        g.setColor(new Color(100, 100, 200, 200));
        g.fillRect(10, 85, 105, 85);
        
        
        drawButton("Import Data", 0, 100, g);
        drawButton("Export Data", 1, 115, g);
        drawButton("Change Epsilion", 2, 130, g);
        drawButton("Reset", 3, 145, g);
        drawButton("Toggle Bounds", 4, 160, g);
    }
    
    public void drawButton(String str, int id, int y, Graphics g)
    {
        if(overButton[id])
            g.setColor(Color.red);
        else
            g.setColor(Color.black);
        
        g.drawString(str, 15, y);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) 
    {
        
        BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        FontMetrics fm = g2d.getFontMetrics();
        
        if(overButton[0])
        {
            graph.inputData(JOptionPane.showInputDialog("Please insert data."));
        }
        else if(overButton[1])
        {
            System.out.println(graph.toString());
        }
        else if(overButton[2])
        {
            String ep = JOptionPane.showInputDialog("Please insert a new epsilion.");
            if(ep.isEmpty())
            {
                JOptionPane.showMessageDialog(this, "No number inputted", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                graph.setEpsilion(Double.parseDouble(ep));
            }
        }
        else if(overButton[3])
        {
            graph.reset();
        }
        else if(overButton[4])
        {
            graph.toggleBounds();
        }
        else if (!((e.getX() >= 10 && e.getX() <= 210 && e.getY() >= 10 && e.getY() <= 80)) &&
                !(e.getX() >= 10 && e.getX() <= 115 && e.getY() >= 85 && e.getY() <= 170))
        {
            graph.addPoint(new DoubleDimension(e.getX() - graph.getXDrag(), -(e.getY()) + graph.getYDrag()));
        }
        repaint();
    }
    @Override
    public void mousePressed(MouseEvent me) {}
    
    @Override
    public void mouseReleased(MouseEvent me) 
    {
        if(beingDragged)
        {
            beingDragged = false;
        }
    }
    
    @Override
    public void mouseEntered(MouseEvent me) {}
    @Override
    public void mouseExited(MouseEvent me) {}
    
    @Override
    public void mouseDragged(MouseEvent e) 
    {
        if(!beingDragged)
        {
            beingDragged = true;
            originalDragX = e.getX();
            originalDragY = e.getY();
        }
        else
        {
            graph.setXDrag(graph.getXDrag() - (originalDragX - e.getX()));
            graph.setYDrag(graph.getYDrag() - (originalDragY - e.getY()));
            originalDragX = e.getX();
            originalDragY = e.getY();
        }
        repaint();
    }
    
    @Override
    public void mouseMoved(MouseEvent e) 
    {
        BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        FontMetrics fm = g2d.getFontMetrics();
        
        
        for(int i = 0; i < overButton.length; i++)
        {
            overButton[i] = false;
        }
        
        if(e.getX() >= 15 && e.getX() <= fm.stringWidth("Import Data") + 15 && e.getY() >= 85 && e.getY() < 100)
        {
            overButton[0] = true;
        }
        else if(e.getX() >= 15 && e.getX() <= fm.stringWidth("Export Data") + 15 && e.getY() >= 100 && e.getY() < 115)
        {
            overButton[1] = true;
        }
        else if(e.getX() >= 15 && e.getX() <= fm.stringWidth("Change Epsilion") + 15 && e.getY() >= 115 && e.getY() < 130)
        {
            overButton[2] = true;
        }
        else if(e.getX() >= 15 && e.getX() <= fm.stringWidth("Reset") + 15 && e.getY() >= 130 && e.getY() < 145)
        {
            overButton[3] = true;
        }
        else if(e.getX() >= 15 && e.getX() <= fm.stringWidth("Toggle Bounds") + 15 && e.getY() >= 145 && e.getY() < 160)
        {
            overButton[4] = true;
        }
        repaint();
    }
}
