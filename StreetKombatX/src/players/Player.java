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
    protected int comboIndex = 0;
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
    protected boolean previousOne = false;
    public boolean isWalkingLeft = false;
    public boolean isWalkingRight = false;
    public boolean isBlocking = false;
    public boolean isCrouching = false;
    public boolean isJumping = false;
    public boolean isAbleToPress = true;
    public boolean isJumpingOne = false;
    public boolean isJumpingTwo = false;
    public boolean isDownOne = false;
    public boolean isDownTwo = false;
    public boolean isStandingOne = false;
    public boolean isStandingTwo = false;
    public boolean isStandingOneOne = false;
    public boolean isActive = false;
    public boolean isHit = false;
    public boolean isRecovering = false;
    protected Rectangle hitbox;
    public String charTitle;
    protected int[] xNameTag_Player1 = {90,260,240,110};
    protected int[] yNameTag_Player1 = {110,110,160,160};
    protected int[] xNameTag_Player2 = {1020,1190,1170,1040};
    protected int[] yNameTag_Player2 = {110,110,160,160};
    
    public Player(Game game, float x, float y, int width, int height, int playerNum) {
        this.playerNum = playerNum;
        this.x = x;
        this.y = y;
        this.yInitial = y;
        this.game = game;
        this.width = width;
        this.height = height;
    }
    
    public int getWidth(){
        return width;
    }
    
    public float getX(){
        return x;
    }
    
    public void setX(float x) {
        this.x = x;
    }
    
    public abstract Rectangle getHitbox();
    
    public abstract int getState();
    
    public abstract void setRecovery(int recovery);
    
    public abstract void setHealth(int healthDecrease);
    
    public abstract void tick();
    
    public abstract void render(Graphics g);
    
}
