/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_view;

import controller.Chip8Controller;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

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

        SoundOnOff = new javax.swing.JLabel();
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

        SoundOnOff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui_view/icons/SoundOffNoSelected.png"))); // NOI18N
        SoundOnOff.setPreferredSize(new java.awt.Dimension(54, 24));
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
        getContentPane().add(SoundOnOff, new org.netbeans.lib.awtextra.AbsoluteConstraints(889, 98, -1, -1));

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
            SoundOnOff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui_view/icons/SoundOffSelected.png")));
        }else{
            controller.setSoundOn(true);
            SoundOnOff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui_view/icons/SoundOnSelected.png")));
        }
    }//GEN-LAST:event_SoundOnOffMouseClicked

    private void SoundOnOffMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SoundOnOffMouseEntered
        if(controller.isSoundOn()){
            SoundOnOff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui_view/icons/SoundOnSelected.png")));
        }else{
            SoundOnOff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui_view/icons/SoundOffSelected.png")));
        }
    }//GEN-LAST:event_SoundOnOffMouseEntered

    private void SoundOnOffMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SoundOnOffMouseExited
        if(controller.isSoundOn()){
            SoundOnOff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui_view/icons/SoundOnNoSelected.png")));
        }else{
            SoundOnOff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui_view/icons/SoundOffNoSelected.png")));
        }
    }//GEN-LAST:event_SoundOnOffMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CloseIcon;
    private javax.swing.JLabel Fondo;
    private javax.swing.JLabel RomError;
    private javax.swing.JLabel RomLabel;
    public javax.swing.JPanel Screen;
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
    public String romPath = "ROMS/PONG";
    boolean hasStarted = false;
    // End of my variables
    
    
}