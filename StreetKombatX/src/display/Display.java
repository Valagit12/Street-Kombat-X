/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package display;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author h9113
 */
public class Display extends JFrame {
    
    private Canvas canvas;
    
    public Display(int width, int height, String title) {
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
