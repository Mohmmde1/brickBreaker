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
    private int brickCount = 0;
    public Canvas() { generate(); }

    public boolean equals(int brickCount) {
        return this.brickCount == brickCount;
    }

    /**
     * loops through bricks and checks if there is intersection
     * @param projectile
     */
    public boolean intersect(Projectile projectile) {
        for (Brick brick : bricks) {
            if (brick.intersects(projectile)) {
                brick.onHit();
                if (brick.destroyed){
                    bricks.remove(brick);
                    brickCount--;
                }
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
    private void generate() {
        for (int i = 0; i < tiles; i++) {
            if (i % 2 == 0) continue;
            for (int j = 0; j < tiles; j++) {
                if (j % 2 == 0) continue;
                bricks.add(new Brick(
                    new Point(startX + i * tileDim.width, startY + j * tileDim.height), 
                    new Dimension(tileDim.width, tileDim.height)
                ));
                brickCount++;
            }
        }

    }
}
