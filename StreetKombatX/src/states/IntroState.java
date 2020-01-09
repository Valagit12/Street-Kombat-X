/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package states;

import gfx.Assets;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import streetkombatx.Game;

/**
 *
 * @author h9113
 */
public class IntroState extends State{
    
    private int tick = 0;
    private int timer = 0;
    
    private BufferedImage loading1;
    private BufferedImage loading2;
    
    private State state;
    private MenuState menuState;
    
    public IntroState(Game game, State state, MenuState menuState) {
        super(game);
        this.state = state;
        this.menuState = menuState;
        loading1 = Assets.loading1;
        loading2 = Assets.loading2;
    }

    @Override
    public void tick() {
        if (tick == 60){
            timer++;
            tick = 0;
        }
        tick++;
        if (timer >= 5){
            state.setState(menuState);
        }
    }

    @Override
    public void render(Graphics gState) {
        if (timer > 0 && timer < 2){
            gState.drawImage(loading1, 0, 0, 1280, 720, null);
        }
        else if (timer > 2 && timer < 4){
            gState.drawImage(loading2, 0, 0, 1280, 720, null);
        }
        else {
            gState.setColor(Color.black);
            gState.fillRect(0, 0, 1280, 720);
        }
        
    }
}
