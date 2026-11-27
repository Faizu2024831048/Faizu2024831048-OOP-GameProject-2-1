package webrunner.core;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import webrunner.entity.Player;

public class InputHandler extends KeyAdapter {

    private final Player player;

    public InputHandler(Player player) {
        this.player = player;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_SPACE || key == KeyEvent.VK_UP) {
            player.jump();
        } else if (key == KeyEvent.VK_DOWN) {
            player.duck();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            player.standUp();
        }
    }
}
