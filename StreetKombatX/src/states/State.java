/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package states;

import java.awt.Graphics;
import streetkombatx.Game;

/**
 *
 * @author Valareza
 */
public abstract class State {
    private static State currentState = null;
    
    public static void setState(State stateSentIn){
        currentState = stateSentIn;
    }
    
    public static State getState(){
        return currentState;
    }
    
    protected Game game;
    
    public State(Game game){
        this.game = game;
    }
    
    public abstract void tick();
    
    public abstract void render(Graphics gState);
    
}
