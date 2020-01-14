/**
 * Name: Valareza Arezehgar and Brian Cho (Pack Studios)
 * Date: January 13, 2020
 * Version: 1
 * Description: This is an abstract class that results in a hierarchy, where the other states inherit this class's protected fields. State
 *  class also keeps track of what point of the game is currently being played.
 */
package states;

import java.awt.Graphics;
import streetkombatx.Game;

/**
 *
 * @author Pack Studios
 */
public abstract class State {
    private static State currentState = null; //The current state that the game is in, can be any of the other sub-classes
    protected Game game;
    
    public static void setState(State stateSentIn){
        currentState = stateSentIn;
    }
    
    public static State getState(){
        return currentState;
    }
    
    /**
     * Method: This constructor method uses the game parameter to fill out this classes game field
     * Precondition: game parameter must have been initialized as a game properly
     * Post condition: This class's game field has been initialized with the game parameter
     * @param game 
     */
    public State(Game game){
        this.game = game;
    }
    
    public abstract void tick();
    
    public abstract void render(Graphics gState);
    
}
