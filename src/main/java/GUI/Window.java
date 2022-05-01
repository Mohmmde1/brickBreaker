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

    public Window(String title, Dimension dimension, boolean resizeable, boolean visibility, int onCloseOperation) {
        this(title);
        Window.dimension.width = dimension.width;
        Window.dimension.height = dimension.height;
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

        if(horizGap==null) horizGap = 0; centerX = ((int)screenSize.getWidth() - (horizGap*2) - 100 - dimension.width) /2;
        if(vertGap==null) vertGap   = 0; centerY = ((int)screenSize.getHeight() - (vertGap*2) - dimension.height) /2;

        frame.setBounds(centerX, centerY, dimension.width, dimension.height);
        frame.setResizable(resizeable);
        frame.setDefaultCloseOperation(onCloseOperation);
        frame.setVisible(visibility);
    }

    public static int getWidth() { return dimension.width;}
    public static int getHeight() { return dimension.height;}

    /**
     * Adds a game manager
     */
    public void initManager() { frame.add(new GameManager()); }

    protected JFrame frame;
    private int centerX = 0; 
    private int centerY = 0; 
    private static Dimension dimension = new Dimension(900, 600);
    private boolean resizeable = true;
    private boolean visibility = true;
    private int onCloseOperation = JFrame.EXIT_ON_CLOSE;
}
