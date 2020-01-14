/**
 * Name: Brian Cho, Valareza Arezehgar, (Pack Studios)
 * Date: January 12, 2020
 * Version: 1
 * Description: A class that creates a rectangle object on a specific coordinate grid. The class utilizes various amounts of fields for accessor, 
 * class and instance methods that are used to return the fields of the rectangles, and find the intersection points between them, or whether or not
 * they do intersect at all.
 */

package collision;
/**
 * 
 * @author Pack Studios
 */
public class Rectangle {
    private int left;//The coordinate of the bottom left point of the rectangle on the x-axis
    private int bottom;// The coordinate of the bottom left point of the rectangle on the y-axis
    private int width;// The width of the rectangle in terms of units on the x-axis
    private int height;// The height of the rectangle in terms of units on the y-axis


    //Constructor methods
    
    /**
     * Method: This method overrides java's default constructor method to automatically create the rectangle object in any class where it is called, with default values set to zero
     * Precondition: The fields must be of type int.
     * Post condition: The rectangle's fields are populated with the default values.
     */
    public Rectangle () {
        this.left = 0;
        this.bottom = 0;
        this.width = 0;
        this.height = 0;
    }

    /**
     * Method: This method populates a rectangle object with the values stored in the field of another rectangle object sent through in the parameters
     * Precondition: Rectangle other's fields must all be greater than 0, rectangle r must be declared properly and have proper values as its respective fields
     * Post condition: this rectangle will be populated with the same data as that of rectangle other
     * @param other : a rectangle used to populate the fields of this rectangle
     */
    public Rectangle (Rectangle other) {
        this.left = other.left;
        this.bottom = other.bottom;
        this.width = other.width;
        this.height = other.height;
    }

    /**
     * Method: This constructor method populates the rectangle's fields with data sent through by the parameter
     * Precondition: l,b,w and h must all be proper int values
     * Post condition: the rectangle's fields will be populated with the parameters
     * @param l - an int value representing the x value of the left corner of the rectangle
     * @param b - an int value representing the y value of the left corner of the rectangle
     * @param w - an int value representing the width or length of the rectangle(in terms of x)
     * @param h - an int value representing the height of the rectangle(in terms of y)
     */
    public Rectangle (int l, int b, int w, int h) {
        this.left = l;
        this.bottom = b;
        if (w >= 0) {
            this.width = w;
        }
        else {
            this.width = 0;
        }

        if (w >= 0) {
            this.height = h;
        }
        else {
            this.height = 0;
        }
    }

    //Instance Methods
    
    /**
     * Method: This is an instance method that sets the value of the field left to the the value sent in by the parameter
     * Precondition: The left variable must be of proper type int
     * Post condition: The left field is initialized as the left parameter
     * @param left: An int that is sent as an argument
     */
    public void setLeft(int left) {
        this.left = left;
    }

    /**
     * Method: This is an instance method that sets the value of the field bottom to the value sent in by the parameter
     * Precondition: The bottom variable must be of proper type int
     * Post condition: The bottom field is initialized as the bottom parameter
     * @param bottom: An int that is sent in as an argument
     */
    public void setBottom(int bottom) {
        this.bottom = bottom;
    }
    
    //Class Methods
    
    /**
     * Method: Calculates the intersecting Rectangle between two different Rectangle objects
     * Precondition: Two Rectangle objects must be sent through the parameters and its fields must be valid
     * Post-condition: the intersecting Rectangle is returned as an object Rectangle
     * @param r the first Rectangle
     * @param e the second Rectangle
     * @return intersect the intersecting Rectangle as a Rectangle object
     */
    public static Rectangle intersection (Rectangle r, Rectangle e){
        int l = 0;
        int b = 0;
        int w = 0;
        int h = 0;

        w = min((r.left + r.width), (e.left + e.width)) - max(r.left, e.left); //determines the width of the intersecting Rectangle
        h = min((r.bottom + r.height), (e.bottom + e.height)) - max(r.bottom, e.bottom); //determines the height of the intersecting Rectangle

        if (w > 0 && h > 0 || w == 0 || h == 0){ //if the two Rectangles intersect
            l = max(r.left, e.left); //determines the lower left coordinate
            b = max(r.bottom, e.bottom);
        }
        else { //if the two Rectangle do not intersect
            w = 0; //set all fields to zero
            h = 0;
            l = 0;
            b = 0;
        }

        Rectangle intersect = new Rectangle(l,b,w,h);
        return intersect;
    }
    
    /**
     * Method: This method uses the parameters sent in to determine if two rectangles sent in are intersecting. If they are, the method returns true, else, it returns false
     * Precondition: The rectangles r and e must have proper values that correspond with their respective fields
     * Post condition: The method determines if the two parameter rectangles are intersecting and returns a matching boolean value
     * @param r: The first rectangle
     * @param e: The second rectangle
     * @return: True if the rectangles are intersecting, false if they are not
     */
    public static boolean isIntersecting (Rectangle r, Rectangle e){
        int l = 0;
        int b = 0;
        int w = 0;
        int h = 0;

        w = min((r.left + r.width), (e.left + e.width)) - max(r.left, e.left); //determines the width of the intersecting Rectangle
        h = min((r.bottom + r.height), (e.bottom + e.height)) - max(r.bottom, e.bottom); //determines the height of the intersecting Rectangle

        if (w > 0 && h > 0 || w == 0 || h == 0){ //if the two Rectangles intersect
            return true;
        }
        else { //if the two Rectangle do not intersect
            return false;
        }
    }

   

    /**
     * Determines the larger value between two numbers
     * Pre-condition: the parameters must be integers
     * Post-condition: the larger parameter is returned
     * @param x the first number
     * @param y the second number
     * @return the value that is larger
     */
    private static int max (int x, int y){
        if (x >= y){
            return x;
        }
        else {
            return y;
        }
    }

    /**
     * Determines the smaller value between two numbers
     * Pre-condition: the parameters must be integers
     * Post-condition; the smaller parameter is returned
     * @param x the first number
     * @param y the second number
     * @return the value that is smaller
     */
    private static int min (int x, int y){
        if (x <= y){
            return x;
        }
        else {
            return y;
        }
    }

}

