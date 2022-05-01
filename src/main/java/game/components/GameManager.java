package game.components;

import GUI.Window;

import javax.swing.JPanel;

import java.awt.Graphics;   
import java.awt.Point;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import java.io.IOException;
import java.util.Timer;

public class GameManager extends JPanel implements Input
{
    public GameManager() {
        paddle = new Paddle(new Point(Window.getWidth() / 2, Window.getHeight() - Paddle.offset), new Dimension(60, 10));
        addKeyListener(this);
        setFocusable(true);
    }

    public void paint(Graphics g) {
        super.paint(g);
        try { paddle.draw(g); } 
        catch (IOException e) { e.printStackTrace(); }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            movePaddle(Direction.RIGHT);
        else if (e.getKeyCode() == KeyEvent.VK_LEFT)
            movePaddle(Direction.LEFT);
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
    }

    private void movePaddle(Direction direction) {
        repaint(paddle.x, paddle.y, paddle.width, paddle.height);
        paddle.move(direction);
        repaint(paddle.x, paddle.y, paddle.width, paddle.height);
    }

    private boolean isPlaying;
    private Timer timer;
    private Paddle paddle; 
}
