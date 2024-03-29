/**
 * Name: Valareza Arezehgar, Brian Cho (Pack Studios)
 * Date: January 13, 2020
 * Version: 1
 * Description: This class extends the JFrame class which means it inherits the JFrame data and so, creates a JFrame but as a canvas for the game based on a variety of fields
 */
package display;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author Pack Studios
 */
public class Display extends JFrame {
    
    private Canvas canvas;//The canvas that can be drawn on in the JFrame
    private int width; //Width of the Canvas JFrame
    private int height;//Height of the Canvas JFrame
    private String title;// Title of the Canvas JFrame
    
    //Constructor Methods
    
    /**
     * Method: This method overrides the default constructor java uses to populate the fields of the Display object, and sets all values to zero and null, and then constructs a Jframe and Canvas using those fields
     * Precondition: createDisplay must create a proper canvas, width and height must be of type int, title must be of type String
     * Post condition: The Display is created with default 0 and null values
     */
    public Display(){
        this.width = 0;
        this.height = 0;
        this.title = null;
        createDisplay();
    }
    
    /**
     * Method: This constructor method uses parameters to populate the display's fields, and create a Jframe and Canvas 
     * Precondition: createDisplay must create a proper canvas, width and height must be of type int, title must be of type String
     * Post condition: The Display is created with the values of the parameters
     * @param width: an int value representing the width of the canvas in pixels
     * @param height: an int value representing the height of the canvas in pixels
     * @param title: an String that holds the title of the game
     */
    public Display(int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;
        createDisplay();
    }
    
    /**
     * Method: This constructor method uses the display parameter, to initialize the fields of the display being created and create the respective Jframe and Canvas
     * Precondition: createDisplay must create a proper canvas, width and height must be of type int, title must be of type String
     * Post Condition: The Display is created with the values of the parameter
     * @param d : A display object parameter that will be used to populate the fields of the display object that is calling an instance of this method
     */
    public Display(Display d){
        this.width = d.width;
        this.height = d.height;
        this.title = d.title;
        createDisplay();
    }
    
    //Instance Methods
    
    /**
     * Method: This instance method creates a canvas with all appropriate features using the fields 
     * Precondition: width and height must be proper int values, title must be a proper string
     * Post condition: canvas is created
     */
    private void createDisplay(){
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(width, height);
        setTitle(title);
        setLocationRelativeTo(null);
        setResizable(false);
        
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setFocusable(false);
        
        add(canvas);
        pack();
        
    }
    /**
     * Method: This Accessor method returns the canvas that has been initialized in this class
     * Precondition: canvas must have been created properly to have a proper size 
     * Post condition: canvas is sent back to calling instances of this method
     * @return the canvas is returned
     */
    public Canvas getCanvas() {
        return canvas;
    }
    
    /**
     * Method: This Accessor method returns the JFrame display of this class itself
     * Precondition: This must have been created properly with appropriate fields
     * Post condition: this is sent back to calling instances of this method
     * @return: this is returned
     */
    public JFrame getFrame() {
        return this;
    }
}
