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
    public static void main(String[] args) throws IOException, ParseException {
        String env = (String) Config.dotenv.get("APP_ENV");
        if (env.equalsIgnoreCase("production")) {
            new Launcher(new Dimension(200, 150));
            return;
        }
        gameWindow = new Window();
        gameWindow.initManager();
    }

    public static void start() throws IOException, ParseException {
        gameWindow = new Window(Config.title() + " v" + Config.getVersion(), Config.getWindowDimension(), Config.resizable(), true, JFrame.EXIT_ON_CLOSE);
        gameWindow.initManager();
    }
    
    private static Window gameWindow;
}
