package machine;

import inputOutput.Screen;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javax.swing.JFrame;


/**
 *
 * @author David
 */
public class Chip8 {
    private final int MEMSIZE = 4096;
    private final Memory memory;
    
    private final RegisterBank registerBank;
    private final CPU cpu;
    
    private final int SCALE = 15;
    private final int SCREEN_WIDTH =  64 * SCALE;
    private final int SCREEN_HEIGHT = 32 * SCALE;
    private Screen screen;
        
    
    public Chip8(){        
        this.memory = new Memory(MEMSIZE);
        this.registerBank = new RegisterBank();
        this.cpu = new CPU(this.memory, this.registerBank);    
        
        createGUI();
    }
  
    /**
     * Loads a binary rom 
     * @param rom To load
     * @throws java.io.IOException
     */
    public void loadRom(String rom) throws IOException{
        File f = new File("ROMS/"+rom);
        byte[] loadedRom = Files.readAllBytes(f.toPath());
        
        //Starts to load in 0x200 (where PC starts)
        short addressToLoad = 0x200; 
        for(byte b: loadedRom){
            this.memory.set(addressToLoad, b);
            addressToLoad += 0x1;
        }
    }     
    
    private void createGUI(){
        JFrame frame = new JFrame("Chip-8");
        
        // Set frame location to the middle of the screen
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(d.width/2 - SCREEN_WIDTH/2, d.height/2 - SCREEN_HEIGHT/2);
        
        // New Screen pannel instance 
        screen = new Screen(memory, SCALE, SCREEN_WIDTH, SCREEN_HEIGHT);          
        frame.setContentPane(screen);
        frame.pack();
        
        // Other frame options
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        
        System.out.println("[CHIP-8] Chip-8 GUI correctly initialized.");
    }
   
}
