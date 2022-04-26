import javax.swing.JFrame;

public class Window {
  
    private String title;
    private int width;
    private int height;
    private boolean resizeable;
    private boolean visibility;
    private int onCloseOperation;
    
    public Window(String title, int width, int height, boolean resizeable, boolean visibility, int onCloseOperation) {
        this.title = title;
        this.width = width;
        this.height = height;
        this.resizeable = resizeable;
        this.visibility = visibility;
        this.onCloseOperation = onCloseOperation;
    }

    public void init() {
        JFrame window = new JFrame();
        // Game game = new Game();
        
        window.setBounds(10, 10, width, height);
	    window.setTitle(title);
	    window.setResizable(resizeable);
	    window.setVisible(visibility);
	    window.setDefaultCloseOperation(onCloseOperation);
        // window.add(game);
    }
    
}
