package game.components;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Graphics;
import java.io.IOException;

public class Live extends GameObject{
    
    Live(Point point, Dimension dimension) {
        super(point, dimension);
    }

    public void draw(Graphics g) throws IOException {
        g.drawImage(loadBufferedImage("paddle.png"), x, y, dimension.width , dimension.height, null);   
    }
    
}
