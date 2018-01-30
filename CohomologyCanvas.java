/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
        graph.paint(g);
    }

    @Override
    public void mouseClicked(MouseEvent me) {}
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
