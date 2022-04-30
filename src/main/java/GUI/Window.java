package GUI;

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.Dimension;

import game.components.GameManager;

public class Window
{
    public Window(String title) {
        frame = new JFrame();
        frame.setTitle(title);
    }

    public Window(String title, int width, int height, boolean resizeable, boolean visibility, int onCloseOperation) {
        this(title);
        this.width = width;
        this.height = height;
        this.resizeable = resizeable;
        this.visibility = visibility;
        this.onCloseOperation = onCloseOperation;

        setWindowProps(0, 0);
    }

    /**
     * Initializes the window frame
     */
    public void setWindowProps(Integer horizGap, Integer vertGap) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        if(horizGap==null) horizGap = 0; int centerX = ((int)screenSize.getWidth() - (horizGap*2) - width) /2;
        if(vertGap==null) vertGap   = 0; int centerY = ((int)screenSize.getHeight() - (vertGap*2) - height) /2;

        frame.setBounds(centerX, centerY, width, height);
        frame.setResizable(resizeable);
        frame.setDefaultCloseOperation(onCloseOperation);
        frame.setVisible(visibility);
    }

    /**
     * Adds a game manager
     */
    public void initManager() {
        GameManager manager = new GameManager();
        frame.add(manager);
    }

    protected JFrame frame;
    private int width  = 800; 
    private int height = 600;
    private boolean resizeable = true;
    private boolean visibility = true;
    private int onCloseOperation = JFrame.EXIT_ON_CLOSE;
}
