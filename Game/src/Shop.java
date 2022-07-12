import java.awt.event.*;
import java.awt.*;

/**
 * a class to represent the shop screen
 * inherits methods and attributes from MouseAdapter
 * 
 * @author Abdulla Moledina
 */
public class Shop extends MouseAdapter {

    //ATTRIBUTES
    private Handler handler;
    private HUD hud;
    private int b1 = 100;
    private int b2 = 100;
    private int b3 = 100;
    
    /*
     * constructor for the shop screen
     * 
     * @param handler the handler of the game
     * @param hud the HUD of the game
     */
    public Shop(Handler handler, HUD hud) {
        this.handler = handler;
        this.hud = hud;
    }

    /*
     * renders the objects on the shop screen
     * 
     * @param g the graphics object
     */
    public void render (Graphics g) {
        g.setColor(Color.white);
        g.setFont(new Font("arial", 0, 48));
        g.drawString("SHOP", Game.WIDTH/2 - 100, 50);

        //box 1
        g.setFont(new Font("arial", 0, 12));
        g.drawString("Upgrade Health", 110, 120);
        g.drawString("Cost: " + b1, 110, 140);
        g.drawRect(100, 100, 100, 80);

        //box 2
        g.drawString("Upgrade speed", 260, 120);
        g.drawString("Cost: " + b2, 260, 140);
        g.drawRect(250, 100, 100, 80);

        //box 3
        g.drawString("Refill Health", 410, 120);
        g.drawString("Cost: " + b3, 410, 140);
        g.drawRect(400, 100, 100, 80);

        g.drawString("SCORE: " + hud.getScore(), Game.WIDTH/2 - 50, 300);
        g.drawString("Press SPACE to go back", Game.WIDTH/2 - 50, 330);
    }

    /*
     * finds where the mouse is and checks if the user clicked on a box
     * 
     * @param e the mouse event
     */
    public void mousePressed (MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        //box 1
        if (mx >= 100 && mx <= 200) {
            if (my >= 100 && my <= 180) {
                if (hud.getScore() >= b1) {
                    hud.setScore(hud.getScore() - b1);
                    b1 += 100;
                    hud.bounds += 20;
                    HUD.health = (100 + (hud.bounds / 2));
                }
            }
        }

        //box 2
        if (mx >= 250 && mx <= 350) {
            if (my >= 100 && my <= 180) {
                if (hud.getScore() >= b2) {
                    hud.setScore(hud.getScore() - b2);
                    b2 += 100;
                    handler.speed += 1;
                }
            }
        }

        //box 3
        if (mx >= 400 && mx <= 500) {
            if (my >= 100 && my <= 180) {
                if (hud.getScore() >= b3) {
                    hud.setScore(hud.getScore() - b3);
                    b3 += 100;
                    HUD.health = (100 + (hud.bounds / 2));
                }
            }
        }
    }

}
