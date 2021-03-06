package machine_model.inputOutput;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import machine_model.Memory;

/**
 *
 * @author david
 */
public class Screen extends JPanel{
    /**
     * Screen config (width, height and scale).
     */
    int scale; 
    int width; 
    int height;
    
    /**
     * Graphics to paint in the screen.
     */
    private Graphics g;
    
    /**
     * Chip8 memory to obtain the pixels.
     */
    private final Memory mem;
    
    /**
     * Constructor - creates a new Screen.
     * @param mem - Memory where pixels are saved
     * @param scale
     * @param width
     * @param height 
     */
    public Screen(Memory mem, int scale, int width, int height){
        super(null);
        this.mem = mem;
        this.scale = scale;
        this.width = width;
        this.height = height;
        
        setPreferredSize(new Dimension(width, height));
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
