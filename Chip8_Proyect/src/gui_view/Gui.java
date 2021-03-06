/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_view;

import controller.Chip8Controller;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author David
 */
public class Gui extends javax.swing.JFrame {

    /**
     * Creates new form Frame
     * @param controller
     */
    public Gui(Chip8Controller controller){
        this.controller = controller;
        loopThreadTask = new Runnable() {
            @Override
            public void run() {
                controller.startEmulation();
            }
        };
        
        initComponents();
        this.addKeyListener(controller.getKeyboard());
        this.controller.getScreen().setVisible(true);
        
        System.out.println("[CHIP-8] Chip-8 gui correctly initialized.");
        
        this.controller.loadRom(this.romPath);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        OptionsError = new javax.swing.JLabel();
        OptionsPanel = new javax.swing.JPanel();
        ArduinoPort = new javax.swing.JLabel();
        ChangePortButton = new javax.swing.JLabel();
        ArduinoOnOff = new javax.swing.JLabel();
        CloseOptions = new javax.swing.JLabel();
        SoundOnOff = new javax.swing.JLabel();
        Options = new javax.swing.JLabel();
        SelectOptionIcon = new javax.swing.JLabel();
        RomError = new javax.swing.JLabel();
        StartStopButton = new javax.swing.JLabel();
        StartStopIcon = new javax.swing.JLabel();
        CloseIcon = new javax.swing.JLabel();
        SelectRomIcon = new javax.swing.JLabel();
        RomLabel = new javax.swing.JLabel();
        Screen = controller.getScreen();
        Fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        OptionsError.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui_view/icons/OptionsError.png"))); // NOI18N
        getContentPane().add(OptionsError, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 130, -1, -1));
        OptionsError.setVisible(false);

        OptionsPanel.setEnabled(false);
        OptionsPanel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                OptionsPanelMouseDragged(evt);
            }
        });
        OptionsPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                OptionsPanelMousePressed(evt);
            }
        });
        OptionsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ArduinoPort.setFont(new java.awt.Font("Minercraftory", 0, 14)); // NOI18N
        ArduinoPort.setForeground(new java.awt.Color(255, 255, 255));
        ArduinoPort.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ArduinoPort.setPreferredSize(new java.awt.Dimension(118, 28));
        ArduinoPort.setRequestFocusEnabled(false);
        OptionsPanel.add(ArduinoPort, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 157, -1, -1));

        ChangePortButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui_view/icons/ChangePortButton.png"))); // NOI18N
        ChangePortButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ChangePortButtonMouseClicked(evt);
            }
        });
        OptionsPanel.add(ChangePortButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 160, -1, -1));

        ArduinoOnOff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui_view/icons/OffNoSelected.png"))); // NOI18N
        ArduinoOnOff.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ArduinoOnOffMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ArduinoOnOffMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ArduinoOnOffMouseExited(evt);
            }
        });
        OptionsPanel.add(ArduinoOnOff, new org.netbeans.lib.awtextra.AbsoluteConstraints(181, 111, -1, -1));

        CloseOptions.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui_view/icons/CloseOptions.png"))); // NOI18N
        CloseOptions.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CloseOptionsMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                CloseOptionsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                CloseOptionsMouseExited(evt);
            }
        });
        OptionsPanel.add(CloseOptions, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 10, -1, -1));

        SoundOnOff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui_view/icons/OffNoSelected.png"))); // NOI18N
        SoundOnOff.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SoundOnOffMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                SoundOnOffMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                SoundOnOffMouseExited(evt);
            }
        });
        OptionsPanel.add(SoundOnOff, new org.netbeans.lib.awtextra.AbsoluteConstraints(181, 62, -1, -1));

        Options.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui_view/icons/Options.png"))); // NOI18N
        OptionsPanel.add(Options, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getContentPane().add(OptionsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 90, -1, -1));
        this.OptionsPanel.setVisible(false);

        SelectOptionIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui_view/icons/Selection.png"))); // NOI18N
        SelectOptionIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SelectOptionIconMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                SelectOptionIconMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                SelectOptionIconMouseExited(evt);
            }
        });
        getContentPane().add(SelectOptionIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(912, 98, -1, -1));

        RomError.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui_view/icons/RomError.png"))); // NOI18N
        RomError.setVisible(false);
        getContentPane().add(RomError, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 80, -1, -1));

        StartStopButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui_view/icons/StartStopButton.png"))); // NOI18N
        StartStopButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                StartStopButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                StartStopButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                StartStopButtonMouseExited(evt);
            }
        });
        getContentPane().add(StartStopButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(519, 129, -1, -1));

        StartStopIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui_view/icons/StartIcon.png"))); // NOI18N
        getContentPane().add(StartStopIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(438, 97, -1, -1));

        CloseIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui_view/icons/CloseIcon1.png"))); // NOI18N
        CloseIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CloseIconMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                CloseIconMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                CloseIconMouseExited(evt);
            }
        });
        getContentPane().add(CloseIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 30, 30, 30));

        SelectRomIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui_view/icons/Selection.png"))); // NOI18N
        SelectRomIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SelectRomIconMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                SelectRomIconMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                SelectRomIconMouseExited(evt);
            }
        });
        getContentPane().add(SelectRomIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 98, -1, -1));

        RomLabel.setFont(new java.awt.Font("Minercraftory", 0, 16)); // NOI18N
        RomLabel.setForeground(new java.awt.Color(255, 255, 255));
        RomLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RomLabel.setText("PONG");
        RomLabel.setPreferredSize(new java.awt.Dimension(62, 134));
        getContentPane().add(RomLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 134, 190, 29));

        Screen.setBackground(new java.awt.Color(102, 102, 102));
        Screen.setPreferredSize(new java.awt.Dimension(960, 480));
        Screen.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                ScreenMouseDragged(evt);
            }
        });
        Screen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ScreenMousePressed(evt);
            }
        });

        javax.swing.GroupLayout ScreenLayout = new javax.swing.GroupLayout(Screen);
        Screen.setLayout(ScreenLayout);
        ScreenLayout.setHorizontalGroup(
            ScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 960, Short.MAX_VALUE)
        );
        ScreenLayout.setVerticalGroup(
            ScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 480, Short.MAX_VALUE)
        );

        getContentPane().add(Screen, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 210, -1, -1));

        Fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui_view/icons/Background.png"))); // NOI18N
        Fondo.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                FondoMouseDragged(evt);
            }
        });
        Fondo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                FondoMousePressed(evt);
            }
        });
        getContentPane().add(Fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    
    private void FondoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FondoMousePressed
        xLast = evt.getX();
        yLast = evt.getY();
    }//GEN-LAST:event_FondoMousePressed

    private void FondoMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FondoMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        
        this.setLocation(x-xLast, y-yLast);
    }//GEN-LAST:event_FondoMouseDragged

    private void CloseIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CloseIconMouseClicked
        if(controller.isArduinoConnected())
            controller.removeArduino();
        
        controller.stopEmulation();
        //Wait for the loop thread to finish
        if(loopThread != null){
            try {
                loopThread.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                this.dispose();
            }
        }else{
            this.dispose();
        }
    }//GEN-LAST:event_CloseIconMouseClicked

    private void CloseIconMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CloseIconMouseEntered
        CloseIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui_view/icons/CloseIcon2.png")));
    }//GEN-LAST:event_CloseIconMouseEntered

    private void CloseIconMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CloseIconMouseExited
        CloseIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui_view/icons/CloseIcon1.png")));
    }//GEN-LAST:event_CloseIconMouseExited

    private void ScreenMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ScreenMousePressed
        // No handling code -> when dragged from Screen the Gui should`t move
    }//GEN-LAST:event_ScreenMousePressed

    private void ScreenMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ScreenMouseDragged
        // No handling code -> when dragged from Screen the Gui should`t move
    }//GEN-LAST:event_ScreenMouseDragged

    private void SelectRomIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SelectRomIconMouseClicked
        if(!controller.isChip8Working()){
            JFileChooser jfc = new JFileChooser();
            int selected = jfc.showOpenDialog(this);

            if(selected == JFileChooser.APPROVE_OPTION){
                this.romPath = jfc.getSelectedFile().toString();
                this.RomLabel.setText(jfc.getSelectedFile().getName());
                controller.loadRom(this.romPath);         
            }
        
        }else{
            RomError.setVisible(true);
        }
    }//GEN-LAST:event_SelectRomIconMouseClicked

    private void SelectRomIconMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SelectRomIconMouseEntered
        SelectRomIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui_view/icons/Selection2.png")));
    }//GEN-LAST:event_SelectRomIconMouseEntered

    private void SelectRomIconMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SelectRomIconMouseExited
        SelectRomIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui_view/icons/Selection.png")));
    }//GEN-LAST:event_SelectRomIconMouseExited

    private void StartStopButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_StartStopButtonMouseEntered
        StartStopButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui_view/icons/StartStopButton2.png")));
    }//GEN-LAST:event_StartStopButtonMouseEntered

    private void StartStopButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_StartStopButtonMouseExited
        StartStopButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui_view/icons/StartStopButton.png")));
    }//GEN-LAST:event_StartStopButtonMouseExited

    private void StartStopButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_StartStopButtonMouseClicked
        if(this.hasStarted){
            StartStopIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui_view/icons/StartIcon.png")));
            this.hasStarted = false;
            controller.stopEmulation();
            RomError.setVisible(false);
            OptionsError.setVisible(false);
            
        }else{
            StartStopIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui_view/icons/StopIcon.png")));
            this.hasStarted = true;
            
            loopThread = new Thread(loopThreadTask,"Chip8-Loop-Thread");
            loopThread.start();
        }        
    }//GEN-LAST:event_StartStopButtonMouseClicked

    private void SoundOnOffMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SoundOnOffMouseClicked
        if(controller.isSoundOn()){
            controller.setSoundOn(false);
            SoundOnOff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui_view/icons/OffSelected.png")));
        }else{
            controller.setSoundOn(true);
            SoundOnOff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui_view/icons/OnSelected.png")));
        }
    }//GEN-LAST:event_SoundOnOffMouseClicked

    private void SoundOnOffMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SoundOnOffMouseEntered
        if(controller.isSoundOn()){
            SoundOnOff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui_view/icons/OnSelected.png")));
        }else{
            SoundOnOff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui_view/icons/OffSelected.png")));
        }
    }//GEN-LAST:event_SoundOnOffMouseEntered

    private void SoundOnOffMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SoundOnOffMouseExited
        if(controller.isSoundOn()){
            SoundOnOff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui_view/icons/OnNoSelected.png")));
        }else{
            SoundOnOff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui_view/icons/OffNoSelected.png")));
        }
    }//GEN-LAST:event_SoundOnOffMouseExited

    private void SelectOptionIconMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SelectOptionIconMouseEntered
        SelectOptionIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui_view/icons/Selection2.png")));
    }//GEN-LAST:event_SelectOptionIconMouseEntered

    private void SelectOptionIconMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SelectOptionIconMouseExited
        SelectOptionIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui_view/icons/Selection.png")));
    }//GEN-LAST:event_SelectOptionIconMouseExited

    private void SelectOptionIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SelectOptionIconMouseClicked
        if(!controller.isChip8Working()){
            this.OptionsPanel.setEnabled(true);
            this.OptionsPanel.setVisible(true);
        }else{
            OptionsError.setVisible(true);
        }
    }//GEN-LAST:event_SelectOptionIconMouseClicked

    private void OptionsPanelMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OptionsPanelMouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_OptionsPanelMouseDragged

    private void OptionsPanelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OptionsPanelMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_OptionsPanelMousePressed

    private void CloseOptionsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CloseOptionsMouseEntered
        CloseOptions.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui_view/icons/CloseOptions2.png")));
    }//GEN-LAST:event_CloseOptionsMouseEntered

    private void CloseOptionsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CloseOptionsMouseExited
        CloseOptions.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui_view/icons/CloseOptions.png")));
    }//GEN-LAST:event_CloseOptionsMouseExited

    private void CloseOptionsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CloseOptionsMouseClicked
        this.OptionsPanel.setVisible(false);
        this.OptionsPanel.setEnabled(false);
    }//GEN-LAST:event_CloseOptionsMouseClicked

    private void ArduinoOnOffMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ArduinoOnOffMouseClicked
        if(controller.isArduinoConnected()){
            if(controller.removeArduino()){
                ArduinoOnOff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui_view/icons/OffSelected.png")));
            }    
        }else{
            if(controller.activateArduino(arduinoPort)){
                ArduinoOnOff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui_view/icons/OnSelected.png")));
            }
        }
    }//GEN-LAST:event_ArduinoOnOffMouseClicked

    private void ArduinoOnOffMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ArduinoOnOffMouseEntered
        if(controller.isArduinoConnected()){
            ArduinoOnOff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui_view/icons/OnSelected.png")));
        }else{
            ArduinoOnOff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui_view/icons/OffSelected.png")));
        }
    }//GEN-LAST:event_ArduinoOnOffMouseEntered

    private void ArduinoOnOffMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ArduinoOnOffMouseExited
        if(controller.isArduinoConnected()){
            ArduinoOnOff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui_view/icons/OnNoSelected.png")));
        }else{
            ArduinoOnOff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui_view/icons/OffNoSelected.png")));
        }
    }//GEN-LAST:event_ArduinoOnOffMouseExited

    private void ChangePortButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ChangePortButtonMouseClicked
        String showInputDialog = JOptionPane.showInputDialog("Insert new port:");
        if(showInputDialog != null && !showInputDialog.isBlank()){
            this.arduinoPort = showInputDialog;
            this.ArduinoPort.setText(this.arduinoPort);
        }
    }//GEN-LAST:event_ChangePortButtonMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ArduinoOnOff;
    private javax.swing.JLabel ArduinoPort;
    private javax.swing.JLabel ChangePortButton;
    private javax.swing.JLabel CloseIcon;
    private javax.swing.JLabel CloseOptions;
    private javax.swing.JLabel Fondo;
    private javax.swing.JLabel Options;
    private javax.swing.JLabel OptionsError;
    private javax.swing.JPanel OptionsPanel;
    private javax.swing.JLabel RomError;
    private javax.swing.JLabel RomLabel;
    public javax.swing.JPanel Screen;
    private javax.swing.JLabel SelectOptionIcon;
    private javax.swing.JLabel SelectRomIcon;
    private javax.swing.JLabel SoundOnOff;
    private javax.swing.JLabel StartStopButton;
    private javax.swing.JLabel StartStopIcon;
    // End of variables declaration//GEN-END:variables

    // My variables
    Chip8Controller controller;
    
    Thread loopThread;
    Runnable loopThreadTask;
    
    int xLast,yLast; //To move the window
    String romPath = "ROMS/PONG";
    String arduinoPort = "";
    boolean hasStarted = false;
    // End of my variables
    
    
}
