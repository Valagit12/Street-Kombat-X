/**
 * Name: Valareza Arezehgar and Brian Cho (Pack Studios)
 * Date: January 13, 2020
 * Version: 1
 * Description: This class is responsible for checking the conditions of the attacks and positions of the players to determine whether or not that 
 * attacks have landed, and players are in stun frames or not. It also tracks positions to make sure that players cannot leave the window or cross over each other.
 */
package collision;

import players.Player;

/**
 *
 * @author Pack Studios
 */
public class CollisionCheck {

    private int hitIndex_player1 = 0; // An int that determines how many times the opponent will get hit
    private int comboIndex_player1 = 0;//Like hitIndex, how ever it is used for strings that cause damage more than once, so standing one, one, or one,one,one
    private int hitIndex_player2 = 0;//hitIndex but applies to player 2
    private int comboIndex_player2 = 0;//comboIndex but applies to player 2
    private int collisionOffset = 30;// An int that is used to prevent the two players from wallking past each other
    private float xPlayer1, xPlayer2; // The location of each of the players on the x-axis

    private Player player1;// Player object representing player 1
    private Player player2;// Player object representing player 2

    private Rectangle hitbox_player1;// Rectangle object that represents the hitbox of player 1
    private Rectangle hitbox_player2;// Rectangle object that represents the hitbox of player 2

    private boolean canSpecialCancel = false;// Used to determine situations in which the user can cancel their attach string into a special move

    /**
     * Method: This is a constructor method that takes in two player parameters and sets the fields of this object as those sent in through the parameters
     * Precondition: The player objects must have the proper field values
     * Post condition: The player object fields of this class will be initialized as those sent through the parameters
     * @param player1: represents player 1
     * @param player2: represents player 2
     */
    public CollisionCheck(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    /**
     * Method: This method is the main source of all of the conditions in the combat system of the game.It checks if the players can move, and where they can move. It checks whether or not two players are 
     * range to connect attacks with each other. Then it checks if the opponent is able to be attacked (if they are not blocking correctly, or the player
     * is not stunned). They also check the conditions of combos, and apply the appropriate stun frames for each character. It also applies the stun frames
     * and combo capabilities of the special moves of each character
     * @param time- an int that represents the time the game has been played
     */
    public void checkCollision(int time) {
        hitbox_player1 = player1.getHitbox();
        hitbox_player2 = player2.getHitbox();

        if (player1.getHealth() > 0 && player2.getHealth() > 0 && time > 0) { // If both players are alive
            if (player1.getX() + player1.getWidth() > player2.getX() + collisionOffset) { //Checks player one location compared two player two to ensure player one and two do not intersect or cross over
                if (player2.getIsWalkingLeft() && !player1.getIsWalkingRight()) {//This goes on to push the player 1 to the left if player one is not moving right and player 2 is, and pushing player 1
                    if (player1.getX() < 0) {
                        player1.setX(0);
                        player2.setX(player1.getX() + player1.getWidth() - collisionOffset);
                    } else {
                        player1.setX(player2.getX() + collisionOffset - player1.getWidth());
                    }
                } else if (!player2.getIsWalkingLeft() && player1.getIsWalkingRight()) {// This goes on to push player 2 to the right if player 2 is not moving right and player 1 is and pushing player 2
                    if (player2.getX() > 1150) {
                        player2.setX(1150);
                        player1.setX(player2.getX() + collisionOffset - player1.getWidth());
                    } else {
                        player2.setX(player1.getX() + player1.getWidth() - collisionOffset);
                    }
                } else { // If the players are touching but not moving, the xPlayer int are set to set the x values of the players
                    xPlayer1 = player2.getX() + collisionOffset - player1.getWidth();
                    xPlayer2 = player1.getX() + player1.getWidth() - collisionOffset;
                    player1.setX(xPlayer1);
                    player2.setX(xPlayer2);
                }
            } else {// If they are not touching, a limit exists on the X value of each player so that they cannot go off of the map
                if (player1.getX() < 0) {
                    player1.setX(0);
                }

                if (player2.getX() > 1150) {
                    player2.setX(1150);
                }
            }
        }

        if (time <= 0) {// If the time runs out, the player with the least health loses and gets sent off of the screen
            if (player1.getHealth() < player2.getHealth()) {
                player1.setKnockBack(2000);
            } else if (player2.getHealth() < player1.getHealth()) {
                player2.setKnockBack(2000);
            } 
        }

        if (Rectangle.isIntersecting(hitbox_player1, hitbox_player2)) { // if the hitboxes are intersecting, that means that the players are in connecting range from each other
            //The following lines of code dictate whether or not damage is dealt to the opponent based off of what attack the user has done
            if (player1.getIsJumpingOne() && player1.getIsActive() && !player2.getIsBlocking()) {
                player2.setIsHit(true);
                if (hitIndex_player1 < 1) {
                    player2.setHealth(5);
                }
                hitIndex_player1++;
            } else if (player1.getIsJumpingTwo() && player1.getIsActive() && !player2.getIsBlocking()) {
                player2.setIsHit(true);
                if (hitIndex_player1 < 1) {
                    player2.setHealth(7);
                }
                hitIndex_player1++;
            } else if (player1.getIsDownOne() && player1.getIsActive() && !player2.getIsBlocking() && !player2.getIsCrouching()) {
                player2.setIsHit(true);
                if (hitIndex_player1 < 1) {
                    player2.setHealth(5);
                }
                hitIndex_player1++;
            } else if (player1.getIsDownTwo() && player1.getIsActive() && !player2.getIsCrouching()) {
                player2.setIsHit(true);
                if (hitIndex_player1 < 1) {
                    player2.setHealth(7);
                }
                hitIndex_player1++;
            } else if (player1.getIsStandingTwo() && player1.getIsActive() && !player2.getIsBlocking()) {
                player2.setIsHit(true);
                if (hitIndex_player1 < 1) {
                    player2.setHealth(8);
                }
                hitIndex_player1++;
            } else if (player1.getIsSpecial() && player1.getIsActive()) {
                if (player1.getCharTitle().equals("Kasai")) {
                    if (!player2.getIsBlocking() && !player2.isStun()) {
                        player2.setIsHit(true);
                        if (hitIndex_player1 < 1) {
                            player2.setHealth(7);
                            player2.setStun(120);
                        }
                        hitIndex_player1++;
                    }
                } else {
                    if (!player2.getIsCrouching() && !player2.getIsBlocking() && !player2.isStun() || canSpecialCancel) {
                        player2.setIsHit(true);
                        player2.setStun(120);
                        if (hitIndex_player1 < 1) {
                            player2.setHealth(7);
                            player2.setStun(120);
                        }
                        hitIndex_player1++;
                        canSpecialCancel = false;
                    }
                }
            } else if (player1.getIsStandingOne() && player1.getIsActive()) { // The standing one attacks have different amount of minus frames and so, player 2 and player one data will depend on if they are Kasai or Dom
                if (player1.getCharTitle().equals("Kasai")) {
                    if (player2.getIsCrouching()) {
                    player1.setStun(40);
                }
                    if (!player2.getIsCrouching() && !player2.getIsBlocking()) {
                        player2.setIsHit(true);
                        if (hitIndex_player1 < 1) {
                            player2.setHealth(5);
                            comboIndex_player1++;
                        }
                        hitIndex_player1++;
                    }
                } else {
                    if (player2.getIsCrouching() || player2.getIsBlocking()) {
                    player1.setStun(40);
                }
                    if (!player2.getIsCrouching() && !player2.getIsBlocking()) {
                        player2.setIsHit(true);
                        if (hitIndex_player1 < 1) {
                            player2.setHealth(5);
                            comboIndex_player1++;
                        }
                        hitIndex_player1++;
                    }
                }
            } else if (player1.getIsStandingOneOne() && player1.getIsActive()) {
                if (player1.getCharTitle().equals("Kasai")) {
                    if (!player2.getIsCrouching()) {
                        player2.setIsHit(true);
                        if (comboIndex_player1 < 2) {
                            player2.setHealth(5);
                            comboIndex_player1++;
                        }
                        hitIndex_player1++;
                    }
                } else {
                    if (!player2.getIsCrouching() && !player2.getIsBlocking()) {
                        player2.setIsHit(true);
                        if (comboIndex_player1 < 2) {
                            player2.setHealth(5);
                            comboIndex_player1++;
                            player2.setStun(40);
                        }
                        hitIndex_player1++;
                        canSpecialCancel = true;
                    }
                }
            } else if (player1.getIsStandingOneOneOne() && player1.getIsActive()) {
                if (player1.getCharTitle().equals("Kasai")) {
                    if (!player2.getIsCrouching()) {
                        player2.setIsHit(true);
                        if (comboIndex_player1 < 3) {
                            player2.setHealth(5);
                            comboIndex_player1++;
                        }
                        hitIndex_player1++;
                        if (player1.getComboFrame() == 15) {
                            player2.setKnockBack(20);
                        }
                    }
                } else {
                    if (!player2.getIsCrouching() && !player2.getIsBlocking()) {
                        player2.setIsHit(true);
                        if (comboIndex_player1 < 3) {
                            player2.setHealth(5);
                            comboIndex_player1++;
                        }
                        hitIndex_player1++;
                        if (player1.getComboFrame() == 15) {
                            player2.setKnockBack(20);
                        }
                    }
                }
            } else {
                player2.setIsHit(false);
                hitIndex_player1 = 0;
                comboIndex_player1 = 0;
            }

            if (player2.getIsJumpingOne() && player2.getIsActive() && !player1.getIsBlocking()) {
                player1.setIsHit(true);
                if (hitIndex_player2 < 1) {
                    player1.setHealth(5);
                }
                hitIndex_player2++;
            } else if (player2.getIsJumpingTwo() && player2.getIsActive() && !player1.getIsBlocking()) {
                player1.setIsHit(true);
                if (hitIndex_player2 < 1) {
                    player1.setHealth(7);
                }
                hitIndex_player2++;
            } else if (player2.getIsDownOne() && player2.getIsActive() && !player1.getIsBlocking() && !player1.getIsCrouching()) {
                player1.setIsHit(true);
                if (hitIndex_player2 < 1) {
                    player1.setHealth(5);
                }
                hitIndex_player2++;
            } else if (player2.getIsDownTwo() && player2.getIsActive() && !player1.getIsCrouching()) {
                player1.setIsHit(true);
                if (hitIndex_player2 < 1) {
                    player1.setHealth(7);
                }
                hitIndex_player2++;
            } else if (player2.getIsStandingTwo() && player2.getIsActive() && !player1.getIsBlocking()) {
                player1.setIsHit(true);
                if (hitIndex_player2 < 1) {
                    player1.setHealth(8);
                }
                hitIndex_player2++;
            } else if (player2.getIsSpecial() && player2.getIsActive()) {
                if (player2.getCharTitle().equals("Kasai")) {
                    if (!player1.getIsBlocking() && !player1.isStun()) {
                        player1.setIsHit(true);
                        if (hitIndex_player2 < 1) {
                            player1.setHealth(7);
                            player1.setStun(120);
                        }
                        hitIndex_player2++;
                    }
                } else {
                    if (!player1.getIsCrouching() && !player1.getIsBlocking() && !player1.isStun() || canSpecialCancel) {
                        player1.setIsHit(true);
                        if (hitIndex_player2 < 1) {
                            player1.setHealth(7);
                            player1.setStun(120);
                        }
                        hitIndex_player2++;
                        canSpecialCancel = false;
                    }
                }
            } else if (player2.getIsStandingOne() && player2.getIsActive()) {
                if (player2.getCharTitle().equals("Kasai")) {
                    if (player1.getIsCrouching()) {
                        player2.setStun(40);
                    }
                    if (!player1.getIsCrouching()) {
                        player1.setIsHit(true);
                        if (hitIndex_player2 < 1) {
                            player1.setHealth(5);
                            comboIndex_player2++;
                        }
                        hitIndex_player2++;
                    }
                } else {
                    if (player1.getIsCrouching() || player1.getIsBlocking()) {
                        player2.setStun(40);
                    }
                    if (!player1.getIsCrouching() && !player1.getIsBlocking()) {
                        player1.setIsHit(true);
                        if (hitIndex_player2 < 1) {
                            player1.setHealth(5);
                            comboIndex_player2++;
                        }
                        hitIndex_player2++;
                    }
                }
            } else if (player2.getIsStandingOneOne() && player2.getIsActive()) {
                if (player2.getCharTitle().equals("Kasai")) {
                    if (!player1.getIsCrouching()) {
                        player1.setIsHit(true);
                        if (comboIndex_player2 < 2) {
                            player1.setHealth(5);
                            comboIndex_player2++;
                        }
                        hitIndex_player2++;
                    }
                } else {
                    if (!player1.getIsCrouching() && !player1.getIsBlocking()) {
                        player1.setIsHit(true);
                        if (comboIndex_player2 < 2) {
                            player1.setHealth(5);
                            comboIndex_player2++;
                            player1.setStun(40);
                        }
                        hitIndex_player2++;
                        canSpecialCancel = true;
                    }
                }
            } else if (player2.getIsStandingOneOneOne() && player2.getIsActive()) {
                if (player2.getCharTitle().equals("Kasai")) { 
                    if (!player1.getIsCrouching()) {
                        player1.setIsHit(true);
                        if (comboIndex_player2 < 3) {
                            player1.setHealth(5);
                            comboIndex_player2++;
                        }
                        hitIndex_player2++;
                        if (player2.getComboFrame() == 15) {
                            player1.setKnockBack(20);
                        }
                    }
                } else {
                    if (!player1.getIsCrouching() && !player1.getIsBlocking()) {
                        player1.setIsHit(true);
                        if (comboIndex_player2 < 3) {
                            player1.setHealth(5);
                            comboIndex_player2++;
                            player1.setKnockBack(20);
                        }
                        hitIndex_player2++;
                        if (player2.getComboFrame() == 15) {
                            player1.setKnockBack(20);
                        }
                    }
                }
            } else {//If none of the above occurs, no damage, stun or hit has occured
                player1.setIsHit(false);
                hitIndex_player2 = 0;
                comboIndex_player2 = 0;
            }
        } else {
            player1.setIsHit(false);
            player2.setIsHit(false);
        }
    }
}
