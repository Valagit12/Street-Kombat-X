/**
 * Name: Brian Cho, Valareza Arezehgar (Pack Studios)
 * Date: January 13, 2020
 * Version: 1
 * Description: The main class of the program. This class implements runnable so that it can work concurrently alongside other classes as threads.
 * 
 */
package streetkombatx;

import players.Player;
import display.Display;
import gfx.Assets;
import input.KeyManager;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import music.Music;
import states.IntroState;
import states.State;

/**
 * 
 * @author Pack Studios
 */
public class Game implements Runnable {
    
    // Fields

    private int width, height; //width and height of the JFrame
    private String title; //title of the JFrame

    private boolean running = false; //boolean that determines whether the thread is running

    private Thread main; //main Thread
    private Display frame;//the window for the game
    private BufferStrategy bs;// how to buffer the assets and graphics within the game
    private Graphics g;// graphics object that handles all of the rendering that occurs in the game
    private State state;// a state object that identifies at what point in the game is currently being played
    private KeyManager keyManager;// a keyManager object that is being used read keyboard inputs

    // Constructor Methods
    
    /**
     * Method: This method overwrites java's default constructor method for the
     * Game object Precondition: height and width need to be proper int values,
     * title needs to be a proper string Post condition: The game object is
     * created and the thread is started
     */
    public Game() {
        this.width = 0;
        this.height = 0;
        this.title = null;
        frame = new Display(width, height, title);
        start();
    }

    /**
     * Method: This constructor method uses the parameter values to create a game object and thread 
     * Precondition: height and width need to be proper int values, title needs to be a proper string 
     * Post condition: The game object is created and the thread is started
     *
     * @param width: an int value representing the width of the canvas in pixels
     * @param height: an int value representing the height of the canvas in
     * pixels
     * @param title: an String that holds the title of the game
     */
    public Game(int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;
        frame = new Display(width, height, title);
        start();
    }

    /**
     * Method: This constructor method uses the parameter game to create a game object and thread 
     * Precondition: height and width need to be proper int values, title needs to be a proper string 
     * Post condition: The game object is created and the thread is started
     *
     * @param g: A game object that will be used to populate the fields of the game object that is calling an instance of this method
     */
    public Game(Game g) {
        this.width = g.width;
        this.height = g.height;
        this.title = g.title;
        frame = new Display(width, height, title);
        start();
    }

    /**
     * Method: This method exists as a means of creating instances for the various fields within this class. For example, it initializes the assets
     * by calling the appropriate method, it initializes the key manager, creates an instance if an introState and sets the State object as introState
     * Precondition: Assets must have an init method that loads all of the images, etc properly
     * Post condition: The different fields in the game have been initialized
     */
    public void initialize() {
        Assets.init();
        keyManager = new KeyManager();
        frame.getFrame().addKeyListener(keyManager);
         
        IntroState introState = new IntroState(this);
        State.setState(introState);
    }

    /**
     * Method: This method exists to be re-updated 60 times per second. It serves as a physical refresh of the game, so that the game can check for 
     * keyboard inputs, and run the appropriate state tick method many times per second.
     * Precondition: The keyManager tick method must be checking for appropriate input, the correlating state tick method must be running appropriate code,
     * both tick methods must exist and be in working condition
     * Post condition: The game continuously checks for user input, while the state tick method is run to essentially, run the game
     */
    public void tick() {
        keyManager.tick();
        state.getState().tick();
    }
    
    // Instance Methods
    
    /**
     * Method: The render method updates as many times as the tick method, but it's purpose is to display visual information that the user can comprehend.
     * Essentially, it is responsible for the visuals of the game, and updates continuously.
     * Precondition: frame must have a proper getCanvas method that returns an appropriate canvas, g must be a proper graphic object
     * Post condition: The canvas, frame will be cleared of all previous drawings, and the new updated frame will be printed based on the current 
     * state of the game
     */
    public void render() {
        bs = frame.getCanvas().getBufferStrategy();
        if (bs == null) {
            frame.getCanvas().createBufferStrategy(3);
            return;
        } 

        g = bs.getDrawGraphics();

        g.clearRect(0, 0, width, height);

        state.getState().render(g);

        bs.show();
        g.dispose();
    }

    /**
     * Method: The run method handles most of the function of the game while the game is being played. First, the fields are initialized, then an 
     * infinite while loop occurs (since running will never be false once it has been made true) which keeps running the game's tick and render methods
     * Precondition: initialize() must have initialized all of the correct fields. The following tick and render methods must have correct code to 
     * run the program properly.
     * Post condition: The game has started running infinitely, until the program is closed.
     */
    public void run() {
       
        initialize();

        /*
        int ticks = 0; //counts the numbers of ticks that happened
        int updates = 0; //counts the number of times the screen updates
        long endTime; //measure the time when loop begins
        long startTime = System.nanoTime(); //measure the time when the previous loops has ended
        long timer = 0; //measures the time passed
        double nanoConversion = 1000000000 / 60; //converts nanoseconds to 1/60th of a second
        double changeInTime = 0; //the change in time between each ticks
        */
        
        //The above code was a tool used for testing purposes. The correlating uses of these variables have been commented out below  
         
        while (running) {//An infinite loop to keep the game running, until it is stopped properly by the usr, by closing the window
         /* endTime = System.nanoTime();
            changeInTime += (endTime - startTime) / nanoConversion;
            timer += changeInTime;
            startTime = endTime;

            if (changeInTime >= 1) {
                ticks++;
                changeInTime--;
            }

            if (timer >= 60) {
                System.out.println("Ticks: " + ticks);
                System.out.println("FPS: " + updates);
                ticks = 0;
                updates = 0;
                timer = 0;
            }*/
         //updates++;
         tick();
         render();
        }
    }
    
    /**
     * Method: This method marks the beginning of the program. It starts off the main thread, and checks if the game is already running. If so, then
     * it returns nothing but if not, then the method would set the field running as true, to begin the program.
     * Precondition: The variable running must be a proper boolean type, 
     * Post condition: The main thread and consequently the game has started.
     */
    public void start() {
        if (running) {
            return;
        }
        running = true;
        main = new Thread(this);
        main.start();
    }
    
    // Accessor Methods
    
   /**
    * Method: This is a simple accessor method that returns the keyManager used in this class
    * Precondition: keyManager must have been a proper field within the class.
    * Post condition: keyManager will have been returned
    * @return: keyManager, the object that is responsible for checking user input, will be returned
    */
    public KeyManager getKeyManager() {
        return keyManager;
    }    

    //Main Method
    
    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {//These exceptions are useful for the instance of the music object within this method
        Game mainGame = new Game(1280, 720, "Street Kombat X"); //creates an instance of the main thread Game and starts the program
        Music mainMusic = new Music();
    }

}
