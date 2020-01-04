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

    private boolean left, right, up, down;
    private boolean isAbleToPress = true;
    private Animation stance, walk_left, walk_right, block, crouch, jump, land;

    public Kasai(Game game, float x, float y, int width, int height, int playerNum) {
        super(game, x, y, width, height, playerNum);

        if (playerNum == 1) {
            stance = new Animation(66.668, Assets.kasai_stance_player1);
            walk_left = new Animation(50.001, Assets.kasai_walk_left_player1);
            walk_right = new Animation(50.001, Assets.kasai_walk_right_player1);
            block = new Animation(50.001, Assets.kasai_block_player1);
            crouch = new Animation(50.001, Assets.kasai_crouch_player1);
            jump = new Animation(50.001, Assets.kasai_jumpUp_player1);
            land = new Animation(50.001, Assets.kasai_landing_player1);
        } else if (playerNum == 2) {
            stance = new Animation(66.668, Assets.kasai_stance_player2);
            walk_left = new Animation(50.001, Assets.kasai_walk_left_player2);
            walk_right = new Animation(50.001, Assets.kasai_walk_right_player2);
            block = new Animation(50.001, Assets.kasai_block_player2);
            crouch = new Animation(50.001, Assets.kasai_crouch_player2);
        }
    }

    @Override
    public void tick() {
        stance.tick();
        walk_left.tick();
        walk_right.tick();
        
            if (playerNum == 1) {
                if (isAbleToPress){
                up = game.getKeyManager().player1_jump;}
                down = game.getKeyManager().player1_crouch;
                right = game.getKeyManager().player1_right;
                left = game.getKeyManager().player1_left;
            } else if (playerNum == 2) {
                up = game.getKeyManager().player2_jump;
                down = game.getKeyManager().player2_crouch;
                right = game.getKeyManager().player2_right;
                left = game.getKeyManager().player2_left;
            }
        
       
        if (isJumping) {
            y -= 12;
            if (y < 350) {
                isJumping = false;
                isLanding = true;
            }
        }
        if (isLanding) {
            y += 12;
            if (y > 450) {
                isLanding = false;
                isAbleToPress = true;
            }
        }
         if (up) {
            isJumping = true;
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
        if (isJumping) {
            return jump.getCurrentFrame();
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
