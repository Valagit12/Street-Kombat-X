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
    private boolean right, left, up, down;

    private State state;
    private GameState gameState;
    private BufferedImage menu1;
    private BufferedImage menu2;
    private BufferedImage menu3;

    public HelpState(Game game) {
        super(game);
        this.state = state;
        this.gameState = gameState;
    }

    @Override
    public void tick() {
        up = game.getKeyManager().player2_jump;
        down = game.getKeyManager().player2_crouch;
        right = game.getKeyManager().player2_right;
        left = game.getKeyManager().player2_left;
        
        

        System.out.println(selection);
    }

    @Override
    public void render(Graphics gState) {
    }
}
