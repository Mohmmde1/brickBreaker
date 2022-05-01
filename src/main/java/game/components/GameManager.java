package game.components;

import GUI.Window;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Timer;
import javax.swing.JPanel;
import java.awt.Graphics;   

public class GameManager extends JPanel implements Input{

    private boolean isPlaying;
    private Timer timer;
    private int displacement = 30;
    Paddle paddle; 

    public GameManager() {
        paddle = new Paddle(new Point((int)Window.width/2, Window.height - 50), 60, 10);
        addKeyListener(this);
        setFocusable(true);
    }

    private void movePaddle(int displacement, Dir dir) {

        repaint(paddle.x, paddle.y, paddle.width, paddle.height);
        paddle.move(displacement, dir);
        repaint(paddle.x, paddle.y, paddle.width, paddle.height);
    }

    public void paint(Graphics g) {
        super.paint(g);
        try {
            paddle.draw(g);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            movePaddle(displacement, Dir.RIGHT);
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            movePaddle(displacement, Dir.LEFT);
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

}
