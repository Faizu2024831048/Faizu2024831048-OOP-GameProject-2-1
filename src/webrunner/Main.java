package webrunner;

import javax.swing.SwingUtilities;

import webrunner.core.GameFrame;

/**
 * Entry point of Web Runner. Its only job is to start the game window.
 */
public class Main {

    /**
     * Starts the game.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Swing windows must be created on Swing's own thread (the Event Dispatch Thread).
        SwingUtilities.invokeLater(() -> new GameFrame());
    }
}
