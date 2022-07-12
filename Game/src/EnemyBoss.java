import java.awt.*;
import java.util.*;

/**
 * a representation of the enemy boss object
 * inherits methods and attributes from GameObject
 * 
 * @author Abdulla Moledina
 */
public class EnemyBoss extends GameObject {

    //ATTRIBUTES
    private Handler handler;
    private int timer = 80;
    private int timer2 = 50;
    Random r = new Random();

    /*
     * constructor for the enemy boss object
     * 
     * @param x the x coordinate of the enemy boss
     * @param y the y coordinate of the enemy boss
     * @param ID the ID of the enemy boss
     * @param handler the handler of the game
     */
    public EnemyBoss(float x, float y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;

        velX = 0;
        velY = 2;
    }

    /*
     * gets the bounds of the enemy boss and returns it
     * 
     * @return a rectangle representing the bounds of the enemy boss
     */
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 64, 64);
    }

    /*
     * updates the enemy boss and makes it move
     */
    public void tick() {
        x += velX;
        y += velY;

        //makes the boss slow down when it reaches the edge of the screen
        if (timer <= 0) {
            velY = 0;
        } else {
            timer -= 1;
        }

        if (timer <= 0) {
            timer2 -= 1;   
        }

        if (timer2 <= 0) {
            if (velX == 0) {
                velX = 2;
            }

            if (velX > 0) {
                velX += 0.1;
            } else {
                velX -= 0.1;
            }

            velX = Game.clamp(velX, -10, 10);
        
            //spawns bullets randomly
            int spawn = r.nextInt(10);
            if (spawn == 0) {
                handler.addObject(new EnemyBossBullet(x + 32, y + 32, ID.BasicEnemy, handler));
            }
        }
        
        //makes our enemy bounce around the screen when they reach the edges
        if (x <= 0 || x >= Game.WIDTH - 64) {
            velX *= -1;
        }

        //adds a trail to the boss
        handler.addObject(new Trail(x, y, ID.Trail, Color.red, 64, 64, 0.1f, handler));

    }

    /*
     * renders the enemy boss
     * 
     * @param g the graphics object
     */
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int) x, (int) y, 64, 64);
    }
    
}