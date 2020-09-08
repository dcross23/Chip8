package machine;

/**
 *
 * @author David
 */
public class Memory {
    /**
     * Memory. 
     */
    private byte[] mem;
    
    /**
     * Stack. 
     */
    private short[] stack;
    
    /**
     * Screen pixels : true = white, false = black.
     */
    public boolean pixels[][];
    
    /**
     * Flag to control when drawing the screen.
     */
    public boolean drawScreen;
    
    /**
     * All sprites from 0 to F.
     */
    private static byte sprites[][] = {
        new byte[]{(byte)0xF0,(byte)0x90,(byte)0x90,(byte)0x90,(byte)0xF0},    //SPRITE_0 
        new byte[]{(byte)0x20,(byte)0x60,(byte)0x20,(byte)0x20,(byte)0x70},    //SPRITE_1
        new byte[]{(byte)0xF0,(byte)0x10,(byte)0xF0,(byte)0x80,(byte)0xF0},    //SPRITE_2
        new byte[]{(byte)0xF0,(byte)0x10,(byte)0xF0,(byte)0x10,(byte)0xF0},    //SPRITE_3
        new byte[]{(byte)0x90,(byte)0x90,(byte)0xF0,(byte)0x10,(byte)0x10},    //SPRITE_4
        new byte[]{(byte)0xF0,(byte)0x80,(byte)0xF0,(byte)0x10,(byte)0xF0},    //SPRITE_5
        new byte[]{(byte)0xF0,(byte)0x80,(byte)0xF0,(byte)0x90,(byte)0xF0},    //SPRITE_6
        new byte[]{(byte)0xF0,(byte)0x10,(byte)0x20,(byte)0x40,(byte)0x40},    //SPRITE_7
        new byte[]{(byte)0xF0,(byte)0x90,(byte)0xF0,(byte)0x90,(byte)0xF0},    //SPRITE_8
        new byte[]{(byte)0xF0,(byte)0x90,(byte)0xF0,(byte)0x10,(byte)0xF0},    //SPRITE_9
        new byte[]{(byte)0xF0,(byte)0x90,(byte)0xF0,(byte)0x90,(byte)0x90},    //SPRITE_A
        new byte[]{(byte)0xE0,(byte)0x90,(byte)0xE0,(byte)0x90,(byte)0xE0},    //SPRITE_B
        new byte[]{(byte)0xF0,(byte)0x80,(byte)0x80,(byte)0x80,(byte)0xF0},    //SPRITE_C
        new byte[]{(byte)0xE0,(byte)0x90,(byte)0x90,(byte)0x90,(byte)0xE0},    //SPRITE_D
        new byte[]{(byte)0xF0,(byte)0x80,(byte)0xF0,(byte)0x80,(byte)0xF0},    //SPRITE_E
        new byte[]{(byte)0xF0,(byte)0x80,(byte)0xF0,(byte)0x80,(byte)0x80},    //SPRITE_F        
    };
    
    
    /**
     * Constructor - Initializes the Memory with a specific size.
     * @param size 
     */
    public Memory(int size){
        this.mem = new byte[size];
        this.stack = new short[16];
        this.pixels = new boolean[64][32];
        this.drawScreen = false; 
    }
    
    /**
     * Returns the head of the stack.
     * @param head
     * @return 
     */
    public short getStackHead(short head){
        return stack[head];
    }
    
    /**
     * Adds a new value to the stack.
     * @param newHead
     * @param value 
     */
    public void setStackHead(short newHead, short value){
        stack[newHead] = value;
    }
    
    /**
     * Sets the content "content" in the address "address" if this last one exists
     * @param address to save the content
     * @param content to be saved
     */
    public void set(short address, byte content){
        if(address > 0x0FFF){
            System.err.printf("[ERROR] Cannot set content in address 0x%04x\n",address);
            System.err.printf("[ERROR] Address out of memory address range: 0x%04x\n",address);
            
        }else{
            this.mem[address] = content;
        }
    }
      
    /**
     * Gets the content of the address "address" if it exists and returns it  
     * @param address to locate the content
     * @return the content saved at "address"
     */
    public byte get(short address){
        if(address > 0x0FFF){
            System.err.printf("[ERROR] Cannot get content of addres 0x%04x\n",address);
            System.err.printf("[ERROR] Address out of memory address range: 0x%04x\n",address);
            return 0x00;
        }else{
            return this.mem[address];
        }
    }
    
 
}
