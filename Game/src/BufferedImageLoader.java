import java.awt.image.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;

public class BufferedImageLoader {
    
    BufferedImage image;

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
