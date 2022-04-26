package game.components;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Timer;
import javax.swing.JPanel;

public class GameManager extends JPanel implements Input
{
    private boolean isPlaying;
    private Timer timer;

    public GameManager() {}
    void paint() {}

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
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
