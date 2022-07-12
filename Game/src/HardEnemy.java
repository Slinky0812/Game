import java.awt.*;
import java.util.*;
import java.awt.image.*;

/**
 * a representation of the hard enemy object
 * inherits methods and attributes from GameObject
 * 
 * @author Abdulla Moledina
 */
public class HardEnemy extends GameObject {

    //ATTRIBUTES
    private Random r = new Random();
    private BufferedImage enemy_image;

    /*
     * constructor for the hard enemy object
     * 
     * @param x the x coordinate of the hard enemy
     * @param y the y coordinate of the hard enemy
     * @param ID the ID of the hard enemy
     */
    public HardEnemy(float x, float y, ID id) {
        super(x, y, id);

        velX = 5;
        velY = 5;

        //load the enemy image
        SpriteSheet ss = new SpriteSheet(Game.sprite_sheet);
        enemy_image = ss.grabImage(7, 1, 16, 16);
    }

    /*
     * gets the bounds of the hard enemy and returns it
     * 
     * @return a rectangle representing the bounds of the hard enemy
     */
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 16, 16);
    }

    /*
     * updates the hard enemy and makes it move
     */
    public void tick() {
        x += velX;
        y += velY;

        //makes our enemy bounce around the screen when they reach the edges
        if (y <= 0 || y >= Game.HEIGHT - 48) {
            if (velY < 0) {
                velY = -(r.nextInt(7) + 1) * -1;
            } else {
                velY = (r.nextInt(7) + 1) * -1;
            }
        } else if (x <= 0 || x >= Game.WIDTH - 32) {
            if (velX < 0) {
                velX = -(r.nextInt(7) + 1) * -1;
            } else {
                velX = (r.nextInt(7) + 1) * -1;
            }
        }
    }

    /*
     * renders the hard enemy
     * 
     * @param g the Graphics object to render to
     */
    public void render(Graphics g) {
        g.drawImage(enemy_image, (int) x, (int) y, null);

    }
    
}
