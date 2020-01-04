/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package states;

import gfx.Animation;
import gfx.Assets;
import java.awt.Graphics;
import streetkombatx.Game;
import players.Player;

/**
 *
 * @author Valareza
 */
public class GameState extends State {
    private Player player1;
    private Player player2;
    
    private Animation background;
    
    float posOne;
    float posTwo;
    
    public GameState(Game game, Player player1, Player player2){
        super(game);
        this.player1 = player1;
        this.player2 = player2;
        
        background = new Animation(60, Assets.fireTemple);
    }
    @Override
    public void tick() {
        background.tick();
        player1.tick();
        player2.tick();
        
        posOne = player1.getX();
        posTwo = player2.getX();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(background.getCurrentFrame(), 0, 0, null);
        player1.render(g);
        player2.render(g);
    }
    
    public float getPositionOne(){
        return posOne;
    }
    
    public float getPositionTwo(){
        return posTwo;
    }
}
