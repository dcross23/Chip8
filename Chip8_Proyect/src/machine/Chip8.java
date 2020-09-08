package machine;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 *
 * @author David
 */
public class Chip8 {
    static final int MEMSIZE = 4096;
    Memory memory;

    RegisterBank registerBank;
    CPU cpu;
    
    public Chip8(){
        this.memory = new Memory(MEMSIZE);
        this.registerBank = new RegisterBank();
        this.cpu = new CPU(this.memory, this.registerBank);
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
}
