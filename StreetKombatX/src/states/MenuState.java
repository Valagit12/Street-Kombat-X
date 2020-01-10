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
public class MenuState extends State {

    private int timer = 0;
    private int selection = 0;
    private boolean up, down, enter, previousUp, previousDown;

    private State state;
    private GameState gameState;
    private BufferedImage menu1;
    private BufferedImage menu2;
    private BufferedImage menu3;

    public MenuState(Game game, GameState gameState, State state) {
        super(game);
        this.state = state;
        this.gameState = gameState;

        this.menu1 = Assets.menu.get(0);
        this.menu2 = Assets.menu.get(1);
        this.menu3 = Assets.menu.get(2);
    }

    @Override
    public void tick() {
        up = game.getKeyManager().player2_jump;
        down = game.getKeyManager().player2_crouch;
        enter = game.getKeyManager().enter;

        if (selection < 2 && down && !previousDown) {
            selection++;
        } else if (selection > 0 && up && !previousUp) {
            selection--;
        }

        if (enter) {
            if (selection == 0) {
                state.setState(gameState);
            } else if (selection == 1) {

            } else {

            }
        }

        previousUp = up;
        previousDown = down;

        System.out.println(selection);
    }

    @Override
    public void render(Graphics gState) {
        if (selection == 0) {
            gState.drawImage(menu1, 0, 0, 1280, 720, null);
        } else if (selection == 1) {
            gState.drawImage(menu2, 0, 0, 1280, 720, null);
        } else {
            gState.drawImage(menu3, 0, 0, 1280, 720, null);
        }
    }
}
