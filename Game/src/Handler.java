import java.util.*;
import java.awt.*;

//maintain or update and render objects in our game
public class Handler {

    //list of all game objects
    LinkedList<GameObject> objects = new LinkedList<GameObject>();
    public int speed = 5;

    public void tick() {
        //looping through every single game object in our game
        for (int i = 0; i < objects.size(); i++) {
            //set temp object to the current game object in the loop
            GameObject tempObject = objects.get(i);

            tempObject.tick();
        }
    }

    public void render(Graphics g) {
        //looping through every single game object in our game
        for (int i = 0; i < objects.size(); i++) {
            //set temp object to the current game object in the loop
            GameObject tempObject = objects.get(i);

            tempObject.render(g);
        }
    }

    public void clearEnemies() {
        objects.removeIf(object -> object.getId() != ID.Player);
    }

    //add game object to our list of game objects
    public void addObject (GameObject object) {
        this.objects.add(object);
    }

    //remove game object from our list of game objects
    public void removeObject (GameObject object) {
        this.objects.remove(object);
    }

}
