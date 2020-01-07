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
import java.awt.image.BufferedImage;
import streetkombatx.Game;

/**
 *
 * @author h9113
 */
public class Kasai extends Player {

    private Animation stance, walk_left, walk_right, block, crouch, jump, jump1, jump2, hit, down1, down2, standing2;

    public Kasai(Game game, float x, float y, int width, int height, int playerNum) {
        super(game, x, y, width, height, playerNum);

        if (playerNum == 1) {
            stance = new Animation(66.668, Assets.kasai_stance_player1);
            walk_left = new Animation(50.001, Assets.kasai_walk_left_player1);
            walk_right = new Animation(50.001, Assets.kasai_walk_right_player1);
            block = new Animation(20, Assets.kasai_block_player1);
            crouch = new Animation(20, Assets.kasai_crouch_player1);
            jump = new Animation(50.001, Assets.kasai_jump_player1);
            jump1 = new Animation(50.001, Assets.kasai_jump1_player1);
            jump2 = new Animation(50.001, Assets.kasai_jump2_player1);
            hit = new Animation(50.001, Assets.kasai_hit_player1);
            down1 = new Animation(50.001, Assets.kasai_down1_player1);
            down2 = new Animation(50.001, Assets.kasai_down2_player1);
            standing2 = new Animation(50.001, Assets.kasai_2_player1);
        } else if (playerNum == 2) {
            stance = new Animation(66.668, Assets.kasai_stance_player2);
            walk_left = new Animation(50.001, Assets.kasai_walk_left_player2);
            walk_right = new Animation(50.001, Assets.kasai_walk_right_player2);
            block = new Animation(20, Assets.kasai_block_player2);
            crouch = new Animation(20, Assets.kasai_crouch_player2);
            jump = new Animation(50.001, Assets.kasai_jump_player2);
            jump1 = new Animation(50.001, Assets.kasai_jump1_player2);
            jump2 = new Animation(50.001, Assets.kasai_jump2_player2);
            hit = new Animation(50.001, Assets.kasai_hit_player2);
            down1 = new Animation(50.001, Assets.kasai_down1_player2);
            down2 = new Animation(50.001, Assets.kasai_down2_player2);
            standing2 = new Animation(50.001, Assets.kasai_2_player2);
        }
        
        hitbox = new Rectangle ((int)x, (int)y + height, width, height);
        charTitle = "Kasai";
    }

    @Override
    public void tick() {
        setHitbox();
        stance.tick();
        walk_left.tick();
        walk_right.tick();
        
        if (playerNum == 1) {
            if (isAbleToPress){
                up = game.getKeyManager().player1_jump;
                down = game.getKeyManager().player1_crouch;
            }
            right = game.getKeyManager().player1_right;
            left = game.getKeyManager().player1_left;
            blocking = game.getKeyManager().player1_block;
            one = game.getKeyManager().player1_1;
            two = game.getKeyManager().player1_2;
        } else if (playerNum == 2) {
            if (isAbleToPress){
                up = game.getKeyManager().player2_jump;
                down = game.getKeyManager().player2_crouch;
            }
            right = game.getKeyManager().player2_right;
            left = game.getKeyManager().player2_left;
            blocking = game.getKeyManager().player2_block;
            one = game.getKeyManager().player2_1;
            two = game.getKeyManager().player2_2;
        }
        
        if (recovery > 0){
            up = false;
            down = false;
            right = false;
            left = false;
            blocking = false;
            one = false;
            two = false;
            recovery--;
        }
            
        if (y < yInitial){
            isAbleToPress = false;
            y += 7;
            if (y > yInitial){
                y = yInitial;
            }
        }
        else if (y == yInitial) {
            isAbleToPress = true;
            isJumpingOne = false;
            isJumpingTwo = false;
            jump1.setIndex(0);
            jumpAttackIndex = 0;
        }
        
        if (isJumping) {
            if (jump.getCurrentIndex() < 4){
                y -= 20;
            }
            else if (jump.getCurrentIndex() == 6){
                isJumping = false;
                isJumpingOne = false;
                jump.setIndex(0);
            }
            jump.tick();
        }
        
        if (!isAbleToPress){
            if (isJumpingOne){
                if (jump1.getCurrentIndex() == 5){
                    isJumpingOne = false;
                    jump1.setIndex(0);
                    jumpAttackIndex++;
                    recovery = jump1Recovery;
                }
                else {
                    jump1.tick();
                }
            }
        }
        
        if (isBlocking) {
            right = false;
            left = false;
            up = false;
            if (blocking){
                if (block.getCurrentIndex() < 4) {
                    block.tick();
                }
            }
            else {
                if (block.getCurrentIndex() != 6){
                    block.tick();
                }
                else {
                    block.setIndex(0);
                }
            }
        }
        
        if (isCrouching) {
            right = false;
            left = false;
            up = false;
            if (down) {
                if (crouch.getCurrentIndex() < 5){
                    crouch.tick();
                }
            }
            else {
                if (crouch.getCurrentIndex() != 7) {
                    crouch.tick();
                }
                else {
                    crouch.setIndex(0);
                }
            }
        }
        
        if (isDownOne){
            if (down1.getCurrentIndex() != 7){
                down1.tick();
            }
            else {
                down1.setIndex(0);
                isDownOne = false;
                recovery = down1Recovery;
            }
        }
        else if (isDownTwo){
            if (down2.getCurrentIndex() != 8){
                down2.tick();
            }
            else {
                down2.setIndex(0);
                isDownTwo = false;
                recovery = down2Recovery;
            }
        }
        
        if (isStandingTwo){
            if(standing2.getCurrentIndex() != 7) {
                standing2.tick();
            }
            else {
                standing2.setIndex(0);
                isStandingTwo = false;
                recovery = standing2Recovery;
            }
        }
        
        if (up) {
            isJumping = true;
            isAbleToPress = false;
            up = false;
        }
        
        if (blocking || block.getCurrentIndex() != 0) {
            isBlocking = true;
        }
        else {
            isBlocking = false;
        }
        
        if (down || crouch.getCurrentIndex() != 0) {
            isCrouching = true;
        }
        else {
            isCrouching = false;
        }
        
        if (one) {
            if (!isAbleToPress && jumpAttackIndex < 1){
                isJumpingOne = true;
            }
            else if (isCrouching){
                isDownOne = true;
            }
            else{
                
            }
        }
        
        if (two){
            if (!isAbleToPress && jumpAttackIndex < 1){
                isJumpingTwo = true;
            }
            else if (isCrouching){
                isDownTwo = true;
            }
            else {
                isStandingTwo = true;
            }
        }
       
        
        if (right) {
            x += 3;
            if (x > 1160) {
                x = 1160;
            }
            isWalkingRight = true;
            isWalkingLeft = false;
        } else if (left) {
            x -= 3;
            if (x < 0) {
                x = 0;
            }
            isWalkingLeft = true;
            isWalkingRight = false;
        } else {
            isWalkingLeft = false;
            isWalkingRight = false;
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationState(), (int) x, (int) y, width, height, null);
        drawHealth(g);
    }
    
    public Rectangle getHitbox() {
        return hitbox;
    }
    
    public int getState() {
        if (isBlocking){
            state = 1;
        }
        else if (isCrouching){
            state = 2;
        }
        else {
            state = 0;
        }
        return state;
    }
    
    public void setRecovery(int recovery) {
        this.recovery = recovery;
    }

    private BufferedImage getCurrentAnimationState() {
        if(isStandingTwo){
            return standing2.getCurrentFrame();
        }
        
        if (isDownOne) {
            return down1.getCurrentFrame();
        }
        
        if (isDownTwo) {
            return down2.getCurrentFrame();
        }
        
        if (isJumpingTwo){
            return jump2.getCurrentFrame();
        }
        
        if (isJumpingOne){
            return jump1.getCurrentFrame();
        }
        
        if (isJumping) {
            return jump.getCurrentFrame();
        }
        
        if (isBlocking) {
            return block.getCurrentFrame();
        }
        
        if (isCrouching) {
            return crouch.getCurrentFrame();
        }
        
        if (isWalkingLeft) {
            return walk_left.getCurrentFrame();
        } else if (isWalkingRight) {
            return walk_right.getCurrentFrame();
        } else {
            return stance.getCurrentFrame();
        }
    }
    
    private void setHitbox() {
        hitbox.setLeft((int)x);
        hitbox.setBottom((int)y + height);
    }
    
    private void drawHealth(Graphics g) {
        if (playerNum == 1){
            g.setColor(Color.black);
            g.fillPolygon(xNameTag_Player1,yNameTag_Player1, 4);
            g.setColor(Color.red);
            g.drawString(charTitle, 100, 200);
            g.fillRoundRect(80,60, 450, 50, 25, 25);
            g.setColor(Color.yellow);
            g.fillRoundRect(80, 60, (450*health)/100, 50, 25, 25);
        }
    }

}
