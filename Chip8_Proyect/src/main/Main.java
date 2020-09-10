package main;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import machine.Chip8;

/**
 *
 * @author David
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String rom = "TICTAC";       
        
        try {
            Chip8 chip8 = new Chip8();
            chip8.loadRom(rom.toUpperCase());
            
            chip8.startEmulationLoop();
            
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
