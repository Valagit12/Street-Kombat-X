/**
 * Name: Valareza Arezehgar and Brian Cho (Pack Studios)
 * Date: January 13, 2020
 * Version: 1
 * Description: This class falls under the State class, and is responsible for the End screen, after a fight.
 */
package states;

import gfx.Assets;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import players.Dom;
import players.Kasai;
import players.Player;
import streetkombatx.Game;

/**
 *
 * @author Pack Studios
 */
public class EndState extends State{
   
    private int selection = 0; 
    private boolean up, down, enter, previousDown, previousUp;
    
    private Player player1;
    private Player player2;
    
    private ArrayList<BufferedImage> endScreen;// an array list storing the endScreen pictures
    private BufferedImage[] stage; //an array storing the stage frames
    
    /**
     * Method: This constructor method uses the parameters to fill out the fields of this class using the super method, and it initializes new player objects(Dom and Kasai)
     * Precondition: game must have been initialized properly with appropriate fields, BufferedImage[] stage contains the correct images for the stage, player classes were initialized correctly with appropriate fields
     * Post condition: The EndState class has initialized its fields and created new Player objects(Kasai and Dom)
     * @param game: The main game
     * @param player1: player 1
     * @param player2: Player 2
     * @param stage: array containing the frames that the stage consists of
     */
    public EndState(Game game, Player player1, Player player2, BufferedImage[] stage) {
        super(game);
        
        if (player1.getCharTitle().equals("Kasai")){
            this.player1 = new Kasai(game, 200, 410, 150, 300, 1);
        }
        else if (player1.getCharTitle().equals("Dom")){
            this.player1 = new Dom(game, 200, 410, 150, 300, 1);
        }
        
        if (player2.getCharTitle().equals("Kasai")){
            this.player2 = new Kasai(game, 1000, 410, 150, 300, 2);
        }
        else if (player2.getCharTitle().equals("Dom")){
            this.player2 = new Dom(game, 1000, 410, 150, 300, 2);
        }
        
        this.stage = stage;
        this.endScreen = Assets.endScreen;
    }
    
    /**
     * Method: This tick method continuously checks for user input and updates the cursor accordingly
     * Precondition: the key inputs have to be proper booleans, stage array and player objects must have the correct information stored in their fields
     * Post condition: The method updates according to user input, and sets a new state depending on the user
     */
    @Override
    public void tick(){
        up = game.getKeyManager().isPlayer2_jump();
        down = game.getKeyManager().isPlayer2_crouch();
        enter = game.getKeyManager().isEnter();
            
        if (selection < 2 && down && !previousDown) {
            selection++;
        } else if (selection > 0 && up && !previousUp) {
            selection--;
        }
            
        if(enter){
            if (selection == 0){
                State.setState(new GameState(game, player1, player2, stage));
            }
            else if (selection == 1){
                State.setState(new CharSelectState(game));
            }
            else{
                State.setState(new MenuState(game));
            }
        } 
        previousDown = down;
        previousUp = up;
    }

    /**
     * Method: This method is responsible for the visual updates that come according to the tick class
     * Precondition: The images have been loaded in correctly, as bufferedImages
     * Post condition: The method updates the canvas with the appropriate images
     * @param gState : a graphics object used to draw images on the canvas
     */
    @Override
    public void render(Graphics gState) {
        if (selection == 0){
                gState.drawImage(endScreen.get(0), 0, 0, 1280, 720, null);
            }
            else if (selection == 1){
                gState.drawImage(endScreen.get(1), 0, 0, 1280, 720, null);
            }
            else {
                gState.drawImage(endScreen.get(2), 0, 0, 1280, 720, null);
            }
    }
    
}
