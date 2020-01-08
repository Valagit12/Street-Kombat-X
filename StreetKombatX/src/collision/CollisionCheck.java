/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collision;

import players.Player;

/**
 *
 * @author h9113
 */
public class CollisionCheck {
    
    private int hitIndex_player1 = 0;
    private int comboIndex_player1 = 0;
    private int hitIndex_player2 = 0;
    private int comboIndex_player2 = 0;
    private int collisionOffset = 30;
    private float xPlayer1, xPlayer2;
    
    private Player player1;
    private Player player2;
    
    private Rectangle hitbox_player1;
    private Rectangle hitbox_player2;
    
    public CollisionCheck (Player player1, Player player2){
        this.player1 = player1;
        this.player2 = player2;
    }
    
    public void checkCollision() {
        hitbox_player1 = player1.getHitbox();
        hitbox_player2 = player2.getHitbox();
        
        
        if (player1.getX() + player1.getWidth() > player2.getX() + collisionOffset){
            if (player2.isWalkingLeft && !player1.isWalkingRight){
                if (player1.getX() < 0){
                    player1.setX(0);
                    player2.setX(player1.getX()+player1.getWidth()-collisionOffset);
                }
                else {
                    player1.setX(player2.getX()+collisionOffset-player1.getWidth());
                }
            }
            else if (!player2.isWalkingLeft && player1.isWalkingRight){
                if (player2.getX() > 1150){
                    player2.setX(1150);
                    player1.setX(player2.getX()+collisionOffset-player1.getWidth());
                }
                else {
                    player2.setX(player1.getX()+player1.getWidth()-collisionOffset);
                }
            }
            else {
                xPlayer1 = player2.getX()+collisionOffset-player1.getWidth();
                xPlayer2 = player1.getX()+player1.getWidth()-collisionOffset;
                player1.setX(xPlayer1);
                player2.setX(xPlayer2);
            }
        }
        else {
            if (player1.getX() < 0){
            player1.setX(0);
        }
        
        if (player2.getX() > 1150){
            player2.setX(1150);
        }
        }
        

        if(Rectangle.isIntersecting(hitbox_player1, hitbox_player2)){
            if (player1.isJumpingOne && player1.isActive && !player2.isBlocking){
                player2.isHit = true;
                if (hitIndex_player1 < 1){
                    player2.setHealth(5);
                }
                hitIndex_player1++;
            }
            else if (player1.isJumpingTwo && player1.isActive && !player2.isBlocking){
                player2.isHit = true;
                if (hitIndex_player1 < 1){
                    player2.setHealth(7);
                }
                hitIndex_player1++;
            }
            else if (player1.isDownOne && player1.isActive && !player2.isBlocking && !player2.isCrouching){
                player2.isHit = true;
                if (hitIndex_player1 < 1){
                    player2.setHealth(5);
                }
                hitIndex_player1++;
            }
            else if (player1.isDownTwo && player1.isActive && !player2.isCrouching){
                player2.isHit = true;
                if (hitIndex_player1 < 1){
                    player2.setHealth(7);
                }
                hitIndex_player1++;
            }
            else if (player1.isStandingTwo && player1.isActive && !player2.isBlocking){
                player2.isHit = true;
                if (hitIndex_player1 < 1){
                    player2.setHealth(8);
                }
                hitIndex_player1++;
            }
            else if (player1.isSpecial && player1.isActive){
                if (player1.charTitle.equals("Kasai")){
                    if (!player2.isBlocking && !player2.isStun()){
                        player2.isHit = true;
                        if (hitIndex_player1 < 1){
                            player2.setHealth(7);
                            player2.setStun(100);
                        }
                        hitIndex_player1++;
                    }
                }
                else {
                    if (!player2.isCrouching && !player2.isBlocking && !player2.isStun()){
                        player2.isHit = true;
                        if (hitIndex_player1 < 1){
                            player2.setHealth(7);
                            player2.setStun(100);
                        }
                        hitIndex_player1++;
                    }
                }
            }
            else if (player1.isStandingOne && player1.isActive){
                if (player1.charTitle.equals("Kasai")){
                    if (!player2.isCrouching){
                        player2.isHit = true;
                        if (hitIndex_player1 < 1){
                            player2.setHealth(5);
                            comboIndex_player1++;
                        }
                        hitIndex_player1++;
                    }
                }
                else {
                    if (!player2.isCrouching && !player2.isBlocking){
                        player2.isHit = true;
                        if (hitIndex_player1 < 1){
                            player2.setHealth(5);
                            comboIndex_player1++;
                        }
                        hitIndex_player1++;
                    }
                }
            }
            else if (player1.isStandingOneOne && player1.isActive){
                if (player1.charTitle.equals("Kasai")){
                    if (!player2.isCrouching){
                        player2.isHit = true;
                        if (comboIndex_player1 < 2){
                            player2.setHealth(5);
                            comboIndex_player1++;
                        }
                        hitIndex_player1++;
                    }
                }
                else {
                    if (!player2.isCrouching && !player2.isBlocking){
                        player2.isHit = true;
                        if (comboIndex_player1 < 2){
                            player2.setHealth(5);
                            comboIndex_player1++;
                        }
                        hitIndex_player1++;
                    }
                }
            }
            else if (player1.isStandingOneOneOne && player1.isActive){
                if (player1.charTitle.equals("Kasai")){
                    if (!player2.isCrouching){
                        player2.isHit = true;
                        if (comboIndex_player1 < 3){
                            player2.setHealth(5);
                            comboIndex_player1++;
                        }
                        hitIndex_player1++;
                        if (player1.getComboFrame() == 15){
                            player2.setKnockBack(20);
                        }
                    }
                }
                else {
                    if (!player2.isCrouching && !player2.isBlocking){
                        player2.isHit = true;
                        if (comboIndex_player1 < 3){
                            player2.setHealth(5);
                            comboIndex_player1++;
                        }
                        hitIndex_player1++;
                        if (player1.getComboFrame() == 15){
                            player2.setKnockBack(20);
                        }
                    }
                }
            }
            else {
                player2.isHit = false;
                hitIndex_player1 = 0;
                comboIndex_player1 = 0;
            }
            
            if (player2.isJumpingOne && player2.isActive && !player1.isBlocking){
                player1.isHit = true;
                if (hitIndex_player2 < 1){
                    player1.setHealth(5);
                }
                hitIndex_player2++;
            }
            else if (player2.isJumpingTwo && player2.isActive && !player1.isBlocking){
                player1.isHit = true;
                if (hitIndex_player2 < 1){
                    player1.setHealth(7);
                }
                hitIndex_player2++;
            }
            else if (player2.isDownOne && player2.isActive && !player1.isBlocking && !player1.isCrouching){
                player1.isHit = true;
                if (hitIndex_player2 < 1){
                    player1.setHealth(5);
                }
                hitIndex_player2++;
            }
            else if (player2.isDownTwo && player2.isActive && !player1.isCrouching){
                player1.isHit = true;
                if (hitIndex_player2 < 1){
                    player1.setHealth(7);
                }
                hitIndex_player2++;
            }
            else if (player2.isStandingTwo && player2.isActive && !player1.isBlocking){
                player1.isHit = true;
                if (hitIndex_player2 < 1){
                    player1.setHealth(8);
                }
                hitIndex_player2++;
            }
            else if (player2.isSpecial && player2.isActive){
                if (player2.charTitle.equals("Kasai")){
                    if (!player1.isBlocking && !player1.isStun()){
                        player1.isHit = true;
                        if (hitIndex_player2 < 1){
                            player1.setHealth(7);
                            player1.setStun(100);
                        }
                        hitIndex_player2++;
                    }
                }
                else {
                    if (!player1.isCrouching && !player1.isBlocking && !player1.isStun()){
                        player1.isHit = true;
                        if (hitIndex_player2 < 1){
                            player1.setHealth(7);
                            player1.setStun(100);
                        }
                        hitIndex_player2++;
                    }
                }
            }
            else if (player2.isStandingOne && player2.isActive){
                if (player2.charTitle.equals("Kasai")){
                    if (!player1.isCrouching){
                        player1.isHit = true;
                        if (hitIndex_player2 < 1){
                            player1.setHealth(5);
                            comboIndex_player2++;
                        }
                        hitIndex_player2++;
                    }
                }
                else {
                    if (!player1.isCrouching && !player1.isBlocking){
                        player1.isHit = true;
                        if (hitIndex_player2 < 1){
                            player1.setHealth(5);
                            comboIndex_player2++;
                        }
                        hitIndex_player2++;
                    }
                }
            }
            else if (player2.isStandingOneOne && player2.isActive){
                if (player2.charTitle.equals("Kasai")){
                    if (!player1.isCrouching){
                        player1.isHit = true;
                        if (comboIndex_player2 < 2){
                            player1.setHealth(5);
                            comboIndex_player2++;
                        }
                        hitIndex_player2++;
                    }
                }
                else {
                    if (!player1.isCrouching && !player1.isBlocking){
                        player1.isHit = true;
                        if (comboIndex_player2 < 2){
                            player1.setHealth(5);
                            comboIndex_player2++;
                        }
                        hitIndex_player2++;
                    }
                }
            }
            else if (player2.isStandingOneOneOne && player2.isActive){
                if (player2.charTitle.equals("Kasai")){
                    if (!player1.isCrouching){
                        player1.isHit = true;
                        if (comboIndex_player2 < 3){
                            player1.setHealth(5);
                            comboIndex_player2++;
                        }
                        hitIndex_player2++;
                        if (player2.getComboFrame() == 15){
                            player1.setKnockBack(20);
                        }
                    }
                }
                else {
                    if (!player1.isCrouching && !player1.isBlocking){
                        player1.isHit = true;
                        if (comboIndex_player2 < 3){
                            player1.setHealth(5);
                            comboIndex_player2++;
                            player1.setKnockBack(20);
                        }
                        hitIndex_player2++;
                        if (player2.getComboFrame() == 15){
                            player1.setKnockBack(20);
                        }
                    }
                }
            }
            else {
                player1.isHit = false;
                hitIndex_player2 = 0;
                comboIndex_player2 = 0;
            }
        }
        else {
            player1.isHit = false;
            player2.isHit = false;
        }
    }
}
