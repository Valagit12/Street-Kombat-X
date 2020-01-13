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
    private boolean player1_jump, player1_crouch, player1_left, player1_right;
    private boolean player1_block, player1_1, player1_2, player1_special;
    private boolean player2_jump, player2_crouch, player2_left, player2_right;
    private boolean player2_block, player2_1, player2_2, player2_special;
    private boolean enter, escape;
    
    public KeyManager() {
        keys = new boolean[256];
    }
    
    public void tick() {
        //Enter
        enter = keys[KeyEvent.VK_ENTER];
        escape = keys[KeyEvent.VK_ESCAPE];
        
        //player 1 input
        player1_jump = keys[KeyEvent.VK_W];
        player1_crouch = keys[KeyEvent.VK_S];
        player1_right = keys[KeyEvent.VK_D];
        player1_left = keys[KeyEvent.VK_A];
        player1_block = keys[KeyEvent.VK_Y];
        player1_1 = keys[KeyEvent.VK_E];
        player1_2 = keys[KeyEvent.VK_R];
        player1_special = keys[KeyEvent.VK_T];
        
        //player 2 input
        player2_jump = keys[KeyEvent.VK_UP];
        player2_crouch = keys[KeyEvent.VK_DOWN];
        player2_right = keys[KeyEvent.VK_RIGHT];
        player2_left = keys[KeyEvent.VK_LEFT];
        player2_block = keys[KeyEvent.VK_M];
        player2_1 = keys[KeyEvent.VK_SLASH];
        player2_2 = keys[KeyEvent.VK_PERIOD];
        player2_special = keys[KeyEvent.VK_COMMA];
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

    public boolean isPlayer1_jump() {
        return player1_jump;
    }

    public boolean isPlayer1_crouch() {
        return player1_crouch;
    }

    public boolean isPlayer1_left() {
        return player1_left;
    }

    public boolean isPlayer1_right() {
        return player1_right;
    }

    public boolean isPlayer1_block() {
        return player1_block;
    }

    public boolean isPlayer1_1() {
        return player1_1;
    }

    public boolean isPlayer1_2() {
        return player1_2;
    }

    public boolean isPlayer1_special() {
        return player1_special;
    }

    public boolean isPlayer2_jump() {
        return player2_jump;
    }

    public boolean isPlayer2_crouch() {
        return player2_crouch;
    }

    public boolean isPlayer2_left() {
        return player2_left;
    }

    public boolean isPlayer2_right() {
        return player2_right;
    }

    public boolean isPlayer2_block() {
        return player2_block;
    }

    public boolean isPlayer2_1() {
        return player2_1;
    }

    public boolean isPlayer2_2() {
        return player2_2;
    }

    public boolean isPlayer2_special() {
        return player2_special;
    }

    public boolean isEnter() {
        return enter;
    }

    public boolean isEscape() {
        return escape;
    }
    
    
}
