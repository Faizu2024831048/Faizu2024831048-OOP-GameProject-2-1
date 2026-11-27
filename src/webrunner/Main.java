package webrunner;

import javax.swing.SwingUtilities;

import webrunner.core.GameFrame;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GameFrame());
    }
}
