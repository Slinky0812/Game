import java.awt.*;
import java.util.*;

public class MenuParticle extends GameObject{
    private Handler handler;
    Random r = new Random();

    private Color color;

    //constructor
    public MenuParticle(float x, float y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;

        velX = (r.nextInt(10) - 5);
        velY = (r.nextInt(10) - 5);
        if (velX == 0) {
            velX = 1;
        } else if (velY == 0) {
            velY = 1;
        }

        color = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
    }


    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 16, 16);
    }

    //makes our enemy move
    public void tick() {
        x += velX;
        y += velY;

        //makes our enemy bounce around the screen when they reach the edges
        if (y <= 0 || y >= Game.HEIGHT - 48) {
            velY *= -1;
        } else if (x <= 0 || x >= Game.WIDTH - 32) {
            velX *= -1;
        }

        handler.addObject(new Trail(x, y, ID.Trail, color, 16, 16, 0.05f, handler));

    }

    //draws our enemy
    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect((int) x, (int) y, 16, 16);
    }
}
