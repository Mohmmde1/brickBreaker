package game.Interfaces;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

/**
 * Input interface that implements KeyListener and ActionListener interfaces
 */
public interface IKeyAction extends KeyListener, ActionListener 
{
    public void keyTyped(KeyEvent e);

    public void keyPressed(KeyEvent e);

    public void keyReleased(KeyEvent e);
    
    public void actionPerformed(ActionEvent e);
}
