package utils;

import java.awt.image.BufferedImage;
import java.awt.GraphicsEnvironment;
import java.awt.Graphics2D;

public class Image {
    public static final String IMG_DIR = "Assets/img/";

    /**
     * @description Convert the image into a compatible format for local rendering.
     * @param img the bufferedImage
     * @return the converted image
     */
    public static BufferedImage optimizeImage(BufferedImage img) {
        BufferedImage tempImg = GraphicsEnvironment
            .getLocalGraphicsEnvironment()
            .getDefaultScreenDevice()
            .getDefaultConfiguration()
            .createCompatibleImage(
                img.getWidth(),
                img.getHeight(),
                img.getColorModel().getTransparency()
                // img.getColorModel().hasAlpha() ? Transparency.BITMASK
                // : Transparency.OPAQUE
                );
        Graphics2D g = tempImg.createGraphics();
        g.drawImage(img, 0, 0, null);
        g.dispose();

        return tempImg;
    }
}
