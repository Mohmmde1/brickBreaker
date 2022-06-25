package game.components;

import javax.swing.JPanel;
import javax.swing.Timer;

import org.json.simple.parser.ParseException;

import game.Player;
import game.Interfaces.IKeyAction;
import network.Firebase;
import utils.Config;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.awt.Color;

public class GameManager extends JPanel implements IKeyAction {

    public static ScoreManager scoreManager;

    public GameManager() {
        timer = new Timer(DELAY, this);
        timer.start(); // Sends action events
        scoreManager = new ScoreManager();
    }

    public void initComponents() {
        paddle = new Paddle(new Point(getWidth() / 2, getHeight() - Paddle.yOffset), new Dimension(60, 10));
        ball = new Projectile(new Point(paddle.x + Projectile.xOffset, paddle.y - Projectile.yOffset), new Dimension(10, 10));
        canva = new Canvas();
    }

    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        super.paint(g2D);

        try {
            if (scoreManager.getTrials().equals(0)) {
                onGameOver(g2D);
            } else {
                paddle.draw(g2D);
                ball.draw(g2D);
                canva.draw(g2D);
            }
        } catch (IOException e) { e.printStackTrace(); }
    }

    @Override public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            movePaddle(Direction.RIGHT);
        else if (e.getKeyCode() == KeyEvent.VK_LEFT)
            movePaddle(Direction.LEFT);

        if (e.getKeyCode() == KeyEvent.VK_SPACE
            || e.getKeyCode() == KeyEvent.VK_RIGHT
            || e.getKeyCode() == KeyEvent.VK_LEFT
            && Projectile.isIdle) { isPlaying = true; }

        if (e.getKeyCode() == KeyEvent.VK_SPACE && Projectile.isIdle) ball.randomize();
        
        if (e.getKeyCode() == KeyEvent.VK_R && isPlaying==false) { 
            if (Player.highestScore < Player.score) Player.highestScore = Player.score;
            try { 
                Config.updatePlayer(true);
                if (Player.connected) Firebase.uploadPlayerInfo();
            } catch (InterruptedException | ExecutionException | IOException | ParseException e1) { e1.printStackTrace(); }
            restart();
        }
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE && isPlaying) System.exit(1);
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE && !isPlaying) {
            if(Player.highestScore < Player.score) Player.highestScore = Player.score;
            try { 
                Config.updatePlayer(true);
                if (Player.connected) Firebase.uploadPlayerInfo();
            } catch (InterruptedException | ExecutionException | IOException | ParseException e1) { e1.printStackTrace(); }
            System.exit(1);
        }
        
        repaint();
    }

    @Override public void keyTyped(KeyEvent e) { System.out.println("GameManager.keyTyped()"); }
    @Override public void keyReleased(KeyEvent e) { System.out.println("GameManager.keyReleased()"); }

    @Override public void actionPerformed(ActionEvent e) {
        if (isPlaying) {
            repaint();
            ball.update();
            if (Projectile.isIdle) {
                ball.x = paddle.x + Projectile.xOffset;
                ball.y = paddle.y - Projectile.yOffset;
            }
            if (canva.intersect(ball)) {
                scoreManager.onUpdate();
                ball.dispY = -ball.dispY;
            }
            if (ball.x > getWidth() - (ball.width * 2) || ball.x <= 0) {
                ball.dispX = -ball.dispX;
            } else if (ball.y <= 0 - ball.height) {
                ball.dispY = -ball.dispY;
            } else if ((ball.intersects(paddle))) {
                ball.bounce(paddle);
            } else if (ball.y > getHeight()) {
                Projectile.isIdle = true;
                if (scoreManager.getTrials().onUpdate()) scoreManager.repaint();
                else isPlaying = false;
            }
            repaint();
        }
    }

    private void movePaddle(Direction direction) {
        repaint(paddle.x, paddle.y, paddle.width, paddle.height);
        paddle.move(direction, getWidth());
        repaint(paddle.x, paddle.y, paddle.width, paddle.height);
    }

    public void onGameOver(Graphics g) {
        isPlaying = false;
        final int yOffset = 50;

        g.setColor(Color.RED);
        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 50));
        g.drawString("Game Over", getWidth() / 2 - 120, getHeight() / 2);

        g.setColor(Color.BLACK);
        g.setFont(new Font(Font.MONOSPACED, Font.ITALIC, 20));
        g.drawString("Press R to try again", getWidth() / 2 - 110, getHeight() / 2 + yOffset);
        g.drawString("Press ESC to exit", getWidth() / 2 - 100, getHeight() / 2 + (yOffset * 2));
    }

    public void restart() {
        initComponents();
        scoreManager.reset();
    }

    private final int DELAY = 2;
    private boolean isPlaying = false;
    private Timer timer;
    private Paddle paddle;
    private Projectile ball;
    private Canvas canva;
}
