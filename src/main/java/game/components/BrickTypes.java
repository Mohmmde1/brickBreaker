package game.components;

import java.awt.Color;

/** Enumrate for brick types */
enum BrickTypes {
    VARIANTA(Color.RED, 1, "01-Breakout-Tiles.png"),
    VARIANTB(Color.YELLOW, 2, "02-Breakout-Tiles.png"),
    VARIANTC(Color.BLACK, 3, "03-Breakout-Tiles.png"),
    VARIANTD(Color.GRAY, 4, "03-Breakout-Tiles.png");

    public Color color;
    public int hits;
    public String img;

    BrickTypes(Color color, int hits, String img){
        this.color = color;
        this.hits = hits;
        this.img = img;
    }
}
