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
import players.Player;
import streetkombatx.Game;

/**
 *
 * @author h9113
 */
public class StageSelectState extends State{
    
    private int stageSelectionHorizontal = 0;
    private int stageSelectionVertical = 0;
    private int xPlayer = 466;
    private int yPlayer = 234;
    
    private boolean right, left, up, down;
    private boolean enter, escape;
    private boolean previousEnter = true;
    private boolean previousEscape = true;
    
    private Player player1;
    private Player player2;
    
    private BufferedImage stageSelectScreen;
    private BufferedImage[] stage;
    
    public StageSelectState(Game game, Player player1, Player player2){
        super(game);
        this.player1 = player1;
        this.player2 = player2;
        stageSelectScreen = Assets.stageSelectScreen;
    }

    @Override
    public void tick() {
        player1.getStance().tick();
        player2.getStance().tick();
        
        right = game.getKeyManager().isPlayer2_right();
        left = game.getKeyManager().isPlayer2_left();
        up = game.getKeyManager().isPlayer2_jump();
        down = game.getKeyManager().isPlayer2_crouch();
        
        enter = game.getKeyManager().isEnter();
        escape = game.getKeyManager().isEscape();
        
        if (right && stageSelectionHorizontal == 0){
            stageSelectionHorizontal++;
        }
        else if (left && stageSelectionHorizontal == 1){
            stageSelectionHorizontal--;
        }
        
        if (up && stageSelectionVertical == 1){
            stageSelectionVertical--;
        }
        else if (down && stageSelectionVertical == 0){
            stageSelectionVertical++;
        }
             
        if (stageSelectionHorizontal == 0){
            xPlayer = 466;
        }
        else if (stageSelectionHorizontal == 1){
            xPlayer = 647;
        }
        
        if (stageSelectionVertical == 0){
            yPlayer = 234;
        }
        else if (stageSelectionVertical == 1){
            yPlayer = 344;
        }
        
        confirmSelection();
        previousEnter = enter;
        previousEscape = escape;
    }

    @Override
    public void render(Graphics gState) {
        gState.drawImage(stageSelectScreen, 0, 0, 1280, 720, null);
        gState.drawImage(player1.getStance().getCurrentFrame(), 29, 161, 411, 822, null);
        gState.drawImage(player2.getStance().getCurrentFrame(), 861, 161, 411, 822, null);
        gState.setFont(Assets.dragonForceNum);
        gState.setColor(Color.red);
        gState.drawString(player1.getCharTitle(), 77, 81);
        gState.drawString(player1.getCharTitle(), 1020, 81);
        gState.drawString(getStageTitle(), 425, 180);
        for (int i = 0; i < 4; i++)
            gState.drawRect(xPlayer + i, yPlayer + i, 166 - 2 * i, 98 - 2 * i);
    }
    
    private String getStageTitle() {
        if (stageSelectionHorizontal == 0 && stageSelectionVertical == 0){
            return "Kasai's Fire Temple";
        }
        else if (stageSelectionHorizontal == 1 && stageSelectionVertical == 0){
            return "King Dom's Kingdom";
        }
        else {
            return "";
        }
    }
    
    private void confirmSelection() {
        if (stageSelectionVertical != 1 && stageSelectionVertical != 1 && enter && !previousEnter){
            if (stageSelectionHorizontal == 0 && stageSelectionVertical == 0){
                stage = Assets.fireTemple;
            }
            else if (stageSelectionHorizontal == 1 && stageSelectionVertical == 0){
                stage = Assets.kingdom;
            }
            
            State.setState(new GameState(game, player1, player2, stage));
        }
        
        if (escape && !previousEscape){
            State.setState(new CharSelectState(game));
        }
    }
}
    

