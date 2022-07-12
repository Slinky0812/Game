import java.awt.image.*;

/**
 * a class to load the sprite sheet and find the subimages of the player and enemies
 * 
 * @author Abdulla Moledina
 */
public class SpriteSheet {

    //ATTRIBUTE
    private BufferedImage sprite;

    /*
     * constructor for the sprite sheet
     * 
     * @param image the image of player/enemy
     */
    public SpriteSheet(BufferedImage image) {
        this.sprite = image;
    }

    /*
     * grabs the subimage of the player/enemy
     * 
     * @param col the column of the subimage
     * @param row the row of the subimage
     * @param width the width of the subimage
     * @param height the height of the subimage
     * @return the subimage of the player/enemy
     */
    public BufferedImage grabImage(int col, int row, int width, int height) {
        BufferedImage img = sprite.getSubimage((col * width) - width, (row * height) - height, width, height);
        return img;
    }
}
