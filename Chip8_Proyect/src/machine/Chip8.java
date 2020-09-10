package machine;

import inputOutput.KeyBoard;
import inputOutput.Screen;
import inputOutput.Sound;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javax.swing.JFrame;


/**
 * Class that represents Chip8.
 * @author David
 */
public class Chip8 {      
    /**
     * Memory and memory size.
     */
    private final int MEMSIZE = 4096;
    private final Memory memory;
    
    /**
     * Cpu and Register Bank.
     */
    private final RegisterBank registerBank;
    private final CPU cpu;
    
    /**
     * Screen and screen configuration.
     */
    private final int SCALE = 15;
    private final int SCREEN_WIDTH =  64 * SCALE;
    private final int SCREEN_HEIGHT = 32 * SCALE;
    private Screen screen;
    
    /**
     * Chip8 16 digit keyboard.
     */
    KeyBoard keyboard;
    
    Sound sound;
    
    
    /**
     * Size of the loaded rom.
     */
    private int romSize;
        
    /**
     * Constructor - Creates Chip8.
     */
    public Chip8(){
        this.memory = new Memory(MEMSIZE);
        this.registerBank = new RegisterBank();
        this.keyboard = new KeyBoard();    
        this.sound = new Sound(true);
        this.cpu = new CPU(this.memory, this.registerBank, this.keyboard); 
        System.out.println("[CHIP-8] Chip-8 components correctly initialized.");
        
        createGUI();
        
        System.out.println("[CHIP-8] Chip-8 system correctly initialized.");
    }
    
     /**
     * Creates and initializes Chip8 screen.
     */
    private void createGUI(){
        JFrame frame = new JFrame("Chip-8");
        
        //Add keyboard as key listener
        frame.addKeyListener(keyboard);
        
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
    
  
    /**
     * Loads a binary rom 
     * @param rom To load
     * @throws java.io.IOException
     */
    public void loadRom(String rom) throws IOException{
        File f = new File("ROMS/"+rom);
        byte[] loadedRom = Files.readAllBytes(f.toPath());
        this.romSize = loadedRom.length;
        
        //Starts to load in 0x200 (where PC starts)
        short addressToLoad = 0x200; 
        for(byte b: loadedRom){
            this.memory.set(addressToLoad, b);
            addressToLoad += 0x1;
        }
    }       
    
    /**
     * Chip8 main loop.
     * @throws InterruptedException 
     */
    public void startEmulationLoop() throws InterruptedException{        
        long time = System.currentTimeMillis();
        
        while(true){
            
            //1.- Fetch (Load instruction from memory according to PC)
            cpu.getNextOpcode();

            //2.- Increment PC before executing, so if a JMP is done, it will be overriden.
            cpu.incrementPC();

            //3.- Decode instruction and execute it
            cpu.decode();

            //4.- Update screen 
            if(memory.drawScreen){
                screen.repaint();
                memory.drawScreen = false;
            }
            
            if(System.currentTimeMillis() - time > (1000/60)){                
                //5.- Decrement DT
                if(registerBank.DT > 0){
                    registerBank.DT = (byte)(registerBank.DT - 0x01);
                }

                //6.- Decrement ST. 
                if(registerBank.ST > 0){
                    sound.startSound();
                    registerBank.ST = (byte)(registerBank.ST - 0x01);
                    if(registerBank.ST == 0){
                        sound.stopSound();
                    }
                }
                
                time = System.currentTimeMillis();
            }
            
            Thread.sleep(1);
        }
    }
   

    /**
     * Print loaded rom from 0x200 to romSize.
     */
    public void printRom(){      
        registerBank.PC = 0x200;
        for(int i=0; i<romSize; i++){
            cpu.getNextOpcode();
            cpu.printOpcode();
            cpu.incrementPC();
        }        
    }
    
    /**
     * Prints Memory content.
     */
    public void printMemory(){
        memory.printMemory();
    }

    /**
     * Prints Register Bank content.
     */
    public void printRegisterBank(){
        registerBank.printRegisterBank();
    }
}