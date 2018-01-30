package cohomology;

import java.awt.Dimension;
import javax.swing.JFrame;

public class Cohomology 
{
    public static int width;
    public static int height;
    
    public static void createAndShowGUI()
    {
        JFrame frame = new JFrame("Cech Cohomology");
        frame.setPreferredSize(new Dimension (1000, 618));
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        CohomologyCanvas c = new CohomologyCanvas();
        frame.add(c);
        frame.pack();
        frame.setResizable(false);
        frame.setFocusable(true);
        frame.setVisible(true);
    }
    
    public static void main(String[] args) 
    {
        createAndShowGUI();
    }
}
