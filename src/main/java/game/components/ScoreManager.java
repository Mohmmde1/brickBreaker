package game.components;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import java.io.IOException;

public class ScoreManager extends JPanel {
    public ScoreManager() { trials = new Trials(); }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        super.paint(g2D);

        try {
            trials.draw(g2D);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Trials getTrials() { return trials; }

    public void reset() {
        trials.numTrials = Trials.MAXTRIALS;
        repaint();
    }

    private Trials trials;
}
