package game.components;

import java.awt.Color;


// enum BrickTypes {
//     BRONZE("bronzeBrick.png"),
//     SILVER("silverBrick.png"),
//     GOLDEN("goldBrick.png");

//     String img;

//     BrickTypes(String img){
//         this.img = img;
//     }

    
// }

/*
* enumrates for the @brick types
*/
enum BrickTypes {
    BRONZE(Color.green),
    SILVER(Color.BLUE),
    GOLDEN(Color.BLACK);

    Color color;

    BrickTypes(Color color){
        this.color = color;
    }

    

}
