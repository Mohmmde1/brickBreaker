package game.components;

import java.awt.Dimension;
import java.awt.Point;
import java.io.IOException;
import java.util.Vector;


import java.awt.Graphics;



public class Trials{

    private Dimension dimension = new  Dimension(30, 5);
    private Vector<Live> lives;
    public int numTrials = 3;
    
    Trials(){
        lives = new Vector<Live>();
        _init_Trials();
    }


    public boolean hit() {
        if (numTrials == 0) return false;
        --numTrials; 
        return true;
    }

    public void draw(Graphics g) throws IOException {
        for (int i = 0; i < numTrials; i++) {
            lives.get(i).draw(g);
        };
    }

    private void _init_Trials(){
        int xOffset = 20;
        int space = 10;
        for (int i = 0; i < numTrials; i++) 
            lives.add(new Live(new Point(i * dimension.width + i * space + xOffset, dimension.height), dimension));
        
    }

}