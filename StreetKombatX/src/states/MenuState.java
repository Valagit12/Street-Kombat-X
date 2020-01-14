/**
 * Name: Valareza Arezehgar and Brian Cho (Pack Studios)
 * Date: January 13, 2020
 * Version: 1
 * Description: This class falls under the State class, and is responsible for the menu select screen at the beginning of the game. 
 */
package states;

import gfx.Assets;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import streetkombatx.Game;

/**
 *
 * @ Pack Studios
 */
public class MenuState extends State {

    private int timer = 0;// timer
    private int selection = 0; // what the cursor is currently on
    private boolean up, down, enter, previousUp, previousDown;// directional inputs for the cursor as well as its previous movements
    private boolean previousEnter = true;// represents if already have pressed enter
    
    private BufferedImage menu1;// three different pictures for the menu screen
    private BufferedImage menu2;
    private BufferedImage menu3;

    /**
     * Method: This is a constructor method that takes in a game parameter, and calls the super method with this game parameter. It also initializes the three menu pictures
     * Precondition: Game must have been initialized with appropriate fields, Assets.menu must return the appropriate images
     * Post condition: MenuState has called the super method to adopt the State features, and has initialized menu pictures
     * @param game 
     */
    public MenuState(Game game) {
        super(game);
        this.menu1 = Assets.menu.get(0);
        this.menu2 = Assets.menu.get(1);
        this.menu3 = Assets.menu.get(2);
    }

    /**
     * Method: This tick method continuously checks for user input and updates the cursor location accordingly. If a certain menu is selected, then the State is reset to the appropriate state.
     * Precondition: The key inputs must be of type boolean
     * Post condition: The method is refreshes as the user gives input, and will open a new state should the situation arise
     */
    @Override
    public void tick() {
        up = game.getKeyManager().isPlayer2_jump();
        down = game.getKeyManager().isPlayer2_crouch();
        enter = game.getKeyManager().isEnter();

        if (selection < 2 && down && !previousDown) {
            selection++;
        } else if (selection > 0 && up && !previousUp) {
            selection--;
        }

        if (enter && !previousEnter) {
            if (selection == 0) {
                State.setState(new CharSelectState(game));
            } else if (selection == 1) {
                State.setState(new HelpState(game, this));
            } else {
                new NameEnterState();
            }
        }

        previousUp = up;
        previousDown = down;
        previousEnter = enter;
    }

    /**
     * Method: This method is the render method that updates the visuals of the game according to the Tick method
     * Precondition: All menu buffered Images must have been initialized properly, graphics gState must have been initialized properly
     * Post condition: A visual output is refreshed to reflect the nature of the new updates the tick method makes
     * @param gState: A graphics object used to draw images on the canvas
     */
    @Override
    public void render(Graphics gState) {
        if (selection == 0) {
            gState.drawImage(menu1, 0, 0, 1280, 720, null);
        } else if (selection == 1) {
            gState.drawImage(menu2, 0, 0, 1280, 720, null);
        } else {
            gState.drawImage(menu3, 0, 0, 1280, 720, null);
        }
    }
}
