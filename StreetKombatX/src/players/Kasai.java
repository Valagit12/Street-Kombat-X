/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package players;

import gfx.Animation;
import gfx.Assets;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import streetkombatx.Game;

/**
 *
 * @author h9113
 */
public class Kasai extends Player {

    private Animation stance, walk_left, walk_right, block, crouch, jump, jump1, jump2, hit, down1, down2;

    public Kasai(Game game, float x, float y, int width, int height, int playerNum) {
        super(game, x, y, width, height, playerNum);

        if (playerNum == 1) {
            stance = new Animation(66.668, Assets.kasai_stance_player1);
            walk_left = new Animation(50.001, Assets.kasai_walk_left_player1);
            walk_right = new Animation(50.001, Assets.kasai_walk_right_player1);
            block = new Animation(50.001, Assets.kasai_block_player1);
            crouch = new Animation(20, Assets.kasai_crouch_player1);
            jump = new Animation(50.001, Assets.kasai_jump_player1);
            jump1 = new Animation(60, Assets.kasai_jump1_player1);
            jump2 = new Animation(50.001, Assets.kasai_jump2_player1);
            hit = new Animation(50.001, Assets.kasai_hit_player1);
            down1 = new Animation(50.001, Assets.kasai_down1_player1);
            down2 = new Animation(50.001, Assets.kasai_down2_player1);
        } else if (playerNum == 2) {
            stance = new Animation(66.668, Assets.kasai_stance_player2);
            walk_left = new Animation(50.001, Assets.kasai_walk_left_player2);
            walk_right = new Animation(50.001, Assets.kasai_walk_right_player2);
            block = new Animation(50.001, Assets.kasai_block_player2);
            crouch = new Animation(20, Assets.kasai_crouch_player2);
            jump = new Animation(50.001, Assets.kasai_jump_player2);
            jump1 = new Animation(50.001, Assets.kasai_jump1_player2);
            jump2 = new Animation(50.001, Assets.kasai_jump2_player2);
            hit = new Animation(50.001, Assets.kasai_hit_player2);
            down1 = new Animation(50.001, Assets.kasai_down1_player2);
            down2 = new Animation(50.001, Assets.kasai_down2_player2);
        }
        
    }

    @Override
    public void tick() {
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
            
        if (y < 460){
            isAbleToPress = false;
            y += 7;
            if (y > 460){
                y = 460;
            }
        }
        else if (y == 460) {
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
                if (isDownOne){
                    if (down1.getCurrentIndex() != 7){
                        down1.tick();
                    }
                    else {
                        down1.setIndex(0);
                        isDownOne = false;
                    }
                }
                else if (isDownTwo){
                    if (down2.getCurrentIndex() != 8){
                        down2.tick();
                    }
                    else {
                        down2.setIndex(0);
                        isDownTwo = false;
                    }
                }
            }
            else {
                if (crouch.getCurrentIndex() != 7) {
                    crouch.tick();
                }
                else {
                    isDownTwo = false;
                    isDownOne = false;
                    crouch.setIndex(0);
                    down1.setIndex(0);
                    down2.setIndex(0);
                }
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
        
        if (one && !isAbleToPress){
            if (jumpAttackIndex < 1)
                isJumpingOne = true;
        }
        else if (one && isCrouching){
            isDownOne = true;
        }
        else if (two && !isAbleToPress){
            if (jumpAttackIndex < 1)
                isJumpingTwo = true;
        }
        else if (two && isCrouching){
            isDownTwo = true;
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
    }

    private BufferedImage getCurrentAnimationState() {
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

}
