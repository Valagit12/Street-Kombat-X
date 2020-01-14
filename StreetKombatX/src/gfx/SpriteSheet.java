/**
 * Name: Valareza Arezehgar and Brian Cho (Pack Studios)
 * Date: January 13, 2020
 * Version: 1
 * Description: This Class is responsible for creating a sprite sheet, and using the fields of this class in order to crop out sprites from said sprite sheet
 */
package gfx;

import java.awt.image.BufferedImage;

/**
 *
 * @author Pack Studios
 */
public class SpriteSheet {
    
    private int width; //The width of individual sprites in pixels
    private int height;//The height of individual sprites in pixels
    private final int numRow;// what row the desired frame resides
    
    private BufferedImage b; // an image
    
    /**
     * Method: This is a constructor method that takes in the parameters and set them equal to the fields of this class
     * Precondition: B must be a proper BufferedImage, width, height and numRow must all be proper int values
     * Post condition: The fields of this class are set equal to the parameters
     * @param b: The Buffered Image that holds the sprite sheet
     * @param width: The width of one sprite on the sheet
     * @param height: The height of one sprite on the sheet
     * @param numRow : The row that the sprite is in
     */
    public SpriteSheet(BufferedImage b, int width, int height, int numRow){
        this.b = b;
        this.width = width;
        this.height = height;
        this.numRow = numRow;
    }
    
    /**
     * Method: This method is responsible for cropping sections of the sprite sheet in order to get frames of animations
     * Precondition: TileNum has to be a proper int value that matches the index of the picture in the sprite sheet
     * @param tileNum: Represents the tile number of the desired sprite
     * @return: The sprite is returned 
     */
    public BufferedImage crop (int tileNum){
        int x = tileNum%numRow;
        int y = tileNum/numRow;
        
        return b.getSubimage(width*x, height*y, width, height);
    }
}
