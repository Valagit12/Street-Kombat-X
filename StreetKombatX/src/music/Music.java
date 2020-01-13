/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package music;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import states.State;

/**
 *
 * @author Valareza
 */
public class Music {

    private Long currentFrame;
    private Clip clip;
    private String Status;
    private AudioInputStream audioInputStream;
    private String filePath;

    public Music() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
            filePath = "Menu OST.wav";
        
         audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
}
