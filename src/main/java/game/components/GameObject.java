package game.components;

import java.awt.Rectangle;
import java.awt.Point;
import java.awt.Dimension;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import utils.Image;

import java.io.File;
import java.io.IOException;

public class GameObject extends Rectangle 
{
     protected Point point;
     protected Dimension dimension;

     GameObject(Point point, Dimension dimension) {
          super(point, dimension);
          this.point = point;
          this.dimension = dimension;
     }

     protected final BufferedImage loadBufferedImage(String filename) throws IOException {
          return Image.optimizeImage((BufferedImage)ImageIO.read(new File(Image.IMG_DIR, filename)));
     }
}
