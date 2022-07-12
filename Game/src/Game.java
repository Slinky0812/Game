import java.awt.*;
//import java.awt.image.BufferStrategy;
import java.util.*;
import java.awt.image.*;
//import javax.imageio.*;


public class Game extends Canvas implements Runnable {

    //attributes
    public static final long serialVersionUID = 1L;
    public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9; //gives approx 16 by 9 ratio
    //entire game is gonna run within this thread
    private Thread thread;
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

    public static STATE gameState = STATE.Menu;

    public static BufferedImage sprite_sheet;

    //constructor
    public Game() {

        BufferedImageLoader loader = new BufferedImageLoader();

        try {
            sprite_sheet = loader.loadImage("C:/Users/Abdullah Moledina/OneDrive - University of Leeds/Desktop/Game/Game/res/sprite_sheet.png");
            System.out.print("loaded");
        } catch (Exception e) {
            e.printStackTrace();
        }

        handler = new Handler();
        hud = new HUD();
        menu = new Menu(this, handler, hud);
        this.addKeyListener(new KeyInput(handler, this));
        this.addMouseListener(menu);

        new Window(WIDTH, HEIGHT, "Let's build a game!", this);

        spawn = new Spawn(handler, hud, this);

        //menu = new Menu(Game, Handler);

        r = new Random();

        if (gameState == STATE.Game) {     
            handler.addObject(new Player(WIDTH/2 - 32, HEIGHT/2 - 32, ID.Player, handler)); //player spawns in the middle of the screen
            handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler));
        } else {
            for (int i = 0; i < 20; i++) {
                handler.addObject(new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticle, handler));
            }
        }
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

    private void tick() {
        if (gameState == STATE.Game) {
            if (!pause) {
                hud.tick();
                spawn.tick();
                handler.tick();

                if (hud.health <= 0) {
                    hud.health = 100;
                    gameState = STATE.End;
                    handler.clearEnemies();
                    for (int i = 0; i < handler.objects.size(); i++) {
                        handler.removeObject(handler.objects.get(i));
                    }
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
        handler.render(g);
        if(pause) {
            g.setColor(Color.white);
            g.drawString("Paused", WIDTH/2 - 50, HEIGHT/2);
        }

        if (gameState == STATE.Game) {
            hud.render(g);
        } else if (gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End || gameState == STATE.Select) {
            menu.render(g);
        }

        g.dispose();
        bs.show();
    }

    //sets our boundaries for our player
    public static float clamp (float var, float min, float max) {
        //if our var is greater than max, return max so it doesn't go over max
        if (var >= max) {
            return var = max;
        //if our var is less than max, return max so it doesn't go over min
        } else if (var <= min) {
            return var = min;
        } else {
            return var;
        }
    }

    public static void main (String args[]) {
        new Game();
    }
}