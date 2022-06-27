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
            //if the temp object is player2
            } else if (tempObject.getId() == ID.Player2) {
                //key events for player 2
                if (key == KeyEvent.VK_UP) {
                    tempObject.setVelY(-5);
                } else if (key == KeyEvent.VK_DOWN) {
                    tempObject.setVelY(5);
                } else if (key == KeyEvent.VK_LEFT) {
                    tempObject.setVelX(-5);
                } else if (key == KeyEvent.VK_RIGHT) {
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
            //if the temp object is player2
            } else if (tempObject.getId() == ID.Player2) {
                //key events for player 2
                if (key == KeyEvent.VK_UP) {
                    tempObject.setVelY(0);
                } else if (key == KeyEvent.VK_DOWN) {
                    tempObject.setVelY(0);
                } else if (key == KeyEvent.VK_LEFT) {
                    tempObject.setVelX(0);
                } else if (key == KeyEvent.VK_RIGHT) {
                    tempObject.setVelX(0);
                }
            }
        }
    }
    
    public void keyTyped(KeyEvent e) {
        
    }
        
}
