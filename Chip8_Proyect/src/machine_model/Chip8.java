package machine_model;

import machine_model.inputOutput.KeyBoard;
import machine_model.inputOutput.Screen;
import machine_model.inputOutput.Sound;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;


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
     * Posibility to add Arduino keypad as keyboard.
     */
    private KeyBoard keyboard;
    
    /**
     * Chip8 sound.
     */
    private Sound sound;
    
    /**
     * Size of the loaded rom.
     */
    private int romSize;
    private String rom;
    private boolean hasLoadedARom = false;
    
    /**
     * Say if startEmulationLoop function is working.
     */
    public boolean isWorking = false;
    
    /**
     * Says when chip8 has to stop emulation loop.
     */
    private boolean mustQuit = false;
        
    /**
     * Constructor - Creates Chip8.
     */
    public Chip8(){
        this.memory = new Memory(MEMSIZE);
        this.registerBank = new RegisterBank();
        this.keyboard = new KeyBoard();    
        this.cpu = new CPU(this.memory, this.registerBank, this.keyboard); 
        this.sound = new Sound(false);
        this.screen = new Screen(memory, SCALE, SCREEN_WIDTH, SCREEN_HEIGHT);
        System.out.println("[CHIP-8] Chip-8 components correctly initialized.");       
    }
  
    /**
     * Loads a binary rom 
     */
    public void loadRom(){
        File f = new File(this.rom);
        byte[] loadedRom;
        try {
            loadedRom = Files.readAllBytes(f.toPath());
            this.romSize = loadedRom.length;
        
            //Starts to load in 0x200 (where PC starts)
            short addressToLoad = 0x200; 
            for(byte b: loadedRom){
                this.memory.set(addressToLoad, b);
                addressToLoad += 0x1;
                if(addressToLoad > 0xFFF){
                    break;
                }
            }
        
        } catch (IOException ex) {
            Logger.getLogger(Chip8.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Chip8 main loop.
     */
    public void startEmulationLoop(){    
        this.isWorking = true;
        long time = System.currentTimeMillis();
        mustQuit = false;
        while(!mustQuit){
            
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
            
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(Chip8.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.isWorking = false;
    }
   
    public void stopEmulationLoop(){
        this.mustQuit = true;
        this.memory.resetMemory(MEMSIZE);
        this.registerBank.resetRegisterBank();
    }
    
    public boolean addArduinoKeypad(String port){
        this.keyboard.setArduinoPort(port);
        return this.keyboard.openArduinoPort();
    }
    
    public boolean removeArduinoKeypad(){
        return this.keyboard.closeArduinoPort();
    }
    
    public boolean isArduinoConnected(){
        return this.keyboard.isArduinoConnected();
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

    
    //GETTERS AND SETTERS.
    public Memory getMemory() {
        return this.memory;
    }
    public RegisterBank getRegisterBank() {
        return this.registerBank;
    }
    public CPU getCpu() {
        return this.cpu;
    }
    public Screen getScreen() {
        return this.screen;
    }
    public KeyBoard getKeyboard() {
        return this.keyboard;
    }
    public void setRom(String rom){
        this.rom = rom;
    }
    public void setSoundTo(boolean onOff){
        this.sound.setEnabled(onOff);
    }
    public boolean isSoundOn(){
        return this.sound.isEnabled();
    }
}