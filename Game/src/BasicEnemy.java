import java.awt.*;
import java.awt.image.*;

public class BasicEnemy extends GameObject {

    private Handler handler;
    private BufferedImage enemy_image;

    //constructor
    public BasicEnemy(float x, float y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;

        velX = 5;
        velY = 5;

        SpriteSheet ss = new SpriteSheet(Game.sprite_sheet);
        enemy_image = ss.grabImage(3, 1, 16, 16);
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

        //handler.addObject(new Trail(x, y, ID.Trail, Color.red, 16, 16, 0.1f, handler));

    }

    //draws our enemy
    public void render(Graphics g) {
        g.drawImage(enemy_image, (int) x, (int) y, null);

    }
    
}