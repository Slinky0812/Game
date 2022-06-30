import java.awt.*;

public class SmartEnemy extends GameObject {

    private Handler handler;
    private GameObject player;

    //constructor
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

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 16, 16);
    }

    //makes our enemy move'
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

        handler.addObject(new Trail(x, y, ID.Trail, Color.green, 16, 16, 0.1f, handler));

    }

    //draws our enemy
    public void render(Graphics g) {
        g.setColor(Color.green);
        g.fillRect((int) x, (int) y, 16, 16);
    }
    
}
