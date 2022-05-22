package game.components;

import java.awt.Point;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;
public class Brick extends GameObject{
    BrickTypes strength;
    BrickTypes[] arr = BrickTypes.values();
    
    boolean destroyed = false;

   
    Brick(Point p, Dimension d) {
        super(p, d);
        strength =  arr[(new Random().nextInt()) % 1];
    }

    void hit() {
        if (destroyed) return;
        if(strength == BrickTypes.BRONZE) destroyed = true;
        int index = strength.ordinal()-1;
        strength = arr[index+1];
    }

    void draw(Graphics g){
        if (destroyed) return;
        g.setColor(strength.color);
     
        g.drawRect(point.x, point.y, dimension.width, dimension.height);   
        
    }
}
