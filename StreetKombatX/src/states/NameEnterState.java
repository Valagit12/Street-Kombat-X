/**
 * Name: Valareza Arezehgar and Brian Cho (Pack Studios)
 * Date: January 13, 2020
 * Version: 1
 * Description: This class allows users to input their name, so that the game can remember them, and keep track of how much they've played. This implements runnable so that it can work concurrently with the actual game, as an alternate thread
 */
package states;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author h9113
 */
public class NameEnterState implements Runnable{
    
    private String text;
    
    private Thread nameEnter;
    private JFrame frame; // new frame
    private JButton enter;//button
    private JLabel enterName, output, output2;//output from the JFrame
    private JTextField textfield;//text field to recieve input
    private FileWriter fw;
    private PrintWriter pw;
    private ArrayList<String> texts;
    private String[] names; // names are stored
    private ArrayList<Integer> nums;// num of times played are stored
    private int[] numbers;
    
    /**
     * Method: This is a constructor method that does not take parameters, but initializes two fields and starts the thread
     * Precondition: texts and nums should both have been proper type string ArrayLists
     * Post Condition: The class has a few populated arrayList fields and the thread has started
     */
    public NameEnterState() {
        texts = new ArrayList<String>();
        nums = new ArrayList<Integer>();
        start();
    }

    /**
     * Method: This run method calls helper methods to read the file and sort it and relay it back towards the user using the displayFrame method 
     * Precondition: The helper methods called in this method must return the proper values
     * Post condition: The names on the files is read stored, sorted and displayed to users
     */
    @Override
    public void run() {
        readFile();
        quickSort(names, numbers);
        texts.clear();
        nums.clear();
        for (int i = 0; i < names.length; i++){
            texts.add(names[i]);
            nums.add(numbers[i]);
        }
        displayFrame();
    }
    
    /*
    Method: This is the actual methd that starts the thread
    Precondition: thread must have been initialized properly
    Post condition: The thread will have started the thread
    */
    public void start() {
        nameEnter = new Thread(this);
        nameEnter.start();
    }
    
    /**
     * Method: This method creates a new JFrame window for the NameEnterState
     * Precondition: frame must be a proper Jframe, names must be a proper String array, text must be a proper String array list
     * Post condition: The name of the user is recorded, searched for, and if the user has played before, a meaningful message appears
     */
    public void displayFrame() {
        frame = new JFrame("Enter Name"); 
        enter = new JButton("Enter");    
	enter.setBounds(100,100,140, 40);    
	enterName = new JLabel();		
	enterName.setText("Enter Name :");
	enterName.setBounds(10, 10, 100, 100);
	output = new JLabel();
	output.setBounds(10, 110, 200, 100);
        output2 = new JLabel();
        output2.setBounds(10, 130, 200, 100);
	textfield= new JTextField();
	textfield.setBounds(110, 50, 130, 30);
	frame.add(output);
        frame.add(output2);
	frame.add(textfield);
	frame.add(enterName);
	frame.add(enter);    
	frame.setSize(300,300);    
	frame.setLayout(null);    
	frame.setVisible(true);
        frame.setLocationRelativeTo(null);
	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);   
	enter.addActionListener(new ActionListener() {	        
            @Override
            public void actionPerformed(ActionEvent arg0) {
                text = textfield.getText();
                int searchResult = binarySearch (names, text);
                if (searchResult >= 0){
                    output.setText("Welcome Back, " + names[searchResult]);
                    if (numbers[searchResult] == 1){
                        output2.setText("You have played the game " + numbers[searchResult] + " time");
                    }
                    else {
                        output2.setText("You have played the game " + numbers[searchResult] + " times");
                    }
                    System.out.println(numbers[searchResult]);
                    nums.set(searchResult, numbers[searchResult]+1);
                    System.out.println(nums.get(searchResult));
                }
                else {
                    output.setText("First Time, " + text + "?");
                    output2.setText("You have played the game 0 times");
                    texts.add(text);
                    nums.add(1);
                }
                write();
            }          
	});
    }
    
    /**
     * Method: This method uses method overloading to recursively call another more complicated binary search method
     * Precondition: list must be a proper string array populated with Strings, searchName must be a proper String
     * Post condition: binarySearch is recursively called
     * @param list: a list of names that have played the game
     * @param searchName: The name being searched for
     * @return: An instance of another method of the same identifier is called, to use the binary search algorithm
     */
    private int binarySearch (String[] list, String searchName){
        return binarySearch(list, 0, list.length-1, searchName);
    }
    
    /**
     * Method: This method takes in the String array list, a high value and a low value, a desired name, and uses the binary search algorithm to search the array for the specific name
     * Precondition: list must be a proper String array populated with Strings, low and high int values must match values necessary for binary search and be positive int values
     * Post condition: The array has been traversed and searched for the desired String
     * @param list: A string holding the list of names
     * @param low: An int representing the low value of the index of the array
     * @param high: An int representing the high value of the index of the array
     * @param searchName: The desired name 
     * @return: If the algorithm gets through the whole array, -1 is returned. If it finds the desired String, it's index is returned. It works recursively until those two circumstances
     */
    private int binarySearch (String[] list, int low, int high, String searchName){
        if (low > high){
            return -1;
        }
        int middle = (low+high)/2;
        if (list[middle].equalsIgnoreCase(searchName)){
            return middle;
        }
        if (list[middle].compareTo(searchName) > 0){
            return binarySearch(list, low, middle-1, searchName);
        }
        else {
            return binarySearch (list, middle+1, high, searchName);
        }
    }
    
    /**
     * Method: This method accesses a txt file, reads it, and stores it in a dynamic array
     * Precondition: The txt file must be properly organized, and in the right location
     * Post condition: The file is read through and stored on an array texts
     */
    private void readFile() {
        try {
            FileReader fr = new FileReader("Names.txt");
            Scanner s = new Scanner(fr);
            int lines = 0;
            String line = "";
            while (s.hasNextLine()){
                try {
                    line = s.nextLine();
                    nums.add(Integer.parseInt(line));
                } catch (NumberFormatException e){
                    texts.add(line);
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        numbers = new int[nums.size()];
        names = new String[texts.size()];
        for (int i = 0; i < nums.size(); i++){
            numbers[i] = nums.get(i);
            names[i] = texts.get(i);
        }
    }
    
    /**
     * Method: This method uses method overloading to call an instance of the actual quickSort method
     * Preconditions: list must be completely populated with appropriate Strings, nums must be completely populated with positive int values
     * Post conditions: The quickSort method is called
     * @param list: an array containing a list of names
     * @param nums: an array containing a list of numbers
     */
    private void quickSort (String[] list, int[] nums){
        quickSort(list, nums, 0, list.length - 1);
    }
    
    /**
     * Method: Organizes the data sent through as parameters in ascending order
     * Precondition: list must be populated with appropriate Strings, nums must be populated with appropriate positive int values, low and high must be positive int values
     * Post condition: The arrays are put in ascending order
     * @param list: A list of names
     * @param nums: A list of numbers
     * @param low: An int representing the low value of the index of the array
     * @param high: An int representing the high value of the index of the array
     */
    private void quickSort (String[] list, int[] nums, int low, int high){
        final int MOVING_LEFT = 0;
        final int MOVING_RIGHT = 0;
        if (low < high){
            int left = low;
            int right = high;
            int currentDirection = MOVING_LEFT;
            int numPivot = nums[low];
            String pivot = list[low];
            
            while (left < right){
                if (currentDirection == MOVING_LEFT) {
                    while ((list[right].compareTo(pivot) >= 0) && (left < right))
                        right--;
                    list[left] = list[right];
                    nums[left] = nums[right];
                    currentDirection = MOVING_RIGHT;
                }
                if (currentDirection == MOVING_RIGHT){
                    while ((list[left].compareTo(pivot) <= 0) && (left < right))
                        left++;
                    list[right] = list[left];
                    nums[right] = nums[left];
                    currentDirection = MOVING_LEFT;
                }
            }
            list[left] = pivot;
            nums[left] = numPivot;
            quickSort(list, nums, low, left - 1);
            quickSort(list, nums, right+1, high);
        }
    }
    
    /**
     * Method: This method prints the values stored in the arrays texts and nums, on to, (or creates and then prints on to) a txt file
     * Precondition: The txt file must be in the proper location, Both arrays must be completely populated, nums with positive int values and texts with Strings
     * Post condition: The data stored in the arrays has been stored on to a txt file
     */
    private void write() {
        try{
            fw = new FileWriter("Names.txt");
        } catch (IOException e){
            e.printStackTrace();
        }
        
        pw = new PrintWriter(fw);
        for (int i = 0; i < texts.size(); i++){
            pw.println(texts.get(i));
            pw.println(nums.get(i));
        }
        pw.close();
    }
}
