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
    
    protected float playerNum;
    protected float x, y;
    protected int height, width;
    protected int health = 100;
    protected Game game;
    protected boolean left, right, up, down, blocking;
    protected boolean isWalkingLeft = false;
    protected boolean isWalkingRight = false;
    protected boolean isBlocking = false;
    protected boolean isCrouching = false;
    protected boolean isJumping = false;
    protected boolean isLanding = false;
    protected boolean isAbleToPress = true;
    
    public Player(Game game, float x, float y, int width, int height, int playerNum) {
        this.playerNum = playerNum;
        this.x = x;
        this.y = y;
        this.game = game;
        this.width = width;
        this.height = height;
    }
    
    public float getX(){
        return x;
    }
    
    public abstract void tick();
    
    public abstract void render(Graphics g);
    
}
