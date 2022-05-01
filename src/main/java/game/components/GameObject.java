package game.components;

import java.awt.Point;
import java.awt.Color;
import java.awt.Graphics;

import java.awt.Rectangle;

public class GameObject extends Rectangle {
     public Point point;

     GameObject(Point point, int width, int height) {
          super(point.x, point.y, width, height);
          this.point = point;
     }

     GameObject(Point point){
          this(point, 10, 10);
     }

     // public void draw(Graphics g) {
          // // TODO Auto-generated method stub
          // g.setColor(Color.RED);
          // g.fillRect(point.x, point.y, this.width, this.height);
          // g.setColor(Color.BLACK);
          // g.drawRect(point.x, point.y, this.width, this.height);
     // }

     // public void intersect(GameObject obj) {
     //      // TODO Auto-generated method stub
     // }

}
