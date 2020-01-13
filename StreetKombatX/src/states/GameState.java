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
import java.awt.image.BufferedImage;
import java.util.ArrayList;
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
    private int time = 93;
    private int ticks = 0;
    private int endTimer = 0;
    private int selection = 0;
    private String winner;
    private boolean escape, up, down, enter;
    private boolean previousUp = false;
    private boolean previousDown = false;
    private boolean pause = false;
    
    private ArrayList<BufferedImage> pauseScreen;
    private BufferedImage[] stage;
    
    public GameState(Game game, Player player1, Player player2, BufferedImage[] stage){
        super(game);
        this.player1 = player1;
        this.player2 = player2;
        this.stage = stage;
        background = new Animation(60, this.stage);
        collisionCheck = new CollisionCheck(player1, player2);
        pauseScreen = Assets.pause;
    }
    
    @Override
    public void tick() {
        
        escape = game.getKeyManager().escape;
        
        if (escape && !(player1.getHealth() <= 0 || player2.getHealth() <= 0 || time <= 0)){
            pause = true;
        }
        
        if (pause){
            up = game.getKeyManager().player2_jump;
            down = game.getKeyManager().player2_crouch;
            enter = game.getKeyManager().enter;
            
            if (selection < 2 && down && !previousDown) {
                selection++;
            } else if (selection > 0 && up && !previousUp) {
                selection--;
            }
            
            if(enter){
                if (selection == 0){
                    pause = false;
                }
                else if (selection == 1){
                    State.setState(new HelpState(game, this));
                }
                else{
                    State.setState(new MenuState(game));
                }
            } 
            previousDown = down;
            previousUp = up;
        }        
        else{
            System.out.println(time);
            if (time > 0 && ticks == 60){
                time--;
                ticks = 0;
            }
            
            ticks++;
            
            if (time <= 90){
                background.tick();
                player1.tick();
                player2.tick();
                collisionCheck.checkCollision(time);

                if (time <= 0 || player1.getHealth() <= 0 || player2.getHealth() <= 0){
                    if (player1.getHealth() < player2.getHealth()){
                        winner = player2.getCharTitle();
                    }
                    else if (player2.getHealth() < player1.getHealth()){
                        winner = player1.getCharTitle();
                    }

                    if (endTimer >= 5){
                        State.setState(new EndState(game, player1, player2, stage));
                    }

                    if (ticks >= 60){
                        endTimer++;
                    }
                }
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
        if (time > 90){
            g.drawString(Integer.toString(90), 615, 113);
            if (time > 91){
                g.drawString("READY", 550, 350);
            }
            else {
                g.drawString("FIGHT", 550, 350);
            }
        }
        else {
            g.drawString(Integer.toString(time), 615, 113);
        }
        if (player1.getHealth() <= 0 || player2.getHealth() <= 0 || time <= 0){
            endScreen(g, winner);
        }
        
        if (pause){
            if (selection == 0){
                g.drawImage(pauseScreen.get(0), 0, 0, 1280, 720, null);
            }
            else if (selection == 1){
                g.drawImage(pauseScreen.get(1), 0, 0, 1280, 720, null);
            }
            else {
                g.drawImage(pauseScreen.get(2), 0, 0, 1280, 720, null);
            }
        }
    }
    
    public void endScreen(Graphics g, String charTitle) {
        g.setFont(Assets.dragonForceEndScreen);
        g.setColor(Color.red);
        g.drawString(charTitle + " WINS", 415, 350);
    }
    

}
