package webrunner.entity;

import java.awt.Color;
import java.awt.Graphics2D;

import webrunner.core.Constants;

/**
 * One building in the city. Its roof is what the hero runs on.
 * Buildings move left every frame to make the world scroll.
 */
public class Building extends GameObject {

    private static final Color WALL_COLOR = new Color(40, 40, 58);
    private static final Color WINDOW_COLOR = new Color(255, 214, 110);

    public Building(int x, int width, int height) {
        super(x, Constants.HEIGHT - height, width, height);
    }

    /** Moves the building left. */
    @Override
    public void update() {
        setX(getX() - Constants.SPEED);
    }

    /** Draws the wall and a grid of lit windows. */
    @Override
    public void draw(Graphics2D g) {
        g.setColor(WALL_COLOR);
        g.fillRect(getX(), getY(), getWidth(), getHeight());

        g.setColor(WINDOW_COLOR);
        for (int wy = getY() + 20; wy < getBottom() - 20; wy += 40) {
            for (int wx = getX() + 20; wx < getRight() - 20; wx += 45) {
                g.fillRect(wx, wy, 14, 18);
            }
        }
    }
}
