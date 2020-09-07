package inputOutput;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import machine.Memory;

/**
 *
 * @author david
 */
public class Screen extends JPanel{
       
    int scale; 
    int width; 
    int height;
    
    private Graphics g;
    private final Memory mem;
    
    public Screen(Memory mem, int scale, int width, int height){
        super(null);
        this.mem = mem;
        this.scale = scale;
        this.width = width;
        this.height = height;
        
        setBackground(new Color(20,27,140)); //Blue screen
        setPreferredSize(new Dimension(width, height));
        setBounds(0, 0, width, height);
    }
    
    /**
     * Paints a unique pixel, given by its coordenates
     * @param isActivated
     * @param x
     * @param y
     */
    private void paintPixel(boolean isActivated, int x, int y) {
        Color color = isActivated ? Color.WHITE : Color.BLACK;

        g.setColor(color);
        g.fillRect(x * scale, y * scale, scale, scale);
    }
    
    /**
     * Paints the full screen.
     */
    private void paintFullScreen(){
        for(int y=0; y<mem.pixels[0].length;y++){
            for(int x=0; x<mem.pixels.length; x++){
                paintPixel(mem.pixels[x][y],x,y);
            }
        }
    }
        
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.g = g;
        paintFullScreen();
    }
}
