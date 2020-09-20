package machine_model.inputOutput;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/*
 * ORIGINAL:           KEYBOARD:            ARDUINO:         
 * 1 2 3 C              1 2 3 4             1 2 3 A
 * 4 5 6 D              Q W E R             4 5 6 B
 * 7 8 9 E              A S D F             7 8 9 C
 * A 0 B F              Z X C V             * 0 # D
 */

/**
 * Class that manages the keyboard.
 * It supports Arduino input from a 4x4 keypad
 * @author David
 */
public class KeyBoard implements KeyListener{
    /**
     * Stores what keys are pressed.
     */
    private boolean[] keys_pressed;
    
    /**
     * Stores the number of keys pressed.
     */
    private int numPressedKeys;
    
    /**
     * Last key that was pressed.
     */
    private byte lastPressed;
        
    /**
     * SerialPort to receive data from Arduino.
     */
    private SerialPort sp;
    
    /**
     * Port where arduino has been connected.
     */
    private String arduinoPort;
    private boolean isArduinoConnected;
    
    
    /**
     * Constructor - Initializes the keyboard.
     */
    public KeyBoard(){
        keys_pressed = new boolean[16]; //16 keys, 0-F
        numPressedKeys = 0;
        sp = null;
        arduinoPort = null;      
        isArduinoConnected = false;
        System.out.println("[Chip8] Arduino keyboard not added");               
    }    
    
    /**
     * Initializes Serial Port to receive arduino data. 
     * @return 
     */    
    public boolean openArduinoPort(){
        if(arduinoPort != null && !arduinoPort.isEmpty() && !arduinoPort.isBlank()){
            sp = SerialPort.getCommPort(arduinoPort);
            sp.setComPortParameters(9600, 8, 1, 0);
            if(sp.openPort()){
                System.out.println("[Chip8] Arduino port oppened succesfully");

                sp.addDataListener(new SerialPortDataListener() {
                    @Override
                    public int getListeningEvents() {
                        return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
                    }

                    @Override
                    public void serialEvent(SerialPortEvent event) {
                        if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE)
                            return;

                        byte[] newData = new byte[sp.bytesAvailable()];
                        int numBytes = sp.readBytes(newData, newData.length);   
                        if(numBytes == 1){
                            byte data = newData[0];                   

                            if(data <= 'F'){
                                //Pressed
                                if(setArduinoKeyTo(data, true)){
                                    numPressedKeys++;
                                }

                            }else{
                                //Released 
                                if(setArduinoKeyTo(data-'0', false)){
                                    numPressedKeys--;
                                }
                            }
                        }
                    }
                    
                    
                });
                isArduinoConnected = true;
                System.out.println("[Chip8] Arduino keyboard added");
                return true;
            
            }else{
                isArduinoConnected = false;
                System.out.println("[Chip8] Can not open arduino port:"+arduinoPort);
                System.out.println("[Chip8] Arduino keyboard can not be added");
                return false;
            }
            
        }else{
            System.out.println("[Chip8] Arduino port not added");
            return false;
        }
    }
    
    /**
     * Closes Serial Port if it was opened.
     * @return 
     */
    public boolean closeArduinoPort(){
        if(sp.isOpen()){
            if(sp.closePort()){
                isArduinoConnected = false;
                System.out.println("[Chip8] Arduino keyboard removed");
                return true;
            }else{
                System.out.println("[Chip8] Can not close arduino port");
                return false;
            }
        }else{
            System.out.println("[Chip8] Arduino port is not open");
            System.out.println("[Chip8] Can not close arduino port");
            return false;
        }
    }
    
    /**
     * Sets a new arduino port.
     * @param newPort 
     */
    public void setArduinoPort(String newPort){
        this.arduinoPort = newPort;
    }
    
    /**
     * Returns arduino port.
     * @return 
     */
    public String getArduinoPort(){
        return this.arduinoPort;
    }
    
    public boolean isArduinoConnected(){
        return this.isArduinoConnected;
    }
    
    /**
     * Returns if the key is pressed or not.
     * @param key
     * @return true if pressed
     */
    public boolean isPressed(byte key){
        return this.keys_pressed[key];
    }
    
    
    /**
     * Waits for a key to be pressed and returns it.
     * @return 
     */
    public byte waitForKey(){
        while(numPressedKeys == 0){
            try{
                Thread.sleep(20);
            }catch(InterruptedException ie){
            }
        }
        return lastPressed;
    }
    
    
    /**
     * Sets the key pressed to true;
     * @param e 
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if(setKeyTo(e.getKeyCode(), true)){
            numPressedKeys++;
        }
    }

    
    /**
     * Sets the key released to false;
     * @param e 
     */
    @Override
    public void keyReleased(KeyEvent e) {
        if(setKeyTo(e.getKeyCode(), false)){
            numPressedKeys--;
        }
    }
    
    
    /**
     * Sets the key "key" to true if pressed and false if released.
     * @param key
     * @param pressedOrReleased
     * @return 
     */
    private boolean setKeyTo(int key, boolean pressedOrReleased){
        switch(key){
            case KeyEvent.VK_1:
                keys_pressed[0x1] = pressedOrReleased;
                lastPressed = 0x1;
              break;
                
            case KeyEvent.VK_2:
                keys_pressed[0x2] = pressedOrReleased;
                lastPressed = 0x2;
              break;

            case KeyEvent.VK_3:
                keys_pressed[0x3] = pressedOrReleased;
                lastPressed = 0x3;
              break;

            case KeyEvent.VK_4:
                keys_pressed[0xC] = pressedOrReleased;
                lastPressed = 0xC;
              break;

            case KeyEvent.VK_Q:
                keys_pressed[0x4] = pressedOrReleased;
                lastPressed = 0x4;
              break;
              
            case KeyEvent.VK_W:
                keys_pressed[0x5] = pressedOrReleased;
                lastPressed = 0x5;
              break;
              
            case KeyEvent.VK_E:
                keys_pressed[0x6] = pressedOrReleased;
                lastPressed = 0x6;
              break;
              
            case KeyEvent.VK_R:
                keys_pressed[0xD] = pressedOrReleased;
                lastPressed = 0xD;
              break;
              
            case KeyEvent.VK_A:
                keys_pressed[0x7] = pressedOrReleased;
                lastPressed = 0x7;
              break;
              
            case KeyEvent.VK_S:
                keys_pressed[0x8] = pressedOrReleased;
                lastPressed = 0x8;
              break;
              
            case KeyEvent.VK_D:
                keys_pressed[0x9] = pressedOrReleased;
                lastPressed = 0x9;
              break;
              
            case KeyEvent.VK_F:
                keys_pressed[0xE] = pressedOrReleased;
                lastPressed = 0xE;
              break;
              
            case KeyEvent.VK_Z:
                keys_pressed[0xA] = pressedOrReleased;
                lastPressed = 0xA;
              break;
              
            case KeyEvent.VK_X:
                keys_pressed[0x0] = pressedOrReleased;
                lastPressed = 0x0;
              break;  
              
            case KeyEvent.VK_C:
                keys_pressed[0xB] = pressedOrReleased;
                lastPressed = 0xB;
              break;
              
            case KeyEvent.VK_V:
                keys_pressed[0xF] = pressedOrReleased;
                lastPressed = 0xF;
              break; 
              
            default: return false; 
        }
        return true;
    }
    
    
    /**
     * Sets the key "key" to true or false.
     * As there is not key pressed/released with serial input, it sets the key
     * as pressed if it was false (not pressed) and sets the key as not pressed
     * if it was true (pressed).
     * @param key
     * @return 
     */
    private boolean setArduinoKeyTo(int key, boolean pressedOrReleased){
        byte newKey; 
        switch(key){
            case KeyEvent.VK_1:
                newKey = 0x1;
              break;
                
            case KeyEvent.VK_2:
                newKey = 0x2;
              break;

            case KeyEvent.VK_3:
                newKey = 0x3;
              break;

            case KeyEvent.VK_4:
                newKey = 0x4;
              break;
              
            case KeyEvent.VK_5:
                newKey = 0x5;
              break;
              
            case KeyEvent.VK_6:
                newKey = 0x6;
              break;
              
            case KeyEvent.VK_7:
                newKey = 0x7;
              break;
              
            case KeyEvent.VK_8:
                newKey = 0x8;
              break;
              
            case KeyEvent.VK_9:
                newKey = 0x9;
              break;
              
            case KeyEvent.VK_0:
                newKey = 0x0;
              break;  
              
            case KeyEvent.VK_A:
                newKey = 0xA;
              break; 
              
            case KeyEvent.VK_B:
                newKey = 0xB;
              break; 
            
            case KeyEvent.VK_C:
                newKey = 0xC;
              break; 
            
            case KeyEvent.VK_D:
                newKey = 0xD;
              break; 
            
            case KeyEvent.VK_E:
                newKey = 0xE;
              break; 
            
            case KeyEvent.VK_F:
                newKey = 0xF;
              break; 
              
            default: return false; 
        }
        
        keys_pressed[newKey] = pressedOrReleased;
        lastPressed = newKey;
        return true;
    }
    
    
    @Override
    public void keyTyped(KeyEvent e) {}
}
