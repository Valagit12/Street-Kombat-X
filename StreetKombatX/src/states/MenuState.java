/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package states;

import gfx.ImageLoader;
import java.awt.Graphics;
import streetkombatx.Game;

/**
 *
 * @author Valareza
 */
public class MenuState extends State{
        private int timer = 0;
    
    public MenuState(Game game){
        super(game);
    }
    
    @Override
    public void tick() {
        
    }

    @Override
    public void render(Graphics gState){
        gState.drawImage(ImageLoader.loadImage("res/Menu/Loadin Screen Part 1.png") , -200, -200, null);
        timer++;
        if (timer>25){
            gState.drawImage(ImageLoader.loadImage("res/Menu/Loading Screen Part 2.png") , -200, -200, null);
        }
        if(timer>40){
            gState.drawImage(ImageLoader.loadImage("res/Menu/Menu Screen Part 1.png") , -200, -200, null);
        }
    }
    
    public static void menuNavigator(){
    }
    
}
