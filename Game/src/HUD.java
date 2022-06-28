import java.awt.*;

public class HUD {

    public static int health = 100;
    private int greenValue = 255;

    private int score = 0;
    private int level = 1;

    public void tick() {
        health = Game.clamp(health, 0, 100);
        greenValue = Game.clamp(greenValue, 0, 255);

        //changes the colour of the health bar from green to red when losing health
        greenValue = 255 * health/100;

        //increment score
        score+=1;
    }

    public void render(Graphics g) {
        g.setColor(Color.gray);
        g.fillRect(15, 15, 200, 32);

        g.setColor(new Color(150, greenValue, 0));
        g.fillRect(15, 15, health * 2, 32);

        g.setColor(Color.white);
        //puts a border around the health bar
        g.drawRect(15, 15, 200, 32);


        g.drawString("Score: " + score, 15, 64);
        g.drawString("Level: " + level, 15, 80);
    }

    //setters
    public void setScore(int score) {
        this.score = score;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    //getters
    public int getScore() {
        return score;
    }

    public int getLevel() {
        return level;
    }

}
