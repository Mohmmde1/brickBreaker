package game;

import javax.swing.JFrame;
import java.awt.Dimension;

import GUI.Launcher;
import GUI.Window;

/**
 * Entrypoint
 */
public final class Application {
    public static void main(String[] args) {
        new Launcher(new Dimension(200, 150));
    }

    public static void start() {
        gameWindow = new Window("Brick Breaker v0.1", new Dimension(900, 600), true, true, JFrame.EXIT_ON_CLOSE);
        gameWindow.initManager();
    }

    private static Window gameWindow;
}
