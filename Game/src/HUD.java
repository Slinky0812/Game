import java.awt.*;

public class HUD {

    public static int health = 100;

    public void tick() {
        health = Game.clamp(health, 0, 100);
    }

    public void render(Graphics g) {
        g.setColor(Color.gray);
        g.fillRect(15, 15, 200, 32);

        g.setColor(Color.green);
        g.fillRect(15, 15, health * 2, 32);

        g.setColor(Color.white);
        //puts a border around the health bar
        g.drawRect(15, 15, 200, 32);
    }


}
