package game.components;

import java.util.Vector;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Dimension;
import java.io.IOException;

public class Canvas {

    private Dimension tileDim = new Dimension(50, 20);
    private Vector<Brick> bricks = new Vector<Brick>();
    private int startX = 100;
    private int startY = 50;
    private int tiles = 10;

    
    public static Canvas getInstance() {
        Canvas s_Instance = new Canvas();
        s_Instance.generate();
        return s_Instance;
    }

    /**
     * loops through bricks and checks if there is intersection
     * @param projectile
     */
    public boolean intersect(Projectile projectile) {
        for (Brick brick : bricks) {
            if (brick.intersects(projectile)) {
                brick.onHit();
                if (brick.destroyed)
                    bricks.remove(brick);
                return true;
            }
        }
        return false;
    }

    /**
     * draw the bricks
     * @param g
     * @throws IOException
     */
    public void draw(Graphics g) throws IOException {
        for (Brick brick : bricks) {
            brick.draw(g);
        }
    }

    /** Generates the bricks */
    public void generate() {
        for (int i = 0; i < tiles; i++) {
            if (i % 2 == 0) continue;
            for (int j = 0; j < tiles; j++) {
                if (j % 2 == 0) continue;
                bricks.add(new Brick(
                    new Point(startX + i * tileDim.width, startY + j * tileDim.height), 
                    new Dimension(tileDim.width, tileDim.height)
                ));
            }
        }

    }
}
