package webrunner.core;

import javax.swing.JFrame;


public class GameFrame extends JFrame {

    /** Creates and shows the game window. */
    public GameFrame() {
        setTitle(Constants.GAME_TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        add(new GamePanel());
        pack();

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
