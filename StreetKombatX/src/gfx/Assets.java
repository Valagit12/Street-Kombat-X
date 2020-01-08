/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gfx;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author h9113
 */
public class Assets {
    
    //Font
    public static Font dragonForce;
    public static Font dragonForceNum;
    public static Font dragonForceEndScreen;
    
    //Backgrounds
    public static BufferedImage[] fireTemple;
    public static BufferedImage[] kingdom;
    
    //Animations
    public static BufferedImage[] kasai_stance_player1, kasai_walk_left_player1, kasai_walk_right_player1, kasai_block_player1, kasai_crouch_player1, kasai_jump_player1, kasai_hit_player1, kasai_jump2_player1, kasai_jump1_player1;
    public static BufferedImage[] kasai_down1_player1, kasai_down2_player1, kasai_2_player1, kasai_1_player1, kasai_11_player1, kasai_111_player1, kasai_special_player1;
    public static BufferedImage[] kasai_stance_player2, kasai_walk_left_player2, kasai_walk_right_player2, kasai_block_player2, kasai_crouch_player2, kasai_jump_player2, kasai_hit_player2, kasai_jump2_player2, kasai_jump1_player2;
    public static BufferedImage[] kasai_down1_player2, kasai_down2_player2, kasai_2_player2, kasai_1_player2, kasai_11_player2, kasai_111_player2, kasai_special_player2;
    
    public static BufferedImage[] dom_stance_player1, dom_walk_left_player1, dom_walk_right_player1, dom_block_player1, dom_crouch_player1, dom_jump_player1, dom_hit_player1, dom_jump2_player1, dom_jump1_player1;
    public static BufferedImage[] dom_down1_player1, dom_down2_player1, dom_2_player1, dom_1_player1, dom_11_player1, dom_111_player1, dom_special_player1, dom_special_cancel_player1;
    public static BufferedImage[] dom_stance_player2, dom_walk_left_player2, dom_walk_right_player2, dom_block_player2, dom_crouch_player2, dom_jump_player2, dom_hit_player2, dom_jump2_player2, dom_jump1_player2;
    public static BufferedImage[] dom_down1_player2, dom_down2_player2, dom_2_player2, dom_1_player2, dom_11_player2, dom_111_player2, dom_special_player2, dom_special_cancel_player2;
    
    public static void init() {
        try{
            dragonForce = Font.createFont(Font.TRUETYPE_FONT, new File("res/font/DragonForcE.ttf")).deriveFont(Font.PLAIN, 50f);
            dragonForceNum = Font.createFont(Font.TRUETYPE_FONT, new File("res/font/DragonForcE.ttf")).deriveFont(Font.PLAIN, 80f);
            dragonForceEndScreen = Font.createFont(Font.TRUETYPE_FONT, new File("res/font/DragonForcE.ttf")).deriveFont(Font.PLAIN, 120f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(dragonForce);
            ge.registerFont(dragonForceNum);
            ge.registerFont(dragonForceEndScreen);
        } catch (IOException e){
            e.printStackTrace();
            System.out.println("hi");
        } catch (FontFormatException ex) {
            ex.printStackTrace();
            System.out.println("hi");
        }
        
        SpriteSheet kasai_sheet_player1 = new SpriteSheet(ImageLoader.loadImage("res/SpriteSheet/Kasai/Player1/Kasai_SpriteSheet.png"), 32, 64, 21);
        SpriteSheet kasai_sheet_player2 = new SpriteSheet(ImageLoader.loadImage("res/SpriteSheet/Kasai/Player2/Kasai_SpriteSheet.png"), 32, 64, 21);
        SpriteSheet dom_sheet_player2 = new SpriteSheet(ImageLoader.loadImage("res/SpriteSheet/Dom/Player2/Dom_SpriteSheet.png"), 32, 64, 21);
        SpriteSheet dom_sheet_player1 = new SpriteSheet(ImageLoader.loadImage("res/SpriteSheet/Dom/Player1/Dom_SpriteSheet.png"), 32, 64, 21);
        //Backgrounds
        SpriteSheet fireTemple_sheet = new SpriteSheet(ImageLoader.loadImage("res/backgrounds/FireTemple.png"), 1280, 720, 3);
        SpriteSheet kingdom_sheet = new SpriteSheet (ImageLoader.loadImage("res/backgrounds/King Dom's Kingdom.gif"), 1280, 720, 3);
        
        fireTemple = new BufferedImage[8];
        kingdom = new BufferedImage[8];
        
        for (int i = 0; i < fireTemple.length; i++){
            fireTemple[i] = fireTemple_sheet.crop(i);
        }
        
        for (int i = 0; i < kingdom.length; i++){
            kingdom[i] = kingdom_sheet.crop(i);
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
        kasai_down2_player1 = new BufferedImage[9];
        kasai_2_player1 = new BufferedImage[8];
        kasai_1_player1 = new BufferedImage[10];
        kasai_11_player1 = new BufferedImage[16];
        kasai_111_player1 = new BufferedImage[21];
        kasai_special_player1 = new BufferedImage[11];
        
        for (int i = 0; i < kasai_stance_player1.length; i++){
            kasai_stance_player1[i] = kasai_sheet_player1.crop(i);
        }
        
        for (int i = 0; i < kasai_walk_left_player1.length; i++){
            kasai_walk_right_player1[i] = kasai_sheet_player1.crop(i+21);
            kasai_walk_left_player1[kasai_walk_left_player1.length - 1 - i] = kasai_walk_right_player1[i];
        }
        
        for (int i = 0; i < kasai_block_player1.length; i++){
            kasai_block_player1[i] = kasai_sheet_player1.crop(i+21*2);
        }
        
        for (int i = 0; i < kasai_crouch_player1.length; i++){
            kasai_crouch_player1[i] = kasai_sheet_player1.crop(i+21*3);
        }
        
        for (int i = 0; i < kasai_jump_player1.length; i++){
            kasai_jump_player1[i] = kasai_sheet_player1.crop(i + 21*5);
        }
        
        for (int i = 0; i < kasai_hit_player1.length; i++) {
            kasai_hit_player1[i] = kasai_sheet_player1.crop(i + 21*4);
        }
        
        kasai_jump2_player1[0] = kasai_sheet_player1.crop(126);
        
        for (int i = 0; i < kasai_jump1_player1.length; i++) {
            kasai_jump1_player1[i] = kasai_sheet_player1.crop(i + 2 + 21*6);
        }
        
        for (int i = 0; i < kasai_down1_player1.length; i++){
            kasai_down1_player1[i] = kasai_sheet_player1.crop(i + 21*7);
        }
        
        for (int i = 0; i < kasai_down2_player1.length; i++){
            kasai_down2_player1[i] = kasai_sheet_player1.crop(i + 21*8);
        }
        
        for (int i = 0; i < kasai_2_player1.length; i++){
            kasai_2_player1[i] = kasai_sheet_player1.crop(i + 21*9);
        }
        
        for(int i = 0; i < kasai_1_player1.length; i++){
            kasai_1_player1[i] = kasai_sheet_player1.crop(i + 21*10);
        }
        
        for(int i = 0; i < kasai_11_player1.length; i++){
            kasai_11_player1[i] = kasai_sheet_player1.crop(i + 21*11);
        }
        
        for(int i = 0; i < kasai_111_player1.length; i++){
            kasai_111_player1[i] = kasai_sheet_player1.crop(i + 21*12);
        }
        
        for(int i = 0; i < kasai_special_player1.length; i++){
            kasai_special_player1[i] = kasai_sheet_player1.crop(i + 21*13);
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
        kasai_down2_player2 = new BufferedImage[9];
        kasai_2_player2 = new BufferedImage[8];
        kasai_1_player2 = new BufferedImage[10];
        kasai_11_player2 = new BufferedImage[16];
        kasai_111_player2 = new BufferedImage[21];
        kasai_special_player2 = new BufferedImage[11];
        
        for (int i = 0; i < kasai_stance_player2.length; i++){
            kasai_stance_player2[i] = kasai_sheet_player2.crop(i);
        }
        
        for (int i = 0; i < kasai_walk_left_player2.length; i++){
            kasai_walk_left_player2[i] = kasai_sheet_player2.crop(i+21);
            kasai_walk_right_player2[kasai_walk_right_player2.length - 1 - i] = kasai_walk_left_player2[i];
        }
        
        for (int i = 0; i < kasai_block_player2.length; i++){
            kasai_block_player2[i] = kasai_sheet_player2.crop(i+21*2);
        }
        
        for (int i = 0; i < kasai_crouch_player2.length; i++){
            kasai_crouch_player2[i] = kasai_sheet_player2.crop(i+21*3);
        }
        
        for (int i = 0; i < kasai_jump_player2.length; i++){
            kasai_jump_player2[i] = kasai_sheet_player2.crop(i + 21*5);
        }
        
        for(int i = 0; i < kasai_hit_player2.length; i++) {
            kasai_hit_player2[i] = kasai_sheet_player2.crop(i + 21*4);
        }
        
        kasai_jump2_player2[0] = kasai_sheet_player2.crop(126);
        
        for (int i = 0; i < kasai_jump1_player2.length; i++) {
            kasai_jump1_player2[i] = kasai_sheet_player2.crop(i + 2 + 21*6);
        }
        
        for (int i = 0; i < kasai_down1_player2.length; i++){
            kasai_down1_player2[i] = kasai_sheet_player2.crop(i + 21*7);
        }
        
        for (int i = 0; i < kasai_down2_player2.length; i++){
            kasai_down2_player2[i] = kasai_sheet_player2.crop(i + 21*8);
        }
        
        for (int i = 0; i < kasai_2_player2.length; i++){
            kasai_2_player2[i] = kasai_sheet_player2.crop(i + 21*9);
        }
        
        for(int i = 0; i < kasai_1_player2.length; i++){
            kasai_1_player2[i] = kasai_sheet_player2.crop(i + 21*10);
        }
        
        for(int i = 0; i < kasai_11_player2.length; i++){
            kasai_11_player2[i] = kasai_sheet_player2.crop(i + 21*11);
        }
        
        for(int i = 0; i < kasai_111_player2.length; i++){
            kasai_111_player2[i] = kasai_sheet_player2.crop(i + 21*12);
        }
        
        for(int i = 0; i < kasai_special_player2.length; i++){
            kasai_special_player2[i] = kasai_sheet_player2.crop(i + 21*13);
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
        dom_down1_player1 = new BufferedImage[8];
        dom_down2_player1 = new BufferedImage[9];
        dom_2_player1 = new BufferedImage[8];
        dom_1_player1 = new BufferedImage[10];
        dom_11_player1 = new BufferedImage[16];
        dom_111_player1 = new BufferedImage[21];
        dom_special_player1 = new BufferedImage[11];
        dom_special_cancel_player1 = new BufferedImage[24];
        
        
        for (int i = 0; i < dom_stance_player1.length; i++){
            dom_stance_player1[i] = dom_sheet_player1.crop(i);
        }
        
        for (int i = 0; i < dom_walk_left_player1.length; i++){
            dom_walk_right_player1[i] = dom_sheet_player1.crop(i+21);
            dom_walk_left_player1[dom_walk_left_player1.length - 1 - i] = dom_walk_right_player1[i];
        }
        
        for (int i = 0; i < dom_block_player1.length; i++){
            dom_block_player1[i] = dom_sheet_player1.crop(i+21*2);
        }
        
        for (int i = 0; i < dom_crouch_player1.length; i++){
            dom_crouch_player1[i] = dom_sheet_player1.crop(i+21*3);
        }
        
        for (int i = 0; i < dom_jump_player1.length; i++){
            dom_jump_player1[i] = dom_sheet_player1.crop(i + 21*5);
        }
        
        for (int i = 0; i < dom_hit_player1.length; i++) {
            dom_hit_player1[i] = dom_sheet_player1.crop(i + 21*4);
        }
        
        dom_jump2_player1[0] = dom_sheet_player1.crop(126);
        
        for (int i = 0; i < dom_jump1_player1.length; i++) {
            dom_jump1_player1[i] = dom_sheet_player1.crop(i + 2 + 21*6);
        }
        
        for (int i = 0; i < dom_down1_player1.length; i++){
            dom_down1_player1[i] = dom_sheet_player1.crop(i + 21*7);
        }
        
        for (int i = 0; i < dom_down2_player1.length; i++){
            dom_down2_player1[i] = dom_sheet_player1.crop(i + 21*8);
        }
        
        for (int i = 0; i < dom_2_player1.length; i++){
            dom_2_player1[i] = dom_sheet_player1.crop(i + 21*9);
        }
        
        for(int i = 0; i < dom_1_player1.length; i++){
            dom_1_player1[i] = dom_sheet_player1.crop(i + 21*10);
        }
        
        for(int i = 0; i < dom_11_player1.length; i++){
            dom_11_player1[i] = dom_sheet_player1.crop(i + 21*11);
        }
        
        for(int i = 0; i < dom_111_player1.length; i++){
            dom_111_player1[i] = dom_sheet_player1.crop(i + 21*12);
        }
        
        for(int i = 0; i < dom_special_player1.length; i++){
            dom_special_player1[i] = dom_sheet_player1.crop(i + 21*13);
        }
        
        for (int i = 0; i < 14; i++){
            dom_special_cancel_player1[i] = dom_sheet_player1.crop(i+21*12);
        }
        
        for (int i = 14; i < dom_special_cancel_player1.length; i++){
            dom_special_cancel_player1[i] = dom_sheet_player1.crop(i+21*13 -14);
        }
        
        //Dom Player 2
        dom_stance_player2 = new BufferedImage[8];
        dom_walk_left_player2 = new BufferedImage[10];
        dom_walk_right_player2 = new BufferedImage[10];
        dom_block_player2 = new BufferedImage[7];
        dom_crouch_player2 = new BufferedImage[8];
        dom_jump_player2 = new BufferedImage[7];
        dom_hit_player2 = new BufferedImage[7];
        dom_jump2_player2 = new BufferedImage[1];
        dom_jump1_player2 = new BufferedImage[6];
        dom_down1_player2 = new BufferedImage[8];
        dom_down2_player2 = new BufferedImage[9];
        dom_2_player2 = new BufferedImage[8];
        dom_1_player2 = new BufferedImage[10];
        dom_11_player2 = new BufferedImage[16];
        dom_111_player2 = new BufferedImage[21];
        dom_special_player2 = new BufferedImage[11];
        dom_special_cancel_player2 = new BufferedImage[24];
        
        
        for (int i = 0; i < dom_stance_player2.length; i++){
            dom_stance_player2[i] = dom_sheet_player2.crop(i);
        }
        
        for (int i = 0; i < dom_walk_left_player2.length; i++){
            dom_walk_right_player2[i] = dom_sheet_player2.crop(i+21);
            dom_walk_left_player2[dom_walk_left_player2.length - 1 - i] = dom_walk_right_player2[i];
        }
        
        for (int i = 0; i < dom_block_player2.length; i++){
            dom_block_player2[i] = dom_sheet_player2.crop(i+21*2);
        }
        
        for (int i = 0; i < dom_crouch_player2.length; i++){
            dom_crouch_player2[i] = dom_sheet_player2.crop(i+21*3);
        }
        
        for (int i = 0; i < dom_jump_player2.length; i++){
            dom_jump_player2[i] = dom_sheet_player2.crop(i + 21*5);
        }
        
        for (int i = 0; i < dom_hit_player2.length; i++) {
            dom_hit_player2[i] = dom_sheet_player2.crop(i + 21*4);
        }
        
        dom_jump2_player2[0] = dom_sheet_player2.crop(126);
        
        for (int i = 0; i < dom_jump1_player2.length; i++) {
            dom_jump1_player2[i] = dom_sheet_player2.crop(i + 2 + 21*6);
        }
        
        for (int i = 0; i < dom_down1_player2.length; i++){
            dom_down1_player2[i] = dom_sheet_player2.crop(i + 21*7);
        }
        
        for (int i = 0; i < dom_down2_player2.length; i++){
            dom_down2_player2[i] = dom_sheet_player2.crop(i + 21*8);
        }
        
        for (int i = 0; i < dom_2_player2.length; i++){
            dom_2_player2[i] = dom_sheet_player2.crop(i + 21*9);
        }
        
        for(int i = 0; i < dom_1_player2.length; i++){
            dom_1_player2[i] = dom_sheet_player2.crop(i + 21*10);
        }
        
        for(int i = 0; i< dom_11_player2.length; i++){
            dom_11_player2[i] = dom_sheet_player2.crop(i + 21*11);
        }
        
        for(int i = 0; i < dom_111_player2.length; i++){
            dom_111_player2[i] = dom_sheet_player2.crop(i + 21*12);
        }
        
        for(int i = 0; i < dom_special_player2.length; i++){
            dom_special_player2[i] = dom_sheet_player2.crop(i + 21*13);
        }
        
        for (int i = 0; i < 14; i++){
            dom_special_cancel_player2[i] = dom_sheet_player2.crop(i+21*12);
        }
        
        for (int i = 14; i < dom_special_cancel_player1.length; i++){
            dom_special_cancel_player2[i] = dom_sheet_player2.crop(i+21*13-14);
        }
    }
    
}
