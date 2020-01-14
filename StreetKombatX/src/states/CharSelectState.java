/**
 * Name: Valareza Arezehgar and Brian Cho (Pack Studios)
 * Date: January 13, 2020
 * Version: 1
 * Description: This class falls under the State class, and is responsible for the character select stage
 */
package states;

import gfx.Animation;
import gfx.Assets;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import players.Dom;
import players.Kasai;
import players.Player;
import streetkombatx.Game;

/**
 *
 * @author Pack Studios
 */
public class CharSelectState extends State{
    //Cursors
    private int player1SelectionHorizontal = 0;
    private int player1SelectionVertical = 0;
    private int player2SelectionHorizontal = 0;
    private int player2SelectionVertical = 0;
    private int xPlayer1, xPlayer2, yPlayer1, yPlayer2;
    
    //Directional Inputs and Confirmation Inputs
    private boolean player1Right, player1Left, player1Up, player1Down;
    private boolean player2Right, player2Left, player2Up, player2Down;
    private boolean enter, escape;
    private boolean previousEnter = true;
    private boolean previousEscape = true;
    
    private Player player1, player2;
    private BufferedImage charSelectScreen;//images required for charSelectScreen
    private StageSelectState stageSelectState;
    private Animation dom_stance_player1, kasai_stance_player1, dom_stance_player2, kasai_stance_player2;
    
    /**
     * Method: This is a constructor method that takes in the game object parameter, uses the super method to initialize fields, and initializes the images and animations necessary for this state
     * Precondition: The game class has been initialized correctly with the correct fields, 
     * Post Condition: The CharSelectState's fields have been populated and its assets have been initialized
     * @param game: the main game object
     */
    public CharSelectState(Game game) {
        super(game);
        charSelectScreen = Assets.charSelectScreen;
        dom_stance_player1 = new Animation(66.668, Assets.dom_stance_player1);
        dom_stance_player2 = new Animation(66.668, Assets.dom_stance_player2);
        kasai_stance_player1 = new Animation(66.668, Assets.kasai_stance_player1);
        kasai_stance_player2 = new Animation(66.668, Assets.kasai_stance_player2);
    }

    /**
     * Method: This tick method continuously checks for user input and updates the cursor accordingly
     * Precondition: All key inputs are proper boolean
     * Post condition: The user's are able to choose their characters or go back, and this leads to a change in state
     */
    @Override
    public void tick() {
        dom_stance_player1.tick();
        dom_stance_player2.tick();
        kasai_stance_player1.tick();
        kasai_stance_player2.tick();
        
        player1Right = game.getKeyManager().isPlayer1_right();
        player1Left = game.getKeyManager().isPlayer1_left();
        player1Up = game.getKeyManager().isPlayer1_jump();
        player1Down = game.getKeyManager().isPlayer1_crouch();
        
        player2Right = game.getKeyManager().isPlayer2_right();
        player2Left = game.getKeyManager().isPlayer2_left();
        player2Up = game.getKeyManager().isPlayer2_jump();
        player2Down = game.getKeyManager().isPlayer2_crouch();
        
        enter = game.getKeyManager().isEnter();
        
        escape = game.getKeyManager().isEscape();
        
        if (player1Right && player1SelectionHorizontal == 0){
            player1SelectionHorizontal++;
        }
        else if (player1Left && player1SelectionHorizontal == 1){
            player1SelectionHorizontal--;
        }
        
        if (player1Up && player1SelectionVertical == 1){
            player1SelectionVertical--;
        }
        else if (player1Down && player1SelectionVertical == 0){
            player1SelectionVertical++;
        }
        
        if (player2Right && player2SelectionHorizontal == 0){
            player2SelectionHorizontal++;
        }
        else if (player2Left && player2SelectionHorizontal == 1){
            player2SelectionHorizontal--;
        }
        
        if (player2Up && player2SelectionVertical == 1){
            player2SelectionVertical--;
        }
        else if (player2Down && player2SelectionVertical == 0){
            player2SelectionVertical++;
        }
             
        if (player1SelectionHorizontal == 0){
            xPlayer1 = 487;
        }
        else if (player1SelectionHorizontal == 1){
            xPlayer1 = 642;
        }
        
        if (player1SelectionVertical == 0){
            yPlayer1 = 163;
        }
        else if (player1SelectionVertical == 1){
            yPlayer1 = 315;
        }
                
        if (player2SelectionHorizontal == 0){
            xPlayer2 = 618;
        }
        else if (player2SelectionHorizontal == 1){
            xPlayer2 = 773;
        }
        
        if (player2SelectionVertical == 0){
            yPlayer2 = 164;
        }
        else if (player2SelectionVertical == 1){
            yPlayer2 = 316;
        }
        
        confirmSelection();
        previousEnter = enter;
        previousEscape = escape;
    }

    /**
     * Method: This render method updates the visuals of the game based off of the tick method
     * Precondition: The images and animations have been loaded in correctly
     * Post condition: The game is visually updated every based on data from the tick method
     * @param gState: A graphics object used to draw images on the canvas
     */
    @Override
    public void render(Graphics gState) {
        gState.drawImage(charSelectScreen, 0, 0, null);
        gState.setColor(Color.red);
        gState.fillRect(xPlayer1, yPlayer1, 20,20);
        gState.setColor(Color.blue);
        gState.fillRect(xPlayer2, yPlayer2, 20,20);
        gState.drawImage(getCurrentAnimationState_Player1(), 29, 161, 411, 822, null);
        gState.drawImage(getCurrentAnimationState_Player2(), 861, 161, 411, 822, null);
        gState.setFont(Assets.dragonForceNum);
        gState.setColor(Color.red);
        gState.drawString(getCharTitle_Player1(), 77, 81);
        gState.drawString(getCharTitle_Player2(), 1020, 81);
    }
    
    /**
     * Method: This method locks in the choices of the users on the characters
     * Precondition: The player constructor classes initialize the objects correctly
     * Post condition: The players select a character(Dom object or Kasai object is constructed) and the game moves on to the stage select state
     */
    private void confirmSelection() {
        if (player1SelectionVertical != 1 && player2SelectionVertical != 1 && enter && !previousEnter){
            if (player1SelectionHorizontal == 0 && player1SelectionVertical == 0){
                player1 = new Dom(game, 200, 410, 150, 300, 1);
            }
            else if (player1SelectionHorizontal == 1 && player1SelectionVertical == 0){
                player1 = new Kasai(game, 200, 410, 150, 300, 1);
            }
            
            if (player2SelectionHorizontal == 0 && player2SelectionVertical == 0){
                player2 = new Dom(game, 1000, 410, 150, 300, 2); 
            }
            else if (player2SelectionHorizontal == 1 && player2SelectionVertical == 0){
                player2 = new Kasai(game, 1000, 410, 150, 300, 2);
            }   
            State.setState(new StageSelectState(game, player1, player2));
        }
        
        if (escape && !previousEscape){
            State.setState(new MenuState(game));
        }
    }
    
    //Getter Methods
   
    private BufferedImage getCurrentAnimationState_Player1() {
        if (player1SelectionHorizontal == 0 && player1SelectionVertical == 0){
            return dom_stance_player1.getCurrentFrame();
        }
        else if (player1SelectionHorizontal == 1 && player1SelectionVertical == 0){
            return kasai_stance_player1.getCurrentFrame();
        }
        else {
            return null;
        }
    }
    
    private BufferedImage getCurrentAnimationState_Player2() {
        if (player2SelectionHorizontal == 0 && player2SelectionVertical == 0){
            return dom_stance_player2.getCurrentFrame();
        }
        else if (player2SelectionHorizontal == 1 && player2SelectionVertical == 0){
            return kasai_stance_player2.getCurrentFrame();
        }
        else {
            return null;
        }
    }
    
    private String getCharTitle_Player1() {
        if (player1SelectionHorizontal == 0 && player1SelectionVertical == 0){
            return "Dom";
        }
        else if (player1SelectionHorizontal == 1 && player1SelectionVertical == 0){
            return "Kasai";
        }
        else {
            return "";
        }
    }
    
    private String getCharTitle_Player2() {
        if (player2SelectionHorizontal == 0 && player2SelectionVertical == 0){
            return "Dom";
       }
        else if (player2SelectionHorizontal == 1 && player2SelectionVertical == 0){
            return "Kasai";
        }
        else {
            return "";
        }
    }
}
