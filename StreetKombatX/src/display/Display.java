/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package display;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author h9113
 */
public class Display extends JFrame {
    
    private Canvas canvas;
    private int width;
    private int height;
    private String title;
    
    public Display(){
        this.width = 0;
        this.height = 0;
        this.title = null;
        createDisplay();
    }
    
    public Display(int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;
        createDisplay();
    }
    
    public Display(Display d){
        this.width = d.width;
        this.height = d.height;
        this.title = d.title;
        createDisplay();
    }
    
    private void createDisplay(){
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(width, height);
        setTitle(title);
        setLocationRelativeTo(null);
        
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        
        add(canvas);
        pack();
        
    }
    
    public Canvas getCanvas() {
        return canvas;
    }
    
    public void setCanvas(int width, int height) {
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
    }
}
