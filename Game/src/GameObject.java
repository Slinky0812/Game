import java.awt.*;

/**
 * a representation of the game objects on the screen
 * examples - enemies, player, bullets, etc.
 * 
 * @author Abdulla Moledina
 */
public abstract class GameObject {

    //ATTRIBUTES
    protected float x, y; //protected means it can only be accessed by classes that extend this class
    protected ID id; //ID variable to know which type of object it is
    protected float velX, velY; //controls the speed in our x and y directions


    /*
     * constructor for the game object
     * 
     * @param x the x coordinate of the game object
     * @param y the y coordinate of the game object
     * @param ID the ID of the game object
     */
    public GameObject(float x, float y, ID id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }
    
    /*
     * abstract methods to be implemented by the subclasses
     * 
     * tick - updates the game object
     * 
     * render - renders the game object
     * @param g the graphics object to be rendered
     * 
     * getBounds - gets the bounds of the game object
     * @return a rectangle representing the bounds of the game object
     */
    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();

    //SETTERS

    /*
     * sets the x coordinate of the game object
     * 
     * @param x the x coordinate of the game object
     */
    public void setX(float x) {
        this.x = x;
    }

    /*
     * sets the y coordinate of the game object
     * 
     * @param y the y coordinate of the game object
     */
    public void setY(float y) {
        this.y = y;
    }

    /*
     * sets the ID of the game object
     * 
     * @param id the ID of the game object
     */
    public void setId(ID id) {
        this.id = id;
    }

    /*
     * sets the velocity in the x direction of the game object
     * 
     * @param velX the velocity in the x direction of the game object
     */
    public void setVelX(float velX) {
        this.velX = velX;
    }

    /*
     * sets the velocity in the y direction of the game object
     * 
     * @param velY the velocity in the y direction of the game object
     */
    public void setVelY(float velY) {
        this.velY = velY;
    }

    //GETTERS

    /*
     * gets the x coordinate of the game object
     * 
     * @return the x coordinate of the game object
     */
    public float getX() {
        return x;
    }

    /*
     * gets the y coordinate of the game object
     * 
     * @return the y coordinate of the game object
     */
    public float getY() {
        return y;
    }

    /*
     * gets the ID of the game object
     * 
     * @return the ID of the game object
     */
    public ID getId() {
        return id;
    }

    /*
     * gets the velocity in the x direction of the game object
     * 
     * @return the velocity in the x direction of the game object
     */
    public float getVelX() {
        return velX;
    }

    /*
     * gets the velocity in the y direction of the game object
     * 
     * @return the velocity in the y direction of the game object
     */
    public float getVelY() {
        return velY;
    }

}
