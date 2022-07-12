import java.awt.*;
import java.awt.image.*;

/**
 * a representation of the fast enemy object
 * inherits methods and attributes from GameObject
 * 
 * @author Abdulla Moledina
 */
public class FastEnemy extends GameObject{

    //ATTRIBUTE
    private BufferedImage enemy_image;

    /*
     * constructor for the fast enemy object
     * 
     * @param x the x coordinate of the enemy
     * @param y the y coordinate of the enemy
     * @param ID the ID of the enemy
     */
    public FastEnemy(float x, float y, ID id) {
        super(x, y, id);

        velX = 2;
        velY = 9;

        //load the enemy image
        SpriteSheet ss = new SpriteSheet(Game.sprite_sheet);
        enemy_image = ss.grabImage(5, 1, 16, 16);
    }

    /*
     * gets the bounds of the enemy and returns it
     * 
     * @return a rectangle representing the bounds of the enemy
     */
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 16, 16);
    }

    /*
     * updates the enemy and makes it move
     */
    public void tick() {
        x += velX;
        y += velY;

        //makes our enemy bounce around the screen when they reach the edges
        if (y <= 0 || y >= Game.HEIGHT - 48) {
            velY *= -1;
        } else if (x <= 0 || x >= Game.WIDTH - 32) {
            velX *= -1;
        }
    }

    /*
     * renders the enemy
     * 
     * @param g the graphics object
     */
    public void render(Graphics g) {
        g.drawImage(enemy_image, (int) x, (int) y, null);

    }
}
