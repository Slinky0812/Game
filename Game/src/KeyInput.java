import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * a class to represent the key input
 * inherits methods and attributes from KeyAdapter
 * 
 * @author Abdulla Moledina
 */
public class KeyInput extends KeyAdapter{

    //ATTRIBUTES
    private Handler handler;
    private boolean[] keyDown = new boolean[4]; //boolean array to prevent sticky keys
    Game game;
        
    /*
     * constructor for the key input
     * 
     * @param handler the handler of the game
     * @param game the game object
     */
    public KeyInput(Handler handler, Game game) {
        this.handler = handler;
        this.game = game;

        keyDown[0] = false; //w
        keyDown[1] = false; //s
        keyDown[2] = false; //a
        keyDown[3] = false; //d
    }
    
    /*
     * checks for key presses
     * move the players in the direction intended when the correct key is pressed
     * 
     * @param e the key event
     */
    public void keyPressed(KeyEvent e) {
        //setting key equal to the ASCII value of the key that was pressed
        int key = e.getKeyCode();

        //looping through every single game object in our game
        for (int i = 0; i < handler.objects.size(); i++) {
            //set temp object to the current game object in the loop
            GameObject tempObject = handler.objects.get(i);
            //if the temp object is player1
            if (tempObject.getId() == ID.Player) {
                //key events for player 1
                if (key == KeyEvent.VK_W) {
                    tempObject.setVelY(-(handler.speed));
                    keyDown[0] = true;
                } else if (key == KeyEvent.VK_S) {
                    tempObject.setVelY(handler.speed);
                    keyDown[1] = true;
                } else if (key == KeyEvent.VK_A) {
                    tempObject.setVelX(-(handler.speed));
                    keyDown[2] = true;
                } else if (key == KeyEvent.VK_D) {
                    tempObject.setVelX(handler.speed);
                    keyDown[3] = true;
                }
            }
        }

        //pausing the game
        if (key == KeyEvent.VK_P) {
            if (Game.gameState == STATE.Game) {
                if (Game.pause) {
                    Game.pause = false;
                } else {
                    Game.pause = true;
                }
            }
        }

        //opening the shop menu
        if (key == KeyEvent.VK_SPACE) {
            if (Game.gameState == STATE.Game) {
                Game.gameState = STATE.Shop;
            } else if (Game.gameState == STATE.Shop) {
                Game.gameState = STATE.Game;
            }
        }
    }
    
    /*
     * checks for key releases
     * stops the player from moving in the direction intended when the correct key is released
     * 
     * @param e the key event
     */
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        //looping through every single game object in our game
        for (int i = 0; i < handler.objects.size(); i++) {
            //set temp object to the current game object in the loop
            GameObject tempObject = handler.objects.get(i);
            //if the temp object is player1
            if (tempObject.getId() == ID.Player) {
                //key events for player 1
                if (key == KeyEvent.VK_W) {
                    keyDown[0] = false;
                } else if (key == KeyEvent.VK_S) {
                    keyDown[1] = false;
                } else if (key == KeyEvent.VK_A) {
                    keyDown[2] = false;
                } else if (key == KeyEvent.VK_D) {
                    keyDown[3] = false;
                }
                
                //vertical movement
                if (!keyDown[0] && !keyDown[1]) {
                    tempObject.setVelY(0);
                }
                //horizontal movement
                if (!keyDown[2] && !keyDown[3]) {
                    tempObject.setVelX(0);
                }
            }   
        }

        //allows us to escape the game using the escape key
        if (key == KeyEvent.VK_ESCAPE) {
            System.exit(1);
        }
    }
        
}
