package game.components;

import javax.swing.JFrame;

/**
 * Window class inherets from swing JFrame
 */
public class Window extends JFrame 
{
    private String title;
    private int width  = 800; 
    private int height = 600;
    private boolean resizeable = true;
    private boolean visibility = true;
    private int onCloseOperation = JFrame.EXIT_ON_CLOSE;

    public Window(String title) {
        this.title = title;
    }

    public Window(String title, int width, int height, boolean resizeable, boolean visibility, int onCloseOperation) {
        this(title);
        this.width = width;
        this.height = height;
        this.resizeable = resizeable;
        this.visibility = visibility;
        this.onCloseOperation = onCloseOperation;
    }

    /**
     * Initializes the window frame
     */
    public void init() {
        JFrame frame = new JFrame();
        // TODO Add GameManger
        // GameManager manager = new GameManager();

        frame.setTitle(title);
        frame.setBounds(0, 0, width, height);
        frame.setResizable(resizeable);
        frame.setDefaultCloseOperation(onCloseOperation);
        // frame.add(game);
        frame.setVisible(visibility);
    }

}
