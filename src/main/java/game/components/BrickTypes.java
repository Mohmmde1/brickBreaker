package game.components;

import java.awt.Color;

/** Enumrate for brick types */
enum BrickTypes {
    VARIANTA(Color.green, 1, "01-Breakout-Tiles.png"),
    VARIANTB(Color.BLACK, 2, "03-Breakout-Tiles.png"),
    VARIANTC(Color.BLUE, 3, "05-Breakout-Tiles.png");

    public Color color;
    public int hits;
    public String img;

    BrickTypes(Color color, int hits, String img){
        this.color = color;
        this.hits = hits;
        this.img = img;
    }
}
