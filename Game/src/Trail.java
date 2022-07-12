import java.awt.*;

/**
 * a representation of the trail left behind by the enemies
 * inherits methods and attributes from GameObject
 * 
 * @author Abdulla Moledina
 */
public class Trail extends GameObject {

    //ATTRIBUTES
    private float alpha = 1;
    private Handler handler;
    private Color color;
    private int width, height;
    private float life;

    /*
     * constructor for the trail
     * 
     * @param x the x coordinate of the enemy
     * @param y the y coordinate of the enemy
     * @param ID the ID of the enemy
     * @param color the color of the enemy
     * @param width the width of the enemy
     * @param height the height of the enemy
     * @param life the life of the trail
     * @param handler the handler of the game
     */
    public Trail(float x, float y, ID id, Color color, int width, int height, float life, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        this.color = color;
        this.width = width;
        this.height = height;
        this.life = life;
    }

    /*
     * updates the trail and makes it move
     */
    public void tick() {
        if (alpha > life) {
            alpha -= (life - 0.001f);
        } else {
            handler.removeObject(this);
        }
    }

    /*
     * draws the trail
     * 
     * @param g the graphics object
     */
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(makeTransparent(alpha));

        g.setColor(color);
        g.fillRect((int) x, (int) y, width, height);

        g2d.setComposite(makeTransparent(1));
    }

    /*
     * makes the trail fade out
     * 
     * @param alpha the alpha value of the trail
     * @return a transparent composite
     */
    private AlphaComposite makeTransparent(float alpha) {
        int type = AlphaComposite.SRC_OVER;
        return(AlphaComposite.getInstance(type, alpha));
    }

    /*
     * gets the bounds of the trail and returns it
     * 
     * @return a rectangle representing the bounds of the trail
     */
    public Rectangle getBounds() {
        return null;
    }
        
}
