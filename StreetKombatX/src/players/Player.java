/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package players;

import collision.Rectangle;
import gfx.Animation;
import gfx.Assets;
import java.awt.Color;
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
    protected int stun = 0;
    protected int knockBack;
    protected int standing2Recovery = 10;
    protected int down1Recovery = 15;
    protected int down2Recovery = 9;
    protected int jump1Recovery = 5;
    protected int standing1Recovery = 9;
    protected int standing11Recovery = 12;
    protected int standing111Recovery = 5;
    protected int specialMoveRecovery = 18;
    protected Game game;
    protected boolean left, right, up, down, blocking, one, two, specialButton;
    protected boolean previousOne = false;
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
    protected boolean isStandingOne = false;
    protected boolean isStandingTwo = false;
    protected boolean isStandingOneOne = false;
    protected boolean isStandingOneOneOne = false;
    protected boolean isSpecial = false;
    protected boolean isActive = false;
    protected boolean isHit = false;
    protected boolean isRecovering = false;
    protected Rectangle hitbox;
    protected String charTitle;
    protected int[] xNameTag_Player1 = {90,260,240,110};
    protected int[] yNameTag_Player1 = {110,110,160,160};
    protected int[] xNameTag_Player2 = {1020,1190,1170,1040};
    protected int[] yNameTag_Player2 = {110,110,160,160};
    protected Animation stance, walk_left, walk_right, block, crouch, jump, jump1, jump2, hit, down1, down2, standing2;
    protected Animation standing1, standing11, standing111, special;
    
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
    
    public void setKnockBack(int knockBack){
        this.knockBack = knockBack;
    }
    
    public Rectangle getHitbox() {
        return hitbox;
    }
    
    public void setStun(int stun){
        this.stun = stun;
    }
    
    public void setHealth(int healthDecrease){
        this.health -= healthDecrease;
    }
    
    public int getHealth(){
        return health;
    }
    
    public String getCharTitle() {
        return charTitle;
    }
    
    public Animation getStance() {
        return stance;
    }
    
    public void drawHealth(Graphics g) {
        if (playerNum == 1){
            g.setColor(Color.black);
            g.fillPolygon(xNameTag_Player1,yNameTag_Player1, 4);
            g.setColor(Color.red);
            g.setFont(Assets.dragonForce);
            g.drawString(charTitle, 120, 150);
            g.fillRoundRect(80,60, 450, 50, 25, 25);
            g.setColor(Color.yellow);
            g.fillRoundRect(80, 60, (450*health)/100, 50, 25, 25);
        }
        else {
            g.setColor(Color.black);
            g.fillPolygon(xNameTag_Player2,yNameTag_Player2, 4);
            g.setColor(Color.red);
            g.setFont(Assets.dragonForce);
            g.drawString(charTitle, 1080, 150);
            g.fillRoundRect(750,60, 450, 50, 25, 25);
            g.setColor(Color.yellow);
            g.fillRoundRect(750+ (450-(450*health)/100), 60, (450*health)/100, 50, 25, 25);
        }
    }
    
    public int getComboFrame() {
        return standing111.getCurrentIndex();
    }
    
    public boolean isStun() {
        if (stun > 0){
            return true;
        }
        else {
            return false;
        }
    }
    
    public boolean getIsWalkingLeft() {
        return isWalkingLeft;
    }

    public void setIsWalkingLeft(boolean isWalkingLeft) {
        this.isWalkingLeft = isWalkingLeft;
    }

    public boolean getIsWalkingRight() {
        return isWalkingRight;
    }

    public void setIsWalkingRight(boolean isWalkingRight) {
        this.isWalkingRight = isWalkingRight;
    }

    public boolean getIsBlocking() {
        return isBlocking;
    }

    public void setIsBlocking(boolean isBlocking) {
        this.isBlocking = isBlocking;
    }

    public boolean getIsCrouching() {
        return isCrouching;
    }

    public void setIsCrouching(boolean isCrouching) {
        this.isCrouching = isCrouching;
    }

    public boolean getIsJumping() {
        return isJumping;
    }

    public void setIsJumping(boolean isJumping) {
        this.isJumping = isJumping;
    }

    public boolean getIsAbleToPress() {
        return isAbleToPress;
    }

    public void setIsAbleToPress(boolean isAbleToPress) {
        this.isAbleToPress = isAbleToPress;
    }

    public boolean getIsJumpingOne() {
        return isJumpingOne;
    }

    public void setIsJumpingOne(boolean isJumpingOne) {
        this.isJumpingOne = isJumpingOne;
    }

    public boolean getIsJumpingTwo() {
        return isJumpingTwo;
    }

    public void setIsJumpingTwo(boolean isJumpingTwo) {
        this.isJumpingTwo = isJumpingTwo;
    }

    public boolean getIsDownOne() {
        return isDownOne;
    }

    public void setIsDownOne(boolean isDownOne) {
        this.isDownOne = isDownOne;
    }

    public boolean getIsDownTwo() {
        return isDownTwo;
    }

    public void setIsDownTwo(boolean isDownTwo) {
        this.isDownTwo = isDownTwo;
    }

    public boolean getIsStandingOne() {
        return isStandingOne;
    }

    public void setIsStandingOne(boolean isStandingOne) {
        this.isStandingOne = isStandingOne;
    }

    public boolean getIsStandingTwo() {
        return isStandingTwo;
    }

    public void setIsStandingTwo(boolean isStandingTwo) {
        this.isStandingTwo = isStandingTwo;
    }

    public boolean getIsStandingOneOne() {
        return isStandingOneOne;
    }

    public void setIsStandingOneOne(boolean isStandingOneOne) {
        this.isStandingOneOne = isStandingOneOne;
    }

    public boolean getIsStandingOneOneOne() {
        return isStandingOneOneOne;
    }

    public void setIsStandingOneOneOne(boolean isStandingOneOneOne) {
        this.isStandingOneOneOne = isStandingOneOneOne;
    }

    public boolean getIsSpecial() {
        return isSpecial;
    }

    public void setIsSpecial(boolean isSpecial) {
        this.isSpecial = isSpecial;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean getIsHit() {
        return isHit;
    }

    public void setIsHit(boolean isHit) {
        this.isHit = isHit;
    }

    public boolean getIsRecovering() {
        return isRecovering;
    }

    public void setIsRecovering(boolean isRecovering) {
        this.isRecovering = isRecovering;
    }
    
    public abstract void tick();
    
    public abstract void render(Graphics g);
    
}
