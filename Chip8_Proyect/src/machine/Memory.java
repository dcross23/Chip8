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
     * Constructor - Initializes the Memory with a specific size.
     * @param size 
     */
    public Memory(int size){
        this.mem = new byte[size];
        this.stack = new short[16];
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
