package game.components;

import GUI.Window;

import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Graphics;   
import java.awt.Point;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.Rectangle;
import java.io.IOException;

public class GameManager extends JPanel implements Input
{
    public GameManager() {
        paddle = new Paddle(new Point(Window.getWidth() / 2 - Paddle.xOffset, Window.getHeight() - Paddle.yOffset), new Dimension(60, 10));
        ball = new Projectile(new Point(paddle.x + Projectile.xOffset , paddle.y - Projectile.yOffset), new Dimension(10, 10));
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        timer= new Timer(delay, this);
		timer.start(); // Sends action events
    }

    public void paint(Graphics g) {
        final Graphics graphics = g;
        super.paint(g);

        try { paddle.draw(graphics); } catch (IOException e) { e.printStackTrace(); }
        try { ball.draw(graphics); } catch (IOException e) { e.printStackTrace(); }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
        movePaddle(Direction.RIGHT);
        else if (e.getKeyCode() == KeyEvent.VK_LEFT)
        movePaddle(Direction.LEFT);
        
        if (e.getKeyCode() == KeyEvent.VK_SPACE && Projectile.isIdle) {
            ball.randomize();
            isPlaying = true;
        }
        
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) { System.out.println("GameManager.keyTyped()"); }

    @Override
    public void keyReleased(KeyEvent e) { System.out.println("GameManager.keyReleased()"); }

    @Override
    public void actionPerformed(ActionEvent e) {
		timer.start(); // Sends action events
        if(isPlaying) { 
            repaint();
            ball.update();
            if(ball.x >= Window.dimension.width - ball.width || ball.x <= 0) { ball.dispX = -ball.dispX; }
            else if(ball.y <= 0 || (ball.intersects(paddle))) ball.dispY = -ball.dispY;
            repaint();
        }
    }

    private void movePaddle(Direction direction) {
        repaint(paddle.x, paddle.y, paddle.width, paddle.height);
        paddle.move(direction);
        repaint(paddle.x, paddle.y, paddle.width, paddle.height);
    }

    private final int delay = 10;
    private boolean isPlaying = false;
    private Timer timer;
    private Paddle paddle; 
    private Projectile ball; 
}
