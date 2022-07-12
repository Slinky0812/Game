import java.awt.*;
import java.util.*;
import java.awt.image.*;

/**
 * a representation of the player object
 * inherits attributes and methods from GameObject
 * 
 * @author Abdulla Moledina
 */
public class Player extends GameObject {
    
    //ATTRIBUTES
    Random r = new Random();
    Handler handler;
    private BufferedImage player_image;

    /*
     * constructor for the player object
     * 
     * @param x the x coordinate of the player
     * @param y the y coordinate of the player
     * @param ID the ID of the player
     * @param handler the handler of the game
     */
    public Player(float x, float y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;

        //load the player image
        SpriteSheet ss = new SpriteSheet(Game.sprite_sheet);
        player_image = ss.grabImage(1, 1, 32, 32);
    }

    /*
     * gets the bounds of the player and returns it
     * 
     * @return a rectangle representing the bounds of the player
     */
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 32, 32);
    }

    /*
     * updates the player and makes it move
     */
    public void tick() {
        x += velX;
        y += velY;

        //setting bounds for our player
        x = Game.clamp(x, 0, Game.WIDTH - 48);
        y = Game.clamp(y, 0, Game.HEIGHT - 68);

        collision();
    }

    /*
     * checks for collisions with the player and the objects in the game
     */
    private void collision() {
        //loops through all the objects in the handler
        for (int i = 0; i < handler.objects.size(); i++) {
            //create temp object to store the object in the arraylist
            GameObject tempObject = handler.objects.get(i);

            //if the temp object is an enemy
            if (tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy || tempObject.getId() == ID.SmartEnemy) {
                //collision code
                if (getBounds().intersects(tempObject.getBounds())) {
                    HUD.health -= 5;
                }
            }
        }
    }
    
    /*
     * renders the player
     * 
     * @param g the graphics object
     */
    public void render(Graphics g) {
        g.drawImage(player_image, (int) x, (int) y, null);
    }

}