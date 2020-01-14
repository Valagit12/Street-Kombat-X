/**
 * Name: Valareza Arezehgar and Brian Cho (Pack Studios)
 * Date: January 13, 2020
 * Version: 1
 * Description: This class is an image loader that is responsible for accessing images
 */
package gfx;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Pack Studios
 */
public class ImageLoader {
    
    /**
     * Method: This method takes the parameter of the String path to use as an address in the project file and load the image
     * Precondition: path must be a proper address and off type string
     * Post condition: the desired image is loaded
     * @param path: the address on where to locate the specific image
     * @return nothing if the address is inaccurate. If it is correct, then the image with the correct address is returned(filepath)
     */
    public static BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
