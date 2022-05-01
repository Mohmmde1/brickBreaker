package game.components;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Dimension;
import java.io.IOException;
import java.awt.Color;
public class Projectile extends GameObject
{
    public int dispX = 1;
    public int dispY = 1;
    public final static int yOffset = 20;
    public final static int xOffset = 25;
    public static boolean isIdle = true;

    /**
     * @param point
     * @param dimension
     */
    Projectile(Point point, Dimension dimension) { super(point, dimension); }

    /**
     * Draws the ball
     * @param g
     * @throws IOException
     */
    public void draw(Graphics g) throws IOException {
        g.setColor(Color.RED);
        g.drawImage(loadBufferedImage("ball.png"), x, y+10, dimension.width, dimension.height, null); 
    }

    /**
     * Randomizes the initial direction of the projectile
     */
    public void randomize() {
        int max = 4;
        int min = -4;
        dispX = (int)Math.floor (Math.random()*(max-min+1)+min);
        dispY = (int)Math.floor(Math.random()*((max=-2)-min+1)+min);
        isIdle = false;
    }

    /**
     * Updates the position of the projectile
     */
    public void update() {
        if(isIdle == false) {
            x += dispX;
            y += dispY;
        }
    }

    // public void hit(){
    // // TODO Auto-generated method stub
    // }
}
