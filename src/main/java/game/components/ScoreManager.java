package game.components;

import game.Player;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.Color;

import javax.swing.JPanel;
import java.io.IOException;

public class ScoreManager extends JPanel {
    public ScoreManager() { trials = new Trials(); }

    @Override public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        super.paint(g2D);

        try { draw(g2D); } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public Trials getTrials() { return trials; }

    public void reset() {
        trials.numTrials = Trials.MAXTRIALS;
        Player.score = 0;
        currentScore = 0;
        repaint();
    }
    
    public void onUpdate() {
        boolean triggerEvent = (currentScore != Player.score);
        currentScore = Player.score;
        if(triggerEvent) repaint();
    }

    private void draw(Graphics2D g2D) throws IOException {
        trials.draw(g2D);
        g2D.setColor(Color.BLACK);
        g2D.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 15));
        g2D.drawString("Score " + currentScore, getWidth() / 2 - 30, 15);
        g2D.drawString("Personal Best " + Player.highestScore, getWidth() - 200, 15);
    }

    private Trials trials;
    private int currentScore = 0;
}
