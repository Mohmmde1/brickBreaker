package game.components;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Graphics;

import java.io.IOException;
import java.util.Vector;

public class Trials {
    public final static int MAXTRIALS = 3;
    public int numTrials = MAXTRIALS;

    private Dimension dimension = new  Dimension(30, 5);
    private Vector<Live> lives;
    
    Trials(){  
        lives = new Vector<Live>(numTrials + 1);
        initLives();
    }

    private void initLives(){
        int xOffset = 20;
        int padding = 10;
        for (int i = 0; i < numTrials; i++) 
            lives.add(new Live(new Point(i * dimension.width + i * padding + xOffset, dimension.height), dimension));
    }

    public boolean onUpdate() {
        if (numTrials == 0) return false;
        numTrials--; 
        return true;
    }


    public void draw(Graphics g) throws IOException {
        for (int i = 0; i < numTrials; i++) 
            lives.get(i).draw(g);
    }

    public boolean equals(int value) {
        return numTrials == value;
    }

    class Live extends GameObject {
        Live(Point point, Dimension dimension) { super(point, dimension); }
    
        void draw(Graphics g) throws IOException {
            g.drawImage(loadBufferedImage("paddle.png"), x, y, dimension.width , dimension.height, null);   
        } 
    }
}