package game.components;

import java.awt.Color;
enum BrickTypes {
    BRONZE(Color.BLACK),
    SILVER(Color.CYAN),
    GOLDEN(Color.BLUE);

    Color color;

    BrickTypes(Color color){
        this.color = color;
    }

    
}