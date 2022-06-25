import java.awt.*;


public class Game extends Canvas implements Runnable {

    //attributes
    public static final long serialVersionUID = 1L;
    public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9; //gives approx 16 by 9 ratio

    //constructor
    public Game() {
        new Window(WIDTH, HEIGHT, "Let's build a game!", this);
    }

    public synchronized void start() {

    }

    public void run() {

    }

    public static void main (String args[]) {
        new Game();
    }
}
