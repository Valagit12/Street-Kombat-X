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
    
    public void checkCollision(int time) {
        hitbox_player1 = player1.getHitbox();
        hitbox_player2 = player2.getHitbox();
        
        if (player1.getHealth() > 0 && player2.getHealth() > 0 && time > 0) {
            if (player1.getX() + player1.getWidth() > player2.getX() + collisionOffset){
                if (player2.getIsWalkingLeft() && !player1.getIsWalkingRight()){
                    if (player1.getX() < 0){
                        player1.setX(0);
                        player2.setX(player1.getX()+player1.getWidth()-collisionOffset);
                    }
                    else {
                        player1.setX(player2.getX()+collisionOffset-player1.getWidth());
                    }
                }
                else if (!player2.getIsWalkingLeft() && player1.getIsWalkingRight()){
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
        }
        
        if (time <= 0){
            if (player1.getHealth() < player2.getHealth()){
                player1.setKnockBack(2000);
            }
            else if (player2.getHealth() < player1.getHealth()){
                player2.setKnockBack(2000);
            }
        }

        if(Rectangle.isIntersecting(hitbox_player1, hitbox_player2)){
            if (player1.getIsJumpingOne() && player1.getIsActive() && !player2.getIsBlocking()){
                player2.setIsHit(true);
                if (hitIndex_player1 < 1){
                    player2.setHealth(5);
                }
                hitIndex_player1++;
            }
            else if (player1.getIsJumpingTwo() && player1.getIsActive() && !player2.getIsBlocking()){
                player2.setIsHit(true);
                if (hitIndex_player1 < 1){
                    player2.setHealth(7);
                }
                hitIndex_player1++;
            }
            else if (player1.getIsDownOne() && player1.getIsActive() && !player2.getIsBlocking() && !player2.getIsCrouching()){
                player2.setIsHit(true);
                if (hitIndex_player1 < 1){
                    player2.setHealth(5);
                }
                hitIndex_player1++;
            }
            else if (player1.getIsDownTwo() && player1.getIsActive() && !player2.getIsCrouching()){
                player2.setIsHit(true);
                if (hitIndex_player1 < 1){
                    player2.setHealth(7);
                }
                hitIndex_player1++;
            }
            else if (player1.getIsStandingTwo() && player1.getIsActive() && !player2.getIsBlocking()){
                player2.setIsHit(true);
                if (hitIndex_player1 < 1){
                    player2.setHealth(8);
                }
                hitIndex_player1++;
            }
            else if (player1.getIsSpecial() && player1.getIsActive()){
                if (player1.getCharTitle().equals("Kasai")){
                    if (!player2.getIsBlocking() && !player2.isStun()){
                        player2.setIsHit(true);
                        if (hitIndex_player1 < 1){
                            player2.setHealth(7);
                            player2.setStun(120);
                        }
                        hitIndex_player1++;
                    }
                }
                else {
                    if (!player2.getIsCrouching() && !player2.getIsBlocking() && !player2.isStun()){
                        player2.setIsHit(true);
                        if (hitIndex_player1 < 1){
                            player2.setHealth(7);
                            player2.setStun(120);
                        }
                        hitIndex_player1++;
                    }
                }
            }
            else if (player1.getIsStandingOne() && player1.getIsActive()){
                if (player1.getCharTitle().equals("Kasai")){
                    if (!player2.getIsCrouching()){
                        player2.setIsHit(true);
                        if (hitIndex_player1 < 1){
                            player2.setHealth(5);
                            comboIndex_player1++;
                        }
                        hitIndex_player1++;
                    }
                }
                else {
                    if (!player2.getIsCrouching() && !player2.getIsBlocking()){
                        player2.setIsHit(true);
                        if (hitIndex_player1 < 1){
                            player2.setHealth(5);
                            comboIndex_player1++;
                        }
                        hitIndex_player1++;
                    }
                }
            }
            else if (player1.getIsStandingOneOne() && player1.getIsActive()){
                if (player1.getCharTitle().equals("Kasai")){
                    if (!player2.getIsCrouching()){
                        player2.setIsHit(true);
                        if (comboIndex_player1 < 2){
                            player2.setHealth(5);
                            comboIndex_player1++;
                        }
                        hitIndex_player1++;
                    }
                }
                else {
                    if (!player2.getIsCrouching() && !player2.getIsBlocking()){
                        player2.setIsHit(true);
                        if (comboIndex_player1 < 2){
                            player2.setHealth(5);
                            comboIndex_player1++;
                            player2.setStun(80);
                        }
                        hitIndex_player1++;
                    }
                }
            }
            else if (player1.getIsStandingOneOneOne() && player1.getIsActive()){
                if (player1.getCharTitle().equals("Kasai")){
                    if (!player2.getIsCrouching()){
                        player2.setIsHit(true);
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
                    if (!player2.getIsCrouching() && !player2.getIsBlocking()){
                        player2.setIsHit(true);
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
                player2.setIsHit(false);
                hitIndex_player1 = 0;
                comboIndex_player1 = 0;
            }
            
            if (player2.getIsJumpingOne() && player2.getIsActive() && !player1.getIsBlocking()){
                player1.setIsHit(true);
                if (hitIndex_player2 < 1){
                    player1.setHealth(5);
                }
                hitIndex_player2++;
            }
            else if (player2.getIsJumpingTwo() && player2.getIsActive() && !player1.getIsBlocking()){
                player1.setIsHit(true);
                if (hitIndex_player2 < 1){
                    player1.setHealth(7);
                }
                hitIndex_player2++;
            }
            else if (player2.getIsDownOne() && player2.getIsActive() && !player1.getIsBlocking() && !player1.getIsCrouching()){
                player1.setIsHit(true);
                if (hitIndex_player2 < 1){
                    player1.setHealth(5);
                }
                hitIndex_player2++;
            }
            else if (player2.getIsDownTwo() && player2.getIsActive() && !player1.getIsCrouching()){
                player1.setIsHit(true);
                if (hitIndex_player2 < 1){
                    player1.setHealth(7);
                }
                hitIndex_player2++;
            }
            else if (player2.getIsStandingTwo() && player2.getIsActive() && !player1.getIsBlocking()){
                player1.setIsHit(true);
                if (hitIndex_player2 < 1){
                    player1.setHealth(8);
                }
                hitIndex_player2++;
            }
            else if (player2.getIsSpecial() && player2.getIsActive()){
                if (player2.getCharTitle().equals("Kasai")){
                    if (!player1.getIsBlocking() && !player1.isStun()){
                        player1.setIsHit(true);
                        if (hitIndex_player2 < 1){
                            player1.setHealth(7);
                            player1.setStun(120);
                        }
                        hitIndex_player2++;
                    }
                }
                else {
                    if (!player1.getIsCrouching() && !player1.getIsBlocking() && !player1.isStun()){
                        player1.setIsHit(true);
                        if (hitIndex_player2 < 1){
                            player1.setHealth(7);
                            player1.setStun(120);
                        }
                        hitIndex_player2++;
                    }
                }
            }
            else if (player2.getIsStandingOne() && player2.getIsActive()){
                if (player2.getCharTitle().equals("Kasai")){
                    if (!player1.getIsCrouching()){
                        player1.setIsHit(true);
                        if (hitIndex_player2 < 1){
                            player1.setHealth(5);
                            comboIndex_player2++;
                        }
                        hitIndex_player2++;
                    }
                }
                else {
                    if (!player1.getIsCrouching() && !player1.getIsBlocking()){
                        player1.setIsHit(true);
                        if (hitIndex_player2 < 1){
                            player1.setHealth(5);
                            comboIndex_player2++;
                        }
                        hitIndex_player2++;
                    }
                }
            }
            else if (player2.getIsStandingOneOne() && player2.getIsActive()){
                if (player2.getCharTitle().equals("Kasai")){
                    if (!player1.getIsCrouching()){
                        player1.setIsHit(true);
                        if (comboIndex_player2 < 2){
                            player1.setHealth(5);
                            comboIndex_player2++;
                        }
                        hitIndex_player2++;
                    }
                }
                else {
                    if (!player1.getIsCrouching() && !player1.getIsBlocking()){
                        player1.setIsHit(true);
                        if (comboIndex_player2 < 2){
                            player1.setHealth(5);
                            comboIndex_player2++;
                            player1.setStun(80);
                        }
                        hitIndex_player2++;
                    }
                }
            }
            else if (player2.getIsStandingOneOneOne() && player2.getIsActive()){
                if (player2.getCharTitle().equals("Kasai")){
                    if (!player1.getIsCrouching()){
                        player1.setIsHit(true);
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
                    if (!player1.getIsCrouching() && !player1.getIsBlocking()){
                        player1.setIsHit(true);
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
                player1.setIsHit(false);
                hitIndex_player2 = 0;
                comboIndex_player2 = 0;
            }
        }
        else {
            player1.setIsHit(false);
            player2.setIsHit(false);
        }
    }
}
