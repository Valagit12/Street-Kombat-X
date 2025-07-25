/**
 * Name: Valareza Arezehgar and Brian Cho (Pack Studios)
 * Date: January 13, 2020
 * Version: 1
 * Description: This class is responsible for playing music throughout the game
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
 * @author Pack Studios
 */
public class Music {

    private Clip clip; //This clip will be used to control the audio file
    private AudioInputStream audioInputStream;// Audio file
    private String filePath;//the address of the audio file

    /**
     * Method: This method loads in and plays the game's music continuously 
     * Precondition: The desired audio file must be a wav file and must be in the right location, so that the address matches the location of the file
     * Post condition: The audio file loops
     * @throws UnsupportedAudioFileException: If the audio file is not supported
     * @throws IOException: Input output exception if the desired file does not match
     * @throws LineUnavailableException: if the music file is unavailable 
     */
    public Music() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
            filePath = "StreetKombatX/Menu OST.wav";
        
         audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
}
