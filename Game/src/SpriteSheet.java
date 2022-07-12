import java.awt.image.*;

public class SpriteSheet {
    private BufferedImage sprite;

    public SpriteSheet(BufferedImage image) {
        this.sprite = image;
    }

    //finds the image in our spritesheet and returns it
    public BufferedImage grabImage(int col, int row, int width, int height) {
        BufferedImage img = sprite.getSubimage((col * width) - width, (row * height) - height, width, height);
        return img;
    }
}
