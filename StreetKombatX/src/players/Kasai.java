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
public class Kasai extends Player{
    
    private Animation stance, walk_left, walk_right, block, crouch;
    
    public Kasai(Game game, float x, float y, int width,int height) {
        super(game, x, y, width, height);
        
        stance = new Animation(66.668, Assets.kasai_stance);
        walk_left = new Animation(50.001, Assets.kasai_walk_left);
        walk_right = new Animation(50.001, Assets.kasai_walk_right);
    }

    @Override
    public void tick() {
        stance.tick();
        walk_left.tick();
        walk_right.tick();
        if (game.getKeyManager().right){
            x += 3;
            if (x > 1160){
                x = 1160;
            }
            isWalkingRight = true;
            isWalkingLeft = false;
        }
        else if (game.getKeyManager().left){
            x -= 3;
            if (x < 0){
                x = 0;
            }
            isWalkingLeft = true;
            isWalkingRight = false;
        }
        else {
            isWalkingLeft = false;
            isWalkingRight = false;
        }
        System.out.println(x);
        System.out.println(y);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationState(), (int) x, (int) y, width, height, null);
    }
    
    private BufferedImage getCurrentAnimationState() {
        if (isWalkingLeft)
            return walk_left.getCurrentFrame();
        else if (isWalkingRight)
            return walk_right.getCurrentFrame();
        else 
            return stance.getCurrentFrame();
    }
    
}
