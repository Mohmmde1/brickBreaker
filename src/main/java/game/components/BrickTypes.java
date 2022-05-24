package game.components;

import java.awt.Color;

/*
* enumrates for the @brick types
*/
enum BrickTypes {
    BRONZE(Color.green, 1, "01-Breakout-Tiles.png"),
    GOLDEN(Color.BLACK, 2, "03-Breakout-Tiles.png"),
    SILVER(Color.BLUE, 3, "05-Breakout-Tiles.png");

    public Color color;
    public int hits;
    public String img;

    BrickTypes(Color color, int hits, String img){
        this.color = color;
        this.hits = hits;
        this.img = img;
    }
}
