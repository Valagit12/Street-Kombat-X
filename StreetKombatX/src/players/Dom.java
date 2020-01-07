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
 * @author Valareza
 */
public class Dom extends Player{
    private Animation stance, walk_left, walk_right, block, crouch, jump, jump1, jump2, hit, down1, down2, standing2, standing1, standing11;

    public Dom(Game game, float x, float y, int width, int height, int playerNum) {
        super(game, x, y, width, height, playerNum);

        if (playerNum == 1) {
            stance = new Animation(66.668, Assets.dom_stance_player1);
            walk_left = new Animation(50, Assets.dom_walk_left_player1);
            walk_right = new Animation(50, Assets.dom_walk_right_player1);
            block = new Animation(20, Assets.dom_block_player1);
            crouch = new Animation(20, Assets.dom_crouch_player1);
            jump = new Animation(50, Assets.dom_jump_player1);
            jump1 = new Animation(50, Assets.dom_jump1_player1);
            jump2 = new Animation(50, Assets.dom_jump2_player1);
            hit = new Animation(50, Assets.dom_hit_player1);
            down1 = new Animation(30, Assets.dom_down1_player1);
            down2 = new Animation(70, Assets.dom_down2_player1);
            standing2 = new Animation(90, Assets.dom_2_player1);
            standing1 = new Animation(50, Assets.dom_1_player1);
            standing11 = new Animation(50, Assets.dom_11_player1);
        } else if (playerNum == 2) {
            stance = new Animation(66.668, Assets.dom_stance_player2);
            walk_left = new Animation(50, Assets.dom_walk_left_player2);
            walk_right = new Animation(50, Assets.dom_walk_right_player2);
            block = new Animation(20, Assets.dom_block_player2);
            crouch = new Animation(20, Assets.dom_crouch_player2);
            jump = new Animation(50, Assets.dom_jump_player2);
            jump1 = new Animation(50, Assets.dom_jump1_player2);
            jump2 = new Animation(50, Assets.dom_jump2_player2);
            hit = new Animation(50, Assets.dom_hit_player2);
            down1 = new Animation(30, Assets.dom_down1_player2);
            down2 = new Animation(70, Assets.dom_down2_player2);
            standing2 = new Animation(90, Assets.dom_2_player2);
            standing1 = new Animation(50, Assets.dom_1_player2);
            standing11 = new Animation(50, Assets.dom_11_player2);
        }
        
        hitbox = new Rectangle ((int)x, (int)y, width, height);
        charTitle = "Dom";
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
            isActive = false;
            jump1.setIndex(0);
            jumpAttackIndex = 0;
        }
        
        if (isHit){
            if(hit.getCurrentIndex() < 3){
                hit.tick();
            }
        }
        else if (hit.getCurrentIndex() != 0){
            isRecovering = true;
            hit.tick();
        }
        else {
            hit.setIndex(0);
            isRecovering = false;
        }
        
        if (isJumping) {
            if (jump.getCurrentIndex() < 4){
                y -= 20;
            }
            else if (jump.getCurrentIndex() == 6){
                isJumping = false;
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
                    isActive = false;
                }
                else if (jump1.getCurrentIndex() == 2 || jump1.getCurrentIndex() == 3 || jump1.getCurrentIndex() == 4){
                    isActive = true;
                    jump1.tick();
                }
                else {
                    jump1.tick();
                    isActive = false;
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
            up = false;
            one = false;
            two = false;
            if (down1.getCurrentIndex() == 7){
                down1.setIndex(0);
                isDownOne = false;
                recovery = down1Recovery;
                isActive = false;
            }
            else if (down1.getCurrentIndex() >= 3 && down1.getCurrentIndex() <= 5){
                isActive = true;
                down1.tick();
            }
            else {
                down1.tick();
                isActive = false;
            }
        }
        else if (isDownTwo){
            up = false;
            one = false;
            two = false;
            if (down2.getCurrentIndex() == 8){
                down2.setIndex(0);
                isDownTwo = false;
                recovery = down2Recovery;
                isActive = false;
            }
            else if (down2.getCurrentIndex() == 4 || down2.getCurrentIndex() == 5){
                isActive = true;
                down2.tick();
            }
            else {
                down2.tick();
                isActive = false;
            }
        }
        
        if (isStandingTwo){
            up = false;
            left = false;
            right = false;
            one = false;
            two = false;
            if(standing2.getCurrentIndex() == 7) {
                standing2.setIndex(0);
                isStandingTwo = false;
                recovery = standing2Recovery;
                isActive = false;
            }
            else if (standing2.getCurrentIndex() >= 3 && standing2.getCurrentIndex() <= 5){
                standing2.tick();
                isActive = true;
            }
            else {
                standing2.tick();
                isActive = false;
            }
        }
        
        if (isStandingOne){
            up = false;
            left = false;
            right = false;
            two = false;
            if(standing1.getCurrentIndex() == 9) {
                standing1.setIndex(0);
                isStandingOne = false;
                recovery = standing1Recovery;
                isActive = false;
                comboIndex = 0;
            }
            else if (standing1.getCurrentIndex() >= 2 && standing1.getCurrentIndex() <= 4){
                standing1.tick();
                isActive = true;
            }
            else {
                standing1.tick();
                isActive = false;
            }
        }
        
        if (isStandingOneOne){
            up = false;
            left = false;
            right = false;
            two = false;
            if(standing11.getCurrentIndex() == 15) {
                standing11.setIndex(0);
                standing1.setIndex(0);
                isStandingOneOne = false;
                recovery = standing11Recovery;
                isActive = false;
                comboIndex = 0;
            }
            else if (standing11.getCurrentIndex() >= 2 && standing11.getCurrentIndex() <= 13){
                standing11.tick();
                isActive = true;
            }
            else {
                standing11.tick();
                isActive = false;
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
            if (!previousOne){
                comboIndex++;
            }
            if (!isAbleToPress && jumpAttackIndex < 1){
                isJumpingOne = true;
            }
            else if (isCrouching){
                isDownOne = true;
            }
            else if (comboIndex == 2 && isStandingOne && standing1.getCurrentIndex() <= 6){
                isStandingOneOne = true;
                isStandingOne = false;
                standing11.setIndex(standing1.getCurrentIndex());
            }
            else{
                isStandingOne = true;
            }
            previousOne = true;
        }
        else {
            previousOne = false;
        }
        
        if (two){
            if (!isAbleToPress && jumpAttackIndex < 1){
                isJumpingTwo = true;
                isActive = true;
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
            isWalkingRight = true;
            isWalkingLeft = false;
        } else if (left) {
            x -= 3;
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
    
    public void setHealth(int healthDecrease){
        this.health -= healthDecrease;
    }

    private BufferedImage getCurrentAnimationState() {
        if (isHit || isRecovering){
            return hit.getCurrentFrame();
        }
        
        if(isStandingOne){
            return standing1.getCurrentFrame();
        }
        
        if (isStandingOneOne){
            return standing11.getCurrentFrame();
        }
        
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
        hitbox.setBottom((int)y);
    }
    
    private void drawHealth(Graphics g) {
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

}
