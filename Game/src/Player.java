import java.awt.*;
import java.util.*;

public class Player extends GameObject {
    
    Random r = new Random();
    Handler handler;

    //constructor
    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }

    //makes our player move
    public void tick() {
        x += velX;
        y += velY;

        //setting bounds for our player
        x = Game.clamp(x, 0, Game.WIDTH - 48);
        y = Game.clamp(y, 0, Game.HEIGHT - 68);

        handler.addObject(new Trail(x, y, ID.Player, Color.white, 32, 32, 0.1f, handler));

        collision();
    }

    private void collision() {
        //loops through all the objects in the handler
        for (int i = 0; i < handler.objects.size(); i++) {
            //create temp object to store the object in the arraylist
            GameObject tempObject = handler.objects.get(i);

            //if the temp object is an enemy
            if (tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy) {
                //collision code
                if (getBounds().intersects(tempObject.getBounds())) {
                    HUD.health -= 5;
                }
            }
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect(x, y, 32, 32);
    }

}