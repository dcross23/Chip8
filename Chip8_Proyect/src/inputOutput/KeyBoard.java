package inputOutput;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/*
 * ORIGINAL:            MAPPED:              
 * 1 2 3 C              1 2 3 4
 * 4 5 6 D              Q W E R
 * 7 8 9 E              A S D F
 * A 0 B F              Z X C V
 */

/**
 * Class that manages the keyboard.
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
     * Constructor - Initializes the keyboard.
     */
    public KeyBoard(){
        keys_pressed = new boolean[16]; //16 keys, 0-F
        numPressedKeys = 0;
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
    
    
    @Override
    public void keyPressed(KeyEvent e) {
        if(setKeyTo(e.getKeyCode(), true)){
            numPressedKeys++;
        }
    }

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
    
    @Override
    public void keyTyped(KeyEvent e) {}
}
