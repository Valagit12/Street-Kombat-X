/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package states;

import collision.CollisionCheck;
import gfx.Animation;
import gfx.Assets;
import java.awt.Color;
import java.awt.Graphics;
import streetkombatx.Game;
import players.Player;
import states.EndState;
/**
 *
 * @author Valareza
 */
public class GameState extends State {
    private Player player1;
    private Player player2;
    
    private Animation background;
    private CollisionCheck collisionCheck;
    private EndState endState;
    private State state;
    private int time = 90;
    private int ticks = 0;
    private int endTimer = 0;
    private String winner;
    
    public GameState(Game game, Player player1, Player player2, EndState endState, State state){
        super(game);
        this.player1 = player1;
        this.player2 = player2;
        this.endState = endState;
        this.state = state;
        
        background = new Animation(60, Assets.fireTemple);
        collisionCheck = new CollisionCheck(player1, player2);
    }
    @Override
    public void tick() {
        
        if (time > 0 && ticks == 60){
            time--;
            ticks = 0;
        }
        background.tick();
        player1.tick();
        player2.tick();
        collisionCheck.checkCollision(time);
        
        ticks++;
        
        
        if (time <= 0 || player1.getHealth() <= 0 || player2.getHealth() <= 0){
            if (player1.getHealth() < player2.getHealth()){
                winner = player2.getCharTitle();
            }
            else if (player2.getHealth() < player1.getHealth()){
                winner = player1.getCharTitle();
            }
            
            if (endTimer >= 5){
                state.setState(endState);
                
            }
            
            if (ticks >= 60){
                endTimer++;
            }
        }
        
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(background.getCurrentFrame(), 0, 0, null);
        player1.render(g);
        player2.render(g);
        g.setFont(Assets.dragonForceNum);
        g.setColor(Color.red);
        g.drawString(Integer.toString(time), 615, 113);
        if (player1.getHealth() <= 0 || player2.getHealth() <= 0 || time <= 0){
            endScreen(g, winner);
        }
    }
    
    public void endScreen(Graphics g, String charTitle) {
        g.setFont(Assets.dragonForceEndScreen);
        g.setColor(Color.red);
        g.drawString(charTitle + " WINS", 415, 350);
    }
    

}
