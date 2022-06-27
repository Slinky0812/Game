import java.awt.*;

public class BasicEnemy extends GameObject {

    //constructor
    public BasicEnemy(int x, int y, ID id) {
        super(x, y, id);

        velX = 5;
        velY = 5;
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


    }

    //draws our enemy
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x, y, 16, 16);
    }
    
}
