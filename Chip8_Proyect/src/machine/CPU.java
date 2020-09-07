package machine;

/**
 *
 * @author David
 */
public class CPU {
    RegisterBank regBank;
    Memory mem;
    
    short opcode;
    
    public CPU(Memory memory, RegisterBank registerBank){
        this.regBank = registerBank;
        this.mem = memory;
    }
    
    
    /**
     * Gets the next opcode from memory using pc. 
     */
    public void getNextOpcode(){
        short firstByte = (short) (mem.get(regBank.PC) << 8);
        short secondByte = mem.get((short)(regBank.PC + 0x0001));
        
        opcode = (short) ((firstByte & 0xFF00) | (secondByte & 0x00FF)); 
    }
    
    /**
     * Increments PC to get next opcode.
     */
    public void incrementPC(){
        regBank.PC += 2;
    }
    
    
    /**
     * Decodes the opcode readed.
     */
    public void decode(){
        short nnn = extractNNN(opcode);
        byte   kk = this.extractKK(opcode);
        byte    x = this.extractX(opcode);
        byte    y = this.extractY(opcode);
        byte    n = this.extractN(opcode);
        byte    f = this.extractF(opcode); 
        
        switch(f){
            case 0x0:
                switch(nnn){
                    case 0x0E0:
                        /** 
                         * CLS -> clears the display (00E0). 
                         */
                      break;
                        
                    case 0x0EE:
                        /** 
                         * RET -> Return from a subroutine (00EE).
                         * The interpreter sets the program counter to the 
                         * address at the top of the stack, then subtracts 1 
                         * from the stack pointer 
                         */
                        if(regBank.SP > 0){
                            regBank.PC = (short) (mem.getStackHead(regBank.SP) & 0xFFF);
                            regBank.SP--;
                        }
                      break;
                }
              break;
                
            case 0x1:
                /**
                 * JP nnn -> Jump to location nnn (1nnn). 
                 * The interpreter sets the program counter to nnn.
                 */
                regBank.PC = nnn;
              break;
                
                
            case 0x2:
                /**
                 * CALL addr -> Call subroutine at nnn (2nnn). 
                 * The interpreter increments the stack pointer, then puts the 
                 * current PC on the top of the stack. The PC is then set to nnn.
                 */
                if(regBank.SP <= 0xF){
                    regBank.SP += 0x1;
                    mem.setStackHead(regBank.SP, regBank.PC);
                }
                regBank.PC = nnn;
              break;

                
            case 0x3:
                /**
                 * SE Vx,byte -> Skip next instruction if Vx = kk (3xkk).
                 * The interpreter compares register Vx to kk, and if they are 
                 * equal, increments the program counter by 2.
                 */
                if(regBank.V[x] == kk){
                    incrementPC();
                }
              break;

            case 0x4:
                /**
                 * SNE Vx,byte -> Skip next instruction if Vx != kk (4xkk).
                 * The interpreter compares register Vx to kk, and if they are 
                 * not equal, increments the program counter by 2.
                 */
                if(regBank.V[x] != kk){
                    incrementPC();
                }
                break;

            case 0x5:
                /**
                 * SE Vx, Vy -> Skip next instruction if Vx = Vy (5xy0).
                 * The interpreter compares register Vx to register Vy, and if 
                 * they are equal, increments the program counter by 2.
                 */
                if(regBank.V[x] == regBank.V[y]){
                    incrementPC();
                }
              break;

            case 0x6:
                /**
                 * LD Vx, byte -> Set Vx = kk (6xkk).
                 * The interpreter puts the value kk into register Vx.              * 
                 */
                regBank.V[x] = kk;
              break;

            case 0x7:
                /**
                 * ADD Vx, byte -> Set Vx = Vx + kk (7xkk).
                 * Adds the value kk to the value of register Vx, 
                 * then stores the result in Vx.
                 */
                //Dont need mask, Java does this automatically if case there
                //  is sum carry
                regBank.V[x] += kk;
                break;

            case 0x8:
                switch(n){
                    case 0x0:
                        /**
                         * LD Vx, Vy -> Set Vx = Vy (8xy0). 
                         * Stores the value of register Vy in register Vx.
                         */
                        regBank.V[x] = regBank.V[y];
                        break;
                        
                    case 0x1:
                        /**
                         * OR Vx, Vy -> Set Vx = Vx OR Vy (8xy1). 
                         * Performs a bitwise OR on the values of Vx and Vy, then 
                         * stores the result in Vx. A bitwise OR compares the 
                         * corresponding bits from two values, and if either bit 
                         * is 1, then the same bit in the result is also 1. 
                         * Otherwise, it is 0.
                         */
                        regBank.V[x] = (byte) (regBank.V[x] | regBank.V[y]);
                        break;
                        
                    case 0x2:
                        /**
                         * AND Vx, Vy -> Set Vx = Vx AND Vy (8xy2). 
                         * Performs a bitwise AND on the values of Vx and Vy, then 
                         * stores the result in Vx. A bitwise AND compares the 
                         * corresponding bits from two values, and if both bits 
                         * are 1, then the same bit in the result is also 1. 
                         * Otherwise, it is 0.
                         */
                        regBank.V[x] = (byte) (regBank.V[x] & regBank.V[y]);
                        break;
                        
                    case 0x3:
                        /**
                         * XOR Vx, Vy -> Set Vx = Vx XOR Vy (8xy3). 
                         * Performs a bitwise exclusive OR on the values of Vx and Vy,
                         * then stores the result in Vx. An exclusive OR compares the 
                         * corresponding bits from two values, and if the bits are 
                         * not both the same, then the corresponding bit in the 
                         * result is set to 1. Otherwise, it is 0.                         * 
                         */
                        regBank.V[x] = (byte) (regBank.V[x] ^ regBank.V[y]);
                        break;

                    case 0x4:
                        /**
                         * ADD Vx, Vy -> Set Vx = Vx + Vy, set VF = carry. 
                         * The values of Vx and Vy are added together. If the 
                         * result is greater than 8 bits (i.e., ¿ 255,) VF is 
                         * set to 1, otherwise 0. Only the lowest 8 bits of the 
                         * result are kept, and stored in Vx
                         */
                        break;

                    case 0x5:
                        /**
                         * SUB Vx, Vy -> Set Vx = Vx - Vy, set VF = NOT borrow (8xy5). 
                         * If Vx ¿ Vy, then VF is set to 1, otherwise 0. Then Vy 
                         * is subtracted from Vx, and the results stored in Vx.
                         */
                        break;

                    case 0x6:
                        /**
                         * SHR Vx {, Vy} -> Set Vx = Vx SHR 1 (8xy6). 
                         * If the least-significant bit of Vx is 1, then VF is set 
                         * to 1, otherwise 0. Then Vx is divided by 2.
                         */
                        break;

                    case 0x7:
                        /**
                         * SUBN Vx, Vy -> Set Vx = Vy - Vx, set VF = NOT borrow (8xy7). 
                         * If Vy ¿ Vx, then VF is set to 1, otherwise 0. Then Vx is 
                         * subtracted from Vy, and the results stored in Vx.
                         */
                        break;

                    case 0xE:
                        /**
                         * SHL Vx {, Vy} -> Set Vx = Vx SHL 1 (8xyE). 
                         * If the most-significant bit of Vx is 1, then VF is set 
                         * to 1, otherwise to 0. Then Vx is multiplied by 2.
                         */
                        break;
                }
                break;

            case 0x9:
                /**
                 * SNE Vx, Vy -> Skip next instruction if Vx != Vy (9xy0). 
                 * The values of Vx and Vy are compared, and if they are not equal, 
                 * the program counter is increased by 2.
                 */
                
                //if(n == 0)
                break;
            
            case 0xA:
                /**
                 * LD I, addr -> Set I = nnn (Annn). 
                 * The value of register I is set to nnn
                 */
                break;

            case 0xB:
                /**
                 * JP V0, addr -> Jump to location nnn + V0 (Bnnn). 
                 * The program counter is set to nnn plus the value of V0.
                 */
                break;

            case 0xC:
                /**
                 * RND Vx, byte -> Set Vx = random byte AND kk (Cxkk). 
                 * The interpreter generates a random number from 0 to 255, 
                 * which is then ANDed with the value kk. The results are stored 
                 * in Vx. See instruction 8xy2 for more information on AND.

                 */
                break;
                
            case 0xD:
                /**
                 * DRW Vx, Vy, nibble -> Display n-byte sprite starting at memory 
                 *                   location I at (Vx, Vy), set VF = collision (Dxyn). 
                 * The interpreter reads n bytes from memory, starting at the address 
                 * stored in I. These bytes are then displayed as sprites on screen at 
                 * coordinates (Vx, Vy). 
                 * Sprites are XOR’d onto the existing screen. If this causes any 
                 * pixels to be erased, VF is set to 1, otherwise it is set to 0. 
                 * If the sprite is positioned so part of it is outside the coordinates 
                 * of the display, it wraps around to the opposite side of the screen.
                 */
                break;

                
            case 0xE:
                switch( (short) (kk & 0x00FF) ){
                    case 0x009E:
                        /**
                         *  SKP Vx -> Skip next instruction if key with the value
                         *            of Vx is pressed (Ex9E). 
                         * Checks the keyboard, and if the key corresponding to the value 
                         * of Vx is currently in the down position, PC is increased by 2.
                         */
                        break;
                        
                    case 0x00A1:
                        /**
                         * SKNP Vx -> Skip next instruction if key with the value 
                         *            of Vx is not pressed (ExA1). 
                         * Checks the keyboard, and if the key corresponding to the value 
                         * of Vx is currently in the up position, PC is increased by 2.
                         */
                        break;
                }
                break;

                
            case 0xF:
                switch( (short) (kk & 0x00FF) ){
                    case 0x0007:
                        /**
                         * LD Vx, DT -> Set Vx = delay timer value (Fx07). 
                         * The value of DT is placed into Vx.
                         */
                        break;
                        
                    case 0x000A:
                        /**
                         * LD Vx, K -> Wait for a key press, store the value 
                         *             of the key in Vx (Fx0A). 
                         * All execution stops until a key is pressed, then the 
                         * value of that key is stored in Vx.
                         */
                        break;
                        
                    case 0x0015:
                        /**
                         * LD DT, Vx -> Set delay timer = Vx (Fx15).  
                         * Delay Timer is set equal to the value of Vx.
                         */
                        break;
                        
                    case 0x0018:
                        /**
                         * LD ST, Vx -> Set sound timer = Vx (Fx18). 
                         * Sound Timer is set equal to the value of Vx.
                         */
                        break;
                        
                    case 0x001E:
                        /**
                         * ADD I, Vx -> Set I = I + Vx (Fx1E). 
                         * The values of I and Vx are added, and the results 
                         * are stored in I
                         */
                        break;
                        
                    case 0x0029:
                        /**
                         * LD F, Vx -> Set I = location of sprite for digit Vx. 
                         * The value of I is set to the location for the hexadecimal 
                         * sprite corresponding to the value of Vx. 
                         */
                        break;
                    
                    case 0x0033:
                        /**
                         * LD B, Vx -> Store BCD representation of Vx in memory 
                         *             locations I, I+1, and I+ (Fx33). 
                         * The interpreter takes the decimal value of Vx, and 
                         * places the hundreds digit in memory at location in I, 
                         * the tens digit at location I+1, and the ones digit at 
                         * location I+2.
                         */
                        break;
                        
                    case 0x0055:
                        /**
                         * LD [I], Vx -> Stores V0 to VX in memory starting at 
                         *               address I (Fx55).
                         * I is then set to I + x + 1.
                         */
                        break;
                        
                    case 0x0065:
                        /**
                         * LD Vx, [I] -> Fills V0 to VX with values from memory 
                         *               starting at address I (Fx65). 
                         * I is then set to I + x + 1.
                         */
                        break;   
                }
            break;         
        }
    }
    
    
    /**
     * Prints the opcode readed.
     */
    public void printOpcode(){
        short nnn = extractNNN(opcode);
        byte   kk = this.extractKK(opcode);
        byte    x = this.extractX(opcode);
        byte    y = this.extractY(opcode);
        byte    n = this.extractN(opcode);
        byte    f = this.extractF(opcode); 
        
        switch(f){
            case 0x0:
                switch(nnn){
                    case 0x0E0:
                        /** 
                         * CLS -> clears the display (00E0). 
                         */
                        System.out.println("CLS");
                        break;
                        
                    case 0x0EE:
                        /** 
                         * RET -> Return from a subroutine (00EE).
                         * The interpreter sets the program counter to the 
                         * address at the top of the stack, then subtracts 1 
                         * from the stack pointer 
                         */
                        System.out.println("RET");
                        break;
                }
                break;
                
            case 0x1:
                /**
                 * JP nnn -> Jump to location nnn (1nnn). 
                 * The interpreter sets the program counter to nnn.
                 */
                System.out.printf("JP %x\n",nnn);
                break;
                
            case 0x2:
                /**
                 * CALL addr -> Call subroutine at nnn (2nnn). 
                 * The interpreter increments the stack pointer, then puts the 
                 * current PC on the top of the stack. The PC is then set to nnn.
                 */
                System.out.printf("CALL %x\n",nnn);
                break;

            case 0x3:
                /**
                 * SE Vx,byte -> Skip next instruction if Vx = kk (3xkk).
                 * The interpreter compares register Vx to kk, and if they are 
                 * equal, increments the program counter by 2.
                 */
                System.out.printf("SE %x, %x\n",x,kk);
                break;

            case 0x4:
                /**
                 * SNE Vx,byte -> Skip next instruction if Vx != kk (4xkk).
                 * The interpreter compares register Vx to kk, and if they are 
                 * not equal, increments the program counter by 2.
                 */
                System.out.printf("SNE %x, %x\n",x,kk); 
                break;

            case 0x5:
                /**
                 * SE Vx, Vy -> Skip next instruction if Vx = Vy (5xy0).
                 * The interpreter compares register Vx to register Vy, and if 
                 * they are equal, increments the program counter by 2.
                 */
                System.out.printf("SE %x, %x\n",x,y);
                break;

            case 0x6:
                /**
                 * LD Vx, byte -> Set Vx = kk (6xkk).
                 * The interpreter puts the value kk into register Vx.              * 
                 */
                System.out.printf("LD %x, %x\n",x,kk);
                break;

            case 0x7:
                /**
                 * ADD Vx, byte -> Set Vx = Vx + kk (7xkk).
                 * Adds the value kk to the value of register Vx, 
                 * then stores the result in Vx.
                 */
                System.out.printf("ADD %x, %x\n",x,kk);
                break;

            case 0x8:
                switch(n){
                    case 0x0:
                        /**
                         * LD Vx, Vy -> Set Vx = Vy (8xy0). 
                         * Stores the value of register Vy in register Vx.
                         */
                        System.out.printf("LD %x, %x\n",x,y);
                        break;
                        
                    case 0x1:
                        /**
                         * OR Vx, Vy -> Set Vx = Vx OR Vy (8xy1). 
                         * Performs a bitwise OR on the values of Vx and Vy, then 
                         * stores the result in Vx. A bitwise OR compares the 
                         * corresponding bits from two values, and if either bit 
                         * is 1, then the same bit in the result is also 1. 
                         * Otherwise, it is 0.
                         */
                        System.out.printf("OR %x, %x\n",x,y);
                        break;
                        
                    case 0x2:
                        /**
                         * AND Vx, Vy -> Set Vx = Vx AND Vy (8xy2). 
                         * Performs a bitwise AND on the values of Vx and Vy, then 
                         * stores the result in Vx. A bitwise AND compares the 
                         * corresponding bits from two values, and if both bits 
                         * are 1, then the same bit in the result is also 1. 
                         * Otherwise, it is 0.
                         */
                        System.out.printf("AND %x, %x\n",x,y);
                        break;
                        
                    case 0x3:
                        /**
                         * XOR Vx, Vy -> Set Vx = Vx XOR Vy (8xy3). 
                         * Performs a bitwise exclusive OR on the values of Vx and Vy,
                         * then stores the result in Vx. An exclusive OR compares the 
                         * corresponding bits from two values, and if the bits are 
                         * not both the same, then the corresponding bit in the 
                         * result is set to 1. Otherwise, it is 0.                         * 
                         */
                        System.out.printf("XOR %x, %x\n",x,y);
                        break;

                    case 0x4:
                        /**
                         * ADD Vx, Vy -> Set Vx = Vx + Vy, set VF = carry. 
                         * The values of Vx and Vy are added together. If the 
                         * result is greater than 8 bits (i.e., ¿ 255,) VF is 
                         * set to 1, otherwise 0. Only the lowest 8 bits of the 
                         * result are kept, and stored in Vx
                         */
                        System.out.printf("ADD %x, %x\n",x,y);
                        break;

                    case 0x5:
                        /**
                         * SUB Vx, Vy -> Set Vx = Vx - Vy, set VF = NOT borrow (8xy5). 
                         * If Vx ¿ Vy, then VF is set to 1, otherwise 0. Then Vy 
                         * is subtracted from Vx, and the results stored in Vx.
                         */
                        System.out.printf("SUB %x, %x\n",x,y);
                        break;

                    case 0x6:
                        /**
                         * SHR Vx {, Vy} -> Set Vx = Vx SHR 1 (8xy6). 
                         * If the least-significant bit of Vx is 1, then VF is set 
                         * to 1, otherwise 0. Then Vx is divided by 2.
                         */
                        System.out.printf("SHR %x\n",x);
                        break;

                    case 0x7:
                        /**
                         * SUBN Vx, Vy -> Set Vx = Vy - Vx, set VF = NOT borrow (8xy7). 
                         * If Vy ¿ Vx, then VF is set to 1, otherwise 0. Then Vx is 
                         * subtracted from Vy, and the results stored in Vx.
                         */
                        System.out.printf("SUBN %x, %x\n",x,y);
                        break;

                    case 0xE:
                        /**
                         * SHL Vx {, Vy} -> Set Vx = Vx SHL 1 (8xyE). 
                         * If the most-significant bit of Vx is 1, then VF is set 
                         * to 1, otherwise to 0. Then Vx is multiplied by 2.
                         */
                        System.out.printf("SHL %x\n",x);
                        break;
                }
                break;

            case 0x9:
                /**
                 * SNE Vx, Vy -> Skip next instruction if Vx != Vy (9xy0). 
                 * The values of Vx and Vy are compared, and if they are not equal, 
                 * the program counter is increased by 2.
                 */
                
                if(n == 0){
                    System.out.printf("SNE %x,%x\n",x,y);
                }
                break;
            
            case 0xA:
                /**
                 * LD I, addr -> Set I = nnn (Annn). 
                 * The value of register I is set to nnn
                 */
                System.out.printf("LD I, %x\n",nnn);
                break;

            case 0xB:
                /**
                 * JP V0, addr -> Jump to location nnn + V0 (Bnnn). 
                 * The program counter is set to nnn plus the value of V0.
                 */
                System.out.printf("JP V0, %x\n",nnn);
                break;

            case 0xC:
                /**
                 * RND Vx, byte -> Set Vx = random byte AND kk (Cxkk). 
                 * The interpreter generates a random number from 0 to 255, 
                 * which is then ANDed with the value kk. The results are stored 
                 * in Vx. See instruction 8xy2 for more information on AND.
                 */
                System.out.printf("RND %x, %x\n",x,kk);
                break;
                
            case 0xD:
                /**
                 * DRW Vx, Vy, nibble -> Display n-byte sprite starting at memory 
                 *                   location I at (Vx, Vy), set VF = collision (Dxyn). 
                 * The interpreter reads n bytes from memory, starting at the address 
                 * stored in I. These bytes are then displayed as sprites on screen at 
                 * coordinates (Vx, Vy). 
                 * Sprites are XOR’d onto the existing screen. If this causes any 
                 * pixels to be erased, VF is set to 1, otherwise it is set to 0. 
                 * If the sprite is positioned so part of it is outside the coordinates 
                 * of the display, it wraps around to the opposite side of the screen.
                 */
                System.out.printf("DRW %x,%x,%x\n",x,y,n);
                break;

                
            case 0xE:
                switch( (short) (kk & 0x00FF) ){
                    case 0x009E:
                        /**
                         *  SKP Vx -> Skip next instruction if key with the value
                         *            of Vx is pressed (Ex9E). 
                         * Checks the keyboard, and if the key corresponding to the value 
                         * of Vx is currently in the down position, PC is increased by 2.
                         */
                        System.out.printf("SKP %x\n",x);
                        break;
                        
                    case 0x00A1:
                        /**
                         * SKNP Vx -> Skip next instruction if key with the value 
                         *            of Vx is not pressed (ExA1). 
                         * Checks the keyboard, and if the key corresponding to the value 
                         * of Vx is currently in the up position, PC is increased by 2.
                         */
                        System.out.printf("SKNP %x\n",x);
                        break;
                }
                break;

                
            case 0xF:
                switch( (short) (kk & 0x00FF) ){
                    case 0x0007:
                        /**
                         * LD Vx, DT -> Set Vx = delay timer value (Fx07). 
                         * The value of DT is placed into Vx.
                         */
                        System.out.printf("LD %x, DT\n",x);
                        break;
                        
                    case 0x000A:
                        /**
                         * LD Vx, K -> Wait for a key press, store the value 
                         *             of the key in Vx (Fx0A). 
                         * All execution stops until a key is pressed, then the 
                         * value of that key is stored in Vx.
                         */
                        System.out.printf("LD %x, K\n",x);
                        break;
                        
                    case 0x0015:
                        /**
                         * LD DT, Vx -> Set delay timer = Vx (Fx15).  
                         * Delay Timer is set equal to the value of Vx.
                         */
                        System.out.printf("LD DT, 5x\n",x);
                        break;
                        
                    case 0x0018:
                        /**
                         * LD ST, Vx -> Set sound timer = Vx (Fx18). 
                         * Sound Timer is set equal to the value of Vx.
                         */
                        System.out.printf("LD ST, %x\n",x);
                        break;
                        
                    case 0x001E:
                        /**
                         * ADD I, Vx -> Set I = I + Vx (Fx1E). 
                         * The values of I and Vx are added, and the results 
                         * are stored in I
                         */
                        System.out.printf("ADD I, %x\n",x);
                        break;
                        
                    case 0x0029:
                        /**
                         * LD F, Vx -> Set I = location of sprite for digit Vx. 
                         * The value of I is set to the location for the hexadecimal 
                         * sprite corresponding to the value of Vx. 
                         */
                        System.out.printf("LD F, %x\n",x);
                        break;
                    
                    case 0x0033:
                        /**
                         * LD B, Vx -> Store BCD representation of Vx in memory 
                         *             locations I, I+1, and I+ (Fx33). 
                         * The interpreter takes the decimal value of Vx, and 
                         * places the hundreds digit in memory at location in I, 
                         * the tens digit at location I+1, and the ones digit at 
                         * location I+2.
                         */
                        System.out.printf("LD B, %x\n",x);
                        break;
                        
                    case 0x0055:
                        /**
                         * LD [I], Vx -> Stores V0 to VX in memory starting at 
                         *               address I (Fx55).
                         * I is then set to I + x + 1.
                         */
                        System.out.printf("LD [I], %x\n",x);
                        break;
                        
                    case 0x0065:
                        /**
                         * LD Vx, [I] -> Fills V0 to VX with values from memory 
                         *               starting at address I (Fx65). 
                         * I is then set to I + x + 1.
                         */
                        System.out.printf("LD %x, [I]x\n",x);
                        break;   
                }
            break;         
        }
    }
    
    
   
    /**
     * Extract nnn from opcode -> oNNN
     * @param opc opcode from where extract nnn
     * @return nnn part of the opcode
     */
    private short extractNNN(short opc){
        return (short) (opc & 0x0FFF);
    }
    
    /**
     * Extract kk from opcode -> ooKK
     * @param opc opcode from where extract k
     * @return kk part of the opcode
     */
    private byte extractKK(short opc){
        return (byte) (opc & 0x00FF);
    }
    
    /**
     * Extract f from opcode -> Fooo
     * @param opc opcode from where extract f
     * @return f part of the opcode
     */
    private byte extractF(short opc){
        return (byte) ((opc & 0xF000) >> 12);
    }
    
    /**
     * Extract x from opcode -> oXoo
     * @param opc opcode from where extract x
     * @return x part of the opcode
     */
    private byte extractX(short opc){
        return (byte) ((opc & 0x0F00) >> 8);
    }
    
    /**
     * Extract y from opcode -> ooYo
     * @param opc opcode from where extract y
     * @return y part of the opcode
     */
    private byte extractY(short opc){
        return (byte) ((opc & 0x00F0) >> 4);
    }
    
    /**
     * Extract n from opcode -> oooN
     * @param opc opcode from where extract n
     * @return n part of the opcode
     */
    private byte extractN(short opc){
       return (byte) (opc & 0x000F);
    }
}
