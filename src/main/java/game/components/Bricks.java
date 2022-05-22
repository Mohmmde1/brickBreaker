package game.components;

import java.util.Vector;
import java.awt.Graphics;
import java.awt.Point;
import java.io.IOException;
import java.awt.Dimension;


public class Bricks {

    private Vector<Brick> bricks = new Vector<Brick>();
    private int startX = 200;
    private int startY = 50;
    private int brickWidth = 50;
    private int brickHeight = 20;
    private int brickCount = 10;


    Bricks(){
        generate();
    }
    /*
    loops through @bricks 
    and check if there is interseciton
    */
    public boolean intersect(Projectile projectile) {
        for (Brick brick : bricks) {
            if (brick.intersects(projectile)) {
                brick.hit();
                if (brick.destroyed) bricks.remove(brick);
                return true;
            }
        }
        return false;
    }

    /*
    * darw the bricks
    */
    public void draw(Graphics g) throws IOException{
        for (Brick brick : bricks) {
            brick.draw(g);
        }
    }

    /*
    * generates @bricks
    */
    public void generate() {
        for (int i = 0; i < brickCount; i++) {
            for (int j = 0; j < brickCount; j++) {
                
                bricks.add(new Brick(new Point(
                startX + i * brickWidth, startY + j * brickHeight), 
                new Dimension(brickWidth, brickHeight)));
            }
        }
        
    }
}
