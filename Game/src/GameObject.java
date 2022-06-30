import java.awt.*;

//going to be what we refer to as all the game objects, e.g. enemies, coins, player, etc.
public abstract class GameObject {

    //protected means it can only be accessed by classes that extend this class
    protected float x, y;
    //ID variable to know which type of object it is
    protected ID id;
    //controls the speed in our x and y directions
    protected float velX, velY;

    //constructor
    public GameObject(float x, float y, ID id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }
    
    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();

    //setters
    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }

    //getters
    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public ID getId() {
        return id;
    }

    public float getVelX() {
        return velX;
    }

    public float getVelY() {
        return velY;
    }

}
