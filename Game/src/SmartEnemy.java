import java.awt.*;

/**
 * a representation of the smart enemy object
 * inherits methods and attributes from GameObject
 * 
 * @author Abdulla Moledina
 */
public class SmartEnemy extends GameObject {

    //ATTRIBUTE
    private Handler handler;
    private GameObject player;

    /*
     * constructor for the smart enemy object
     * 
     * @param x the x coordinate of the smart enemy
     * @param y the y coordinate of the smart enemy
     * @param ID the ID of the smart enemy
     * @param handler the handler of the game
     */
    public SmartEnemy(float x, float y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;

        //loop through the handler to find the player
        for (int i = 0; i < handler.objects.size(); i++) {
            if (handler.objects.get(i).getId() == ID.Player) {
                player = handler.objects.get(i);
            }
        }

    }

    /*
     * gets the bounds of the smart enemy and returns it
     * 
     * @return a rectangle representing the bounds of the smart enemy
     */
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 16, 16);
    }

    /*
     * updates the smart enemy and makes it move
     */
    public void tick() {
        x += velX;
        y += velY;

        //find the difference between the middle of our enemy and the middle of the player in x
        float diffX = x - player.getX() - 16;
        //same idea for y
        float diffY = y - player.getY() - 16;

        //find the distance between the player and the enemy using pythagoras
        float distance = (float) Math.sqrt( ((x-player.getX())*(x-player.getX())) + ((y-player.getY())*(y-player.getY())) );

        velX = ((float) ((-1/distance) * diffX));
        velY = ((float) ((-1/distance) * diffY));   

        //adds a trail behind the enemy
        handler.addObject(new Trail(x, y, ID.Trail, Color.green, 16, 16, 0.1f, handler));
    }

    /*
     * renders the smart enemy
     * 
     * @param g the graphics object to render to
     */
    public void render(Graphics g) {
        g.setColor(Color.green);
        g.fillRect((int) x, (int) y, 16, 16);
    }
    
}
