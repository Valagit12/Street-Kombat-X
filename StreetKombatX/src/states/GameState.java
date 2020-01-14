/**
 * Name: Valareza Arezehgar and Brian Cho (Pack Studios)
 * Date: January 13, 2020
 * Version: 1
 * Description: This class falls under the State class, and is responsible for the actual game play portion of our game
 */
package states;

import collision.CollisionCheck;
import gfx.Animation;
import gfx.Assets;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import streetkombatx.Game;
import players.Player;
import states.EndState;
/**
 *
 * @author Pack Studios
 */
public class GameState extends State {
    private Player player1;// players
    private Player player2;
    
    private Animation background;//stage
    private CollisionCheck collisionCheck;//check collision to know if getting hit
    private int time = 93;//total time in game
    private int ticks = 0;
    private int endTimer = 0;
    private int selection = 0;
    private String winner;// final winner of match
    private boolean escape, up, down, enter;//keys
    private boolean previousUp = false;
    private boolean previousDown = false;
    private boolean pause = false;
    
    private ArrayList<BufferedImage> pauseScreen;//loaded in pause screen buffer images
    private BufferedImage[] stage;
    
    /**
     * Method: This method uses its parameters to populate this class's fields, and load its stage animations, and pause screen pictures 
     * Precondition: collisionCheck is correct type of object, game has been initialized correctly with appropriate fields, stage Array has correct images loaded
     * Post condition: The GameState class's fields are populated and the assets have been loaded
     * @param game: The main game
     * @param player1: Player 1
     * @param player2: Player 2
     * @param stage: The array storing the frames for the stage
     */
    public GameState(Game game, Player player1, Player player2, BufferedImage[] stage){
        super(game);
        this.player1 = player1;
        this.player2 = player2;
        this.stage = stage;
        background = new Animation(60, this.stage);
        collisionCheck = new CollisionCheck(player1, player2);
        pauseScreen = Assets.pause;
    }
    
    /**
     * Method: This method continuously checks for user input, and updates the fields accordingly. At the end, the winner of the game is determined by the surplus of health points.
     * Precondition: All key inputs must be of appropriate type boolean, collisionCheck must have been initialized correctly with the appropriate fields
     * Post condition: The game data is updated concurrently as it is receiving input from the user, handling the numerical aspect of the game
     */
    @Override
    public void tick() {
        
        escape = game.getKeyManager().isEscape();
        
        if (escape && !(player1.getHealth() <= 0 || player2.getHealth() <= 0 || time <= 0)){
            pause = true;
        }
        
        if (pause){
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
                    pause = false;
                }
                else if (selection == 1){
                    State.setState(new HelpState(game, this));
                }
                else{
                    State.setState(new MenuState(game));
                }
            } 
            previousDown = down;
            previousUp = up;
        }        
        else{
            System.out.println(time);
            if (time > 0 && ticks == 60){
                time--;
                ticks = 0;
            }
            
            ticks++;
            
            if (time <= 90){
                background.tick();
                player1.tick();
                player2.tick();
                collisionCheck.checkCollision(time);

                if (time <= 0 || player1.getHealth() <= 0 || player2.getHealth() <= 0){
                    if (player1.getHealth() < player2.getHealth()){
                        winner = player2.getCharTitle() + " wins";
                    }
                    else if (player2.getHealth() < player1.getHealth()){
                        winner = player1.getCharTitle() + " wins";
                    } else {
                        winner = "Tie Game";
                    }

                    if (endTimer >= 5){
                        State.setState(new EndState(game, player1, player2, stage));
                    }

                    if (ticks >= 60){
                        endTimer++;
                    }
                }
            }
        }
    }

    /**
     * Method: This render method updates the visual aspect of the game, using animations etc... according to the tick method
     * Precondition: All images and animations have been loaded in correctly(As buffered images)
     * Post condition: The game's visual appearance is updated
     * @param g: a graphics object used to draw images on the canvas
     */
    @Override
    public void render(Graphics g) {
        g.drawImage(background.getCurrentFrame(), 0, 0, null);
        player1.render(g);
        player2.render(g);
        g.setFont(Assets.dragonForceNum);
        g.setColor(Color.red);
        if (time > 90){
            g.drawString(Integer.toString(90), 615, 113);
            if (time > 91){
                g.drawString("READY", 550, 350);
            }
            else {
                g.drawString("FIGHT", 550, 350);
            }
        }
        else {
            g.drawString(Integer.toString(time), 615, 113);
        }
        if (player1.getHealth() <= 0 || player2.getHealth() <= 0 || time <= 0){
            endScreen(g, winner);
        }
        
        if (pause){
            if (selection == 0){
                g.drawImage(pauseScreen.get(0), 0, 0, 1280, 720, null);
            }
            else if (selection == 1){
                g.drawImage(pauseScreen.get(1), 0, 0, 1280, 720, null);
            }
            else {
                g.drawImage(pauseScreen.get(2), 0, 0, 1280, 720, null);
            }
        }
    }
    
    /**
     * Method: This method shows the match ending winner of the game
     * Precondition: graphics g has been initialized correctly, charTitle is a proper string
     * Post condition: The victor of the game is printed
     * @param g: a graphics object that is used to draw images on the canvas
     * @param charTitle: The name of the winning character
     */
    public void endScreen(Graphics g, String charTitle) {
        g.setFont(Assets.dragonForceEndScreen);
        g.setColor(Color.red);
        g.drawString(winner, 490, 350);
    }
    

}
