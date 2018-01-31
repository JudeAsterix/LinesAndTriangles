package cohomology;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author Jandre
 */
public class CohomologyCanvas extends Canvas implements MouseListener, MouseMotionListener
{
    Graph graph = new Graph();
    public CohomologyCanvas()
    {
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }
    
    public void paint(Graphics g)
    {
        graph.show();
        
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(Color.BLACK);
        g.fillRect(this.getWidth() / 2 - 1, this.getHeight() / 2 - 1, 3, 3);
        graph.paint(g);
    }
    
    public void repaint(Graphics g)
    {
        paint(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) 
    {
        graph.addPoint(new DoubleDimension(e.getX(), e.getY()));
        repaint();
    }
    @Override
    public void mousePressed(MouseEvent me) {}
    @Override
    public void mouseReleased(MouseEvent me) {}
    @Override
    public void mouseEntered(MouseEvent me) {}
    @Override
    public void mouseExited(MouseEvent me) {}
    @Override
    public void mouseDragged(MouseEvent me) {}
    @Override
    public void mouseMoved(MouseEvent me) {}
    
}
