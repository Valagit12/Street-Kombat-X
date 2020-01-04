/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author h9113
 */
public class KeyManager implements KeyListener{
    
    private boolean[] keys;
    public boolean up, down, left, right;
    
    public KeyManager() {
        keys = new boolean[256];
    }
    
    public void tick() {
        up = keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_S];
        right = keys[KeyEvent.VK_D];
        left = keys[KeyEvent.VK_A];
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }
    
}
