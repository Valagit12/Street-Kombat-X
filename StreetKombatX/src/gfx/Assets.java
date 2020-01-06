/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gfx;

import java.awt.image.BufferedImage;

/**
 *
 * @author h9113
 */
public class Assets {
    
    //Backgrounds
    public static BufferedImage[] fireTemple;
    
    //Animations
    public static BufferedImage[] kasai_stance_player1, kasai_walk_left_player1, kasai_walk_right_player1, kasai_block_player1, kasai_crouch_player1, kasai_jump_player1, kasai_hit_player1, kasai_jump2_player1, kasai_jump1_player1;
    public static BufferedImage[] kasai_down1_player1;
    public static BufferedImage[] kasai_stance_player2, kasai_walk_left_player2, kasai_walk_right_player2, kasai_block_player2, kasai_crouch_player2, kasai_jump_player2, kasai_hit_player2, kasai_jump2_player2, kasai_jump1_player2;
    public static BufferedImage[] kasai_down1_player2;
    
    public static BufferedImage[] dom_stance_player1, dom_walk_left_player1, dom_walk_right_player1, dom_block_player1, dom_crouch_player1, dom_jump_player1, dom_hit_player1, dom_jump2_player1, dom_jump1_player1;
    public static BufferedImage[] dom_stance_player2, dom_walk_left_player2, dom_walk_right_player2, dom_block_player2, dom_crouch_player2, dom_jump_player2, dom_hit_player2, dom_jump2_player2, dom_jump1_player2;
    
    public static void init() {
        SpriteSheet kasai_sheet_player1 = new SpriteSheet(ImageLoader.loadImage("res/SpriteSheet/Kasai/Player1/Kasai_SpriteSheet.png"), 32, 64, 14);
        SpriteSheet kasai_sheet_player2 = new SpriteSheet(ImageLoader.loadImage("res/SpriteSheet/Kasai/Player2/Kasai_SpriteSheet.png"), 32, 64, 14);
        SpriteSheet dom_sheet_player2 = new SpriteSheet(ImageLoader.loadImage("res/SpriteSheet/Dom/Player2/Dom_SpriteSheet.png"), 32, 64, 14);
        SpriteSheet dom_sheet_player1 = new SpriteSheet(ImageLoader.loadImage("res/SpriteSheet/Dom/Player1/Dom_SpriteSheet.png"), 32, 64, 14);
        //Backgrounds
        SpriteSheet fireTemple_sheet = new SpriteSheet(ImageLoader.loadImage("res/backgrounds/FireTemple.png"), 1280, 720, 3);
        
        fireTemple = new BufferedImage[8];
        
        for (int i = 0; i < fireTemple.length; i++){
            fireTemple[i] = fireTemple_sheet.crop(i);
        }
        
        //Kasai Player1
        kasai_stance_player1 = new BufferedImage[8];
        kasai_walk_left_player1 = new BufferedImage[10];
        kasai_walk_right_player1 = new BufferedImage[10];
        kasai_block_player1 = new BufferedImage[7];
        kasai_crouch_player1 = new BufferedImage[8];
        kasai_jump_player1 = new BufferedImage[7];
        kasai_hit_player1 = new BufferedImage[7];
        kasai_jump2_player1 = new BufferedImage[1];
        kasai_jump1_player1 = new BufferedImage[6];
        kasai_down1_player1 = new BufferedImage[8];
        
        for (int i = 0; i < kasai_stance_player1.length; i++){
            kasai_stance_player1[i] = kasai_sheet_player1.crop(i);
        }
        
        for (int i = 0; i < kasai_walk_left_player1.length; i++){
            kasai_walk_right_player1[i] = kasai_sheet_player1.crop(i+14);
            kasai_walk_left_player1[kasai_walk_left_player1.length - 1 - i] = kasai_walk_right_player1[i];
        }
        
        for (int i = 0; i < kasai_block_player1.length; i++){
            kasai_block_player1[i] = kasai_sheet_player1.crop(i+14*2);
        }
        
        for (int i = 0; i < kasai_crouch_player1.length; i++){
            kasai_crouch_player1[i] = kasai_sheet_player1.crop(i+14*3);
        }
        
        for (int i = 0; i < kasai_jump_player1.length; i++){
            kasai_jump_player1[i] = kasai_sheet_player1.crop(i + 14*5);
        }
        
        for (int i = 0; i < kasai_hit_player1.length; i++) {
            kasai_hit_player1[i] = kasai_sheet_player1.crop(i + 14*4);
        }
        
        kasai_jump2_player1[0] = kasai_sheet_player1.crop(84);
        
        for (int i = 0; i < kasai_jump1_player1.length; i++) {
            kasai_jump1_player1[i] = kasai_sheet_player1.crop(i + 2 + 14*6);
        }
        
        for (int i = 0; i < kasai_down1_player1.length; i++){
            kasai_down1_player1[i] = kasai_sheet_player1.crop(i + 14*7);
        }
        
        //Kasai Player2
        kasai_stance_player2 = new BufferedImage[8];
        kasai_walk_left_player2 = new BufferedImage[10];
        kasai_walk_right_player2 = new BufferedImage[10];
        kasai_block_player2 = new BufferedImage[7];
        kasai_crouch_player2 = new BufferedImage[8];
        kasai_jump_player2 = new BufferedImage[7];
        kasai_hit_player2 = new BufferedImage[7];
        kasai_jump2_player2 = new BufferedImage[1];
        kasai_jump1_player2 = new BufferedImage[6];
        kasai_down1_player2 = new BufferedImage[8];
        
        for (int i = 0; i < kasai_stance_player2.length; i++){
            kasai_stance_player2[i] = kasai_sheet_player2.crop(i);
        }
        
        for (int i = 0; i < kasai_walk_left_player2.length; i++){
            kasai_walk_left_player2[i] = kasai_sheet_player2.crop(i+14);
            kasai_walk_right_player2[kasai_walk_right_player2.length - 1 - i] = kasai_walk_left_player2[i];
        }
        
        for (int i = 0; i < kasai_block_player2.length; i++){
            kasai_block_player2[i] = kasai_sheet_player2.crop(i+14*2);
        }
        
        for (int i = 0; i < kasai_crouch_player2.length; i++){
            kasai_crouch_player2[i] = kasai_sheet_player2.crop(i+14*3);
        }
        
        for (int i = 0; i < kasai_jump_player2.length; i++){
            kasai_jump_player2[i] = kasai_sheet_player2.crop(i + 14*5);
        }
        
        for(int i = 0; i < kasai_hit_player2.length; i++) {
            kasai_hit_player2[i] = kasai_sheet_player2.crop(i + 14*4);
        }
        
        kasai_jump2_player2[0] = kasai_sheet_player2.crop(84);
        
        for (int i = 0; i < kasai_jump1_player2.length; i++) {
            kasai_jump1_player2[i] = kasai_sheet_player2.crop(i + 2 + 14*6);
        }
        
        for (int i = 0; i < kasai_down1_player2.length; i++){
            kasai_down1_player2[i] = kasai_sheet_player2.crop(i + 14*7);
        }
        
        
        //Dom player 1
        dom_stance_player1 = new BufferedImage[8];
        dom_walk_left_player1 = new BufferedImage[10];
        dom_walk_right_player1 = new BufferedImage[10];
        dom_block_player1 = new BufferedImage[7];
        dom_crouch_player1 = new BufferedImage[8];
        dom_jump_player1 = new BufferedImage[7];
        dom_hit_player1 = new BufferedImage[7];
        dom_jump2_player1 = new BufferedImage[1];
        dom_jump1_player1 = new BufferedImage[6];
        
        
        for (int i = 0; i < dom_stance_player1.length; i++){
            dom_stance_player1[i] = dom_sheet_player1.crop(i);
        }
        
        for (int i = 0; i < dom_walk_left_player1.length; i++){
            dom_walk_right_player1[i] = dom_sheet_player1.crop(i+14);
            dom_walk_left_player1[dom_walk_left_player1.length - 1 - i] = dom_walk_right_player1[i];
        }
        
        for (int i = 0; i < dom_block_player1.length; i++){
            dom_block_player1[i] = dom_sheet_player1.crop(i+14*2);
        }
        
        for (int i = 0; i < dom_crouch_player1.length; i++){
            dom_crouch_player1[i] = dom_sheet_player1.crop(i+14*3);
        }
        
        for (int i = 0; i < dom_jump_player1.length; i++){
            dom_jump_player1[i] = dom_sheet_player1.crop(i + 14*5);
        }
        
        for (int i = 0; i < dom_hit_player1.length; i++) {
            dom_hit_player1[i] = dom_sheet_player1.crop(i + 14*4);
        }
        
        dom_jump2_player1[0] = dom_sheet_player1.crop(84);
        
        for (int i = 0; i < dom_jump1_player1.length; i++) {
            dom_jump1_player1[i] = dom_sheet_player1.crop(i + 2 + 14*6);
        }
    }
    
}
