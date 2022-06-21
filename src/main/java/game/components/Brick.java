package game.components;

import java.awt.Point;
import java.awt.Dimension;
import java.awt.Graphics;

import java.util.Random;
import java.io.IOException;

public class Brick extends GameObject {
    private BrickTypes type;
    private final BrickTypes[] types = BrickTypes.values();
    
    // to keep attract of how many bricks have been created
    public static int countBricks = 0; 
    public boolean destroyed = false;

    Brick(Point point, Dimension dimension) {
        super(point, dimension);
        type = types[(new Random().nextInt(types.length))];
        countBricks++;
    }

    /** OnHit event trigger */
    void onHit() {
        if (type.hits == 1) destroyed = true;
        if (destroyed) return;
        else type = types[type.hits-2];
    }

    /*
    * draw the brick
    */
    void draw(Graphics g) throws IOException{
        if (destroyed) return;
        // g.drawImage(loadBufferedImage(type.img), x, y, dimension.width, dimension.height, null
        g.setColor(type.color);
        g.fillRect(x, y, dimension.width, dimension.height);
    }
}
