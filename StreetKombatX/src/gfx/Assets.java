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
    
    
    public static void init() {
        SpriteSheet kasai = new SpriteSheet(ImageLoader.loadImage("res/SpriteSheet/Kasai/Kasai_SpriteSheet.png"));
    }
    
}
