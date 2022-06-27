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

        //setting bounds for our player
        x = Game.clamp(x, 0, Game.WIDTH - 48);
        y = Game.clamp(y, 0, Game.HEIGHT - 68);
    }

    public void render(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect(x, y, 32, 32);
    }


}
