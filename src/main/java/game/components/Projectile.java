package game.components;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Point;
import java.awt.Dimension;
import java.io.IOException;
import java.util.Random;

public class Projectile extends GameObject
{
    public int dispX = 0;
    public int dispY = 6;
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
        g.drawImage(loadBufferedImage("ball.png"), x, y + width, dimension.width, dimension.height, null); 
    }

    /**
     * Randomizes the initial direction of the projectile
     */
    public void randomize() {
        int max = 4;
        int min = -4;

        dispX = new Random().nextInt(max - min) + min;
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

    /**
     * Determines whether or not this {@code Rectangle} and the specified
     * {@code Rectangle} intersect. Two rectangles intersect if
     * their intersection is nonempty.
     *
     * @param r the specified {@code Rectangle}
     * @return    {@code true} if the specified {@code Rectangle}
     *            and this {@code Rectangle} intersect;
     *            {@code false} otherwise.
     */
    @Override public boolean intersects(Rectangle r) {
        int tw = this.width;
        int th = this.height;
        int rw = r.width;
        int rh = r.height;
        if (rw <= 0 || rh <= 0 || tw <= 0 || th <= 0) {
            return false;
        }
        int tx = this.x;
        int ty = this.y;
        int rx = r.x;
        int ry = r.y - this.width;
        rw += rx;
        rh += ry;
        tw += tx;
        th += ty;
        //      overflow || intersect
        return ((rw < rx || rw > tx) &&
                (rh < ry || rh > ty) &&
                (tw < tx || tw > rx) &&
                (th < ty || th > ry));
    }

    /**
     * Controls the bouncing behavior of the ball when
     * it intersects with the paddle {@code Rectangle}
     * 
     * @param r is the paddle {@code Rectangle}
     */
    public void bounce(Rectangle r) {
        int rw = r.width;
        int rx = r.x;
        int tx = this.x;

        int partsNo = 3;
        int part = rw / partsNo;

        dispY = -dispY;

        if ((tx < rx + part) && dispX > 0) {
            dispX = -Math.abs(dispX);
            System.out.println("log: paddle's first part");
        } else if ((tx < rx + part)) {
            dispX--;
            System.out.println("log: paddle's first part");
        }

        if ((tx >= rx + part) && (tx <= rx + 2 * (rw / partsNo))) {
            dispX *= 0.75;
            System.out.println("log: paddle's second part");
        }

        
        if ((tx > rx + 2 * (rw / partsNo) && dispX < 0)) {
            dispX = Math.abs(dispX);
            System.out.println("log: paddle's third part");
        } else if ((tx > rx + 2 * (rw / partsNo))) {
            dispX++;
            System.out.println("log: paddle's third part");
        }

        System.out.println("log: ball bounced");
    }
}
