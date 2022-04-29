package game.components;

import javax.swing.JFrame;

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

        setWindowProps();
    }

    /**
     * Initializes the window frame
     */
    public void setWindowProps() {
        frame.setBounds(0, 0, width, height);
        frame.setResizable(resizeable);
        frame.setDefaultCloseOperation(onCloseOperation);
        frame.setVisible(visibility);
    }

    /**
     * Add a manager
     */
    public void initManager() {
        GameManager manager = new GameManager();
        frame.add(manager);
    }

    JFrame frame;
    private int width  = 800; 
    private int height = 600;
    private boolean resizeable = true;
    private boolean visibility = true;
    private int onCloseOperation = JFrame.EXIT_ON_CLOSE;
}
