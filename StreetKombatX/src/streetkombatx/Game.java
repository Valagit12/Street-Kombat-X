//Brian Cho, Valareza Arezehgar
//Jan. 13, 2020
//Culminating Performance Task
package streetkombatx;

import display.Display;

/**
 *
 * @author h9113
 */
public class Game implements Runnable{
    
    private int width, height; //width and height of the JFrame
    private String title; //title of the JFrame
    
    private boolean running = false; //boolean that determines whether the thread is running
    
    private Thread main; //main Thread
    
    public Game(int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;
        
        Display frame = new Display(width, height, title);
    }
    
    public void initialize() {
        
    }
    
    public void tick() {
        
    }
    
    public void render() {
        
    }
    
    public void run() {
        initialize();
    }
    
    public void start() {
        if (running)
            return;
        running = true;
        main = new Thread(this);
    }
    
    public void stop() {
        if (!running)
            return;
        running = false;
        try {
            main.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        new Game (1920, 1080, "Street Kombat X"); //creates an instance of the main thread Game and starts the program
    }
}
