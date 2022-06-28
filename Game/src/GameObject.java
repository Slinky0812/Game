import java.awt.*;

//going to be what we refer to as all the game objects, e.g. enemies, coins, player, etc.
public abstract class GameObject {

    //protected means it can only be accessed by classes that extend this class
    protected int x, y;
    //ID variable to know which type of object it is
    protected ID id;
    //controls the speed in our x and y directions
    protected int velX, velY;

    //constructor
    public GameObject(int x, int y, ID id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }
    
    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();

    //setters
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }

    //getters
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public ID getId() {
        return id;
    }

    public int getVelX() {
        return velX;
    }

    public int getVelY() {
        return velY;
    }

}
