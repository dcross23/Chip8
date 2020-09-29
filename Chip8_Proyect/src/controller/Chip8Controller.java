/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import gui_view.Gui;
import machine_model.CPU;
import machine_model.Chip8;
import machine_model.Memory;
import machine_model.RegisterBank;
import machine_model.inputOutput.KeyBoard;
import machine_model.inputOutput.Screen;

/**
 *
 * @author David
 */
public class Chip8Controller {
    private final Gui gui;
    private final Chip8 chip8;
    
    public Chip8Controller(){
        chip8 = new Chip8();  
        gui = new Gui(this);  
        gui.setVisible(true);     
        System.out.println("[CHIP-8] Chip-8 system correctly initialized.");
    }    
    
    public void startEmulation(){
        this.chip8.startEmulationLoop();
    }
    
    public void stopEmulation(){
        this.chip8.stopEmulationLoop();
        chip8.loadRom();
    }
    
    public void loadRom(String rom){
        this.chip8.setRom(rom);
        this.chip8.loadRom();
    }
    
    public boolean activateArduino(String port){
        return this.chip8.addArduinoKeypad(port);
    }
    
    public boolean removeArduino(){
        return this.chip8.removeArduinoKeypad();
    }
    
    public boolean isArduinoConnected(){
        return this.chip8.isArduinoConnected();
    }
    
    
    public Memory getMemory() {
        return chip8.getMemory();
    }
    public RegisterBank getRegisterBank() {
        return chip8.getRegisterBank();
    }
    public CPU getCpu() {
        return chip8.getCpu();
    }
    public Screen getScreen() {
        return chip8.getScreen();
    }
    public KeyBoard getKeyboard() {
        return chip8.getKeyboard();
    } 
    public boolean isChip8Working(){
        return this.chip8.isWorking;
    }
    public boolean isSoundOn(){
        return this.chip8.isSoundOn();
    }
    public void setSoundOn(boolean onOff){
        this.chip8.setSoundTo(onOff);
    }
}
