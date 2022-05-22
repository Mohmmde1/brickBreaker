package game.components;

import java.util.Vector;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Dimension;


public class Bricks {

    private Vector<Brick> bricks = new Vector<Brick>();
    private String pattren = "Random";
    private int startX = 100;
    private int startY = 50;
    private int brickWidth = 50;
    private int brickHeight = 20;
    private int brickCount = 10;

    Bricks(String pattren){
        this.pattren = pattren; 
        populate();
    }
    Bricks(){
        populate();
    }
    public boolean intersect(Projectile projectile) {
        for (Brick brick : bricks) {
            if (brick.intersects(projectile)) {
                brick.hit();
                bricks.remove(brick);
                return true;
            }
        }
        return false;
    }

    public void draw(Graphics g){
        for (Brick brick : bricks) {
            brick.draw(g);
        }
    }

    public void populate() {
        for (int i = 0; i < brickCount; i++) {
            for (int j = 0; j < brickCount; j++) {
                
                bricks.add(new Brick(new Point(
                startX + i * brickWidth, startY + j * brickHeight), 
                new Dimension(brickWidth, brickHeight)));
            }
        }
        
    }
}
