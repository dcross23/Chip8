package machine_model;

/**
 * Class that manages the memory.
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
    public final short spritesStartAddress = 0x0000;
    private static final byte sprites[][] = {
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
        this.resetMemory(size);
    }
    
    public void resetMemory(int size){
        this.mem = new byte[size];
        this.stack = new short[16];
        this.pixels = new boolean[64][32];
        this.drawScreen = false; 
        
        loadSpritesOnMemory();
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
    
    /**
     * Prints the memory content and addresses. 
     */
    public void printMemory(){
        for(int i=0; i<this.mem.length; i++){
            System.out.printf("%02x -> 0x%04x\n",this.mem[i],i);
        }
    }
    
    
    /**
     * Loads the default sprites in memory. 
     */
    private void loadSpritesOnMemory(){
        for(int i=0; i<sprites[0x0].length; i++){
            set((short) (spritesStartAddress + i),sprites[0x0][i]);
        }
        for(int i=0; i<sprites[0x1].length; i++){
            set((short) (spritesStartAddress +5 + i),sprites[0x1][i]);
        }
        for(int i=0; i<sprites[0x2].length; i++){
            set((short) (spritesStartAddress +10 + i),sprites[0x2][i]);
        }
        for(int i=0; i<sprites[0x3].length; i++){
            set((short) (spritesStartAddress +15 + i),sprites[0x3][i]);
        }
        for(int i=0; i<sprites[0x4].length; i++){
            set((short) (spritesStartAddress +20 + i),sprites[0x4][i]);
        }
        for(int i=0; i<sprites[0x5].length; i++){
            set((short) (spritesStartAddress +25 + i),sprites[0x5][i]);
        }
        for(int i=0; i<sprites[0x6].length; i++){
            set((short) (spritesStartAddress +30 + i),sprites[0x6][i]);
        }
        for(int i=0; i<sprites[0x7].length; i++){
            set((short) (spritesStartAddress +35 + i),sprites[0x7][i]);
        }
        for(int i=0; i<sprites[0x8].length; i++){
            set((short) (spritesStartAddress +40 + i),sprites[0x8][i]);
        }
        for(int i=0; i<sprites[0x9].length; i++){
            set((short) (spritesStartAddress +45 + i),sprites[0x9][i]);
        }
        for(int i=0; i<sprites[0xA].length; i++){
            set((short) (spritesStartAddress +50 + i),sprites[0xA][i]);
        }
        for(int i=0; i<sprites[0xB].length; i++){
            set((short) (spritesStartAddress +55 + i),sprites[0xB][i]);
        }
        for(int i=0; i<sprites[0xC].length; i++){
            set((short) (spritesStartAddress +60 + i),sprites[0xC][i]);
        }
        for(int i=0; i<sprites[0xD].length; i++){
            set((short) (spritesStartAddress +65 + i),sprites[0xD][i]);
        }
        for(int i=0; i<sprites[0xE].length; i++){
            set((short) (spritesStartAddress +70 + i),sprites[0xE][i]);
        }
        for(int i=0; i<sprites[0xF].length; i++){
            set((short) (spritesStartAddress +75 + i),sprites[0xF][i]);
        }
        
    }
}
