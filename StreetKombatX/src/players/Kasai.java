/**
 * Name: Valareza Arezehgar and Brian Cho (Pack Studios)
 * Date: January 13, 2020
 * Version: 1
 * Description: This Class outlines the Kasai's character's features, such as his animations, hitbox, health, recovery, stun frames,knockback, and jumping
 */
package players;

import collision.Rectangle;
import gfx.Animation;
import gfx.Assets;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import streetkombatx.Game;

/**
 *
 * @author Pack Studios
 */
public class Kasai extends Player {

    /**
     * Method: This constructor method will take in parameters to initialize animations, title of Character, the position on the coordinate grid, and player number
     * Precondition: game must have proper fields, x and y must be proper int values and fit on the coordinate grid, width, height and playerNum must be proper int values
     * Post condition: The necessary traits of Kasai are initialized, including all of his animations
     * @param game: The main game 
     * @param x: The location of Kasai on the x-xis
     * @param y: The location of Kasai on the y-axis
     * @param width: The width of the hitbox of Kasai
     * @param height: The height of the hitbox of Kasai
     * @param playerNum: The int value of the player
     */
    public Kasai(Game game, float x, float y, int width, int height, int playerNum) {
        super(game, x, y, width, height, playerNum);

        if (playerNum == 1) {
            stance = new Animation(66.668, Assets.kasai_stance_player1);
            walk_left = new Animation(50, Assets.kasai_walk_left_player1);
            walk_right = new Animation(50, Assets.kasai_walk_right_player1);
            block = new Animation(20, Assets.kasai_block_player1);
            crouch = new Animation(20, Assets.kasai_crouch_player1);
            jump = new Animation(50, Assets.kasai_jump_player1);
            jump1 = new Animation(50, Assets.kasai_jump1_player1);
            jump2 = new Animation(50, Assets.kasai_jump2_player1);
            hit = new Animation(50, Assets.kasai_hit_player1);
            down1 = new Animation(30, Assets.kasai_down1_player1);
            down2 = new Animation(70, Assets.kasai_down2_player1);
            standing2 = new Animation(90, Assets.kasai_2_player1);
            standing1 = new Animation(50, Assets.kasai_1_player1);
            standing11 = new Animation(50, Assets.kasai_11_player1);
            standing111 = new Animation(50, Assets.kasai_111_player1);
            special = new Animation(100, Assets.kasai_special_player1);
        } else if (playerNum == 2) {
            stance = new Animation(66.668, Assets.kasai_stance_player2);
            walk_left = new Animation(50, Assets.kasai_walk_left_player2);
            walk_right = new Animation(50, Assets.kasai_walk_right_player2);
            block = new Animation(20, Assets.kasai_block_player2);
            crouch = new Animation(20, Assets.kasai_crouch_player2);
            jump = new Animation(50, Assets.kasai_jump_player2);
            jump1 = new Animation(50, Assets.kasai_jump1_player2);
            jump2 = new Animation(50, Assets.kasai_jump2_player2);
            hit = new Animation(50, Assets.kasai_hit_player2);
            down1 = new Animation(30, Assets.kasai_down1_player2);
            down2 = new Animation(70, Assets.kasai_down2_player2);
            standing2 = new Animation(90, Assets.kasai_2_player2);
            standing1 = new Animation(50, Assets.kasai_1_player2);
            standing11 = new Animation(50, Assets.kasai_11_player2);
            standing111 = new Animation(50, Assets.kasai_111_player2);
            special = new Animation(100, Assets.kasai_special_player2);
        }
        
        hitbox = new Rectangle ((int)x, (int)y, width, height);
        charTitle = "Kasai";
    }

    /**
     * Method: This tick method exists to check which move the player has inputted, and carry out the necessary steps afterwatd. For example, if the player jumps, then the y coordinate and hitbox of Kasai will jump as well
     * Precondition: The different moves have to be boolean, The x and y values must be within the coordinate grid
     * Post condition: The method has carried out the necessary steps based on user input
     */
    @Override
    public void tick() {
        setHitbox();
        stance.tick();
        walk_left.tick();
        walk_right.tick();
        
        if (playerNum == 1) {
            if (isAbleToPress){
                up = game.getKeyManager().isPlayer1_jump();
                down = game.getKeyManager().isPlayer1_crouch();
            }
            right = game.getKeyManager().isPlayer1_right();
            left = game.getKeyManager().isPlayer1_left();
            blocking = game.getKeyManager().isPlayer1_block();
            one = game.getKeyManager().isPlayer1_1();
            two = game.getKeyManager().isPlayer1_2();
            specialButton = game.getKeyManager().isPlayer1_special();
        } else if (playerNum == 2) {
            if (isAbleToPress){
                up = game.getKeyManager().isPlayer2_jump();
                down = game.getKeyManager().isPlayer2_crouch();
            }
            right = game.getKeyManager().isPlayer2_right();
            left = game.getKeyManager().isPlayer2_left();
            blocking = game.getKeyManager().isPlayer2_block();
            one = game.getKeyManager().isPlayer2_1();
            two = game.getKeyManager().isPlayer2_2();
            specialButton = game.getKeyManager().isPlayer2_special();
        }
        
        if (health <= 0){
            setKnockBack(2000);
        }
        
        if (recovery > 0){//If Kasai is recovering, he cant move, so all his possible moves are false
            up = false;
            down = false;
            right = false;
            left = false;
            blocking = false;
            one = false;
            two = false;
            recovery--;
        }
        
        if (stun > 0){//If Kasai is stunned, he cant move, so all his possible moves are false
            up = false;
            down = false;
            right = false;
            left = false;
            blocking = false;
            one = false;
            two = false;
            stun--;
        }
        
        if (knockBack > 0){
            if (playerNum == 1){
                x -= 10;
                knockBack--;
            }
            else {
                x += 10;
                knockBack--;
            }
        }
            
        if (y < yInitial){
            isAbleToPress = false;
            y += 7;
            if (y > yInitial){
                y = yInitial;
            }
        }
        else if (y == yInitial) {
            isAbleToPress = true;
            isJumpingOne = false;
            isJumpingTwo = false;
            isActive = false;
            jump1.setIndex(0);
            jumpAttackIndex = 0;
        }
        
        if (isHit){
            if(hit.getCurrentIndex() < 3){
                hit.tick();
            }
        }
        else if (hit.getCurrentIndex() != 0){
            isRecovering = true;
            hit.tick();
        }
        else {
            hit.setIndex(0);
            isRecovering = false;
        }
        
        if (isJumping) {
            if (jump.getCurrentIndex() < 4){
                y -= 20;
            }
            else if (jump.getCurrentIndex() == 6){
                isJumping = false;
                jump.setIndex(0);
            }
            jump.tick();
        }
        
        if (!isAbleToPress){
            if (isJumpingOne){
                if (jump1.getCurrentIndex() == 5){
                    isJumpingOne = false;
                    jump1.setIndex(0);
                    jumpAttackIndex++;
                    recovery = jump1Recovery;
                    isActive = false;
                }
                else if (jump1.getCurrentIndex() == 2 || jump1.getCurrentIndex() == 3 || jump1.getCurrentIndex() == 4){
                    isActive = true;
                    jump1.tick();
                }
                else {
                    jump1.tick();
                    isActive = false;
                }
            }
        }
        
        if (isBlocking) {
            right = false;
            left = false;
            up = false;
            one = false;
            two = false;
            specialButton = false;
            isCrouching = false;
            if (blocking){
                if (block.getCurrentIndex() < 4) {
                    block.tick();
                }
            }
            else {
                if (block.getCurrentIndex() != 6){
                    block.tick();
                }
                else {
                    block.setIndex(0);
                }
            }
        }
        
        if (isCrouching) {
            right = false;
            left = false;
            up = false;
            specialButton = false;
            isBlocking = false;
            if (down) {
                if (crouch.getCurrentIndex() < 5){
                    crouch.tick();
                }
            }
            else {
                if (crouch.getCurrentIndex() != 7) {
                    crouch.tick();
                }
                else {
                    crouch.setIndex(0);
                }
            }
        }
        
        if (isDownOne){
            up = false;
            left = false;
            right = false;
            one = false;
            two = false;
            specialButton = false;
            if (down1.getCurrentIndex() == 7){
                down1.setIndex(0);
                isDownOne = false;
                recovery = down1Recovery;
                isActive = false;
            }
            else if (down1.getCurrentIndex() >= 3 && down1.getCurrentIndex() <= 5){
                isActive = true;
                down1.tick();
            }
            else {
                down1.tick();
                isActive = false;
            }
        }
        else if (isDownTwo){
            up = false;
            left = false;
            right = false;
            one = false;
            two = false;
            specialButton = false;
            if (down2.getCurrentIndex() == 8){
                down2.setIndex(0);
                isDownTwo = false;
                recovery = down2Recovery;
                isActive = false;
            }
            else if (down2.getCurrentIndex() == 4 || down2.getCurrentIndex() == 5){
                isActive = true;
                down2.tick();
            }
            else {
                down2.tick();
                isActive = false;
            }
        }
        
        if (isStandingTwo){
            up = false;
            left = false;
            right = false;
            one = false;
            two = false;
            specialButton = false;
            if(standing2.getCurrentIndex() == 7) {
                standing2.setIndex(0);
                isStandingTwo = false;
                recovery = standing2Recovery;
                isActive = false;
            }
            else if (standing2.getCurrentIndex() >= 3 && standing2.getCurrentIndex() <= 5){
                standing2.tick();
                isActive = true;
            }
            else {
                standing2.tick();
                isActive = false;
            }
        }
        
        if (isStandingOne){
            up = false;
            left = false;
            right = false;
            two = false;
            specialButton = false;
            if(standing1.getCurrentIndex() == 9) {
                standing1.setIndex(0);
                isStandingOne = false;
                recovery = standing1Recovery;
                isActive = false;
                comboIndex = 0;
            }
            else if (standing1.getCurrentIndex() >= 2 && standing1.getCurrentIndex() <= 4){
                standing1.tick();
                isActive = true;
            }
            else {
                standing1.tick();
                isActive = false;
            }
        }
        
        if (isStandingOneOne){
            up = false;
            left = false;
            right = false;
            two = false;
            specialButton = false;
            if (playerNum == 1){
                x += 1;
            }
            else {
                x -= 1;
            }
            if(standing11.getCurrentIndex() == 15) {
                standing11.setIndex(0);
                standing1.setIndex(0);
                isStandingOneOne = false;
                recovery = standing11Recovery;
                isActive = false;
                comboIndex = 0;
            }
            else if (standing11.getCurrentIndex() >= 2 && standing11.getCurrentIndex() <= 13){
                standing11.tick();
                isActive = true;
            }
            else {
                standing11.tick();
                isActive = false;
            }
        }
        
        if (isStandingOneOneOne){
            up = false;
            left = false;
            right = false;
            two = false;
            specialButton = false;
            if (playerNum == 1){
                x += 1;
            }
            else {
                x -= 1;
            }
            if (standing111.getCurrentIndex() == 20){
                standing111.setIndex(0);
                standing11.setIndex(0);
                standing1.setIndex(0);
                isStandingOneOneOne = false;
                recovery = standing111Recovery;
                isActive = false;
                comboIndex = 0;
            }
            else if (standing111.getCurrentIndex() >= 2 && standing11.getCurrentIndex() <= 18){
                standing111.tick();
                isActive = true;
            }
            else {
                standing111.tick();
                isActive = false;
            }
        }
        
        if (isSpecial){
            up = false;
            left = false;
            right = false;
            one = false;
            two = false;
            if (special.getCurrentIndex() == 10){
                special.setIndex(0);
                isSpecial = false;
                recovery = specialMoveRecovery;
                isActive = false;
            }
            else if (special.getCurrentIndex() >= 4 && special.getCurrentIndex() <= 6){
                special.tick();
                isActive = true;
            }
            else {
                special.tick();
                isActive = false;
            }
        }
        
        if (up) {
            isJumping = true;
            isAbleToPress = false;
            up = false;
        }
        
        if (blocking || block.getCurrentIndex() != 0) {
            isBlocking = true;
        }
        else {
            isBlocking = false;
        }
        
        if ((down || crouch.getCurrentIndex() != 0) && !isBlocking) {
            isCrouching = true;
        }
        else {
            isCrouching = false;
        }
        
        if (one) {
            if (!previousOne){
                comboIndex++;
            }
            if (!isAbleToPress && jumpAttackIndex < 1){
                isJumpingOne = true;
            }
            else if (isCrouching){
                isDownOne = true;
            }
            else if (comboIndex == 2 && isStandingOne && standing1.getCurrentIndex() <= 6){
                isStandingOneOne = true;
                isStandingOne = false;
                standing11.setIndex(standing1.getCurrentIndex());
            }
            else if (comboIndex == 3 && isStandingOneOne && standing11.getCurrentIndex() <= 12){
                isStandingOneOneOne = true;
                isStandingOneOne = false;
                standing111.setIndex(standing11.getCurrentIndex());
            }
            else{
                isStandingOne = true;
            }
            previousOne = true;
        }
        else {
            previousOne = false;
        }
        
        if (two){
            if (!isAbleToPress && jumpAttackIndex < 1){
                isJumpingTwo = true;
                isActive = true;
            }
            else if (isCrouching){
                isDownTwo = true;
            }
            else {
                isStandingTwo = true;
            }
        }
        
        if (specialButton){
            isSpecial = true;
        }
       
        
        if (right) {
            x += 3;
            isWalkingRight = true;
            isWalkingLeft = false;
        } else if (left) {
            x -= 3;
            isWalkingLeft = true;
            isWalkingRight = false;
        } else {
            isWalkingLeft = false;
            isWalkingRight = false;
        }
    }

    /**
     * Method: This method runs as many times as the tick method, but it accounts for visual updates, such as drawing the current animation state and healthbar
     * Precondition: Graphics must have been initialized properly, x,y,width and height must all be proper int values that fit within the coordinate grid
     * Post condition: The images for the game are updated
     * @param g: a graphics object to draw images on the canvas
     */
    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationState(), (int) x, (int) y, width, height, null);
        drawHealth(g);
    }
    
    private BufferedImage getCurrentAnimationState() {
        if (isHit || isRecovering){
            return hit.getCurrentFrame();
        }
        
        if (stun > 0){
            return hit.getFrame(hit.getCurrentIndex());
        }
        
        if (isSpecial){
            return special.getCurrentFrame();
        }
        
        if(isStandingOne){
            return standing1.getCurrentFrame();
        }
        
        if (isStandingOneOne){
            return standing11.getCurrentFrame();
        }
        
        if (isStandingOneOneOne){
            return standing111.getCurrentFrame();
        }
        
        if(isStandingTwo){
            return standing2.getCurrentFrame();
        }
        
        if (isDownOne) {
            return down1.getCurrentFrame();
        }
        
        if (isDownTwo) {
            return down2.getCurrentFrame();
        }
        
        if (isJumpingTwo){
            return jump2.getCurrentFrame();
        }
        
        if (isJumpingOne){
            return jump1.getCurrentFrame();
        }
        
        if (isJumping) {
            return jump.getCurrentFrame();
        }
        
        if (isBlocking) {
            return block.getCurrentFrame();
        }
        
        if (isCrouching) {
            return crouch.getCurrentFrame();
        }
        
        if (isWalkingLeft) {
            return walk_left.getCurrentFrame();
        } else if (isWalkingRight) {
            return walk_right.getCurrentFrame();
        } else {
            return stance.getCurrentFrame();
        }
    }
    
    private void setHitbox() {
        hitbox.setLeft((int)x);
        hitbox.setBottom((int)y);
    }

}
