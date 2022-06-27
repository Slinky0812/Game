import java.awt.*;
import java.util.*;

public class Player extends GameObject {
    
    Random r = new Random();

    //constructor
    public Player(int x, int y, ID id) {
        super(x, y, id);
    }


    //makes our player move
    public void tick() {
        x += velX;
        y += velY;
    }

    public void render(Graphics g) {
        if (id == ID.Player) {
            g.setColor(Color.blue);
            //g.fillRect(x, y, 32, 32);
        } else if (id == ID.Player2) {
            g.setColor(Color.red);
            //g.fillRect(x, y, 32, 32);
        }
        g.fillRect(x, y, 32, 32);
    }
}
