/**
 * Name: Valareza Arezehgar and Brian Cho (Pack Studios)
 * Date: January 13, 2020
 * Version: 1
 * Description: This class falls under the State class, and is responsible for the intro screens of the game
 */
package states;

import gfx.Assets;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import streetkombatx.Game;

/**
 *
 * @author Pack Studios
 */
public class IntroState extends State{
    
    private int tick = 0;//counts ticks
    private int timer = 0;//timer
    
    private BufferedImage loading1;//the two images for this class
    private BufferedImage loading2;
    
    /**
     * Method: This is a constructor method that uses the game object parameter to call the super method of the state, and to initialize the two images needed for this class
     * Precondition: The images must have been initialized correctly in Assets, game object must have been initialized correctly
     * Post condition: The Intro state has called the super method to adopt its features, and initialize the loading screen images
     * @param game : the main game object
     */
    public IntroState(Game game) {
        super(game);
        loading1 = Assets.loading1;
        loading2 = Assets.loading2;
    }

    /**
     * Method: This method continuously updates as time goes on, and uses counters to know when to update, by for example, setting the state as the menu state after the timer reaches 10
     * Precondition: tick must be a proper int value, timer must be a proper int value
     * Post condition: The game has run through the loading state and moves on to the menu state
     */
    @Override
    public void tick() {
        if (tick == 60){
            timer++;
            tick = 0;
        }
        tick++;
        if (timer >= 10){
            State.setState(new MenuState(game));
        }
    }

    /**
     * Method: This method updates according to the tick method. Using time points, the method updates the picture it shows on the screen
     * Precondition: The images must have been loaded into the game correctly
     * Post condition: The images for the loading screen are presented
     * @param gState: A graphics object used to draw images on the canvas 
     */
    @Override
    public void render(Graphics gState) {
        if (timer > 0 && timer < 4){
            gState.drawImage(loading1, 0, 0, 1280, 720, null);
        }
        else if (timer > 4 && timer < 8){
            gState.drawImage(loading2, 0, 0, 1280, 720, null);
        }
        else {
            gState.setColor(Color.black);
            gState.fillRect(0, 0, 1280, 720);
        }
        
    }
}
