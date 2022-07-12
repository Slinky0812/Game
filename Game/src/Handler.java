import java.util.*;
import java.awt.*;

/**
 * a representation of the handler used in our game
 * maintains or updates and renders the game objects in our game
 * 
 * @author Abdulla Moledina
 */
public class Handler {

    //ATTRIBUTES
    LinkedList<GameObject> objects = new LinkedList<GameObject>(); //list of all game objects
    public int speed = 5;

    /*
     * updates every object in the list of objects
     */
    public void tick() {
        //looping through every single game object in our game
        for (int i = 0; i < objects.size(); i++) {
            //set temp object to the current game object in the loop
            GameObject tempObject = objects.get(i);

            tempObject.tick();
        }
    }

    /*
     * renders every object in the list of objects
     * 
     * @param g the graphics object
     */
    public void render(Graphics g) {
        //looping through every single game object in our game
        for (int i = 0; i < objects.size(); i++) {
            //set temp object to the current game object in the loop
            GameObject tempObject = objects.get(i);

            tempObject.render(g);
        }
    }

    /*
     * removes all the objects that aren't the player
     */
    public void clearEnemies() {
        objects.removeIf(object -> object.getId() != ID.Player);
    }

    /*
     * adds an object to the list of objects
     * 
     * @param object the object to be added
     */
    public void addObject (GameObject object) {
        this.objects.add(object);
    }

    /*
     * removes an object from the list of objects
     * 
     * @param object the object to be removed
     */
    public void removeObject (GameObject object) {
        this.objects.remove(object);
    }

}
