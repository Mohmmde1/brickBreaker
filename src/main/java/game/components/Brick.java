package game.components;

import java.awt.Point;
import java.io.IOException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;
public class Brick extends GameObject{
    private BrickTypes brickType;
    private BrickTypes[] arr = BrickTypes.values();
    
    public boolean destroyed = false;

   
    Brick(Point p, Dimension d) {
        super(p, d);
        brickType =  arr[(new Random().nextInt(arr.length))];
    }

    /* 
    * when brick gets hit by the ball 
    * it changes the @brickType
    */
    void hit() {
        int index = brickType.ordinal();
        if (destroyed) return;
        if(brickType.ordinal() == 0) destroyed = true;
        else brickType = arr[--index];
    }

    /*
    * draw the brick
    */
    void draw(Graphics g) throws IOException{
        if (destroyed) return;
        g.setColor(Color.BLACK);
        g.drawRect(point.x, point.y, dimension.width, dimension.height);   
        g.setColor(brickType.color);
        g.fillRect(point.x, point.y, dimension.width, dimension.height);
        // g.drawImage(loadBufferedImage(brickType.img), x, y, dimension.width, dimension.height, null); 
    }
}
