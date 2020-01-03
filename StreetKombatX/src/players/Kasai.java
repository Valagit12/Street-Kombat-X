/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package players;

import gfx.Assets;
import java.awt.Graphics;
import streetkombatx.Game;

/**
 *
 * @author h9113
 */
public class Kasai extends Player{
    
    public Kasai(Game game, float x, float y) {
        super(game, x, y);
    }

    @Override
    public void tick() {
        if (game.getKeyManager().up){
            y -= 3;
        }
        if (game.getKeyManager().down){
            y += 3;
        }
        if (game.getKeyManager().right){
            x += 3;
        }
        if (game.getKeyManager().left){
            x -= 3;
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.test, (int) x, (int) y, null);
    }
    
}
