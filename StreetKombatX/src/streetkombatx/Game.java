
package streetkombatx;

import display.Display;

/**
 *
 * @author h9113
 */
public class Game implements Runnable{
    
    private int width, height;
    private String title;
    
    public Game(int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;
        System.out.println("gay");
        
        Display frame = new Display(width, height, title);
    }
    
    public void initialize() {
        
    }
    
    public void tick() {
        
    }
    
    public void render() {
        
    }
    
    public void run() {
        
    }
    
    public void start() {
        
    }
    
    public void stop() {
        
    }
    
    public static void main(String[] args) {
        new Game (1920, 1080, "Street Kombat X"); //creates an instance of the main thread Game and starts the program
    }
}
