import java.awt.*;
import java.util.*;
import java.awt.image.*;

public class HardEnemy extends GameObject {

    private Handler handler;
    private Random r = new Random();
    private BufferedImage enemy_image;

    //constructor
    public HardEnemy(float x, float y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;

        velX = 5;
        velY = 5;

        SpriteSheet ss = new SpriteSheet(Game.sprite_sheet);
        enemy_image = ss.grabImage(7, 1, 16, 16);
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

        //handler.addObject(new Trail(x, y, ID.Trail, Color.yellow, 16, 16, 0.1f, handler));

    }

    //draws our enemy
    public void render(Graphics g) {
        g.drawImage(enemy_image, (int) x, (int) y, null);

    }
    
}
