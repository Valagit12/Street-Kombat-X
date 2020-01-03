/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package players;

import java.awt.Graphics;
import streetkombatx.Game;

/**
 *
 * @author h9113
 */
public abstract class Player {
    
    protected float x, y;
    protected int health = 100;
    protected Game game;
    
    public Player(Game game, float x, float y) {
        this.x = x;
        this.y = y;
        this.game = game;
    }
    
    public abstract void tick();
    
    public abstract void render(Graphics g);
    
}
