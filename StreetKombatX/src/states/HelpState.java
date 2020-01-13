/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package states;

import gfx.Assets;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import streetkombatx.Game;

/**
 *
 * @author Valareza
 */
public class HelpState extends State {
    private int timer = 0;
    private int selection = 0;
    private boolean right, left, escape;
    private int pagelvl = 0;
    private boolean previousLeft, previousRight;

    private State previousState;
    private BufferedImage hlpScr1;
    private BufferedImage hlpScr2;
    private BufferedImage hlpScr3;
    private BufferedImage hlpScrCtrl;

    public HelpState(Game game, State previousState) {
        super(game);
        this.previousState = previousState;
        this.hlpScr1 = Assets.help.get(0);
        this.hlpScr2 = Assets.help.get(1);
        this.hlpScr3 = Assets.help.get(2);
        this.hlpScrCtrl = Assets.help.get(3);
    }

    @Override
    public void tick() {
        
        right = game.getKeyManager().player2_right;
        left = game.getKeyManager().player2_left;
        escape = game.getKeyManager().escape;
        
        
        if (pagelvl < 3 && right && !previousRight) {
            pagelvl++;
        } else if (pagelvl > 0 && left && !previousLeft) {
            pagelvl--;
        }
        if (escape){
            State.setState(previousState);
        }
        previousRight = right;
        previousLeft = left;
        
        
    }

    @Override
    public void render(Graphics gState) {
         if (pagelvl == 0) {
            gState.drawImage(hlpScr1, 0, 0, 1280, 720, null);
        } else if (pagelvl == 1) {
            gState.drawImage(hlpScr2, 0, 0, 1280, 720, null);
        } else if (pagelvl == 2) {
            gState.drawImage(hlpScr3, 0, 0, 1280, 720, null);
        } else if (pagelvl == 3){
            gState.drawImage(hlpScrCtrl, 0, 0, 1280, 720, null);
        }
    }
}
