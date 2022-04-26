import java.awt.event.*;

interface Input extends KeyListener, ActionListener {

    public void actionPerformed(ActionEvent e);

    public void keyTyped(KeyEvent e);

    public void keyPressed(KeyEvent e);

    public void keyReleased(KeyEvent e);
    
}
