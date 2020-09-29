package machine_model;

/**
 * Class that manages al registers.
 * @author david
 */
public class RegisterBank {
    byte V[];       //16 general registers                   -> 8-bit  (1 byte) per register : V0-VF
    short I;        //I register to store memory addresses   -> 16-bit (2 bytes)             : I
    short PC;       //PC register, current executing address -> 16-bit (2 bytes)             : PC
    byte SP;        //SP register, points top of stack       -> 8-bit  (1 byte)              : SP
    byte DT;        //DT delay timer for timers and sound    -> 8-bit  (1 byte)              : DT
    byte ST;        //ST sound timer for timers and sound    -> 8-bit  (1 byte)              : ST
    
    /**
     * Contructor - Initializes the register bank.
     */
    public RegisterBank(){
        this.resetRegisterBank();
    }
    
    
    public void resetRegisterBank(){
        V = new byte[16];
        I = 0x0000;
        PC = 0x200; //PC starts at 512 = 0x200
        SP = 0x00;
        DT = 0x00;
        ST = 0x00; 
    }
    
    /**
     * Returns a string representation of the register bank.
     * @return 
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("V={\n");
        for(int i=0; i<16; i++){
            if(i >= 10)
                sb.append(String.format("  V[%d] = 0x%02x\n",i,this.V[i]));
            else
                sb.append(String.format("  V[%d]  = 0x%02x\n",i,this.V[i]));
        }
        sb.append("}\n\n");
        
        sb.append(String.format("I  = 0x%04x\n",this.I));
        sb.append(String.format("PC = 0x%04x\n",this.PC));
        sb.append(String.format("SP = 0x%02x\n",this.SP));
        sb.append(String.format("DT = 0x%02x\n",this.DT));
        sb.append(String.format("ST = 0x%02x\n",this.ST)); 
        
        return sb.toString();
    }
    
    
    
    
    
}
