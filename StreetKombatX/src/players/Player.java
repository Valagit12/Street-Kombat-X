/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package players;

import collision.Rectangle;
import java.awt.Graphics;
import streetkombatx.Game;

/**
 *
 * @author h9113
 */
public abstract class Player {
    
    protected float playerNum;
    protected float x, y;
    protected float yInitial;
    protected int height, width;
    protected int health = 100;
    protected int jumpAttackIndex = 0;
    protected int state = 0;
    protected int recovery = 0;
    protected int standing2Recovery = 5;
    protected int down1Recovery = 9;
    protected int down2Recovery = 5;
    protected int jump1Recovery = 2;
    protected int standing1Recovery = 5;
    protected int standing11Recovery = 8;
    protected int standing111Recovery = 2;
    protected int specialMoveRecovery = 10;
    protected Game game;
    protected boolean left, right, up, down, blocking, one, two;
    protected boolean isWalkingLeft = false;
    protected boolean isWalkingRight = false;
    protected boolean isBlocking = false;
    protected boolean isCrouching = false;
    protected boolean isJumping = false;
    protected boolean isAbleToPress = true;
    protected boolean isJumpingOne = false;
    protected boolean isJumpingTwo = false;
    protected boolean isDownOne = false;
    protected boolean isDownTwo = false;
    protected boolean isStandingTwo = false;
    protected Rectangle hitbox;
    protected String charTitle;
    protected int[] xNameTag_Player1 = {90,260,240,110};
    protected int[] yNameTag_Player1 = {110,110,160,160};
    
    public Player(Game game, float x, float y, int width, int height, int playerNum) {
        this.playerNum = playerNum;
        this.x = x;
        this.y = y;
        this.yInitial = y;
        this.game = game;
        this.width = width;
        this.height = height;
    }
    
    public float getX(){
        return x;
    }
    
    public abstract Rectangle getHitbox();
    
    public abstract int getState();
    
    public abstract void setRecovery(int recovery);
    
    public abstract void tick();
    
    public abstract void render(Graphics g);
    
}
