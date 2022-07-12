import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;

/**
 * a class to load the image of the player and enemies
 * 
 * @author Abdulla Moledina
 */
public class BufferedImageLoader {
    
    //ATTRIBUTE
    BufferedImage image;

    /*
     * loads the image of the player and enemies
     * 
     * @param path the filepath of the image
     * @return the image of the player and enemies
     */
    public BufferedImage loadImage(String path) {
        try {
            //image = ImageIO.read(getClass().getResource("/res/sprite_sheet.png"));
            image = ImageIO.read(new FileImageInputStream(new File(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

}
