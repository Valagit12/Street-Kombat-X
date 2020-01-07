//Brian Cho
//Nov. 27, 2019
//Unit 5 Culminating Assignment Rectangle
//Create an object class Rectangle that contains encapsulated fields and methods

package collision;

public class Rectangle {
    private int left;
    private int bottom;
    private int width;
    private int height;


    //Constructor methods
    public Rectangle () {
        this.left = 0;
        this.bottom = 0;
        this.width = 0;
        this.height = 0;
    }

    public Rectangle (Rectangle other) {
        this.left = other.left;
        this.bottom = other.bottom;
        this.width = other.width;
        this.height = other.height;
    }

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

    //Accessor Methods
    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getBottom() {
        return bottom;
    }

    public void setBottom(int bottom) {
        this.bottom = bottom;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Converts the fields of object Rectangle to a single string
     * Pre-condition: the fields of Rectangle are valid
     * Post-condition: the Rectangle fields are returned in a single String
     * @return dimensions single String 
     */
    public String toString() {
        String dimensions = "base: (" + this.left + "," + this.bottom + ") w:" + this.width + " h:" + this.height;
        return dimensions;
    }

    /**
     * Calculates the area of the Rectangle
     * Pre-condition: the fields of the Rectangle must be valid
     * Post-condition: an integer is returned as an area of the Rectangle
     * @return area the area of the rectangle as an integer value
     */
    public int area() {
        int area = this.width*this.height;
        return  area;
    }

    /**
     * Calculates the perimeter of the Rectangle
     * Pre-condition: the fields of the Rectangle must be valid
     * Post-condition: the perimeter of the Rectangle is returned as an integer
     * @return perimeter the perimeter of the rectangle as an integer value
     */
    public int perimeter() {
        int perimeter = 2*this.width + 2*this.height;
        return perimeter;
    }

    /**
     * Calculates the intersecting Rectangle between two different Rectangle objects
     * Pre-condition: Two Rectangle objects must be sent through the parameters and its fields must be valid
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
     * Calculates the total perimeter of two Rectangles
     * Pre-condition: the two parameter values must be Rectangles and its fields must be valid
     * Post-condition: the total perimeter is returned as an integer
     * @param r the first Rectangle
     * @param e the second Rectangle
     * @return perimeter the total perimeter of the two Rectangles as an integer
     */
    public static int totalPerimeter (Rectangle r, Rectangle e){
        Rectangle intersect = intersection(r,e);
        int perimeter = ((r.width*2) + (r.height*2) + (e.width*2) + (e.height*2)) - ((intersect.height*2)+(intersect.width*2)); //subtract the perimeter of the insecting rectangle from the perimeter of the two Rectangles
        return perimeter;
    }

    /**
     * Determines if the Rectangle passed as a parameter is contained within the main Rectangle
     * Pre-condition: the Rectangle passed as a parameter must be an object Rectangle and its fields must be valid
     * Post-condition: a boolean that determines whether the Rectangle is contained or not is returned
     * @param other the second Rectangle
     * @return a boolean that returns true if the second Rectangle is contained within the main Rectangle
     */
    public boolean contains (Rectangle other){
        Rectangle intersect = intersection(this, other);
        //if the intersecting Rectangle is equal to the second Rectangle
        if (intersect.left == other.left && intersect.bottom == other.bottom && intersect.width == other.width && intersect.height == other.height){
            return true;
        }
        else {
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

