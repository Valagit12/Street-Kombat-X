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
public class SpriteSheet {
    
    private final int width = 64;
    private final int height = 64;
    private final int numRow = 10;
    
    private BufferedImage b;
    
    public SpriteSheet(BufferedImage b){
        this.b = b;
    }
    
    public BufferedImage crop (int tileNum){
        int x = tileNum%numRow;
        int y = tileNum/numRow;
        
        return b.getSubimage(64*x, 64*y, 64, 64);
    }
}
