import java.awt.*;
import javax.swing.*;

public class Window extends Canvas {

    private static final long serialVersionUID = 1L;

    //constructor
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
