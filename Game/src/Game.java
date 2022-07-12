import java.awt.*;
import java.util.*;
import java.awt.image.*;

/**
 * main class for the game.
 * 
 * @author Abdulla Moledina
 */
public class Game extends Canvas implements Runnable {

    //ATTRIBUTES
    public static final long serialVersionUID = 1L;
    public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9; //gives approx 16 by 9 ratio
    private Thread thread;     //entire game is gonna run within this thread
    private boolean running = false;

    public static boolean pause = false;
    public int difficulty = 0;
    //0 is normal
    //1 is hard

    private Random r;
    private Handler handler;
    private HUD hud;
    private Spawn spawn;
    private Menu menu;
    private Shop shop;

    public static STATE gameState = STATE.Menu;

    public static BufferedImage sprite_sheet;

    /*
     * constructor for the game
     */
    public Game() {
        BufferedImageLoader loader = new BufferedImageLoader();

        try {
            sprite_sheet = loader.loadImage("C:/Users/Abdullah Moledina/OneDrive - University of Leeds/Desktop/Game/Game/res/sprite_sheet.png");
        } catch (Exception e) {
            e.printStackTrace();
        }

        handler = new Handler();
        hud = new HUD();
        shop = new Shop(handler, hud);
        menu = new Menu(this, handler, hud);
        this.addKeyListener(new KeyInput(handler, this));
        this.addMouseListener(menu);
        this.addMouseListener(shop);

        new Window(WIDTH, HEIGHT, "Let's build a game!", this);

        spawn = new Spawn(handler, hud, this);

        r = new Random();

        //checks if the game is running
        if (gameState == STATE.Game) {     
            handler.addObject(new Player(WIDTH/2 - 32, HEIGHT/2 - 32, ID.Player, handler)); //player spawns in the middle of the screen
            handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler));
        } else {
            for (int i = 0; i < 20; i++) {
                handler.addObject(new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticle, handler));
            }
        }
    }

    /* 
     * starts the game and runs it in a thread
     */
    public synchronized void start() {
        //starting a new thread
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    /* 
     * stops the game and closes the thread
     */
    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            //run an error bug in our console and tell us what happened
            e.printStackTrace();
        }
    }

    /* 
     * runs the game
     */
    public void run() {
        //means the user doesn't need to click on the screen to use keyboard controls
        this.requestFocus();

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
                frames = 0;
            }
        }

        stop();
    }

    /* 
     * updates the game every tick
     */
    private void tick() {
        //checks if the game is running
        if (gameState == STATE.Game) {
            //updates the game if the game isn't paused
            if (!pause) {
                hud.tick();
                spawn.tick();
                handler.tick();

                //checks if the player has died
                if (HUD.health <= 0) {
                    HUD.health = 100;
                    gameState = STATE.End;
                    //removes all the enemies
                    handler.clearEnemies();
                    for (int i = 0; i < handler.objects.size(); i++) {
                        handler.removeObject(handler.objects.get(i));
                    }
                    //adds menu particles to the background of the game
                    for (int i = 0; i < 20; i++) {
                        handler.addObject(new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticle, handler));
                    }
                }
            }
        } else if (gameState == STATE.Menu || gameState == STATE.End || gameState == STATE.Select) {
            menu.tick();
            handler.tick();
        }
    }

    /* 
     * renders the game every tick
     */
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

        //rendering the game objects
        if (pause) {
            g.setColor(Color.white);
            g.drawString("Paused", WIDTH/2 - 50, HEIGHT/2);
        }

        //calls the render methods of certain classes depending on the state of the game.
        if (gameState == STATE.Game) {
            hud.render(g);
            handler.render(g);
        }else if (gameState == STATE.Shop) {
            shop.render(g);
        } else if (gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End || gameState == STATE.Select) {
            menu.render(g);
            handler.render(g);
        }

        g.dispose();
        bs.show();
    }

    /*
     * sets boundaries for game objects
     * 
     * @param var the variable we are checking
     * @param min the minimum value that the variable can be
     * @param max the maximum value that the variable can be
     * 
     * @return the variable that is within the boundaries
     */
    public static float clamp (float var, float min, float max) {
        //if our var is greater than max, return max so it doesn't go over max
        if (var >= max) {
            return var = max;
        //if our var is less than max, return max so it doesn't go over min
        } else if (var <= min) {
            return var = min;
        //our var is fine, return it
        } else {
            return var;
        }
    }

    /*
     * main method
     * 
     * @param args the command line arguments
     */
    public static void main (String args[]) {
        new Game();
    }
}