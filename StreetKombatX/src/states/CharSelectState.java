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
public class CharSelectState extends State{
    
    public int player1SelectionHorizontal = 0;
    public int player1SelectionVertical = 0;
    public int player2SelectionHorizontal = 0;
    public int player2SelectionVertical = 0;
    public int xPlayer1, xPlayer2, yPlayer1, yPlayer2;
    
    public boolean player1Right, player1Left, player1Up, player1Down;
    public boolean player2Right, player2Left, player2Up, player2Down;
    
    public BufferedImage charSelectScreen;
    public State state;
    public StageSelectState stageSelectState;
    
    public CharSelectState(Game game, StageSelectState stageSelectState, State state) {
        super(game);
        this.state = state;
        this.stageSelectState = stageSelectState;
        charSelectScreen = Assets.charSelectScreen;
    }

    @Override
    public void tick() {
        player1Right = game.getKeyManager().player1_right;
        player1Left = game.getKeyManager().player1_left;
        player1Up = game.getKeyManager().player1_jump;
        player1Down = game.getKeyManager().player1_crouch;
        
        player2Right = game.getKeyManager().player2_right;
        player2Left = game.getKeyManager().player2_left;
        player2Up = game.getKeyManager().player2_jump;
        player2Down = game.getKeyManager().player2_crouch;
        
        if (player1Right && player1SelectionHorizontal == 0){
            player1SelectionHorizontal++;
        }
        else if (player1Left && player1SelectionHorizontal == 1){
            player1SelectionHorizontal--;
        }
        
        if (player1Up && player1SelectionVertical == 1){
            player1SelectionVertical--;
        }
        else if (player1Down && player1SelectionVertical == 0){
            player1SelectionVertical++;
        }
        
        if (player2Right && player2SelectionHorizontal == 0){
            player2SelectionHorizontal++;
        }
        else if (player2Left && player2SelectionHorizontal == 1){
            player2SelectionHorizontal--;
        }
        
        if (player2Up && player2SelectionVertical == 1){
            player2SelectionVertical--;
        }
        else if (player2Down && player2SelectionVertical == 0){
            player2SelectionVertical++;
        }
             
        if (player1SelectionHorizontal == 0){
            xPlayer1 = 487;
        }
        else if (player1SelectionHorizontal == 1){
            xPlayer1 = 642;
        }
        
        if (player1SelectionVertical == 0){
            yPlayer1 = 163;
        }
        else if (player1SelectionVertical == 1){
            yPlayer1 = 315;
        }
                
        if (player2SelectionHorizontal == 0){
            xPlayer2 = 618;
        }
        else if (player2SelectionHorizontal == 1){
            xPlayer2 = 773;
        }
        
        if (player2SelectionVertical == 0){
            yPlayer2 = 164;
        }
        else if (player2SelectionVertical == 1){
            yPlayer2 = 316;
        }
    }

    @Override
    public void render(Graphics gState) {
        gState.drawImage(charSelectScreen, 0, 0, null);
        gState.setColor(Color.red);
        gState.fillRect(xPlayer1, yPlayer1, 20,20);
        gState.setColor(Color.blue);
        gState.fillRect(xPlayer2, yPlayer2, 20,20);
    }
}
