/**
 * Name: Valareza Arezehgar and Brian Cho (Pack Studios)
 * Date: January 13, 2020
 * Version: 1
 * Description: This class falls under the State class, and is responsible for the help screen section of our game
 */
package states;

import gfx.Assets;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import streetkombatx.Game;

/**
 *
 * @author Pack Studios
 */
public class HelpState extends State {
    private int timer = 0;//timer
    private int selection = 0;//where the cursor is
    
    private boolean right, left, escape;//the key inputs
    private int pagelvl = 0;
    private boolean previousLeft, previousRight;//previous inputs

    private State previousState;// the previous state
    
    private BufferedImage hlpScr1; // The pictures for the help screen
    private BufferedImage hlpScr2;
    private BufferedImage hlpScr3;
    private BufferedImage hlpScr4;
    private BufferedImage hlpScrCtrl;

    /**
     * Method: This constructor method uses the game object to call an instance of the super class, sets the previousState field of this class equal to that sent in by the parameter, and initializes the images required for the help screen
     * Precondition: game must have been initialized with correct fields, previousState must be a proper state, Assets should have loaded in the images correctly
     * Post condition: The HelpState constructs the HelpState object by calling the super method, and it initialized the necessary images
     * @param game: the main game
     * @param previousState: The previous state of the game, before helpState 
     */
    public HelpState(Game game, State previousState) {
        super(game);
        this.previousState = previousState;
        this.hlpScr1 = Assets.help.get(0);
        this.hlpScr2 = Assets.help.get(1);
        this.hlpScr3 = Assets.help.get(2);
        this.hlpScr4 = Assets.help.get(3);
        this.hlpScrCtrl = Assets.help.get(4);
    }

    /**
     * Method: This method continuously checks for user input, and updates the cursor data accordingly
     * Precondition: The key inputs must be of type boolean
     * Post condition: The method checks for user input and is updated, until the user escapes helpState and returns to the previousState
     */
    @Override
    public void tick() {
        
        right = game.getKeyManager().isPlayer2_right();
        left = game.getKeyManager().isPlayer2_left();
        escape = game.getKeyManager().isEscape();
        
        
        if (pagelvl < 4 && right && !previousRight) {
            pagelvl++;
        } else if (pagelvl > 0 && left && !previousLeft) {
            pagelvl--;
        }
        if (escape){
            State.setState(previousState);
        }
        previousRight = right;
        previousLeft = left;
        
        
    }

    /**
     * Method: This render method updates the visuals of the helpState according to the tick method
     * Precondition: All images have been loaded in right(as Buffered Images), pagelvl is a proper int value below 5 and above 0
     * Post condition: The images are drawn according to the tick method's  results
     * @param gState: A graphics object used to draw images on to the canvas 
     */
    @Override
    public void render(Graphics gState) {
         if (pagelvl == 0) {
            gState.drawImage(hlpScr1, 0, 0, 1280, 720, null);
        } else if (pagelvl == 1) {
            gState.drawImage(hlpScr2, 0, 0, 1280, 720, null);
        } else if (pagelvl == 2) {
            gState.drawImage(hlpScr3, 0, 0, 1280, 720, null);
        } else if (pagelvl == 3){
            gState.drawImage(hlpScr4, 0, 0, 1280, 720, null);
        } else if (pagelvl == 4){
            gState.drawImage(hlpScrCtrl, 0, 0, 1280, 720, null);
        }
    }
}
