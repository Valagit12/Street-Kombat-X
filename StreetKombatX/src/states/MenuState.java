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
    private boolean previousEnter = true;
    
    private BufferedImage menu1;
    private BufferedImage menu2;
    private BufferedImage menu3;

    public MenuState(Game game) {
        super(game);
        this.menu1 = Assets.menu.get(0);
        this.menu2 = Assets.menu.get(1);
        this.menu3 = Assets.menu.get(2);
    }

    @Override
    public void tick() {
        up = game.getKeyManager().isPlayer2_jump();
        down = game.getKeyManager().isPlayer2_crouch();
        enter = game.getKeyManager().isEnter();

        if (selection < 2 && down && !previousDown) {
            selection++;
        } else if (selection > 0 && up && !previousUp) {
            selection--;
        }

        if (enter && !previousEnter) {
            if (selection == 0) {
                State.setState(new CharSelectState(game));
            } else if (selection == 1) {
                State.setState(new HelpState(game, this));
            } else {
                new NameEnterState();
            }
        }

        previousUp = up;
        previousDown = down;
        previousEnter = enter;
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
