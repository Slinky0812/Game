import java.awt.*;

/**
 * a class to represent the HUD of the game
 * 
 * @author Abdulla Moledina
 */
public class HUD {

    //ATTRIBUTES
    public int bounds = 0;
    public static float health = 100;
    private float greenValue = 255;

    private int score = 0;
    private int level = 1;

    /*
     * updates the HUD
     */
    public void tick() {
        health = Game.clamp(health, 0, 100 + (bounds / 2));
        
        //changes the colour of the health bar from green to red when losing health
        greenValue = health * 2;
        
        greenValue = Game.clamp(greenValue, 0, 255);

        //increment score
        score+=1;
    }

    /*
     * renders the HUD
     * 
     * @param g the graphics object
     */
    public void render(Graphics g) {
        g.setColor(Color.gray);
        g.fillRect(15, 15, 200 + bounds, 32);

        g.setColor(new Color(150, (int) greenValue, 0));
        g.fillRect(15, 15, (int) health * 2, 32);

        g.setColor(Color.white);
        //puts a border around the health bar
        g.drawRect(15, 15, 200 + bounds, 32);


        g.drawString("Score: " + score, 15, 64);
        g.drawString("Level: " + level, 15, 80);
        g.drawString("Space for shop", 15, 94);
    }

    //SETTERS

    /*
     * sets the health of the player
     * 
     * @param health the health of the player
     */
    public void setScore(int score) {
        this.score = score;
    }

    /*
     * sets the level of the player
     * 
     * @param level the level of the player
     */
    public void setLevel(int level) {
        this.level = level;
    }

    //GETTERS

    /*
     * gets the score of the player
     * 
     * @return the score of the player
     */
    public int getScore() {
        return score;
    }

    /*
     * gets the level of the player
     * 
     * @return the level of the player
     */
    public int getLevel() {
        return level;
    }

}
