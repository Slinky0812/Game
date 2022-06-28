import java.awt.*;

public class FastEnemy extends GameObject{
    private Handler handler;

    //constructor
    public FastEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;

        velX = 2;
        velY = 9;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 16, 16);
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

        handler.addObject(new Trail(x, y, ID.Trail, Color.CYAN, 16, 16, 0.1f, handler));

    }

    //draws our enemy
    public void render(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillRect(x, y, 16, 16);
    }
}