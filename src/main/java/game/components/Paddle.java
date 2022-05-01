package game.components;

import GUI.Window;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.Color;


public class Paddle  extends GameObject{

    public int displacement = 30;
    Paddle(Point point, int width, int height) {
        super(point, width, height);
    }

    Paddle(Point point){
        super(point);
    }

    /**
     * move the paddle with save guard
     * @param displacement
     * @param direction
     */
    public void move(int displacement, Dir direction) {
        if (Dir.LEFT == direction) {
            if (x - displacement > 0 )
                x -= displacement;
        } if (Dir.RIGHT == direction) {
            if ((x + width  + displacement) < Window.width) 
                x += displacement;
    }
}
    /** 
    * drawing the paddle
    */
    public void draw(Graphics g) throws IOException{
        g.setColor(Color.RED);
        final BufferedImage image = ImageIO.read(new File("src/main/java/game/img/paddle.png"));
        g.drawImage(image, x, y, width, height, null); 
    }
}
