package game;

import javax.swing.JFrame;

import org.json.simple.parser.ParseException;
import java.io.IOException;

import java.awt.Dimension;

import GUI.Launcher;
import GUI.Window;
import utils.Config;

/**
 * Entrypoint
 */
public final class Application {
    public static void main(String[] args) { new Launcher(new Dimension(200, 150)); }

    public static void start() throws IOException, ParseException {
        gameWindow = new Window("Brick Breaker " + Config.getVersion(), Config.getWindowDimension(), true, true, JFrame.EXIT_ON_CLOSE);
        gameWindow.initManager();
    }

    private static Window gameWindow;
}
