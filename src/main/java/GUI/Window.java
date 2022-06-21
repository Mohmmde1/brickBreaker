package GUI;

import javax.swing.JFrame;
import java.awt.Dimension;

import game.components.GameManager;


public class Window {

    protected JFrame frame = new JFrame();
    private GameManager gameManager = new GameManager();


    /**
     * @param title
     * @param dimension
     * @param resizeable
     * @param visibility
     * @param onCloseOperation
     */
    public Window(String title, Dimension dimension, boolean resizeable, boolean visibility, int onCloseOperation) {
        frame.setTitle(title);
        frame.setSize(dimension);
        frame.setResizable(resizeable);
        frame.setVisible(visibility);
        frame.setDefaultCloseOperation(onCloseOperation);


    }

    public Window(){
        this(title, dimension, resizable, visibility, onCloseOperation);
    }

  
    public void fitPanels() {

        
        frame.addKeyListener(gameManager);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(GameManager.scoreManager);
        GameManager.scoreManager.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 39, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout gameManagerLayout = new javax.swing.GroupLayout(gameManager);
        gameManager.setLayout(gameManagerLayout);
        gameManagerLayout.setHorizontalGroup(
            gameManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 744, Short.MAX_VALUE)
        );
        gameManagerLayout.setVerticalGroup(
            gameManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 512, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(frame.getContentPane());
        frame.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(GameManager.scoreManager, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(gameManager, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(GameManager.scoreManager, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gameManager, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        frame.pack();
        gameManager.initComponents();
    } 

    

    private static String title = "BreakBreaker";
    private static boolean resizable = false;
    private static boolean visibility = true;
    private static int onCloseOperation = JFrame.EXIT_ON_CLOSE;
    private static Dimension dimension = new Dimension(900, 900);
}
