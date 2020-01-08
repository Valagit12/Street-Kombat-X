//Brian Cho, Valareza Arezehgar
//Jan. 13, 2020
//Culminating Performance Task
package streetkombatx;

import players.Player;
import display.Display;
import gfx.Assets;
import gfx.ImageLoader;
import input.KeyManager;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import music.Music;
import players.Dom;
import players.Kasai;
import states.EndState;
import states.GameState;
import states.MenuState;
import states.State;

/**
 *
 * @author h9113
 */
public class Game implements Runnable {

    private int width, height; //width and height of the JFrame
    private String title; //title of the JFrame

    private boolean running = false; //boolean that determines whether the thread is running

    private Thread main; //main Thread
    private Display frame;
    private BufferStrategy bs;
    private Graphics g;
    private Player player1;
    private Player player2;
    private State state;
    private KeyManager keyManager;

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
     * Method: This constructor method uses the parameter values to create a
     * game object and thread Precondition: height and width need to be proper
     * int values, title needs to be a proper string Post condition: The game
     * object is created and the thread is started
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
     * Method: This constructor method uses the parameter game to create a game
     * object and thread Precondition: height and width need to be proper int
     * values, title needs to be a proper string Post condition: The game object
     * is created and the thread is started
     *
     * @param g: A game object that will be used to populate the fields of the
     * game object that is calling an instance of this method
     */
    public Game(Game g) {
        this.width = g.width;
        this.height = g.height;
        this.title = g.title;
        frame = new Display(width, height, title);
        start();
    }

    public void initialize() {
        Assets.init();
        keyManager = new KeyManager();
        frame.getFrame().addKeyListener(keyManager);

        //temporary
        player1 = new Dom(this, 200, 410, 150, 300, 1);
        player2 = new Kasai(this, 1000, 410, 150, 300, 2);
        
        EndState endState = new EndState(this);
        GameState gameState = new GameState(this, player1, player2, endState, state);
        MenuState menuState = new MenuState(this);
        state.setState(gameState);

    }

    public void tick() {
        keyManager.tick();
        state.getState().tick();
    }

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

    public void run() {
        initialize();

        int ticks = 0; //counts the numbers of ticks that happened
        int updates = 0; //counts the number of times the screen updates
        long endTime; //measure the time when loop begins
        long startTime = System.nanoTime(); //measure the time when the previous loops has ended
        long timer = 0; //measures the time passed
        double nanoConversion = 1000000000 / 60; //converts nanoseconds to 1/60th of a second
        double changeInTime = 0; //the change in time between each ticks

        while (running) {
            endTime = System.nanoTime();
            changeInTime += (endTime - startTime) / nanoConversion;
            timer += changeInTime;
            startTime = endTime;

            if (changeInTime >= 1) {
                tick();
                ticks++;
                changeInTime--;
            }

            if (timer >= 60) {
                System.out.println("Ticks: " + ticks);
                System.out.println("FPS: " + updates);
                ticks = 0;
                updates = 0;
                timer = 0;
            }

            render();
            updates++;
        }

    }

    public void start() {
        if (running) {
            return;
        }
        running = true;
        main = new Thread(this);
        main.start();
    }

    public void stop() {
        if (!running) {
            return;
        }
        running = false;
        try {
            main.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }
    
    public State getState() {
        return state;
    }
    
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getTitle() {
        return title;
    }

    public boolean getRunning() {
        return running;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        new Game(1280, 720, "Street Kombat X"); //creates an instance of the main thread Game and starts the program
        int currentState = 2;
        new Music(currentState);
    }
}
