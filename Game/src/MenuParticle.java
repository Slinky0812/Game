import java.awt.*;
import java.util.*;

/**
 * a representation of the menu particles used in the background of the menu screen
 * inherits methods and attributes from GameObject
 * 
 * @author Abdulla Moledina
 */
public class MenuParticle extends GameObject{

    //ATTRIBUTES
    private Handler handler;
    Random r = new Random();
    private Color color;

    /*
     * constructor for the menu particles
     * 
     * @param x the x coordinate of the menu particle
     * @param y the y coordinate of the menu particle
     * @param ID the ID of the menu particle
     * @param handler the handler of the game
     */
    public MenuParticle(float x, float y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;

        //gives us a random speed for each bullet from 5 to -5
        velX = (r.nextInt(10) - 5);
        velY = (r.nextInt(10) - 5);
        if (velX == 0) {
            velX = 1;
        } else if (velY == 0) {
            velY = 1;
        }

        color = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
    }

    /*
     * gets the bounds of the menu particle and returns it
     * 
     * @return a rectangle representing the bounds of the menu particle
     */
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 16, 16);
    }

    /*
     * updates the menu particle and makes it move
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

        //adds a trail for every menu particle
        handler.addObject(new Trail(x, y, ID.Trail, color, 16, 16, 0.05f, handler));

    }

    /*
     * renders the menu particle
     * 
     * @param g the graphics object
     */
    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect((int) x, (int) y, 16, 16);
    }
}
