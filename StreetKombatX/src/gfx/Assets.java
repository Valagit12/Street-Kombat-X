/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gfx;

import java.awt.image.BufferedImage;

/**
 *
 * @author h9113
 */
public class Assets {
    
    public static BufferedImage[] kasai_stance, kasai_walk_left, kasai_walk_right, kasai_block, kasai_crouch;
    
    public static void init() {
        SpriteSheet kasai = new SpriteSheet(ImageLoader.loadImage("res/SpriteSheet/Kasai/Kasai_SpriteSheet.png"));
        
        kasai_stance = new BufferedImage[8];
        kasai_walk_left = new BufferedImage[10];
        kasai_walk_right = new BufferedImage[10];
        kasai_block = new BufferedImage[7];
        kasai_crouch = new BufferedImage[8];
        
        for (int i = 0; i < kasai_stance.length; i++){
            kasai_stance[i] = kasai.crop(i);
        }
        
        for (int i = 0; i < kasai_walk_left.length; i++){
            kasai_walk_left[i] = kasai.crop(i+14);
            kasai_walk_right[kasai_walk_right.length - 1 - i] = kasai_walk_left[i];
        }
        
        for (int i = 0; i < kasai_block.length; i++){
            kasai_block[i] = kasai.crop(i+14*2);
        }
        
        for (int i = 0; i < kasai_crouch.length; i++){
            kasai_crouch[i] = kasai.crop(i+14*3);
        }
    }
    
}
