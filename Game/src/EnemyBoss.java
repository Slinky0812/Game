import java.awt.*;
import java.util.*;

public class EnemyBoss extends GameObject {

    private Handler handler;
    private int timer = 80;
    private int timer2 = 50;
    Random r = new Random();

    //constructor
    public EnemyBoss(float x, float y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;

        velX = 0;
        velY = 2;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 64, 64);
    }

    //makes our enemy move
    public void tick() {
        x += velX;
        y += velY;

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
            
            
            int spawn = r.nextInt(10);
            if (spawn == 0) {
                handler.addObject(new EnemyBossBullet(x + 32, y + 32, ID.BasicEnemy, handler));
                //timer2 = 50;
            }
        }
        
        //makes our enemy bounce around the screen when they reach the edges
        if (x <= 0 || x >= Game.WIDTH - 64) {
            velX *= -1;
        }

        handler.addObject(new Trail(x, y, ID.Trail, Color.red, 64, 64, 0.1f, handler));

    }

    //draws our enemy
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int) x, (int) y, 64, 64);
    }
    
}