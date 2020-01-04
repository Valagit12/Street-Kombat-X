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
    
    private int width;
    private int height;
    private final int numRow;
    
    private BufferedImage b;
    
    public SpriteSheet(BufferedImage b, int width, int height, int numRow){
        this.b = b;
        this.width = width;
        this.height = height;
        this.numRow = numRow;
    }
    
    public BufferedImage crop (int tileNum){
        int x = tileNum%numRow;
        int y = tileNum/numRow;
        
        
        return b.getSubimage(width*x, height*y, width, height);
    }
}
