import java.awt.*;
import java.awt.image.BufferStrategy;


public class Game extends Canvas implements Runnable {

    //attributes
    public static final long serialVersionUID = 1L;
    public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9; //gives approx 16 by 9 ratio
    //entire game is gonna run within this thread
    private Thread thread;
    private boolean running = false;

    //constructor
    public Game() {
        new Window(WIDTH, HEIGHT, "Let's build a game!", this);
    }

    public synchronized void start() {
        //starting a new thread
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            //run an error bug in our console and tell us what happened
            e.printStackTrace();
        }


    }

    public void run() {
        //game loop, very commonly used        
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            if (running) {
                render();
            }
            frames++;
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }

        stop();

    }

    private void tick() {

    }

    private void render() {
        //clearing the screen
        BufferStrategy bs = this.getBufferStrategy();
        //bs is gonna be null if we don't have a buffer strategy
        if (bs == null) {
            //3 is the number of buffers we want to use. 
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        //colouring the blackground
        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.dispose();
        bs.show();
    }

    public static void main (String args[]) {
        new Game();
    }
}
