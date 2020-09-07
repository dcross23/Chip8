package machine;

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
    
    
    
    
}
