/**
 * Name: Valareza Arezehgar and Brian Cho (Pack Studios)
 * Date: January 13, 2020
 * Version: 1
 * Description: This Class handles all of the basic methods required to initialize the animations of the game
 */
package gfx;

import java.awt.image.BufferedImage;

/**
 *
 * @author Pack Studios
 */
public class Animation {
    
    private int index; // What point in the animation is the current frame
    private double speed; // at what speed are the animations being played
    private long lastTime, timer; //time fields that will be used later on in this class
    private BufferedImage[] frames;// an array that stores frames of animations
    
    /**
     * Method: This is a constructor method that takes in speed and an array as parameters and defines them as the fields of this class. It then populates the time fields of this class
     * Precondition: speed must be a proper double, Frames must be a proper array composed of Buffered Images
     * Post condition: The fields of this method are populated with the parameters
     * @param speed: An int representing the speed of the animation
     * @param frames: An array of Buffered Images with all of the frames of the animation
     */
    public Animation(double speed, BufferedImage[] frames){
        this.speed = speed;
        this.frames = frames;
        index = 0;
        timer = 0;
        lastTime = System.currentTimeMillis();
    }
    
    /**
     * Method: This tick method updates the values of the animation many times per second, which allows for the index number to increase according to the claas's fields
     * Precondition: timer, lastTime and speed must all be of type int, frames must be the appropriate length so that there are no null pointer exceptions
     * Post condition: The fields of the method are updated so that the animation can progress
     */
    public void tick() {
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();
        
        if (timer > speed){
            index++;
            timer = 0;
            if (index >= frames.length)
                index = 0;
        }
    }
    
    /**
     * Method: This is an accessor method that returns the current Frame of the animation
     * Precondition: frames must be populated with the correct buffered Images
     * Post condition: the current frame of the animation is returned
     * @return: the current frame of the animation
     */
    public BufferedImage getCurrentFrame() {
        return frames[index];
    }
    
    public void setIndex(int index) {
        this.index = index;
    }
    
    public int getCurrentIndex() {
        return index;
    }
    
    public BufferedImage getFrame(int index){
        return frames[index];
    }
}
