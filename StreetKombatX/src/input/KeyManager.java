/**
 * Name: Valareza Arezehgar and Brian Cho (Pack Studios)
 * Date: January 13, 2020
 * Version: 1
 * Description: This class is responsible for reading key board inputs from the user. All of the controls are initlialized in this class.
 */
package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Pack Studions
 */
public class KeyManager implements KeyListener{
    
    private boolean[] keys;
    private boolean player1_jump, player1_crouch, player1_left, player1_right;
    private boolean player1_block, player1_1, player1_2, player1_special;
    private boolean player2_jump, player2_crouch, player2_left, player2_right;
    private boolean player2_block, player2_1, player2_2, player2_special;
    private boolean enter, escape;
    //All of the above booleans are possible inputs
    
    /**
     * Method: This is a constructor method that creates the keyManager, with the possibility of 256 booleans stored in the keys array
     * Precondition: Keys  must be a proper boolean array
     * Post condition: An array of 256 boolean is initialized
     */
    public KeyManager() {
        keys = new boolean[256];
    }
    
    /**
     * Method: This tick method is responsible for constantly reading user input 60 times per second
     * Precondition: keys has been initialized with a length of 256 booleans
     * Post condition: The method checks for one of the specific inputs
     */
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
        //This is a necessary class in order to run the other keyPressed method
    }

    /**
     * Method: This method takes in a KeyEvent as a parameter and sets the key boolean array at that specific key event true
     * Precondition: keys must be boolean array with a size of 256, KeyEvent must be a possible keyEvent as defined in the previous method
     * Post condition: The keyEvent has been defined as true
     * @param e: A Keyboard input 
     */
    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    /**
     * Method: This method takes in a moment when a key is released called keyRelease, and sets the key boolean array at that specific key release false
     * Precondition: keys must be a boolean array with a size of 256, KeyEvent must be a possible keyEvent as defined in the previous method
     * Post Condition: The keyEvent has been defined as false
     * @param e: A Keyboard input
     */
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
