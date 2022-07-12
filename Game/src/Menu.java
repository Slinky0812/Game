import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

/**
 * a representation of the menu screen
 * inherits methods and attributes from MouseAdapter
 * 
 * @author Abdulla Moledina
 */
public class Menu extends MouseAdapter{

    //ATTRIBUTES
    private Game game;
    private Handler handler;
    private HUD hud;
    private Random r = new Random();

    /*
     * constructor for the menu screen
     * 
     * @param game the game object
     * @param handler the handler of the game
     * @param hud the HUD of the game
     */
    public Menu (Game game, Handler handler, HUD hud) {
        this.game = game;
        this.hud = hud;
        this.handler = handler;
    }

    /*
     * finds the position of the mouse and checks if it has clicked any of the boxes on the screen
     * 
     * @param e the mouse event
     */
    public void mousePressed(MouseEvent e) {
        //storing the x and y positions of the mouse
        int mx = e.getX();
        int my = e.getY();

        //menu screen
        if (Game.gameState == STATE.Menu) {
            //play button
            if (mouseOver(mx, my, 210, 150, 200, 64)) {
                Game.gameState = STATE.Select;
                return;
            }

            //help button
            if (mouseOver(mx, my, 210, 250, 200, 64)) {
                Game.gameState = STATE.Help;
            }

            //quit button
            if (mouseOver(mx, my, 210, 350, 200, 64)) {
                System.exit(1);
            }
        }

        //select screen
        if (Game.gameState == STATE.Select) {
            //nromal button
            if (mouseOver(mx, my, 210, 150, 200, 64)) {
                Game.gameState = STATE.Game;
                handler.addObject(new Player(Game.WIDTH/2 - 32, Game.HEIGHT/2 - 32, ID.Player, handler)); //player spawns in the middle of the screen
                handler.clearEnemies();
                handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy));

                game.difficulty = 0;

            }

            //hard button
            if (mouseOver(mx, my, 210, 250, 200, 64)) {
                Game.gameState = STATE.Game;
                handler.addObject(new Player(Game.WIDTH/2 - 32, Game.HEIGHT/2 - 32, ID.Player, handler)); //player spawns in the middle of the screen
                handler.clearEnemies();
                handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy));

                game.difficulty = 1;
            }

            //back button
            if (mouseOver(mx, my, 210, 350, 200, 64)) {
                Game.gameState = STATE.Menu;
                return;
            }
        }

        //help screen
        if (Game.gameState == STATE.Help) {
            //back button
            if (mouseOver(mx, my, 210, 350, 200, 64)) {
                Game.gameState = STATE.Menu;
                return;
            }
        }

        //end screen
        if (Game.gameState == STATE.End) {
            //play again button
            if (mouseOver(mx, my, 210, 350, 200, 64)) {
                Game.gameState = STATE.Menu;
                hud.setLevel(1);
                hud.setScore(0);
            }
        }
    }
    
    // public void mouseReleased(MouseEvent e) {
        
    // }

    /*
     * checks if the mouse is over a certain area
     * 
     * @param mx the x position of the mouse
     * @param my the y position of the mouse
     * @param x the x position of the box
     * @param y the y position of the box
     * @param width the width of the box
     * @param height the height of the box
     * @return true if the mouse is over the box, false otherwise
     */
    private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
        //checks if our mouse is within the bounds of our button
        if (mx > x && mx < x + width) {
            if (my > y && my < y + height) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    } 

    /*
     * updates the menu screen
     */
    public void tick() {

    }

    /*
     * draws the menu screen
     * 
     * @param g the graphics object
     */
    public void render (Graphics g) {

        //main menu screen
        if (Game.gameState == STATE.Menu) {
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);
    
            //title
            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Wave", 240, 70);
    
            //start button
            g.setFont(fnt2);
            g.drawRect(210, 150, 200, 64);
            g.drawString("Start", 270, 190);
    
            //help button
            g.drawRect(210, 250, 200, 64);
            g.drawString("Help", 270, 290);
    
            //quit button
            g.drawRect(210, 350, 200, 64);
            g.drawString("Quit", 270, 390);
        
        //help screen
        } else if (Game.gameState == STATE.Help) {
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);
            Font fnt3 = new Font("arial", 1, 20);
    
            //title
            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Help", 240, 70);

            //string of instructions
            g.setFont(fnt3);
            g.drawString("Use WASD to move and dodge the enemies!", 60, 200);

            //back button
            g.setFont(fnt2);
            g.drawRect(210, 350, 200, 64);
            g.drawString("Back", 270, 390);

        //game over screen
        } else if (Game.gameState == STATE.End) {
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);
            Font fnt3 = new Font("arial", 1, 20);
    
            //title
            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Game Over", 180, 70);

            //end string with score
            g.setFont(fnt3);
            g.drawString("You lost with a score of " + hud.getScore(), 175, 200);

            //play again button
            g.setFont(fnt2);
            g.drawRect(210, 350, 200, 64);
            g.drawString("Try Again", 245, 390);

        //select screen for difficulty
        } else if (Game.gameState == STATE.Select) {
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);
    
            //title
            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Select Difficulty", 140, 70);
    
            //normal button
            g.setFont(fnt2);
            g.drawRect(210, 150, 200, 64);
            g.drawString("Normal", 270, 190);
    
            //hard button
            g.drawRect(210, 250, 200, 64);
            g.drawString("Hard", 270, 290);
    
            //back button
            g.drawRect(210, 350, 200, 64);
            g.drawString("Back", 270, 390);
        }
    }
}
