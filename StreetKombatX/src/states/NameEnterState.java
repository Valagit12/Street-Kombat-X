/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
    private JFrame frame;
    private JButton enter;
    private JLabel enterName, output, output2;
    private JTextField textfield;
    private FileWriter fw;
    private PrintWriter pw;
    private ArrayList<String> texts;
    private String[] names;
    private ArrayList<Integer> nums;
    private int[] numbers;
    
    public NameEnterState() {
        texts = new ArrayList<String>();
        nums = new ArrayList<Integer>();
        start();
    }

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
    
    public void start() {
        nameEnter = new Thread(this);
        nameEnter.start();
    }

    public void stop() {
        try {
            nameEnter.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
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
    
    private int binarySearch (String[] list, String searchName){
        return binarySearch(list, 0, list.length-1, searchName);
    }
    
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
    
    private void quickSort (String[] list, int[] nums){
        quickSort(list, nums, 0, list.length - 1);
    }
    
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
