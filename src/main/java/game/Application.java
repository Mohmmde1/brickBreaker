package game;

import javax.swing.JFrame;

import game.components.Input;
import game.components.Window;
import game.components.GameManager;
/**
 * Entrypoint 
 */
public class Application 
{
    private static Window window;
    public static void main( String[] args ) 
    {
        window = new Window("Brick Breaker v0.1", 800, 600, true, true, JFrame.EXIT_ON_CLOSE);
        window.initManager();
    }
}
