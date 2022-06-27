import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{

    private Handler handler;
        
    public KeyInput(Handler handler) {
        this.handler = handler;
    }
    
    //move the players in the direction intended when the correct key is pressed
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
                    tempObject.setVelY(-5);
                } else if (key == KeyEvent.VK_S) {
                    tempObject.setVelY(5);
                } else if (key == KeyEvent.VK_A) {
                    tempObject.setVelX(-5);
                } else if (key == KeyEvent.VK_D) {
                    tempObject.setVelX(5);
                }
            }
        }
    }
    
    //get the players to stop moving in that direction when key is released.
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
                    tempObject.setVelY(0);
                } else if (key == KeyEvent.VK_S) {
                    tempObject.setVelY(0);
                } else if (key == KeyEvent.VK_A) {
                    tempObject.setVelX(0);
                } else if (key == KeyEvent.VK_D) {
                    tempObject.setVelX(0);
                }
            }
        }

        //allows us to escape the game using the escape key
        if (key == KeyEvent.VK_ESCAPE) {
            System.exit(1);
        }
    }
    
    public void keyTyped(KeyEvent e) {
        
    }
        
}
