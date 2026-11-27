package webrunner.core;

import javax.swing.JFrame;


public class GameFrame extends JFrame {

    /** Creates and shows the game window. */
    public GameFrame() {
        setTitle(Constants.GAME_TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        GamePanel panel = new GamePanel();
        add(panel);
        pack();

        setLocationRelativeTo(null);
        setVisible(true);
        panel.requestFocusInWindow();
    }
}
