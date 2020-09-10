package machine;

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
        V = new byte[16];
        I = 0x0000;
        PC = 0x200; //PC starts at 512 = 0x200
        SP = 0x00;
        DT = 0x00;
        ST = 0x00;        
    }
    
    /**
     * Prints the register bank by standar output (stdout).
     */
    public void printRegisterBank(){
        System.out.println("REGISTER BANK");
        System.out.println("V={");
        for(int i=0; i<16; i++){
            if(i >= 10)
                System.out.printf("  V[%d] = 0x%02x\n",i,this.V[i]);
            else
                System.out.printf("  V[%d]  = 0x%02x\n",i,this.V[i]);
        }
        System.out.println("}\n");
        
        System.out.printf("I  = 0x%04x\n",this.I);
        System.out.printf("PC = 0x%04x\n",this.PC);
        System.out.printf("SP = 0x%02x\n",this.SP);
        System.out.printf("DT = 0x%02x\n",this.DT);
        System.out.printf("ST = 0x%02x\n",this.ST);        
    }
    
    
}
