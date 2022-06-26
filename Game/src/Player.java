import java.awt.*;
import java.util.*;

public class Player extends GameObject {
    
    Random r = new Random();

    //constructor
    public Player(int x, int y, ID id) {
        super(x, y, id);

        velX = r.nextInt(5) + 1;
        velY = r.nextInt(5);
    }


    //makes our player move
    public void tick() {
        x += velX;
        y += velY;
    }

    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x, y, 32, 32);
    }
}
