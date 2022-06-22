package GUI;

import static utils.Image.IMG_DIR;
import game.Application;
import game.Player;
import network.Firebase;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;

public class Launcher extends Window 
{
    public Launcher(Dimension dimension) {
        super("Brick Breaker Game Launcher", dimension, false, true, JFrame.EXIT_ON_CLOSE);
        setWindowProps(horizGap, vertGap, dimension);
        initComponents(dimension);
    }

    /**
     * Centers the window
     * 
     * @param horizGap
     * @param vertGap
     * @param dimension
     */
    private void setWindowProps(Integer horizGap, Integer vertGap, Dimension dimension) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        centerX = (int) (screenSize.getWidth() - (horizGap * 2) - 100 - dimension.getWidth()) / 2;
        centerY = (int) ((screenSize.getHeight() - (vertGap * 2) - dimension.getHeight()) / 2);
    }

    private void initComponents(Dimension dimension) {
        final ImageIcon bkg = new ImageIcon(IMG_DIR + "bkg.png");
        final Dimension bkgSize = new Dimension(425, 110);
        final Font font = new Font(Font.MONOSPACED, Font.BOLD, 18);
        
        frame.setBounds(centerX, centerY, (int)dimension.getWidth(), (int)dimension.getHeight());

        mainPanel = new javax.swing.JPanel(){
            @Override public void paintComponent(Graphics g){
                g.drawImage(bkg.getImage(), 0, 0, null);
            }
        };

        nickname = new javax.swing.JTextField();
        labelName = new javax.swing.JLabel();
        buttonLabel = new javax.swing.JLabel();
        launchButton = new javax.swing.JButton();

        // Set the font 
        nickname.setFont(font);
        labelName.setFont(font);
        buttonLabel.setFont(font);
        launchButton.setFont(font);

        // Set the text color
        nickname.setForeground(Color.BLACK);
        labelName.setForeground(Color.WHITE);

        // Background jpanel integration
        mainPanel.setPreferredSize(bkgSize);
        mainPanel.setMinimumSize(bkgSize);
        mainPanel.setMaximumSize(bkgSize);
        mainPanel.setSize(bkgSize);
        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);

        mainPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(vertGap, horizGap, vertGap, horizGap));
        labelName.setText("NICKNAME:");
        launchButton.setText("  START GAME  ");

        launchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    OnLaunch(evt);
                } catch (IOException | ParseException | InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelName)
                    .addComponent(buttonLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nickname).addGap(190, 190, 190)
                .addGap(18, 18, 18)
                .addComponent(launchButton)
                .addGap(0, 65, Short.MAX_VALUE))
        ));
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelName)
                    .addComponent(nickname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonLabel)
                    .addComponent(launchButton))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(frame.getContentPane());
        frame.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        frame.pack();
    }
    
    /**
     *   This is the called to perform an action onSubmit
     * @throws ParseException
     * @throws IOException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private void OnLaunch(java.awt.event.ActionEvent evt) throws IOException, ParseException, InterruptedException, ExecutionException {
        Player.name = nickname.getText();
        if("".equals(Player.name)) {
            JOptionPane.showMessageDialog(frame, "Enter a nickname", "Missing player information", JOptionPane.ERROR_MESSAGE);
        } else {
            frame.dispose();
            if (Firebase.initialize()) { Firebase.uploadPlayerInfo(); }
            Application.start();
        }
    }

    private JButton launchButton;
    private JLabel buttonLabel;
    private JLabel labelName;
    private JPanel mainPanel;
    private JTextField nickname;
    private int horizGap = 50;
    private int vertGap = 10;
    private int centerX;
    private int centerY;
}
