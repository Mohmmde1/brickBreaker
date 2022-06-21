package game.components;

import java.awt.Graphics;
import java.io.IOException;

import javax.swing.JPanel;
import java.awt.Graphics2D;

public class ScoreManager extends JPanel {

    public Trials trials = new Trials();


    @Override
    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        super.paint(g2D);
        System.out.println("Here");

        try {
            trials.draw(g2D);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ScoreManager getInstance() {
        return new ScoreManager();
    }

}
