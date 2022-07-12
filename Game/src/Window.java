import java.awt.*;
import javax.swing.*;

/**
 * a representation of the window to draw our game
 * inherits methods and attributes from Canvas
 */
public class Window extends Canvas {

    //ATTRIBUTE
    private static final long serialVersionUID = 1L;

    /*
     * constructor for the window object
     * 
     * @param width the width of the window
     * @param height the height of the window
     * @param title the title of the window
     * @param game the game object
     */
    public Window(int width, int height, String title, Game game) {
        //created a JFrame object, which is the frame for our window
        JFrame frame = new JFrame(title);

        //set the size of the frame
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));
        
        //having the close button working
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //not allowing our window to be resized
        frame.setResizable(false);
        //the frame starts in the middle of the screen
        frame.setLocationRelativeTo(null);
        //adding our game class to our frame
        frame.add(game);
        //making the frame visible
        frame.setVisible(true);
        //running the game
        game.start();
    }
}
