package webrunner.core;

import javax.swing.JFrame;

/**
 * The main window of the game.
 * It extends JFrame (inheritance), so it gets all window features from Swing
 * and only sets them up for Web Runner.
 */
public class GameFrame extends JFrame {

    /** Creates and shows the game window. */
    public GameFrame() {
        setTitle(Constants.GAME_TITLE);
        setSize(Constants.WIDTH, Constants.HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null); // null = center on the screen
        setVisible(true);
    }
}
