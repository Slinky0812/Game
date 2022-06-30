import java.awt.*;
import java.util.*;

public class EnemyBossBullet extends GameObject {

    private Handler handler;
    Random r = new Random(); 

    //constructor
    public EnemyBossBullet(float x, float y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;

        //gives us a random x value from 5 to -5
        velX = (r.nextInt(5 - -5) + -5);
        velY = 5;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 16, 16);
    }

    //makes our enemy move
    public void tick() {
        x += velX;
        y += velY;

        //makes our enemy bounce around the screen when they reach the edges
        // if (y <= 0 || y >= Game.HEIGHT - 48) {
        //     velY *= -1;
        // } else if (x <= 0 || x >= Game.WIDTH - 32) {
        //     velX *= -1;
        // }

        //remove object from the game
        if (y > Game.HEIGHT) {
            handler.removeObject(this);
        }

        handler.addObject(new Trail(x, y, ID.Trail, Color.red, 16, 16, 0.1f, handler));

    }

    //draws our enemy
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int) x, (int) y, 16, 16);
    }
    
}
