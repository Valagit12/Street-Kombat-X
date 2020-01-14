/**
 * Name: Valareza Arezehgar and Brian Cho (Pack Studios)
 * Date: January 13, 2020
 * Version: 1
 * Description: This class falls under the State class, and is responsible for the stage select screen in the game. 
 */
package states;

import gfx.Assets;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import players.Player;
import streetkombatx.Game;

/**
 *
 * @author Pack Studios
 */
public class StageSelectState extends State{
    
    private int stageSelectionHorizontal = 0;// value for the movement of the cursor horizontally
    private int stageSelectionVertical = 0;//like above but for vertical movement
    private int xPlayer = 466;// location of player on x-axis
    private int yPlayer = 234;//location of player on y-axis
    
    private boolean right, left, up, down;// the possible directional inputs
    private boolean enter, escape;// the button inputs
    private boolean previousEnter = true; // used to check if had pressed enter previously
    private boolean previousEscape = true;//like aboce but with escape
    
    private Player player1;
    private Player player2;
    
    private BufferedImage stageSelectScreen;// The image for the stage select screen background
    private BufferedImage[] stage;// an array that stores the stages
    
    /**
     * Method: This is a constructor method that takes in the parameters sent through, and populates the fields of this class with them
     * Precondition: game and player parameters must have been initialized properly with the correct fields.
     * Post condition: StageSelectState fields are populated with the parameters
     * @param game: The main game
     * @param player1: Player one object
     * @param player2: Player two object
     */
    public StageSelectState(Game game, Player player1, Player player2){
        super(game);
        this.player1 = player1;
        this.player2 = player2;
        stageSelectScreen = Assets.stageSelectScreen;
    }

    /**
     * Method: This tick method is run constantly, checking for input and adjusting the cursor location accordingly
     * Precondition: the key input booleans(up, down, enter) must all be proper boolean, .tick method for getStance should be returning the correct stance for the player object
     * Post condition: The program has read for key input and the correlating adjustments to the cursor have been made
     */
    @Override
    public void tick() {
        player1.getStance().tick();
        player2.getStance().tick();
        
        right = game.getKeyManager().isPlayer2_right();
        left = game.getKeyManager().isPlayer2_left();
        up = game.getKeyManager().isPlayer2_jump();
        down = game.getKeyManager().isPlayer2_crouch();
        
        enter = game.getKeyManager().isEnter();
        escape = game.getKeyManager().isEscape();
        
        if (right && stageSelectionHorizontal == 0){
            stageSelectionHorizontal++;
        }
        else if (left && stageSelectionHorizontal == 1){
            stageSelectionHorizontal--;
        }
        
        if (up && stageSelectionVertical == 1){
            stageSelectionVertical--;
        }
        else if (down && stageSelectionVertical == 0){
            stageSelectionVertical++;
        }
             
        if (stageSelectionHorizontal == 0){
            xPlayer = 466;
        }
        else if (stageSelectionHorizontal == 1){
            xPlayer = 647;
        }
        
        if (stageSelectionVertical == 0){
            yPlayer = 234;
        }
        else if (stageSelectionVertical == 1){
            yPlayer = 344;
        }
        
        confirmSelection();
        previousEnter = enter;
        previousEscape = escape;
    }

    /**
     * Method: This method runs as many times as the tick method, providing graphical updates, as the tick provides numerical and variable updates
     * Precondition: gState object must have been initialized properly
     * Post condition: The graphic has been updated for one tick
     * @param gState 
     */
    @Override
    public void render(Graphics gState) {
        gState.drawImage(stageSelectScreen, 0, 0, 1280, 720, null);
        gState.drawImage(player1.getStance().getCurrentFrame(), 29, 161, 411, 822, null);
        gState.drawImage(player2.getStance().getCurrentFrame(), 861, 161, 411, 822, null);
        gState.setFont(Assets.dragonForceNum);
        gState.setColor(Color.red);
        gState.drawString(player1.getCharTitle(), 77, 81);
        gState.drawString(player1.getCharTitle(), 1020, 81);
        gState.drawString(getStageTitle(), 425, 180);
        for (int i = 0; i < 4; i++)
            gState.drawRect(xPlayer + i, yPlayer + i, 166 - 2 * i, 98 - 2 * i);
    }
    
    /**
     * Method: This method takes the location of the cursor and returns the name of the stage
     * Precondition: StageSelectionHorizontal and Vertical must be proper int values, 
     * Post condition: The name of the selected stage is returned
     * @return: The name of the selected stage
     */
    private String getStageTitle() {
        if (stageSelectionHorizontal == 0 && stageSelectionVertical == 0){
            return "Kasai's Fire Temple";
        }
        else if (stageSelectionHorizontal == 1 && stageSelectionVertical == 0){
            return "King Dom's Kingdom";
        }
        else {
            return "";
        }
    }
    
    
    /**
     * Method: This method loads the correct background stage for the game, and then sets the state as gameState, so that the game can progress
     * Precondition: The Assets class must have initialized each of the stage animations properly
     * Post condition: The stage has been loaded and the gameState object has become the state of the game
     */
    private void confirmSelection() {
        if (stageSelectionVertical != 1 && stageSelectionVertical != 1 && enter && !previousEnter){
            if (stageSelectionHorizontal == 0 && stageSelectionVertical == 0){
                stage = Assets.fireTemple;
            }
            else if (stageSelectionHorizontal == 1 && stageSelectionVertical == 0){
                stage = Assets.kingdom;
            }
            
            State.setState(new GameState(game, player1, player2, stage));
        }
        
        if (escape && !previousEscape){
            State.setState(new CharSelectState(game));
        }
    }
}
    

