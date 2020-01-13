/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package states;

import gfx.Assets;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import players.Dom;
import players.Kasai;
import players.Player;
import streetkombatx.Game;

/**
 *
 * @author h9113
 */
public class EndState extends State{
    
    private int selection = 0;
    private boolean up, down, enter, previousDown, previousUp;
    
    private Player player1;
    private Player player2;
    
    private ArrayList<BufferedImage> endScreen;
    private BufferedImage[] stage;
    
    public EndState(Game game, Player player1, Player player2, BufferedImage[] stage) {
        super(game);
        
        if (player1.getCharTitle().equals("Kasai")){
            this.player1 = new Kasai(game, 200, 410, 150, 300, 1);
        }
        else if (player1.getCharTitle().equals("Dom")){
            this.player1 = new Dom(game, 200, 410, 150, 300, 1);
        }
        
        if (player2.getCharTitle().equals("Kasai")){
            this.player2 = new Kasai(game, 1000, 410, 150, 300, 2);
        }
        else if (player2.getCharTitle().equals("Dom")){
            this.player2 = new Dom(game, 1000, 410, 150, 300, 2);
        }
        
        this.stage = stage;
        this.endScreen = Assets.endScreen;
    }
    
    @Override
    public void tick(){
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
                State.setState(new GameState(game, player1, player2, stage));
            }
            else if (selection == 1){
                State.setState(new CharSelectState(game));
            }
            else{
                State.setState(new MenuState(game));
            }
        } 
        previousDown = down;
        previousUp = up;
    }

    @Override
    public void render(Graphics gState) {
        if (selection == 0){
                gState.drawImage(endScreen.get(0), 0, 0, 1280, 720, null);
            }
            else if (selection == 1){
                gState.drawImage(endScreen.get(1), 0, 0, 1280, 720, null);
            }
            else {
                gState.drawImage(endScreen.get(2), 0, 0, 1280, 720, null);
            }
    }
    
}
