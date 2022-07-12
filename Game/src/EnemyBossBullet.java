import java.awt.*;
import java.util.*;

/**
 * a representation of the bullets that the boss shoots at the player
 * inherits methods and attributes from GameObject
 * 
 * @author Abdulla Moledina
 */
public class EnemyBossBullet extends GameObject {

    //ATTRIBUTES
    private Handler handler;
    Random r = new Random(); 

    /*
     * constructor for the enemy boss bullet object
     * 
     * @param x the x coordinate of the enemy boss bullet
     * @param y the y coordinate of the enemy boss bullet
     * @param ID the ID of the enemy boss bullet
     * @param handler the handler of the game
     */
    public EnemyBossBullet(float x, float y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;

        //gives us a random x value from 5 to -5
        velX = (r.nextInt(10) + -5);
        velY = 5;
    }

    /*
     * gets the bounds of the enemy boss bullet and returns it
     * 
     * @return a rectangle representing the bounds of the enemy boss bullet
     */
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 16, 16);
    }

    /*
     * updates the enemy boss bullet and makes it move
     */
    public void tick() {
        x += velX;
        y += velY;

        //remove object from the game once they are off the screen
        if (y > Game.HEIGHT) {
            handler.removeObject(this);
        }

        //adds a trail to the end of the bullet
        handler.addObject(new Trail(x, y, ID.Trail, Color.red, 16, 16, 0.1f, handler));

    }

    /*
     * renders the enemy boss bullet
     * 
     * @param g the graphics object
     */
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int) x, (int) y, 16, 16);
    }
    
}
